package org.gbif.api.service.collections;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/** Base CRUD service for {@link CollectionEntity} entities. */
public interface CrudService<T extends CollectionEntity> {

  /**
   * Creates a new entity.
   *
   * @param entity to create
   * @return UUID of the created entity.
   */
  UUID create(@NotNull T entity);

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
  void update(@NotNull T entity);
}
