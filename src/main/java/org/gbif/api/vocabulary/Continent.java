/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
 * Enumeration for all continents based on <a href="http://en.wikipedia.org/wiki/Continent">Wikipedia</a>.
 */
public enum Continent {

  AFRICA,
  ANTARCTICA,
  ASIA,
  OCEANIA,
  EUROPE,
  NORTH_AMERICA,
  SOUTH_AMERICA;

  /**
   * @param continent name
   *
   * @return the matching continent or null
   */
  public static Continent fromString(String continent) {
    return (Continent) VocabularyUtils.lookupEnum(continent, Continent.class);
  }

}
