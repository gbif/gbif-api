package org.gbif.api.model.collections;

import org.gbif.api.model.collections.vocabulary.Discipline;
import org.gbif.api.model.collections.vocabulary.InstitutionGovernance;
import org.gbif.api.model.collections.vocabulary.InstitutionType;
import org.gbif.api.model.registry.Identifiable;
import org.gbif.api.model.registry.Identifier;
import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.model.registry.Taggable;
import org.gbif.api.util.HttpURI;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Institution implements Taggable, Identifiable, LenientEquals<Institution> {

  private UUID key;
  private String code;
  private String name;
  private String description;
  private InstitutionType type;
  private boolean active;
  private URI homepage;
  private URI catalogUrl;
  private URI apiUrl;
  private InstitutionGovernance institutionalGovernance;
  private List<Discipline> disciplines;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Address mailingAddress;
  private Address address;
  private List<String> additionalNames;
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
  private List<Tag> tags;
  private List<Identifier> identifiers;
  private List<Staff> contacts;

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  @NotNull
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Size(min = 10)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public InstitutionType getType() {
    return type;
  }

  public void setType(InstitutionType type) {
    this.type = type;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  @HttpURI
  @Nullable
  public URI getCatalogUrl() {
    return catalogUrl;
  }

  public void setCatalogUrl(URI catalogUrl) {
    this.catalogUrl = catalogUrl;
  }

  @HttpURI
  @Nullable
  public URI getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(URI apiUrl) {
    this.apiUrl = apiUrl;
  }

  public InstitutionGovernance getInstitutionalGovernance() {
    return institutionalGovernance;
  }

  public void setInstitutionalGovernance(InstitutionGovernance institutionalGovernance) {
    this.institutionalGovernance = institutionalGovernance;
  }

  public List<Discipline> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(List<Discipline> disciplines) {
    this.disciplines = disciplines;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  @Valid
  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  @Valid
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<String> getAdditionalNames() {
    return additionalNames;
  }

  public void setAdditionalNames(List<String> additionalNames) {
    this.additionalNames = additionalNames;
  }

  public Date getFoundingDate() {
    return foundingDate;
  }

  public void setFoundingDate(Date foundingDate) {
    this.foundingDate = foundingDate;
  }

  public String getGeographicDescription() {
    return geographicDescription;
  }

  public void setGeographicDescription(String geographicDescription) {
    this.geographicDescription = geographicDescription;
  }

  public String getTaxonomicDescription() {
    return taxonomicDescription;
  }

  public void setTaxonomicDescription(String taxonomicDescription) {
    this.taxonomicDescription = taxonomicDescription;
  }

  public int getNumberSpecimens() {
    return numberSpecimens;
  }

  public void setNumberSpecimens(int numberSpecimens) {
    this.numberSpecimens = numberSpecimens;
  }

  public boolean isIndexHerbariorumRecord() {
    return indexHerbariorumRecord;
  }

  public void setIndexHerbariorumRecord(boolean indexHerbariorumRecord) {
    this.indexHerbariorumRecord = indexHerbariorumRecord;
  }

  @HttpURI
  @Nullable
  public URI getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(URI logoUrl) {
    this.logoUrl = logoUrl;
  }

  public String getCitesPermitNumber() {
    return citesPermitNumber;
  }

  public void setCitesPermitNumber(String citesPermitNumber) {
    this.citesPermitNumber = citesPermitNumber;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public Date getDeleted() {
    return deleted;
  }

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

  public List<Staff> getContacts() {
    return contacts;
  }

  public void setContacts(List<Staff> contacts) {
    this.contacts = contacts;
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
           && Objects.equals(contacts, that.contacts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key,
                        code,
                        name,
                        description,
                        type,
                        active,
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
                        contacts);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Institution.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("code='" + code + "'")
      .add("name='" + name + "'")
      .add("description='" + description + "'")
      .add("type=" + type)
      .add("active=" + active)
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
           && Objects.equals(deleted, other.deleted);
  }
}
