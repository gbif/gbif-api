/*
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

import org.gbif.api.util.VocabularyUtils;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Enumeration for all continents based on the 7 continent model found on
 * <a href="https://en.wikipedia.org/wiki/Continent#Number_of_continents">Wikipedia</a> and the
 * <a href="http://www.tdwg.org/standards/109">World Geographical Scheme for Recording Plant Distributions (WGSRPD)</a>.
 *
 * <p>In particular this splits the Americas into North and South America with North America including the Caribbean (except Trinidad and Tobago) and
 * reaching down and including Panama. See the <a href="https://github.com/gbif/continents/">GBIF Continents</a> for the exact divisions.
 *
 * <p>This is a geographical division.  For GBIF's political divisions, see {@link GbifRegion}.
 */
@Schema(
  description = "The continent, based on a 7 continent model described on [Wikipedia](https://en.wikipedia.org/wiki/Continent#Number_of_continents) " +
    "and the [World Geographical Scheme for Recording Plant Distributions (WGSRPD)](https://www.tdwg.org/standards/109).\n\n" +
    "In particular this splits the Americas into North and South America with North America including the Caribbean " +
    "(except Trinidad and Tobago) and reaching down and including Panama.\n\n" +
    "See the [GBIF Continents](https://github.com/gbif/continents/) for the exact divisions.\n\n" +
    "*This is a geographical division.  See `GBIFRegion` for GBIF's political divisions.*",
  externalDocs = @ExternalDocumentation(
    description = "API call to retrieve all official values.",
    url = "https://api.gbif.org/v1/enumeration/basic/Continent"
  )
)
public enum Continent {

  /**
   * Africa.
   */
  AFRICA("Africa"),

  /**
   * Antarctica.
   */
  ANTARCTICA("Antarctica"),

  /**
   * Asia.
   */
  ASIA("Asia"),

  /**
   * Oceania.
   */
  OCEANIA("Oceania"),

  /**
   * Europe.
   */
  EUROPE("Europe"),

  /**
   * North America
   *
   * <p>This includes the Caribbean and Central America.
   */
  NORTH_AMERICA("North America"),

  /**
   * South America.
   */
  SOUTH_AMERICA("South America");

  private final String title;

  Continent(String title) {
    this.title = title;
  }

  /**
   * @return the continent name in the English language.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param continent name
   *
   * @return the matching continent or null
   */
  public static Continent fromString(String continent) {
    try {
      return (Continent) VocabularyUtils.lookupEnum(continent, Continent.class);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
