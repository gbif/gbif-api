/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.CitesAppendix;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.OccurrenceStatus;
import org.gbif.api.vocabulary.ThreatStatus;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.google.common.base.Objects;

/**
 * Distribution Model Object represents a species distribution.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/distribution.xml">Distribution Definition</a>
 */
public class Distribution implements NameUsageExtension {

  private String locationId;
  private String locality;
  private Country country;
  private OccurrenceStatus status;
  private LifeStage lifeStage;
  private String temporal;
  private ThreatStatus threatStatus;
  private EstablishmentMeans establishmentMeans;
  private CitesAppendix appendixCites;
  private String source;
  private Integer sourceTaxonKey;
  private Integer startDayOfYear;
  private Integer endDayOfYear;
  private String remarks;

  /**
   * The CITES (Convention on International Trade in Endangered Species of Wild Fauna and Flora) Appendix number the
   * taxa is listed. It is possible to have different appendix numbers for different areas, but "global" as an area is
   * also valid if its the same worldwide see also http://www.cites.org/eng/app/index.shtml
   * <blockquote>
   * <p>
   * <i>Example:</i> II
   * </p>
   * </blockquote>
   *
   * @return the appendixCites.
   */
  @Nullable
  public CitesAppendix getAppendixCites() {
    return appendixCites;
  }

  /**
   * @param appendixCites the appendixCites to set
   */
  public void setAppendixCites(CitesAppendix appendixCites) {
    this.appendixCites = appendixCites;
  }

  /**
   * @return the country
   */
  @Nullable
  public Country getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * Seasonal temporal subcontext within the eventDate context. The latest ordinal day of the year on which the
   * distribution record is valid.
   * <blockquote>
   * <p>
   * <i>Example:</i> 120
   * </p>
   * </blockquote>
   *
   * @return the endDayOfYear.
   */
  @Nullable
  @Min(1)
  @Max(366)
  public Integer getEndDayOfYear() {
    return endDayOfYear;
  }

  /**
   * @param endDayOfYear the endDayOfYear to set
   */
  public void setEndDayOfYear(Integer endDayOfYear) {
    this.endDayOfYear = endDayOfYear;
  }

  /**
   * Term describing whether the organism occurs natively, is introduced or cultivated.
   * <blockquote>
   * <p>
   * <i>Example:</i> introduced
   * </p>
   * </blockquote>
   *
   * @return the establishmentMeans
   */
  @Nullable
  public EstablishmentMeans getEstablishmentMeans() {
    return establishmentMeans;
  }

  /**
   * @param establishmentMeans the establishmentMeans to set
   */
  public void setEstablishmentMeans(EstablishmentMeans establishmentMeans) {
    this.establishmentMeans = establishmentMeans;
  }

  /**
   * The distribution information pertains solely to a specific life stage of the taxon.
   * <blockquote>
   * <p>
   * <i>Example:</i> adult
   * </p>
   * </blockquote>
   *
   * @return the lifeStage
   *
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/life_stage.xml">life stage vocabulary for recommended values</a>
   */
  @Nullable
  public LifeStage getLifeStage() {
    return lifeStage;
  }

  /**
   * @param lifeStage the lifeStage to set
   */
  public void setLifeStage(LifeStage lifeStage) {
    this.lifeStage = lifeStage;
  }

  /**
   * The verbatim name of the area this distribution record is about.
   * <blockquote>
   * <p>
   * <i>Example:</i> Patagonia
   * </p>
   * </blockquote>
   *
   * @return the locality
   */
  @Nullable
  public String getLocality() {
    return locality;
  }

  /**
   * @param locality the locality to set
   */
  public void setLocality(String locality) {
    this.locality = locality;
  }

  /**
   * A code for the named area this distributon record is about.
   * See http://en.wikipedia.org/wiki/ISO_3166-2 for state codes within a country,
   * otherwise use a prefix for each code to indicate the source of the code.
   * <p/>
   * <blockqoute>
   * <p>
   * <i>Example:</i> TDWG:AGS-TF <i>Example:</i> ISO3166:AR <i>Example:</i> WOEID:564721
   * </p>
   * </blockquote>
   *
   * @return the location id
   */
  public String getLocationId() {
    return locationId;
  }

