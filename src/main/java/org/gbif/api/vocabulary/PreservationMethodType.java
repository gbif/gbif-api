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
 * The definition of the available preservation method types (the method used to preserve specimens).
 * With no exceptions, it mirrors the Preservation Method GBIF Vocabulary:
 * link{http://rs.gbif.org/vocabulary/gbif/preservation_method.xml} and similarly This vocabulary was based on the
 * PreservationMethodClassVoc from the BioCASe MetaProfile.
 */
public enum PreservationMethodType {

  /**
   * No treatment.
   */
  NO_TREATMENT,
  /**
   * Alcohol.
   */
  ALCOHOL,
  /**
   * Deep frozen.
   */
  DEEP_FROZEN,
  /**
   * Dried.
   */
  DRIED,
  /**
   * Dried and pressed.
   */
  DRIED_AND_PRESSED,
  /**
   * Formalin.
   */
  FORMALIN,
  /**
   * Refrigerated.
   */
  REFRIGERATED,
  /**
   * Freeze dried.
   */
  FREEZE_DRIED,
  /**
   * Glycerin.
   */
  GLYCERIN,
  /**
   * Gum Arabic.
   */
  GUM_ARABIC,
  /**
   * Microscopic preparation.
   */
  MICROSCOPIC_PREPARATION,
  /**
   * Mounted.
   */
  MOUNTED,
  /**
   * Pinned.
   */
  PINNED,
  /**
   * Other.
   */
  OTHER

}
