/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
import org.gbif.api.vocabulary.TypeStatus;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.UnknownTerm;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Represents an Occurrence as interpreted by GBIF, adding typed properties on top of the verbatim ones.
 */
public class Occurrence extends VerbatimOccurrence implements LinneanClassification, LinneanClassificationKeys {

  public static final String GEO_DATUM = "WGS84";
  // keep names of ALL properties of this class in a set for jackson serialization, see #properties()
  private static final Set<String> PROPERTIES = ImmutableSet.copyOf(
    Iterables.concat(
      // we need to these json properties manually cause we have a fixed getter but no field for it
      Lists.newArrayList(DwcTerm.geodeticDatum.simpleName(), "class", "countryCode"),
      Iterables.transform(
        Iterables.concat(Lists.newArrayList(Occurrence.class.getDeclaredFields()),
                Lists.newArrayList(VerbatimOccurrence.class.getDeclaredFields())
        ), new Function<Field, String>() {

          @Nullable
          @Override
          public String apply(@Nullable Field f) {
            return f.getName();
          }
        })
      )
    );
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
  // taxonomy as name strings -> LinneanClassification
  private String scientificName;  // the interpreted name matching taxonKey
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
  // interpreted extension data
  private List<Identifier> identifiers = Lists.newArrayList();
  private List<MediaObject> media = Lists.newArrayList();
  private List<FactOrMeasurment> facts = Lists.newArrayList();
  private List<OccurrenceRelation> relations = Lists.newArrayList();

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

  @Nullable
  /**
   * The best matching, accepted GBIF backbone name usage representing this occurrence.
   * In case the verbatim scientific name and its classification can only be matched to a higher rank this will
   * represent the lowest matching rank. In the worst case this could just be for example Animalia.
   */
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
  public LinkedHashMap<Integer, String> getHigherClassificationMap() {
    return taxonKey == null ? ClassificationUtils.getHigherClassificationMap(this)
      : ClassificationUtils.getHigherClassificationMap(this, taxonKey, null, null);
  }

