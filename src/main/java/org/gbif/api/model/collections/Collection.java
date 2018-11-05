package org.gbif.api.model.collections;

import org.gbif.api.model.collections.vocabulary.AccessionStatus;
import org.gbif.api.model.collections.vocabulary.CollectionContentType;
import org.gbif.api.model.collections.vocabulary.PreservationType;
import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.Identifiable;
import org.gbif.api.model.registry.Identifier;
import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.model.registry.Taggable;
import org.gbif.api.util.HttpURI;

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
 * A group of specimens or other natural history objects.
 * Types of collections can be: specimens, original artwork, archives, observations, library materials,
 * datasets, photographs or mixed collections such as those that result from expeditions and voyages of discovery.
 */
public class Collection implements CollectionEntity, Contactable, Taggable, Identifiable, LenientEquals<Collection> {

  private UUID key;
  private String code;
  private String name;
  private String description;
  private List<CollectionContentType> contentType;
  private boolean active;
  private boolean personalCollection;
  private DOI doi;
  private URI homepage;
  private URI catalogUrl;
  private URI apiUrl;
  private List<PreservationType> preservationTypes;
  private AccessionStatus accessionStatus;
  private UUID institutionKey;
  private Address mailingAddress;
  private Address address;
  private String createdBy;
  private String modifiedBy;
  private Date created;
  private Date modified;
  private Date deleted;
  private List<Tag> tags = new ArrayList<>();
  private List<Identifier> identifiers = new ArrayList<>();
  private List<Staff> contacts;

  /**
   * List of alternative identifiers: UUIDs, external system identifiers, LSIDs, etc..
   */
  @Override
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  @Override
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * (Meta)Tags or labels.
   */
  @Override
  public List<Tag> getTags() {
    return tags;
  }

  @Override
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  /**
   * GBIF Unique identifier of this collection.
   */
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  /**
   * Collection code: identifies a collection at the owner's location.
   */
  @NotNull
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Descriptive name of a collection.
   */
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Textual description/summary of the contents of a collection.
   */
  @Size(min = 5)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Content type of the elements found in a collection.
   */
  public List<CollectionContentType> getContentType() {
    return contentType;
  }

  public void setContentType(List<CollectionContentType> contentType) {
    this.contentType = contentType;
  }

  /**
   * Is this collection currently active/maintained.
   */
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Does this collections belong to an individual?.
   */
  public boolean isPersonalCollection() {
    return personalCollection;
  }

  public void setPersonalCollection(boolean personalCollection) {
    this.personalCollection = personalCollection;
  }

  /**
   * Digital Object Identifier assigned to this collection.
   */
  public DOI getDoi() {
    return doi;
  }

  public void setDoi(DOI doi) {
    this.doi = doi;
  }

  /**
   * URL containing information about a collection.
   */
  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  /**
   * URI that exposes data about the catalog associated to a collection.
   */
  @HttpURI
  @Nullable
  public URI getCatalogUrl() {
    return catalogUrl;
  }

  public void setCatalogUrl(URI catalogUrl) {
    this.catalogUrl = catalogUrl;
  }

  /**
   * Machine consumable endpoint of information about a collection.
   */
  @HttpURI
  @Nullable
  public URI getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(URI apiUrl) {
    this.apiUrl = apiUrl;
  }

  /**
   * Types of preservation mechanisms used  for this collections.
   */
  public List<PreservationType> getPreservationTypes() {
    return preservationTypes;
  }

  public void setPreservationTypes(List<PreservationType> preservationTypes) {
    this.preservationTypes = preservationTypes;
  }

  /**
   * Defines how a collection as been added or joined.
   */
  public AccessionStatus getAccessionStatus() {
    return accessionStatus;
  }

  public void setAccessionStatus(AccessionStatus accessionStatus) {
    this.accessionStatus = accessionStatus;
  }

  /**
   * Institution that owns or hosts this collection.
   */
  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  /**
   * Address used to send/receive physical mail.
   */
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

  /**
   * Directions of where this collections is situated.
   */
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
  public List<Staff> getContacts() {
    return contacts;
  }

  @Override
  public void setContacts(List<Staff> contacts) {
    this.contacts = contacts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Collection that = (Collection) o;
    return active == that.active
           && personalCollection == that.personalCollection
           && Objects.equals(key, that.key)
           && Objects.equals(code, that.code)
           && Objects.equals(name, that.name)
           && Objects.equals(description, that.description)
           && Objects.equals(contentType, that.contentType)
           && Objects.equals(doi, that.doi)
           && Objects.equals(homepage, that.homepage)
           && Objects.equals(catalogUrl, that.catalogUrl)
           && Objects.equals(apiUrl, that.apiUrl)
           && Objects.equals(preservationTypes, that.preservationTypes)
           && accessionStatus == that.accessionStatus
           && Objects.equals(institutionKey, that.institutionKey)
           && Objects.equals(mailingAddress, that.mailingAddress)
           && Objects.equals(address, that.address)
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
                        contentType,
                        active,
                        personalCollection,
                        doi,
                        homepage,
                        catalogUrl,
                        apiUrl,
                        preservationTypes,
                        accessionStatus,
                        institutionKey,
                        mailingAddress,
                        address,
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
    return new StringJoiner(", ", Collection.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("code='" + code + "'")
      .add("name='" + name + "'")
      .add("description='" + description + "'")
      .add("contentType=" + contentType)
      .add("active=" + active)
      .add("personalCollection=" + personalCollection)
      .add("doi=" + doi)
      .add("homepage=" + homepage)
      .add("catalogUrl=" + catalogUrl)
      .add("apiUrl=" + apiUrl)
      .add("preservationTypes=" + preservationTypes)
      .add("accessionStatus=" + accessionStatus)
      .add("institutionKey=" + institutionKey)
      .add("mailingAddress=" + mailingAddress)
      .add("address=" + address)
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
  public boolean lenientEquals(Collection other) {
    if (this == other) {
      return true;
    }
    return active == other.active
           && personalCollection == other.personalCollection
           && Objects.equals(key, other.key)
           && Objects.equals(code, other.code)
           && Objects.equals(name, other.name)
           && Objects.equals(description, other.description)
           && Objects.equals(contentType, other.contentType)
           && Objects.equals(doi, other.doi)
           && Objects.equals(homepage, other.homepage)
           && Objects.equals(catalogUrl, other.catalogUrl)
           && Objects.equals(apiUrl, other.apiUrl)
           && Objects.equals(preservationTypes, other.preservationTypes)
           && accessionStatus == other.accessionStatus
           && Objects.equals(institutionKey, other.institutionKey)
           && Objects.equals(mailingAddress, other.mailingAddress)
           && Objects.equals(address, other.address)
           && Objects.equals(deleted, other.deleted);
  }
}
