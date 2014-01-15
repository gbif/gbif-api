/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.OccurrenceValidationRule;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.Sex;
import org.gbif.api.vocabulary.TypeStatus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.codehaus.jackson.annotate.JsonIgnore;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents an Occurrence as interpreted by GBIF, adding typed properties on top of the verbatim ones.
 */
public class Occurrence extends VerbatimOccurrence implements LinneanClassification, LinneanClassificationKeys {

  // occurrence fields
  private BasisOfRecord basisOfRecord;
  private Integer individualCount;
  private Sex sex;
  private LifeStage lifeStage;
  private EstablishmentMeans establishmentMeans;
  // taxonomy as nub keys -> LinneanClassificationKeys
  private Integer taxonKey;   // taxonID?
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
  private String clazz;
  private String order;
  private String family;
  private String genus;
  private String subgenus;
  private String species;
  // identification
  private Date identificationDate;
  // location
  private Double longitude;
  private Double latitude;
  private Double coordinateAccurracy;
  private String geodeticDatum;
  private Integer altitude;  // min/max elevationInMeters?
  private Integer altitudeAccurracy;  // new term
  private Integer depth;  // min/max depthInMeters?
  private Integer depthAccurracy;  // new term
  private Continent continent;
  private Country country;
  private String stateProvince;
  private String waterBody;
  // recording event
  private Integer year;
  private Integer month;
  private Integer day;
  private Date eventDate;
  // Types  See ABCD: http://www.bgbm.org/TDWG/CODATA/Schema/ABCD_2.06/HTML/ABCD_2.06.html#element_NomenclaturalTypeDesignations_Link02052C80
  private TypeStatus typeStatus;
  // extracted from type status, but we should propose a new dwc term for this!
  // for example: "Paratype of Taeniopteryx metequi Ricker & Ross" is status=Paratype, typifiedName=Taeniopteryx metequi Ricker & Ross
  private String typifiedName;
  // validation rules: true means rule has passed (no error), false means rule failed (error), null means not evaluated
  private Map<OccurrenceValidationRule, Boolean> validations = Maps.newHashMap();
  // record level
  private Date modified;  // interpreted dc:modified, i.e. date changed in source
  private Date lastInterpreted;

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
    return taxonKey == null ? ClassificationUtils.getHigherClassificationMap(
      this) : ClassificationUtils.getHigherClassificationMap(this, taxonKey, null, null);
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
  public Date getIdentificationDate() {
    return identificationDate == null ? null : new Date(identificationDate.getTime());
  }

  public void setIdentificationDate(@Nullable Date identificationDate) {
    this.identificationDate = identificationDate == null ? null : new Date(identificationDate.getTime());
  }

  @Nullable
  /**
   * The longitude in decimal degrees always for the WGS84 datum. If a different geodetic datum was given the verbatim
   * coordinates are transformed into WGS84 values.
   */
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(@Nullable Double longitude) {
    this.longitude = longitude;
  }

  @Nullable
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(@Nullable Double latitude) {
    this.latitude = latitude;
  }

  @Nullable
  /**
   * The uncertainty radius for lat/lon in decimal degrees.
   */
  public Double getCoordinateAccurracy() {
    return coordinateAccurracy;
  }

  public void setCoordinateAccurracy(@Nullable Double coordinateAccurracy) {
    this.coordinateAccurracy = coordinateAccurracy;
  }

  @Nullable
  /**
   * The geodetic datum for the original verbatim coordinates.
   */
  public String getGeodeticDatum() {
    return geodeticDatum;
  }

  public void setGeodeticDatum(@Nullable String geodeticDatum) {
    this.geodeticDatum = geodeticDatum;
  }

  @Nullable
  /**
   * Altitude in meters above sea level.
   */
  public Integer getAltitude() {
    return altitude;
  }

  public void setAltitude(@Nullable Integer altitude) {
    this.altitude = altitude;
  }

  @Nullable
  public Integer getAltitudeAccurracy() {
    return altitudeAccurracy;
  }

  public void setAltitudeAccurracy(@Nullable Integer altitudeAccurracy) {
    this.altitudeAccurracy = altitudeAccurracy;
  }

  @Nullable
  /**
   * Depth in meters below the surface. Complimentary to altitude, the depth can be 10 meters
   * below the surface of a lake in 1100m (=altitude).
   */
  public Integer getDepth() {
    return depth;
  }

  public void setDepth(@Nullable Integer depth) {
    this.depth = depth;
  }

  @Nullable
  public Integer getDepthAccurracy() {
    return depthAccurracy;
  }

