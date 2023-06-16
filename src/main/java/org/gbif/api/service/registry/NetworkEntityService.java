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
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.vocabulary.IdentifierType;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface NetworkEntityService<T>
    extends MachineTagService,
        TagService,
        CommentService,
        IdentifierService,
        EndpointService,
        ContactService {

  /**
   * Creates a new entity. Data should be valid.
   *
   * @param entity to create
   * @return a key of the newly created entity.
   */
  UUID create(@NotNull @Valid T entity);

  /**
   * Updates an existing entity. Data should be valid.
   *
   * @param entity to update.
   */
  void update(@NotNull @Valid T entity);

  // TODO: Define behavior when it is already deleted or does not exist

  /**
   * Deletes an entity by the provided key.
   *
   * @param key of the entity to delete.
   */
  void delete(@NotNull UUID key);

  /**
   * Gets an entity by the provided key.
   *
   * @param key of the entity to get
   * @return an entity.
   */
  T get(@NotNull UUID key);

  /**
   * Retrieves all titles for the requested entity keys in one go
   *
   * @param keys entity keys to get titles
   * @return pairs of entity key - entity title.
   */
  Map<UUID, String> getTitles(@NotNull Collection<UUID> keys);
}
