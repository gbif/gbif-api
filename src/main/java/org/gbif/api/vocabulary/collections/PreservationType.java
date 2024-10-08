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
package org.gbif.api.vocabulary.collections;

/**
 * Mechanism of preservation applied to a collection.
 *
 * Note: The descriptions are the original values migrated from GRBIO.
 *
 * Deprecated to use the vocabulary server instead.
 */
@Deprecated
public enum PreservationType {

  STORAGE_OUTDOORS("Storage Environment: Ambient uncontrolled environment (outdoors)"),
  STORAGE_INDOORS("Storage Environment: Ambient controlled environment (indoors)"),
  STORAGE_CONTROLLED_ATMOSPHERE("Storage Environment: Controlled atmosphere (N, C02, humidity)"),
  STORAGE_FROZEN_MINUS_20("Storage Environment: Frozen (-20)"),
  STORAGE_FROZEN_MINUS_80("Storage Environment: Frozen (-80)"),
  STORAGE_FROZEN_BETWEEN_MINUS_132_AND_MINUS_196("Storage Environment: Frozen (-132 - -196)"),
  STORAGE_OTHER("Storage Environment: Other (please define)"),
  STORAGE_REFRIGERATED("Storage Environment: Refrigerated (+4)"),
  STORAGE_RECORDED("Storage Environment: Recorded (digital, paper, film, audio, etc.)"),
  STORAGE_VACUUM("Storage Environment: Vacuum"),
  SAMPLE_CRYOPRESERVED("Sample Treatment: Cryopreserved"),
  SAMPLE_DRIED("Sample Treatment: Dried"),
  SAMPLE_EMBEDDED("Sample Treatment: Embedded"),
  SAMPLE_FLUID_PRESERVED("Sample Treatment: Fluid preserved"),
  SAMPLE_PINNED("Sample Treatment: Pinned"),
  SAMPLE_PRESSED("Sample Treatment: Pressed"),
  SAMPLE_SKELETONIZED("Sample Treatment: Skeletonized"),
  SAMPLE_SLIDE_MOUNT("Sample Treatment: Slide mount"),
  SAMPLE_SURFACE_COATING("Sample Treatment: Surface coating"),
  SAMPLE_TANNED("Sample Treatment: Tanned"),
  SAMPLE_WAX_BLOCK("Sample Treatment: Wax Block"),
  SAMPLE_OTHER("Sample Treatment: Other (please define)"),
  SAMPLE_FREEZE_DRYING("Sample Treatment: Freeze-drying (or lyophilization)");

  private String description;

  PreservationType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
