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

import org.gbif.api.model.collections.Person;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * API Service for the contacts in the collections service.
 */
public interface ContactService {

  /**
   * Lists all the contacts of an entity.
   *
   * @param entityKey of the entity
   * @return list of {@link Person}
   */
  List<Person> listContacts(@NotNull UUID entityKey);

  /**
   * Adds a {@link Person} contact to an entity.
   *
   * @param entityKey key of the entity where the contact will be added to.
   * @param personKey key of the contact to add.
   */
  void addContact(@NotNull UUID entityKey, @NotNull UUID personKey);

  /**
   * Removes a {@link Person} contact from an entity.
   *
   * @param entityKey key of the entity where the contact will be removed from.
   * @param personKey key of the contact to remove.
   */
  void removeContact(@NotNull UUID entityKey, @NotNull UUID personKey);
}
