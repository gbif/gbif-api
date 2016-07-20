/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.vocabulary;

import com.google.common.base.Strings;

/**
 * Enumeration to classify name usages by how they originated.
 */
public enum Origin {

  /**
   * Record came straight from source record.
   */
  SOURCE,

  /**
   * Implicit usage from a denormalised classification.
   */
  DENORMED_CLASSIFICATION,

  /**
   * Implicit usage from a verbatim parent name usage.
   */
  VERBATIM_PARENT,

  /**
   * Implicit usage from a verbatim accepted name usage.
  */
  VERBATIM_ACCEPTED,

    /**
   * Implicit usage from a verbatim basionym/original name.
   */
  VERBATIM_BASIONYM,

  /**
   * Duplicated usage from a single pro parte record.
   */
  PROPARTE,

  /**
   * Generated, missing autonym.
   */
  AUTONYM,

  /**
   * Generated, missing genus or species for "orphaned" lower name.
   */
  IMPLICIT_NAME,

  /**
   * Artificial accepted usage for a synonym if its missing to preserve the taxonomic hierarchy.
   */
  MISSING_ACCEPTED,

  /**
   * Placeholder usage for a missing or implicit basionym.
   */
  BASIONYM_PLACEHOLDER,

  /**
   * Implicit synonym based on the illegitimate ex author.
   * See ICN article 46: http://www.iapt-taxon.org/nomen/main.php?page=art46
   */
  EX_AUTHOR_SYNONYM,

  /**
   * Any other origin not covered by the above.
   */
  OTHER;

  /**
   * Case insensitive lookup of an Origin by its name that does not throw an exception but returns null
   * for not found origins.
   *
   * @param origin case insensitive name of the origin
   *
   * @return the matching Origin or null
   */
  public static Origin fromString(String origin) {
    if (!Strings.isNullOrEmpty(origin)) {
      try {
        return valueOf(origin.toUpperCase().trim());
      } catch (IllegalArgumentException e) {
        // swallow
      }
    }
    return null;
  }

}
