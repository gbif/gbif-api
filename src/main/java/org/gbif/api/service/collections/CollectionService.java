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

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.collections.request.CollectionSearchRequest;
import org.gbif.api.model.collections.view.CollectionView;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.KeyCodeNameResult;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/** API Service to work with collections. */
public interface CollectionService extends PrimaryCollectionEntityService<Collection> {

  /**
   * Pages {@link Collection} entities based on the parameters received.
   *
   * <p>To iterate over <em>all</em> entities you can use code like this: {@code PagingRequest req =
   * new PagingRequest(); PagingResponse<T> response; do { response = service.list(req); for (T obj
   * : response.getResults()) { doStuff(); } req.nextPage(); } while (!response.isEndOfRecords()); }
   *
   * @param searchRequest {@link CollectionSearchRequest} with all the parameters
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<CollectionView> list(CollectionSearchRequest searchRequest);

  /** Provides access to deleted collections. */
  PagingResponse<CollectionView> listDeleted(@Nullable UUID replacedBy, @Nullable Pageable page);

  /** Retrieves a {@link CollectionView} by the collection key. */
  CollectionView getCollectionView(@NotNull UUID key);

  /** Provides a simple suggest service. */
  List<KeyCodeNameResult> suggest(@Nullable String q);
}
