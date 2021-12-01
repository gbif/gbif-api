/*
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

import org.gbif.api.model.registry.PrePersist;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Primary entity. */
public interface PrimaryCollectionEntity extends CollectionEntity {

  /** Identifies an entity at the owner's location. */
  @NotNull(groups = PrePersist.class)
  String getCode();

  void setCode(String code);

  /** Descriptive name of an entity. */
  @NotNull
  String getName();

  void setName(String name);

  /** Textual description/summary of the contents of an entity. */
  @Size(min = 1)
  String getDescription();

  void setDescription(String description);

  /** Is this entity currently active/maintained. */
  boolean isActive();

  void setActive(boolean active);

  /** Replacement of the entity (if applies). */
  UUID getReplacedBy();

  void setReplacedBy(UUID replacedBy);

  /** Master source of the entity. */
  MasterSourceType getMasterSource();

  void setMasterSource(MasterSourceType masterSource);
}
