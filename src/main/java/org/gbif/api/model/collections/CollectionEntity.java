package org.gbif.api.model.collections;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Interface to model a collection entity.
 */
public interface CollectionEntity {

  UUID getKey();

  void setKey(UUID key);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  Date getCreated();

  void setCreated(Date created);

  Date getModified();

  void setModified(Date modified);

  @Nullable
  Date getDeleted();

  void setDeleted(Date deleted);

}
