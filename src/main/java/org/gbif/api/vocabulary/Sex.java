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

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Vocabulary for the sex of an organism.
 *
 * @see <a href="http://rs.gbif.org/vocabulary/gbif/sex">rs.gbif.org vocabulary</a>
 */
@Schema(
  description = "The sex of the biological individual(s) represented in the Occurrence.\n\n" +
    "For definitions, see the [GBIF sex vocabulary](http://rs.gbif.org/vocabulary/gbif/sex)",
  externalDocs = @ExternalDocumentation(
    description = "Darwin Core definition",
    url = "https://rs.tdwg.org/dwc/terms/sex"
  )
)
@Deprecated
public enum Sex {

  /**
   * No sex determination ever has been observed, for example the Fungi Imperfecti:
   * http://en.wikipedia.org/wiki/Fungi_Imperfecti
   */
  NONE,

  MALE,

  FEMALE,

  /**
   * Being male and female at the same time.
   */
  HERMAPHRODITE

}
