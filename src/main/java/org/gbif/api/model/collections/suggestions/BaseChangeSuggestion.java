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
package org.gbif.api.model.collections.suggestions;

import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.util.validators.email.ValidEmail;
import org.gbif.api.vocabulary.Country;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class BaseChangeSuggestion<T extends CollectionEntity>
    implements ChangeSuggestion<T> {

  @Schema(
    description = "Identifier for the change suggestion.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer key;

  @NotNull private Type type;
  private Status status;
  private UUID entityKey;
  private String entityName;
  private Country entityCountry;
  @Valid private T suggestedEntity;
  private Date proposed;
  private String proposedBy;

  @NotNull(groups = PrePersist.class)
  @ValidEmail
  private String proposerEmail;

  private Date applied;
  private String appliedBy;
  private Date discarded;
  private String discardedBy;
  private List<String> comments = new ArrayList<>();
  private UUID mergeTargetKey;
  private List<Change> changes = new ArrayList<>();
  private Date modified;
  private String modifiedBy;

  @Override
  public Integer getKey() {
    return key;
  }

  @Override
  public void setKey(Integer key) {
    this.key = key;
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public UUID getEntityKey() {
    return entityKey;
  }

  @Override
  public void setEntityKey(UUID entityKey) {
    this.entityKey = entityKey;
  }

  @Override
  public String getEntityName() {
    return entityName;
  }

  @Override
  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  @Override
  public Country getEntityCountry() {
    return entityCountry;
  }

  @Override
  public void setEntityCountry(Country entityCountry) {
    this.entityCountry = entityCountry;
  }

  @Override
  public T getSuggestedEntity() {
    return suggestedEntity;
  }

  @Override
  public void setSuggestedEntity(T suggestedEntity) {
    this.suggestedEntity = suggestedEntity;
  }

  @Override
  public Date getProposed() {
    return proposed;
  }

  @Override
  public void setProposed(Date proposed) {
    this.proposed = proposed;
  }

  @Override
  public String getProposedBy() {
    return proposedBy;
  }

  @Override
  public void setProposedBy(String proposedBy) {
    this.proposedBy = proposedBy;
  }

  @Override
  public String getProposerEmail() {
    return proposerEmail;
  }

  @Override
  public void setProposerEmail(String proposerEmail) {
    this.proposerEmail = proposerEmail;
  }

  @Override
  public Date getApplied() {
    return applied;
  }

  @Override
  public void setApplied(Date applied) {
    this.applied = applied;
  }

  @Override
  public String getAppliedBy() {
    return appliedBy;
  }

  @Override
  public void setAppliedBy(String appliedBy) {
    this.appliedBy = appliedBy;
  }

  @Override
  public Date getDiscarded() {
    return discarded;
  }

  @Override
  public void setDiscarded(Date discarded) {
    this.discarded = discarded;
  }

  @Override
  public String getDiscardedBy() {
    return discardedBy;
  }

  @Override
  public void setDiscardedBy(String discardedBy) {
    this.discardedBy = discardedBy;
  }

  @Override
  public List<String> getComments() {
    return comments;
  }

  @Override
  public void setComments(List<String> comments) {
    this.comments = comments;
  }

  @Override
  public UUID getMergeTargetKey() {
    return mergeTargetKey;
  }

  @Override
  public void setMergeTargetKey(UUID mergeTargetKey) {
    this.mergeTargetKey = mergeTargetKey;
  }

  @Override
  public List<Change> getChanges() {
    return changes;
  }

  @Override
  public void setChanges(List<Change> changes) {
    this.changes = changes;
  }

  @Override
  public Date getModified() {
    return modified;
  }

  @Override
  public void setModified(Date modified) {
    this.modified = modified;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseChangeSuggestion<?> that = (BaseChangeSuggestion<?>) o;
    return Objects.equals(key, that.key)
        && type == that.type
        && status == that.status
        && Objects.equals(entityKey, that.entityKey)
        && Objects.equals(entityName, that.entityName)
        && entityCountry == that.entityCountry
        && Objects.equals(suggestedEntity, that.suggestedEntity)
        && Objects.equals(proposed, that.proposed)
        && Objects.equals(proposedBy, that.proposedBy)
        && Objects.equals(proposerEmail, that.proposerEmail)
        && Objects.equals(applied, that.applied)
        && Objects.equals(appliedBy, that.appliedBy)
        && Objects.equals(discarded, that.discarded)
        && Objects.equals(discardedBy, that.discardedBy)
        && Objects.equals(comments, that.comments)
        && Objects.equals(mergeTargetKey, that.mergeTargetKey)
        && Objects.equals(changes, that.changes)
        && Objects.equals(modified, that.modified)
        && Objects.equals(modifiedBy, that.modifiedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        type,
        status,
        entityKey,
        entityName,
        entityCountry,
        suggestedEntity,
        proposed,
        proposedBy,
        proposerEmail,
        applied,
        appliedBy,
        discarded,
        discardedBy,
        comments,
        mergeTargetKey,
        changes,
        modified,
        modifiedBy);
  }

  @Override
  public String toString() {
    return "BaseChangeSuggestion{" +
      "key=" + key +
      ", type=" + type +
      ", status=" + status +
      ", entityKey=" + entityKey +
      ", entityName='" + entityName + '\'' +
      ", entityCountry=" + entityCountry +
      ", suggestedEntity=" + suggestedEntity +
      ", proposed=" + proposed +
      ", proposedBy='" + proposedBy + '\'' +
      ", proposerEmail='" + proposerEmail + '\'' +
      ", applied=" + applied +
      ", appliedBy='" + appliedBy + '\'' +
      ", discarded=" + discarded +
      ", discardedBy='" + discardedBy + '\'' +
      ", comments=" + comments +
      ", mergeTargetKey=" + mergeTargetKey +
      ", changes=" + changes +
      ", modified=" + modified +
      ", modifiedBy='" + modifiedBy + '\'' +
      '}';
  }
}
