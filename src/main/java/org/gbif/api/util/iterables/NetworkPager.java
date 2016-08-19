package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.NetworkService;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Iterates over all datasets belonging to a given network.
 */
public class NetworkPager extends DatasetBasePager {

    private final NetworkService service;
    private final UUID key;

    public NetworkPager(NetworkService service, UUID key, @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.service = service;
        this.key = key;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        return service.listConstituents(key, page);
    }

}