  @Nullable
  @Override
  /**
   * The accepted species for this occurrence. In case the taxonKey is of a higher rank than species (e.g. genus)
   * speciesKey is null. In case taxonKey represents an infraspecific taxon the speciesKey points to the species
   * the infraspecies is classified as. In case of taxonKey being a species the speciesKey is the same.
   */
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(@Nullable Integer speciesKey) {
    this.speciesKey = speciesKey;
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

  @Nullable
  /**
   * The scientific name for taxonKey from the GBIF backbone.
   */
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(@Nullable String scientificName) {
    this.scientificName = scientificName;
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

  @Nullable
  @Override
  /**
   * The corresponding scientific name of the speciesKey from the GBIF backbone.
   */
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

  @Nullable
  /**
   * The decimalLongitude in decimal degrees always for the WGS84 datum. If a different geodetic datum was given the verbatim
   * coordinates are transformed into WGS84 values.
   */
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

  @Nullable
  @Deprecated
  /**
   * @Deprecated to be removed in the public v2 of the API (see POR-3061)
   * The uncertainty for latitude in decimal degrees.
   * Note that the longitude degrees have a different accuracy in degrees which changes with latitude and is largest at the poles.
   */
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


  @Nullable
  /**
   * Elevation in meters usually above sea level (altitude).
   * </br>
   * The elevation is calculated using the equation: (minimumElevationInMeters + maximumElevationInMeters) / 2.
   */
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

  @Nullable
  /**
   * Depth in meters below the surface. Complimentary to elevation, the depth can be 10 meters below the surface of a
   * lake in 1100m (=elevation).
   * </br>
   * The depth is calculated using the equation: (minimumDepthInMeters + maximumDepthInMeters) / 2.
   */
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

  @Nullable
  /**
   * The date the occurrence was recorded or collected.
   */
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

  @Nullable
  /**
   * The scientific name the type status of this specimen applies to.
   */
  public String getTypifiedName() {
    return typifiedName;
  }

  public void setTypifiedName(@Nullable String typifiedName) {
    this.typifiedName = typifiedName;
  }

  @NotNull
  /**
   * A set of issues found for this occurrence.
   */
  public Set<OccurrenceIssue> getIssues() {
    return issues;
  }

  public void setIssues(Set<OccurrenceIssue> issues) {
    Preconditions.checkNotNull("Issues cannot be null", issues);
    this.issues = Sets.newEnumSet(issues, OccurrenceIssue.class);
  }

  public void addIssue(OccurrenceIssue issue) {
    Preconditions.checkNotNull("Issue needs to be specified", issue);
    issues.add(issue);
  }

  @Nullable
  /**
   * The interpreted dc:modified from the verbatim source data.
   * Ideally indicating when a record was last modified in the source.
   */
  public Date getModified() {
    return modified == null ? null : new Date(modified.getTime());
  }

  public void setModified(@Nullable Date modified) {
    this.modified = modified == null ? null : new Date(modified.getTime());
  }

  @Nullable
  /**
   * The date this occurrence last went through the interpretation phase of the GBIF indexing.
   */
  public Date getLastInterpreted() {
    return lastInterpreted == null ? null : new Date(lastInterpreted.getTime());
  }

  public void setLastInterpreted(@Nullable Date lastInterpreted) {
    this.lastInterpreted = lastInterpreted == null ? null : new Date(lastInterpreted.getTime());
  }

  @Nullable
  /**
   * An external link to more details, the records "homepage".
   */
  public URI getReferences() {
    return references;
  }

  public void setReferences(URI references) {
    this.references = references;
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


  @JsonIgnore
  /**
   * Convenience method checking if any spatial validation rule has not passed.
   * Primarily used to indicate that the record should not be displayed on a map.
   */
  public boolean hasSpatialIssue() {
    for (OccurrenceIssue rule : OccurrenceIssue.GEOSPATIAL_RULES) {
      if (issues.contains(rule)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(basisOfRecord, individualCount, sex, lifeStage, establishmentMeans, taxonKey, kingdomKey, phylumKey,
        classKey, orderKey, familyKey, genusKey, subgenusKey, speciesKey, scientificName, kingdom, phylum, clazz,
        order, family, genus, subgenus, species, genericName, specificEpithet, infraspecificEpithet, taxonRank,
        dateIdentified, year, month, day, eventDate, decimalLongitude, decimalLatitude, coordinatePrecision,
        coordinateUncertaintyInMeters, elevation, elevationAccuracy, depth, depthAccuracy,
        continent, country, stateProvince, waterBody, typeStatus, typifiedName, issues, modified,
        lastInterpreted, references, identifiers, media, facts, relations, license);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Occurrence)) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    Occurrence that = (Occurrence) obj;
    return Objects.equal(this.basisOfRecord, that.basisOfRecord)
      && Objects.equal(this.individualCount, that.individualCount)
      && Objects.equal(this.sex, that.sex)
      && Objects.equal(this.lifeStage, that.lifeStage)
      && Objects.equal(this.establishmentMeans, that.establishmentMeans)
      && Objects.equal(this.taxonKey, that.taxonKey)
      && Objects.equal(this.kingdomKey, that.kingdomKey)
      && Objects.equal(this.phylumKey, that.phylumKey)
      && Objects.equal(this.classKey, that.classKey)
      && Objects.equal(this.orderKey, that.orderKey)
      && Objects.equal(this.familyKey, that.familyKey)
      && Objects.equal(this.genusKey, that.genusKey)
      && Objects.equal(this.subgenusKey, that.subgenusKey)
      && Objects.equal(this.speciesKey, that.speciesKey)
      && Objects.equal(this.scientificName, that.scientificName)
      && Objects.equal(this.kingdom, that.kingdom)
      && Objects.equal(this.phylum, that.phylum)
      && Objects.equal(this.clazz, that.clazz)
      && Objects.equal(this.order, that.order)
      && Objects.equal(this.family, that.family)
      && Objects.equal(this.genus, that.genus)
      && Objects.equal(this.subgenus, that.subgenus)
      && Objects.equal(this.species, that.species)
      && Objects.equal(this.genericName, that.genericName)
      && Objects.equal(this.specificEpithet, that.specificEpithet)
      && Objects.equal(this.infraspecificEpithet, that.infraspecificEpithet)
      && Objects.equal(this.taxonRank, that.taxonRank)
      && Objects.equal(this.dateIdentified, that.dateIdentified)
      && Objects.equal(this.year, that.year)
      && Objects.equal(this.month, that.month)
      && Objects.equal(this.day, that.day)
      && Objects.equal(this.eventDate, that.eventDate)
      && Objects.equal(this.decimalLongitude, that.decimalLongitude)
      && Objects.equal(this.decimalLatitude, that.decimalLatitude)
      && Objects.equal(this.coordinatePrecision, that.coordinatePrecision)
      && Objects.equal(this.coordinateUncertaintyInMeters, that.coordinateUncertaintyInMeters)
      && Objects.equal(this.elevation, that.elevation)
      && Objects.equal(this.elevationAccuracy, that.elevationAccuracy)
      && Objects.equal(this.depth, that.depth)
      && Objects.equal(this.depthAccuracy, that.depthAccuracy)
      && Objects.equal(this.continent, that.continent)
      && Objects.equal(this.country, that.country)
      && Objects.equal(this.stateProvince, that.stateProvince)
      && Objects.equal(this.waterBody, that.waterBody)
      && Objects.equal(this.typeStatus, that.typeStatus)
      && Objects.equal(this.typifiedName, that.typifiedName)
      && Objects.equal(this.issues, that.issues)
      && Objects.equal(this.modified, that.modified)
      && Objects.equal(this.lastInterpreted, that.lastInterpreted)
      && Objects.equal(this.references, that.references)
      && Objects.equal(this.identifiers, that.identifiers)
      && Objects.equal(this.media, that.media)
      && Objects.equal(this.facts, that.facts)
      && Objects.equal(this.relations, that.relations)
      && Objects.equal(this.license, that.license);
  }

  @Override
  public String toString() {
    return super.toString() + Objects.toStringHelper(this)
      .add("basisOfRecord", basisOfRecord)
      .add("individualCount", individualCount)
      .add("sex", sex)
      .add("lifeStage", lifeStage)
      .add("establishmentMeans", establishmentMeans)
      .add("taxonKey", taxonKey)
      .add("kingdomKey", kingdomKey)
      .add("phylumKey", phylumKey)
      .add("classKey", classKey)
      .add("orderKey", orderKey)
      .add("familyKey", familyKey)
      .add("genusKey", genusKey)
      .add("subgenusKey", subgenusKey)
      .add("speciesKey", speciesKey)
      .add("scientificName", scientificName)
      .add("kingdom", kingdom)
      .add("phylum", phylum)
      .add("clazz", clazz)
      .add("order", order)
      .add("family", family)
      .add("genus", genus)
      .add("subgenus", subgenus)
      .add("species", species)
      .add("genericName", genericName)
      .add("specificEpithet", specificEpithet)
      .add("infraspecificEpithet", infraspecificEpithet)
      .add("taxonRank", taxonRank)
      .add("dateIdentified", dateIdentified)
      .add("decimalLongitude", decimalLongitude)
      .add("decimalLatitude", decimalLatitude)
      .add("coordinatePrecision", coordinatePrecision)
      .add("coordinateUncertaintyInMeters", coordinateUncertaintyInMeters)
      .add("coordinateAccuracy", coordinateAccuracy)
      .add("elevation", elevation)
      .add("elevationAccuracy", elevationAccuracy)
      .add("depth", depth)
      .add("depthAccuracy", depthAccuracy)
      .add("continent", continent)
      .add("country", country)
      .add("stateProvince", stateProvince)
      .add("waterBody", waterBody)
      .add("year", year)
      .add("month", month)
      .add("day", day)
      .add("eventDate", eventDate)
      .add("typeStatus", typeStatus)
      .add("typifiedName", typifiedName)
      .add("issues", issues)
      .add("modified", modified)
      .add("lastInterpreted", lastInterpreted)
      .add("references", references)
      .add("license", license)
      .toString();
  }

  /**
   * This private method is only for serialization via jackson and not exposed anywhere else!
   * It maps the verbatimField terms into properties with their simple name or qualified names for UnknownTerms.
   */
  @JsonAnyGetter
  private Map<String, String> jsonVerbatimFields() {
    Map<String, String> extendedProps = Maps.newHashMap();
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
