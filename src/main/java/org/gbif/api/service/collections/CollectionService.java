package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;
import org.gbif.api.vocabulary.IdentifierType;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface CollectionService extends TagService, IdentifierService {

  UUID create(@NotNull Collection entity);

  void delete(@NotNull UUID key);

  Collection get(@NotNull UUID key);

  PagingResponse<Collection> list(@Nullable Pageable page);

  PagingResponse<Collection> search(String query, @Nullable Pageable page);

  PagingResponse<Collection> listByIdentifier(IdentifierType type, String identifier, @Nullable Pageable page);
  
  PagingResponse<Collection> listByIdentifier(String identifier, @Nullable Pageable page);

  void update(@NotNull Collection entity);

}
