package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Staff;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;
import javax.annotation.Nullable;

/**
 * API Service for the collections-related staff.
 */
public interface StaffService extends CrudService<Staff> {

  /**
   * Pages {@link Staff} entities by {@link org.gbif.api.model.collections.Institution}.
   *
   * @param institutionKey key of the institution to filter by.
   * @param page parameters for paging.
   * @return pages
   */
  PagingResponse<Staff> listByInstitution(UUID institutionKey, @Nullable Pageable page);

  /**
   * Pages {@link Staff} entities by {@link org.gbif.api.model.collections.Collection}.
   *
   * @param collectionKey key of the collection to filter by.
   * @param page parameters for paging.
   * @return pages
   */
  PagingResponse<Staff> listByCollection(UUID collectionKey, @Nullable Pageable page);
}
