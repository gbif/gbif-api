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

import org.gbif.api.model.registry.Contact;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Service provides a set of operations on {@link Contact}.
 */
@SuppressWarnings("unused")
public interface ContactService {

  /**
   * Add a new Contact to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param contact         Contact to add
   *
   * @return key of Contact added
   */
  int addContact(@NotNull UUID targetEntityKey, @NotNull @Valid Contact contact);

  /**
   * Delete an existing Contact from a target entity by contact key.
   *
   * @param targetEntityKey key of target entity
   * @param contactKey      Contact key to delete
   */
  void deleteContact(@NotNull UUID targetEntityKey, int contactKey);

  /**
   * List all contacts of a target entity.
   *
   * @param targetEntityKey key of target entity
   *
   * @return list of contacts that belong to the entity
   */
  List<Contact> listContacts(@NotNull UUID targetEntityKey);

  /**
   * Updates the given contact for the target entity.
   *
   * @param targetEntityKey Which must be the the owner of the contact or else an exception will be thrown.
   * @param contact To update
   */
  void updateContact(@NotNull UUID targetEntityKey, @NotNull @Valid Contact contact);
}
