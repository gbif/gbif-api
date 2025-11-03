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

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class Tag implements Serializable, LenientEquals<Tag> {

  @Schema(
    description = "Identifier for the tag",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer key;

  @Schema(
    description = "Text value of the tag"
  )
  private String value;

  @Schema(
    description = "The GBIF username of the creator of the tag",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "Timestamp of when the tag was created",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  public Tag() {}

  public Tag(String value) {
    this.value = value;
  }

  public Tag(String value, String createdBy) {
    this.value = value;
    this.createdBy = createdBy;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Min(1)
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  @Size(min = 1)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Size(min = 3)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
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
    Tag tag = (Tag) o;
    return Objects.equals(key, tag.key)
        && Objects.equals(value, tag.value)
        && Objects.equals(createdBy, tag.createdBy)
        && Objects.equals(created, tag.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value, createdBy, created);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("value='" + value + "'")
        .add("createdBy='" + createdBy + "'")
        .add("created=" + created)
        .toString();
  }

  /**
   * A lenient test that returns true if they are the same object or have the same value.
   */
  @Override
  public boolean lenientEquals(Tag other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.value, other.value);
  }
}
