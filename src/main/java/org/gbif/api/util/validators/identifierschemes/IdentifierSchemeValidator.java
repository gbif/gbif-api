package org.gbif.api.util.validators.identifierschemes;

/**
 * Validation and normalization interface for identifier schemes.
 */
public interface IdentifierSchemeValidator {

  /**
   * Is the identifier value valid?.
   */
  boolean isValid(String value);

  /**
   * Transforms the value parameter into a recommended value according to a IdentifierScheme.
   */
  String normalize(String value);
}
