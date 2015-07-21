package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingConstants;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.DatasetService;
import org.gbif.api.service.registry.InstallationService;
import org.gbif.api.service.registry.OrganizationService;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory constructing dataset iterables that exclude deleted datasets.
 */
public class DatasetPagerFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DatasetPagerFactory.class);

    /**
     * @param key a valid dataset, organization or installation key. If null all datasets will be iterated over
     * @throws IllegalArgumentException if given key is not existing
     */
    public static Iterable<Dataset> datasets(@Nullable UUID key, @Nullable DatasetType type, DatasetService ds, OrganizationService os, InstallationService is) {
        return datasets(key, type, ds, os, is, PagingConstants.DEFAULT_PARAM_LIMIT);
    }

    /**
     * Returns a dataset iterable by testing the given registry key first to see whether it is a dataset, organization or installation.
     * In case of an organization key the published datasets will be returned.
     *
     * @param key a valid dataset, organization or installation key. If null all datasets will be iterated over
     * @param pageSize to use when talking to the registry
     * @throws IllegalArgumentException if given key is not existing
     */
    public static Iterable<Dataset> datasets(@Nullable UUID key, @Nullable DatasetType type, DatasetService ds, OrganizationService os, InstallationService is, int pageSize) {
        if (key == null) {
            LOG.info("Iterate over all {} datasets", type == null ? "" : type);
            return new DatasetPager(ds, type, pageSize);

        } else if (isDataset(key, ds)) {
            LOG.info("Iterate over dataset {}", key);
            return ImmutableList.of(ds.get(key));

        } else if (isOrganization(key, os)) {
            LOG.info("Iterate over all {} datasets published by {}", type == null ? "" : type, key);
            return new OrgPublishingPager(os, key, type, pageSize);

        } else if (isInstallation(key, is)) {
            LOG.info("Iterate over all {} datasets hosted by installation {}", type == null ? "" : type, key);
            return new InstallationPager(is, key, type, pageSize);
        }
        throw new IllegalArgumentException("Given key is no valid GBIF registry key: " + key);
    }

    /**
     * @param type an optional filter to just include the given dataset type
     */
    public static Iterable<Dataset> allDatasets(@Nullable DatasetType type, DatasetService service) {
        LOG.info("Iterate over all {} datasets", type == null ? "" : type);
        return new DatasetPager(service, type, PagingConstants.DEFAULT_PARAM_LIMIT);
    }

    /**
     * @param key a valid organization key
     * @param type an optional filter to just include the given dataset type
     */
    public static Iterable<Dataset> publishedDatasets(UUID key, @Nullable DatasetType type, OrganizationService service) {
        LOG.info("Iterate over all {} datasets published by {}", type == null ? "" : type, key);
        return new OrgPublishingPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
    }

    /**
     * @param key a valid organization key
     * @param type an optional filter to just include the given dataset type
     */
    public static Iterable<Dataset> hostedDatasets(UUID key, @Nullable DatasetType type, OrganizationService service) {
        LOG.info("Iterate over all {} datasets hosted organization by {}", type == null ? "" : type, key);
        return new OrgHostingPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
    }

    /**
     * @param key a valid installation key
     * @param type an optional filter to just include the given dataset type
     */
    public static Iterable<Dataset> hostedDatasets(UUID key, @Nullable DatasetType type, InstallationService service) {
        LOG.info("Iterate over all {} datasets hosted organization by {}", type == null ? "" : type, key);
        return new InstallationPager(service, key, type, PagingConstants.DEFAULT_PARAM_LIMIT);
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
}
