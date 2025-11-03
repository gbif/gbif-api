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
package org.gbif.api.service.occurrence;

import org.gbif.api.exception.ServiceUnavailableException;
import org.gbif.api.model.occurrence.Occurrence;
import org.gbif.api.model.occurrence.VerbatimOccurrence;

import java.util.UUID;

import jakarta.annotation.Nullable;

public interface OccurrenceService {

  /**
   * Attempt to find an occurrence matching the passed key.
   *
   * @param key that identifies an occurrence (Long rather than long for use in methods/classes using generic types)
   *
   * @return a matching occurrence, or null if no occurrence can be found
   *
   * @throws ServiceUnavailableException if the underlying data connection fails
   */
  @Nullable
  Occurrence get(Long key);

  /**
   * Attempt to find an occurrence matching the passed dataset key and occurrenceId.
   *
   * @param datasetKey datasey key that should contain the  occurrenceID
   *
   * @param occurrenceId that identifies an occurrence in a dataset
   *
   * @return a matching occurrence, or null if no occurrence can be found
   *
   * @throws ServiceUnavailableException if the underlying data connection fails
   */
  @Nullable
  Occurrence get(UUID datasetKey, String occurrenceId);

  /**
   * Attempt to find the verbatim values for an occurrence matching the passed key.
   *
   * @param key that identifies the occurrence
   *
   * @return the verbatim occurrence, or null if no occurrence can be found
   *
   * @throws ServiceUnavailableException if the underlying data connection fails
   */
  @Nullable
  VerbatimOccurrence getVerbatim(Long key);

  /**
   * Returns the "fragment" of raw data (either xml response fragment or json dwca fragment) for the passed in key.
   * Returns null if no fragment exists.
   *
   * @param key that identifies an occurrence
   *
   * @return the raw data fragment as a String, or null if no fragment is found
   *
   * @throws ServiceUnavailableException if the underlying data connection fails
   */
  @Nullable
  String getFragment(long key);
}
