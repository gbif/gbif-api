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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TypeDesignationType;
import org.gbif.api.vocabulary.TypeStatus;

import javax.annotation.Nullable;

import com.google.common.base.Objects;


/**
 * TypeSpecimen Model for specimens and types, including type specimens, type species and type genera and simple
 * specimens unrelated to types.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/typesandspecimen.xml">Types And Specimen Definition</a>
 */
public class TypeSpecimen extends NameUsageComponent {

  private String source;
  private String citation;
  private TypeStatus typeStatus;
  private TypeDesignationType typeDesignationType;
  private String typeDesignatedBy;
  private String scientificName;
  private String verbatimLabel;
  private String locality;
  private String recordedBy;
  private String verbatimEventDate;
  private String verbatimLongitude;
  private String verbatimLatitude;
  private String occurrenceId;
  private Rank taxonRank;
  // TODO: consider if we can collapse the next 3 properties into the occurrenceId - do we ever need them atomized?
  private String institutionCode;
  private String collectionCode;
  private String catalogNumber;

  /**
   * An identifier (preferably unique) for the record within the data set or collection.
   * <blockquote>
   * <p>
   * <i>Examples:</i> "2008.1334", "145732a", "145732".
   * </p>
   * </blockquote>
   *
   * @return the catalogNumber
   */
  @Nullable
  public String getCatalogNumber() {
    return catalogNumber;
  }

  /**
   * @param catalogNumber the catalogNumber to set.
   */
  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  /**
   * A text string citating the described specimen. Often found in taxonomic treatments and frequently based on
   * institution code and catalog number.
   * <blockquote>
   * <p>
   * <i>Examples:</i> Iraq: Mosul: Jabal Khantur prope Sharanish N. Zakho, in fissures rupium calc., 1200 m, Rech.
   * 12083. (W!).
   * </p>
   * </blockquote>
   *
   * @return the citation
   */
  @Nullable
  public String getCitation() {
    return citation;
  }

  /**
   * @param citation the citation to set
   */
  public void setCitation(String citation) {
    this.citation = citation;
  }

  /**
   * The name, acronym, coden, or initialism identifying the collection or data set from which the record was derived.
   * <blockquote>
   * <p>
   * <i>Examples:</i> "Mammals", "Hildebrandt", "eBird".
   * </p>
   * </blockquote>
   *
   * @return the collectionCode
   */
  @Nullable
  public String getCollectionCode() {
    return collectionCode;
  }

  /**
   * @param collectionCode the collectionCode to set
   */
  public void setCollectionCode(String collectionCode) {
    this.collectionCode = collectionCode;
  }

  /**
   * The name (or acronym) in use by the institution having custody of the object(s) or information referred to in the
   * record.
   * <blockquote>
   * <p>
   * <i>Examples:</i> "MVZ", "FMNH", "AKN-CLO", "University of California Museum of Paleontology (UCMP)".
   * </p>
   * </blockquote>
   *
   * @return the institutionCode
   */
  @Nullable
  public String getInstitutionCode() {
    return institutionCode;
  }

  /**
   * @param institutionCode the institutionCode to set
   */
  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  /**
   * The location where the the specimen was collected. In case of type specimens the type locality.
   * <blockquote>
   * <p>
   * <i>Examples:</i> Iraq: Mosul: Jabal Khantur prope Sharanish N. Zakho, in fissures rupium calc., 1200 m.
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
   * An identifier for the specimen, preferably a resolvable globally unique identifier.
   *
   * @return the occurrenceId
   */
  @Nullable
  public String getOccurrenceId() {
    return occurrenceId;
  }

  /**
   * @param occurrenceId the occurrenceId to set
   */
  public void setOccurrenceId(String occurrenceId) {
    this.occurrenceId = occurrenceId;
  }

  /**
   * The primary collector or observer, especially one who applies a personal identifier (recordNumber), should be
   * listed first.
   * <blockquote>
   * <p>
   * <i>Examples:</i> KH Rechinger.
   * </p>
   * </blockquote>
   *
   * @return the recordedBy
   */
  @Nullable
  public String getRecordedBy() {
    return recordedBy;
  }

  /**
   * @param recordedBy the recordedBy to set
   */
  public void setRecordedBy(String recordedBy) {
    this.recordedBy = recordedBy;
  }

  /**
   * The scientific name originally used for the specimen, species or genus. Not necessarily the same as the currently
   * recognized name. For type species this is the species name.
   * <blockquote>
   * <p>
   * <i>Examples:</i> "Ctenomys sociabilis" - "Roptrocerus typographi (Györfi, 1952)".
   * </p>
   * </blockquote>
   *
   * @return the scientificName
   */
  @Nullable
  public String getScientificName() {
    return scientificName;
  }

  /**
   * @param scientificName the scientificName to set
   */
  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * The source.
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

  /**
   * The rank of the taxon bearing the scientific name.
   *
   * @return the taxonRank
   */
  public Rank getTaxonRank() {
    return taxonRank;
  }

  /**
   * @param taxonRank the taxonRank to set
   */
  public void setTaxonRank(Rank taxonRank) {
    this.taxonRank = taxonRank;
  }

  /**
   * The citation of the publication where the type designation is found.
   * <blockquote>
   * <p>
   * <i>Examples:</i> Vachal, J. (1897) Éclaircissements sur de genre Scrapter et description d’une espéce nouvelle de
   * Dufourea. Bulletin de la Société Entomologique de France, 1897, 61–64.
   * </p>
   * </blockquote>
   *
   * @return the typeDesignatedBy
   */
  @Nullable
  public String getTypeDesignatedBy() {
    return typeDesignatedBy;
  }

