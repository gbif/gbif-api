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

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.vocabulary.Country;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ChangeSuggestion<T extends CollectionEntity> extends Serializable {

  Integer getKey();

  void setKey(Integer key);

  Type getType();

  void setType(Type type);

  Status getStatus();

  void setStatus(Status status);

  UUID getEntityKey();

  void setEntityKey(UUID entityKey);

  Country getEntityCountry();

  void setEntityCountry(Country entityCountry);

  String getEntityName();

  void setEntityName(String entityName);

  T getSuggestedEntity();

  void setSuggestedEntity(T suggestedEntity);

  Date getProposed();

  void setProposed(Date proposed);

  String getProposedBy();

  void setProposedBy(String proposedBy);

  String getProposerEmail();

  void setProposerEmail(String proposerEmail);

  Date getApplied();

  void setApplied(Date applied);

  String getAppliedBy();

  void setAppliedBy(String appliedBy);

  Date getDiscarded();

  void setDiscarded(Date discarded);

  String getDiscardedBy();

  void setDiscardedBy(String discardedBy);

  List<String> getComments();

  void setComments(List<String> comments);

  UUID getMergeTargetKey();

  void setMergeTargetKey(UUID mergeTargetKey);

  List<Change> getChanges();

  void setChanges(List<Change> changes);

  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  Date getModified();

  void setModified(Date modified);
}
