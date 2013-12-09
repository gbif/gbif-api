/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RankTest {

  private static final Map<String, Rank> NAMES = new HashMap<String, Rank>();

  static {
    NAMES.put("Asteraceae", Rank.FAMILY);
    NAMES.put("Magnoliophyta", Rank.PHYLUM);
    NAMES.put("Fabales", Rank.ORDER);
    NAMES.put("Hominidae", Rank.FAMILY);
    NAMES.put("Drosophilinae", Rank.SUBFAMILY);
    NAMES.put("Agaricomycetes", Rank.CLASS);
  }

  @Test
  public void testInferRank() {
    for (Map.Entry<String, Rank> stringRankEntry : NAMES.entrySet()) {
      assertEquals(stringRankEntry.getValue(), Rank.inferRank(stringRankEntry.getKey(), null, null, null, null));
    }

    assertEquals(Rank.SPECIES, Rank.inferRank("Abies", "Abies", "alba", null, null));
    assertEquals(Rank.SPECIES, Rank.inferRank("Abies", null, "alba", null, null));
    assertEquals(Rank.INFRAGENERIC_NAME, Rank.inferRank(null, "Abies", null, null, null));
    assertEquals(Rank.INFRAGENERIC_NAME, Rank.inferRank("", "Abies", null, null, null));
    assertEquals(Rank.VARIETY, Rank.inferRank(null, "Abies", "alba", "var.", "alpina"));
    assertEquals(Rank.SUBSPECIES, Rank.inferRank(null, "Abies", "alba", "ssp.", null));
    assertEquals(Rank.SPECIES, Rank.inferRank(null, "Abies", null, "spec.", null));
    assertEquals(Rank.SUPRAGENERIC_NAME, Rank.inferRank("Neurolaenodinae", null, null, "ib.", null));
    assertEquals(Rank.SUPRAGENERIC_NAME, Rank.inferRank("Neurolaenodinae", null, null, "supersubtrib.", null));

    // should not be able to infer the correct family
    assertEquals(Rank.UNRANKED, Rank.inferRank("Compositae", null, null, null, null));
  }

  @Test
  public void testIsInfraspecific() {
    assertFalse(Rank.SUPERFAMILY.isInfraspecific());
    assertFalse(Rank.KINGDOM.isInfraspecific());
    assertFalse(Rank.INFRAGENERIC_NAME.isInfraspecific());
    assertFalse(Rank.GENUS.isInfraspecific());
    assertFalse(Rank.SPECIES.isInfraspecific());
    assertTrue(Rank.SUBFORM.isInfraspecific());
    assertFalse(Rank.STRAIN.isInfraspecific());
    assertFalse(Rank.CULTIVAR.isInfraspecific());
    assertTrue(Rank.VARIETY.isInfraspecific());
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
    assertFalse(Rank.INFORMAL.isLinnean());
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
    assertTrue(Rank.INFORMAL.isUncomparable());
  }

  @Test
  public void testSuffixMap() {
    // wrong matches

    for (Map.Entry<String, Rank> stringRankEntry : NAMES.entrySet()) {
      Rank r = null;
      for (String suffix : Rank.SUFFICES_RANK_MAP.keySet()) {
        if (stringRankEntry.getKey().endsWith(suffix)) {
          r = Rank.SUFFICES_RANK_MAP.get(suffix);
          break;
        }
      }
      assertEquals(stringRankEntry.getValue(), r);
    }
  }

}
