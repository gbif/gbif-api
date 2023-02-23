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
 * A simple enumeration of all DarwinCore values of BasisOfRecord legal for occurrences.
 *
 * @see <a href="https://rs.gbif.org/vocabulary/dwc/basis_of_record.xml">GBIF Vocabulary</a>
 */
@Schema(
  description = "The values of the Darwin Core term Basis of Record which can apply to occurrences.\n\n" +
    "See GBIF's [Darwin Core Type Vocabulary](https://rs.gbif.org/vocabulary/dwc/basis_of_record.xml) for definitions.",
  externalDocs = @ExternalDocumentation(
    description = "API call to retrieve all official values.",
    url = "https://api.gbif.org/v1/enumeration/basic/BasisOfRecord"
  )
)
public enum BasisOfRecord {

  /**
   * An occurrence record describing a preserved specimen.
   */
  PRESERVED_SPECIMEN,

  /**
   * An occurrence record describing a fossilized specimen.
   */
  FOSSIL_SPECIMEN,

  /**
   * An occurrence record describing a living specimen, e.g. managed animals in a zoo
   * or cultivated plants in a garden.
   */
  LIVING_SPECIMEN,

  /**
   * An occurrence record describing an observation.
   */
  OBSERVATION,

  /**
   * An occurrence record describing an observation made by one or more people.
   */
  HUMAN_OBSERVATION,

  /**
   * An occurrence record describing an observation made by a machine.
   */
  MACHINE_OBSERVATION,

  /**
   * An occurrence record based on samples taken from other specimens or the environment.
   */
  MATERIAL_SAMPLE,

  /**
   * An occurrence record based on literature alone.
   * @deprecated use BasisOfRecord.OCCURRENCE instead.
   */
  @Deprecated
  LITERATURE,

  /**
   * A reference to or citation of one, a part of, or multiple specimens in scholarly publications.
   */
  MATERIAL_CITATION,

  /**
   * An existence of an Organism (sensu http://rs.tdwg.org/dwc/terms/Organism) at a particular place at a particular time.
   */
  OCCURRENCE,

  /**
   * Unknown basis for the record.
   * @deprecated replaced by BasisOfRecord.OCCURRENCE.
   */
  @Deprecated
  UNKNOWN

}
