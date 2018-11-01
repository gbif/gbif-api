package org.gbif.api.service.collections;

import org.gbif.api.model.collections.Staff;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;
import javax.annotation.Nullable;

public interface StaffService extends CrudService<Staff> {

  PagingResponse<Staff> listByInstitution(UUID institutionKey, @Nullable Pageable page);

  PagingResponse<Staff> listByCollection(UUID collectionKey, @Nullable Pageable page);

}
