package org.gbif.api.model.crawler;

/**
 * This enum lists the state of an occurrence or checklist crawl.
 */
public enum ProcessState {

  /**
   * There is no data and nothing to do for further processing.
   */
  EMPTY,

  /**
   * Processing is currently running.
   */
  RUNNING,

  /**
   * Processing has finished, either successfully or with an error.
   * See a crawls' FinishedReason for more information.
   */
  FINISHED
}
