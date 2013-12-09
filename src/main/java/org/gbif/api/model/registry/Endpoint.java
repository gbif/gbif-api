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

import org.gbif.api.vocabulary.EndpointType;

import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Endpoint implements MachineTaggable, LenientEquals<Endpoint> {

  private Integer key;
  private EndpointType type;
  private URI url;
  private String description;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private List<MachineTag> machineTags = Lists.newArrayList();

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
  public int hashCode() {
    return Objects.hashCode(key, type, url, description, createdBy, modifiedBy, created, modified, machineTags);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Endpoint) {
      Endpoint that = (Endpoint) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.url, that.url)
        && Objects.equal(this.description, that.description)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.modifiedBy, that.modifiedBy)
        && Objects.equal(this.created, that.created)
        && Objects.equal(this.modified, that.modified)
        && Objects.equal(this.machineTags, that.machineTags);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("type", type)
      .add("url", url)
      .add("description", description)
      .add("createdBy", createdBy)
      .add("modifiedBy", modifiedBy)
      .add("created", created)
      .add("modified", modified)
      .add("machineTags", machineTags)
      .toString();
  }

  /**
   * Does not include server controlled values, or nested properties.
   */
  @Override
  public boolean lenientEquals(Endpoint other) {
    if (this == other) {
      return true;
    } else {
      return Objects.equal(this.type, other.type)
        && Objects.equal(this.url, other.url)
        && Objects.equal(this.description, other.description);
    }
  }

}
