package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Institution;
import org.gbif.api.model.collections.Staff;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface InstitutionService extends TagService, IdentifierService {

  UUID create(@NotNull Institution entity);

  void delete(@NotNull UUID key);

  Institution get(@NotNull UUID key);

  PagingResponse<Institution> list(@Nullable Pageable page);

  PagingResponse<Institution> search(String query, @Nullable Pageable page);

  void update(@NotNull Institution entity);

  List<Staff> listContacts(@NotNull UUID key);

  void addContact(@NotNull UUID institutionKey, @NotNull UUID staffKey);

  void removeContact(@NotNull UUID institutionKey, @NotNull UUID staffKey);

}
