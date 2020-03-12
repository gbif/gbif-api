/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.util.iterables;

import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.service.registry.NodeService;

import java.util.UUID;

/**
 * Pages through all organizations endorsed by a given node.
 */
public class NodeOrganizationPager extends EntityPager<Organization> {

    private final NodeService service;
    private final UUID endorsingNodeKey;

    public NodeOrganizationPager(NodeService service, UUID endorsingNodeKey, int pageSize) {
        super(pageSize);
        this.service = service;
        this.endorsingNodeKey = endorsingNodeKey;
    }

    @Override
    PagingResponse<Organization> nextPage(PagingRequest page) {
        return service.endorsedOrganizations(endorsingNodeKey, page);
    }

}
