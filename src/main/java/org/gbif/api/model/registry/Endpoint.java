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

import org.gbif.api.vocabulary.EndpointType;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class Endpoint implements MachineTaggable, Serializable, LenientEquals<Endpoint> {

  @Schema(
    description = "Identifier for the endpoint",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer key;

  private EndpointType type;

  private URI url;

  private String description;

  @Schema(
    description = "The GBIF username of the creator of the endpoint",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the endpoint",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the endpoint was created",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the endpoint was last modified",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Schema(
    description = "Machine tags applied to the endpoint",
    required = false
  )
  private List<MachineTag> machineTags = new ArrayList<>();

  @Null(groups = PrePersist.class)
  @NotNull(groups = PostPersist.class)
  @Min(1)
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  public EndpointType getType() {
    return type;
  }

  public void setType(EndpointType type) {
    this.type = type;
  }

  @Nullable
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Size(min = 3)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Size(min = 3)
  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Null(groups = PrePersist.class)
  @NotNull(groups = PostPersist.class)
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Null(groups = PrePersist.class)
  @NotNull(groups = PostPersist.class)
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public List<MachineTag> getMachineTags() {
    return machineTags;
  }

  @Override
  public void setMachineTags(List<MachineTag> machineTags) {
    this.machineTags = machineTags;
  }

  @Override
  public void addMachineTag(MachineTag machineTag) {
    machineTags.add(machineTag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Endpoint endpoint = (Endpoint) o;
    return Objects.equals(key, endpoint.key)
        && type == endpoint.type
        && Objects.equals(url, endpoint.url)
        && Objects.equals(description, endpoint.description)
        && Objects.equals(createdBy, endpoint.createdBy)
        && Objects.equals(modifiedBy, endpoint.modifiedBy)
        && Objects.equals(created, endpoint.created)
        && Objects.equals(modified, endpoint.modified)
        && Objects.equals(machineTags, endpoint.machineTags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key, type, url, description, createdBy, modifiedBy, created, modified, machineTags);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Endpoint.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("type=" + type)
        .add("url=" + url)
        .add("description='" + description + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .add("machineTags=" + machineTags)
        .toString();
  }

  /**
   * Does not include server controlled values, or nested properties.
   */
  @Override
  public boolean lenientEquals(Endpoint other) {
    if (this == other) {
      return true;
    }
    if (other == null) return false;
    else {
      return Objects.equals(this.type, other.type)
          && Objects.equals(this.url, other.url)
          && Objects.equals(this.description, other.description);
    }
  }
}
