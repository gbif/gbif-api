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
package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.MetadataType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Metadata implements Serializable, LenientEquals<Metadata> {

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(key, metadata.key)
        && Objects.equals(datasetKey, metadata.datasetKey)
        && type == metadata.type
        && Objects.equals(content, metadata.content)
        && Objects.equals(createdBy, metadata.createdBy)
        && Objects.equals(modifiedBy, metadata.modifiedBy)
        && Objects.equals(created, metadata.created)
        && Objects.equals(modified, metadata.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, datasetKey, type, content, createdBy, modifiedBy, created, modified);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Metadata.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("datasetKey=" + datasetKey)
        .add("type=" + type)
        .add("content='" + content + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
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
    return Objects.equals(this.datasetKey, other.datasetKey)
        && Objects.equals(this.type, other.type)
        && Objects.equals(this.content, other.content);
  }
}
