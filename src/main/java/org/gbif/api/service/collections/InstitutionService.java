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

import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.request.InstitutionSearchRequest;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.KeyCodeNameResult;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

/** Service for institutions in the collections context. */
public interface InstitutionService extends PrimaryCollectionEntityService<Institution> {

  /**
   * Pages {@link Institution} entities based on the parameters received.
   *
   * <p>To iterate over <em>all</em> entities you can use code like this: {@code PagingRequest req =
   * new PagingRequest(); PagingResponse<T> response; do { response = service.list(req); for (T obj
   * : response.getResults()) { doStuff(); } req.nextPage(); } while (!response.isEndOfRecords()); }
   *
   * @param searchRequest {@link InstitutionSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<Institution> list(InstitutionSearchRequest searchRequest);

  /** Provides access to deleted institutions. */
  PagingResponse<Institution> listDeleted(@Nullable Pageable page);

  /** Provides a simple suggest service. */
  List<KeyCodeNameResult> suggest(@Nullable String q);

  /** Converts an institution into a collection. The institution converted is deleted. */
  void convertToCollection(UUID targetEntityKey, UUID collectionKey);
}
