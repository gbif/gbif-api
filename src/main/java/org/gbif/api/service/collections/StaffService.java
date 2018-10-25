package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Staff;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;
import org.gbif.api.vocabulary.IdentifierType;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface StaffService extends TagService, IdentifierService {

  UUID create(@NotNull Staff entity);

  void delete(@NotNull UUID key);

  Staff get(@NotNull UUID key);

  PagingResponse<Staff> list(@Nullable Pageable page);

  PagingResponse<Staff> search(String query, @Nullable Pageable page);

  PagingResponse<Staff> listByIdentifier(IdentifierType type, String identifier, @Nullable Pageable page);
  
  PagingResponse<Staff> listByIdentifier(String identifier, @Nullable Pageable page);

  void update(@NotNull Staff entity);

}
