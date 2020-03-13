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

import org.gbif.api.util.HttpURI;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A GBIF network.
 */
public class Network implements NetworkEntity, Contactable, Endpointable, MachineTaggable, Taggable, Commentable,
  Identifiable, LenientEquals<Network>, Address {

  private UUID key;
  private String title;
  private String description;
  private Language language;
  private int numConstituents;
  private List<String> email;
  private List<String> phone;
  private List<URI> homepage;
  private URI logoUrl;
  private List<String> address;
  private String city;
  private String province;
  private Country country;
  private String postalCode;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;
  private List<Contact> contacts = new ArrayList<>();
  private List<Endpoint> endpoints = new ArrayList<>();
  private List<MachineTag> machineTags = new ArrayList<>();
  private List<Tag> tags = new ArrayList<>();
  private List<Identifier> identifiers = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();

  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public Date getModified() {
    return modified;
  }

  @Override
  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public Date getDeleted() {
    return deleted;
  }

  @Override
  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @NotNull
  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  @Nullable
  public List<String> getEmail() {
    return email;
  }

  public void setEmail(List<String> email) {
    this.email = email;
  }

  @Nullable
  public List<String> getPhone() {
    return phone;
  }

  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  @Nullable
  public List<URI> getHomepage() {
    return homepage;
  }

  public void setHomepage(List<URI> homepage) {
    this.homepage = homepage;
  }

  @HttpURI
  @Nullable
  public URI getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(URI logoUrl) {
    this.logoUrl = logoUrl;
  }

  @Nullable
  public List<String> getAddress() {
    return address;
  }

  public void setAddress(List<String> address) {
    this.address = address;
  }

  @Nullable
  @Size(min = 1)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Nullable
  @Size(min = 1)
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  @Nullable
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Nullable
  @Size(min = 1)
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  @Override
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Override
  public List<Contact> getContacts() {
    return contacts;
  }

  @Override
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @Override
  public List<Endpoint> getEndpoints() {
    return endpoints;
  }

  @Override
  public void setEndpoints(List<Endpoint> endpoints) {
    this.endpoints = endpoints;
  }

  @Override
  public void addEndpoint(Endpoint endpoint) {
    endpoints.add(endpoint);
  }

  @Override
  public List<MachineTag> getMachineTags() {
    return machineTags;
  }

  @Override
  public void setMachineTags(List<MachineTag> machineTags) {
    this.machineTags = machineTags;
  }

  @Override
  public void addMachineTag(MachineTag machineTag) {
    machineTags.add(machineTag);
  }

  @Override
  public List<Tag> getTags() {
    return tags;
  }

  @Override
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  @Override
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  @Override
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  @Override
  public List<Comment> getComments() {
    return comments;
  }

  @Override
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Min(0)
  public int getNumConstituents() {
    return numConstituents;
  }

  public void setNumConstituents(int numConstituents) {
    this.numConstituents = numConstituents;
  }

  /**
   * Alias for the network title
   */
  @Nullable
  @Override
  @JsonIgnore
  public String getOrganization() {
    return getTitle();
  }

  /**
   * Alias for the network title
   */
  @Override
  public void setOrganization(String organization) {
    setTitle(organization);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Network network = (Network) o;
    return numConstituents == network.numConstituents &&
      Objects.equals(key, network.key) &&
      Objects.equals(title, network.title) &&
      Objects.equals(description, network.description) &&
      language == network.language &&
      Objects.equals(email, network.email) &&
      Objects.equals(phone, network.phone) &&
      Objects.equals(homepage, network.homepage) &&
      Objects.equals(logoUrl, network.logoUrl) &&
      Objects.equals(address, network.address) &&
      Objects.equals(city, network.city) &&
      Objects.equals(province, network.province) &&
      country == network.country &&
      Objects.equals(postalCode, network.postalCode) &&
      Objects.equals(createdBy, network.createdBy) &&
      Objects.equals(modifiedBy, network.modifiedBy) &&
      Objects.equals(created, network.created) &&
      Objects.equals(modified, network.modified) &&
      Objects.equals(deleted, network.deleted) &&
      Objects.equals(contacts, network.contacts) &&
      Objects.equals(endpoints, network.endpoints) &&
      Objects.equals(machineTags, network.machineTags) &&
      Objects.equals(tags, network.tags) &&
      Objects.equals(identifiers, network.identifiers) &&
      Objects.equals(comments, network.comments);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, title, description, language, numConstituents, email, phone, homepage, logoUrl,
        address, city, province, country, postalCode, createdBy, modifiedBy, created, modified,
        deleted, contacts, endpoints, machineTags, tags, identifiers, comments);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Network.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("title='" + title + "'")
      .add("description='" + description + "'")
      .add("language=" + language)
      .add("numConstituents=" + numConstituents)
      .add("email=" + email)
      .add("phone=" + phone)
      .add("homepage=" + homepage)
      .add("logoUrl=" + logoUrl)
      .add("address=" + address)
      .add("city='" + city + "'")
      .add("province='" + province + "'")
      .add("country=" + country)
      .add("postalCode='" + postalCode + "'")
      .add("createdBy='" + createdBy + "'")
      .add("modifiedBy='" + modifiedBy + "'")
      .add("created=" + created)
      .add("modified=" + modified)
      .add("deleted=" + deleted)
      .add("contacts=" + contacts)
      .add("endpoints=" + endpoints)
      .add("machineTags=" + machineTags)
      .add("tags=" + tags)
      .add("identifiers=" + identifiers)
      .add("comments=" + comments)
      .toString();
  }

  /**
   * Tests persisted fields, excluding the server controlled values such as key and createdBy. Does not included nested
   * properties in the test.
   */
  @Override
  public boolean lenientEquals(Network other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.title, other.title)
      && Objects.equals(this.description, other.description)
      && Objects.equals(this.language, other.language)
      && Objects.equals(this.email, other.email)
      && Objects.equals(this.phone, other.phone)
      && Objects.equals(this.homepage, other.homepage)
      && Objects.equals(this.logoUrl, other.logoUrl)
      && Objects.equals(this.address, other.address)
      && Objects.equals(this.city, other.city)
      && Objects.equals(this.province, other.province)
      && Objects.equals(this.country, other.country)
      && Objects.equals(this.postalCode, other.postalCode);
  }

}
