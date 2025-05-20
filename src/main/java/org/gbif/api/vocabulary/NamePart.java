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
package org.gbif.api.vocabulary;

import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration to indicate a part of a canonical scientific name.
 *
 * Deprecated.
 * Please use the name parser enum instead.
 * https://github.com/gbif/name-parser/blob/master/name-parser-api/src/main/java/org/gbif/nameparser/api/NamePart.java
 */

@Deprecated
public enum NamePart {

  GENERIC,
  INFRAGENERIC,
  SPECIFIC,
  INFRASPECIFIC;

  /**
   * Case insensitive lookup of a NamePart by its name that does not throw an exception but returns null
   * for a not found NamePart.
   *
   * @param namePart case insensitive name of name part
   *
   * @return the matching NamePart or null
   */
  public static NamePart fromString(String namePart) {
    if (StringUtils.isNotEmpty(namePart)) {
      try {
        return valueOf(namePart.toUpperCase().trim());
      } catch (IllegalArgumentException e) {
        // swallow
      }
    }
    return null;
  }

}
