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

import org.gbif.api.model.registry.Comment;
import org.gbif.api.model.registry.Identifier;
import org.gbif.api.model.registry.LenientEquals;
import org.gbif.api.model.registry.MachineTag;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.util.HttpURI;
import org.gbif.api.util.LenientEqualsUtils;
import org.gbif.api.util.validators.email.ValidEmail;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.collections.InstitutionGovernance;
import org.gbif.api.vocabulary.collections.InstitutionType;
import org.gbif.api.vocabulary.collections.MasterSourceType;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The owner or location of collection. Usually an established organization or foundation,
 * especially one dedicated to education, public service, or culture.
 */
@SuppressWarnings("unused")
@ToString
@EqualsAndHashCode
public class Institution implements CollectionEntity, LenientEquals<Institution> {

  @Schema(
      description = "Unique GBIF key for the institution.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private UUID key;

  @Schema(
      description =
          "Code used to identify the institution.\n\n" + "*(NB Not required for updates.)*")
  @Sourceable(masterSources = MasterSourceType.IH)
  private String code;

  @Schema(
      description = "Name or title of the institution.\n\n" + "*(NB Not required for updates.)*")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private String name;

  @Schema(description = "Description of the institution.")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY)
  private String description;

  @Schema(description = "Types of the institution, describing its main activities.")
  private List<InstitutionType> types = new ArrayList<>();

