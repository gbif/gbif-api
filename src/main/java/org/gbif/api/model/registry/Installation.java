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

import org.gbif.api.vocabulary.InstallationType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A technical installation which can serve datasets.
 * Note: An Installation may be marked as disabled, meaning that some process has identified it is out of action.
 * For the GBIF crawling infrastructure, this means it will not be "metasynced" nor will any dataset associated be
 * eligible for crawling.
 */
// TODO: Only allow adding of Endpoints of the correct type.
@Schema(
  description = "A technical installation which can serve datasets."
)
@SuppressWarnings("unused")
public class Installation
    implements NetworkEntity,
    Contactable,
    Endpointable,
    MachineTaggable,
    Taggable,
    Commentable,
    Identifiable,
    LenientEquals<Installation> {

  @Schema(
    description = "Unique GBIF key for the installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private UUID key;

  @Schema(
    description = "The publishing organization managing this installation.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private UUID organizationKey;

  @Schema(
    description = "A shared token used to authenticate as this installation.\n" +
      "Referred to as a shared token in user interfaces, although the API uses" +
      "“password”."
  )
  private String password;

  @Schema(
    description = "The type of the installation. Defines what protocols are used" +
      "for communication.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private InstallationType type;

  @Schema(
    description = "A name for the installation.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private String title;

  @Schema(
    description = "A description for the installation."
  )
  private String description;

  @Schema(
    description = "The GBIF username of the creator of the installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the installation was created.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the installation was modified.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Schema(
    description = "If present, the installation was deleted at this time. " +
      "It is possible for it to be restored in the future.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date deleted;

  @Schema(
    description = "Whether the installation is disabled. A disabled installation " +
      "is not checked for new or deleted datasets, or metadata changes to existing" +
      "datasets. However, data updates from existing datasets are not affected."
  )
  private boolean disabled;

  @Schema(
    description = "A list of contacts associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Contact> contacts = new ArrayList<>();

  @Schema(
    description = "A list of endpoints associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Endpoint> endpoints = new ArrayList<>();

  @Schema(
    description = "A list of machine tags associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(
    description = "A list of tags associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Tag> tags = new ArrayList<>();

  @Schema(
    description = "A list of identifiers associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(
    description = "A list of comments associated with this installation.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Comment> comments = new ArrayList<>();

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Installation that = (Installation) o;
    return disabled == that.disabled
        && Objects.equals(key, that.key)
        && Objects.equals(organizationKey, that.organizationKey)
        && Objects.equals(password, that.password)
        && type == that.type
        && Objects.equals(title, that.title)
        && Objects.equals(description, that.description)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(modifiedBy, that.modifiedBy)
        && Objects.equals(created, that.created)
        && Objects.equals(modified, that.modified)
        && Objects.equals(deleted, that.deleted)
        && Objects.equals(contacts, that.contacts)
        && Objects.equals(endpoints, that.endpoints)
        && Objects.equals(machineTags, that.machineTags)
        && Objects.equals(tags, that.tags)
        && Objects.equals(identifiers, that.identifiers)
        && Objects.equals(comments, that.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        organizationKey,
        password,
        type,
        title,
        description,
        createdBy,
        modifiedBy,
        created,
        modified,
        deleted,
        disabled,
        contacts,
        endpoints,
        machineTags,
        tags,
        identifiers,
        comments);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Installation.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("organizationKey=" + organizationKey)
        .add("password='" + password + "'")
        .add("type=" + type)
        .add("title='" + title + "'")
        .add("description='" + description + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .add("deleted=" + deleted)
        .add("disabled=" + disabled)
        .add("contacts=" + contacts)
        .add("endpoints=" + endpoints)
        .add("machineTags=" + machineTags)
        .add("tags=" + tags)
        .add("identifiers=" + identifiers)
        .add("comments=" + comments)
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
    if (other == null) return false;
    return Objects.equals(this.organizationKey, other.organizationKey)
        && Objects.equals(this.type, other.type)
        && Objects.equals(this.title, other.title)
        && Objects.equals(this.description, other.description);
  }
}
