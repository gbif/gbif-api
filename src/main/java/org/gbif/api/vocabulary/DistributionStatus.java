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
 * A statement about the presence or absence of a Taxon at a Location.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/distribution_status_2020-05-14.xml">rs.gbif.org vocabulary</a>
 */
public enum DistributionStatus {

  /*
   * Javadoc definitions are copied from the vocabulary.
   */

  /**
   * There is at least one well documented record of the taxon's presence in the area.
   */
  PRESENT,

  /**
   * Subclass of present: Species observed frequently in most proper habitat.
   */
  COMMON,

  /**
   * Subclass of present: Species occurs regularly, but in small numbers. Requires careful searching of proper habitat.
   */
  RARE,

  /**
   * Subclass of present: May be common in certain years and entirely absent other years.
   */
  IRREGULAR,

  /**
   * The taxon is scored as being present in the area but there is some doubt over the evidence.
   * The doubt may be of different kinds including taxonomic or geographic imprecision in the records.
   */
  DOUBTFUL,

  /**
   * Subclass of absent: The organism is reported in some (gray) literature for a certain region, but is is erroneous.
   * Reason for exclusion could be a misidentification, an old report, a simple publishing mistake or any other or
   * unknown reason.
   */
  EXCLUDED,

  /**
   * There is evidence to document the absence of a taxon in the area. An extinct organism is absent while its
   * establishmentMeans is native.
   */
  ABSENT

}
