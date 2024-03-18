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
package org.gbif.api.model.literature.search;

import lombok.Getter;

import lombok.Setter;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.FacetedSearchRequest;

@Setter
@Getter
@SuppressWarnings("unused")
public class LiteratureSearchRequest extends FacetedSearchRequest<LiteratureSearchParameter> {

  private String doi;

  public LiteratureSearchRequest() {}

  public LiteratureSearchRequest(Pageable page) {
    super(page);
  }

  public LiteratureSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

}
