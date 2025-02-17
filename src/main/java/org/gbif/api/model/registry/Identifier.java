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

import org.gbif.api.util.IdentifierUtils;
import org.gbif.api.vocabulary.IdentifierType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Identifier implements Serializable, LenientEquals<Identifier> {

  @Schema(
    description = "Database identifier for the identifier",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer key;

  private IdentifierType type;

  @Schema(description = "Value for the identifier")
  private String identifier;

  @Schema(
    description = "The GBIF username of the creator of the identifier",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "Timestamp of when the identifier was created",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Whether this is the primary identifier for the associated entity."
  )
  @NotNull
  private Boolean primary = false;

  public Identifier() {}

  public Identifier(IdentifierType type, String identifier) {
    this.type = type;
    this.identifier = identifier;
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
  public IdentifierType getType() {
    return type;
  }

  public void setType(IdentifierType type) {
    this.type = type;
  }

  @NotNull
  @Size(min = 1)
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  /**
   * Creates a http link for an identifier if possible by passing it to some known resolvers for the specific id type.
   * If no link can be constructed, null is returned.
   *
   * @return the url or null if it cannot be created
   *
   * @see org.gbif.api.util.IdentifierUtils#getIdentifierLink(String, org.gbif.api.vocabulary.IdentifierType)
   */
  @Nullable
  @JsonIgnore
  public String getIdentifierLink() {
    return IdentifierUtils.getIdentifierLink(identifier, type);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Identifier that = (Identifier) o;
    return Objects.equals(key, that.key)
        && type == that.type
        && Objects.equals(identifier, that.identifier)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(created, that.created)
        && primary == that.primary;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, type, identifier, createdBy, created, primary);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Identifier.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("type=" + type)
        .add("identifier='" + identifier + "'")
        .add("createdBy='" + createdBy + "'")
        .add("created=" + created + "'")
        .add("primary=" + primary)
        .toString();
  }

  /**
   * A lenient equality check ignoring server controlled values (createdBy, key etc).
   */
  @Override
  public boolean lenientEquals(Identifier other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.type, other.type)
        && Objects.equals(this.identifier, other.identifier);
  }
}
