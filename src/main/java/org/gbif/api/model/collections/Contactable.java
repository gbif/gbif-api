/*
 * Copyright 2020-2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.collections;

import java.util.List;

import javax.annotation.Nullable;

/** Entity that can have a list of contacts and addresses. */
public interface Contactable {

  /** @deprecated replaced by {@link #getContactPersons()} */
  @Deprecated
  @Nullable
  List<Person> getContacts();

  /**
   * @deprecated replaced by {@link #setContactPersons(List)}
   */
  @Deprecated
  void setContacts(List<Person> contacts);

  /** List of associated contacts. */
  @Nullable
  List<Contact> getContactPersons();

  void setContactPersons(List<Contact> contacts);

  /** Address used to send and receive mail. */
  @Nullable
  Address getMailingAddress();

  void setMailingAddress(Address mailingAddress);

  /** Physical or associated address. */
  @Nullable
  Address getAddress();

  void setAddress(Address address);
}
