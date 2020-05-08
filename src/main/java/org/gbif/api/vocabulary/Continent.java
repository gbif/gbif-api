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

import org.gbif.api.util.VocabularyUtils;

/**
 * <p>Enumeration for all continents based on the 7 number model found on
 * <a href="https://en.wikipedia.org/wiki/Continent#Number_of_continents">Wikipedia</a>.
 *
 * <p>In particular this splits the Americas into North and South America with North America including the Caribbean and
 * reaching down and including Panama. See the <a href="https://commons.wikimedia.org/wiki/File:Continents_vide_couleurs.png">Wikipedia continent map</a> for the exact divisions.
 *
 * <p>This is a geographical division.  For GBIF's political divisions, see {@link GbifRegion}.
 *
 * <p>There is currently no validation of continents for GBIF occurrences, so there is no explicit definition of what
 * country is in each continent.
 */
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
