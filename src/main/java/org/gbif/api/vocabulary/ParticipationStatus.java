/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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
 * Enumeration for all of GBIF's participation statuses.
 */
public enum ParticipationStatus {

  VOTING,
  ASSOCIATE,
  OBSERVER,
  FORMER,
  AFFILIATE;

  /**
   * @param participationStatus GBIF node's participation status
   *
   * @return the matching participation status
   */
  public static ParticipationStatus fromString(String participationStatus) {
    return VocabularyUtils.lookupEnum(participationStatus, ParticipationStatus.class);
  }

}
