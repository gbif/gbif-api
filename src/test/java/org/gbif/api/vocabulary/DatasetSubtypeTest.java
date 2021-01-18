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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DatasetSubtypeTest {

  @Test
  public void testFromString() {
    assertEquals(DatasetSubtype.INVENTORY_THEMATIC, DatasetSubtype.fromString("inventory_thematic"));
    assertEquals(DatasetSubtype.INVENTORY_THEMATIC, DatasetSubtype.fromString("inventory thematic"));
    assertEquals(DatasetSubtype.INVENTORY_THEMATIC, DatasetSubtype.fromString("inventory-thematic"));
    assertEquals(DatasetSubtype.INVENTORY_THEMATIC, DatasetSubtype.fromString("inventory.thematic"));
    assertEquals(DatasetSubtype.TAXONOMIC_AUTHORITY, DatasetSubtype.fromString("TAXONOMIC authority"));
    assertEquals(DatasetSubtype.NOMENCLATOR_AUTHORITY, DatasetSubtype.fromString("NOMENCLATOR AUTHORITY"));
    assertEquals(DatasetSubtype.INVENTORY_REGIONAL, DatasetSubtype.fromString("INVENTORY_REGIONAL"));
    assertEquals(DatasetSubtype.GLOBAL_SPECIES_DATASET, DatasetSubtype.fromString("GLOBAL_SPECIES_DATASET"));
    assertEquals(DatasetSubtype.DERIVED_FROM_OCCURRENCE, DatasetSubtype.fromString("DERIVED_FROM_OCCURRENCE"));
    assertEquals(DatasetSubtype.SPECIMEN, DatasetSubtype.fromString("SPECIMEN"));
    assertEquals(DatasetSubtype.OBSERVATION, DatasetSubtype.fromString("OBSERVATION"));
  }

  @Test
  public void testOccurrenceDatasetSubtypes() {
    assertEquals(2, DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES.size());
    assertTrue(DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES.contains(DatasetSubtype.OBSERVATION));
    assertTrue(DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES.contains(DatasetSubtype.SPECIMEN));
  }

  @Test
  public void testChecklistDatasetSubtypes() {
    assertEquals(6, DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.size());
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.INVENTORY_THEMATIC));
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.TAXONOMIC_AUTHORITY));
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.NOMENCLATOR_AUTHORITY));
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.INVENTORY_REGIONAL));
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.GLOBAL_SPECIES_DATASET));
    assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(DatasetSubtype.DERIVED_FROM_OCCURRENCE));
  }

  /**
   * A test to make sure all values of the enumeration are in at exactly one of the two sets (groups).
   */
  @Test
  public void testEnumerationGrouping() {
    for (DatasetSubtype e : DatasetSubtype.values()) {
      if (DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(e) && DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES
        .contains(e)) {
        fail("Enumeration present in both sets - can only be present in one");
      } else if (!DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(e) && !DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES
        .contains(e)) {
        fail("Enumeration not present in either set");
      } else {
        assertTrue(DatasetSubtype.CHECKLIST_DATASET_SUBTYPES.contains(e) || DatasetSubtype.OCCURRENCE_DATASET_SUBTYPES
          .contains(e));
      }
    }
  }
}
