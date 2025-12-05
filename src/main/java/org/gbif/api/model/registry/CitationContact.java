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
package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.ContactType;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import jakarta.annotation.Nullable;

/**
 * A contact used to generate a dataset citation.
 */
public class CitationContact implements Serializable {

  private Integer key;

  private String abbreviatedName;

  @Nullable
  private String firstName;

  @Nullable
  private String lastName;

  private Set<ContactType> roles;

  @Nullable
  private Set<String> userId;

  public CitationContact(){}

  public CitationContact(
    Integer key,
    String abbreviatedName,
    @Nullable String firstName,
    @Nullable String lastName,
    Set<ContactType> roles,
    @Nullable Set<String> userId
  ) {
    this.key = key;
    this.abbreviatedName = abbreviatedName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.roles = roles;
    this.userId = userId;
  }

  /**
   * Key associated to this contact.
   */
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * Abbreviated name used for the citation.
   */
  public String getAbbreviatedName() {
    return abbreviatedName;
  }

  public void setAbbreviatedName(String abbreviatedName) {
    this.abbreviatedName = abbreviatedName;
  }

  /**
   * Contact's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Contact's last name.
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Roles or contact type of this contact.
   */
  public Set<ContactType> getRoles() {
    return roles;
  }

  public void setRoles(Set<ContactType> roles) {
    this.roles = roles;
  }

  /**
   * GBIF Portal users associated to this contact.
   */
  public Set<String> getUserId() {
    return userId;
  }

  public void setUserId(Set<String> userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CitationContact contact = (CitationContact) o;
    return Objects.equals(key, contact.key) && Objects.equals(abbreviatedName, contact.abbreviatedName) &&
           Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName) &&
           Objects.equals(roles, contact.roles) && Objects.equals(userId, contact.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, abbreviatedName, firstName, lastName, roles, userId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CitationContact.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("abbreviatedName='" + abbreviatedName + "'")
      .add("firstName='" + firstName + "'")
      .add("lastName='" + lastName + "'")
      .add("roles=" + roles)
      .add("userId=" + userId)
      .toString();
  }
}
