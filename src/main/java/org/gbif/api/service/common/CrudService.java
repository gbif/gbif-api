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

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import javax.annotation.Nullable;

/**
 * A generic CRUD service interface for any writable entity.
 *
 * @param <T> The complete entity class
 * @param <W> The writable entity class, can be the same as T
 * @param <K> The primary key class of the entity
 */
public interface CrudService<T, W, K> {

  /**
   * Persists a new entity.
   *
   * @param entity to create.
   *
   * @return The persisted entity.
   */
  K create(W entity);

  /**
   * Deletes the entity.
   *
   * @param key The entity key to delete.
   */
  void delete(K key);

  /**
   * Get an entity by its unique key.
   *
   * @return the entity or null if not existing.
   */
  @Nullable
  T get(K key);


  /**
   * Lists all entity.
   *
   * @param page to enable paging.
   *
   * @return The pagable list of entities.
   */
  PagingResponse<T> list(@Nullable Pageable page);

  /**
   * Updates the entity.
   *
   * @param entity to update
   */
  void update(W entity);

}
