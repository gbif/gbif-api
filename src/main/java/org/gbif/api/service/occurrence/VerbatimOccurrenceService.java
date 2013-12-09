/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
import org.gbif.api.model.occurrence.VerbatimOccurrence;

import javax.annotation.Nullable;

public interface VerbatimOccurrenceService {

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
  VerbatimOccurrence getVerbatim(Integer key);

}
