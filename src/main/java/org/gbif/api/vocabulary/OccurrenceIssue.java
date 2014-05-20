package org.gbif.api.vocabulary;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * An enumeration of validation rules for single occurrence records.
 */
public enum OccurrenceIssue {

  /**
   * Coordinate is the exact 0/0 coordinate, often indicating a bad null coordinate.
   */
  ZERO_COORDINATE,

  /**
   * Coordinate has invalid lat/lon values out of their decimal max range.
   */
  COORDINATES_OUT_OF_RANGE,

  /**
   * The interpreted occurrence coordinates fall outside of the indicated country.
   */
  COUNTRY_COORDINATE_MISMATCH,

  /**
   * Interpreted country for dwc:country and dwc:countryCode contradict each other.
   */
  COUNTRY_MISMATCH,

  /**
   * Uninterpretable country values found.
   */
  COUNTRY_INVALID,

  /**
   * The interpreted country is based on the coordinates, not the verbatim string information.
   */
  COUNTRY_DERIVED_FROM_COORDINATES,

  /**
   * The interpreted continent and country do not match up.
   */
  CONTINENT_COUNTRY_MISMATCH,

  /**
   * Uninterpretable continent values found.
   */
  CONTINENT_INVALID,

  /**
   * The interpreted continent is based on the coordinates, not the verbatim string information.
   */
  CONTINENT_DERIVED_FROM_COORDINATES,

  /**
   * Latitude and longitude appear to be swapped.
   */
  PRESUMED_SWAPPED_COORDINATE,

  /**
   * Longitude appears to be negated, e.g. 32.3 instead of -32.3
   */
  PRESUMED_NEGATED_LONGITUDE,

  /**
   * Latitude appears to be negated, e.g. 32.3 instead of -32.3
   */
  PRESUMED_NEGATED_LATITUDE,

  /**
   * The recording date specified as the eventDate string and the individual year, month, day are contradicting.
   */
  RECORDED_DATE_MISMATCH,

  /**
   * A (partial) invalid date is given, such as a non existing date, invalid zero month, etc.
   */
  RECORDED_DATE_INVALID,

  /**
   * The recording date is highly unlikely, falling either into the future
   * or represents a very old date before 1600 that predates modern taxonomy.
   */
  RECORDED_DATE_UNLIKELY,

  /**
   * Matching to the taxonomic backbone can only be done using a fuzzy, non exact match.
   */
  TAXON_MATCH_FUZZY,

  /**
   * Matching to the taxonomic backbone can only be done on a higher rank and not the scientific name.
   */
  TAXON_MATCH_HIGHERRANK,

  /**
   * Matching to the taxonomic backbone cannot be done cause there was no match at all
   * or several matches with too little information to keep them apart (homonyms).
   */
  TAXON_MATCH_NONE,

  /**
   * Set if supplied depth is not given in the metric system, for example using feet instead of meters
   */
  DEPTH_NOT_METRIC,

  /**
   * Set if depth is larger than 11.000m or negative.
   */
  DEPTH_UNLIKELY,

  /**
   * Set if supplied min>max
   */
  DEPTH_MIN_MAX_SWAPPED,

  /**
   * Set if depth is a non numeric value
   */
  DEPTH_NON_NUMERIC,

  /**
   * Set if elevation is above the troposphere (17km) or below 11km (Mariana Trench).
   */
  ELEVATION_UNLIKELY,

  /**
   * Set if supplied min > max elevation
   */
  ELEVATION_MIN_MAX_SWAPPED,

  /**
   * Set if supplied elevation is not given in the metric system, for example using feet instead of meters
   */
  ELEVATION_NOT_METRIC,

  /**
   * Set if elevation is a non numeric value
   */
  ELEVATION_NON_NUMERIC,

  /**
   * A (partial) invalid date is given for dc:modified, such as a non existing date, invalid zero month, etc.
   */
  MODIFIED_DATE_INVALID,

  /**
   * The date given for dc:modified is in the future or predates unix time (1970).
   */
  MODIFIED_DATE_UNLIKELY,

  /**
   * The date given for dwc:dateIdentified is in the future or before Linnean times (1700).
   */
  IDENTIFIED_DATE_UNLIKELY,

  /**
   * The date given for dwc:dateIdentified is invalid and cant be interpreted at all.
   */
  IDENTIFIED_DATE_INVALID,

  /**
   * The given basis of record is impossible to interpret or seriously different from the recommended vocabulary.
   */
  BASIS_OF_RECORD_INVALID,

  /**
   * The given type status is impossible to interpret or seriously different from the recommended vocabulary.
   */
  TYPE_STATUS_INVALID,

  /**
   * An invalid date is given for dc:created of a multimedia object.
   */
  MULTIMEDIA_DATE_INVALID,

  /**
   * An invalid uri is given for a multimedia object.
   */
  MULTIMEDIA_URI_INVALID,

  /**
   * An invalid uri is given for dc:references.
   */
  REFERENCES_URI_INVALID,

  /**
   * The geodetic datum given is no interpretable value according to
   */
  GEODETIC_DATUM_INVALID;

  /**
   * All issues that indicate problems with the coordinates and thus should not be shown on maps.
   */
  public static final List<OccurrenceIssue> GEOSPATIAL_RULES = ImmutableList.of(ZERO_COORDINATE,
                                                                                   COORDINATES_OUT_OF_RANGE,
                                                                                   COUNTRY_COORDINATE_MISMATCH,
                                                                                   PRESUMED_SWAPPED_COORDINATE,
                                                                                   PRESUMED_NEGATED_LATITUDE,
                                                                                   PRESUMED_NEGATED_LONGITUDE);
  public static final List<OccurrenceIssue> TAXONOMIC_RULES = ImmutableList.of(TAXON_MATCH_FUZZY,
                                                                               TAXON_MATCH_HIGHERRANK,
                                                                               TAXON_MATCH_NONE);

}
