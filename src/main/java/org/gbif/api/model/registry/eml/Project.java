/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.registry.eml;

import org.gbif.api.model.registry.Contact;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;


/**
 * A dataset can be part of a project.
 */
public class Project implements Serializable {

  private static final long serialVersionUID = -2625204169061362016L;

  private String title;
  private String description;

  private List<Contact> contacts;
  private String funding;
  private String studyAreaDescription;
  private String designDescription;

  public Project() {
  }

  public Project(
    String title, List<Contact> contacts, String funding, String studyAreaDescription, String designDescription
  ) {
    this.title = title;
    this.contacts = contacts;
    this.funding = funding;
    this.studyAreaDescription = studyAreaDescription;
    this.designDescription = designDescription;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public String getDesignDescription() {
    return designDescription;
  }

  public void setDesignDescription(String designDescription) {
    this.designDescription = designDescription;
  }

  public String getFunding() {
    return funding;
  }

  public void setFunding(String funding) {
    this.funding = funding;
  }

  public String getStudyAreaDescription() {
    return studyAreaDescription;
  }

  public void setStudyAreaDescription(String studyAreaDescription) {
    this.studyAreaDescription = studyAreaDescription;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Add contact to Contact List.
   */
  public void addContact(Contact contact) {
    if (contacts == null) {
      contacts = new LinkedList<Contact>();
    }
    contacts.add(contact);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Project)) {
      return false;
    }

    Project that = (Project) obj;
    return Objects.equal(this.title, that.title)
           && Objects.equal(this.description, that.description)
           && Objects.equal(this.contacts, that.contacts)
           && Objects.equal(this.funding, that.funding)
           && Objects.equal(this.studyAreaDescription, that.studyAreaDescription)
           && Objects.equal(this.designDescription, that.designDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(title, contacts, funding, studyAreaDescription, designDescription);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("title", title)
      .add("description", description)
      .add("contacts", contacts)
      .add("funding", funding)
      .add("studyAreaDescription", studyAreaDescription)
      .add("designDescription", designDescription)
      .toString();
  }

}
