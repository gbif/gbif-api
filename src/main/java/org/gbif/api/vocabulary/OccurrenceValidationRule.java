package org.gbif.api.vocabulary;

/**
 * An enumeration of validation rules for single occurrence records.
 * Every rule has to be answered in a simple boolean result that is kept in the validations map of an interpreted
 * Occurrence.
 */
public enum OccurrenceValidationRule {
  /**
   * True if the interpreted WGS84 coordinates (lat/lon) have been transposed from a different datum.
   */
  COORDINATE_TRANSPOSED,

  /**
   * True if the interpreted occurrence coordinates fall into the indicated country.
   */
  COORDINATE_IN_COUNTRY
}
