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
package org.gbif.api.util;

import org.gbif.api.vocabulary.Extension;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static org.gbif.api.vocabulary.Extension.AMPLIFICATION;
import static org.gbif.api.vocabulary.Extension.CHRONOMETRIC_AGE;
import static org.gbif.api.vocabulary.Extension.CHRONOMETRIC_DATE;
import static org.gbif.api.vocabulary.Extension.CLONING;
import static org.gbif.api.vocabulary.Extension.EXTENDED_MEASUREMENT_OR_FACT;
import static org.gbif.api.vocabulary.Extension.GEL_IMAGE;
import static org.gbif.api.vocabulary.Extension.GERMPLASM_ACCESSION;
import static org.gbif.api.vocabulary.Extension.GERMPLASM_MEASUREMENT_SCORE;
import static org.gbif.api.vocabulary.Extension.GERMPLASM_MEASUREMENT_TRAIT;
import static org.gbif.api.vocabulary.Extension.GERMPLASM_MEASUREMENT_TRIAL;
import static org.gbif.api.vocabulary.Extension.IDENTIFICATION;
import static org.gbif.api.vocabulary.Extension.IDENTIFIER;
import static org.gbif.api.vocabulary.Extension.LOAN;
import static org.gbif.api.vocabulary.Extension.MATERIAL_SAMPLE;
import static org.gbif.api.vocabulary.Extension.MEASUREMENT_OR_FACT;
import static org.gbif.api.vocabulary.Extension.PERMIT;
import static org.gbif.api.vocabulary.Extension.PREPARATION;
import static org.gbif.api.vocabulary.Extension.PRESERVATION;
import static org.gbif.api.vocabulary.Extension.REFERENCE;
import static org.gbif.api.vocabulary.Extension.RESOURCE_RELATIONSHIP;

/** List of extensions used for hdfs/hive tables  */
public class ExtensionTable {

  private static final Map<Extension, String> EXTENSION_MAP = new EnumMap<>(Extension.class);

  static {
    EXTENSION_MAP.put(MEASUREMENT_OR_FACT, "http://rs.gbif.org/extension/dwc/measurements_or_facts.xml");
    EXTENSION_MAP.put(IDENTIFICATION, "http://rs.gbif.org/extension/dwc/identification.xml");
    EXTENSION_MAP.put(RESOURCE_RELATIONSHIP, "http://rs.gbif.org/extension/dwc/resource_relation.xml");
    EXTENSION_MAP.put(AMPLIFICATION, "http://rs.gbif.org/extension/ggbn/amplification.xml");
    EXTENSION_MAP.put(CLONING, "http://rs.gbif.org/extension/ggbn/cloning.xml");
    EXTENSION_MAP.put(GEL_IMAGE, "http://rs.gbif.org/extension/ggbn/gelimage.xml");
    EXTENSION_MAP.put(LOAN, "http://rs.gbif.org/extension/ggbn/loan.xml");
    EXTENSION_MAP.put(MATERIAL_SAMPLE, "http://rs.gbif.org/extension/ggbn/materialsample.xml");
    EXTENSION_MAP.put(PERMIT, "http://rs.gbif.org/extension/ggbn/permit.xml");
    EXTENSION_MAP.put(PREPARATION, "http://rs.gbif.org/extension/ggbn/preparation.xml");
    EXTENSION_MAP.put(PRESERVATION, "http://rs.gbif.org/extension/ggbn/preservation.xml");
    EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_SCORE, "http://rs.gbif.org/extension/germplasm/MeasurementScore.xml");
    EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_TRAIT, "http://rs.gbif.org/extension/germplasm/MeasurementTrait.xml");
    EXTENSION_MAP.put(GERMPLASM_MEASUREMENT_TRIAL, "http://rs.gbif.org/extension/germplasm/MeasurementTrial.xml");
    EXTENSION_MAP.put(GERMPLASM_ACCESSION, "http://rs.gbif.org/extension/germplasm/GermplasmAccession.xml");
    EXTENSION_MAP.put(EXTENDED_MEASUREMENT_OR_FACT, "http://rs.gbif.org/extension/obis/extended_measurement_or_fact.xml");
    EXTENSION_MAP.put(CHRONOMETRIC_AGE, "http://rs.gbif.org/extension/zooarchnet/ChronometricAge_2020-10-06.xml");
    EXTENSION_MAP.put(CHRONOMETRIC_DATE, "http://rs.gbif.org/extension/zooarchnet/chronometricDate.xml");
    EXTENSION_MAP.put(REFERENCE, "http://rs.gbif.org/extension/gbif/1.0/references.xml");
    EXTENSION_MAP.put(IDENTIFIER, "http://rs.gbif.org/extension/gbif/1.0/identifier.xml");
  }

  private ExtensionTable() {
  }

  public static Map<Extension, String> getExtensionResourceMap() {
    return EXTENSION_MAP;
  }

  public static Set<Extension> getExtensionList() {
    return EXTENSION_MAP.keySet();
  }

}
