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
package org.gbif.api.model.collections;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.Comment;
import org.gbif.api.model.registry.Identifier;
import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.util.HttpURI;
import org.gbif.api.util.LenientEqualsUtils;
import org.gbif.api.util.validators.email.ValidEmail;
import org.gbif.api.vocabulary.collections.AccessionStatus;
import org.gbif.api.vocabulary.collections.CollectionContentType;
import org.gbif.api.vocabulary.collections.MasterSourceType;
import org.gbif.api.vocabulary.collections.PreservationType;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A group of specimens or other natural history objects. Types of collections can be: specimens,
 * original artwork, archives, observations, library materials, datasets, photographs or mixed
 * collections such as those that result from expeditions and voyages of discovery.
 */
@SuppressWarnings("unused")
@ToString
@EqualsAndHashCode
public class Collection implements CollectionEntity, LenientEquals<Collection> {

  @Schema(
      description = "Unique GBIF key for the collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private UUID key;

  @Schema(
      description =
          "Code of the collection — identifies a collection at the "
              + "owner's location.\n\n"
              + "*(NB Not required for updates.)*")
  @Sourceable(masterSources = MasterSourceType.IH)
  private String code;

  @Schema(
      description = "Descriptive name of the collection.\n\n" + "*(NB Not required for updates.)*")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY)
  @Sourceable(masterSources = MasterSourceType.IH, overridable = true)
  private String name;

