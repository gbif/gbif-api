/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.util.iterables;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.descriptors.Descriptor;
import org.gbif.api.model.collections.request.CollectionSearchRequest;
import org.gbif.api.model.collections.request.DescriptorSearchRequest;
import org.gbif.api.model.collections.request.InstitutionSearchRequest;
import org.gbif.api.model.collections.view.CollectionView;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingConstants;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.occurrence.DownloadStatistics;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.DatasetOccurrenceDownloadUsage;
import org.gbif.api.model.registry.Node;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.model.registry.search.DatasetSearchRequest;
import org.gbif.api.model.registry.search.DatasetSearchResult;
import org.gbif.api.service.collections.CollectionService;
import org.gbif.api.service.collections.DescriptorsService;
import org.gbif.api.service.collections.InstitutionService;
import org.gbif.api.service.registry.DatasetSearchService;
import org.gbif.api.service.registry.DatasetService;
import org.gbif.api.service.registry.InstallationService;
import org.gbif.api.service.registry.NetworkService;
import org.gbif.api.service.registry.NodeService;
import org.gbif.api.service.registry.OccurrenceDownloadService;
import org.gbif.api.service.registry.OrganizationService;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Factory constructing registry entity iterables using specific pagers under the hood. */
@SuppressWarnings("unused")
public class Iterables {
  private static final Logger LOG = LoggerFactory.getLogger(Iterables.class);

  /** Private default constructor. */
  private Iterables() {
    // empty private constructor
  }

