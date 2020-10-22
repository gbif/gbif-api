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

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.eml.Collection;
import org.gbif.api.model.registry.eml.DataDescription;
import org.gbif.api.model.registry.eml.KeywordCollection;
import org.gbif.api.model.registry.eml.Project;
import org.gbif.api.model.registry.eml.SamplingDescription;
import org.gbif.api.model.registry.eml.TaxonomicCoverages;
import org.gbif.api.model.registry.eml.curatorial.CuratorialUnitComposite;
import org.gbif.api.model.registry.eml.geospatial.GeospatialCoverage;
import org.gbif.api.model.registry.eml.temporal.TemporalCoverage;
import org.gbif.api.util.HttpURI;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;
import org.gbif.api.vocabulary.Language;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MaintenanceUpdateFrequency;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A GBIF dataset which provides occurrence data, checklist data, sampling event data or metadata.
 * This Dataset class is covering all of the GBIF metadata profile v1.1, but only few properties are kept in the
 * database table:
 * <ul>
 * <li>key</li>
 * <li>parentDatasetKey</li>
 * <li>duplicateOfDatasetKey</li>
 * <li>version</li>
 * <li>installationKey</li>
 * <li>publishingOrganizationKey</li>
 * <li>license</li>
 * <li>maintenanceUpdateFrequency</li>
 * <li>external</li>
 * <li>numConstituents</li>
 * <li>type</li>
 * <li>subtype</li>
 * <li>title</li>
 * <li>alias</li>
 * <li>abbreviation</li>
 * <li>description</li>
 * <li>language</li>
 * <li>homepage</li>
 * <li>logoUrl</li>
 * <li>citation</li>
 * <li>rights</li>
 * <li>lockedForAutoUpdate</li>
 * <li>createdBy</li>
 * <li>modifiedBy</li>
 * <li>created</li>
 * <li>modified</li>
 * <li>deleted</li>
 * </ul>
 *
 * @see <a href="http://rs.gbif.org/schema/eml-gbif-profile/dev/eml.xsd">GBIF EML Profile XML Schema</a>
 */
