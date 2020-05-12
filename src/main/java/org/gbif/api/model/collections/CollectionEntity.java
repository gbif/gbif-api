/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.collections;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Nullable;

/**
 * Entity .
 */
public interface CollectionEntity extends Serializable {

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
