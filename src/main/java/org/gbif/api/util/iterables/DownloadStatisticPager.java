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

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.occurrence.DownloadStatistics;
import org.gbif.api.service.registry.OccurrenceDownloadService;
import org.gbif.api.vocabulary.Country;

import java.util.Date;
import java.util.UUID;

import jakarta.annotation.Nullable;

/**
 * Iterates over results of {@link OccurrenceDownloadService#getDownloadStatistics(Date, Date, Country, UUID, UUID, Pageable)}.
 */
public class DownloadStatisticPager extends BasePager<DownloadStatistics> {

    private final OccurrenceDownloadService service;
    private final Date fromDate;
    private final Date toDate;
    private final Country publishingCountry;
    private final UUID datasetKey;
    private final UUID publishingOrgKey;

    public DownloadStatisticPager(OccurrenceDownloadService service,
                                  @Nullable Date fromDate,
                                  @Nullable Date toDate,
                                  @Nullable Country publishingCountry,
                                  @Nullable UUID datasetKey,
                                  @Nullable UUID publishingOrgKey,
                                  int pageSize) {
        super(pageSize);
        this.service = service;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.publishingCountry = publishingCountry;
        this.datasetKey = datasetKey;
        this.publishingOrgKey = publishingOrgKey;

    }

    @Override
    public PagingResponse<DownloadStatistics> nextPage(PagingRequest page) {
        return service.getDownloadStatistics(fromDate, toDate, publishingCountry, datasetKey, publishingOrgKey, page);
    }

}
