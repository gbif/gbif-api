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
import org.gbif.api.service.registry.OrganizationService;
import org.gbif.api.vocabulary.Country;

import javax.annotation.Nullable;

/**
 * Pages through all organizations optionally filtering by country.
 */
public class OrganizationPager extends EntityPager<Organization> {

    private final OrganizationService service;
    private final @Nullable Country country;

    public OrganizationPager(OrganizationService service, @Nullable Country country, int pageSize) {
        super(pageSize);
        this.service = service;
        this.country = country;
    }

    @Override
    PagingResponse<Organization> nextPage(PagingRequest page) {
        if (country != null) {
            return service.listByCountry(country, page);
        } else {
            return service.list(page);
        }
    }

}
