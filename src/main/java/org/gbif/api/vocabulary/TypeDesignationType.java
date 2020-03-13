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

/**
 *A vocabulary to be used for reasons for a type designation of a specimen or name.
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/type_designation_type.xml">rs.gbif.org vocabulary</a>
 */
public enum TypeDesignationType {

  /**
   * If one nominal species is explicitly designated as the type species when a nominal genus-group taxon is established,
   * that nominal species is the type species (type by original designation).
   */
  ORIGINAL_DESIGNATION,

  /**
   *
   */
  PRESENT_DESIGNATION,

  /**
   *
   */
  SUBSEQUENT_DESIGNATION,

  /**
   * Type species by monotypy.
   * When an author establishes a new nominal genus-group taxon for a single taxonomic species and denotes
   * that species by an available name, the nominal species so named is the type species.
   */
  MONOTYPY,

  /**
   *
   */
  SUBSEQUENT_MONOTYPY,

  /**
   * If a valid species-group name, or its cited synonym, originally included in a nominal genus-group taxon
   * is identical with the name of that taxon, the nominal species denoted by that specific name (if available)
   * is the type species.
   */
  TAUTONYMY,

  /**
   * The identical spelling of a generic or subgeneric name and the specific or subspecific name of one
   * of its originally included nominal species or subspecies.
   */
  ABSOLUTE_TAUTONYMY,

  /**
   * The identical spelling of a new generic or subgeneric name established before 1931 and a pre-1758 name
   * cited as a synonym of only one of the species or subspecies originally included in that genus.
   */
  LINNAEAN_TAUTONYMY,

  /**
   * An explicit, published decision by the Commission or Congress
   */
  RULING_BY_COMMISSION

}
