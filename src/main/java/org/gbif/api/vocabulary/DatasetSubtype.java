/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.vocabulary;

import org.gbif.api.util.VocabularyUtils;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Enumeration for all possible dataset subtypes.
 */
public enum DatasetSubtype {
  /**
   * A taxonomic checklist that is authoritative in its classification and synonymy.
   */
  TAXONOMIC_AUTHORITY,
  /**
   * A nomenclatoral checklist that is authoritative in the pure names and publication information.
   */
  NOMENCLATOR_AUTHORITY,
  /**
   * A thematic checklist that has a theme for grouping names, e.g. parasites of elephants.
   */
  INVENTORY_THEMATIC,
  /**
   * A regional checklist that has a regional commonality in grouping names, e.g. species in a protected area.
   */
  INVENTORY_REGIONAL,
  /**
   * A taxonomic checklist with a global, spatial coverage.
   * This subtype is used in particular by the catalogue of life to assemble its aggregated checklist.
   */
  GLOBAL_SPECIES_DATASET,
  /**
   * A taxonomic checklist that has been derived from data that was originally occurrence data.
   */
  DERIVED_FROM_OCCURRENCE,
  /**
   * Specimen data. Possible museum collection.
   */
  SPECIMEN,
  /**
   * Observation data. Possible monitoring and field observations.
   */
  OBSERVATION;

  /**
   * A set of all DatasetSubtype that belong to DatasetType OCCURRENCE.
   *
   * @see DatasetType#OCCURRENCE
   */
  public static final Set<DatasetSubtype> OCCURRENCE_DATASET_SUBTYPES = ImmutableSet.of(SPECIMEN, OBSERVATION);

  /**
   * A set of all DatasetSubtype that belong to DatasetType CHECKLIST.
   *
   * @see DatasetType#CHECKLIST
   */
  public static final Set<DatasetSubtype> CHECKLIST_DATASET_SUBTYPES = ImmutableSet
    .of(TAXONOMIC_AUTHORITY, NOMENCLATOR_AUTHORITY, INVENTORY_THEMATIC, INVENTORY_REGIONAL, GLOBAL_SPECIES_DATASET,
      DERIVED_FROM_OCCURRENCE);

  /**
   * @return the matching DatasetSubtype or null
   */
  public static DatasetSubtype fromString(String datasetSubType) {
    return (DatasetSubtype) VocabularyUtils.lookupEnum(datasetSubType, DatasetSubtype.class);
  }
}
