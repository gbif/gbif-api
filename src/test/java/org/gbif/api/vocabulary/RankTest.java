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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RankTest {

  @Test
  public void testIsInfraspecific() {
    assertFalse(Rank.SUPERFAMILY.isInfraspecific());
    assertFalse(Rank.KINGDOM.isInfraspecific());
    assertFalse(Rank.INFRAGENERIC_NAME.isInfraspecific());
    assertFalse(Rank.GENUS.isInfraspecific());
    assertFalse(Rank.SPECIES.isInfraspecific());
    assertTrue(Rank.SUBFORM.isInfraspecific());
    assertTrue(Rank.STRAIN.isInfraspecific());
    assertTrue(Rank.CULTIVAR.isInfraspecific());
    assertTrue(Rank.VARIETY.isInfraspecific());
    assertTrue(Rank.PATHOVAR.isInfraspecific());
    for (Rank r : Rank.values()) {
      if (r.isRestrictedToCode() == NomenclaturalCode.BACTERIAL)
      assertTrue(r.isInfraspecific());
    }
  }

  @Test
  public void testIsLinnean2() {
    for (Rank r : Rank.values()) {
      System.out.print("'"+r.name()+"', ");
    }

  }
  @Test
  public void testIsLinnean() {
    assertTrue(Rank.KINGDOM.isLinnean());
    assertTrue(Rank.PHYLUM.isLinnean());
    assertTrue(Rank.CLASS.isLinnean());
    assertTrue(Rank.ORDER.isLinnean());
    assertTrue(Rank.FAMILY.isLinnean());
    assertTrue(Rank.GENUS.isLinnean());
    assertTrue(Rank.SPECIES.isLinnean());
    assertFalse(Rank.SUBSECTION.isLinnean());
    assertFalse(Rank.SUBGENUS.isLinnean());
    assertFalse(Rank.SUPERFAMILY.isLinnean());
    assertFalse(Rank.INFRAGENERIC_NAME.isLinnean());
    assertFalse(Rank.PATHOVAR.isLinnean());
    assertFalse(Rank.CHEMOFORM.isLinnean());
  }

  @Test
  public void testIsDwC() {
    assertTrue(Rank.DWC_RANKS.contains(Rank.KINGDOM));
    assertTrue(Rank.DWC_RANKS.contains(Rank.PHYLUM));
    assertTrue(Rank.DWC_RANKS.contains(Rank.CLASS));
    assertTrue(Rank.DWC_RANKS.contains(Rank.ORDER));
    assertTrue(Rank.DWC_RANKS.contains(Rank.FAMILY));
    assertTrue(Rank.DWC_RANKS.contains(Rank.GENUS));
    assertTrue(Rank.DWC_RANKS.contains(Rank.SUBGENUS));
    assertTrue(Rank.DWC_RANKS.contains(Rank.SPECIES));
  }

  @Test
  public void testIsSpeciesOrBelow() {
    assertFalse(Rank.SUPERFAMILY.isSpeciesOrBelow());
    assertFalse(Rank.KINGDOM.isSpeciesOrBelow());
    assertFalse(Rank.INFRAGENERIC_NAME.isSpeciesOrBelow());
    assertFalse(Rank.GENUS.isSpeciesOrBelow());
    assertTrue(Rank.SPECIES.isSpeciesOrBelow());
    assertTrue(Rank.SUBFORM.isSpeciesOrBelow());
    assertTrue(Rank.STRAIN.isSpeciesOrBelow());
    assertTrue(Rank.CULTIVAR.isSpeciesOrBelow());
    assertTrue(Rank.VARIETY.isSpeciesOrBelow());
    assertTrue(Rank.PATHOVAR.isSpeciesOrBelow());
    assertTrue(Rank.NATIO.isSpeciesOrBelow());
    assertTrue(Rank.GREX.isSpeciesOrBelow());
  }

  @Test
  public void testIsLegacy() {
    assertTrue(Rank.NATIO.isLegacy());
    assertTrue(Rank.PROLES.isLegacy());
  }

  @Test
  public void testIsInfrageneric() {
    assertFalse(Rank.SUPERFAMILY.isInfrageneric());
    assertFalse(Rank.FAMILY.isInfrageneric());
    assertFalse(Rank.SUPRAGENERIC_NAME.isInfrageneric());
    assertFalse(Rank.SUBFAMILY.isInfrageneric());
    assertFalse(Rank.TRIBE.isInfrageneric());
    assertFalse(Rank.GENUS.isInfrageneric());

    assertTrue(Rank.SUBGENUS.isInfrageneric());
    assertTrue(Rank.INFRAGENERIC_NAME.isInfrageneric());
    assertTrue(Rank.SPECIES.isInfrageneric());
    assertTrue(Rank.SUBFORM.isInfrageneric());
    assertTrue(Rank.STRAIN.isInfrageneric());
    assertTrue(Rank.CULTIVAR.isInfrageneric());
    assertTrue(Rank.VARIETY.isInfrageneric());
  }

  @Test
  public void testIsSuprageneric() {
    assertTrue(Rank.SUPERFAMILY.isSuprageneric());
    assertTrue(Rank.KINGDOM.isSuprageneric());
    assertTrue(Rank.PHYLUM.isSuprageneric());
    assertTrue(Rank.SUPERFAMILY.isSuprageneric());
    assertTrue(Rank.SUPRAGENERIC_NAME.isSuprageneric());
    assertTrue(Rank.TRIBE.isSuprageneric());
    assertTrue(Rank.SUBFAMILY.isSuprageneric());
    assertFalse(Rank.INFRAGENERIC_NAME.isSuprageneric());
    assertFalse(Rank.GENUS.isSuprageneric());
    assertFalse(Rank.SPECIES.isSuprageneric());
    assertFalse(Rank.SUBFORM.isSuprageneric());
    assertFalse(Rank.STRAIN.isSuprageneric());
    assertFalse(Rank.CULTIVAR.isSuprageneric());
    assertFalse(Rank.VARIETY.isSuprageneric());
  }

  @Test
  public void testIsUncomparable() {
    assertFalse(Rank.KINGDOM.isUncomparable());
    assertFalse(Rank.PHYLUM.isUncomparable());
    assertFalse(Rank.CLASS.isUncomparable());
    assertFalse(Rank.ORDER.isUncomparable());
    assertFalse(Rank.FAMILY.isUncomparable());
    assertFalse(Rank.GENUS.isUncomparable());
    assertFalse(Rank.SPECIES.isUncomparable());
    assertFalse(Rank.SUBSECTION.isUncomparable());
    assertFalse(Rank.SUBGENUS.isUncomparable());
    assertFalse(Rank.SUPERFAMILY.isUncomparable());
    assertTrue(Rank.INFRAGENERIC_NAME.isUncomparable());
  }

  @Test
  public void testHigher() {
    assertFalse(Rank.SUPERFAMILY.higherThan(Rank.KINGDOM));
    assertFalse(Rank.SUPERFAMILY.higherThan(Rank.KINGDOM));
    assertFalse(Rank.SPECIES.higherThan(Rank.SUBGENUS));

    assertTrue(Rank.INFRASPECIFIC_NAME.higherThan(Rank.VARIETY));
    assertTrue(Rank.SUPERFAMILY.higherThan(Rank.FAMILY));
    assertTrue(Rank.SPECIES.higherThan(Rank.VARIETY));
    assertTrue(Rank.GENUS.higherThan(Rank.INFRAGENERIC_NAME));

    int expectedHigher = Rank.DWC_RANKS.size() - 1;
    for (Rank r : Rank.DWC_RANKS) {
      int higherCount = 0;
      for (Rank r2 : Rank.DWC_RANKS) {
        if (r.higherThan(r2)) {
          higherCount++;
        }
      }
      assertEquals(expectedHigher, higherCount);
      expectedHigher--;
    }

    // questionable
    assertFalse(Rank.UNRANKED.higherThan(Rank.VARIETY));
  }

}
