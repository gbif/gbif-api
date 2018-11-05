package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.service.registry.IdentifierService;
import org.gbif.api.service.registry.TagService;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * API Service to work with collections.
 */
public interface CollectionService
    extends CrudService<Collection>, ContactService, TagService, IdentifierService {

  /**
   * Pages {@link Collection} entities by {@link org.gbif.api.model.collections.Institution}.
   *
   * @param institutionKey key of the institution to filter by.
   * @param page with the parameters for paging
   * @return pages
   */
  PagingResponse<Collection> listByInstitution(UUID institutionKey, @Nullable Pageable page);
}
