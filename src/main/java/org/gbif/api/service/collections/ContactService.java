/*
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
import org.gbif.api.model.collections.Contact;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.gbif.api.model.collections.suggestions.ChangeSuggestion;

/** API Service for the contacts in the collections service. */
public interface ContactService {

  /**
   * Lists all the contacts of an entity.
   *
   * @param entityKey of the entity
   * @return list of {@link org.gbif.api.model.collections.Contact}
   */
  List<Contact> listContactPersons(@NotNull UUID entityKey);

  /**
   * Adds a {@link Contact} contact to an entity.
   *
   * @param entityKey key of the entity where the contact will be added to.
   * @param contact key of the contact to add.
   */
  int addContactPerson(@NotNull UUID entityKey, @NotNull @Valid Contact contact);

  /**
   * Updates a {@link Contact} contact in an entity.
   *
   * @param entityKey key of the entity where the contact will be updated.
   * @param contact updated contact.
   */
  void updateContactPerson(@NotNull UUID entityKey, @NotNull @Valid Contact contact);

  /**
   * Removes a {@link Contact} contact from an entity.
   *
   * @param entityKey key of the entity where the contact will be removed from.
   * @param contactKey key of the contact to remove.
   */
  void removeContactPerson(@NotNull UUID entityKey, @NotNull int contactKey);

  /**
   * Removes all the contacts from an entity.
   *
   * @param entityKey key of the entity where the contact will be removed from.
   * @param newContactPersons contact persons that will replace the existing ones.
   */
  void replaceContactPersons(@NotNull UUID entityKey, List<@Valid Contact> newContactPersons);

  /**
   * Adds a {@link Contact} IH contact to an entity.
   *
   * @param entityKey key of the entity where the contact will be added to.
   * @param changeSuggestion suggestion to get contacts from
   */
  <T extends CollectionEntity> void addSuggestionContacts(@NotNull UUID entityKey, @NotNull ChangeSuggestion<T> changeSuggestion);
}
