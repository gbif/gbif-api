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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.Size;

/**
 * A citation can have one or both of a textual component and an identifier which can be
 * of any form, but expected to be resolvable in some way such as an LSID, DOI or similar.
 */
public class Citation implements Serializable {

  public static class Contact implements Serializable {

    private Integer key;

    private String abbreviatedName;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    private Set<ContactType> roles;

    @Nullable
    private Set<String> userId;

    public Contact(){}

    public Contact(
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
      Contact contact = (Contact) o;
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
      return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("abbreviatedName='" + abbreviatedName + "'")
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("roles=" + roles)
        .add("userId=" + userId)
        .toString();
    }
  }

  private static final long serialVersionUID = 5587531070690709593L;

  @Nullable private String text;

  @Nullable
  @Size(min = 1, max = 100)
  private String identifier;

  private List<Contact> contacts;

  public Citation() {}

  public Citation(String text, String identifier, List<Contact> contacts) {
    this.text = text;
    this.identifier = identifier;
    this.contacts = contacts;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Citation citation = (Citation) o;
    return Objects.equals(text, citation.text) && Objects.equals(identifier, citation.identifier) &&
           Objects.equals(contacts, citation.contacts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, identifier, contacts);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Citation.class.getSimpleName() + "[", "]")
        .add("text='" + text + "'")
        .add("identifier='" + identifier + "'")
        .add("contacts='" + contacts + "'")
        .toString();
  }
}
