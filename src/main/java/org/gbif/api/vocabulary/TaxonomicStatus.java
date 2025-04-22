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
 * The taxonomic status of a taxon.
 * For synonyms more detailed status values are provided which "subclass" synonym.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/taxonomic_status.xml">rs.gbif.org vocabulary</a>
 */
public enum TaxonomicStatus {

  ACCEPTED,

  /**
   * Treated as accepted, but doubtful whether this is correct.
   */
  DOUBTFUL,

  /**
   * A general synonym, the exact type is unknown.
   */
  SYNONYM,

  /**
   * More specific subclass of SYNONYM.
   */
  HETEROTYPIC_SYNONYM,

  /**
   * More specific subclass of SYNONYM.
   */
  HOMOTYPIC_SYNONYM,

  /**
   * More specific subclass of SYNONYM.
   */
  PROPARTE_SYNONYM,

  /**
   * More specific subclass of SYNONYM.
   */
  MISAPPLIED,

  /**
   * Names which are ambiguous because they point at the current species and one or more others
   * e.g. homonyms, pro-parte synonyms (in other words, names which appear more than in one place in the Catalogue).
   */
  AMBIGUOUS_SYNONYM,

  /**
   * "Treated as accepted, but doubtful whether this is correct." (as used in ChecklistBank).
   */
  PROVISIONALLY_ACCEPTED;

  public boolean isSynonym() {
    return !isAccepted();
  }

  public boolean isAccepted() {
    return this == ACCEPTED || this == DOUBTFUL;
  }
}
