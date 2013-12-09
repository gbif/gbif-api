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

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 * A tag that has a namespace, name and a value. {@code created} and {@code createdBy} are automatically set upon
 * persisting.
 */
// TODO: Document the rules regarding duplicate names and values
public class MachineTag implements LenientEquals<MachineTag> {

  private Integer key;
  private String namespace;
  private String name;
  private String value;
  private String createdBy;
  private Date created;

  public static MachineTag newInstance(String namespace, String name, String value) {
    return new MachineTag(namespace, name, value);
  }

  public MachineTag() {
    // Needed
  }

  /**
   * This is the default constructor to create new Machine Tags which takes all user settable properties.
   */
  public MachineTag(String namespace, String name, String value) {
    this.namespace = namespace;
    this.name = name;
    this.value = value;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  @NotNull
  @Size(min = 1, max = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotNull
  @Size(min = 1, max = 255)
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
  public int hashCode() {
    return Objects.hashCode(key, namespace, name, value, createdBy, created);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof MachineTag) {
      MachineTag that = (MachineTag) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.namespace, that.namespace)
        && Objects.equal(this.name, that.name)
        && Objects.equal(this.value, that.value)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.created, that.created);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("namespace", namespace)
      .add("name", name)
      .add("value", value)
      .add("createdBy", createdBy)
      .add("created", created)
      .toString();
  }

  /**
   * This implementation of the {@link #equals(Object)} method does only check <em>business equality</em> and disregards
   * automatically set and maintained fields like {@code createdBy, key} and possibly others in the future.
   */
  @Override
  public boolean lenientEquals(MachineTag other) {
    if (this == other) {
      return true;
    }

    return Objects.equal(this.namespace, other.namespace)
      && Objects.equal(this.name, other.name)
      && Objects.equal(this.value, other.value);
    // TODO: http://dev.gbif.org/issues/browse/REG-401
    // && Objects.equal(this.createdBy, other.createdBy);
  }
}
