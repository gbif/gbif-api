package org.gbif.api.model.collections;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Entity .
 */
public interface CollectionEntity {

  /**
   * Unique identifier.
   */
  UUID getKey();

  void setKey(UUID key);

  /**
   * Creator of the database record.
   */
  String getCreatedBy();

  void setCreatedBy(String createdBy);

  /**
   * Person or agent that modified the database record.
   */
  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  /**
   * Date when the records as created.
   */
  Date getCreated();

  void setCreated(Date created);

  /**
   * Date when the records was last modified.
   */
  Date getModified();

  void setModified(Date modified);

  /**
   * Date when the records was (logically) deleted.
   */
  @Nullable
  Date getDeleted();

  void setDeleted(Date deleted);

}
