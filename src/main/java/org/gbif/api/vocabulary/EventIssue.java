package org.gbif.api.vocabulary;

import static org.gbif.api.vocabulary.InterpretationRemarkSeverity.INFO;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.gbif.api.util.AnnotationUtils;
import org.gbif.dwc.terms.EcoTerm;
import org.gbif.dwc.terms.Term;

public enum EventIssue implements InterpretationRemark {
  /** See {@link OccurrenceIssue#ZERO_COORDINATE}. */
  ZERO_COORDINATE(OccurrenceIssue.ZERO_COORDINATE),
  /** See {@link OccurrenceIssue#COORDINATE_OUT_OF_RANGE}. */
  COORDINATE_OUT_OF_RANGE(OccurrenceIssue.COORDINATE_OUT_OF_RANGE),
  /** See {@link OccurrenceIssue#COORDINATE_INVALID}. */
  COORDINATE_INVALID(OccurrenceIssue.COORDINATE_INVALID),
  /** See {@link OccurrenceIssue#COORDINATE_ROUNDED}. */
  COORDINATE_ROUNDED(OccurrenceIssue.COORDINATE_ROUNDED),
  /** See {@link OccurrenceIssue#GEODETIC_DATUM_INVALID}. */
  GEODETIC_DATUM_INVALID(OccurrenceIssue.GEODETIC_DATUM_INVALID),
  /** See {@link OccurrenceIssue#GEODETIC_DATUM_ASSUMED_WGS84}. */
  GEODETIC_DATUM_ASSUMED_WGS84(OccurrenceIssue.GEODETIC_DATUM_ASSUMED_WGS84),
  /** See {@link OccurrenceIssue#COORDINATE_REPROJECTED}. */
  COORDINATE_REPROJECTED(OccurrenceIssue.COORDINATE_REPROJECTED),
  /** See {@link OccurrenceIssue#COORDINATE_REPROJECTION_FAILED}. */
  COORDINATE_REPROJECTION_FAILED(OccurrenceIssue.COORDINATE_REPROJECTION_FAILED),
  /** See {@link OccurrenceIssue#COORDINATE_REPROJECTION_SUSPICIOUS}. */
  COORDINATE_REPROJECTION_SUSPICIOUS(OccurrenceIssue.COORDINATE_REPROJECTION_SUSPICIOUS),
  /** See {@link OccurrenceIssue#COORDINATE_ACCURACY_INVALID}. */
  @Deprecated // see POR-3061
  COORDINATE_ACCURACY_INVALID(OccurrenceIssue.COORDINATE_ACCURACY_INVALID),
  /** See {@link OccurrenceIssue#COORDINATE_PRECISION_INVALID}. */
  COORDINATE_PRECISION_INVALID(OccurrenceIssue.COORDINATE_PRECISION_INVALID),
  /** See {@link OccurrenceIssue#COORDINATE_UNCERTAINTY_METERS_INVALID}. */
  COORDINATE_UNCERTAINTY_METERS_INVALID(OccurrenceIssue.COORDINATE_UNCERTAINTY_METERS_INVALID),
  /** See {@link OccurrenceIssue#COORDINATE_PRECISION_UNCERTAINTY_MISMATCH}. */
  @Deprecated // see POR-1804
  COORDINATE_PRECISION_UNCERTAINTY_MISMATCH(
      OccurrenceIssue.COORDINATE_PRECISION_UNCERTAINTY_MISMATCH),
  /** See {@link OccurrenceIssue#FOOTPRINT_SRS_INVALID}. */
  FOOTPRINT_SRS_INVALID(OccurrenceIssue.FOOTPRINT_SRS_INVALID),
  /** See {@link OccurrenceIssue#FOOTPRINT_WKT_MISMATCH}. */
  FOOTPRINT_WKT_MISMATCH(OccurrenceIssue.FOOTPRINT_WKT_MISMATCH),
  /** See {@link OccurrenceIssue#FOOTPRINT_WKT_INVALID}. */
  FOOTPRINT_WKT_INVALID(OccurrenceIssue.FOOTPRINT_WKT_INVALID),
  /** See {@link OccurrenceIssue#COUNTRY_COORDINATE_MISMATCH}. */
  COUNTRY_COORDINATE_MISMATCH(OccurrenceIssue.COUNTRY_COORDINATE_MISMATCH),
  /** See {@link OccurrenceIssue#COUNTRY_MISMATCH}. */
  COUNTRY_MISMATCH(OccurrenceIssue.COUNTRY_MISMATCH),
  /** See {@link OccurrenceIssue#COUNTRY_INVALID}. */
  COUNTRY_INVALID(OccurrenceIssue.COUNTRY_INVALID),
  /** See {@link OccurrenceIssue#COUNTRY_DERIVED_FROM_COORDINATES}. */
  COUNTRY_DERIVED_FROM_COORDINATES(OccurrenceIssue.COUNTRY_DERIVED_FROM_COORDINATES),
  /** See {@link OccurrenceIssue#CONTINENT_COORDINATE_MISMATCH}. */
  CONTINENT_COORDINATE_MISMATCH(OccurrenceIssue.CONTINENT_COORDINATE_MISMATCH),
  /** See {@link OccurrenceIssue#CONTINENT_COUNTRY_MISMATCH}. */
  CONTINENT_COUNTRY_MISMATCH(OccurrenceIssue.CONTINENT_COUNTRY_MISMATCH),
  /** See {@link OccurrenceIssue#CONTINENT_INVALID}. */
  CONTINENT_INVALID(OccurrenceIssue.CONTINENT_INVALID),
  /** See {@link OccurrenceIssue#CONTINENT_DERIVED_FROM_COUNTRY}. */
  CONTINENT_DERIVED_FROM_COUNTRY(OccurrenceIssue.CONTINENT_DERIVED_FROM_COUNTRY),
  /** See {@link OccurrenceIssue#CONTINENT_DERIVED_FROM_COORDINATES}. */
  CONTINENT_DERIVED_FROM_COORDINATES(OccurrenceIssue.CONTINENT_DERIVED_FROM_COORDINATES),
  /** See {@link OccurrenceIssue#PRESUMED_SWAPPED_COORDINATE}. */
  PRESUMED_SWAPPED_COORDINATE(OccurrenceIssue.PRESUMED_SWAPPED_COORDINATE),
  /** See {@link OccurrenceIssue#PRESUMED_NEGATED_LONGITUDE}. */
  PRESUMED_NEGATED_LONGITUDE(OccurrenceIssue.PRESUMED_NEGATED_LONGITUDE),
  /** See {@link OccurrenceIssue#PRESUMED_NEGATED_LATITUDE}. */
  PRESUMED_NEGATED_LATITUDE(OccurrenceIssue.PRESUMED_NEGATED_LATITUDE),
  /** See {@link OccurrenceIssue#RECORDED_DATE_MISMATCH}. */
  RECORDED_DATE_MISMATCH(OccurrenceIssue.RECORDED_DATE_MISMATCH),
  /** See {@link OccurrenceIssue#RECORDED_DATE_INVALID}. */
  RECORDED_DATE_INVALID(OccurrenceIssue.RECORDED_DATE_INVALID),
  /** See {@link OccurrenceIssue#RECORDED_DATE_UNLIKELY}. */
  RECORDED_DATE_UNLIKELY(OccurrenceIssue.RECORDED_DATE_UNLIKELY),
  /** See {@link OccurrenceIssue#TAXON_MATCH_FUZZY}. */
  TAXON_MATCH_FUZZY(OccurrenceIssue.TAXON_MATCH_FUZZY),
  /** See {@link OccurrenceIssue#TAXON_MATCH_HIGHERRANK}. */
  TAXON_MATCH_HIGHERRANK(OccurrenceIssue.TAXON_MATCH_HIGHERRANK),
  /** See {@link OccurrenceIssue#TAXON_MATCH_AGGREGATE}. */
  TAXON_MATCH_AGGREGATE(OccurrenceIssue.TAXON_MATCH_AGGREGATE),
  /** See {@link OccurrenceIssue#TAXON_MATCH_SCIENTIFIC_NAME_ID_IGNORED}. */
  TAXON_MATCH_SCIENTIFIC_NAME_ID_IGNORED(OccurrenceIssue.TAXON_MATCH_SCIENTIFIC_NAME_ID_IGNORED),
  /** See {@link OccurrenceIssue#TAXON_MATCH_TAXON_CONCEPT_ID_IGNORED}. */
  TAXON_MATCH_TAXON_CONCEPT_ID_IGNORED(OccurrenceIssue.TAXON_MATCH_TAXON_CONCEPT_ID_IGNORED),
  /** See {@link OccurrenceIssue#TAXON_MATCH_TAXON_ID_IGNORED}. */
  TAXON_MATCH_TAXON_ID_IGNORED(OccurrenceIssue.TAXON_MATCH_TAXON_ID_IGNORED),
  /** See {@link OccurrenceIssue#SCIENTIFIC_NAME_ID_NOT_FOUND}. */
  SCIENTIFIC_NAME_ID_NOT_FOUND(OccurrenceIssue.SCIENTIFIC_NAME_ID_NOT_FOUND),
  /** See {@link OccurrenceIssue#TAXON_CONCEPT_ID_NOT_FOUND}. */
  TAXON_CONCEPT_ID_NOT_FOUND(OccurrenceIssue.TAXON_CONCEPT_ID_NOT_FOUND),
  /** See {@link OccurrenceIssue#TAXON_ID_NOT_FOUND}. */
  TAXON_ID_NOT_FOUND(OccurrenceIssue.TAXON_ID_NOT_FOUND),
  /** See {@link OccurrenceIssue#SCIENTIFIC_NAME_AND_ID_INCONSISTENT}. */
  SCIENTIFIC_NAME_AND_ID_INCONSISTENT(OccurrenceIssue.SCIENTIFIC_NAME_AND_ID_INCONSISTENT),
  /** See {@link OccurrenceIssue#TAXON_MATCH_NONE}. */
  TAXON_MATCH_NONE(OccurrenceIssue.TAXON_MATCH_NONE),
  /** See {@link OccurrenceIssue#TAXON_MATCH_NAME_AND_ID_AMBIGUOUS}. */
  TAXON_MATCH_NAME_AND_ID_AMBIGUOUS(OccurrenceIssue.TAXON_MATCH_NAME_AND_ID_AMBIGUOUS),
  /** See {@link OccurrenceIssue#DEPTH_NOT_METRIC}. */
  DEPTH_NOT_METRIC(OccurrenceIssue.DEPTH_NOT_METRIC),
  /** See {@link OccurrenceIssue#DEPTH_UNLIKELY}. */
  DEPTH_UNLIKELY(OccurrenceIssue.DEPTH_UNLIKELY),
  /** See {@link OccurrenceIssue#DEPTH_MIN_MAX_SWAPPED}. */
  DEPTH_MIN_MAX_SWAPPED(OccurrenceIssue.DEPTH_MIN_MAX_SWAPPED),
  /** See {@link OccurrenceIssue#DEPTH_NON_NUMERIC}. */
  DEPTH_NON_NUMERIC(OccurrenceIssue.DEPTH_NON_NUMERIC),
  /** See {@link OccurrenceIssue#ELEVATION_UNLIKELY}. */
  ELEVATION_UNLIKELY(OccurrenceIssue.ELEVATION_UNLIKELY),
  /** See {@link OccurrenceIssue#ELEVATION_MIN_MAX_SWAPPED}. */
  ELEVATION_MIN_MAX_SWAPPED(OccurrenceIssue.ELEVATION_MIN_MAX_SWAPPED),
  /** See {@link OccurrenceIssue#ELEVATION_NOT_METRIC}. */
  ELEVATION_NOT_METRIC(OccurrenceIssue.ELEVATION_NOT_METRIC),
  /** See {@link OccurrenceIssue#ELEVATION_NON_NUMERIC}. */
  ELEVATION_NON_NUMERIC(OccurrenceIssue.ELEVATION_NON_NUMERIC),
  /** See {@link OccurrenceIssue#MODIFIED_DATE_INVALID}. */
  MODIFIED_DATE_INVALID(OccurrenceIssue.MODIFIED_DATE_INVALID),
  /** See {@link OccurrenceIssue#MODIFIED_DATE_UNLIKELY}. */
  MODIFIED_DATE_UNLIKELY(OccurrenceIssue.MODIFIED_DATE_UNLIKELY),
  /** See {@link OccurrenceIssue#IDENTIFIED_DATE_UNLIKELY}. */
  IDENTIFIED_DATE_UNLIKELY(OccurrenceIssue.IDENTIFIED_DATE_UNLIKELY),
  /** See {@link OccurrenceIssue#IDENTIFIED_DATE_INVALID}. */
  IDENTIFIED_DATE_INVALID(OccurrenceIssue.IDENTIFIED_DATE_INVALID),

