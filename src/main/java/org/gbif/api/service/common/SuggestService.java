/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.service.common;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.common.search.SearchRequest;

import java.util.List;


/**
 * Generic search interface for a suggest/autocomplete service.
 * This marker interface should be extended by other interfaces that support this functionality.
 *
 * @param <T> the type of returned results
 * @param <P> the supported search parameter enumeration
 * @param <R> the supported search request type. For faceted searches this needs to extend
 *        {@link org.gbif.api.model.common.search.FacetedSearchRequest}
 */
public interface SuggestService<T, P extends Enum<?> & SearchParameter, R extends SearchRequest<P>> {

  /**
   * Issues a SearchRequest for a suggest and retrieves the list of matches.
   * The actual result information will contain a list elements of type T.
   *
   * @param suggestRequest the input object to be used for performing the operation.
   */
  List<T> suggest(R suggestRequest);

}
