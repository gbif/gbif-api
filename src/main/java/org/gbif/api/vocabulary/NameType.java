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
 * A short classification of scientific name strings used in Checklist Bank.
 *
 * Deprecated.
 * Please use the name parser enum instead.
 * https://github.com/gbif/name-parser/blob/master/name-parser-api/src/main/java/org/gbif/nameparser/api/NameType.java
 */

@Deprecated
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
   * Operational Taxonomic Unit.
   * An OTU is a pragmatic definition to group individuals by similarity, equivalent to but not necessarily in line
   * with classical Linnaean taxonomy or modern Evolutionary taxonomy.
   *
   * A OTU usually refers to clusters of organisms, grouped by DNA sequence similarity of a specific taxonomic marker gene.
   * In other words, OTUs are pragmatic proxies for "species" at different taxonomic levels.
   *
   * Sequences can be clustered according to their similarity to one another,
   * and operational taxonomic units are defined based on the similarity threshold (usually 97% similarity) set by the researcher.
   * Typically, OTU's are based on similar 16S rRNA sequences.
   */
  OTU,

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
  NO_NAME,

  /**
   * A name that has been flagged by the name parser due to the use of blacklisted epithets/words.
   */
  BLACKLISTED;

  /**
   * Case insensitive lookup of a name type by its name that does not throw an exception but returns null
   * for not found name types.
   *
   * @param nameType case insensitive name of name type
   *
   * @return the matching NameType or null
   */
  public static NameType fromString(String nameType) {
    if (StringUtils.isNotEmpty(nameType)) {
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
    return this == SCIENTIFIC || this == INFORMAL || this == CULTIVAR || this == CANDIDATUS || this == DOUBTFUL || this == BLACKLISTED;
  }

}
