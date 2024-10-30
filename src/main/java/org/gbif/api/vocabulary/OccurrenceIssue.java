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
package org.gbif.api.vocabulary;

import static org.gbif.api.vocabulary.InterpretationRemarkSeverity.ERROR;
import static org.gbif.api.vocabulary.InterpretationRemarkSeverity.INFO;
import static org.gbif.api.vocabulary.InterpretationRemarkSeverity.WARNING;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import org.gbif.api.util.AnnotationUtils;
import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;

/** An enumeration of validation rules for single occurrence records. */
public enum OccurrenceIssue implements InterpretationRemark {

  /** Coordinate is the exact 0°, 0° coordinate, often indicating a bad null coordinate. */
  ZERO_COORDINATE(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /**
   * Coordinate has a latitude and/or longitude value beyond the maximum (or minimum) decimal value.
   */
  COORDINATE_OUT_OF_RANGE(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /** Coordinate value is given in some form but GBIF is unable to interpret it. */
  COORDINATE_INVALID(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /** Original coordinate modified by rounding to 5 decimals. */
  COORDINATE_ROUNDED(INFO, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /** The geodetic datum given could not be interpreted. */
  GEODETIC_DATUM_INVALID(WARNING, DwcTerm.geodeticDatum),

  /**
   * Indicating that the interpreted coordinates assume they are based on WGS84 datum as the datum
   * was either not indicated or interpretable. See GEODETIC_DATUM_INVALID.
   */
  GEODETIC_DATUM_ASSUMED_WGS84(INFO, DwcTerm.geodeticDatum),

  /**
   * The original coordinate was successfully reprojected from a different geodetic datum to WGS84.
   */
  COORDINATE_REPROJECTED(INFO, TermsGroup.COORDINATES_TERMS),

  /**
   * The given decimal latitude and longitude could not be reprojected to WGS84 based on the
   * provided datum.
   */
  COORDINATE_REPROJECTION_FAILED(WARNING, TermsGroup.COORDINATES_TERMS),

  /**
   * Indicates successful coordinate reprojection according to provided datum, but which results in
   * a datum shift larger than 0.1 decimal degrees.
   */
  COORDINATE_REPROJECTION_SUSPICIOUS(WARNING, TermsGroup.COORDINATES_TERMS),

  /**
   * Indicates an invalid or very unlikely coordinate accuracy derived from precision or uncertainty
   * in meters.
   */
  @Deprecated // see POR-3061
  COORDINATE_ACCURACY_INVALID(WARNING),

  /** Indicates an invalid or very unlikely coordinatePrecision */
  COORDINATE_PRECISION_INVALID(WARNING, DwcTerm.coordinatePrecision),

  /** Indicates an invalid or very unlikely dwc:uncertaintyInMeters. */
  COORDINATE_UNCERTAINTY_METERS_INVALID(WARNING, DwcTerm.coordinateUncertaintyInMeters),

  /** There is a mismatch between coordinate uncertainty in meters and coordinate precision. */
  @Deprecated // see POR-1804
  COORDINATE_PRECISION_UNCERTAINTY_MISMATCH(WARNING),

  /** The Footprint Spatial Reference System given could not be interpreted. */
  FOOTPRINT_SRS_INVALID(WARNING, DwcTerm.footprintSRS),

  /**
   * The Footprint Well-Known-Text conflicts with the interpreted coordinates
   * (Decimal Latitude, Decimal Longitude etc).
   */
  FOOTPRINT_WKT_MISMATCH(WARNING, DwcTerm.footprintWKT),

  /** The Footprint Well-Known-Text given could not be interpreted. */
  FOOTPRINT_WKT_INVALID(WARNING, DwcTerm.footprintWKT),

  /** The interpreted occurrence coordinates fall outside of the indicated country. */
  COUNTRY_COORDINATE_MISMATCH(WARNING, TermsGroup.COORDINATES_COUNTRY_TERMS),

  /** Interpreted country for dwc:country and dwc:countryCode contradict each other. */
  COUNTRY_MISMATCH(WARNING, TermsGroup.COUNTRY_TERMS),

  /** Uninterpretable country values found. */
  COUNTRY_INVALID(WARNING, TermsGroup.COUNTRY_TERMS),

  /** The interpreted country is based on the coordinates, not the verbatim string information. */
  COUNTRY_DERIVED_FROM_COORDINATES(WARNING, TermsGroup.COORDINATES_COUNTRY_TERMS),

  /** The interpreted occurrence coordinates fall outside of the indicated continent. */
  CONTINENT_COORDINATE_MISMATCH(WARNING),

  /** The interpreted continent and country do not match. */
  CONTINENT_COUNTRY_MISMATCH(WARNING),

  /** Uninterpretable continent values found. */
  CONTINENT_INVALID(WARNING),

  /** The interpreted continent is based on the country, not the verbatim string information. */
  CONTINENT_DERIVED_FROM_COUNTRY(WARNING),

  /** The interpreted continent is based on the coordinates, not the verbatim string information. */
  CONTINENT_DERIVED_FROM_COORDINATES(WARNING),

  /** Latitude and longitude appear to be swapped. */
  PRESUMED_SWAPPED_COORDINATE(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /** Longitude appears to be negated, e.g. 32.3 instead of -32.3 */
  PRESUMED_NEGATED_LONGITUDE(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /** Latitude appears to be negated, e.g. 32.3 instead of -32.3 */
  PRESUMED_NEGATED_LATITUDE(WARNING, TermsGroup.COORDINATES_TERMS_NO_DATUM),

  /**
   * The recorded date specified as the eventDate string and the individual year, month, day and/or
   * startDayOfYear, endDayOfYear are contradictory.
   */
  RECORDED_DATE_MISMATCH(WARNING, TermsGroup.RECORDED_DATE_TERMS),

  /** A (partial) invalid date is given, such as a non-existent date, zero month, etc. */
  RECORDED_DATE_INVALID(WARNING, TermsGroup.RECORDED_DATE_TERMS),

  /**
   * The recorded date is highly unlikely, falling either into the future or representing a very old
   * date before 1600 thus predating modern taxonomy.
   */
  RECORDED_DATE_UNLIKELY(WARNING, TermsGroup.RECORDED_DATE_TERMS),

  /**
   * Matching to the taxonomic backbone can only be done using a fuzzy, non-exact match.
   */
  TAXON_MATCH_FUZZY(WARNING, TermsGroup.TAXONOMY_TERMS),

  /**
   * Matching to the taxonomic backbone can only be done on a higher rank and not the scientific
   * name.
   */
  TAXON_MATCH_HIGHERRANK(WARNING, TermsGroup.TAXONOMY_TERMS),

  /**
   * Matching to the taxonomic backbone can only be done on a species level,
   * but the occurrence was in fact considered a broader species aggregate/complex.
   *
   * @see <a href="https://github.com/gbif/portal-feedback/issues/2935">gbif/portal-feedback#2935</a>
   */
  TAXON_MATCH_AGGREGATE(WARNING, TermsGroup.TAXONOMY_TERMS),

  /**
   * The scientificNameID was not used when mapping the record to the GBIF backbone. This may indicate one of
   * <ul>
   *   <li>The ID uses a pattern not configured for use by GBIF</li>
   *   <li>The ID did not uniquely(!) identify a concept in the checklist</li>
   *   <li>The ID found a concept in the checklist which did not map to the backbone</li>
   *   <li>A different ID was used, or the record names were used as no ID lookup successfully linked to the backbone</li>
   * </ul>
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_MATCH_SCIENTIFIC_NAME_ID_IGNORED(INFO, DwcTerm.scientificNameID),

  /**
   * The taxonConceptID was not used when mapping the record to the GBIF backbone. This may indicate one of
   * <ul>
   *   <li>The ID uses a pattern not configured for use by GBIF</li>
   *   <li>The ID did not uniquely(!) identify a concept in the checklist</li>
   *   <li>The ID found a concept in the checklist which did not map to the backbone</li>
   *   <li>A different ID was used, or the record names were used as no ID lookup successfully linked to the backbone</li>
   * </ul>
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_MATCH_TAXON_CONCEPT_ID_IGNORED(INFO, DwcTerm.taxonConceptID),

  /**
   * The taxonID was not used when mapping the record to the GBIF backbone. This may indicate one of
   *
   * <ul>
   *   <li>The ID uses a pattern not configured for use by GBIF</li>
   *   <li>The ID did not uniquely(!) identify a concept in the checklist</li>
   *   <li>The ID found a concept in the checklist which did not map to the backbone</li>
   *   <li>A different ID was used, or the record names were used as no ID lookup successfully linked to the backbone</li>
   * </ul>
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_MATCH_TAXON_ID_IGNORED(INFO, DwcTerm.taxonID),

  /**
   * The scientificNameID matched a known pattern, but it was not found in the associated checklist.
   * The backbone lookup was performed using either the names or a different ID on the record.
   * This may indicate a poorly formatted identifier or may be caused by a newly created ID that
   * isn't yet known in the version of the published checklist.
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  SCIENTIFIC_NAME_ID_NOT_FOUND(WARNING, DwcTerm.scientificNameID),

  /**
   * The taxonConceptID matched a known pattern, but it was not found in the associated checklist.
   * The backbone lookup was performed using either the names or a different ID on the record.
   * This may indicate a poorly formatted identifier or may be caused by a newly created ID that isn't yet
   * known in the version of the published checklist.
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_CONCEPT_ID_NOT_FOUND(WARNING, DwcTerm.taxonConceptID),

  /**
   * The taxonID matched a known pattern, but it was not found in the associated checklist.
   * The backbone lookup was performed using either the names or a different ID on the record.
   * This may indicate a poorly formatted identifier or may be caused by a newly created ID that isn't yet
   * known in the version of the published checklist.
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_ID_NOT_FOUND(WARNING, DwcTerm.taxonID),

  /**
   * The scientificName provided in the occurrence record does not precisely match the name in the
   * registered checklist when using the scientificNameID, taxonID or taxonConceptID to look it up.
   * Publishers are advised to check the IDs are correct, or update the formatting of the names on
   * their records.
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  SCIENTIFIC_NAME_AND_ID_INCONSISTENT(
      WARNING,
      DwcTerm.scientificNameID,
      DwcTerm.taxonID,
      DwcTerm.taxonConceptID,
      DwcTerm.scientificName),

  /**
   * Matching to the taxonomic backbone cannot be done because there was no match at all, or several
   * matches with too little information to keep them apart (potentially homonyms).
   */
  TAXON_MATCH_NONE(WARNING, TermsGroup.TAXONOMY_TERMS),

  /**
   * The GBIF Backbone concept was found using the scientificNameID, taxonID or taxonConceptID, but
   * it differs from what would have been found if the classification names on the record were used.
   * This may indicate a gap in the GBIF backbone, a poor mapping between the checklist and the
   * backbone, or a mismatch between the classification names and the declared IDs (scientificNameID
   * or taxonConceptID) on the occurrence record itself.
   *
   * @see <a href="https://github.com/gbif/pipelines/issues/217">gbif/pipelines#217</a>
   */
  TAXON_MATCH_NAME_AND_ID_AMBIGUOUS(WARNING, TermsGroup.TAXONOMY_TERMS),

  /**
   * Set if supplied depth is not given in the metric system, for example using feet instead of
   * meters
   */
  DEPTH_NOT_METRIC(WARNING, DwcTerm.minimumDepthInMeters, DwcTerm.maximumDepthInMeters),

  /** Set if depth is larger than 11,000m or negative. */
  DEPTH_UNLIKELY(WARNING, DwcTerm.minimumDepthInMeters, DwcTerm.maximumDepthInMeters),

  /** Set if supplied minimum depth > maximum depth */
  DEPTH_MIN_MAX_SWAPPED(WARNING, DwcTerm.minimumDepthInMeters, DwcTerm.maximumDepthInMeters),

  /** Set if depth is a non-numeric value */
  DEPTH_NON_NUMERIC(WARNING, DwcTerm.minimumDepthInMeters, DwcTerm.maximumDepthInMeters),

  /** Set if elevation is above the troposphere (17km) or below 11km (Mariana Trench). */
  ELEVATION_UNLIKELY(WARNING, DwcTerm.minimumElevationInMeters, DwcTerm.maximumElevationInMeters),

  /** Set if supplied minimum elevation > maximum elevation */
  ELEVATION_MIN_MAX_SWAPPED(
      WARNING, DwcTerm.minimumElevationInMeters, DwcTerm.maximumElevationInMeters),

  /**
   * Set if supplied elevation is not given in the metric system, for example using feet instead of
   * meters
   */
  ELEVATION_NOT_METRIC(WARNING, DwcTerm.minimumElevationInMeters, DwcTerm.maximumElevationInMeters),

  /** Set if elevation is a non-numeric value */
  ELEVATION_NON_NUMERIC(
      WARNING, DwcTerm.minimumElevationInMeters, DwcTerm.maximumElevationInMeters),

  /**
   * A (partial) invalid date is given for dc:modified, such as a nonexistent date, zero month, etc.
   */
  MODIFIED_DATE_INVALID(WARNING, DcTerm.modified),

  /** The date given for dc:modified is in the future or predates Unix time (1970). */
  MODIFIED_DATE_UNLIKELY(WARNING, DcTerm.modified),

  /** The date given for dwc:dateIdentified is in the future or before Linnean times (1700). */
  IDENTIFIED_DATE_UNLIKELY(WARNING, DwcTerm.dateIdentified),

  /** The date given for dwc:dateIdentified is invalid and can't be interpreted at all. */
  IDENTIFIED_DATE_INVALID(WARNING, DwcTerm.dateIdentified),

  /**
   * The given basis of record is impossible to interpret or significantly different from the
   * recommended vocabulary.
   */
  BASIS_OF_RECORD_INVALID(WARNING, DwcTerm.basisOfRecord),

  /**
   * The given type status is impossible to interpret or significantly different from the
   * recommended vocabulary.
   */
  TYPE_STATUS_INVALID(WARNING, DwcTerm.typeStatus),

  /**
   * The given type status contains some words that express uncertainty.
   */
  SUSPECTED_TYPE(WARNING, DwcTerm.typeStatus),

  /** An invalid date is given for dc:created of a multimedia object. */
  MULTIMEDIA_DATE_INVALID(WARNING),

  /** An invalid URI is given for a multimedia object. */
  MULTIMEDIA_URI_INVALID(WARNING),

  /** An invalid URI is given for dc:references. */
  REFERENCES_URI_INVALID(WARNING, DcTerm.references),

  /** An error occurred during interpretation, leaving the record interpretation incomplete. */
  INTERPRETATION_ERROR(ERROR),

  /** The individual count value is not a positive integer */
  INDIVIDUAL_COUNT_INVALID(WARNING, DwcTerm.individualCount),

  /** Example: individual count value > 0, but occurrence status is absent. */
  INDIVIDUAL_COUNT_CONFLICTS_WITH_OCCURRENCE_STATUS(WARNING, DwcTerm.individualCount),

  /** Occurrence status value can't be assigned to {@link OccurrenceStatus} */
  OCCURRENCE_STATUS_UNPARSABLE(WARNING, DwcTerm.occurrenceStatus),

  /** Occurrence status was inferred from the individual count value */
  OCCURRENCE_STATUS_INFERRED_FROM_INDIVIDUAL_COUNT(WARNING, DwcTerm.occurrenceStatus),

  /** Occurrence status was inferred from basis of records */
  OCCURRENCE_STATUS_INFERRED_FROM_BASIS_OF_RECORD(WARNING, DwcTerm.occurrenceStatus),

  /** The date given for dwc:georeferencedDate is in the future or before Linnean times (1700). */
  GEOREFERENCED_DATE_UNLIKELY(WARNING, DwcTerm.georeferencedDate),

  /** The date given for dwc:georeferencedDate is invalid and can't be interpreted at all. */
  GEOREFERENCED_DATE_INVALID(WARNING, DwcTerm.georeferencedDate),

  /** The given institution matches with more than 1 GRSciColl institution. */
  AMBIGUOUS_INSTITUTION(INFO, TermsGroup.INSTITUTION_TERMS),

  /** The given collection matches with more than 1 GRSciColl collection. */
  AMBIGUOUS_COLLECTION(INFO, TermsGroup.COLLECTION_TERMS),

  /** The given institution couldn't be matched with any GRSciColl institution. */
  INSTITUTION_MATCH_NONE(INFO, TermsGroup.INSTITUTION_TERMS),

  /** The given collection couldn't be matched with any GRSciColl collection. */
  COLLECTION_MATCH_NONE(INFO, TermsGroup.COLLECTION_TERMS),

  /**
   * The given institution was fuzzily matched to a GRSciColl institution. This can happen when
   * either the code or the ID don't match or when the institution name is used instead of the code.
   */
  INSTITUTION_MATCH_FUZZY(INFO, TermsGroup.INSTITUTION_TERMS),

  /**
   * The given collection was fuzzily matched to a GRSciColl collection. This can happen when either
   * the code or the ID don't match or when the collection name is used instead of the code.
   */
  COLLECTION_MATCH_FUZZY(INFO, TermsGroup.COLLECTION_TERMS),

  /** The collection matched doesn't belong to the institution matched. */
  INSTITUTION_COLLECTION_MISMATCH(
      INFO, ArrayUtils.addAll(TermsGroup.INSTITUTION_TERMS, TermsGroup.INSTITUTION_TERMS)),

  /**
   * The given owner institution is different than the given institution. Therefore we assume it
   * could be on loan and we don't link it to the occurrence.
   *
   * <p>Deprecated by {@link #DIFFERENT_OWNER_INSTITUTION}.
   */
  @Deprecated
  POSSIBLY_ON_LOAN(INFO, TermsGroup.INSTITUTION_TERMS),

  /**
   * The given owner institution is different than the given institution. Therefore we assume it
   * doesn't belong to the institution and we don't link it to the occurrence.
   */
  DIFFERENT_OWNER_INSTITUTION(INFO, TermsGroup.INSTITUTION_TERMS),

  /** Era or erathem was inferred from a parent rank. */
  ERA_OR_ERATHEM_INFERRED_FROM_PARENT_RANK(
      INFO, DwcTerm.earliestEraOrLowestErathem, DwcTerm.latestEraOrHighestErathem),
  /** Period or system was inferred from a parent rank. */
  PERIOD_OR_SYSTEM_INFERRED_FROM_PARENT_RANK(
      INFO, DwcTerm.earliestPeriodOrLowestSystem, DwcTerm.latestPeriodOrHighestSystem),
  /** Epoch or series was inferred from a parent rank. */
  EPOCH_OR_SERIES_INFERRED_FROM_PARENT_RANK(
      INFO, DwcTerm.earliestEpochOrLowestSeries, DwcTerm.latestEpochOrHighestSeries),
  /** Age or stage was inferred from a parent rank. */
  AGE_OR_STAGE_INFERRED_FROM_PARENT_RANK(
      INFO, DwcTerm.earliestAgeOrLowestStage, DwcTerm.latestAgeOrHighestStage),

  /** The eon or eonothem provided belongs to another rank. */
  EON_OR_EONOTHEM_RANK_MISMATCH(
      INFO, DwcTerm.earliestEonOrLowestEonothem, DwcTerm.latestEonOrHighestEonothem),
  /** The era or erathem provided belongs to another rank. */
  ERA_OR_ERATHEM_RANK_MISMATCH(
      INFO, DwcTerm.earliestEraOrLowestErathem, DwcTerm.latestEraOrHighestErathem),
  /** The period or system provided belongs to another rank. */
  PERIOD_OR_SYSTEM_RANK_MISMATCH(
      INFO, DwcTerm.earliestPeriodOrLowestSystem, DwcTerm.latestPeriodOrHighestSystem),
  /** The period or series provided belongs to another rank. */
  EPOCH_OR_SERIES_RANK_MISMATCH(
      INFO, DwcTerm.earliestEpochOrLowestSeries, DwcTerm.latestEpochOrHighestSeries),
  /** The age or stage provided belongs to another rank. */
  AGE_OR_STAGE_RANK_MISMATCH(
      INFO, DwcTerm.earliestAgeOrLowestStage, DwcTerm.latestAgeOrHighestStage),

  /** The earliest eon or eonothem has to be earlier than the latest. */
  EON_OR_EONOTHEM_INVALID_RANGE(
      INFO, DwcTerm.earliestEonOrLowestEonothem, DwcTerm.latestEonOrHighestEonothem),
  /** The era or erathem has to be earlier than the latest. */
  ERA_OR_ERATHEM_INVALID_RANGE(
      INFO, DwcTerm.earliestEraOrLowestErathem, DwcTerm.latestEraOrHighestErathem),
  /** The period or system has to be earlier than the latest. */
  PERIOD_OR_SYSTEM_INVALID_RANGE(
      INFO, DwcTerm.earliestPeriodOrLowestSystem, DwcTerm.latestPeriodOrHighestSystem),
  /** The period or series has to be earlier than the latest. */
  EPOCH_OR_SERIES_INVALID_RANGE(
      INFO, DwcTerm.earliestEpochOrLowestSeries, DwcTerm.latestEpochOrHighestSeries),
  /** The age or stage has to be earlier than the latest. */
  AGE_OR_STAGE_INVALID_RANGE(
      INFO, DwcTerm.earliestAgeOrLowestStage, DwcTerm.latestAgeOrHighestStage),

  /** The era or erathem don't belong to the eon or eonothem. */
  EON_OR_EONOTHEM_AND_ERA_OR_ERATHEM_MISMATCH(
      INFO,
      DwcTerm.earliestEonOrLowestEonothem,
      DwcTerm.latestEonOrHighestEonothem,
      DwcTerm.earliestEraOrLowestErathem,
      DwcTerm.latestEraOrHighestErathem),

  /** The period or system don't belong to the era or erathem. */
  ERA_OR_ERATHEM_AND_PERIOD_OR_SYSTEM_MISMATCH(
      INFO,
      DwcTerm.earliestEraOrLowestErathem,
      DwcTerm.latestEraOrHighestErathem,
      DwcTerm.earliestPeriodOrLowestSystem,
      DwcTerm.latestPeriodOrHighestSystem),

  /** The epoch or series don't belong to the period or system. */
  PERIOD_OR_SYSTEM_AND_EPOCH_OR_SERIES_MISMATCH(
      INFO,
      DwcTerm.earliestPeriodOrLowestSystem,
      DwcTerm.latestPeriodOrHighestSystem,
      DwcTerm.earliestEpochOrLowestSeries,
      DwcTerm.latestEpochOrHighestSeries),

  /** The age or stage don't belong to the epoch or series. */
  EPOCH_OR_SERIES_AND_AGE_OR_STAGE_MISMATCH(
      INFO,
      DwcTerm.earliestEpochOrLowestSeries,
      DwcTerm.latestEpochOrHighestSeries,
      DwcTerm.earliestAgeOrLowestStage,
      DwcTerm.latestAgeOrHighestStage);

  /**
   * Simple helper nested class to allow grouping of Term mostly to increase readability of this
   * class.
   */
  private static class TermsGroup {

    static final Term[] COORDINATES_TERMS_NO_DATUM = {
      DwcTerm.decimalLatitude,
      DwcTerm.decimalLongitude,
      DwcTerm.verbatimLatitude,
      DwcTerm.verbatimLongitude,
      DwcTerm.verbatimCoordinates
    };

    static final Term[] COORDINATES_TERMS = {
      DwcTerm.decimalLatitude,
      DwcTerm.decimalLongitude,
      DwcTerm.verbatimLatitude,
      DwcTerm.verbatimLongitude,
      DwcTerm.verbatimCoordinates,
      DwcTerm.geodeticDatum
    };

    static final Term[] COUNTRY_TERMS = {DwcTerm.country, DwcTerm.countryCode};

    static final Term[] COORDINATES_COUNTRY_TERMS = {
      DwcTerm.decimalLatitude,
      DwcTerm.decimalLongitude,
      DwcTerm.verbatimLatitude,
      DwcTerm.verbatimLongitude,
      DwcTerm.verbatimCoordinates,
      DwcTerm.geodeticDatum,
      DwcTerm.country,
      DwcTerm.countryCode
    };

    static final Term[] RECORDED_DATE_TERMS = {
      DwcTerm.eventDate,
      DwcTerm.year,
      DwcTerm.month,
      DwcTerm.day,
      DwcTerm.startDayOfYear,
      DwcTerm.endDayOfYear
    };

    static final Term[] TAXONOMY_TERMS = {
      DwcTerm.kingdom,
      DwcTerm.phylum,
      DwcTerm.class_,
      DwcTerm.order,
      DwcTerm.family,
      DwcTerm.genus,
      DwcTerm.scientificName,
      DwcTerm.scientificNameAuthorship,
      DwcTerm.genericName,
      DwcTerm.specificEpithet,
      DwcTerm.infraspecificEpithet,
      DwcTerm.scientificNameID,
      DwcTerm.taxonConceptID,
    };

    static final Term[] INSTITUTION_TERMS = {
      DwcTerm.institutionCode, DwcTerm.institutionID, DwcTerm.ownerInstitutionCode
    };

    static final Term[] COLLECTION_TERMS = {DwcTerm.collectionCode, DwcTerm.collectionID};
  }

  private final Set<Term> relatedTerms;
  private final InterpretationRemarkSeverity severity;
  private final boolean isDeprecated;

  /** {@link OccurrenceIssue} not linked to any specific {@link Term}. */
  OccurrenceIssue(InterpretationRemarkSeverity severity) {
    this.severity = severity;
    this.relatedTerms = Collections.emptySet();
    this.isDeprecated = AnnotationUtils.isFieldDeprecated(OccurrenceIssue.class, this.name());
  }

  /** {@link OccurrenceIssue} linked to the provided {@link Term}. */
  OccurrenceIssue(InterpretationRemarkSeverity severity, Term... relatedTerms) {
    this.severity = severity;
    this.relatedTerms = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(relatedTerms)));
    this.isDeprecated = AnnotationUtils.isFieldDeprecated(OccurrenceIssue.class, this.name());
  }

  @Override
  public String getId() {
    return name();
  }

  @Override
  public Set<Term> getRelatedTerms() {
    return relatedTerms;
  }

  @Override
  public InterpretationRemarkSeverity getSeverity() {
    return severity;
  }

  @Override
  public boolean isDeprecated() {
    return isDeprecated;
  }

  /**
   * All issues that indicate problems with the coordinates and thus should not be shown on maps.
   */
  public static final List<OccurrenceIssue> GEOSPATIAL_RULES =
      Collections.unmodifiableList(
          Arrays.asList(
              ZERO_COORDINATE,
              COORDINATE_OUT_OF_RANGE,
              COORDINATE_INVALID,
              COUNTRY_COORDINATE_MISMATCH,
              PRESUMED_SWAPPED_COORDINATE,
              PRESUMED_NEGATED_LONGITUDE,
              PRESUMED_NEGATED_LATITUDE));
}
