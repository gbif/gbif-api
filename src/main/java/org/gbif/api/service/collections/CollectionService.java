/*
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
import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.collections.latimercore.ObjectGroup;
import org.gbif.api.model.collections.request.CollectionSearchRequest;
import org.gbif.api.model.collections.request.InstitutionSearchRequest;
import org.gbif.api.model.collections.view.CollectionView;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.search.collections.KeyCodeNameResult;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/** API Service to work with collections. */
public interface CollectionService extends CollectionEntityService<Collection> {

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

  /**
   * Similar to the {@link #list(CollectionSearchRequest)} method but returns the results in Latimer
   * Core format.
   */
  PagingResponse<ObjectGroup> listAsLatimerCore(CollectionSearchRequest searchRequest);

  /** Provides access to deleted collections. */
  PagingResponse<CollectionView> listDeleted(CollectionSearchRequest searchRequest);

  /** Similar to {@link #get(UUID)} but it returns the result in Latimer Core format. */
  ObjectGroup getAsLatimerCore(@NotNull UUID key);

  /** Similar to {@link #create(CollectionEntity)} but it accepts Latimer Core. */
  UUID createFromLatimerCore(@NotNull @Valid ObjectGroup objectGroup);

  /** Similar to {@link #update(CollectionEntity)})} but it accepts Latimer Core. */
  void updateFromLatimerCore(@NotNull @Valid ObjectGroup entity);

  /** Retrieves a {@link CollectionView} by the collection key. */
  CollectionView getCollectionView(@NotNull UUID key);

  /** Provides a simple suggest service. */
  List<KeyCodeNameResult> suggest(@Nullable String q);

  /**
   * Creates a {@link Collection} from a {@link org.gbif.api.model.registry.Dataset}.
   *
   * @param datasetKey key of the dataset to create the collection from
   * @param collectionCode the code to assign to the collection since it can't be inferred from the
   *     dataset
   * @return UUID of the created collection
   */
  UUID createFromDataset(UUID datasetKey, String collectionCode);

  /**
   * Gets collections that belong to institutions matching the provided search criteria.
   * The method first retrieves all matching institutions using pagination, then returns their collections.
   *
   * @param searchRequest the institution search criteria
   * @return a list of collections
   */
  List<CollectionView> getCollectionsForInstitutionsBySearch(InstitutionSearchRequest searchRequest);
}
