package org.gbif.api.model.registry.metasync;

/**
 * Enumeration of all the different categories of results we want to report on during metadata synchronisation.
 */
public enum MetasyncResult {

  /**
   * The synchronization was successful.
   */
  OK,

  /**
   * Is any kind of error establishing a connection or during a connection that's usually on the network level
   * (connection refused, timeouts etc.).
   */
  IO_EXCEPTION,

  /**
   * Any kind of HTTP error (e.g. a non 200 response code).
   */
  HTTP_ERROR,

  /**
   * This means that we got a reply from the endpoint but it does not conform to what we expected (e.g. HTML instead of
   * XML).
   */
  PROTOCOL_ERROR,

  /**
   * Anything that doesn't fit in the former categories (e.g. invalid URI stored in our Registry).
   */
  OTHER_ERROR
}
