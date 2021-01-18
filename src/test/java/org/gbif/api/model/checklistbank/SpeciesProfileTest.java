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
package org.gbif.api.model.checklistbank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeciesProfileTest {

  @Test
  public void testEquals() {
    SpeciesProfile sp1 = new SpeciesProfile();
    sp1.setHybrid(true);
    sp1.setMassInGram(1000);
    sp1.setHabitat("Tropical forest");

    SpeciesProfile sp2 = new SpeciesProfile();
    sp2.setHybrid(true);
    sp2.setMassInGram(1000);
    sp2.setHabitat("Tropical forest");

    assertEquals(sp1, sp2);
  }
}
