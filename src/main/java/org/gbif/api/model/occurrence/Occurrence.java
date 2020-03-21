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
package org.gbif.api.model.occurrence;

import org.gbif.api.model.common.Identifier;
import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.model.common.MediaObject;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.Sex;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.TypeStatus;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.UnknownTerm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Represents an Occurrence as interpreted by GBIF, adding typed properties on top of the verbatim ones.
 */
@SuppressWarnings("unused")
public class Occurrence extends VerbatimOccurrence implements LinneanClassification, LinneanClassificationKeys {

  public static final String GEO_DATUM = "WGS84";
  // keep names of ALL properties of this class in a set for jackson serialization, see #properties()
  private static final Set<String> PROPERTIES = Collections.unmodifiableSet(
    Stream.concat(
      // we need to these json properties manually cause we have a fixed getter but no field for it
      Stream.of(DwcTerm.geodeticDatum.simpleName(), "class", "countryCode"),
      Stream.concat(Arrays.stream(Occurrence.class.getDeclaredFields()),
        Arrays.stream(VerbatimOccurrence.class.getDeclaredFields()))
        .filter(field -> !Modifier.isStatic(field.getModifiers()))
        .map(Field::getName)).collect(Collectors.toSet()));
  // occurrence fields
  private BasisOfRecord basisOfRecord;
  private Integer individualCount;
  private Sex sex;
  private LifeStage lifeStage;
  private EstablishmentMeans establishmentMeans;
  // taxonomy as nub keys -> LinneanClassificationKeys
  private Integer taxonKey;
  private Integer kingdomKey;
  private Integer phylumKey;
  private Integer classKey;
  private Integer orderKey;
  private Integer familyKey;
  private Integer genusKey;
  private Integer subgenusKey;
  private Integer speciesKey;
  private Integer acceptedTaxonKey;
  // taxonomy as name strings -> LinneanClassification
  private String scientificName;  // the interpreted name matching taxonKey
  private String acceptedScientificName;
  private String kingdom;
  private String phylum;
  @JsonProperty("class")
  private String clazz;
  private String order;
  private String family;
  private String genus;
  private String subgenus;
  private String species;
  // atomised scientific name
  private String genericName; // missing from DwC
  private String specificEpithet;
  private String infraspecificEpithet;
  private Rank taxonRank;
  private TaxonomicStatus taxonomicStatus;
  // identification
  private Date dateIdentified;
  // location
  private Double decimalLongitude;
  private Double decimalLatitude;

  //coordinatePrecision and coordinateUncertaintyInMeters should be BigDecimal see POR-2795
  private Double coordinatePrecision;
  private Double coordinateUncertaintyInMeters;
  @Deprecated //see getter
  private Double coordinateAccuracy;

  private Double elevation;
  private Double elevationAccuracy;
  private Double depth;
  private Double depthAccuracy;
  private Continent continent;
  @JsonSerialize(using = Country.IsoSerializer.class)
  @JsonDeserialize(using = Country.IsoDeserializer.class)
  private Country country;
  private String stateProvince;
  private String waterBody;
  // recording event
  private Integer year;
  private Integer month;
  private Integer day;
  private Date eventDate;
  private TypeStatus typeStatus;
  // extracted from type status, but we should propose a new dwc term for this!
  // for example: "Paratype of Taeniopteryx metequi Ricker & Ross" is status=Paratype, typifiedName=Taeniopteryx metequi
// Ricker & Ross
  private String typifiedName; // missing from DwC
  private Set<OccurrenceIssue> issues = EnumSet.noneOf(OccurrenceIssue.class);
  // record level
  private Date modified;  // interpreted dc:modified, i.e. date changed in source
  private Date lastInterpreted;
  private URI references;
  private License license;
  private Double organismQuantity;
  private String organismQuantityType;
  private String sampleSizeUnit;
  private Double sampleSizeValue;
  private Double relativeOrganismQuantity;
  // interpreted extension data
  private List<Identifier> identifiers = new ArrayList<>();
  private List<MediaObject> media = new ArrayList<>();
  private List<FactOrMeasurment> facts = new ArrayList<>();
  private List<OccurrenceRelation> relations = new ArrayList<>();
  @JsonProperty("recordedByIDs")
  private List<AgentIdentifier> recordedByIds = new ArrayList<>();
  @JsonProperty("identifiedByIDs")
  private List<AgentIdentifier> identifiedByIds = new ArrayList<>();

