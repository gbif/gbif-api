package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.service.registry.InstallationService;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Iterates over all datasets hosted by a given installation.
 */
public class InstallationPager extends DatasetBasePager {

    private final InstallationService service;
    private final UUID installationKey;

    public InstallationPager(InstallationService service, UUID installationKey,
                             @Nullable DatasetType type, int pageSize) {
        super(type, pageSize);
        this.service = service;
        this.installationKey = installationKey;
    }

    @Override
    PagingResponse<Dataset> nextPage(PagingRequest page) {
        return service.getHostedDatasets(installationKey, page);
    }

}
