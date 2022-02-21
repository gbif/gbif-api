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
package org.gbif.api.model;

import java.util.UUID;

/**
 * Common variables used by API.
 */
public final class Constants {

  /**
   * The dataset key for the GBIF backbone (NUB) taxonomy.
   */
  public static final UUID NUB_DATASET_KEY = UUID.fromString("d7dddbf4-2cf0-4f39-9b2a-bb099caae36c");

  /**
   * The network key for the GBIF backbone sources.
   */
  public static final UUID NUB_NETWORK_KEY = UUID.fromString("029f9226-0d8a-4f28-97fe-13180e9eb0e5");

  /**
   * The dataset key for the Catalogue of Life.
   */
  public static final UUID COL_DATASET_KEY = UUID.fromString("7ddf754f-d193-4cc9-b351-99906754a03b");

  /**
   * The publishing organization key for Plazi.
   */
  public static final UUID PLAZI_ORG_KEY = UUID.fromString("7ce8aef0-9e92-11dc-8738-b8a03c50a862");

  /**
   * The network key for the Ocean Biodiversity Information System (OBIS).
   */
  public static final UUID OBIS_NETWORK_KEY = UUID.fromString("2b7c7b4f-4d4f-40d3-94de-c28b6fa054a6");

  /**
   * The dataset key for the EOD - eBird Observation Dataset.
   */
  public static final UUID EBIRD_DATASET_KEY = UUID.fromString("4fa7b334-ce0d-4e88-aaae-2e0c138d049e");

  /**
   * The dataset key for the iNaturalist Research-grade Observations dataset.
   */
  public static final UUID INATURALIST_DATASET_KEY = UUID.fromString("50c9509d-22c7-4a22-a47d-8c48425ef4a7");

  /**
   * The dataset key for the IUCN Red List of Threatened Species
   */
  public static final UUID IUCN_DATASET_KEY = UUID.fromString("19491596-35ae-4a91-9a98-85cf505f1bd3");

  /**
   * The maximum key for a NUB usage. All non-NUB usages have a key above this limit, so it can be used as a simple way
   * to distinguish between NUB and checklist usages.
   */
  public static final int NUB_MAXIMUM_KEY = 99999999;

  private Constants() {
    throw new UnsupportedOperationException("Can't initialize class");
  }

}
