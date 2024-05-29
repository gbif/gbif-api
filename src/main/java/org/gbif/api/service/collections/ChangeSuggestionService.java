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
package org.gbif.api.service.collections;

import org.gbif.api.model.collections.CollectionEntity;
import org.gbif.api.model.collections.suggestions.ChangeSuggestion;
import org.gbif.api.model.collections.suggestions.Status;
import org.gbif.api.model.collections.suggestions.Type;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;

/** Defines the service to suggest changes in GRSciColl entities. */
public interface ChangeSuggestionService<
    T extends CollectionEntity, R extends ChangeSuggestion<T>> {

  int createChangeSuggestion(@Valid R changeSuggestion);

  void updateChangeSuggestion(@Valid R changeSuggestion);

  void discardChangeSuggestion(int key);

  // returns the entity created if applies
  UUID applyChangeSuggestion(int key);

  R getChangeSuggestion(int key);

  PagingResponse<R> list(
      @Nullable Status status,
      @Nullable Type type,
      @Nullable String proposedBy,
      @Nullable UUID entityKey,
      @Nullable Pageable page);
}
