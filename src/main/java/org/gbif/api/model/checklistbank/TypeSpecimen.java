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

import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TypeDesignationType;
import org.gbif.api.vocabulary.TypeStatus;

import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TypeSpecimen Model originally designed for both specimens and types, including type specimens, type species and type genera and simple
 * specimens unrelated to types.
 *
 * Since the initial release of the GBIF API version 1.0 ChecklistBank has been modified to only store type species or type genera.
 * All specimens including types are stored in the Occurrence store, hence the majority of properties in this class are now deprecated!
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/typesandspecimen.xml">Types And Specimen Definition</a>
 */
@SuppressWarnings("unused")
public class TypeSpecimen implements NameUsageExtension {

  private Integer taxonKey;
  private String source;
  private Integer sourceTaxonKey;
  private TypeDesignationType typeDesignationType;
  private String typeDesignatedBy;
  private String scientificName;
  private Rank taxonRank;

  @Deprecated
  private TypeStatus typeStatus;
  @Deprecated
  private String citation;
  @Deprecated
  private String verbatimLabel;
  @Deprecated
  private String locality;
  @Deprecated
  private String recordedBy;
  @Deprecated
  private String verbatimEventDate;
  @Deprecated
  private String verbatimLongitude;
  @Deprecated
  private String verbatimLatitude;
  @Deprecated
  private String occurrenceId;
  @Deprecated
  private String institutionCode;
  @Deprecated
  private String collectionCode;
  @Deprecated
  private String catalogNumber;

