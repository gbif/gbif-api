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

public class MaintenanceUpdateFrequencyTest {

  @Test
  public void testFromString() {
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
