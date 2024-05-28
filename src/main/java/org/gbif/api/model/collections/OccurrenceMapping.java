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

import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

/** Models the mapping of a GRSciColl institution or collection to an occurrence record. */
public class OccurrenceMapping implements Serializable, LenientEquals<OccurrenceMapping> {

  private Integer key;
  private String code;
  /*
   Only used for collections when they need to specify the institution code.
  */
  private String parentCode;
  private String identifier;
  private UUID datasetKey;
  private String createdBy;
  private Date created;

  public OccurrenceMapping() {}

  public OccurrenceMapping(String code, String identifier, UUID datasetKey) {
    this(code, identifier, datasetKey, null);
  }

  public OccurrenceMapping(String code, String identifier, UUID datasetKey, String parentCode) {
    this.code = code;
    this.identifier = identifier;
    this.datasetKey = datasetKey;
    this.parentCode = parentCode;
  }

  /** Unique identifier, assigned by the persistence store. */
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @Nullable
  @Size(min = 1)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Nullable
  @Size(min = 1)
  public String getParentCode() {
    return parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  @Nullable
  @Size(min = 1)
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccurrenceMapping that = (OccurrenceMapping) o;
    return Objects.equals(key, that.key)
        && Objects.equals(code, that.code)
        && Objects.equals(parentCode, that.parentCode)
        && Objects.equals(identifier, that.identifier)
        && Objects.equals(datasetKey, that.datasetKey)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(created, that.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, code, parentCode, identifier, datasetKey, createdBy, created);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OccurrenceMapping.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("code='" + code + "'")
        .add("parentCode='" + parentCode + "'")
        .add("identifier='" + identifier + "'")
        .add("datasetKey=" + datasetKey)
        .add("createdBy='" + createdBy + "'")
        .add("created=" + created)
        .toString();
  }

  @Override
  public boolean lenientEquals(OccurrenceMapping other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(code, other.code)
        && Objects.equals(parentCode, other.parentCode)
        && Objects.equals(identifier, other.identifier)
        && Objects.equals(datasetKey, other.datasetKey);
  }
}
