package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

import java.util.UUID;
import javax.annotation.Nullable;

public interface CollectionService extends CrudService<Collection>, ContactService, TagService, IdentifierService {

  PagingResponse<Collection> listByInstitution(UUID institutionKey, @Nullable Pageable page);

}