  /**
   * @param key a valid dataset, organization or installation key. If null all datasets will be
   *     iterated over
   * @throws IllegalArgumentException if given key is not existing
   */
  public static Iterable<Dataset> datasets(
      @Nullable UUID key,
      @Nullable DatasetType type,
      DatasetService ds,
      OrganizationService os,
      InstallationService is,
      NetworkService ns,
      NodeService nos) {
    return datasets(key, type, ds, os, is, ns, nos, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * Returns a dataset iterable by testing the given registry key first to see whether it is a
   * dataset, organization or installation. In case of an organization key the published datasets
   * will be returned.
   *
   * @param key a valid dataset, organization or installation key. If null all datasets will be
   *     iterated over
   * @param pageSize to use when talking to the registry
   * @throws IllegalArgumentException if given key is not existing
   */
  public static Iterable<Dataset> datasets(
      @Nullable UUID key,
      @Nullable DatasetType type,
      DatasetService ds,
      OrganizationService os,
      InstallationService is,
      NetworkService ns,
      NodeService nos,
      int pageSize) {
    if (key == null) {
      LOG.info("Iterate over all {} datasets", type == null ? "" : type);
      return new DatasetPager(ds, type, pageSize);

    } else if (isDataset(key, ds)) {
      LOG.info("Iterate over dataset {}", key);
      return Collections.singletonList(ds.get(key));

    } else if (isOrganization(key, os)) {
      LOG.info("Iterate over all {} datasets published by {}", type == null ? "" : type, key);
      return new OrgPublishingPager(os, key, type, pageSize);

    } else if (isInstallation(key, is)) {
      LOG.info(
          "Iterate over all {} datasets hosted by installation {}", type == null ? "" : type, key);
      return new InstallationPager(is, key, type, pageSize);

    } else if (isNode(key, nos)) {
      LOG.info("Iterate over all {} datasets endorsed by node {}", type == null ? "" : type, key);
      return new NetworkPager(ns, key, type, pageSize);

    } else if (isNetwork(key, ns)) {
      LOG.info(
          "Iterate over all {} datasets belonging to network {}", type == null ? "" : type, key);
      return new NodeDatasetPager(nos, key, type, pageSize);
    }
    throw new IllegalArgumentException("Given key is no valid GBIF registry key: " + key);
  }

  /**
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> datasets(@Nullable DatasetType type, DatasetService service) {
    LOG.info("Iterate over all {} datasets", type == null ? "" : type);
    return new DatasetPager(service, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /** Iterates over dataset search results. */
  public static Iterable<DatasetSearchResult> datasetSearchResults(
      @Nullable DatasetSearchRequest datasetSearchRequest,
      DatasetSearchService datasetSearchService,
      @Nullable Integer limit) {
    return new DatasetSearchResultsPager(
        datasetSearchService,
        datasetSearchRequest,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /**
   * @param key a valid organization key
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> publishedDatasets(
      UUID key, @Nullable DatasetType type, OrganizationService service) {
    LOG.info("Iterate over all {} datasets published by {}", type == null ? "" : type, key);
    return new OrgPublishingPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param key a valid organization key
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> hostedDatasets(
      UUID key, @Nullable DatasetType type, OrganizationService service) {
    LOG.info(
        "Iterate over all {} datasets hosted by organization {}", type == null ? "" : type, key);
    return new OrgHostingPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param key a valid installation key
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> hostedDatasets(
      UUID key, @Nullable DatasetType type, InstallationService service) {
    LOG.info(
        "Iterate over all {} datasets hosted by installation {}", type == null ? "" : type, key);
    return new InstallationPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param key a valid dataset key
   */
  public static Iterable<Dataset> constituentDatasets(UUID key, DatasetService service) {
    LOG.info("Iterate over all constituent datasets of {}", key);
    return new DatasetConstituentPager(service, key, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * Iterates over all constituents of a given network.
   *
   * @param key a valid network key
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> networkDatasets(
      UUID key, @Nullable DatasetType type, NetworkService service) {
    LOG.info("Iterate over all {} datasets belonging to network {}", type == null ? "" : type, key);
    return new NetworkPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param nodeKey a valid endorsing node key
   * @param type an optional filter to just include the given dataset type
   */
  public static Iterable<Dataset> endorsedDatasets(
      UUID nodeKey, @Nullable DatasetType type, NodeService service) {
    LOG.info("Iterate over all {} datasets endorsed by node {}", type == null ? "" : type, nodeKey);
    return new NodeDatasetPager(service, nodeKey, type, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param pager producer function of next page response
   * @return a dataset iterable based on producer function
   */
  public static Iterable<Dataset> datasetsIterable(
      Function<PagingRequest, PagingResponse<Dataset>> pager) {
    return new DatasetBasePager(null, PagingConstants.DEFAULT_PARAM_LIMIT) {
      @Override
      public PagingResponse<Dataset> nextPage(PagingRequest page) {
        return pager.apply(page);
      }
    };
  }

  /**
   * @param country an optional country filter
   */
  public static Iterable<Organization> organizations(
      @Nullable Country country, OrganizationService service) {
    LOG.info("Iterate over all organizations {}", country == null ? "" : "from country " + country);
    return new OrganizationPager(service, country, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * @param nodeKey a valid endorsing node key
   */
  public static Iterable<Organization> endorsedOrganizations(UUID nodeKey, NodeService service) {
    LOG.info("Iterate over all organizations endorsed by node {}", nodeKey);
    return new NodeOrganizationPager(service, nodeKey, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /** Iterate over all endorsing nodes */
  public static Iterable<Node> nodes(NodeService service) {
    LOG.info("Iterate over all nodes");
    return new NodePager(service, PagingConstants.DEFAULT_PARAM_LIMIT);
  }

  /**
   * Iterable for {@link OccurrenceDownloadService#getDownloadStatistics(Date, Date, Country, UUID,
   * UUID, Pageable)}.
   */
  public static Iterable<DownloadStatistics> downloadStatistics(
      OccurrenceDownloadService service,
      @Nullable Date fromDate,
      @Nullable Date toDate,
      @Nullable Country publishingCountry,
      @Nullable UUID datasetKey,
      @Nullable UUID publishingOrgKey,
      @Nullable Integer limit) {
    LOG.info("Iterate over download statistics");
    return new DownloadStatisticPager(
        service,
        fromDate,
        toDate,
        publishingCountry,
        datasetKey,
        publishingOrgKey,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /** Iterable for {@link OccurrenceDownloadService#listDatasetUsages(String, Pageable)}. */
  public static Iterable<DatasetOccurrenceDownloadUsage> datasetOccurrenceDownloadUsages(
      OccurrenceDownloadService service, String downloadKey, @Nullable Integer limit) {
    LOG.info("Iterate over download dataset usages of download {}", downloadKey);
    return new DatasetOccurrenceDownloadUsagesPager(
        service,
        downloadKey,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /** Iterable for {@link CollectionService#list(CollectionSearchRequest)}. */
  public static Iterable<CollectionView> collections(
      CollectionSearchRequest searchRequest, CollectionService service, @Nullable Integer limit) {
    LOG.info("Iterating over a collection's search results");
    return new CollectionsPager(
        service,
        searchRequest,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /** Iterable for {@link InstitutionService#list(InstitutionSearchRequest)}. */
  public static Iterable<Institution> institutions(
      InstitutionSearchRequest searchRequest, InstitutionService service, @Nullable Integer limit) {
    LOG.info("Iterating over a institution's search results");
    return new InstitutionsPager(
        service,
        searchRequest,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /** Iterable for {@link DescriptorsService#listDescriptors(DescriptorSearchRequest)}. */
  public static Iterable<Descriptor> descriptors(
      DescriptorsService service, DescriptorSearchRequest searchRequest, @Nullable Integer limit) {
    LOG.info("Iterating over a collection descriptor's search results");
    return new CollectionDescriptorPager(
        service,
        searchRequest,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  /**
   * Iterable for the verbatim fields of {@link
   * DescriptorsService#listDescriptors(DescriptorSearchRequest)}.
   */
  public static Iterable<Map<String, String>> descriptorVerbatims(
      DescriptorsService service, DescriptorSearchRequest searchRequest, @Nullable Integer limit) {
    LOG.info("Iterating over a collection descriptor's search results");
    return new CollectionDescriptorVerbatimPager(
        service,
        searchRequest,
        Optional.ofNullable(limit).orElse(PagingConstants.DEFAULT_PARAM_LIMIT));
  }

  private static boolean isDataset(UUID key, DatasetService ds) {
    return ds.get(key) != null;
  }

  private static boolean isOrganization(UUID key, OrganizationService os) {
    return os.get(key) != null;
  }

  private static boolean isInstallation(UUID key, InstallationService is) {
    return is.get(key) != null;
  }

  private static boolean isNetwork(UUID key, NetworkService ns) {
    return ns.get(key) != null;
  }

  private static boolean isNode(UUID key, NodeService ns) {
    return ns.get(key) != null;
  }
}