  @Schema(description = "Whether the institution is active or operational.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private boolean active;

  @Schema(description = "Email addresses associated with the institution.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private List<@ValidEmail String> email = new ArrayList<>();

  @Schema(description = "Telephone numbers associated with the instutiton.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private List<String> phone = new ArrayList<>();

  @Schema(description = "The institution's WWW homepage.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private URI homepage;

  @Schema(description = "URLs for the main interactive catalogues of the institution.")
  private List<@HttpURI URI> catalogUrls = new ArrayList<>();

  @Schema(description = "URLs for machine-readable APIs for the institution catalogues.")
  private List<@HttpURI URI> apiUrls = new ArrayList<>();

  @Schema(
      description =
          "The mechanisms, processes and relations by which an "
              + "institution is controlled and directed.")
  private List<InstitutionGovernance> institutionalGovernances = new ArrayList<>();

  @Schema(
      description =
          "The academic or research disciplines to which an " + "institution is dedicated.")
  private List<String> disciplines = new ArrayList<>();

  @Schema(description = "The latitude of the institution.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private BigDecimal latitude;

  @Schema(description = "The longitude of the institution.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private BigDecimal longitude;

  @Schema(description = "The postal address of the institution.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private Address mailingAddress;

  @Schema(description = "The address of the location of the institution.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private Address address;

  @Schema(description = "Additional names by which the institution is known.")
  private List<String> additionalNames = new ArrayList<>();

  @Schema(description = "The date the institution was founded or established.")
  @Sourceable(masterSources = MasterSourceType.IH)
  private Integer foundingDate;

  @Schema(description = "An estimate of the number of specimens hosted by the institution.")
  private Integer numberSpecimens;

  @Schema(description = "A URL to a logo for the institution.")
  @Sourceable(masterSources = MasterSourceType.GBIF_REGISTRY)
  private URI logoUrl;

  @Schema(
      description =
          "The GBIF username of the creator of the institution entity in " + "the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private String createdBy;

  @Schema(
      description =
          "The GBIF username of the last user to modify the institution "
              + "entity in the GBIF registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private String modifiedBy;

  @Schema(
      description =
          "Timestamp of when the institution entity was created in the GBIF " + "registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date created;

  @Schema(
      description =
          "Timestamp of when the institution entity was modified in the GBIF " + "registry.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date modified;

  @Schema(
      description =
          "If present, the institution was deleted at this time. "
              + "It is possible for it to be restored in the future.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Date deleted;

  @Schema(
      description = "A list of tags associated with this institution.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<Tag> tags = new ArrayList<>();

  @Schema(
      description = "A list of identifiers associated with this institution.",
      accessMode = Schema.AccessMode.READ_ONLY)
  @Sourceable(masterSources = MasterSourceType.IH, sourceableParts = "IH_IRN")
  private List<Identifier> identifiers = new ArrayList<>();

  @Schema(description = "A list of contact people for this institution.")
  @Sourceable(masterSources = {MasterSourceType.GBIF_REGISTRY, MasterSourceType.IH})
  private List<Contact> contactPersons = new ArrayList<>();

  @Schema(
      description = "A list of machine tags associated with this institution.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<MachineTag> machineTags = new ArrayList<>();

  @Schema(description = "Alternative codes for this institution.")
  private List<AlternativeCode> alternativeCodes = new ArrayList<>();

  @Schema(
      description = "A list of comments associated with this institution.",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<Comment> comments = new ArrayList<>();

  @Schema(
      description = "Mapping of a GRSciColl institution to occurrence records",
      accessMode = Schema.AccessMode.READ_ONLY)
  private List<OccurrenceMapping> occurrenceMappings = new ArrayList<>();

  @Schema(description = "A collection record that replaces this collection.")
  private UUID replacedBy;

  @Schema(
      description =
          "Indicates if the institution was converted to a collection and specifies the UUID key of that collection")
  private UUID convertedToCollection;

  @Schema(description = "The primary source of this institution record.")
  private MasterSourceType masterSource;

  @Schema(
      description =
          "Information to assist the synchronization of the master source "
              + "record with the record in the GBIF registry.")
  private MasterSourceMetadata masterSourceMetadata;

  @Schema(description = "Whether the institution is shown on the NHC portal.")
  private Boolean displayOnNHCPortal;

  @Schema(description = "An estimate of the number of occurrences linked to the institution.")
  private Integer occurrenceCount;

  @Schema(description = "An estimate of the number of type specimens linked to the institution.")
  private Integer typeSpecimenCount;

  @Schema(
      description =
          "URI to the image to be featured on the institution page, this image should be associated with a license.")
  private URI featuredImageUrl;

  @Schema(
      description = "The license associated with the image to be featured on the institution page.")
  private License featuredImageLicense;

  /** GBIF unique identifier. */
  @Override
  public UUID getKey() {
    return key;
  }

  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  /** Code used to identify the collection. */
  @NotNull(groups = PrePersist.class)
  @Override
  public String getCode() {
    return code;
  }

  @Override
  public void setCode(String code) {
    this.code = code;
  }

  /** Name or title of an institution. */
  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  /** Textual description of institution. */
  @Nullable
  @Size(min = 1)
  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  /** Describes the main activities of an institution. */
  public List<InstitutionType> getTypes() {
    return types;
  }

  public void setTypes(List<InstitutionType> types) {
    this.types = types;
  }

  /** Is the institution active/operational?. */
  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public void setActive(boolean active) {
    this.active = active;
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

  /** URL to the home page of an institution. */
  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  /** URL to the main catalogue of an institution. */
  @Nullable
  public List<URI> getCatalogUrls() {
    return catalogUrls;
  }

  public void setCatalogUrls(List<URI> catalogUrls) {
    this.catalogUrls = catalogUrls;
  }

  /** Machine-consumable endpoint of an institution and probably its collections. */
  @Nullable
  public List<URI> getApiUrls() {
    return apiUrls;
  }

  public void setApiUrls(List<URI> apiUrls) {
    this.apiUrls = apiUrls;
  }

  /** Governance nature of an institution. */
  public List<InstitutionGovernance> getInstitutionalGovernances() {
    return institutionalGovernances;
  }

  public void setInstitutionalGovernances(List<InstitutionGovernance> institutionalGovernances) {
    this.institutionalGovernances = institutionalGovernances;
  }

  /** Activities to which an institution is dedicated. */
  public List<String> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(List<String> disciplines) {
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
  public Integer getFoundingDate() {
    return foundingDate;
  }

  public void setFoundingDate(Integer foundingDate) {
    this.foundingDate = foundingDate;
  }

  /** Estimated number of specimens hosted by an institution. */
  public Integer getNumberSpecimens() {
    return numberSpecimens;
  }

  public void setNumberSpecimens(Integer numberSpecimens) {
    this.numberSpecimens = numberSpecimens;
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

  @Valid
  @Override
  public List<Contact> getContactPersons() {
    return contactPersons;
  }

  @Override
  public void setContactPersons(List<Contact> contactPersons) {
    this.contactPersons = contactPersons;
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
  @Override
  public List<AlternativeCode> getAlternativeCodes() {
    return alternativeCodes;
  }

  @Override
  public void setAlternativeCodes(List<AlternativeCode> alternativeCodes) {
    this.alternativeCodes = alternativeCodes;
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
  public List<OccurrenceMapping> getOccurrenceMappings() {
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

  public UUID getConvertedToCollection() {
    return convertedToCollection;
  }

  public void setConvertedToCollection(UUID convertedToCollection) {
    this.convertedToCollection = convertedToCollection;
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

  @Override
  public Boolean getDisplayOnNHCPortal() {
    return displayOnNHCPortal;
  }

  @Override
  public void setDisplayOnNHCPortal(Boolean displayOnNHCPortal) {
    this.displayOnNHCPortal = displayOnNHCPortal;
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

  @HttpURI
  @Nullable
  @Override
  public URI getFeaturedImageUrl() {
    return featuredImageUrl;
  }

  @Override
  public void setFeaturedImageUrl(URI featuredImageUrl) {
    this.featuredImageUrl = featuredImageUrl;
  }

  @Nullable
  @Override
  public License getFeaturedImageLicense() {
    return featuredImageLicense;
  }

  @Override
  public void setFeaturedImageLicense(License featuredImageLicense) {
    this.featuredImageLicense = featuredImageLicense;
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

  @Override
  public boolean lenientEquals(Institution other) {
    if (this == other) {
      return true;
    }
    return active == other.active
        && Objects.equals(key, other.key)
        && Objects.equals(code, other.code)
        && Objects.equals(name, other.name)
        && Objects.equals(description, other.description)
        && Objects.equals(types, other.types)
        && Objects.equals(email, other.email)
        && Objects.equals(phone, other.phone)
        && Objects.equals(homepage, other.homepage)
        && Objects.equals(catalogUrls, other.catalogUrls)
        && Objects.equals(apiUrls, other.apiUrls)
        && Objects.equals(institutionalGovernances, other.institutionalGovernances)
        && Objects.equals(disciplines, other.disciplines)
        && Objects.equals(latitude, other.latitude)
        && Objects.equals(longitude, other.longitude)
        && LenientEqualsUtils.lenientEquals(mailingAddress, other.mailingAddress)
        && LenientEqualsUtils.lenientEquals(address, other.address)
        && Objects.equals(additionalNames, other.additionalNames)
        && Objects.equals(foundingDate, other.foundingDate)
        && Objects.equals(numberSpecimens, other.numberSpecimens)
        && Objects.equals(logoUrl, other.logoUrl)
        && Objects.equals(deleted, other.deleted)
        && Objects.equals(alternativeCodes, other.alternativeCodes)
        && Objects.equals(comments, other.comments)
        && Objects.equals(occurrenceMappings, other.occurrenceMappings)
        && Objects.equals(replacedBy, other.replacedBy)
        && Objects.equals(convertedToCollection, other.convertedToCollection)
        && Objects.equals(masterSource, other.masterSource)
        && Objects.equals(masterSourceMetadata, other.masterSourceMetadata)
        && Objects.equals(displayOnNHCPortal, other.displayOnNHCPortal)
        && Objects.equals(featuredImageUrl, other.featuredImageUrl)
        && Objects.equals(featuredImageLicense, other.featuredImageLicense);
  }
}