  /**
   * The name usage "taxon" key this description belongs to.
   */
  @Schema(description = "The name usage “taxon“ to which this type specimen belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

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
  @Schema(description = "An identifier, preferable unique, for the record within the dataset or collection.")
  @Nullable
  @Deprecated
  public String getCatalogNumber() {
    return catalogNumber;
  }

  /**
   * @param catalogNumber the catalogNumber to set.
   */
  @Deprecated
  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  /**
   * A text string citing the described specimen. Often found in taxonomic treatments and frequently based on
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
  @Schema(description = "A text string citing the described speciemn.")
  @Nullable
  @Deprecated
  public String getCitation() {
    return citation;
  }

  /**
   * @param citation the citation to set
   */
  @Deprecated
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
  @Schema(description = "The name, acronym, code or initialism identifying the collection or dataset from which the " +
    "record was derived.")
  @Nullable
  @Deprecated
  public String getCollectionCode() {
    return collectionCode;
  }

  /**
   * @param collectionCode the collectionCode to set
   */
  @Deprecated
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
  @Schema(description = "The name or acronym in use by the institution having custody of the type specimen or " +
    "information referred to in the record.")
  @Nullable
  @Deprecated
  public String getInstitutionCode() {
    return institutionCode;
  }

  /**
   * @param institutionCode the institutionCode to set
   */
  @Deprecated
  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  /**
   * The location where the specimen was collected. In case of type specimens the type locality.
   * <blockquote>
   * <p>
   * <i>Examples:</i> Iraq: Mosul: Jabal Khantur prope Sharanish N. Zakho, in fissures rupium calc., 1200 m.
   * </p>
   * </blockquote>
   *
   * @return the locality
   */
  @Schema(description = "The locality where the type specimen was collected, the type locality.")
  @Nullable
  @Deprecated
  public String getLocality() {
    return locality;
  }

  /**
   * @param locality the locality to set
   */
  @Deprecated
  public void setLocality(String locality) {
    this.locality = locality;
  }

  /**
   * An identifier for the specimen, preferably a resolvable globally unique identifier.
   *
   * @return the occurrenceId
   */
  @Schema(description = "An identifier for the specimen, preferable a resolvable, globally-unique identifier.")
  @Nullable
  @Deprecated
  public String getOccurrenceId() {
    return occurrenceId;
  }

  /**
   * @param occurrenceId the occurrenceId to set
   */
  @Deprecated
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
  @Schema(description = "The primary collector or observer, especially one who applied a personal identifier " +
    "(recordNumber), should be listed first.")
  @Nullable
  @Deprecated
  public String getRecordedBy() {
    return recordedBy;
  }

  /**
   * @param recordedBy the recordedBy to set
   */
  @Deprecated
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
  @Schema(description = "The scientific name originally used for the specimen, species or genus.\n\n" +
    "Not necessarily the same as the currently recognized name.  For type species this is the species name.")
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
  @Schema(description = "Bibliographic citation referencing a source for the type specimen.")
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

  @Schema(description = "The name usage key of the taxon in the checklist including this type specimen.")
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
   * The rank of the taxon bearing the scientific name.
   *
   * @return the taxonRank
   */
  @Schema(description = "The rank of the taxon bearing the scientific name.")
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
  @Schema(description = "The citation of the publication where the type designation is found.")
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
  @Schema(description = "The reason why this specimen is designated as a type.")
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
  @Schema(description = "The type status of the specimen, not used for type species or type genera.")
  @Nullable
  @Deprecated
  public TypeStatus getTypeStatus() {
    return typeStatus;
  }

  /**
   * @param typeStatus the typeStatus to set
   */
  @Deprecated
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
  @Schema(description = "The date when the specimen was collected.")
  @Nullable
  @Deprecated
  public String getVerbatimEventDate() {
    return verbatimEventDate;
  }

  /**
   * @param verbatimEventDate the verbatimEventDate to set
   */
  @Deprecated
  public void setVerbatimEventDate(String verbatimEventDate) {
    this.verbatimEventDate = verbatimEventDate;
  }

  /**
   * The full, verbatim text from the specimen label.
   *
   * @return the verbatimLabel
   */
  @Schema(description = "The full, verbatim text from the specimen label.")
  @Nullable
  @Deprecated
  public String getVerbatimLabel() {
    return verbatimLabel;
  }

  /**
   * @param verbatimLabel the verbatimLabel to set
   */
  @Deprecated
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
  @Schema(description = "The geographic latitude.")
  @Nullable
  @Deprecated
  public String getVerbatimLatitude() {
    return verbatimLatitude;
  }

  /**
   * @param verbatimLatitude the verbatimLatitude to set
   */
  @Deprecated
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
  @Schema(description = "The geographic longitude.")
  @Nullable
  @Deprecated
  public String getVerbatimLongitude() {
    return verbatimLongitude;
  }

  /**
   * @param verbatimLongitude the verbatimLongitude to set
   */
  @Deprecated
  public void setVerbatimLongitude(String verbatimLongitude) {
    this.verbatimLongitude = verbatimLongitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TypeSpecimen that = (TypeSpecimen) o;
    return Objects.equals(source, that.source) &&
      Objects.equals(sourceTaxonKey, that.sourceTaxonKey) &&
      typeDesignationType == that.typeDesignationType &&
      Objects.equals(typeDesignatedBy, that.typeDesignatedBy) &&
      Objects.equals(scientificName, that.scientificName) &&
      taxonRank == that.taxonRank &&
      typeStatus == that.typeStatus &&
      Objects.equals(citation, that.citation) &&
      Objects.equals(verbatimLabel, that.verbatimLabel) &&
      Objects.equals(locality, that.locality) &&
      Objects.equals(recordedBy, that.recordedBy) &&
      Objects.equals(verbatimEventDate, that.verbatimEventDate) &&
      Objects.equals(verbatimLongitude, that.verbatimLongitude) &&
      Objects.equals(verbatimLatitude, that.verbatimLatitude) &&
      Objects.equals(occurrenceId, that.occurrenceId) &&
      Objects.equals(institutionCode, that.institutionCode) &&
      Objects.equals(collectionCode, that.collectionCode) &&
      Objects.equals(catalogNumber, that.catalogNumber);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(source, sourceTaxonKey, typeDesignationType, typeDesignatedBy, scientificName,
        taxonRank, typeStatus, citation, verbatimLabel, locality, recordedBy, verbatimEventDate,
        verbatimLongitude, verbatimLatitude, occurrenceId, institutionCode, collectionCode,
        catalogNumber);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TypeSpecimen.class.getSimpleName() + "[", "]")
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("typeDesignationType=" + typeDesignationType)
      .add("typeDesignatedBy='" + typeDesignatedBy + "'")
      .add("scientificName='" + scientificName + "'")
      .add("taxonRank=" + taxonRank)
      .toString();
  }
}
