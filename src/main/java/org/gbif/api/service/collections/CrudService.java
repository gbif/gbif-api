package org.gbif.api.service.collections;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/** Base CRUD service for {@link CollectionEntity} entities. */
interface CrudService<T extends CollectionEntity> {

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
   * Used to retrieve a list of entities.
   *
   * To iterate over <em>all</em> entities you can use code like this:
   * {@code
   * PagingRequest req = new PagingRequest();
   * PagingResponse<T> response;
   * do {
   *   response = service.list(req);
   *   for (T obj : response.getResults()) {
   *     doStuff();
   *   }
   *   req.nextPage();
   * } while (!response.isEndOfRecords());
   * }
   *
   * @return a list of entities ordered by their creation date, newest coming first
   */
  PagingResponse<T> list(@Nullable Pageable page);

  /**
   * A simple search that supports paging.
   *
   * @return a pageable response of entities, with accurate counts.
   */
  PagingResponse<T> search(String query, @Nullable Pageable page);

  /**
   * Updates an existing entity.
   *
   * @param entity that will replace the existing entity.
   */
  void update(@NotNull T entity);
}
