/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
   * a scientific name which is not well formed.
   */
  SCINAME,

  /**
   * a well formed scientific name according to present nomenclatural rules.
   * This is either the canonical orcanonical with authorship.
   */
  WELLFORMED,

  /**
   * doubtful whether this is a scientific name at all.
   */
  DOUBTFUL,

  /**
   * surely not a scientific name.
   */
  BLACKLISTED,

  /**
   * a virus name.
   */
  VIRUS,

  /**
   * a hybrid <b>formula</b> (not a hybrid name).
   */
  HYBRID,

  /**
   * a scientific name with some informal addition like "cf." or indetermined like Abies spec.
   */
  INFORMAL,

  /**
   * a cultivated plant name.
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
  CANDIDATUS;

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
   * @return true if this name type is "better", ie more correct than the given name
   */
  public boolean isBetterThan(NameType other) {
    if (other == null) {
      return true;
    }
    if (BLACKLISTED != this) {
      if (INFORMAL == this && BLACKLISTED == other) {
        return true;
      } else if (DOUBTFUL == this && (BLACKLISTED == other || INFORMAL == other)) {
        return true;
      } else if ((SCINAME == this || VIRUS == this || HYBRID == this)
          && (BLACKLISTED == other || INFORMAL == other || DOUBTFUL == other)) {
        return true;
      } else if (WELLFORMED == this && WELLFORMED != other) {
        return true;
      }
    }
    return false;
  }

  public boolean isParsable() {
    return !(this == HYBRID || VIRUS == this || BLACKLISTED == this);
  }

}
