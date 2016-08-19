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
package org.gbif.api.model.registry;

import org.gbif.api.util.HttpURI;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.NodeType;
import org.gbif.api.vocabulary.ParticipationStatus;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * A GBIF participant node.
 */
public class Node implements NetworkEntity, Contactable, Taggable, MachineTaggable, Commentable, Identifiable,
  Endpointable, LenientEquals<Node>, Address {

  private UUID key;
  private NodeType type;
  private ParticipationStatus participationStatus;
  private Integer participantSince;
  private Date dateSignedMOU;
  private GbifRegion gbifRegion;
  private Continent continent;
  private String title;
  private String participantTitle;
  private String abbreviation;
  private String description;
  private List<String> email = Lists.newArrayList();
  private List<String> phone = Lists.newArrayList();
  private List<URI> homepage = Lists.newArrayList();
  private URI logoUrl;
  private String organization;
  private List<String> address = Lists.newArrayList();
  private String city;
  private String province;
  private Country country;
  private String postalCode;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;
  private List<Contact> contacts = Lists.newArrayList();
  private List<Endpoint> endpoints = Lists.newArrayList();
  private List<MachineTag> machineTags = Lists.newArrayList();
  private List<Tag> tags = Lists.newArrayList();
  private List<Identifier> identifiers = Lists.newArrayList();
  private List<Comment> comments = Lists.newArrayList();

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

  public String getParticipantTitle() {
    return participantTitle;
  }

  public void setParticipantTitle(String participantTitle) {
    this.participantTitle = participantTitle;
  }

  @Nullable
  @Size(min = 1, max = 10)
  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
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
  public NodeType getType() {
    return type;
  }

  public void setType(NodeType type) {
    this.type = type;
  }

  @NotNull
  public ParticipationStatus getParticipationStatus() {
    return participationStatus;
  }

  public void setParticipationStatus(ParticipationStatus participationStatus) {
    this.participationStatus = participationStatus;
  }

  /**
   * 4 digit year since the node participant first joined GBIF.
   */
  @Nullable
  public Integer getParticipantSince() {
    return participantSince;
  }

  public void setParticipantSince(Integer participantSince) {
    this.participantSince = participantSince;
  }

  public Date getDateSignedMOU() {
    return dateSignedMOU;
  }

  public void setDateSignedMOU(Date dateSignedMOU) {
    this.dateSignedMOU = dateSignedMOU;
  }

  @Nullable
  public GbifRegion getGbifRegion() {
    return gbifRegion;
  }

  public void setGbifRegion(GbifRegion gbifRegion) {
    this.gbifRegion = gbifRegion;
  }

  @Nullable
  public Continent getContinent() {
    return continent;
  }

  public void setContinent(Continent continent) {
    this.continent = continent;
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
  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
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

  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, type, participationStatus, participantSince, dateSignedMOU, gbifRegion, continent, title, abbreviation,
        description, email, phone, homepage, logoUrl,
        organization, address, city, province, country, postalCode,
        createdBy, modifiedBy, created, modified, deleted, contacts, endpoints, machineTags, tags, identifiers,
        comments, participantTitle);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Node) {
      Node that = (Node) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.participationStatus, that.participationStatus)
        && Objects.equal(this.participantSince, that.participantSince)
        && Objects.equal(this.dateSignedMOU, that.dateSignedMOU)
        && Objects.equal(this.gbifRegion, that.gbifRegion)
        && Objects.equal(this.continent, that.continent)
        && Objects.equal(this.title, that.title)
        && Objects.equal(this.participantTitle, that.participantTitle)
        && Objects.equal(this.abbreviation, that.abbreviation)
        && Objects.equal(this.description, that.description)
        && Objects.equal(this.email, that.email)
        && Objects.equal(this.phone, that.phone)
        && Objects.equal(this.homepage, that.homepage)
        && Objects.equal(this.logoUrl, that.logoUrl)
        && Objects.equal(this.organization, that.organization)
        && Objects.equal(this.address, that.address)
        && Objects.equal(this.city, that.city)
        && Objects.equal(this.province, that.province)
        && Objects.equal(this.country, that.country)
        && Objects.equal(this.postalCode, that.postalCode)
        && Objects.equal(this.createdBy, that.createdBy)
        && Objects.equal(this.modifiedBy, that.modifiedBy)
        && Objects.equal(this.created, that.created)
        && Objects.equal(this.modified, that.modified)
        && Objects.equal(this.deleted, that.deleted)
        && Objects.equal(this.contacts, that.contacts)
        && Objects.equal(this.endpoints, that.endpoints)
        && Objects.equal(this.machineTags, that.machineTags)
        && Objects.equal(this.tags, that.tags)
        && Objects.equal(this.identifiers, that.identifiers)
        && Objects.equal(this.comments, that.comments);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("type", type)
      .add("participationStatus", participationStatus)
      .add("participantSince", participantSince)
      .add("dateSignedMOU", dateSignedMOU)
      .add("gbifRegion", gbifRegion)
      .add("continent", continent)
      .add("title", title)
      .add("participantTitle", participantTitle)
      .add("abbreviation", abbreviation)
      .add("description", description)
      .add("email", email)
      .add("phone", phone)
      .add("homepage", homepage)
      .add("logoUrl", logoUrl)
      .add("organization", organization)
      .add("address", address)
      .add("city", city)
      .add("province", province)
      .add("country", country)
      .add("postalCode", postalCode)
      .add("createdBy", createdBy)
      .add("modifiedBy", modifiedBy)
      .add("created", created)
      .add("modified", modified)
      .add("deleted", deleted)
      .add("contacts", contacts)
      .add("endpoints", endpoints)
      .add("machineTags", machineTags)
      .add("tags", tags)
      .add("identifiers", identifiers)
      .add("comments", comments)
      .toString();
  }

  /**
   * Compares the entities for business logic equality using a very lenient approach.
   * Only fields persisted in the registry database are used in the comparison, excluding any nested properties and
   * supplementary information which can be added from external databases such as the IMS. Server controlled values such
   * as key and createdBy are omitted.
   */
  @Override
  public boolean lenientEquals(Node other) {
    if (this == other) {
      return true;
    }
    return Objects.equal(this.type, other.type)
      && Objects.equal(this.participationStatus, other.participationStatus)
      && Objects.equal(this.gbifRegion, other.gbifRegion)
      && Objects.equal(this.continent, other.continent)
      && Objects.equal(this.title, other.title)
      && Objects.equal(this.participantTitle, other.participantTitle)
      && Objects.equal(this.country, other.country);
  }

}
