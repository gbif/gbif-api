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
 * <p>An enumeration for all GBIF Regions.  These are based on
 * <a href="https://www.nature.com/articles/sdata20167/figures/1">IPBES regions</a>.
 *
 * <p><em>This is a political division,</em> part of GBIF's governance structure.
 * For a geographical division, see {@link Continent}.
 *
 * @see <a href="https://www.gbif.org/the-gbif-network">The GBIF Network</a>
 */
public enum GbifRegion {

  /**
   * Africa: IPBES regions North Africa, West Africa, Central Africa, East Africa and adjacent islands, Southern Africa.
   */
  AFRICA,

  /**
   * Asia: IPBES regions Western Asia, South Asia, North-East Asia, South-East Asia.
   */
  ASIA,

  /**
   * Europe and Central Asia: IPBES regions Central and Western Europe, Eastern Europe, Central Asia.
   */
  EUROPE,

  /**
   * North America: IPBES region North America.
   */
  NORTH_AMERICA,

  /**
   * Oceania: IPBES region Oceania.
   */
  OCEANIA,

  /**
   * Latin America and the Caribbean: IPBES regions Mesoamerica, Caribbean, South America.
   */
  LATIN_AMERICA,

  /**
   * Antarctica: "Excluded" IPBES regions, or GEO regions except Greenland.  Includes some islands in the Southern Ocean.
   */
  ANTARCTICA;

  /**
   * @param region GBIF's region name
   *
   * @return the matching GBIF region or null
   */
  public static GbifRegion fromString(String region) {
    return VocabularyUtils.lookupEnum(region, GbifRegion.class);
  }

}
