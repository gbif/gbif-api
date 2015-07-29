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

import org.gbif.api.vocabulary.InstallationType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * A technical installation which can serve dataset(s).
 * Note: An Installation may be marked as disabled, meaning that some process has identified it is out of action.
 * For the GBIF crawling infrastructure, this means it will not be "metasynced" nor will any dataset associated be
 * eligble for crawling.
 */
// TODO: Only allow adding of Endpoints of the correct type, I would argue for removing all the set(List) methods
public class Installation implements NetworkEntity, Contactable, Endpointable, MachineTaggable, Taggable, Commentable,
  Identifiable, LenientEquals<Installation> {

  private UUID key;
  private UUID organizationKey;
  private String password;
  private InstallationType type;
  private String title;
  private String description;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;
  private boolean disabled;
  private List<Contact> contacts = Lists.newArrayList();
  private List<Endpoint> endpoints = Lists.newArrayList();
  private List<MachineTag> machineTags = Lists.newArrayList();
  private List<Tag> tags = Lists.newArrayList();
  private List<Identifier> identifiers = Lists.newArrayList();
  private List<Comment> comments = Lists.newArrayList();

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  @Override
  public String getTitle() {
    return title;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public void setCreated(Date created) {
    this.created = created;
  }

  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Override
  public Date getModified() {
    return modified;
  }

  @Override
  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public Date getDeleted() {
    return deleted;
  }

  @Override
  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @NotNull
  public UUID getOrganizationKey() {
    return organizationKey;
  }

  public void setOrganizationKey(UUID organizationKey) {
    this.organizationKey = organizationKey;
  }

  /**
   * Get the installation password. This method is to be ignored on serialization, so that the password is not
   * revealed in the web service response.
   * 
   * @return organization password
   */
  @JsonIgnore
  @Nullable
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @NotNull
  public InstallationType getType() {
    return type;
  }

  public void setType(InstallationType type) {
    this.type = type;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  @Override
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Override
  public List<Contact> getContacts() {
    return contacts;
  }

  @Override
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @Override
  public List<Endpoint> getEndpoints() {
    return endpoints;
  }

  @Override
  public void setEndpoints(List<Endpoint> endpoints) {
    this.endpoints = endpoints;
  }

  @Override
  public void addEndpoint(Endpoint endpoint) {
    endpoints.add(endpoint);
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
  public List<Tag> getTags() {
    return tags;
  }

  @Override
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  @Override
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  @Override
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  @Override
  public List<Comment> getComments() {
    return comments;
  }

  @Override
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, organizationKey, password, type, title, description, createdBy, modifiedBy, created, modified,
        deleted, disabled, contacts, endpoints, machineTags, tags, identifiers, comments);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Installation) {
      Installation that = (Installation) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.organizationKey, that.organizationKey)
        && Objects.equal(this.password, that.password)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.title, that.title)
        && Objects.equal(this.description, that.description)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.modifiedBy, that.modifiedBy)
        && Objects.equal(this.created, that.created)
        && Objects.equal(this.modified, that.modified)
        && Objects.equal(this.deleted, that.deleted)
        && Objects.equal(this.disabled, that.disabled)
        && Objects.equal(this.contacts, that.contacts)
        && Objects.equal(this.endpoints, that.endpoints)
        && Objects.equal(this.machineTags, that.machineTags)
        && Objects.equal(this.tags, that.tags)
        && Objects.equal(this.identifiers, that.identifiers)
        && Objects.equal(this.comments, that.comments);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("organizationKey", organizationKey)
      .add("password", password)
      .add("type", type)
      .add("title", title)
      .add("description", description)
      .add("createdBy", createdBy)
      .add("modifiedBy", modifiedBy)
      .add("created", created)
      .add("modified", modified)
      .add("deleted", deleted)
      .add("disabled", disabled)
      .add("contacts", contacts)
      .add("endpoints", endpoints)
      .add("machineTags", machineTags)
      .add("tags", tags)
      .add("identifiers", identifiers)
      .add("comments", comments)
      .toString();
  }

  /**
   * Does not include the nested properties, or server controlled values (key, createdBy etc) or the password,
   * for security reasons.
   */
  @Override
  public boolean lenientEquals(Installation other) {
    if (this == other) {
      return true;
    }
    return Objects.equal(this.organizationKey, other.organizationKey)
      && Objects.equal(this.type, other.type)
      && Objects.equal(this.title, other.title)
      && Objects.equal(this.description, other.description);
  }
}