  public void setDepthAccurracy(@Nullable Integer depthAccurracy) {
    this.depthAccurracy = depthAccurracy;
  }

  @Nullable
  public Continent getContinent() {
    return continent;
  }

  public void setContinent(@Nullable Continent continent) {
    this.continent = continent;
  }

  @Nullable
  public Country getCountry() {
    return country;
  }

  public void setCountry(@Nullable Country country) {
    this.country = country;
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

  @Nullable
  public Integer getYear() {
    return year;
  }

  public void setYear(@Nullable Integer year) {
    this.year = year;
  }

  @Nullable
  public Integer getMonth() {
    return month;
  }

  public void setMonth(@Nullable Integer month) {
    this.month = month;
  }

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
   * A map of validation rules assessed for this occurrence.
   * All rules passed successfully should result in a true and all false rules are indicating issues.
   * Note that this is not copied defensively.
   */
  public Map<OccurrenceValidationRule, Boolean> getValidations() {
    return validations;
  }

  public void setValidations(Map<OccurrenceValidationRule, Boolean> validations) {
    checkNotNull(validations, "validations can't be null");
    this.validations = validations;
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

  @JsonIgnore
  /**
   * Convenience method checking if any spatial validation rule has not passed.
   */
  public boolean hasSpatialIssue() {
    for (OccurrenceValidationRule rule : OccurrenceValidationRule.GEOSPATIAL_RULES) {
      if (validations.get(rule) == null || !validations.get(rule)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(basisOfRecord, individualCount, sex, lifeStage, establishmentMeans, taxonKey, kingdomKey, phylumKey,
        classKey, orderKey, familyKey, genusKey, subgenusKey, speciesKey, scientificName, kingdom, phylum, clazz, order,
        family, genus, subgenus, species, identificationDate, year, month, day, eventDate, longitude, latitude,
        coordinateAccurracy, geodeticDatum, altitude, altitudeAccurracy, depth, depthAccurracy, continent, country,
        stateProvince, waterBody, typeStatus, typifiedName, validations, modified, lastInterpreted);
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
    return Objects.equal(this.basisOfRecord, that.basisOfRecord) && Objects
      .equal(this.individualCount, that.individualCount) && Objects.equal(this.sex, that.sex) && Objects
             .equal(this.lifeStage, that.lifeStage) && Objects.equal(this.establishmentMeans, that.establishmentMeans)
           && Objects.equal(this.taxonKey, that.taxonKey) && Objects.equal(this.kingdomKey, that.kingdomKey) && Objects
      .equal(this.phylumKey, that.phylumKey) && Objects.equal(this.classKey, that.classKey) && Objects
             .equal(this.orderKey, that.orderKey) && Objects.equal(this.familyKey, that.familyKey) && Objects
             .equal(this.genusKey, that.genusKey) && Objects.equal(this.subgenusKey, that.subgenusKey) && Objects
             .equal(this.speciesKey, that.speciesKey) && Objects.equal(this.scientificName, that.scientificName)
           && Objects.equal(this.kingdom, that.kingdom) && Objects.equal(this.phylum, that.phylum) && Objects
      .equal(this.clazz, that.clazz) && Objects.equal(this.order, that.order) && Objects.equal(this.family, that.family)
           && Objects.equal(this.genus, that.genus) && Objects.equal(this.subgenus, that.subgenus) && Objects
      .equal(this.species, that.species) && Objects.equal(this.identificationDate, that.identificationDate) && Objects
             .equal(this.year, that.year) && Objects.equal(this.month, that.month) && Objects.equal(this.day, that.day)
           && Objects.equal(this.eventDate, that.eventDate) && Objects.equal(this.longitude, that.longitude) && Objects
      .equal(this.latitude, that.latitude) && Objects.equal(this.coordinateAccurracy, that.coordinateAccurracy)
           && Objects.equal(this.geodeticDatum, that.geodeticDatum) && Objects.equal(this.altitude, that.altitude)
           && Objects.equal(this.altitudeAccurracy, that.altitudeAccurracy) && Objects.equal(this.depth, that.depth)
           && Objects.equal(this.depthAccurracy, that.depthAccurracy) && Objects.equal(this.continent, that.continent)
           && Objects.equal(this.country, that.country) && Objects.equal(this.stateProvince, that.stateProvince)
           && Objects.equal(this.waterBody, that.waterBody) && Objects.equal(this.typeStatus, that.typeStatus)
           && Objects.equal(this.typifiedName, that.typifiedName) && Objects.equal(this.validations, that.validations)
           && Objects.equal(this.modified, that.modified) && Objects.equal(this.lastInterpreted, that.lastInterpreted);
  }

}
