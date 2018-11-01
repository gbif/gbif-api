package org.gbif.api.service.collections;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Base CRUD service for collection entities.
 */
public interface CrudService<T extends CollectionEntity> {

  UUID create(@NotNull T entity);

  void delete(@NotNull UUID key);

  T get(@NotNull UUID key);

  PagingResponse<T> list(@Nullable Pageable page);

  PagingResponse<T> search(String query, @Nullable Pageable page);

  void update(@NotNull T entity);

}
