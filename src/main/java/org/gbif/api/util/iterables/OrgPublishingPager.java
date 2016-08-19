package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.OrganizationService;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Iterates over all datasets published by a given organisation.
 */
public class OrgPublishingPager extends DatasetBasePager {

    private final OrganizationService os;
    private final UUID orgKey;

    public OrgPublishingPager(OrganizationService os, UUID orgKey, @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.os = os;
        this.orgKey = orgKey;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        return os.publishedDatasets(orgKey, page);
    }

}
