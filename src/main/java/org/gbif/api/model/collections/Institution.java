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
package org.gbif.api.model.collections;

import org.gbif.api.model.registry.Identifiable;
import org.gbif.api.model.registry.Identifier;
import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.model.registry.MachineTaggable;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.model.registry.Taggable;
import org.gbif.api.util.HttpURI;
import org.gbif.api.vocabulary.collections.Discipline;
import org.gbif.api.vocabulary.collections.InstitutionGovernance;
import org.gbif.api.vocabulary.collections.InstitutionType;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The owner or location of collection. Usually an established organization or foundation,
 * especially one dedicated to education, public service, or culture.
 */
@SuppressWarnings("unused")
public class Institution
    implements CollectionEntity,
        Contactable,
        Taggable,
        MachineTaggable,
        Identifiable,
        LenientEquals<Institution> {

  private UUID key;
  private String code;
  private String name;
  private String description;
  private InstitutionType type;
  private boolean active;
  private List<String> email = new ArrayList<>();
  private List<String> phone = new ArrayList<>();
  private URI homepage;
  private URI catalogUrl;
  private URI apiUrl;
  private InstitutionGovernance institutionalGovernance;
  private List<Discipline> disciplines = new ArrayList<>();
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Address mailingAddress;
  private Address address;
  private List<String> additionalNames = new ArrayList<>();
  private Date foundingDate;
  private String geographicDescription;
  private String taxonomicDescription;
  private int numberSpecimens;
  private boolean indexHerbariorumRecord;
  private URI logoUrl;
  private String citesPermitNumber;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;
  private List<Tag> tags = new ArrayList<>();
  private List<Identifier> identifiers = new ArrayList<>();
  private List<Person> contacts = new ArrayList<>();
  private List<MachineTag> machineTags = new ArrayList<>();
  private List<AlternativeCode> alternativeCodes = new ArrayList<>();

  /** GBIF unique identifier. */
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  /** Code used to identified the collection. */
  @NotNull
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /** Name or title of a institution. */
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /** Textual description of institution. */
  @Nullable
  @Size(min = 10)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /** Describes the main activity of a institution. */
  public InstitutionType getType() {
    return type;
  }

  public void setType(InstitutionType type) {
    this.type = type;
  }

  /** Is the institution active/operational?. */
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public List<String> getEmail() {
    return email;
  }

  public void setEmail(List<String> email) {
    this.email = email;
  }

  public List<String> getPhone() {
    return phone;
  }

  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  /** URL to the home page of a institution. */
  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  /** URL to the main catalog of a institution. */
  @HttpURI
  @Nullable
  public URI getCatalogUrl() {
    return catalogUrl;
  }

  public void setCatalogUrl(URI catalogUrl) {
    this.catalogUrl = catalogUrl;
  }

  /** Machine consumable endpoint of a institution and probably its collections. */
  @HttpURI
  @Nullable
  public URI getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(URI apiUrl) {
    this.apiUrl = apiUrl;
  }

  /** Governance nature of a institution. */
  public InstitutionGovernance getInstitutionalGovernance() {
    return institutionalGovernance;
  }

  public void setInstitutionalGovernance(InstitutionGovernance institutionalGovernance) {
    this.institutionalGovernance = institutionalGovernance;
  }

  /** Activities to which an institution is dedicated. */
  public List<Discipline> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(List<Discipline> disciplines) {
    this.disciplines = disciplines;
  }

  /** Decimal latitude of where this institution is located. */
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  /** Decimal longitude of where this institution is located. */
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  @Nullable
  @Valid
  @Override
  public Address getMailingAddress() {
    return mailingAddress;
  }

  @Override
  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  @Nullable
  @Valid
  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public void setAddress(Address address) {
    this.address = address;
  }

  /** Alternative names of institution. */
  public List<String> getAdditionalNames() {
    return additionalNames;
  }

  public void setAdditionalNames(List<String> additionalNames) {
    this.additionalNames = additionalNames;
  }

  /** Date when the institution was founded or established. */
  public Date getFoundingDate() {
    return foundingDate;
  }

  public void setFoundingDate(Date foundingDate) {
    this.foundingDate = foundingDate;
  }

  /** Geographical coverage of the activities performed by an institution. */
  public String getGeographicDescription() {
    return geographicDescription;
  }

  public void setGeographicDescription(String geographicDescription) {
    this.geographicDescription = geographicDescription;
  }

  /** Taxonomic description of the collections maintained by a institution. */
  public String getTaxonomicDescription() {
    return taxonomicDescription;
  }

  public void setTaxonomicDescription(String taxonomicDescription) {
    this.taxonomicDescription = taxonomicDescription;
  }

  /** Estimated number of specimens hosted by an institution. */
  public int getNumberSpecimens() {
    return numberSpecimens;
  }

  public void setNumberSpecimens(int numberSpecimens) {
    this.numberSpecimens = numberSpecimens;
  }

  /** Was the institution record imported form IndexHerbariorum. */
  public boolean isIndexHerbariorumRecord() {
    return indexHerbariorumRecord;
  }

  public void setIndexHerbariorumRecord(boolean indexHerbariorumRecord) {
    this.indexHerbariorumRecord = indexHerbariorumRecord;
  }

  /** Logo/Image that identifies the institution. */
  @HttpURI
  @Nullable
  public URI getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(URI logoUrl) {
    this.logoUrl = logoUrl;
  }

  /**
   * Cites (see http://ec.europa.eu/environment/cites/info_permits_en.htm) license given for this
   * collection.
   */
  public String getCitesPermitNumber() {
    return citesPermitNumber;
  }

  public void setCitesPermitNumber(String citesPermitNumber) {
    this.citesPermitNumber = citesPermitNumber;
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

  @Override
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  @Override
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
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
  public List<Person> getContacts() {
    return contacts;
  }

  @Override
  public void setContacts(List<Person> contacts) {
    this.contacts = contacts;
  }

  @Override
  public @NotNull List<MachineTag> getMachineTags() {
    return machineTags;
  }

  @Override
  public void setMachineTags(List<MachineTag> machineTags) {
    this.machineTags = machineTags;
  }

  @Override
  public void addMachineTag(MachineTag machineTag) {
    this.machineTags.add(machineTag);
  }

  /** Alternative codes for an institution. */
  public List<AlternativeCode> getAlternativeCodes() {
    return alternativeCodes;
  }

  public void setAlternativeCodes(List<AlternativeCode> alternativeCodes) {
    this.alternativeCodes = alternativeCodes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Institution that = (Institution) o;
    return active == that.active
        && numberSpecimens == that.numberSpecimens
        && indexHerbariorumRecord == that.indexHerbariorumRecord
        && Objects.equals(key, that.key)
        && Objects.equals(code, that.code)
        && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && type == that.type
        && Objects.equals(email, that.email)
        && Objects.equals(phone, that.phone)
        && Objects.equals(homepage, that.homepage)
        && Objects.equals(catalogUrl, that.catalogUrl)
        && Objects.equals(apiUrl, that.apiUrl)
        && institutionalGovernance == that.institutionalGovernance
        && Objects.equals(disciplines, that.disciplines)
        && Objects.equals(latitude, that.latitude)
        && Objects.equals(longitude, that.longitude)
        && Objects.equals(mailingAddress, that.mailingAddress)
        && Objects.equals(address, that.address)
        && Objects.equals(additionalNames, that.additionalNames)
        && Objects.equals(foundingDate, that.foundingDate)
        && Objects.equals(geographicDescription, that.geographicDescription)
        && Objects.equals(taxonomicDescription, that.taxonomicDescription)
        && Objects.equals(logoUrl, that.logoUrl)
        && Objects.equals(citesPermitNumber, that.citesPermitNumber)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(modifiedBy, that.modifiedBy)
        && Objects.equals(created, that.created)
        && Objects.equals(modified, that.modified)
        && Objects.equals(deleted, that.deleted)
        && Objects.equals(tags, that.tags)
        && Objects.equals(identifiers, that.identifiers)
        && Objects.equals(contacts, that.contacts)
        && Objects.equals(machineTags, that.machineTags)
        && Objects.equals(alternativeCodes, that.alternativeCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        code,
        name,
        description,
        type,
        active,
        email,
        phone,
        homepage,
        catalogUrl,
        apiUrl,
        institutionalGovernance,
        disciplines,
        latitude,
        longitude,
        mailingAddress,
        address,
        additionalNames,
        foundingDate,
        geographicDescription,
        taxonomicDescription,
        numberSpecimens,
        indexHerbariorumRecord,
        logoUrl,
        citesPermitNumber,
        createdBy,
        modifiedBy,
        created,
        modified,
        deleted,
        tags,
        identifiers,
        contacts,
        machineTags,
        alternativeCodes);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Institution.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("code='" + code + "'")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("type=" + type)
        .add("active=" + active)
        .add("email=" + email)
        .add("phone=" + phone)
        .add("homepage=" + homepage)
        .add("catalogUrl=" + catalogUrl)
        .add("apiUrl=" + apiUrl)
        .add("institutionalGovernance=" + institutionalGovernance)
        .add("disciplines=" + disciplines)
        .add("latitude=" + latitude)
        .add("longitude=" + longitude)
        .add("mailingAddress=" + mailingAddress)
        .add("address=" + address)
        .add("additionalNames=" + additionalNames)
        .add("foundingDate=" + foundingDate)
        .add("geographicDescription='" + geographicDescription + "'")
        .add("taxonomicDescription='" + taxonomicDescription + "'")
        .add("numberSpecimens=" + numberSpecimens)
        .add("indexHerbariorumRecord=" + indexHerbariorumRecord)
        .add("logoUrl=" + logoUrl)
        .add("citesPermitNumber='" + citesPermitNumber + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modifiedBy='" + modifiedBy + "'")
        .add("created=" + created)
        .add("modified=" + modified)
        .add("deleted=" + deleted)
        .add("tags=" + tags)
        .add("identifiers=" + identifiers)
        .add("contacts=" + contacts)
        .add("machineTags=" + machineTags)
        .add("alternativeCodes=" + alternativeCodes)
        .toString();
  }

  @Override
  public boolean lenientEquals(Institution other) {
    if (this == other) {
      return true;
    }
    return active == other.active
        && numberSpecimens == other.numberSpecimens
        && indexHerbariorumRecord == other.indexHerbariorumRecord
        && Objects.equals(key, other.key)
        && Objects.equals(code, other.code)
        && Objects.equals(name, other.name)
        && Objects.equals(description, other.description)
        && type == other.type
        && Objects.equals(email, other.email)
        && Objects.equals(phone, other.phone)
        && Objects.equals(homepage, other.homepage)
        && Objects.equals(catalogUrl, other.catalogUrl)
        && Objects.equals(apiUrl, other.apiUrl)
        && institutionalGovernance == other.institutionalGovernance
        && Objects.equals(disciplines, other.disciplines)
        && Objects.equals(latitude, other.latitude)
        && Objects.equals(longitude, other.longitude)
        && Objects.equals(mailingAddress, other.mailingAddress)
        && Objects.equals(address, other.address)
        && Objects.equals(additionalNames, other.additionalNames)
        && Objects.equals(foundingDate, other.foundingDate)
        && Objects.equals(geographicDescription, other.geographicDescription)
        && Objects.equals(taxonomicDescription, other.taxonomicDescription)
        && Objects.equals(logoUrl, other.logoUrl)
        && Objects.equals(citesPermitNumber, other.citesPermitNumber)
        && Objects.equals(deleted, other.deleted)
        && Objects.equals(alternativeCodes, other.alternativeCodes);
  }
}
