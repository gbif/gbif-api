package org.gbif.api.util.iterables;

import org.gbif.api.model.registry.Dataset;
import org.gbif.api.vocabulary.DatasetType;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator over datasets from paging responses that filters out deleted and constituent datasets
 * It also allows for an optional type filter.
 */
abstract class DatasetBasePager extends EntityPager<Dataset> {
    private static final Logger LOG = LoggerFactory.getLogger(DatasetBasePager.class);
    private final DatasetType type;

    /**
     * @param pageSize to use when talking to the registry
     * @param type the accepted dataset type, null for all
     */
    public DatasetBasePager(@Nullable DatasetType type, int pageSize) {
        super(pageSize);
        this.type = type;
    }

    @Override
    protected boolean exclude(Dataset d) {
        if (d.getParentDatasetKey() != null) {
            LOG.debug("Ignore constituent dataset {} {} of parent {}", d.getKey(), d.getTitle().replaceAll("\n", " "), d.getParentDatasetKey());
        } else if (type != null && d.getType() != type) {
            LOG.debug("Ignore {} dataset {}: {}", d.getType(), d.getKey(), d.getTitle().replaceAll("\n", " "));
        }
        return false;
    }
}