  /**
   * @param typeDesignatedBy the typeDesignatedBy to set
   */
  public void setTypeDesignatedBy(String typeDesignatedBy) {
    this.typeDesignatedBy = typeDesignatedBy;
  }

  /**
   * The reason why this specimen or name is designated as a type.
   * <blockquote>
   * <p>
   * <i>Examples:</i> monotypy, original designation, tautonomy.
   * </p>
   * </blockquote>
   *
   * @return the typeDesignationType
   */
  @Nullable
  public TypeDesignationType getTypeDesignationType() {
    return typeDesignationType;
  }

  /**
   * @param typeDesignationType the typeDesignationType to set
   */
  public void setTypeDesignationType(TypeDesignationType typeDesignationType) {
    this.typeDesignationType = typeDesignationType;
  }

  /**
   * The type status of the specimen, not used for type species or type genus.
   * <blockquote>
   * <p>
   * <i>Examples:</i> holotype, syntype, lectotype, type species.
   * </p>
   * </blockquote>
   *
   * @return the typeStatus
   */
  @Nullable
  public TypeStatus getTypeStatus() {
    return typeStatus;
  }

  /**
   * @param typeStatus the typeStatus to set
   */
  public void setTypeStatus(TypeStatus typeStatus) {
    this.typeStatus = typeStatus;
  }

  /**
   * The date when the specimen was collected.
   * <blockquote>
   * <p>
   * <i>Examples:</i> "spring 1910", "Marzo 2002", "1999-03-XX", "17IV1934".
   * </p>
   * </blockquote>
   *
   * @return the verbatimEventDate
   */
  @Nullable
  public String getVerbatimEventDate() {
    return verbatimEventDate;
  }

  /**
   * @param verbatimEventDate the verbatimEventDate to set
   */
  public void setVerbatimEventDate(String verbatimEventDate) {
    this.verbatimEventDate = verbatimEventDate;
  }

  /**
   * The full, verbatim text from the specimen label.
   *
   * @return the verbatimLabel
   */
  @Nullable
  public String getVerbatimLabel() {
    return verbatimLabel;
  }

  /**
   * @param verbatimLabel the verbatimLabel to set
   */
  public void setVerbatimLabel(String verbatimLabel) {
    this.verbatimLabel = verbatimLabel;
  }

  /**
   * The geographic latitude.
   * <blockquote>
   * <p>
   * <i>Examples:</i> 41 05 54.03 S.
   * </p>
   * </blockquote>
   *
   * @return the verbatimLatitude
   */
  @Nullable
  public String getVerbatimLatitude() {
    return verbatimLatitude;
  }

  /**
   * @param verbatimLatitude the verbatimLatitude to set
   */
  public void setVerbatimLatitude(String verbatimLatitude) {
    this.verbatimLatitude = verbatimLatitude;
  }

  /**
   * The geographic longitude.
   * <blockquote>
   * <p>
   * <i>Examples:</i> 121d 10' 34 W.
   * </p>
   * </blockquote>
   *
   * @return the verbatimLongitude
   */
  @Nullable
  public String getVerbatimLongitude() {
    return verbatimLongitude;
  }

  /**
   * @param verbatimLongitude the verbatimLongitude to set
   */
  public void setVerbatimLongitude(String verbatimLongitude) {
    this.verbatimLongitude = verbatimLongitude;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof TypeSpecimen)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    TypeSpecimen that = (TypeSpecimen) object;
    return Objects.equal(this.source, that.source)
           && Objects.equal(this.citation, that.citation)
           && Objects.equal(this.typeStatus, that.typeStatus)
           && Objects.equal(this.typeDesignationType, that.typeDesignationType)
           && Objects.equal(this.typeDesignatedBy, that.typeDesignatedBy)
           && Objects.equal(this.scientificName, that.scientificName)
           && Objects.equal(this.verbatimLabel, that.verbatimLabel)
           && Objects.equal(this.locality, that.locality)
           && Objects.equal(this.recordedBy, that.recordedBy)
           && Objects.equal(this.verbatimEventDate, that.verbatimEventDate)
           && Objects.equal(this.verbatimLongitude, that.verbatimLongitude)
           && Objects.equal(this.verbatimLatitude, that.verbatimLatitude)
           && Objects.equal(this.occurrenceId, that.occurrenceId)
           && Objects.equal(this.taxonRank, that.taxonRank)
           && Objects.equal(this.institutionCode, that.institutionCode)
           && Objects.equal(this.collectionCode, that.collectionCode)
           && Objects.equal(this.catalogNumber, that.catalogNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(),
                            source,
                            citation,
                            typeStatus,
                            typeDesignationType,
                            typeDesignatedBy,
                            scientificName,
                            verbatimLabel,
                            locality,
                            recordedBy,
                            verbatimEventDate,
                            verbatimLongitude,
                            verbatimLatitude,
                            occurrenceId,
                            taxonRank,
                            institutionCode,
                            collectionCode,
                            catalogNumber);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("super", super.toString())
      .add("source", source)
      .add("citation", citation)
      .add("typeStatus", typeStatus)
      .add("typeDesignationType", typeDesignationType)
      .add("typeDesignatedBy", typeDesignatedBy)
      .add("scientificName", scientificName)
      .add("verbatimLabel", verbatimLabel)
      .add("locality", locality)
      .add("recordedBy", recordedBy)
      .add("verbatimEventDate", verbatimEventDate)
      .add("verbatimLongitude", verbatimLongitude)
      .add("verbatimLatitude", verbatimLatitude)
      .add("occurrenceId", occurrenceId)
      .add("taxonRank", taxonRank)
      .add("institutionCode", institutionCode)
      .add("collectionCode", collectionCode)
      .add("catalogNumber", catalogNumber)
      .toString();
  }

}
