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

import org.gbif.api.model.collections.request.CollectionSearchRequest;
import org.gbif.api.model.collections.view.CollectionView;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.collections.CollectionService;

/**
 * Iterates over results of {@link CollectionService#list(CollectionSearchRequest)}.
 */
public class CollectionsPager extends BasePager<CollectionView> {

  private final CollectionService service;
  private final CollectionSearchRequest searchRequest;

  public CollectionsPager(CollectionService service,
                          CollectionSearchRequest searchRequest,
                          int pageSize) {
    super(pageSize);
    this.searchRequest = searchRequest;
    this.service = service;
  }

  @Override
  PagingResponse<CollectionView> nextPage(PagingRequest page) {
    return service.list(searchRequest);
  }

}
