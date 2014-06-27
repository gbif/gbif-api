/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.common;

import org.gbif.api.model.common.search.FacetedSearchRequest;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.common.search.SearchRequest;
import org.gbif.api.model.common.search.SearchResponse;

/**
 * Parameterized generic search interface that supports pagination.
 * The generic {@link SearchResponse} and {@link SearchRequest} are used to provide the basic search parameters
 * in a request and allow facets for results.
 * This marker interface should be extended by other interfaces that support full text, faceted and paginated search.
 *
 * @param <T> the type of returned results
 * @param <P> the supported search parameter enumeration
 * @param <R> the supported search request type. For faceted searches this needs to extend {@link FacetedSearchRequest}
 */
public interface SearchService<T, P extends Enum<?> & SearchParameter, R extends SearchRequest<P>> {

  /**
   * Issues a SearchRequest and retrieves a response resulting of the search operation.
   * The actual result information will contain a list elements of type T.
   *
   * @param searchRequest the searchRequest that contains the search parameters
   *
   * @return the SearchResponse resulting of the search operation
   */
  SearchResponse<T, P> search(R searchRequest);

}
