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

public enum RelationType {

  /**
   * An agent endorses another one, typically this will be a Participant Node endorsing an Institution or Organisation.
   */
  ENDORSES,

  /**
   * An agent owns another agent. Typically this will be an organisation or institution hosting one or many resources.
   */
  OWNS,

  /**
   * An agent serves an entity (e.g. has an installation, or hosts a dataset on behalf of another organization).
   */
  SERVES,

  /**
   * An agent has a technical installation deployed by its own.
   */
  HAS_INSTALLATION,

  /**
   * An agent serves as part of a whole.
   */
  HAS_CONSTITUENT

}