  public Occurrence() {

  }

  /**
   * Create occurrence instance from existing verbatim one, copying over all data.
   */
  public Occurrence(@Nullable VerbatimOccurrence verbatim) {
    if (verbatim != null) {
      setKey(verbatim.getKey());
      setDatasetKey(verbatim.getDatasetKey());
      setPublishingOrgKey(verbatim.getPublishingOrgKey());
      setPublishingCountry(verbatim.getPublishingCountry());
      setProtocol(verbatim.getProtocol());
      setCrawlId(verbatim.getCrawlId());
      if (verbatim.getLastCrawled() != null) {
        setLastCrawled(new Date(verbatim.getLastCrawled().getTime()));
      }
      if (verbatim.getVerbatimFields() != null) {
        getVerbatimFields().putAll(verbatim.getVerbatimFields());
      }
      if (verbatim.getLastParsed() != null) {
        setLastParsed(verbatim.getLastParsed());
      }
      setExtensions(verbatim.getExtensions());
    }
  }

  @Nullable
  public BasisOfRecord getBasisOfRecord() {
    return basisOfRecord;
  }

  public void setBasisOfRecord(BasisOfRecord basisOfRecord) {
    this.basisOfRecord = basisOfRecord;
  }

  @Nullable
  public Integer getIndividualCount() {
    return individualCount;
  }

  public void setIndividualCount(Integer individualCount) {
    this.individualCount = individualCount;
  }

  @Nullable
  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  @Nullable
  public LifeStage getLifeStage() {
    return lifeStage;
  }

  public void setLifeStage(LifeStage lifeStage) {
    this.lifeStage = lifeStage;
  }

  @Nullable
  public EstablishmentMeans getEstablishmentMeans() {
    return establishmentMeans;
  }

  public void setEstablishmentMeans(EstablishmentMeans establishmentMeans) {
    this.establishmentMeans = establishmentMeans;
  }

  /**
   * The best matching, accepted GBIF backbone name usage representing this occurrence.
   * In case the verbatim scientific name and its classification can only be matched to a higher rank this will
   * represent the lowest matching rank. In the worst case this could just be for example Animalia.
   */
  @Nullable
  public Integer getTaxonKey() {
    return taxonKey;
  }

  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  @Nullable
  @Override
  public Integer getKingdomKey() {
    return kingdomKey;
  }

