package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.DatasetService;

import java.util.UUID;

/**
 * Iterates over all dataset constituents of a given super dataset.
 */
public class DatasetConstituentPager extends DatasetBasePager {

    private final DatasetService service;
    private final UUID datasetKey;

    public DatasetConstituentPager(DatasetService service, UUID datasetKey, int pageSize) {
        super(null, pageSize);
        this.service = service;
        this.datasetKey = datasetKey;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        return service.listConstituents(datasetKey, page);
    }

}
