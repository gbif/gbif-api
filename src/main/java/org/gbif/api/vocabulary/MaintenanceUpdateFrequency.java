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

import org.gbif.api.util.VocabularyUtils;

/**
 * Enumeration for describing the frequency with which changes and additions are made to the dataset after the initial
 * dataset is completed. Based on the EML v2.1.1 MaintUpFreqType enumeration.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/eml/update_frequency.xml">rs.gbif.org vocabulary</a>
 * @see <a href="https://knb.ecoinformatics.org/#external//emlparser/docs/eml-2.1.1/./eml-dataset.html#MaintUpFreqType">EML
 * Enumeration</a>
 */
public enum MaintenanceUpdateFrequency {
  /**
   * Updated 1 time each day.
   */
  DAILY,
  /**
   * Updated 1 time each week.
   */
  WEEKLY,
  /**
   * Updated 1 time each month.
   */
  MONTHLY,
  /**
   * Updated 2 times each year.
   */
  BIANNUALLY,
  /**
   * Updated 1 time each year.
   */
  ANNUALLY,
  /**
   * Updated as needed.
   */
  AS_NEEDED,
  /**
   * Updated continually.
   */
  CONTINUALLY,
  /**
   * Updated at irregular intervals.
   */
  IRREGULAR,
  /**
   * Further updates are not planned.
   */
  NOT_PLANNED,
  /**
   * Further updates may still happen, but it is not known for sure.
   * Note: typo matches EML enumeration.
   * Deprecated - use {@link MaintenanceUpdateFrequency#UNKNOWN} instead.
   */
  @Deprecated
  UNKOWN,
  /**
   * Further updates may still happen, but it is not known for sure.
   */
  UNKNOWN,
  /**
   * Updated according to some other interval.
   */
  OTHER_MAINTENANCE_PERIOD;

  /**
   * @return the matching MaintenanceUpdateFrequency or null
   */
  public static MaintenanceUpdateFrequency fromString(String frequency) {
    return (MaintenanceUpdateFrequency) VocabularyUtils.lookupEnum(frequency, MaintenanceUpdateFrequency.class);
  }
}
