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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContinentTest {

  @Test
  public void testGetTitle() {
    for (Continent c : Continent.values()) {
      assertNotNull(c.getTitle());
      assertTrue(c.getTitle().length() > 2);
    }
    assertEquals("North America", Continent.NORTH_AMERICA.getTitle());
    assertEquals("Asia", Continent.ASIA.getTitle());
  }

  @Test
  public void testFromEnum() {
    assertEquals(Continent.ANTARCTICA, Continent.fromString("ANTARCTICA"));
    assertEquals(Continent.ANTARCTICA, Continent.fromString(Continent.ANTARCTICA.getTitle()));
    assertEquals(Continent.SOUTH_AMERICA, Continent.fromString("SOUTH_AMERICA"));
    assertEquals(Continent.SOUTH_AMERICA, Continent.fromString(Continent.SOUTH_AMERICA.getTitle()));
  }
}
