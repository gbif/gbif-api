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
package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Person;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.PersonSuggestResult;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

/** API Service for the collections-related staff. */
public interface PersonService extends CollectionEntityService<Person> {

  /**
   * Pages {@link Person} entities based on the parameters received.
   *
   * <p>To iterate over <em>all</em> entities you can use code like this: {@code PagingRequest req =
   * new PagingRequest(); PagingResponse<T> response; do { response = service.list(req); for (T obj
   * : response.getResults()) { doStuff(); } req.nextPage(); } while (!response.isEndOfRecords()); }
   *
   * @param query to make a full text search
   * @param institutionKey key of an institution to filter by
   * @param collectionKey key of a collection to filter by
   * @param page paging parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Person> list(
      @Nullable String query,
      @Nullable UUID institutionKey,
      @Nullable UUID collectionKey,
      @Nullable Pageable page);

  /** Provides access to deleted persons. */
  PagingResponse<Person> listDeleted(@Nullable Pageable page);

  /** Provides a simple suggest service. */
  List<PersonSuggestResult> suggest(@Nullable String q);
}
