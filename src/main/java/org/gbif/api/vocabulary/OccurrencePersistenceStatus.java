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
