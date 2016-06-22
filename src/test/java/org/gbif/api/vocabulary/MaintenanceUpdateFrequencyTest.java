package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaintenanceUpdateFrequencyTest {

  @Test
  public void testFromString() throws Exception {
    // with spaces
    assertEquals(MaintenanceUpdateFrequency.AS_NEEDED, MaintenanceUpdateFrequency.fromString("asNeeded"));
    assertEquals(MaintenanceUpdateFrequency.AS_NEEDED, MaintenanceUpdateFrequency.fromString("as needed"));
    assertEquals(MaintenanceUpdateFrequency.AS_NEEDED, MaintenanceUpdateFrequency.fromString("as-needed"));
    assertEquals(MaintenanceUpdateFrequency.AS_NEEDED, MaintenanceUpdateFrequency.fromString("ASNEEDED"));
    assertEquals(MaintenanceUpdateFrequency.OTHER_MAINTENANCE_PERIOD, MaintenanceUpdateFrequency.fromString("otherMaintenancePeriod"));
    assertEquals(MaintenanceUpdateFrequency.NOT_PLANNED, MaintenanceUpdateFrequency.fromString("notPlanned"));
    // typo matches vocabulary
    assertEquals(MaintenanceUpdateFrequency.UNKOWN, MaintenanceUpdateFrequency.fromString("unkown"));
  }
}