  /** See {@link OccurrenceIssue#TYPE_STATUS_INVALID}. */
  TYPE_STATUS_INVALID(OccurrenceIssue.TYPE_STATUS_INVALID),
  /** See {@link OccurrenceIssue#SUSPECTED_TYPE}. */
  SUSPECTED_TYPE(OccurrenceIssue.SUSPECTED_TYPE),
  /** See {@link OccurrenceIssue#MULTIMEDIA_DATE_INVALID}. */
  MULTIMEDIA_DATE_INVALID(OccurrenceIssue.MULTIMEDIA_DATE_INVALID),
  /** See {@link OccurrenceIssue#MULTIMEDIA_URI_INVALID}. */
  MULTIMEDIA_URI_INVALID(OccurrenceIssue.MULTIMEDIA_URI_INVALID),
  /** See {@link OccurrenceIssue#REFERENCES_URI_INVALID}. */
  REFERENCES_URI_INVALID(OccurrenceIssue.REFERENCES_URI_INVALID),
  /** See {@link OccurrenceIssue#INTERPRETATION_ERROR}. */
  INTERPRETATION_ERROR(OccurrenceIssue.INTERPRETATION_ERROR),
  /** See {@link OccurrenceIssue#GEOREFERENCED_DATE_UNLIKELY}. */
  GEOREFERENCED_DATE_UNLIKELY(OccurrenceIssue.GEOREFERENCED_DATE_UNLIKELY),
  /** See {@link OccurrenceIssue#GEOREFERENCED_DATE_INVALID}. */
  GEOREFERENCED_DATE_INVALID(OccurrenceIssue.GEOREFERENCED_DATE_INVALID),

