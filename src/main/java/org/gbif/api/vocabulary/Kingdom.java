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
 * A simple enumeration of all kingdoms found in the GBIF Backbone Taxonomy and also Catalogue of Life.
 */
public enum Kingdom {

  INCERTAE_SEDIS,
  ANIMALIA,
  ARCHAEA,
  BACTERIA,
  CHROMISTA,
  FUNGI,
  PLANTAE,
  PROTOZOA,
  VIRUSES;

  // the constant name id used in checklistbank for the "incertae sedis" name string
  private static final int INCERTAE_SEDIS_NAME_ID = 9;

  /**
   * Looks up a kingdom by its nub usage id.
   *
   * @param usageID the nub usage id to lookup, must be < 9 to get a result.
   *
   * @return the matching kingdom or null
   */
  public static Kingdom byNubUsageId(Integer usageID) {
    for (Kingdom term : Kingdom.values()) {
      if (term.nubUsageID().equals(usageID)) {
        return term;
      }
    }
    return null;
  }

  /**
   * @return a unique single capital char representing the kingdom.
   */
  public String scientificName() {
    String lower = name().replaceAll("_", " ").toLowerCase();
    return this==INCERTAE_SEDIS ? lower : Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
  }

  /**
   * @return the nub usage id for the kingdom.
   */
  public Integer nubUsageID() {
    return ordinal();
  }

  /**
   * @return the checklistbank name id for the kingdom.
   */
  public Integer clbNameID() {
    return this==INCERTAE_SEDIS ? INCERTAE_SEDIS_NAME_ID : ordinal();
  }

}
