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

import org.gbif.api.jackson.ExtensionDeserializer;
import org.gbif.api.jackson.ExtensionSerializer;

import com.google.common.base.Strings;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Enumeration of dwc extensions for both Occurrence and Taxon that are indexed by GBIF.
 *
 * @see <a href="http://rs.gbif.org/extension">GBIF Resources</a>
 */
@JsonSerialize(using= ExtensionSerializer.class)
@JsonDeserialize(using= ExtensionDeserializer.class)
public enum Extension {

  /**
   * @see <a href="http://rs.gbif.org/extension/audubon/audubon.xml">extension definition</a>
   */
  AUDUBON("http://rs.tdwg.org/ac/terms/Multimedia"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/description.xml">extension definition</a>
   */
  DESCRIPTION("http://rs.gbif.org/terms/1.0/Description"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/distribution.xml">extension definition</a>
   */
  DISTRIBUTION("http://rs.gbif.org/terms/1.0/Distribution"),

  /**
   * @see <a href="http://eol.org/schema/media_extension.xml">extension definition</a>
   */
  EOL_MEDIA("http://eol.org/schema/media/Document"),

  /**
   * @see <a href="http://eol.org/schema/reference_extension.xml">extension definition</a>
   */
  EOL_REFERENCE("http://eol.org/schema/reference/Reference"),

  /**
   * @see <a href="http://rs.gbif.org/extension/germplasm/GermplasmAccession.xml">extension definition</a>
   */
  GERMPLASM_ACCESSION("http://purl.org/germplasm/germplasmTerm#GermplasmAccession"),

  /**
   * @see <a href="http://rs.gbif.org/extension/germplasm/MeasurementScore.xml">extension definition</a>
   */
  GERMPLASM_MEASUREMENT_SCORE("http://purl.org/germplasm/germplasmTerm#MeasurementScore"),

  /**
   * @see <a href="http://rs.gbif.org/extension/germplasm/MeasurementTrait.xml">extension definition</a>
   */
  GERMPLASM_MEASUREMENT_TRAIT("http://purl.org/germplasm/germplasmTerm#MeasurementTrait"),

  /**
   * @see <a href="http://rs.gbif.org/extension/germplasm/MeasurementTrial.xml">extension definition</a>
   */
  GERMPLASM_MEASUREMENT_TRIAL("http://purl.org/germplasm/germplasmTerm#MeasurementTrial"),

  /**
   * @see <a href="http://rs.gbif.org/extension/dwc/identification.xml">extension definition</a>
   */
  IDENTIFICATION("http://rs.tdwg.org/dwc/terms/Identification"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/identifier.xml">extension definition</a>
   */
  IDENTIFIER("http://rs.gbif.org/terms/1.0/Identifier"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/images.xml">extension definition</a>
   */
  IMAGE("http://rs.gbif.org/terms/1.0/Image"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/images.xml">extension definition</a>
   */
  MEASUREMENT_OR_FACT("http://rs.tdwg.org/dwc/terms/MeasurementOrFact"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/multimedia.xml">extension definition</a>
   */
  MULTIMEDIA("http://rs.gbif.org/terms/1.0/Multimedia"),

  /**
   * @see <a href="http://rs.gbif.org/extension/dwc/measurements_or_facts.xml">extension definition</a>
   */
  REFERENCE("http://rs.gbif.org/terms/1.0/Reference"),

  /**
   * @see <a href="http://rs.gbif.org/extension/dwc/resource_relation.xml">extension definition</a>
   */
  RESOURCE_RELATIONSHIP("http://rs.tdwg.org/dwc/terms/ResourceRelationship"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/speciesprofile.xml">extension definition</a>
   */
  SPECIES_PROFILE("http://rs.gbif.org/terms/1.0/SpeciesProfile"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/typesandspecimen.xml">extension definition</a>
   */
  TYPES_AND_SPECIMEN("http://rs.gbif.org/terms/1.0/TypesAndSpecimen"),

  /**
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/vernacularname.xml">extension definition</a>
   */
  VERNACULAR_NAME("http://rs.gbif.org/terms/1.0/VernacularName");


  private final String rowType;

  /**
   * @param rowType the case insensitive row type uri for the extension
   *
   * @return the matching extension or null
   */
  public static Extension fromRowType(String rowType) {
    if (!Strings.isNullOrEmpty(rowType)) {
      for (Extension extension : Extension.values()) {
        if (rowType.equalsIgnoreCase(extension.getRowType())
            || rowType.equalsIgnoreCase(extension.name().replaceAll("_", ""))) {
          return extension;
        }
      }
    }
    return null;
  }

  Extension(String rowType) {
    this.rowType = rowType;
  }

  public String getRowType() {
    return rowType;
  }

}