  @Schema(description = "Description or summary of the contents of the collection.")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY)
  private String description;

  @Schema(description = "Content type of the elements found in the collection.")
  private List<CollectionContentType> contentTypes = new ArrayList<>();

  @Schema(description = "Whether the collection is active/maintained.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private boolean active;

  @Schema(description = "Whether this collection belongs to an individual.")
  private boolean personalCollection;

  @Schema(
      description = "A Digital Object Identifier for the collection.",
      implementation = String.class,
      pattern = "(10(?:\\.[0-9]+)+)" + "/(.+)")
  private DOI doi;

  @Schema(description = "Email addresses associated with the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private List<@ValidEmail String> email = new ArrayList<>();

  @Schema(description = "Telephone numbers associated with the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private List<String> phone = new ArrayList<>();

  @Schema(description = "The collection's WWW homepage.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private URI homepage;

  @Schema(description = "A URL for an interactive catalogue of the collection.")
  private URI catalogUrl;

  @Schema(description = "A URL for a machine-readable API for the collection catalogue.")
  private URI apiUrl;

  @Schema(description = "The preservation mechanisms used for this collection.")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY)
  private List<PreservationType> preservationTypes = new ArrayList<>();

  @Schema(description = "How the collection was added or joined.")
  private AccessionStatus accessionStatus;

  @Schema(description = "The key of the institution owning or hosting the collection.")
  private UUID institutionKey;

  @Schema(description = "The postal address of the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private Address mailingAddress;

  @Schema(description = "The address of the location of the collection.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private Address address;

  @Schema(
      description =
          "The GBIF username of the creator of the collection entity in " + "the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private String createdBy;

  @Schema(
      description =
          "The GBIF username of the last user to modify the collection "
              + "entity in the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private String modifiedBy;

  @Schema(
      description =
          "Timestamp of when the collection entity was created in the GBIF " + "registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date created;

  @Schema(
      description =
          "Timestamp of when the collection entity was modified in the GBIF " + "registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date modified;

  @Schema(
      description =
          "If present, the collection was deleted at this time. "
              + "It is possible for it to be restored in the future.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date deleted;

  @Schema(
      description = "A list of tags associated with this collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<Tag> tags = new ArrayList<>();

  @Schema(
      description = "A list of identifiers associated with this collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  @Sourceable(masterSources = MasterSourceType.IH, sourceableParts = "IH_IRN")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY, sourceableParts = "DOI")
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(
      description = "A list of contact people for this collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private List<Contact> contactPersons = new ArrayList<>();

  @Schema(description = "Whether there is a record for this collection in *Index Herbariorum*.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private boolean indexHerbariorumRecord;

  @Schema(description = "The number of specimens contained in this collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private Integer numberSpecimens;

  @Schema(
      description = "A list of machine tags associated with this collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(description = "The taxonomic coverage of this collection.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private String taxonomicCoverage;

  @Schema(description = "The geographic scope of this collection.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private String geography;

  @Schema(description = "Notes on the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private String notes;

  @Schema(description = "Other collections incorporated into this collection.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private List<String> incorporatedCollections = new ArrayList<>();

  @Schema(description = "Important collectors who have specimens in this collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private List<String> importantCollectors = new ArrayList<>();

  @Schema(
      description = "A summary of the collection." // TODO, a summary of what?
      )
  @Sourceable(masterSources = MasterSourceType.IH)
  private Map<String, Integer> collectionSummary = new HashMap<>();

  @Schema(description = "Alternative codes for this collection.")
  private List<AlternativeCode> alternativeCodes = new ArrayList<>();

  @Schema(
      description = "A list of comments associated with this collection.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<Comment> comments = new ArrayList<>();

  @Schema(
      description = "Mapping of a GRSciColl collection to occurrence records",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<OccurrenceMapping> occurrenceMappings = new ArrayList<>();

  @Schema(description = "A collection record that replaces this collection.")
  private UUID replacedBy;

  @Schema(description = "The primary source of this collection record.")
  private MasterSourceType masterSource;

  @Schema(
      description =
          "Information to assist the synchronization of the master source "
              + "record with the record in the GBIF registry.")
  private MasterSourceMetadata masterSourceMetadata;

  @Schema(description = "An organizational department managing the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private String department;

  @Schema(description = "An organizational division managing the collection.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private String division;

  @Schema(description = "Whether the collection is shown on the NHC portal.")
  private Boolean displayOnNHCPortal;

  @Schema(description = "An estimate of the number of occurrences linked to the institution.")
  private Integer occurrenceCount;

  @Schema(description = "An estimate of the number of type specimens linked to the institution.")
  private Integer typeSpecimenCount;

  /** List of alternative identifiers: UUIDs, external system identifiers, LSIDs, etc.. */
  @Override
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  @Override
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /** (Meta)Tags or labels. */
  @Valid
  @Override
  public List<Tag> getTags() {
    return tags;
  }

  @Override
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  /** GBIF Unique identifier of this collection. */
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  /** Collection code: identifies a collection at the owner's location. */
  @NotNull(groups = PrePersist.class)
  @Override
  public String getCode() {
    return code;
  }

  @Override
  public void setCode(String code) {
    this.code = code;
  }

  /** Descriptive name of a collection. */
  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  /** Textual description/summary of the contents of a collection. */
  @Size(min = 1)
  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  /** Content type of the elements found in a collection. */
  public List<CollectionContentType> getContentTypes() {
    return contentTypes;
  }

  public void setContentTypes(List<CollectionContentType> contentTypes) {
    this.contentTypes = contentTypes;
  }

  /** Is this collection currently active/maintained. */
  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public void setActive(boolean active) {
    this.active = active;
  }

  /** Does this collections belong to an individual?. */
  public boolean isPersonalCollection() {
    return personalCollection;
  }

  public void setPersonalCollection(boolean personalCollection) {
    this.personalCollection = personalCollection;
  }

  /** Digital Object Identifier assigned to this collection. */
  public DOI getDoi() {
    return doi;
  }

  public void setDoi(DOI doi) {
    this.doi = doi;
  }

  @Override
  public List<String> getEmail() {
    return email;
  }

  @Override
  public void setEmail(List<String> email) {
    this.email = email;
  }

  @Override
  public List<String> getPhone() {
    return phone;
  }

  @Override
  public void setPhone(List<String> phone) {
    this.phone = phone;
  }

  /** URL containing information about a collection. */
  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  /** URI that exposes data about the catalog associated to a collection. */
  @HttpURI
  @Nullable
  public URI getCatalogUrl() {
    return catalogUrl;
  }

  public void setCatalogUrl(URI catalogUrl) {
    this.catalogUrl = catalogUrl;
  }

  /** Machine consumable endpoint of information about a collection. */
  @HttpURI
  @Nullable
  public URI getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(URI apiUrl) {
    this.apiUrl = apiUrl;
  }

  /** Types of preservation mechanisms used for this collections. */
  public List<PreservationType> getPreservationTypes() {
    return preservationTypes;
  }

  public void setPreservationTypes(List<PreservationType> preservationTypes) {
    this.preservationTypes = preservationTypes;
  }

  /** Defines how a collection as been added or joined. */
  public AccessionStatus getAccessionStatus() {
    return accessionStatus;
  }

  public void setAccessionStatus(AccessionStatus accessionStatus) {
    this.accessionStatus = accessionStatus;
  }

  /** Institution that owns or hosts this collection. */
  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  /** Address used to send/receive physical mail. */
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

  /** Address where this collection is situated. */
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

  @Valid
  @Override
  public List<Contact> getContactPersons() {
    return contactPersons;
  }

  @Override
  public void setContactPersons(List<Contact> contactPersons) {
    this.contactPersons = contactPersons;
  }

  public boolean isIndexHerbariorumRecord() {
    return indexHerbariorumRecord;
  }

  public void setIndexHerbariorumRecord(boolean indexHerbariorumRecord) {
    this.indexHerbariorumRecord = indexHerbariorumRecord;
  }

  public Integer getNumberSpecimens() {
    return numberSpecimens;
  }

  public void setNumberSpecimens(Integer numberSpecimens) {
    this.numberSpecimens = numberSpecimens;
  }

  @Valid
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
    machineTags.add(machineTag);
  }

  public String getTaxonomicCoverage() {
    return taxonomicCoverage;
  }

  public void setTaxonomicCoverage(String taxonomicCoverage) {
    this.taxonomicCoverage = taxonomicCoverage;
  }

  public String getGeography() {
    return geography;
  }

  public void setGeography(String geography) {
    this.geography = geography;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<String> getIncorporatedCollections() {
    return incorporatedCollections;
  }

  public void setIncorporatedCollections(List<String> incorporatedCollections) {
    this.incorporatedCollections = incorporatedCollections;
  }

  public List<String> getImportantCollectors() {
    return importantCollectors;
  }

  public void setImportantCollectors(List<String> importantCollectors) {
    this.importantCollectors = importantCollectors;
  }

  public Map<String, Integer> getCollectionSummary() {
    return collectionSummary;
  }

  public void setCollectionSummary(Map<String, Integer> collectionSummary) {
    this.collectionSummary = collectionSummary;
  }

  /** Alternative codes for a collection. */
  @Override
  public List<AlternativeCode> getAlternativeCodes() {
    return alternativeCodes;
  }

  @Override
  public void setAlternativeCodes(List<AlternativeCode> alternativeCodes) {
    this.alternativeCodes = alternativeCodes;
  }

  @Override
  public @NotNull List<Comment> getComments() {
    return comments;
  }

  @Override
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public @NotNull List<OccurrenceMapping> getOccurrenceMappings() {
    return occurrenceMappings;
  }

  @Override
  public void setOccurrenceMappings(List<OccurrenceMapping> occurrenceMappings) {
    this.occurrenceMappings = occurrenceMappings;
  }

  @Override
  public UUID getReplacedBy() {
    return replacedBy;
  }

  @Override
  public void setReplacedBy(UUID replacedBy) {
    this.replacedBy = replacedBy;
  }

  @Override
  public MasterSourceType getMasterSource() {
    return masterSource;
  }

  @Override
  public void setMasterSource(MasterSourceType masterSource) {
    this.masterSource = masterSource;
  }

  @Override
  public MasterSourceMetadata getMasterSourceMetadata() {
    return masterSourceMetadata;
  }

  @Override
  public void setMasterSourceMetadata(MasterSourceMetadata masterSourceMetadata) {
    this.masterSourceMetadata = masterSourceMetadata;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  @Override
  public Boolean getDisplayOnNHCPortal() {
    return displayOnNHCPortal;
  }

  @Override
  public void setDisplayOnNHCPortal(Boolean displayOnNHCPortal) {
    this.displayOnNHCPortal = displayOnNHCPortal;
  }

  @Hidden
  @JsonIgnore
  public String getCountry() {
    if (address != null && address.getCountry() != null) {
      return address.getCountry().getIso2LetterCode();
    } else if (mailingAddress != null && mailingAddress.getCountry() != null) {
      return mailingAddress.getCountry().getIso2LetterCode();
    }
    return null;
  }

  @Hidden
  @JsonIgnore
  public String getCity() {
    if (address != null && address.getCity() != null) {
      return address.getCity();
    } else if (mailingAddress != null && mailingAddress.getCity() != null) {
      return mailingAddress.getCity();
    }
    return null;
  }

  @Hidden
  @JsonIgnore
  public String getProvince() {
    if (address != null && address.getProvince() != null) {
      return address.getProvince();
    } else if (mailingAddress != null && mailingAddress.getProvince() != null) {
      return mailingAddress.getProvince();
    }
    return null;
  }

  public Integer getOccurrenceCount() {
    return occurrenceCount;
  }

  public void setOccurrenceCount(Integer occurrenceCount) {
    this.occurrenceCount = occurrenceCount;
  }

  public Integer getTypeSpecimenCount() {
    return typeSpecimenCount;
  }

  public void setTypeSpecimenCount(Integer typeSpecimenCount) {
    this.typeSpecimenCount = typeSpecimenCount;
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
        && Objects.equals(contentTypes, other.contentTypes)
        && Objects.equals(doi, other.doi)
        && Objects.equals(email, other.email)
        && Objects.equals(phone, other.phone)
        && Objects.equals(homepage, other.homepage)
        && Objects.equals(catalogUrl, other.catalogUrl)
        && Objects.equals(apiUrl, other.apiUrl)
        && Objects.equals(preservationTypes, other.preservationTypes)
        && accessionStatus == other.accessionStatus
        && Objects.equals(institutionKey, other.institutionKey)
        && LenientEqualsUtils.lenientEquals(mailingAddress, other.mailingAddress)
        && LenientEqualsUtils.lenientEquals(address, other.address)
        && Objects.equals(deleted, other.deleted)
        && indexHerbariorumRecord == other.indexHerbariorumRecord
        && Objects.equals(numberSpecimens, other.numberSpecimens)
        && Objects.equals(taxonomicCoverage, other.taxonomicCoverage)
        && Objects.equals(geography, other.geography)
        && Objects.equals(notes, other.notes)
        && Objects.equals(incorporatedCollections, other.incorporatedCollections)
        && Objects.equals(importantCollectors, other.importantCollectors)
        && Objects.equals(collectionSummary, other.collectionSummary)
        && Objects.equals(alternativeCodes, other.alternativeCodes)
        && Objects.equals(comments, other.comments)
        && Objects.equals(occurrenceMappings, other.occurrenceMappings)
        && Objects.equals(replacedBy, other.replacedBy)
        && Objects.equals(masterSource, other.masterSource)
        && Objects.equals(masterSourceMetadata, other.masterSourceMetadata)
        && Objects.equals(division, other.division)
        && Objects.equals(department, other.department)
        && Objects.equals(displayOnNHCPortal, other.displayOnNHCPortal);
  }
}
