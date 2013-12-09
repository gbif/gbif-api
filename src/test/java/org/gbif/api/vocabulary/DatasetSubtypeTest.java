package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DatasetSubtypeTest {

  @Test
  public void testFromString() throws Exception {
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
