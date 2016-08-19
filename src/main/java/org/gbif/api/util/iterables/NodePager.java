package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Node;
import org.gbif.api.service.registry.NodeService;

/**
 * Pages through all organizations optionally filtering by country.
 */
public class NodePager extends EntityPager<Node> {

    private final NodeService service;

    public NodePager(NodeService service, int pageSize) {
        super(pageSize);
        this.service = service;
    }

    @Override
    PagingResponse<Node> nextPage(PagingRequest page) {
        return service.list(page);
    }

}
