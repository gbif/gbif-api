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

import org.gbif.api.model.collections.OccurrenceMapping;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/** Service that provides a set of operations on {@link OccurrenceMapping}. */
public interface OccurrenceMappingService {

  /**
   * Add a new {@link OccurrenceMapping} to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param occurrenceMapping {@link OccurrenceMapping} to add
   * @return key of OccurrenceMapping added
   */
  int addOccurrenceMapping(
      @NotNull UUID targetEntityKey, @NotNull @Valid OccurrenceMapping occurrenceMapping);

  /**
   * Delete an existing {@link OccurrenceMapping} from a target entity by OccurrenceMapping key.
   *
   * @param targetEntityKey key of target entity
   * @param occurrenceMappingKey {@link OccurrenceMapping} key to delete
   */
  void deleteOccurrenceMapping(@NotNull UUID targetEntityKey, int occurrenceMappingKey);

  /**
   * List all {@link OccurrenceMapping} of a target entity.
   *
   * @param targetEntityKey key of target entity
   * @return list of {@link OccurrenceMapping} that belong to the entity
   */
  List<OccurrenceMapping> listOccurrenceMappings(@NotNull UUID targetEntityKey);
}
