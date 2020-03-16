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
package org.gbif.api.model.registry.eml;

import org.gbif.api.model.registry.Contact;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;

/**
 * A dataset can be part of a project. A project can have a unique identifier, used to link datasets associated with
 * the same project.
 */
public class Project implements Serializable {

  private static final long serialVersionUID = -2625204169061362016L;

  private String title;
  // TODO: enable searching datasets by their project identifier: http://dev.gbif.org/issues/browse/POR-3129
  private String identifier;
  private String description;

  private List<Contact> contacts;
  private String abstract_;
  private String funding;
  private String studyAreaDescription;
  private String designDescription;

  public Project() {
  }

  public Project(
    String title, String identifier, List<Contact> contacts, String abstract_, String funding,
    String studyAreaDescription, String designDescription) {
    this.title = title;
    this.identifier = identifier;
    this.contacts = contacts;
    this.abstract_ = abstract_;
    this.funding = funding;
    this.studyAreaDescription = studyAreaDescription;
    this.designDescription = designDescription;
  }

  public String getAbstract() {
    return abstract_;
  }

  public void setAbstract(String abstract_) {
    this.abstract_ = abstract_;
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

  /**
   * A unique identifier for the project. Used to link multiple datasets associated with the same project.
   *
   * @return the unique identifier for the project
   */
  @Nullable
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(title, project.title) &&
      Objects.equals(identifier, project.identifier) &&
      Objects.equals(description, project.description) &&
      Objects.equals(contacts, project.contacts) &&
      Objects.equals(abstract_, project.abstract_) &&
      Objects.equals(funding, project.funding) &&
      Objects.equals(studyAreaDescription, project.studyAreaDescription) &&
      Objects.equals(designDescription, project.designDescription);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(title, identifier, description, contacts, abstract_, funding, studyAreaDescription,
        designDescription);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Project.class.getSimpleName() + "[", "]")
      .add("title='" + title + "'")
      .add("identifier='" + identifier + "'")
      .add("description='" + description + "'")
      .add("contacts=" + contacts)
      .add("abstract_='" + abstract_ + "'")
      .add("funding='" + funding + "'")
      .add("studyAreaDescription='" + studyAreaDescription + "'")
      .add("designDescription='" + designDescription + "'")
      .toString();
  }
}
