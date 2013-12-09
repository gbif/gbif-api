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
package org.gbif.api.service.registry;

import org.gbif.api.model.registry.Contact;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public interface ContactService {

  int addContact(@NotNull UUID targetEntityKey, @NotNull Contact contact);

  void deleteContact(@NotNull UUID targetEntityKey, int contactKey);

  List<Contact> listContacts(@NotNull UUID targetEntityKey);

  /**
   * Updates the given contact for the target entity.
   * 
   * @param targetEntityKey Which must be the the owner of the contact or else an exception will be thrown.
   * @param contact To update
   */
  void updateContact(@NotNull UUID targetEntityKey, @NotNull Contact contact);

}
