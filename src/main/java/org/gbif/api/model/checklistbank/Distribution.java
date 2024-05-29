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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.CitesAppendix;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DistributionStatus;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.ThreatStatus;

import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Distribution Model Object represents a species distribution.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/distribution.xml">Distribution Definition</a>
 */
@SuppressWarnings("unused")
public class Distribution implements NameUsageExtension {

  private Integer taxonKey;
  private String locationId;
  private String locality;
  private Country country;
  private DistributionStatus status;
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
   * The name usage "taxon" key this description belongs to.
   */
  @Schema(description = "The name usage “taxon“ key to which this species profile belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  /**
   * The CITES (Convention on International Trade in Endangered Species of Wild Fauna and Flora) Appendix number under which the
   * taxon is listed. It is possible to have different appendix numbers for different areas, but "global" as an area is
   * also valid if it's the same worldwide see also http://www.cites.org/eng/app/index.shtml
   * <blockquote>
   * <p>
   * <i>Example:</i> II
   * </p>
   * </blockquote>
   *
   * @return the appendixCites.
   */
  @Schema(description = "The CITES (Convention on International Trade in Endangered Species of Wild Fauna and Flora) " +
    "Appendix number under which the taxon is listed.\n\n" +
    "It is possible to have different appendix numbers for different areas, but “global” as an area is also valid.")
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
  @Schema(description = "The country")
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
  @Schema(description = "Seasonal temporal subcontext within the eventDate context. " +
    "The latest ordinal day of the year on which the distribution record is valid.")
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
  @Schema(description = "Term describing whether the organism occurs natively, is introduced or cultivated.")
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
  @Schema(description = "The distribution information pertains solely to a specific life stage of the taxon.")
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
  @Schema(description = "The verbatim name of the area this distribution record is about.")
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
  @Schema(description = " A code for the named area this distributon record is about.")
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
  @Schema(description = "Additional notes on the distribution.")
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
  @Schema(description = "Bibliographic citation referencing a source for the distribution.")
  @Nullable
  @Override
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  @Override
  public void setSource(String source) {
    this.source = source;
  }

  @Schema(description = "The name usage key of the taxon in the checklist including this distribution.")
  @Nullable
  @Override
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  @Override
  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  /**
   * Seasonal temporal subcontext within the eventDate context. Useful for migratory species. The earliest ordinal day
   * of the year on which the distribution record is valid. Numbering starts with 1 for 1 January and ends with 365 or 366
   * for 31 December.
   * <blockquote>
   * <p>
   * <i>Example:</i> 90
   * </p>
   * </blockquote>
   *
   * @return the startDayOfYear.
   */
  @Schema(description = "Seasonal temporal subcontext within the eventDate context. Useful for migratory species.\n\n" +
    "The earliest ordinal day of the year on which the distribution record is valid. Numbering starts with 1 for " +
    "1 January and ends with 365 or 366 for 31 December.")
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
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/distribution_status_2020-05-14.xml">occurrence status vocabulary</a> for recommended values
   */
  @Schema(description = "Term describing the occurrence status of the organism in the given area based on how frequent the species occurs.")
  @Nullable
  public DistributionStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(DistributionStatus status) {
    this.status = status;
  }

  /**
   * Relevant temporal context for this entire distribution record including all properties preferably given as a year
   * range or single year on which the distribution record is valid. For the same area and taxon there could therefore
   * be several records with different temporal context, e.g. in 5-year intervals for invasive species.
   * <blockquote>
   *   <p><i>Example:</i> "1930"; "1939-1945"</p>
   * </blockquote>
   * @return the temporal
   */
  @Schema(description = "Relevant temporal context for this entire distribution record including all properties, " +
    "preferably given as a year range or single year on which the distribution record is valid.\n\n" +
    "For the same area and taxon there could therefore be several records with different temporal context, " +
    "e.g. in 5-year intervals for invasive species.")
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
  @Schema(description = "Threat status of a species as defined by IUCN.")
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Distribution that = (Distribution) o;
    return Objects.equals(taxonKey, that.taxonKey) &&
      Objects.equals(locationId, that.locationId) &&
      Objects.equals(locality, that.locality) &&
      country == that.country &&
      status == that.status &&
      lifeStage == that.lifeStage &&
      Objects.equals(temporal, that.temporal) &&
      threatStatus == that.threatStatus &&
      establishmentMeans == that.establishmentMeans &&
      appendixCites == that.appendixCites &&
      Objects.equals(source, that.source) &&
      Objects.equals(sourceTaxonKey, that.sourceTaxonKey) &&
      Objects.equals(startDayOfYear, that.startDayOfYear) &&
      Objects.equals(endDayOfYear, that.endDayOfYear);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(taxonKey, locationId, locality, country, status, lifeStage, temporal, threatStatus,
        establishmentMeans, appendixCites, source, sourceTaxonKey, startDayOfYear, endDayOfYear);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Distribution.class.getSimpleName() + "[", "]")
      .add("taxonKey=" + taxonKey)
      .add("locationId='" + locationId + "'")
      .add("locality='" + locality + "'")
      .add("country=" + country)
      .add("status=" + status)
      .add("lifeStage=" + lifeStage)
      .add("temporal='" + temporal + "'")
      .add("threatStatus=" + threatStatus)
      .add("establishmentMeans=" + establishmentMeans)
      .add("appendixCites=" + appendixCites)
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("startDayOfYear=" + startDayOfYear)
      .add("endDayOfYear=" + endDayOfYear)
      .add("remarks='" + remarks + "'")
      .toString();
  }
}
