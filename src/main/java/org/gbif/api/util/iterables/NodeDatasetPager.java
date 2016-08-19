package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.NodeService;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Pages through all datasets endorsed by a given node.
 */
public class NodeDatasetPager extends DatasetBasePager {

    private final NodeService service;
    private final UUID nodeKey;

    public NodeDatasetPager(NodeService service, UUID nodeKey, @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.service = service;
        this.nodeKey = nodeKey;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        return service.endorsedDatasets(nodeKey, page);
    }

}
