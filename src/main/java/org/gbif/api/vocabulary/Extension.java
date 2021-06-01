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

import org.gbif.api.jackson.ExtensionDeserializer;
import org.gbif.api.jackson.ExtensionKeyDeserializer;
import org.gbif.api.jackson.ExtensionSerializer;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Enumeration of dwc extensions for both Occurrence and Taxon that are indexed by GBIF.
 *
 * @see <a href="http://rs.gbif.org/extension">GBIF Resources</a>
 */
@JsonSerialize(using = ExtensionSerializer.class, keyUsing = ExtensionSerializer.class)
@JsonDeserialize(using = ExtensionDeserializer.class, keyUsing = ExtensionKeyDeserializer.class)
public enum Extension {

  /**
   * @see <a href="http://rs.gbif.org/extension/audubon/audubon.xml">extension definition</a>
   */
  AUDUBON("http://rs.tdwg.org/ac/terms/Multimedia"),

  /**
   * @see <a href="http://data.ggbn.org/schemas/ggbn/terms/Amplification">extension definition</a>
   */
  AMPLIFICATION("http://data.ggbn.org/schemas/ggbn/terms/Amplification"),

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
   * @see <a href="http://rs.gbif.org/extension/gbif/1.0/references.xml">extension definition</a>
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
  VERNACULAR_NAME("http://rs.gbif.org/terms/1.0/VernacularName"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/cloning.xml">extension definition</a>
   */
  CLONING("http://data.ggbn.org/schemas/ggbn/terms/Cloning"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/gelimage.xml">extension definition</a>
   */
  GEL_IMAGE("http://data.ggbn.org/schemas/ggbn/terms/GelImage"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/loan.xml">extension definition</a>
   */
  LOAN("http://data.ggbn.org/schemas/ggbn/terms/Loan"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/materialsample.xml">extension definition</a>
   */
  MATERIAL_SAMPLE("http://data.ggbn.org/schemas/ggbn/terms/MaterialSample"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/permit.xml">extension definition</a>
   */
  PERMIT("http://data.ggbn.org/schemas/ggbn/terms/Permit"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/preparation.xml">extension definition</a>
   */
  PREPARATION("http://data.ggbn.org/schemas/ggbn/terms/Preparation"),

  /**
   * @see <a href="http://rs.gbif.org/extension/ggbn/preservation.xml">extension definition</a>
   */
  PRESERVATION("http://data.ggbn.org/schemas/ggbn/terms/Preservation"),

  /**
   * @see <a href="http://rs.gbif.org/extension/obis/extended_measurement_or_fact.xml">extension definition</a>
   */
  EXTENDED_MEASUREMENT_OR_FACT("http://rs.iobis.org/obis/terms/ExtendedMeasurementOrFact"),

  /**
   * @see <a href="http://rs.gbif.org/extension/zooarchnet/ChronometricAge_2020-10-06.xml">extension definition</a>
   */
  CHRONOMETRIC_AGE("http://rs.tdwg.org/chrono/terms/ChronometricAge"),

  /**
   * @see <a href="http://rs.gbif.org/extension/zooarchnet/chronometricDate.xml">extension definition</a>
   */
  CHRONOMETRIC_DATE("http://zooarchnet.org/dwc/terms/ChronometricDate");


  private final String rowType;

  /**
   * @param rowType the case insensitive row type uri for the extension
   * @return the matching extension or null
   */
  public static Extension fromRowType(String rowType) {
    if (StringUtils.isNotEmpty(rowType)) {
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

  private static final Map<Extension, String> AVAILABLE_EXTENSION_MAP = new EnumMap<>(Extension.class);

  static {
    AVAILABLE_EXTENSION_MAP.put(AUDUBON, "https://rs.gbif.org/extension/ac/audubon_2020_10_06.xml");
    AVAILABLE_EXTENSION_MAP.put(IMAGE, "https://rs.gbif.org/extension/gbif/1.0/images.xml");
    AVAILABLE_EXTENSION_MAP.put(MULTIMEDIA, "http://rs.gbif.org/extension/gbif/1.0/multimedia.xml");
    AVAILABLE_EXTENSION_MAP.put(MEASUREMENT_OR_FACT, "http://rs.gbif.org/extension/dwc/measurements_or_facts.xml");
    AVAILABLE_EXTENSION_MAP.put(IDENTIFICATION, "http://rs.gbif.org/extension/dwc/identification.xml");
    AVAILABLE_EXTENSION_MAP.put(RESOURCE_RELATIONSHIP, "http://rs.gbif.org/extension/dwc/resource_relation.xml");
    AVAILABLE_EXTENSION_MAP.put(AMPLIFICATION, "http://rs.gbif.org/extension/ggbn/amplification.xml");
    AVAILABLE_EXTENSION_MAP.put(CLONING, "http://rs.gbif.org/extension/ggbn/cloning.xml");
    AVAILABLE_EXTENSION_MAP.put(GEL_IMAGE, "http://rs.gbif.org/extension/ggbn/gelimage.xml");
    AVAILABLE_EXTENSION_MAP.put(LOAN, "http://rs.gbif.org/extension/ggbn/loan.xml");
    AVAILABLE_EXTENSION_MAP.put(MATERIAL_SAMPLE, "http://rs.gbif.org/extension/ggbn/materialsample.xml");
    AVAILABLE_EXTENSION_MAP.put(PERMIT, "http://rs.gbif.org/extension/ggbn/permit.xml");
    AVAILABLE_EXTENSION_MAP.put(PREPARATION, "http://rs.gbif.org/extension/ggbn/preparation.xml");
    AVAILABLE_EXTENSION_MAP.put(PRESERVATION, "http://rs.gbif.org/extension/ggbn/preservation.xml");
    AVAILABLE_EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_SCORE, "http://rs.gbif.org/extension/germplasm/MeasurementScore.xml");
    AVAILABLE_EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_TRAIT, "http://rs.gbif.org/extension/germplasm/MeasurementTrait.xml");
    AVAILABLE_EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_TRIAL, "http://rs.gbif.org/extension/germplasm/MeasurementTrial.xml");
    AVAILABLE_EXTENSION_MAP.put(GERMPLASM_ACCESSION, "http://rs.gbif.org/extension/germplasm/GermplasmAccession.xml");
    AVAILABLE_EXTENSION_MAP.put(EXTENDED_MEASUREMENT_OR_FACT, "http://rs.gbif.org/extension/obis/extended_measurement_or_fact.xml");
    AVAILABLE_EXTENSION_MAP.put(CHRONOMETRIC_AGE, "http://rs.gbif.org/extension/dwc/ChronometricAge_2021-03-27.xml");
    AVAILABLE_EXTENSION_MAP.put(REFERENCE, "http://rs.gbif.org/extension/gbif/1.0/references.xml");
    AVAILABLE_EXTENSION_MAP.put(IDENTIFIER, "http://rs.gbif.org/extension/gbif/1.0/identifier.xml");
  }

  public static Map<Extension, String> availableExtensionResources() {
    return AVAILABLE_EXTENSION_MAP;
  }

  public static Set<Extension> availableExtensions() {
    return AVAILABLE_EXTENSION_MAP.keySet();
  }

}
