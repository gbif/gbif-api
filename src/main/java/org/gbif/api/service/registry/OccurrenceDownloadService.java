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
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.occurrence.Download;
import org.gbif.api.model.occurrence.DownloadStatistics;
import org.gbif.api.model.registry.CountryOccurrenceDownloadUsage;
import org.gbif.api.model.registry.DatasetOccurrenceDownloadUsage;
import org.gbif.api.model.registry.OrganizationOccurrenceDownloadUsage;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.CountryUsageSortField;
import org.gbif.api.vocabulary.DatasetUsageSortField;
import org.gbif.api.vocabulary.OrganizationUsageSortField;
import org.gbif.api.vocabulary.SortOrder;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Interface to access and persist information about occurrence download events. */
@SuppressWarnings("unused")
public interface OccurrenceDownloadService {

  /**
   * Persists the occurrence download object. The object must contain a unique key, the persistence
   * storage doesn't generate one for it.
   */
  void create(@NotNull @Valid Download download);

  /** Retrieves a occurrence download by its unique key or DOI. */
  Download get(@NotNull String keyOrDoi);

  /**
   * Retrieves a pageable result of all the downloads, optionally the downloads can be filtered by
   * status and source.
   */
  PagingResponse<Download> list(
      @Nullable Pageable page, @Nullable Set<Download.Status> status, @Nullable String source);

  /** Retrieves a pageable result of the downloads created by a user in a given status. */
  PagingResponse<Download> listByUser(
      @NotNull String user,
      @Nullable Pageable page,
      @Nullable Set<Download.Status> status,
      Date from,
      Boolean statistics);

  /**
   * Retrieves a pageable result of the downloads created by a user in a given status.
   *
   * <p>Internal use only; behaviour may change without notice.
   */
  PagingResponse<Download> listByEraseAfter(
      @Nullable Pageable page,
      @Nullable String eraseAfter,
      @Nullable Long size,
      @Nullable String erasureNotification);

  /** Update an existing occurrence download. */
  void update(@NotNull @Valid Download download);

  /**
   * Retrieves a pageable result of the dataset usages in a occurrence download.
   *
   * <p>The Downloads in the DatasetOccurrenceDownloadUsages are null, to avoid redundant repetition
   * of potentially large objects.
   */
  PagingResponse<DatasetOccurrenceDownloadUsage> listDatasetUsages(
      @NotNull String keyOrDoi, @Nullable Pageable page);

  PagingResponse<DatasetOccurrenceDownloadUsage> listDatasetUsages(
      @NotNull String keyOrDoi,
      @Nullable String datasetTitle,
      @Nullable DatasetUsageSortField sortBy,
      @Nullable SortOrder sortOrder,
      @Nullable Pageable page);

  PagingResponse<OrganizationOccurrenceDownloadUsage> listOrganizationUsages(
      @NotNull String keyOrDoi,
      @Nullable String organizationTitle,
      @Nullable OrganizationUsageSortField sortBy,
      @Nullable SortOrder sortOrder,
      @Nullable Pageable page);

  PagingResponse<CountryOccurrenceDownloadUsage> listCountryUsages(
      @NotNull String keyOrDoi,
      @Nullable CountryUsageSortField sortBy,
      @Nullable SortOrder sortOrder,
      @Nullable Pageable page);

  /** Retrieve citation details of a download by its unique key or DOI. */
  String getCitation(@NotNull String keyOrDoi);

  /** Retrieves downloads monthly stats by country (user and publishing country) and dataset. */
  Map<Integer, Map<Integer, Long>> getDownloadsByUserCountry(
      @Nullable Date fromDate, @Nullable Date toDate, @Nullable Country userCountry);

  /** Retrieves downloads monthly stats by source. */
  Map<Integer, Map<Integer, Long>> getDownloadsBySource(
      @Nullable Date fromDate, @Nullable Date toDate, @Nullable String source);

  /**
   * Retrieves downloaded records monthly stats by country (user and publishing country) and
   * dataset.
   */
  Map<Integer, Map<Integer, Long>> getDownloadedRecordsByDataset(
      @Nullable Date fromDate,
      @Nullable Date toDate,
      @Nullable Country publishingCountry,
      @Nullable UUID datasetKey,
      @Nullable UUID publishingOrgKey);

  /** Retrieves downloads monthly stats by country (user and publishing country) and dataset. */
  Map<Integer, Map<Integer, Long>> getDownloadsByDataset(
      @Nullable Date fromDate,
      @Nullable Date toDate,
      @Nullable Country publishingCountry,
      @Nullable UUID datasetKey,
      @Nullable UUID publishingOrgKey);

  /** Retrieves downloads monthly stats by country (user and publishing country) and dataset. */
  PagingResponse<DownloadStatistics> getDownloadStatistics(
      @Nullable Date fromDate,
      @Nullable Date toDate,
      @Nullable Country publishingCountry,
      @Nullable UUID datasetKey,
      @Nullable UUID publishingOrgKey,
      @Nullable Pageable page);

  /**
   * Persists usages of datasets in an occurrence download.
   *
   * @param downloadKey downloadkey of the datasets' usage information.
   * @param datasetCitations map of datasetkey as key and number of records as value.
   */
  void createUsages(@NotNull String downloadKey, @NotNull Map<UUID, Long> datasetCitations);
}
