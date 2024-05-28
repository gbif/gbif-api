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
package org.gbif.api.service.registry;

import org.gbif.api.model.registry.Identifier;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service provides a set of operations on {@link Identifier}.
 */
@SuppressWarnings("unused")
public interface IdentifierService {

  /**
   * Add a new Identifier to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param identifier      Identifier to add
   *
   * @return key of Identifier added
   */
  int addIdentifier(@NotNull UUID targetEntityKey, @NotNull @Valid Identifier identifier);

  /**
   * Delete an existing Identifier from a target entity by identifier key.
   *
   * @param targetEntityKey key of target entity
   * @param identifierKey   Identifier key to delete
   */
  void deleteIdentifier(@NotNull UUID targetEntityKey, int identifierKey);

  /**
   * List all identifiers of a target entity.
   *
   * @param targetEntityKey key of target entity
   *
   * @return list of identifiers that belong to the entity
   */
  List<Identifier> listIdentifiers(@NotNull UUID targetEntityKey);
}
