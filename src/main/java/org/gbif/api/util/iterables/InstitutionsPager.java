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

import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.request.InstitutionSearchRequest;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.collections.InstitutionService;

/**
 * Iterates over results of {@link InstitutionService#list(InstitutionSearchRequest)}.
 */
public class InstitutionsPager extends BasePager<Institution> {

  private final InstitutionService service;
  private final InstitutionSearchRequest searchRequest;

  public InstitutionsPager(InstitutionService service,
                           InstitutionSearchRequest searchRequest,
                           int pageSize) {
    super(pageSize);
    this.searchRequest = searchRequest;
    this.service = service;
  }

  @Override
  PagingResponse<Institution> nextPage(PagingRequest page) {
    searchRequest.setOffset(page.getOffset());
    searchRequest.setLimit(page.getLimit());
    return service.list(searchRequest);
  }

}