@SuppressWarnings("unused")
public class Dataset
    implements NetworkEntity,
    Contactable,
    Endpointable,
    MachineTaggable,
    Taggable,
    Identifiable,
    Commentable,
    LenientEquals<Dataset> {

  private UUID key;
  private UUID parentDatasetKey;
  private UUID duplicateOfDatasetKey;
  private UUID installationKey;
  private UUID publishingOrganizationKey;
  private DOI doi;
  private String version;
  private boolean external;
  private int numConstituents;
  private DatasetType type;
  private DatasetSubtype subtype;
  private String title;
  private String alias;
  private String abbreviation;
  private String description;
  private Language language = Language.ENGLISH; // sensible default as it is not null
  private URI homepage;
  private URI logoUrl;

  private Citation citation = new Citation();
  private String rights;
  private boolean lockedForAutoUpdate;
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
  // EML specific properties which are not persisted on the dataset table!
  private List<Citation> bibliographicCitations = new ArrayList<>();
  private List<CuratorialUnitComposite> curatorialUnits = new ArrayList<>();
  private List<TaxonomicCoverages> taxonomicCoverages = new ArrayList<>();
  private String geographicCoverageDescription;
  private List<GeospatialCoverage> geographicCoverages = new ArrayList<>();
  private List<TemporalCoverage> temporalCoverages = new ArrayList<>();
  private List<KeywordCollection> keywordCollections = new ArrayList<>();
  private Project project;
  private SamplingDescription samplingDescription;
  private Set<Country> countryCoverage = new HashSet<>();
  private List<Collection> collections = new ArrayList<>();
  private List<DataDescription> dataDescriptions = new ArrayList<>();
  private Language dataLanguage;
  private String purpose;
  private String additionalInfo;
  private Date pubDate;
  private MaintenanceUpdateFrequency maintenanceUpdateFrequency;
  private String maintenanceDescription;
  private License license;

  @Override
  public UUID getKey() {
    return key;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setKey(UUID key) {
    this.key = key;
  }

  @Nullable
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String getTitle() {
    return title;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  @Nullable
  public String getDescription() {
    return description;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  /**
   * Autoassigned in the database table, ignored when persisted.
   */
  @Override
  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public Date getModified() {
    return modified;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  @Nullable
  public Date getDeleted() {
    return deleted;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setDeleted(Date deleted) {
    this.deleted = deleted;
  }

  @Nullable
  public UUID getParentDatasetKey() {
    return parentDatasetKey;
  }

  /**
   * Persisted in the database table.
   */
  public void setParentDatasetKey(UUID parentDatasetKey) {
    this.parentDatasetKey = parentDatasetKey;
  }

  /**
   * If a dataset is registered with GBIF through more than one place we'll mark all but one as a duplicate by pointing
   * it to the canonical dataset. That is done using this field. If it is {@code null} then this is not a known
   * duplicate.
   */
  @Nullable
  public UUID getDuplicateOfDatasetKey() {
    return duplicateOfDatasetKey;
  }

  /**
   * Persisted in the database table.
   */
  public void setDuplicateOfDatasetKey(UUID duplicateOfDatasetKey) {
    this.duplicateOfDatasetKey = duplicateOfDatasetKey;
  }

  @NotNull
  public UUID getInstallationKey() {
    return installationKey;
  }

  /**
   * Persisted in the database table.
   */
  public void setInstallationKey(UUID installationKey) {
    this.installationKey = installationKey;
  }

  @NotNull
  public UUID getPublishingOrganizationKey() {
    return publishingOrganizationKey;
  }

  /**
   * Persisted in the database table.
   */
  public void setPublishingOrganizationKey(UUID publishingOrganizationKey) {
    this.publishingOrganizationKey = publishingOrganizationKey;
  }

  /**
   * Persisted in the database table.
   *
   * @return the frequency with which changes are made to the dataset
   */
  @Nullable
  public MaintenanceUpdateFrequency getMaintenanceUpdateFrequency() {
    return maintenanceUpdateFrequency;
  }
  /**
   * Persisted in the database table.
   */
  public void setMaintenanceUpdateFrequency(MaintenanceUpdateFrequency maintenanceUpdateFrequency) {
    this.maintenanceUpdateFrequency = maintenanceUpdateFrequency;
  }

  /**
   * A description of the maintenance frequency of this resource.
   *
   * @return the description of the maintenance frequency of this resource
   */
  public String getMaintenanceDescription() {
    return maintenanceDescription;
  }

  public void setMaintenanceDescription(String maintenanceDescription) {
    this.maintenanceDescription = maintenanceDescription;
  }

  /**
   * Persisted in the database table.
   * </br>
   * Note for backwards compatibility, we cannot apply @NotNull to license. Otherwise existing users of our API
   * would have to ensure Dataset objects always populate license.
   * </br>
   * In the Registry DB, Dataset.license defaults to CC-BY 4.0. Therefore license must be excluded from lenientEquals
   * method.
   *
   * @return the License applied to the dataset
   *
   * @see <a href="http://dev.gbif.org/issues/browse/POR-3133">POR-3133</a>
   */
  public License getLicense() {
    return license;
  }

  /**
   * Persisted in the database table. Can be interpreted from EML.intellectualRights using machine readable format:
   * <pre>
   * {@code
   * <intellectualRights>
   *   <para>This work is licensed under a <ulink url="http://creativecommons.org/licenses/by/4.0/legalcode"><citetitle>Creative Commons Attribution (CC-BY) 4.0 License</citetitle></ulink>.</para>
   * </intellectualRights>
   * }
   * </pre>
   */
  public void setLicense(License license) {
    this.license = license;
  }

  /**
   * @return the primary DOI for this dataset regardless if issued by GBIF or publisher
   */
  public DOI getDoi() {
    return doi;
  }

  public void setDoi(DOI doi) {
    this.doi = doi;
  }

  public boolean isExternal() {
    return external;
  }

  /**
   * Persisted in the database table.
   */
  public void setExternal(boolean external) {
    this.external = external;
  }

  @Min(0)
  public int getNumConstituents() {
    return numConstituents;
  }

  /**
   * Not persisted in the database table, but calculated on the fly.
   */
  public void setNumConstituents(int numConstituents) {
    this.numConstituents = numConstituents;
  }

  @NotNull
  public DatasetType getType() {
    return type;
  }

  /**
   * Persisted in the database table.
   */
  public void setType(DatasetType type) {
    this.type = type;
  }

  @Nullable
  public DatasetSubtype getSubtype() {
    return subtype;
  }

  /**
   * Persisted in the database table.
   */
  public void setSubtype(DatasetSubtype subtype) {
    this.subtype = subtype;
  }

  /**
   * TODO: Document what this is
   */
  @Nullable
  @Size(min = 2, max = 50)
  public String getAlias() {
    return alias;
  }

  /**
   * Persisted in the database table.
   */
  public void setAlias(String alias) {
    this.alias = alias;
  }

  /**
   * TODO: Document what this is
   * TODO: are both alias & abbreviation needed?
   */
  @Nullable
  @Size(min = 1, max = 50)
  public String getAbbreviation() {
    return abbreviation;
  }

  /**
   * Persisted in the database table.
   */
  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  @NotNull
  public Language getLanguage() {
    return language;
  }

  /**
   * Persisted in the database table.
   */
  public void setLanguage(Language language) {
    this.language = language;
  }

  @HttpURI
  @Nullable
  public URI getHomepage() {
    return homepage;
  }

  /**
   * Persisted in the database table.
   */
  public void setHomepage(URI homepage) {
    this.homepage = homepage;
  }

  @HttpURI
  @Nullable
  public URI getLogoUrl() {
    return logoUrl;
  }

  /**
   * Persisted in the database table.
   */
  public void setLogoUrl(URI logoUrl) {
    this.logoUrl = logoUrl;
  }

  /**
   * The exact form of how to cite this dataset.
   */
  @Nullable
  @Valid
  public Citation getCitation() {
    return citation;
  }

  /**
   * Persisted in the database table.
   */
  public void setCitation(Citation citation) {
    this.citation = citation;
  }

  /**
   * Any kind of (copy)rights/IPR statements that apply to the datasets data.
   */
  @Nullable
  @Size(min = 1)
  public String getRights() {
    return rights;
  }

  /**
   * Persisted in the database table.
   */
  public void setRights(String rights) {
    this.rights = rights;
  }

  public boolean isLockedForAutoUpdate() {
    return lockedForAutoUpdate;
  }

  /**
   * Persisted in the database table.
   */
  public void setLockedForAutoUpdate(boolean lockedForAutoUpdate) {
    this.lockedForAutoUpdate = lockedForAutoUpdate;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * Persisted in the database table.
   */
  @Override
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String getModifiedBy() {
    return modifiedBy;
  }

  /**
   * Persisted in the database table.
   */
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

  public List<Citation> getBibliographicCitations() {
    return bibliographicCitations;
  }

  public void setBibliographicCitations(List<Citation> bibliographicCitations) {
    this.bibliographicCitations = bibliographicCitations;
  }

  public List<CuratorialUnitComposite> getCuratorialUnits() {
    return curatorialUnits;
  }

  public void setCuratorialUnits(List<CuratorialUnitComposite> curatorialUnits) {
    this.curatorialUnits = curatorialUnits;
  }

  public List<TaxonomicCoverages> getTaxonomicCoverages() {
    return taxonomicCoverages;
  }

  public void setTaxonomicCoverages(List<TaxonomicCoverages> taxonomicCoverages) {
    this.taxonomicCoverages = taxonomicCoverages;
  }

  public String getGeographicCoverageDescription() {
    return geographicCoverageDescription;
  }

  public void setGeographicCoverageDescription(String geographicCoverageDescription) {
    this.geographicCoverageDescription = geographicCoverageDescription;
  }

  public List<GeospatialCoverage> getGeographicCoverages() {
    return geographicCoverages;
  }

  public void setGeographicCoverages(List<GeospatialCoverage> geographicCoverages) {
    this.geographicCoverages = geographicCoverages;
  }

  public List<TemporalCoverage> getTemporalCoverages() {
    return temporalCoverages;
  }

  public void setTemporalCoverages(List<TemporalCoverage> temporalCoverages) {
    this.temporalCoverages = temporalCoverages;
  }

  public List<KeywordCollection> getKeywordCollections() {
    return keywordCollections;
  }

  public void setKeywordCollections(List<KeywordCollection> keywordCollections) {
    this.keywordCollections = keywordCollections;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public SamplingDescription getSamplingDescription() {
    return samplingDescription;
  }

  public void setSamplingDescription(SamplingDescription samplingDescription) {
    this.samplingDescription = samplingDescription;
  }

  public Set<Country> getCountryCoverage() {
    return countryCoverage;
  }

  public void setCountryCoverage(Set<Country> countryCoverage) {
    this.countryCoverage = countryCoverage;
  }

  public List<Collection> getCollections() {
    return collections;
  }

  public void setCollections(List<Collection> collections) {
    this.collections = collections;
  }

  public List<DataDescription> getDataDescriptions() {
    return dataDescriptions;
  }

  public void setDataDescriptions(List<DataDescription> dataDescriptions) {
    this.dataDescriptions = dataDescriptions;
  }

  public Language getDataLanguage() {
    return dataLanguage;
  }

  public void setDataLanguage(Language dataLanguage) {
    this.dataLanguage = dataLanguage;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public Date getPubDate() {
    return pubDate;
  }

  public void setPubDate(Date pubDate) {
    this.pubDate = pubDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dataset dataset = (Dataset) o;
    return external == dataset.external
        && numConstituents == dataset.numConstituents
        && lockedForAutoUpdate == dataset.lockedForAutoUpdate
        && Objects.equals(key, dataset.key)
        && Objects.equals(parentDatasetKey, dataset.parentDatasetKey)
        && Objects.equals(duplicateOfDatasetKey, dataset.duplicateOfDatasetKey)
        && Objects.equals(installationKey, dataset.installationKey)
        && Objects.equals(publishingOrganizationKey, dataset.publishingOrganizationKey)
        && Objects.equals(doi, dataset.doi)
        && Objects.equals(version, dataset.version)
        && type == dataset.type
        && subtype == dataset.subtype
        && Objects.equals(title, dataset.title)
        && Objects.equals(alias, dataset.alias)
        && Objects.equals(abbreviation, dataset.abbreviation)
        && Objects.equals(description, dataset.description)
        && language == dataset.language
        && Objects.equals(homepage, dataset.homepage)
        && Objects.equals(logoUrl, dataset.logoUrl)
        && Objects.equals(citation, dataset.citation)
        && Objects.equals(rights, dataset.rights)
        && Objects.equals(createdBy, dataset.createdBy)
        && Objects.equals(modifiedBy, dataset.modifiedBy)
        && Objects.equals(created, dataset.created)
        && Objects.equals(modified, dataset.modified)
        && Objects.equals(deleted, dataset.deleted)
        && Objects.equals(contacts, dataset.contacts)
        && Objects.equals(endpoints, dataset.endpoints)
        && Objects.equals(machineTags, dataset.machineTags)
        && Objects.equals(tags, dataset.tags)
        && Objects.equals(identifiers, dataset.identifiers)
        && Objects.equals(comments, dataset.comments)
        && Objects.equals(bibliographicCitations, dataset.bibliographicCitations)
        && Objects.equals(curatorialUnits, dataset.curatorialUnits)
        && Objects.equals(taxonomicCoverages, dataset.taxonomicCoverages)
        && Objects.equals(geographicCoverageDescription, dataset.geographicCoverageDescription)
        && Objects.equals(geographicCoverages, dataset.geographicCoverages)
        && Objects.equals(temporalCoverages, dataset.temporalCoverages)
        && Objects.equals(keywordCollections, dataset.keywordCollections)
        && Objects.equals(project, dataset.project)
        && Objects.equals(samplingDescription, dataset.samplingDescription)
        && Objects.equals(countryCoverage, dataset.countryCoverage)
        && Objects.equals(collections, dataset.collections)
        && Objects.equals(dataDescriptions, dataset.dataDescriptions)
        && dataLanguage == dataset.dataLanguage
        && Objects.equals(purpose, dataset.purpose)
        && Objects.equals(additionalInfo, dataset.additionalInfo)
        && Objects.equals(pubDate, dataset.pubDate)
        && maintenanceUpdateFrequency == dataset.maintenanceUpdateFrequency
        && Objects.equals(maintenanceDescription, dataset.maintenanceDescription)
        && license == dataset.license;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        parentDatasetKey,
        duplicateOfDatasetKey,
        installationKey,
        publishingOrganizationKey,
        doi,
        version,
        external,
        numConstituents,
        type,
        subtype,
        title,
        alias,
        abbreviation,
        description,
        language,
        homepage,
        logoUrl,
        citation,
        rights,
        lockedForAutoUpdate,
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
        comments,
        bibliographicCitations,
        curatorialUnits,
        taxonomicCoverages,
        geographicCoverageDescription,
        geographicCoverages,
        temporalCoverages,
        keywordCollections,
        project,
        samplingDescription,
        countryCoverage,
        collections,
        dataDescriptions,
        dataLanguage,
        purpose,
        additionalInfo,
        pubDate,
        maintenanceUpdateFrequency,
        maintenanceDescription,
        license);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Dataset.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("parentDatasetKey=" + parentDatasetKey)
        .add("duplicateOfDatasetKey=" + duplicateOfDatasetKey)
        .add("installationKey=" + installationKey)
        .add("publishingOrganizationKey=" + publishingOrganizationKey)
        .add("doi=" + doi)
        .add("version='" + version + "'")
        .add("external=" + external)
        .add("numConstituents=" + numConstituents)
        .add("type=" + type)
        .add("subtype=" + subtype)
        .add("title='" + title + "'")
        .add("alias='" + alias + "'")
        .add("abbreviation='" + abbreviation + "'")
        .add("description='" + description + "'")
        .add("language=" + language)
        .add("homepage=" + homepage)
        .add("logoUrl=" + logoUrl)
        .add("citation=" + citation)
        .add("rights='" + rights + "'")
        .add("lockedForAutoUpdate=" + lockedForAutoUpdate)
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
        .add("bibliographicCitations=" + bibliographicCitations)
        .add("curatorialUnits=" + curatorialUnits)
        .add("taxonomicCoverages=" + taxonomicCoverages)
        .add("geographicCoverageDescription='" + geographicCoverageDescription + "'")
        .add("geographicCoverages=" + geographicCoverages)
        .add("temporalCoverages=" + temporalCoverages)
        .add("keywordCollections=" + keywordCollections)
        .add("project=" + project)
        .add("samplingDescription=" + samplingDescription)
        .add("countryCoverage=" + countryCoverage)
        .add("collections=" + collections)
        .add("dataDescriptions=" + dataDescriptions)
        .add("dataLanguage=" + dataLanguage)
        .add("purpose='" + purpose + "'")
        .add("additionalInfo='" + additionalInfo + "'")
        .add("pubDate=" + pubDate)
        .add("maintenanceUpdateFrequency=" + maintenanceUpdateFrequency)
        .add("maintenanceDescription='" + maintenanceDescription + "'")
        .add("license=" + license)
        .toString();
  }

  /**
   * Only checks the persisted properties, excluding the server controlled fields (key, created, license etc).
   * Does not include the nested properties.
   */
  @Override
  public boolean lenientEquals(Dataset other) {
    if (this == other) {
      return true;
    }
    return Objects.equals(this.parentDatasetKey, other.parentDatasetKey)
        && Objects.equals(this.duplicateOfDatasetKey, other.duplicateOfDatasetKey)
        && Objects.equals(this.installationKey, other.installationKey)
        && Objects.equals(this.publishingOrganizationKey, other.publishingOrganizationKey)
        && Objects.equals(this.doi, other.doi)
        && Objects.equals(this.external, other.external)
        && Objects.equals(this.type, other.type)
        && Objects.equals(this.subtype, other.subtype)
        && Objects.equals(this.title, other.title)
        && Objects.equals(this.alias, other.alias)
        && Objects.equals(this.abbreviation, other.abbreviation)
        && Objects.equals(this.description, other.description)
        && Objects.equals(this.language, other.language)
        && Objects.equals(this.homepage, other.homepage)
        && Objects.equals(this.logoUrl, other.logoUrl)
        && Objects.equals(this.citation, other.citation)
        && Objects.equals(this.rights, other.rights)
        && Objects.equals(this.lockedForAutoUpdate, other.lockedForAutoUpdate)
        && Objects.equals(this.deleted, other.deleted)
        && Objects.equals(this.maintenanceUpdateFrequency, other.maintenanceUpdateFrequency)
        && Objects.equals(this.maintenanceDescription, other.maintenanceDescription);
  }
}
