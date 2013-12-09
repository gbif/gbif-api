/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.MetadataType;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

public class Metadata implements LenientEquals<Metadata> {

  private Integer key;
  private UUID datasetKey;
  private MetadataType type;
  private String content;

  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;

  @Min(1)
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  @NotNull
  public MetadataType getType() {
    return type;
  }

  public void setType(MetadataType type) {
    this.type = type;
  }

  @NotNull
  @Size(min = 1)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @NotNull
  @Size(min = 3)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @NotNull
  @Size(min = 3)
  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @NotNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @NotNull
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, datasetKey, type, content, createdBy, modifiedBy, created, modified);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Metadata) {
      Metadata that = (Metadata) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.datasetKey, that.datasetKey)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.content, that.content)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.modifiedBy, that.modifiedBy)
        && Objects.equal(this.created, that.created)
        && Objects.equal(this.modified, that.modified);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("datasetKey", datasetKey)
      .add("type", type)
      .add("content", content)
      .add("createdBy", createdBy)
      .add("modifiedBy", modifiedBy)
      .add("created", created)
      .add("modified", modified)
      .toString();
  }

  /**
   * Does not include the key or server controlled values (created, createdBy etc).
   */
  @Override
  public boolean lenientEquals(Metadata other) {
    if (this == other) {
      return true;
    }
    return Objects.equal(this.datasetKey, other.datasetKey)
      && Objects.equal(this.type, other.type)
      && Objects.equal(this.content, other.content);
  }
}
