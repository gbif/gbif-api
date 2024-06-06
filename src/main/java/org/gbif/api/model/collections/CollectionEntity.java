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

import org.gbif.api.model.registry.Commentable;
import org.gbif.api.model.registry.Identifiable;
import org.gbif.api.model.registry.MachineTaggable;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.model.registry.Taggable;
import org.gbif.api.util.HttpURI;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.collections.MasterSourceType;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Entity . */
public interface CollectionEntity
    extends Contactable,
        Taggable,
        MachineTaggable,
        Identifiable,
        Commentable,
        OccurrenceMappeable,
        Serializable {

  /** Unique identifier. */
  UUID getKey();

  void setKey(UUID key);

  /** Creator of the database record. */
  String getCreatedBy();

  void setCreatedBy(String createdBy);

  /** Person or agent that modified the database record. */
  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  /** Date when the records as created. */
  Date getCreated();

  void setCreated(Date created);

  /** Date when the records was last modified. */
  Date getModified();

  void setModified(Date modified);

  /** Date when the records was (logically) deleted. */
  @Nullable
  Date getDeleted();

  void setDeleted(Date deleted);

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

  /** Master source metadata */
  MasterSourceMetadata getMasterSourceMetadata();

  void setMasterSourceMetadata(MasterSourceMetadata masterSourceMetadata);

  /** Flag to display the entity in the NHC portal. */
  Boolean getDisplayOnNHCPortal();

  void setDisplayOnNHCPortal(Boolean displayOnNHCPortal);

  /** Emails of the entity */
  List<String> getEmail();

  void setEmail(List<String> email);

  /** Phones of the entity */
  List<String> getPhone();

  void setPhone(List<String> phone);

  /** Alternative codes of the entity */
  List<AlternativeCode> getAlternativeCodes();

  void setAlternativeCodes(List<AlternativeCode> alternativeCodes);

  @HttpURI
  @Nullable
  URI getFeaturedImageUrl();

  void setFeaturedImageUrl(URI featuredImageUrl);

  @Nullable
  License getFeaturedImageLicense();

  void setFeaturedImageLicense(License featuredImageLicense);

  @Nullable
  String getFeaturedImageAttribution();

  void setFeaturedImageAttribution(String featuredImageAttribution);
}
