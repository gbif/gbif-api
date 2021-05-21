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
import org.gbif.api.model.registry.search.DatasetSearchRequest;
import org.gbif.api.model.registry.search.DatasetSearchResult;
import org.gbif.api.service.registry.DatasetSearchService;

import javax.annotation.Nullable;

/**
 * Pages through all datasets search results.
 */
public class DatasetSearchResultsPager extends BasePager<DatasetSearchResult> {

    private final DatasetSearchService datasetSearchService;
    private final DatasetSearchRequest datasetSearchRequest;

    public DatasetSearchResultsPager(DatasetSearchService datasetSearchService,
                                     @Nullable DatasetSearchRequest datasetSearchRequest,
                                     int pageSize) {
      super(pageSize);
      this.datasetSearchService = datasetSearchService;
      this.datasetSearchRequest = datasetSearchRequest;
    }

    @Override
    PagingResponse<DatasetSearchResult> nextPage(PagingRequest page) {
      datasetSearchRequest.copyPagingValues(page);
      return datasetSearchService.search(datasetSearchRequest);
    }


}
