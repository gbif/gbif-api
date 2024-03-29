/*
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
 * Vocabulary for the establishment means of a species.
 *
 * This enumeration is no longer used by the occurrence APIs, Checklistbank still relies on it.
 *
 * @see org.gbif.api.model.checklistbank.Distribution
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/establishment_means.xml">rs.gbif.org vocabulary</a>
 * @see <a href="https://dwc.tdwg.org/em/">Darwin Core vocabulary</a>
 */
@Deprecated
public enum EstablishmentMeans {

  NATIVE,
  INTRODUCED,
  NATURALISED,
  INVASIVE,
  MANAGED,
  UNCERTAIN

}
