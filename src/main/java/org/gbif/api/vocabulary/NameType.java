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
 * A short classification of scientific name strings used in Checklist Bank.
 */
public enum NameType {

  /**
   * A scientific latin name that might contain authorship but is not any of the other name types below (virus, hybrid, cultivar, etc).
   */
  SCIENTIFIC,

  /**
   * A virus name.
   */
  VIRUS,

  /**
   * A hybrid <b>formula</b> (not a hybrid name).
   */
  HYBRID,

  /**
   * A scientific name with some informal addition like "cf." or indetermined like Abies spec.
   */
  INFORMAL,

  /**
   * A cultivated plant name.
   */
  CULTIVAR,

  /**
   * Candidatus is a component of the taxonomic name for a bacterium that cannot be maintained in a
   * Bacteriology Culture Collection. It is an interim taxonomic status for noncultivable organisms.
   * An example would be "Candidatus Phytoplasma allocasuarinae".
   * This can be abbreviated to "Ca. Phytoplasma allocasuarinae".
   *
   * @see <a href="http://en.wikipedia.org/wiki/Candidatus">wikipedia</a>
   * @see <a href="http://www.bacterio.cict.fr/candidatus.html">J.P. Euzéby</a>
   *
   */
  CANDIDATUS,

  /**
   * Doubtful whether this is a scientific name at all.
   */
  DOUBTFUL,

  /**
   * A placeholder name like "incertae sedis" or "unknown genus".
   */
  PLACEHOLDER,

  /**
   * Surely not a scientific name of any kind.
   */
  NO_NAME;

  /**
   * Case insensitive lookup of a name type by its name that does not throw an exception but returns null
   * for not found name types.
   *
   * @param nameType case insensitive name of name type
   *
   * @return the matching NameType or null
   */
  public static NameType fromString(String nameType) {
    if (!Strings.isNullOrEmpty(nameType)) {
      try {
        return valueOf(nameType.toUpperCase().trim());
      } catch (IllegalArgumentException e) {
        // swallow
      }
    }
    return null;
  }

  /**
   * @return true if the type of name is included in the GBIF backbone
   */
  public boolean isBackboneType() {
    return this == SCIENTIFIC || this == VIRUS || this == DOUBTFUL;
  }

  /**
   * @return true if the GBIF name parser can parse such a name into a ParsedName instance
   */
  public boolean isParsable() {
    return this == SCIENTIFIC || this == INFORMAL || this == CULTIVAR || this == CANDIDATUS || this == DOUBTFUL;
  }

}
