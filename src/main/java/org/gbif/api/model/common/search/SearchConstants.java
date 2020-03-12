/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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
