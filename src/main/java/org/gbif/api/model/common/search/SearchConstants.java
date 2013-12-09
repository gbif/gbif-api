package org.gbif.api.model.common.search;

/**
 * Contains common search constants.
 */
public class SearchConstants {

  /**
   * The http parameter name for the search query.
   */
  public static final String QUERY_PARAM = "q";


  /**
   * Wildcard character to be used in 'search all' operations and range queries; e.g.: [* TO 100].
   */
  public static final String QUERY_WILDCARD = "*";

  /**
   * Default limit value for auto-suggest services.
   */
  public static final Integer DEFAULT_SUGGEST_LIMIT = 4;

  /**
   * Default private constructor.
   */
  private SearchConstants() {
    // empty block
  }
}