  /**
   * The geospatial scope area is always greater than or equal to the total area sampled as stated
   * in the (<a href="https://eco.tdwg.org/list/#eco_geospatialScopeAreaValue">term definition</a>).
   */
  GEOSPATIAL_SCOPE_AREA_LOWER_THAN_TOTAL_AREA_SAMPLED(
      INFO, EcoTerm.geospatialScopeAreaValue, EcoTerm.totalAreaSampledValue),

  /** An eco:eventDurationValue must have a corresponding eco:eventDurationUnit. */
  EVENT_DURATION_UNIT_MISSING(INFO, EcoTerm.eventDurationUnit),

  /** An eco:geospatialScopeAreaValue must have a corresponding eco:geospatialScopeAreaUnit. */
  GEOSPATIAL_SCOPE_AREA_UNIT_MISSING(INFO, EcoTerm.geospatialScopeAreaUnit),

  /** An eco:samplingEffortValue must have a corresponding eco:samplingEffortUnit. */
  SAMPLING_EFFORT_UNIT_MISSING(INFO, EcoTerm.samplingEffortUnit),

  /** An eco:totalAreaSampledValue must have a corresponding eco:totalAreaSampledUnit. */
  TOTAL_AREA_SAMPLED_UNIT_MISSING(INFO, EcoTerm.samplingEffortUnit),

