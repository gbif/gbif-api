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

import org.gbif.api.util.IdentifierUtils;
import org.gbif.api.vocabulary.IdentifierType;

import java.util.Date;
import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Identifier implements LenientEquals<Identifier> {

  private Integer key;
  private IdentifierType type;
  private String identifier;
  private String createdBy;
  private Date created;

  public Identifier() {
  }

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
  public int hashCode() {
    return Objects.hashCode(key, type, identifier, createdBy, created);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Identifier) {
      Identifier that = (Identifier) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.identifier, that.identifier)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.created, that.created);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("type", type)
      .add("identifier", identifier)
      .add("createdBy", createdBy)
      .add("created", created)
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
    return Objects.equal(this.type, other.type)
      && Objects.equal(this.identifier, other.identifier);
  }

}
