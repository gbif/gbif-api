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

import org.gbif.api.model.collections.descriptors.Descriptor;
import org.gbif.api.model.collections.request.DescriptorSearchRequest;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.collections.DescriptorsService;

/** Iterates over results of {@link DescriptorsService#listDescriptors(DescriptorSearchRequest)}. */
public class CollectionDescriptorPager extends BasePager<Descriptor> {

  private final DescriptorsService service;
  private final DescriptorSearchRequest searchRequest;

  public CollectionDescriptorPager(
      DescriptorsService service, DescriptorSearchRequest searchRequest, int pageSize) {
    super(pageSize);
    this.searchRequest = searchRequest;
    this.service = service;
  }

  @Override
  public PagingResponse<Descriptor> nextPage(PagingRequest page) {
    searchRequest.setOffset(page.getOffset());
    searchRequest.setLimit(page.getLimit());
    return service.listDescriptors(searchRequest);
  }
}