  /** Non target taxa has been provided but the eco:hasNonTargetTaxa is set to false. */
  HAS_NON_TARGET_TAXA_MISMATCH(INFO, EcoTerm.hasNonTargetTaxa),

  /** Material sample types have been provided but the eco:hasMaterialSamples is set to false. */
  HAS_MATERIAL_SAMPLES_MISMATCH(INFO, EcoTerm.hasMaterialSamples),

  /** Some target taxonomic scope has been excluded. */
  TARGET_TAXONOMIC_SCOPE_EXCLUDED(
      INFO, EcoTerm.targetTaxonomicScope, EcoTerm.excludedTaxonomicScope),

  /** Some target habitat scope has been excluded. */
  TARGET_HABITAT_SCOPE_EXCLUDED(INFO, EcoTerm.targetHabitatScope, EcoTerm.excludedHabitatScope),

  /** Some target life stage scope has been excluded. */
  TARGET_LIFE_STAGE_SCOPE_EXCLUDED(
      INFO, EcoTerm.targetLifeStageScope, EcoTerm.excludedLifeStageScope),

  /** Some target degree of establishment scope has been excluded. */
  TARGET_DEGREE_OF_ESTABLISHMENT_EXCLUDED(
      INFO, EcoTerm.targetDegreeOfEstablishmentScope, EcoTerm.excludedDegreeOfEstablishmentScope),

