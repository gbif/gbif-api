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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.Rank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeSpecimenTest {

  @Test
  public void testEquals() {
    TypeSpecimen ta1 = new TypeSpecimen();
    ta1.setCatalogNumber("CA1000");
    ta1.setSourceTaxonKey(1000);
    ta1.setTaxonRank(Rank.SPECIES);

    TypeSpecimen ta2 = new TypeSpecimen();
    ta2.setCatalogNumber("CA1000");
    ta2.setSourceTaxonKey(1000);
    ta2.setTaxonRank(Rank.SPECIES);

    assertEquals(ta1, ta2);

  }

}