  @Override
  public void setKingdomKey(@Nullable Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Nullable
  @Override
  public Integer getPhylumKey() {
    return phylumKey;
  }

  @Override
  public void setPhylumKey(@Nullable Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Nullable
  @Override
  public Integer getClassKey() {
    return classKey;
  }

  @Override
  public void setClassKey(@Nullable Integer classKey) {
    this.classKey = classKey;
  }

  @Nullable
  @Override
  public Integer getOrderKey() {
    return orderKey;
  }

  @Override
  public void setOrderKey(@Nullable Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Nullable
  @Override
  public Integer getFamilyKey() {
    return familyKey;
  }

  @Override
  public void setFamilyKey(@Nullable Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Nullable
  @Override
  public Integer getGenusKey() {
    return genusKey;
  }

  @Override
  public void setGenusKey(@Nullable Integer genusKey) {
    this.genusKey = genusKey;
  }

  @Nullable
  @Override
  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  @Override
  public void setSubgenusKey(@Nullable Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  @Nullable
  @Override
  public Integer getHigherRankKey(Rank rank) {
    return ClassificationUtils.getHigherRankKey(this, rank);
  }

  /**
   * An ordered map with entries for all higher Linnean ranks excluding the taxonKey itself.
   * The map starts with the highest rank, e.g. the kingdom and maps the name usage key to its canonical name.
   *
   * @return map of higher ranks
   */
  @NotNull
  @JsonIgnore
  public Map<Integer, String> getHigherClassificationMap() {
    return taxonKey == null ? ClassificationUtils.getHigherClassificationMap(this)
      : ClassificationUtils.getHigherClassificationMap(this, taxonKey, null, null);
  }

  /**
   * The accepted species for this occurrence. In case the taxonKey is of a higher rank than species (e.g. genus)
   * speciesKey is null. In case taxonKey represents an infraspecific taxon the speciesKey points to the species
   * the infraspecies is classified as. In case of taxonKey being a species the speciesKey is the same.
   */
  @Nullable
  @Override
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(@Nullable Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  /**
   * The accepted taxon key from the GBIF backbone.
   */
  @Nullable
  public Integer getAcceptedTaxonKey() {
    return acceptedTaxonKey;
  }

  public void setAcceptedTaxonKey(Integer acceptedTaxonKey) {
    this.acceptedTaxonKey = acceptedTaxonKey;
  }

  @Nullable
  public String getSpecificEpithet() {
    return specificEpithet;
  }

  public void setSpecificEpithet(String specificEpithet) {
    this.specificEpithet = specificEpithet;
  }

  @Nullable
  public String getInfraspecificEpithet() {
    return infraspecificEpithet;
  }

  public void setInfraspecificEpithet(String infraspecificEpithet) {
    this.infraspecificEpithet = infraspecificEpithet;
  }

  @Nullable
  public Rank getTaxonRank() {
    return taxonRank;
  }

  public void setTaxonRank(Rank taxonRank) {
    this.taxonRank = taxonRank;
  }

  /**
   * The status of the use of the scientificName as a label for a taxon.
   * The GBIF recommended controlled value vocabulary can be found at <a href="http://rs.gbif.org/vocabulary/gbif/taxonomic_status.xm">http://rs.gbif.org/vocabulary/gbif/taxonomic_status.xm</a>.
   */
  @Nullable
  public TaxonomicStatus getTaxonomicStatus() {
    return taxonomicStatus;
  }

  public void setTaxonomicStatus(TaxonomicStatus taxonomicStatus) {
    this.taxonomicStatus = taxonomicStatus;
  }

  /**
   * The scientific name for taxonKey from the GBIF backbone.
   */
  @Nullable
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(@Nullable String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * The verbatim scientific name as provided by the source.
   */
  @Nullable
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public String getVerbatimScientificName() {
    return getVerbatimField(DwcTerm.scientificName);
  }

  public void setVerbatimScientificName(String scientificName) {
    //DO NOTHING
  }

  /**
   * The accepted scientific name for the acceptedTaxonKey from the GBIF backbone.
   */
  @Nullable
  public String getAcceptedScientificName() {
    return acceptedScientificName;
  }

  public void setAcceptedScientificName(String acceptedScientificName) {
    this.acceptedScientificName = acceptedScientificName;
  }

  @Nullable
  @Override
  public String getKingdom() {
    return kingdom;
  }

  @Override
  public void setKingdom(@Nullable String kingdom) {
    this.kingdom = kingdom;
  }

  @Nullable
  @Override
  public String getPhylum() {
    return phylum;
  }

  @Override
  public void setPhylum(@Nullable String phylum) {
    this.phylum = phylum;
  }

  @Nullable
  @Override
  public String getClazz() {
    return clazz;
  }

  @Override
  public void setClazz(@Nullable String clazz) {
    this.clazz = clazz;
  }

  @Nullable
  @Override
  public String getOrder() {
    return order;
  }

  @Override
  public void setOrder(@Nullable String order) {
    this.order = order;
  }

  @Nullable
  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public void setFamily(@Nullable String family) {
    this.family = family;
  }

  @Nullable
  @Override
  public String getGenus() {
    return genus;
  }

  @Override
  public void setGenus(@Nullable String genus) {
    this.genus = genus;
  }

  @Nullable
  public String getGenericName() {
    return genericName;
  }

  public void setGenericName(String genericName) {
    this.genericName = genericName;
  }

  @Nullable
  @Override
  public String getSubgenus() {
    return subgenus;
  }

  @Override
  public void setSubgenus(@Nullable String subgenus) {
    this.subgenus = subgenus;
  }

  @Nullable
  @Override
  public String getHigherRank(Rank rank) {
    return ClassificationUtils.getHigherRank(this, rank);
  }

  /**
   * The corresponding scientific name of the speciesKey from the GBIF backbone.
   */
  @Nullable
  @Override
  public String getSpecies() {
    return species;
  }

  @Override
  public void setSpecies(@Nullable String species) {
    this.species = species;
  }

  @Nullable
  public Date getDateIdentified() {
    return dateIdentified == null ? null : new Date(dateIdentified.getTime());
  }

  public void setDateIdentified(@Nullable Date dateIdentified) {
    this.dateIdentified = dateIdentified == null ? null : new Date(dateIdentified.getTime());
  }

  /**
   * The decimalLongitude in decimal degrees always for the WGS84 datum. If a different geodetic datum was given the verbatim
   * coordinates are transformed into WGS84 values.
   */
  @Nullable
  public Double getDecimalLongitude() {
    return decimalLongitude;
  }

  public void setDecimalLongitude(@Nullable Double decimalLongitude) {
    this.decimalLongitude = decimalLongitude;
  }

  @Nullable
  public Double getDecimalLatitude() {
    return decimalLatitude;
  }

  public void setDecimalLatitude(@Nullable Double decimalLatitude) {
    this.decimalLatitude = decimalLatitude;
  }

  /**
   * The uncertainty radius for lat/lon in meters.
   */
  @Nullable
  public Double getCoordinateUncertaintyInMeters() {
    return coordinateUncertaintyInMeters;
  }

  public void setCoordinateUncertaintyInMeters(@Nullable Double coordinateUncertaintyInMeters) {
    this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
  }

  @Nullable
  public Double getCoordinatePrecision() {
    return coordinatePrecision;
  }

  public void setCoordinatePrecision(Double coordinatePrecision) {
    this.coordinatePrecision = coordinatePrecision;
  }

  /**
   * @Deprecated to be removed in the public v2 of the API (see POR-3061)
   * The uncertainty for latitude in decimal degrees.
   * Note that the longitude degrees have a different accuracy in degrees which changes with latitude and is largest at the poles.
   */
  @Nullable
  @Deprecated
  public Double getCoordinateAccuracy() {
    return coordinateAccuracy;
  }

  public void setCoordinateAccuracy(@Nullable Double coordinateAccuracy) {
    this.coordinateAccuracy = coordinateAccuracy;
  }

  /**
   * The geodetic datum for the interpreted decimal coordinates.
   * This is always WGS84 if there a coordinate exists as we reproject other datums into WGS84.
   */
  @Nullable
  public String getGeodeticDatum() {
    if (decimalLatitude != null) {
      return GEO_DATUM;
    }
    return null;
  }

  /**
   * This private method is needed for jackson deserialization only.
   */
  private void setGeodeticDatum(String datum) {
    // ignore, we have a static WGS84 value
  }

  /**
   * Elevation in meters usually above sea level (altitude).
   * </br>
   * The elevation is calculated using the equation: (minimumElevationInMeters + maximumElevationInMeters) / 2.
   */
  @Nullable
  public Double getElevation() {
    return elevation;
  }

  public void setElevation(@Nullable Double elevation) {
    this.elevation = elevation;
  }

  /**
   * Elevation accuracy is the uncertainty for the elevation in meters.
   * </br>
   * The elevation accuracy is calculated using the equation: (maximumElevationInMeters - minimumElevationInMeters) / 2
   */
  @Nullable
  public Double getElevationAccuracy() {
    return elevationAccuracy;
  }

  public void setElevationAccuracy(@Nullable Double elevationAccuracy) {
    this.elevationAccuracy = elevationAccuracy;
  }

  /**
   * Depth in meters below the surface. Complimentary to elevation, the depth can be 10 meters below the surface of a
   * lake in 1100m (=elevation).
   * </br>
   * The depth is calculated using the equation: (minimumDepthInMeters + maximumDepthInMeters) / 2.
   */
  @Nullable
  public Double getDepth() {
    return depth;
  }

  public void setDepth(@Nullable Double depth) {
    this.depth = depth;
  }

  /**
   * Depth accuracy is the uncertainty for the depth in meters.
   * </br>
   * The depth accuracy is calculated using the equation: (maximumDepthInMeters - minimumDepthInMeters) / 2
   */
  @Nullable
  public Double getDepthAccuracy() {
    return depthAccuracy;
  }

  public void setDepthAccuracy(@Nullable Double depthAccuracy) {
    this.depthAccuracy = depthAccuracy;
  }

  @Nullable
  public Continent getContinent() {
    return continent;
  }

  public void setContinent(@Nullable Continent continent) {
    this.continent = continent;
  }

  @Nullable
  @JsonProperty("countryCode")
  public Country getCountry() {
    return country;
  }

  public void setCountry(@Nullable Country country) {
    this.country = country;
  }

  /**
   * Renders the country title as json property country in addition to the iso 2 letter countryCode being
   * serialized by the regular country java property.
   * Made private to only use it for json serialization and not within java code.
   */
  @Nullable
  @JsonProperty("country")
  private String getCountryTitle() {
    return country == null ? null : country.getTitle();
  }

  private void setCountryTitle(String country) {
    // ignore, setter only to avoid json being written into the fields map
  }

  @Nullable
  public String getStateProvince() {
    return stateProvince;
  }

  public void setStateProvince(@Nullable String stateProvince) {
    this.stateProvince = stateProvince;
  }

  @Nullable
  public String getWaterBody() {
    return waterBody;
  }

  public void setWaterBody(@Nullable String waterBody) {
    this.waterBody = waterBody;
  }

  /**
   * The full year of the event date.
   *
   * @return the year of the event date
   */
  @Min(1500)
  @Max(2020)
  @Nullable
  public Integer getYear() {
    return year;
  }

  public void setYear(@Nullable Integer year) {
    this.year = year;
  }

  /**
   * The month of the year of the event date starting with zero for january following {@link Date}.
   *
   * @return the month of the event date
   */
  @Min(1)
  @Max(12)
  @Nullable
  public Integer getMonth() {
    return month;
  }

  public void setMonth(@Nullable Integer month) {
    this.month = month;
  }

  /**
   * The day of the month of the event date.
   *
   * @return the day of the event date
   */
  @Min(1)
  @Max(31)
  @Nullable
  public Integer getDay() {
    return day;
  }

  public void setDay(@Nullable Integer day) {
    this.day = day;
  }

  /**
   * The date the occurrence was recorded or collected.
   */
  @Nullable
  public Date getEventDate() {
    return eventDate == null ? null : new Date(eventDate.getTime());
  }

  public void setEventDate(@Nullable Date eventDate) {
    this.eventDate = eventDate == null ? null : new Date(eventDate.getTime());
  }

  @Nullable
  public TypeStatus getTypeStatus() {
    return typeStatus;
  }

  public void setTypeStatus(@Nullable TypeStatus typeStatus) {
    this.typeStatus = typeStatus;
  }

  /**
   * The scientific name the type status of this specimen applies to.
   */
  @Nullable
  public String getTypifiedName() {
    return typifiedName;
  }

  public void setTypifiedName(@Nullable String typifiedName) {
    this.typifiedName = typifiedName;
  }

  /**
   * A set of issues found for this occurrence.
   */
  @NotNull
  public Set<OccurrenceIssue> getIssues() {
    return issues;
  }

  public void setIssues(Set<OccurrenceIssue> issues) {
    Objects.requireNonNull(issues, "Issues cannot be null");
    EnumSet<OccurrenceIssue> set = EnumSet.noneOf(OccurrenceIssue.class);
    set.addAll(issues);
    this.issues = set;
  }

  public void addIssue(OccurrenceIssue issue) {
    Objects.requireNonNull(issue, "Issue needs to be specified");
    issues.add(issue);
  }

  /**
   * The interpreted dc:modified from the verbatim source data.
   * Ideally indicating when a record was last modified in the source.
   */
  @Nullable
  public Date getModified() {
    return modified == null ? null : new Date(modified.getTime());
  }

  public void setModified(@Nullable Date modified) {
    this.modified = modified == null ? null : new Date(modified.getTime());
  }

  /**
   * The date this occurrence last went through the interpretation phase of the GBIF indexing.
   */
  @Nullable
  public Date getLastInterpreted() {
    return lastInterpreted == null ? null : new Date(lastInterpreted.getTime());
  }

  public void setLastInterpreted(@Nullable Date lastInterpreted) {
    this.lastInterpreted = lastInterpreted == null ? null : new Date(lastInterpreted.getTime());
  }

  /**
   * An external link to more details, the records "homepage".
   */
  @Nullable
  public URI getReferences() {
    return references;
  }

  public void setReferences(URI references) {
    this.references = references;
  }

  /**
   * A number or enumeration value for the quantity of organisms.
   */
  @Nullable
  public Double getOrganismQuantity() {
    return organismQuantity;
  }

  public void setOrganismQuantity(@Nullable Double organismQuantity) {
    this.organismQuantity = organismQuantity;
  }

  /**
   * The type of quantification system used for the quantity of organisms.
   */
  @Nullable
  public String getOrganismQuantityType() {
    return organismQuantityType;
  }

  public void setOrganismQuantityType(@Nullable String organismQuantityType) {
    this.organismQuantityType = organismQuantityType;
  }

  /**
   * The unit of measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  @Nullable
  public String getSampleSizeUnit() {
    return sampleSizeUnit;
  }

  public void setSampleSizeUnit(@Nullable String sampleSizeUnit) {
    this.sampleSizeUnit = sampleSizeUnit;
  }

  /**
   * A numeric value for a measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  @Nullable
  public Double getSampleSizeValue() {
    return sampleSizeValue;
  }

  public void setSampleSizeValue(@Nullable Double sampleSizeValue) {
    this.sampleSizeValue = sampleSizeValue;
  }

  /**
   * Calculated filed organismQuantity / sampleSizeValue, if the type is identical
   */
  @Nullable
  public Double getRelativeOrganismQuantity() {
    return relativeOrganismQuantity;
  }

  public void setRelativeOrganismQuantity(@Nullable Double relativeOrganismQuantity) {
    this.relativeOrganismQuantity = relativeOrganismQuantity;
  }

  /**
   * Applied license to the occurrence record or dataset to which this record belongs to.
   */
  @NotNull
  public License getLicense() {
    return license;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  @NotNull
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  @NotNull
  public List<MediaObject> getMedia() {
    return media;
  }

  public void setMedia(List<MediaObject> media) {
    this.media = media;
  }

  @NotNull
  public List<FactOrMeasurment> getFacts() {
    return facts;
  }

  public void setFacts(List<FactOrMeasurment> facts) {
    this.facts = facts;
  }

  @NotNull
  public List<OccurrenceRelation> getRelations() {
    return relations;
  }

  public void setRelations(List<OccurrenceRelation> relations) {
    this.relations = relations;
  }

  @NotNull
  public List<AgentIdentifier> getRecordedByIds() {
    return recordedByIds;
  }

  public void setRecordedByIds(List<AgentIdentifier> recordedByIds) {
    this.recordedByIds = recordedByIds;
  }

  @NotNull
  public List<AgentIdentifier> getIdentifiedByIds() {
    return identifiedByIds;
  }

  public void setIdentifiedByIds(List<AgentIdentifier> identifiedByIds) {
    this.identifiedByIds = identifiedByIds;
  }

  /**
   * Convenience method checking if any spatial validation rule has not passed.
   * Primarily used to indicate that the record should not be displayed on a map.
   */
  @JsonIgnore
  public boolean hasSpatialIssue() {
    for (OccurrenceIssue rule : OccurrenceIssue.GEOSPATIAL_RULES) {
      if (issues.contains(rule)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Occurrence that = (Occurrence) o;
    return basisOfRecord == that.basisOfRecord &&
      Objects.equals(individualCount, that.individualCount) &&
      sex == that.sex &&
      lifeStage == that.lifeStage &&
      establishmentMeans == that.establishmentMeans &&
      Objects.equals(taxonKey, that.taxonKey) &&
      Objects.equals(kingdomKey, that.kingdomKey) &&
      Objects.equals(phylumKey, that.phylumKey) &&
      Objects.equals(classKey, that.classKey) &&
      Objects.equals(orderKey, that.orderKey) &&
      Objects.equals(familyKey, that.familyKey) &&
      Objects.equals(genusKey, that.genusKey) &&
      Objects.equals(subgenusKey, that.subgenusKey) &&
      Objects.equals(speciesKey, that.speciesKey) &&
      Objects.equals(acceptedTaxonKey, that.acceptedTaxonKey) &&
      Objects.equals(scientificName, that.scientificName) &&
      Objects.equals(acceptedScientificName, that.acceptedScientificName) &&
      Objects.equals(kingdom, that.kingdom) &&
      Objects.equals(phylum, that.phylum) &&
      Objects.equals(clazz, that.clazz) &&
      Objects.equals(order, that.order) &&
      Objects.equals(family, that.family) &&
      Objects.equals(genus, that.genus) &&
      Objects.equals(subgenus, that.subgenus) &&
      Objects.equals(species, that.species) &&
      Objects.equals(genericName, that.genericName) &&
      Objects.equals(specificEpithet, that.specificEpithet) &&
      Objects.equals(infraspecificEpithet, that.infraspecificEpithet) &&
      taxonRank == that.taxonRank &&
      taxonomicStatus == that.taxonomicStatus &&
      Objects.equals(dateIdentified, that.dateIdentified) &&
      Objects.equals(decimalLongitude, that.decimalLongitude) &&
      Objects.equals(decimalLatitude, that.decimalLatitude) &&
      Objects.equals(coordinatePrecision, that.coordinatePrecision) &&
      Objects.equals(coordinateUncertaintyInMeters, that.coordinateUncertaintyInMeters) &&
      Objects.equals(elevation, that.elevation) &&
      Objects.equals(elevationAccuracy, that.elevationAccuracy) &&
      Objects.equals(depth, that.depth) &&
      Objects.equals(depthAccuracy, that.depthAccuracy) &&
      continent == that.continent &&
      country == that.country &&
      Objects.equals(stateProvince, that.stateProvince) &&
      Objects.equals(waterBody, that.waterBody) &&
      Objects.equals(year, that.year) &&
      Objects.equals(month, that.month) &&
      Objects.equals(day, that.day) &&
      Objects.equals(eventDate, that.eventDate) &&
      typeStatus == that.typeStatus &&
      Objects.equals(typifiedName, that.typifiedName) &&
      Objects.equals(issues, that.issues) &&
      Objects.equals(modified, that.modified) &&
      Objects.equals(lastInterpreted, that.lastInterpreted) &&
      Objects.equals(references, that.references) &&
      license == that.license &&
      Objects.equals(organismQuantity, that.organismQuantity) &&
      Objects.equals(organismQuantityType, that.organismQuantityType) &&
      Objects.equals(sampleSizeUnit, that.sampleSizeUnit) &&
      Objects.equals(sampleSizeValue, that.sampleSizeValue) &&
      Objects.equals(relativeOrganismQuantity, that.relativeOrganismQuantity) &&
      Objects.equals(identifiers, that.identifiers) &&
      Objects.equals(media, that.media) &&
      Objects.equals(facts, that.facts) &&
      Objects.equals(relations, that.relations) &&
      Objects.equals(identifiedByIds, that.identifiedByIds) &&
      Objects.equals(recordedByIds, that.recordedByIds);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(super.hashCode(), basisOfRecord, individualCount, sex, lifeStage, establishmentMeans,
        taxonKey, kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, subgenusKey,
        speciesKey, acceptedTaxonKey, scientificName, acceptedScientificName, kingdom, phylum,
        clazz, order, family, genus, subgenus, species, genericName, specificEpithet,
        infraspecificEpithet, taxonRank, taxonomicStatus, dateIdentified, decimalLongitude,
        decimalLatitude, coordinatePrecision, coordinateUncertaintyInMeters, elevation,
        elevationAccuracy, depth, depthAccuracy, continent, country, stateProvince, waterBody, year,
        month, day, eventDate, typeStatus, typifiedName, issues, modified, lastInterpreted,
        references, license, organismQuantity, organismQuantityType, sampleSizeUnit,
        sampleSizeValue, relativeOrganismQuantity, identifiers, media, facts, relations, recordedByIds,
        identifiedByIds);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Occurrence.class.getSimpleName() + "[", "]")
      .add("basisOfRecord=" + basisOfRecord)
      .add("individualCount=" + individualCount)
      .add("sex=" + sex)
      .add("lifeStage=" + lifeStage)
      .add("establishmentMeans=" + establishmentMeans)
      .add("taxonKey=" + taxonKey)
      .add("kingdomKey=" + kingdomKey)
      .add("phylumKey=" + phylumKey)
      .add("classKey=" + classKey)
      .add("orderKey=" + orderKey)
      .add("familyKey=" + familyKey)
      .add("genusKey=" + genusKey)
      .add("subgenusKey=" + subgenusKey)
      .add("speciesKey=" + speciesKey)
      .add("acceptedTaxonKey=" + acceptedTaxonKey)
      .add("scientificName='" + scientificName + "'")
      .add("acceptedScientificName='" + acceptedScientificName + "'")
      .add("kingdom='" + kingdom + "'")
      .add("phylum='" + phylum + "'")
      .add("clazz='" + clazz + "'")
      .add("order='" + order + "'")
      .add("family='" + family + "'")
      .add("genus='" + genus + "'")
      .add("subgenus='" + subgenus + "'")
      .add("species='" + species + "'")
      .add("genericName='" + genericName + "'")
      .add("specificEpithet='" + specificEpithet + "'")
      .add("infraspecificEpithet='" + infraspecificEpithet + "'")
      .add("taxonRank=" + taxonRank)
      .add("taxonomicStatus=" + taxonomicStatus)
      .add("dateIdentified=" + dateIdentified)
      .add("decimalLongitude=" + decimalLongitude)
      .add("decimalLatitude=" + decimalLatitude)
      .add("coordinatePrecision=" + coordinatePrecision)
      .add("coordinateUncertaintyInMeters=" + coordinateUncertaintyInMeters)
      .add("coordinateAccuracy=" + coordinateAccuracy)
      .add("elevation=" + elevation)
      .add("elevationAccuracy=" + elevationAccuracy)
      .add("depth=" + depth)
      .add("depthAccuracy=" + depthAccuracy)
      .add("continent=" + continent)
      .add("country=" + country)
      .add("stateProvince='" + stateProvince + "'")
      .add("waterBody='" + waterBody + "'")
      .add("year=" + year)
      .add("month=" + month)
      .add("day=" + day)
      .add("eventDate=" + eventDate)
      .add("typeStatus=" + typeStatus)
      .add("typifiedName='" + typifiedName + "'")
      .add("issues=" + issues)
      .add("modified=" + modified)
      .add("lastInterpreted=" + lastInterpreted)
      .add("references=" + references)
      .add("license=" + license)
      .add("organismQuantity=" + organismQuantity)
      .add("organismQuantityType='" + organismQuantityType + "'")
      .add("sampleSizeUnit='" + sampleSizeUnit + "'")
      .add("sampleSizeValue=" + sampleSizeValue)
      .add("relativeOrganismQuantity=" + relativeOrganismQuantity)
      .add("identifiers=" + identifiers)
      .add("media=" + media)
      .add("facts=" + facts)
      .add("relations=" + relations)
      .add("recordedByIds=" + recordedByIds)
      .add("identifiedByIds=" + identifiedByIds)
      .toString();
  }

  /**
   * This private method is only for serialization via jackson and not exposed anywhere else!
   * It maps the verbatimField terms into properties with their simple name or qualified names for UnknownTerms.
   */
  @JsonAnyGetter
  private Map<String, String> jsonVerbatimFields() {
    Map<String, String> extendedProps = new HashMap<>();
    for (Map.Entry<Term, String> prop : getVerbatimFields().entrySet()) {
      Term t = prop.getKey();
      if (t instanceof UnknownTerm || PROPERTIES.contains(t.simpleName())) {
        extendedProps.put(t.qualifiedName(), prop.getValue());
      } else {
        // render all terms in controlled enumerations as simple names only - unless we have a property of that name already!
        extendedProps.put(t.simpleName(), prop.getValue());
      }
    }
    return extendedProps;
  }
}
