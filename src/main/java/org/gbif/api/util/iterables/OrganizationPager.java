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