  /**
   * @param locationId the locationId to set
   */
  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  /**
   * Additional notes on the distribution.
   *
   * @return the notes
   */
  @Nullable
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * Source reference for this distribution record. Can be proper publication citation, a webpage URL, etc.
   * <blockquote>
   * <p>
   * <i>Example:</i> "Euro+Med Plantbase - the information resource for Euro-Mediterranean plant diversity (2006-).
   * Published on the Internet http://ww2.bgbm.org/EuroPlusMed/ July, 2009"
   * </p>
   * </blockquote>
   *
   * @return the source
   */
  @Nullable
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

  @Nullable
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  /**
   * Seasonal temporal subcontext within the eventDate context. Useful for migratory species. The earliest ordinal day
   * of the year on which the distribution record is valid. Numbering starts with 1 for January 1 and ends with 365 for
   * December 31.
   * <blockquote>
   * <p>
   * <i>Example:</i> 90
   * </p>
   * </blockquote>
   *
   * @return the startDayOfYear.
   */
  @Nullable
  @Min(1)
  @Max(366)
  public Integer getStartDayOfYear() {
    return startDayOfYear;
  }

  /**
   * @param startDayOfYear the startDayOfYear to set
   */
  public void setStartDayOfYear(Integer startDayOfYear) {
    this.startDayOfYear = startDayOfYear;
  }

  /**
   * Term describing the occurrence status of the organism in the given area based on how frequent the species occurs.
   * <blockquote>
   * <p>
   * <i>Example:</i> absent
   * </p>
   * </blockquote>
   *
   * @return the status.
   *
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/occurrence_status.xml">occurrence status vocabulary for
   *      recommended
   *      values</a>
   */
  @Nullable
  public OccurrenceStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(OccurrenceStatus status) {
    this.status = status;
  }

  /**
   * @return the temporal
   */
  @Nullable
  public String getTemporal() {
    return temporal;
  }

  /**
   * @param temporal the temporal to set
   */
  public void setTemporal(String temporal) {
    this.temporal = temporal;
  }

  /**
   * Threat status of a species as defined by IUCN
   * (http://www.iucnredlist.org/static/categories_criteria_3_1#categories).
   * <blockquote>
   * <p>
   * <i>Examples:</i> "EX" - "EW" - "CR"
   * </p>
   * </blockquote>
   *
   * @return the threatStatus.
   *
   * @see <a href="http://rs.gbif.org/vocabulary/iucn/threat_status.xml">threat status vocabulary for recommended
   *      values</a>
   */
  @Nullable
  public ThreatStatus getThreatStatus() {
    return threatStatus;
  }

  /**
   * @param threatStatus the threatStatus to set
   */
  public void setThreatStatus(ThreatStatus threatStatus) {
    this.threatStatus = threatStatus;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Distribution)) {
      return false;
    }

    Distribution that = (Distribution) object;
    return Objects.equal(this.locality, that.locality)
           && Objects.equal(this.country, that.country)
           && Objects.equal(this.status, that.status)
           && Objects.equal(this.lifeStage, that.lifeStage)
           && Objects.equal(this.temporal, that.temporal)
           && Objects.equal(this.threatStatus, that.threatStatus)
           && Objects.equal(this.establishmentMeans, that.establishmentMeans)
           && Objects.equal(this.appendixCites, that.appendixCites)
           && Objects.equal(this.source, that.source)
           && Objects.equal(this.sourceTaxonKey, that.sourceTaxonKey)
           && Objects.equal(this.startDayOfYear, that.startDayOfYear)
           && Objects.equal(this.endDayOfYear, that.endDayOfYear);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(locality,
                            country,
                            status,
                            lifeStage,
                            temporal,
                            threatStatus,
                            establishmentMeans,
                            appendixCites,
                            source,
                            startDayOfYear,
                            endDayOfYear);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("locality", locality)
      .add("country", country)
      .add("status", status)
      .add("lifeStage", lifeStage)
      .add("temporal", temporal)
      .add("threatStatus", threatStatus)
      .add("establishmentMeans", establishmentMeans)
      .add("appendixCites", appendixCites)
      .add("source", source)
      .add("sourceTaxonKey", sourceTaxonKey)
      .add("startDayOfYear", startDayOfYear)
      .add("endDayOfYear", endDayOfYear)
      .add("locationId", locationId)
      .add("remarks", remarks)
      .toString();
  }

}
