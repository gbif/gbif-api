package org.gbif.api.model.collections;

import java.util.Date;
import java.util.UUID;

/**
 * Interface to represent a collection entity.
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

  Date getDeleted();

  void setDeleted(Date deleted);

}
