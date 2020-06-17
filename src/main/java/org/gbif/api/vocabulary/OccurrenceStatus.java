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
 * A statement about the presence or absence of an occurrence at a location at a time.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/occurrence_status_2020-05-14.xml">rs.gbif.org vocabulary</a>
 */
public enum OccurrenceStatus {
  PRESENT,
  ABSENT
}
