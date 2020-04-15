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

import org.gbif.api.model.collections.CollectionEntity;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Base CRUD service for {@link CollectionEntity} entities. */
public interface CrudService<T extends CollectionEntity> {

  /**
   * Creates a new entity.
   *
   * @param entity to create
   * @return UUID of the created entity.
   */
  UUID create(@NotNull @Valid T entity);

  /**
   * Deletes an entity by key.
   *
   * @param key of the entity to be deleted.
   */
  void delete(@NotNull UUID key);

  /**
   * Retrieves an entity by its key.
   *
   * @param key of the entity to be retrieved.
   * @return the entity
   */
  T get(@NotNull UUID key);

  /**
   * Updates an existing entity.
   *
   * @param entity that will replace the existing entity.
   */
  void update(@NotNull @Valid T entity);
}
