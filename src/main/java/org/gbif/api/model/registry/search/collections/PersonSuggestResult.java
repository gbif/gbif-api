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
package org.gbif.api.model.registry.search.collections;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Model object for suggest searchs of persons in collections.
 */
public class PersonSuggestResult {

  private UUID key;
  private String firstName;
  private String lastName;
  private String email;

  public PersonSuggestResult() {

  }

  public PersonSuggestResult(UUID key, String firstName, String lastName, String email) {
    this.key = key;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonSuggestResult that = (PersonSuggestResult) o;
    return Objects.equals(key, that.key)
           && Objects.equals(firstName, that.firstName)
           && Objects.equals(lastName,
                             that.lastName)
           && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, firstName, lastName, email);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PersonSuggestResult.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("firstName='" + firstName + "'")
      .add("lastName='" + lastName + "'")
      .add("email='" + email + "'")
      .toString();
  }
}
