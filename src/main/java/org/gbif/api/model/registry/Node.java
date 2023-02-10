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
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.NodeType;
import org.gbif.api.vocabulary.ParticipationStatus;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A GBIF participant node.
 *
 * Primarily managed in the GBIF Directory.
 */
@Schema(
  description = "A participant node, a country and its nominated organization " +
    "or an international organization which is a member of GBIF.\n\n" +
    "Note that participant nodes are managed in an internal GBIF system," +
    "so methods to create, update and delete participant nodes are generally " +
    "used only to synchronize the two systems.",
  externalDocs = @ExternalDocumentation(
    description = "Further information on GBIF's governance structure",
    url = "https://www.gbif.org/what-is-gbif"
  )
)
@SuppressWarnings("unused")
public class Node
    implements NetworkEntity,
    Contactable,
    Taggable,
    MachineTaggable,
    Commentable,
    Identifiable,
    Endpointable,
    LenientEquals<Node>,
    Address {

  @Schema(
    description = "Unique GBIF key for the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private UUID key;

  @Schema(
    description = "The membership type of the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private NodeType type;

  @Schema(
    description = "The membership status of the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private ParticipationStatus participationStatus;

  @Schema(
    description = "The year the participant node first joined GBIF.",
    minimum = "2001",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Integer participantSince;

  @Schema(
    description = "The date the participant node signed the GBIF Memorandum " +
      "of Understanding.",
    minimum = "2001-01-18",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date dateSignedMOU;

  @Schema(
    description = "The political region, based on " +
      "[IPBES regions](https://www.nature.com/articles/sdata20167/figures/1), " +
      "to which this participant node belongs.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private GbifRegion gbifRegion;

  @Schema(
    description = "Not used"
  )
  @Hidden
  private Continent continent;

  @Schema(
    description = "The name of the participant node.",
    example = "U.S. Geological Survey",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String title;

  @Schema(
    description = "The name of the participant.",
    example = "United States of America",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String participantTitle;

  @Schema(
    description = "The abbreviation for the participant node.",
    example = "USGS",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String abbreviation;

  @Schema(
    description = "The description of the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String description;

  @Schema(
    description = "Email addresses associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<String> email = new ArrayList<>();

  @Schema(
    description = "Telephone numbers associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<String> phone = new ArrayList<>();

  @Schema(
    description = "Homepages with further details on the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<URI> homepage = new ArrayList<>();

  @Schema(
    description = "A logo for the participant node, accessible over HTTP.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private URI logoUrl;

  @Schema(
    description = "Not used"
  )
  @Hidden
  private String organization;

  @Schema(
    description = "Address lines other than the city, province, country and" +
      "postal code, which have their own fields."
  )
  private List<String> address = new ArrayList<>();

  @Schema(
    description = "The city or similar line of the participant node's address."
  )
  private String city;

  @Schema(
    description = "The province or similar line of the participant node's address."
  )
  private String province;

  @Schema(
    description = "The country or other region of the participant node's address."
  )
  private Country country;

  @Schema(
    description = "The postal code or similar line of the participant node's address."
  )
  private String postalCode;

  @Schema(
    description = "The GBIF username of the creator of the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the participant node was created *in " +
      "the Registry*.  See also, `participantSince`.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the participant node was modified.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Schema(
    description = "If present, the participant node was deleted at this time. " +
      "It is possible for it to be restored in the future.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date deleted;

  @Schema(
    description = "A list of contacts associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Contact> contacts = new ArrayList<>();

  @Schema(
    description = "A list of endpoints associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Endpoint> endpoints = new ArrayList<>();

  @Schema(
    description = "A list of machine tags associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(
    description = "A list of tags associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Tag> tags = new ArrayList<>();

  @Schema(
    description = "A list of identifiers associated with this participant node.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(
    description = "A list of comments associated with this participant node.",
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
  public String getOrganization() {
    return organization;
  }

  @Override
  public void setOrganization(String organization) {
    this.organization = organization;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(key, node.key)
        && type == node.type
        && participationStatus == node.participationStatus
        && Objects.equals(participantSince, node.participantSince)
        && Objects.equals(dateSignedMOU, node.dateSignedMOU)
        && gbifRegion == node.gbifRegion
        && continent == node.continent
        && Objects.equals(title, node.title)
        && Objects.equals(participantTitle, node.participantTitle)
        && Objects.equals(abbreviation, node.abbreviation)
        && Objects.equals(description, node.description)
        && Objects.equals(email, node.email)
        && Objects.equals(phone, node.phone)
        && Objects.equals(homepage, node.homepage)
        && Objects.equals(logoUrl, node.logoUrl)
        && Objects.equals(organization, node.organization)
        && Objects.equals(address, node.address)
        && Objects.equals(city, node.city)
        && Objects.equals(province, node.province)
        && country == node.country
        && Objects.equals(postalCode, node.postalCode)
        && Objects.equals(createdBy, node.createdBy)
        && Objects.equals(modifiedBy, node.modifiedBy)
        && Objects.equals(created, node.created)
        && Objects.equals(modified, node.modified)
        && Objects.equals(deleted, node.deleted)
        && Objects.equals(contacts, node.contacts)
        && Objects.equals(endpoints, node.endpoints)
        && Objects.equals(machineTags, node.machineTags)
        && Objects.equals(tags, node.tags)
        && Objects.equals(identifiers, node.identifiers)
        && Objects.equals(comments, node.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        type,
        participationStatus,
        participantSince,
        dateSignedMOU,
        gbifRegion,
        continent,
        title,
        participantTitle,
        abbreviation,
        description,
        email,
        phone,
        homepage,
        logoUrl,
        organization,
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
    return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("type=" + type)
        .add("participationStatus=" + participationStatus)
        .add("participantSince=" + participantSince)
        .add("dateSignedMOU=" + dateSignedMOU)
        .add("gbifRegion=" + gbifRegion)
        .add("continent=" + continent)
        .add("title='" + title + "'")
        .add("participantTitle='" + participantTitle + "'")
        .add("abbreviation='" + abbreviation + "'")
        .add("description='" + description + "'")
        .add("email=" + email)
        .add("phone=" + phone)
        .add("homepage=" + homepage)
        .add("logoUrl=" + logoUrl)
        .add("organization='" + organization + "'")
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
    return Objects.equals(this.type, other.type)
        && Objects.equals(this.participationStatus, other.participationStatus)
        && Objects.equals(this.gbifRegion, other.gbifRegion)
        && Objects.equals(this.continent, other.continent)
        && Objects.equals(this.title, other.title)
        && Objects.equals(this.participantTitle, other.participantTitle)
        && Objects.equals(this.country, other.country);
  }
}
