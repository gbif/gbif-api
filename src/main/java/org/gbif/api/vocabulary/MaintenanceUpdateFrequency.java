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
   * Further updates may still happen, but it is not known for sure. Note: typo matches EML enumeration.
   */
  UNKOWN,
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
