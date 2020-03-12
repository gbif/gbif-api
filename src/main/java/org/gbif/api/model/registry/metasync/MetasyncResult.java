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