  /** Some target growth form scope has been excluded. */
  TARGET_GROWTH_FORM_EXCLUDED(INFO, EcoTerm.targetGrowthFormScope, EcoTerm.excludedGrowthFormScope),

  /** Invalid value for numeric fields in Humboldt extension. */
  SITE_COUNT_INVALID(INFO, EcoTerm.siteCount),
  GEOSPATIAL_SCOPE_AREA_VALUE_INVALID(INFO, EcoTerm.geospatialScopeAreaValue),
  TOTAL_AREA_SAMPLED_VALUE_INVALID(INFO, EcoTerm.totalAreaSampledValue),
  EVENT_DURATION_VALUE_INVALID(INFO, EcoTerm.eventDurationValue),
  SAMPLING_EFFORT_VALUE_INVALID(INFO, EcoTerm.samplingEffortValue),
  ABUNDANCE_CAP_INVALID(INFO, EcoTerm.abundanceCap),

  /** Invalid value for boolean fields in Humboldt extension. */
  IS_TAXONOMIC_SCOPE_FULLY_REPORTED_INVALID(INFO, EcoTerm.isTaxonomicScopeFullyReported),
  IS_ABSENCE_REPORTED_INVALID(INFO, EcoTerm.isAbsenceReported),
  HAS_NON_TARGET_TAXA_INVALID(INFO, EcoTerm.hasNonTargetTaxa),
  ARE_NON_TARGET_TAXA_FULLY_REPORTED_INVALID(INFO, EcoTerm.areNonTargetTaxaFullyReported),
  IS_LIFE_STAGE_SCOPE_FULLY_REPORTED_INVALID(INFO, EcoTerm.isLifeStageScopeFullyReported),
  IS_DEGREE_OF_ESTABLISHMENT_SCOPE_FULLY_REPORTED_INVALID(
      INFO, EcoTerm.isDegreeOfEstablishmentScopeFullyReported),
  IS_GROWTH_FORM_SCOPE_FULLY_REPORTED_INVALID(INFO, EcoTerm.isGrowthFormScopeFullyReported),
  HAS_NON_TARGET_ORGANISMS_INVALID(INFO, EcoTerm.hasNonTargetOrganisms),
  IS_ABUNDANCE_REPORTED_INVALID(INFO, EcoTerm.isAbundanceReported),
  IS_ABUNDANCE_CAP_REPORTED_INVALID(INFO, EcoTerm.isAbundanceCapReported),
  IS_VEGETATION_COVER_REPORTED_INVALID(INFO, EcoTerm.isVegetationCoverReported),
  IS_LEAST_SPECIFIC_TARGET_CATEGORY_QUANTITY_INCLUSIVE_INVALID(
      INFO, EcoTerm.isLeastSpecificTargetCategoryQuantityInclusive),
  HAS_VOUCHERS_INVALID(INFO, EcoTerm.hasVouchers),
  HAS_MATERIAL_SAMPLES_INVALID(INFO, EcoTerm.hasMaterialSamples),
  IS_SAMPLING_EFFORT_REPORTED_INVALID(INFO, EcoTerm.isSamplingEffortReported);

  private final Set<Term> relatedTerms;
  private final InterpretationRemarkSeverity severity;
  private final boolean isDeprecated;

  EventIssue(OccurrenceIssue occurrenceIssue) {
    this.severity = occurrenceIssue.getSeverity();
    this.relatedTerms = occurrenceIssue.getRelatedTerms();
    this.isDeprecated = occurrenceIssue.isDeprecated();
  }

  /** {@link EventIssue} not linked to any specific {@link Term}. */
  EventIssue(InterpretationRemarkSeverity severity) {
    this.severity = severity;
    this.relatedTerms = Collections.emptySet();
    this.isDeprecated = AnnotationUtils.isFieldDeprecated(EventIssue.class, this.name());
  }

  /** {@link EventIssue} linked to the provided {@link Term}. */
  EventIssue(InterpretationRemarkSeverity severity, Term... relatedTerms) {
    this.severity = severity;
    this.relatedTerms = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(relatedTerms)));
    this.isDeprecated = AnnotationUtils.isFieldDeprecated(EventIssue.class, this.name());
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
}
