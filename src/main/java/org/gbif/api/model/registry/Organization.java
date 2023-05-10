/*
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

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A GBIF data publisher.
 */
@Schema(
  description = "A publishing organization is an institution endorsed by a " +
    "GBIF Participant Node to publish datasets to GBIF."
)
@SuppressWarnings("unused")
public class Organization
    implements NetworkEntity,
    Contactable,
    Endpointable,
    MachineTaggable,
    Taggable,
    Identifiable,
    Commentable,
    LenientEquals<Organization>,
    Address {

  @Schema(
    description = "Unique GBIF key for the publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private UUID key;

  @Schema(
    description = "The participant node which has endorsed or would endorse " +
      "this publishing organization.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private UUID endorsingNodeKey;

  @Schema(
    description = "Whether the participant node in `endorsingNodeKey` has " +
      "endorsed this publishing organization — whether `endorsementStatus == " +
      "ENDORSED`."
  )
  private boolean endorsementApproved;

  @Schema(
    description = "The endorsement decision regarding this publishing " +
      "organization made by the participant node in `endorsingNodeKey`."
  )
  private EndorsementStatus endorsementStatus;

  @Schema(
    description = "A shared token for this publishing organization. " +
      "The token is used to authorize publishing or modifying datasets."
  )
  private String password;

  @Schema(
    description = "The title of the publishing organization.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private String title;

  @Schema(
    description = "The abbreviation for the publishing organization."
  )
  private String abbreviation;

  @Schema(
    description = "The description of the publishing organization."
  )
  private String description;

  @Schema(
    description = "The primary language of the description of the publishing " +
      "organization.\n\n" +
      "*(NB Not required for updates.)*"
  )
  private Language language;

  @Schema(
    description = "Email addresses associated with this publishing organization."
  )
  private List<String> email;

  @Schema(
    description = "Telephone numbers associated with this publishing organization."
  )
  private List<String> phone;

  @Schema(
    description = "Homepages with further details on the publishing organization."
  )
  private List<URI> homepage;

  @Schema(
    description = "A logo for the publishing organization, accessible over HTTP."
  )
  private URI logoUrl;

  @Schema(
    description = "Address lines other than the city, province, country and" +
      "postal code, which have their own fields."
  )
  private List<String> address;

  @Schema(
    description = "The city or similar line of the publishing organization's address."
  )
  private String city;

  @Schema(
    description = "The province or similar line of the publishing organization's address."
  )
  private String province;

  @Schema(
    description = "The country or other region of the publishing organization's address."
  )
  private Country country;

  @Schema(
    description = "The postal code or similar line of the publishing organization's address."
  )
  private String postalCode;

  @Schema(
    description = "The latitude of the publishing organization."
  )
  private BigDecimal latitude;

  @Schema(
    description = "The longitude of the publishing organization."
  )
  private BigDecimal longitude;

  @Schema(
    description = "The number of datasets publishing by this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private int numPublishedDatasets;

  @Schema(
    description = "The GBIF username of the creator of the publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String createdBy;

  @Schema(
    description = "The GBIF username of the last user to modify the publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private String modifiedBy;

  @Schema(
    description = "Timestamp of when the publishing organization was created.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date created;

  @Schema(
    description = "Timestamp of when the publishing organization was modified.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date modified;

  @Schema(
    description = "If present, the publishing organization was deleted at this time. " +
      "It is possible for it to be restored in the future.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private Date deleted;

  @Schema(
    description = "The time when this publishing organization was endorsed by " +
      "the linked participant node."
  )
  private Date endorsed;

  @Schema(
    description = "A list of contacts associated with this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Contact> contacts = new ArrayList<>();

  @Schema(
    description = "A list of endpoints associated with this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Endpoint> endpoints = new ArrayList<>();

  @Schema(
    description = "A list of machine tags associated with this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(
    description = "A list of tags associated with this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Tag> tags = new ArrayList<>();

  @Schema(
    description = "A list of identifiers associated with this publishing organization.",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(
    description = "A list of comments associated with this publishing organization.",
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

  public Date getEndorsed() {
    return endorsed;
  }

  public void setEndorsed(Date endorsed) {
    this.endorsed = endorsed;
  }

  @NotNull
  public UUID getEndorsingNodeKey() {
    return endorsingNodeKey;
  }

  public void setEndorsingNodeKey(UUID endorsingNodeKey) {
    this.endorsingNodeKey = endorsingNodeKey;
  }

  public boolean isEndorsementApproved() {
    return endorsementApproved;
  }

  public void setEndorsementApproved(boolean endorsementApproved) {
    this.endorsementApproved = endorsementApproved;
  }

  public EndorsementStatus getEndorsementStatus() {
    return endorsementStatus;
  }

  public void setEndorsementStatus(EndorsementStatus endorsementStatus) {
    this.endorsementStatus = endorsementStatus;
  }

  /**
   * Get the organization password. This method is to be ignored on serialization, so that the password is not
   * revealed in the web service response.
   *
   * @return organization password
   */
  @JsonIgnore
  @Nullable
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Nullable
  @Size(min = 1, max = 30)
  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
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
  @Override
  public String getCity() {
    return city;
  }

  @Override
  public void setCity(String city) {
    this.city = city;
  }

  @Nullable
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
  @Override
  public String getPostalCode() {
    return postalCode;
  }

  @Override
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Nullable
  @Override
  @JsonIgnore
  public String getOrganization() {
    return getTitle();
  }

  @Override
  public void setOrganization(String organization) {
    setTitle(organization);
  }

  @Nullable
  @Min(-90)
  @Max(90)
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  @Nullable
  @Min(-180)
  @Max(180)
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  @Min(0)
  public int getNumPublishedDatasets() {
    return numPublishedDatasets;
  }

  public void setNumPublishedDatasets(int numPublishedDatasets) {
    this.numPublishedDatasets = numPublishedDatasets;
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
    Organization that = (Organization) o;
    return endorsementApproved == that.endorsementApproved
        && endorsementStatus == that.endorsementStatus
        && numPublishedDatasets == that.numPublishedDatasets
        && Objects.equals(key, that.key)
        && Objects.equals(endorsingNodeKey, that.endorsingNodeKey)
        && Objects.equals(password, that.password)
        && Objects.equals(title, that.title)
        && Objects.equals(abbreviation, that.abbreviation)
        && Objects.equals(description, that.description)
        && language == that.language
        && Objects.equals(email, that.email)
        && Objects.equals(phone, that.phone)
        && Objects.equals(homepage, that.homepage)
        && Objects.equals(logoUrl, that.logoUrl)
        && Objects.equals(address, that.address)
        && Objects.equals(city, that.city)
        && Objects.equals(province, that.province)
        && country == that.country
        && Objects.equals(postalCode, that.postalCode)
        && Objects.equals(latitude, that.latitude)
        && Objects.equals(longitude, that.longitude)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(modifiedBy, that.modifiedBy)
        && Objects.equals(created, that.created)
        && Objects.equals(modified, that.modified)
        && Objects.equals(deleted, that.deleted)
        && Objects.equals(endorsed, that.endorsed)
        && Objects.equals(contacts, that.contacts)
        && Objects.equals(endpoints, that.endpoints)
        && Objects.equals(machineTags, that.machineTags)
        && Objects.equals(tags, that.tags)
        && Objects.equals(identifiers, that.identifiers)
        && Objects.equals(comments, that.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        endorsingNodeKey,
        endorsementApproved,
        endorsementStatus,
        password,
        title,
        abbreviation,
        description,
        language,
        email,
        phone,
        homepage,
        logoUrl,
        address,
        city,
        province,
        country,
        postalCode,
        latitude,
        longitude,
        numPublishedDatasets,
        createdBy,
        modifiedBy,
        created,
        modified,
        deleted,
        endorsed,
        contacts,
        endpoints,
        machineTags,
        tags,
        identifiers,
        comments);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("endorsingNodeKey=" + endorsingNodeKey)
        .add("endorsementApproved=" + endorsementApproved)
        .add("endorsementStatus=" + endorsementStatus)
        .add("password='" + password + "'")
        .add("title='" + title + "'")
        .add("abbreviation='" + abbreviation + "'")
        .add("description='" + description + "'")
        .add("language=" + language)
        .add("email=" + email)
        .add("phone=" + phone)
        .add("homepage=" + homepage)
        .add("logoUrl=" + logoUrl)
        .add("address=" + address)
        .add("city='" + city + "'")
        .add("province='" + province + "'")
        .add("country=" + country)
        .add("postalCode='" + postalCode + "'")
        .add("latitude=" + latitude)
        .add("longitude=" + longitude)
        .add("numPublishedDatasets=" + numPublishedDatasets)
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .add("deleted=" + deleted)
        .add("endorsed=" + endorsed)
        .add("contacts=" + contacts)
        .add("endpoints=" + endpoints)
        .add("machineTags=" + machineTags)
        .add("tags=" + tags)
        .add("identifiers=" + identifiers)
        .add("comments=" + comments)
        .toString();
  }

  /**
   * A lenient equality check for business logic uniqueness. Does not include server side controlled fields such as key
   * and createdBy, nested objects and does not include password for security reasons.
   */
  @Override
  public boolean lenientEquals(Organization other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.endorsingNodeKey, other.endorsingNodeKey)
        && Objects.equals(this.endorsementApproved, other.endorsementApproved)
        && Objects.equals(this.endorsementStatus, other.endorsementStatus)
        && Objects.equals(this.title, other.title)
        && Objects.equals(this.abbreviation, other.abbreviation)
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
        && Objects.equals(this.postalCode, other.postalCode)
        && Objects.equals(this.latitude, other.latitude)
        && Objects.equals(this.longitude, other.longitude)
        && Objects.equals(this.deleted, other.deleted)
        && Objects.equals(this.endorsed, other.endorsed);
  }
}
