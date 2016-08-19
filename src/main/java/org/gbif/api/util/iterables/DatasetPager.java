package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.DatasetService;
import org.gbif.api.vocabulary.DatasetType;

import javax.annotation.Nullable;

/**
 * Pages through all datasets filtering by type only.
 */
public class DatasetPager extends DatasetBasePager {

    private final DatasetService ds;
    private final DatasetType type;

    public DatasetPager(DatasetService ds, @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.ds = ds;
        this.type = type;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        if (type == null) {
            return ds.list(page);
        } else {
            return ds.listByType(type, page);
        }
    }

}
