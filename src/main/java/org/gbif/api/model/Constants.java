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
package org.gbif.api.model;

import java.util.UUID;

/**
 * Common variables used by API.
 */
public final class Constants {

  /**
   * The key for the GBIF network.
   * @deprecated since we do not use a Network to group datasets registered in the GBIF registry
   */
  @Deprecated
  public static final UUID GBIF_NETWORK_KEY = UUID.fromString("542c9658-b512-4c1f-ae51-fdcf28c04b70");

  /**
   * The dataset key for the GBIF backbone (nub) taxonomy.
   */
  public static final UUID NUB_DATASET_KEY = UUID.fromString("d7dddbf4-2cf0-4f39-9b2a-bb099caae36c");

  /**
   * The dataset key for the Catalog of Life.
   */
  public static final UUID COL_DATASET_KEY = UUID.fromString("7ddf754f-d193-4cc9-b351-99906754a03b");

  /**
   * The maximum key for a nub usage. All non nub usages have a key above this limit, so it can be used as a simple way
   * to distinguish between nub and checklist usages.
   */
  public static final int NUB_MAXIMUM_KEY = 99999999;

  private Constants() {
    throw new UnsupportedOperationException("Can't initialize class");
  }

}
