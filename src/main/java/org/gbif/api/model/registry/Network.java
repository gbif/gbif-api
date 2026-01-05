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

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A GBIF network.
 *
 * Networks are collections of datasets, organized outside the Node-Organization
 * model to serve some purpose.
 */
@Schema(
  description = "Networks are collections of datasets, organized outside " +
    "the Node-Organization model to serve some purpose."
)
@SuppressWarnings("unused")
public class Network
    implements NetworkEntity,
    Contactable,
    Endpointable,
    MachineTaggable,
    Taggable,
    Commentable,
    Identifiable,
    LenientEquals<Network>,
    Address {

  @Schema(
    description = "Unique GBIF key for the network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private UUID key;

  @Schema(
    description = "A name for the network.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private String title;

  @Schema(
    description = "A description for the network."
  )
  private String description;

  @Schema(
    description = "The language of the network metadata.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private Language language;

  @Schema(
    description = "The number of datasets collected in this network."
  )
  private int numConstituents;

  @Schema(
    description = "Email addresses associated with this network."
  )
  private List<String> email;

  @Schema(
    description = "Telephone numbers associated with this network."
  )
  private List<String> phone;

  @Schema(
    description = "Homepages with further details on the network."
  )
  private List<URI> homepage;

  @Schema(
    description = "A logo for the network, accessible over HTTP."
  )
  private URI logoUrl;

  @Schema(
    description = "Address lines other than the city, province, country and" +
      "postal code, which have their own fields."
  )
  private List<String> address;

  @Schema(
    description = "The city or similar line of the network's address."
  )
  private String city;

  @Schema(
    description = "The province or similar line of the network's address."
  )
  private String province;

  @Schema(
    description = "The country or other region of the network's address."
  )
  private Country country;

  @Schema(
    description = "The postal code or similar line of the network's address."
  )
  private String postalCode;

  @Schema(
    description = "The GBIF username of the creator of the network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the network was created.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the network was modified.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Schema(
    description = "If present, the network was deleted at this time. " +
      "It is possible for it to be restored in the future.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date deleted;

  @Schema(
    description = "A list of contacts associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Contact> contacts = new ArrayList<>();

  @Schema(
    description = "A list of endpoints associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Endpoint> endpoints = new ArrayList<>();

  @Schema(
    description = "A list of machine tags associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(
    description = "A list of tags associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Tag> tags = new ArrayList<>();

  @Schema(
    description = "A list of identifiers associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(
    description = "A list of comments associated with this network.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
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
  @Override
  public List<String> getEmail() {
    return email;
  }

  @Override
  public void setEmail(List<String> email) {
    this.email = email;
  }

  @Nullable
  @Override
  public List<String> getPhone() {
    return phone;
  }

  @Override
  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  @Nullable
  @Override
  public List<URI> getHomepage() {
    return homepage;
  }

  @Override
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
  @Override
  public List<String> getAddress() {
    return address;
  }

  @Override
  public void setAddress(List<String> address) {
    this.address = address;
  }

  @Nullable
  @Size(min = 1)
  @Override
  public String getCity() {
    return city;
  }

  @Override
  public void setCity(String city) {
    this.city = city;
  }

  @Nullable
  @Size(min = 1)
  @Override
  public String getProvince() {
    return province;
  }

  @Override
  public void setProvince(String province) {
    this.province = province;
  }

  @Nullable
  @Override
  public Country getCountry() {
    return country;
  }

  @Override
  public void setCountry(Country country) {
    this.country = country;
  }

  @Nullable
  @Size(min = 1)
  @Override
  public String getPostalCode() {
    return postalCode;
  }

  @Override
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
    return numConstituents == network.numConstituents
        && Objects.equals(key, network.key)
        && Objects.equals(title, network.title)
        && Objects.equals(description, network.description)
        && language == network.language
        && Objects.equals(email, network.email)
        && Objects.equals(phone, network.phone)
        && Objects.equals(homepage, network.homepage)
        && Objects.equals(logoUrl, network.logoUrl)
        && Objects.equals(address, network.address)
        && Objects.equals(city, network.city)
        && Objects.equals(province, network.province)
        && country == network.country
        && Objects.equals(postalCode, network.postalCode)
        && Objects.equals(createdBy, network.createdBy)
        && Objects.equals(modifiedBy, network.modifiedBy)
        && Objects.equals(created, network.created)
        && Objects.equals(modified, network.modified)
        && Objects.equals(deleted, network.deleted)
        && Objects.equals(contacts, network.contacts)
        && Objects.equals(endpoints, network.endpoints)
        && Objects.equals(machineTags, network.machineTags)
        && Objects.equals(tags, network.tags)
        && Objects.equals(identifiers, network.identifiers)
        && Objects.equals(comments, network.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        title,
        description,
        language,
        numConstituents,
        email,
        phone,
        homepage,
        logoUrl,
        address,
        city,
        province,
        country,
        postalCode,
        createdBy,
        modifiedBy,
        created,
        modified,
        deleted,
        contacts,
        endpoints,
        machineTags,
        tags,
        identifiers,
        comments);
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
    if (other == null) return false;
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
