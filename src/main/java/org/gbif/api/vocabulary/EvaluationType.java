package org.gbif.api.vocabulary;

/**
 * Type of Evaluation that can be used within a validation process.
 */
public enum EvaluationType {
  /**
   * Structural evaluation
   */
  STRUCTURE_EVALUATION,

  /**
   * Evaluation based on the interpretation of field(s)
   */
  INTERPRETATION_BASED_EVALUATION
}
