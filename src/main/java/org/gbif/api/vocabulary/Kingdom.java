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

  /**
   * Looks up a kingdom by its nub usage key.
   *
   * @param usageKey the nub usage key to lookup, must be < 9 to get a result.
   *
   * @return the matching kingdom or null
   */
  public static Kingdom byNubUsageKey(int usageKey) {
    try {
      return Kingdom.values()[usageKey];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("There is no kingdom with usage key " + usageKey);
    }
  }

  /**
   * @return a unique single capital char representing the kingdom.
   */
  public String scientificName() {
    String lower = name().replaceAll("_", " ").toLowerCase();
    return this==INCERTAE_SEDIS ? lower : Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
  }

  public int nubUsageKey() {
    return ordinal();
  }

  /**
   * @deprecated please use nubUsageKey() instead
   */
  @Deprecated
  public Integer nubUsageID() {
    return nubUsageKey();
  }

  /**
   * @deprecated please use byNubUsageId(int) instead
   */
  @Deprecated
  public static Kingdom byNubUsageId(int usageKey) {
    return byNubUsageKey(usageKey);
  }
}
