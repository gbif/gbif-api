package org.gbif.api.vocabulary;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * An enumeration of validation rules for single occurrence records.
 * Every rule has to be answered in a simple boolean result that is kept in the validations map of an interpreted
 * Occurrence. All rules evaluating to true should be considered to have successfully passed the validation,
 * e.g. if COUNTRY_COORDINATE_MISMATCH is true, there is no mismatch!
 */
public enum OccurrenceValidationRule {

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
  PRESUMED_NEGATED_LATITUDE;

  public static List<OccurrenceValidationRule> GEOSPATIAL_RULES = ImmutableList.of(ZERO_COORDINATE,
                                                                                   COORDINATES_OUT_OF_RANGE,
                                                                                   COUNTRY_COORDINATE_MISMATCH,
                                                                                   PRESUMED_SWAPPED_COORDINATE,
                                                                                   PRESUMED_NEGATED_LATITUDE,
                                                                                   PRESUMED_NEGATED_LONGITUDE);
  public static List<OccurrenceValidationRule> TAXONOMIC_RULES = ImmutableList.of();

}
