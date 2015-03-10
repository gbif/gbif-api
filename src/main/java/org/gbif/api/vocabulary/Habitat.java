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

/**
 * Very coarse habitat vocabulary matching the boolean biome flags found in the species profile extension.
 * These also match the classifications provided by the WWF, BBC.
 *
 * http://www.ucmp.berkeley.edu/exhibits/biomes/index.php
 * http://wwf.panda.org/about_our_earth/ecoregions/about/habitat_types/
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/speciesprofile.xml">Species Profile Extension</a>
 */
public enum Habitat {

  /**
   * Marine habitats including the deep & open ocean, reefs and estuaries.
   *
   * @see <a href="http://wwf.panda.org/about_our_earth/ecoregions/about/habitat_types/selecting_marine_ecoregions/">WWF</a>
   * @see <a href="http://www.bbc.co.uk/nature/habitats#marine">BBC</a>
   * @see <a href="http://www.ucmp.berkeley.edu/exhibits/biomes/marine.php">UCMP Berkeley</a>
   *
   */
  MARINE,

  /**
   * Freshwater habitats including rivers, lakes, ponds, wetlands, bogs, marsh, swamp and brackish waters!
   *
   * @see <a href="http://wwf.panda.org/about_our_earth/ecoregions/about/habitat_types/selecting_freshwater_ecoregions/">WWF</a>
   * @see <a href="http://www.bbc.co.uk/nature/habitats#freshwater">BBC</a>
   * @see <a href="http://www.ucmp.berkeley.edu/exhibits/biomes/freshwater.php">UCMP Berkeley</a>
   *
   */
  FRESHWATER,

  /**
   * Terrestrial habitats cover all habitats on land
   * including forests, deserts, grasslands, meadows, tundra, mangroves, farmland.
   *
   * @see <a href="http://wwf.panda.org/about_our_earth/ecoregions/about/habitat_types/selecting_terrestrial_ecoregions/">WWF</a>
   * @see <a href="http://www.bbc.co.uk/nature/habitats#land">BBC</a>
   * @see <a href="http://www.ucmp.berkeley.edu/exhibits/biomes/index.php">UCMP Berkeley</a>
   *
   */
  TERRESTRIAL;
}
