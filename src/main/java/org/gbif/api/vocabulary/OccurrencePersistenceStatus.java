package org.gbif.api.vocabulary;

/**
 * These are the possible states of an {@link org.gbif.api.model.occurrence.Occurrence} as it moves through the processing and persistence chain. It
 * is used both in the processing logic as well as the messages that are exchanged as part of that processing.
 */
public enum OccurrencePersistenceStatus {
  /**
   * This occurrence has never been seen before.
   */
  NEW,

  /**
   * This occurrence previously existed and has now been updated with new information.
   */
  UPDATED,

  /**
   * This occurrence previously existed but there is no new information to propagate. This is typical of a crawl
   * in which the exact record that was harvested in the previous crawl is seen again.
   */
  UNCHANGED,

  /**
   * This occurrence has been deleted.
   */
  DELETED
}
