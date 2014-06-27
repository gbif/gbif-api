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
 * A simple enumeration of all DarwinCore values of BasisOfRecord legal for occurrences.
 *
 * @see <a href="http://rs.tdwg.org/dwc/terms/type-vocabulary/index.htm">Darwin Core Type Vocabulary</a>
 * @see <a href="http://rs.gbif.org/vocabulary/dwc/basis_of_record.xml">GBIF Vocabulary</a>
 */
public enum BasisOfRecord {

  /**
   * An occurrence record describing a preserved specimen.
   */
  PRESERVED_SPECIMEN,

  /**
   * An occurrence record describing a fossilized specimen.
   */
  FOSSIL_SPECIMEN,

  /**
   * An occurrence record describing a living specimen, e.g. managed animals in a zoo
   * or cultivated plants in a garden.
   */
  LIVING_SPECIMEN,

  /**
   * An occurrence record describing an observation.
   */
  OBSERVATION,

  /**
   * An occurrence record describing an observation made by one or more people.
   */
  HUMAN_OBSERVATION,

  /**
   * An occurrence record describing an observation made by a machine.
   */
  MACHINE_OBSERVATION,

  /**
   * An occurrence record based on samples taken from other specimens or the environment.
   */
  MATERIAL_SAMPLE,

  /**
   * An occurrence record based on literature alone.
   */
  LITERATURE,

  /**
   * Unknown basis for the record.
   */
  UNKNOWN

}
