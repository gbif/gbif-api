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
        assertEquals(Rank.PATHOVAR, Rank.inferRank(null, "Pseudomonas", "syringae ", "pv.", "aceris"));
        assertEquals(Rank.SUBSPECIES, Rank.inferRank(null, "Abies", "alba", "ssp.", null));
        assertEquals(Rank.SPECIES, Rank.inferRank(null, "Abies", null, "spec.", null));
        assertEquals(Rank.SUPRAGENERIC_NAME, Rank.inferRank("Neurolaenodinae", null, null, "ib.", null));
        assertEquals(Rank.SUPRAGENERIC_NAME, Rank.inferRank("Neurolaenodinae", null, null, "supersubtrib.", null));
        assertEquals(Rank.BIOVAR, Rank.inferRank(null, "Pseudomonas", "syringae ", "bv.", "aceris"));
        assertEquals(Rank.BIOVAR, Rank.inferRank(null, "Thymus", "vulgaris", "biovar", "geraniol"));
        assertEquals(Rank.CHEMOFORM, Rank.inferRank(null, "Thymus", "vulgaris", "ct.", "geraniol"));
        assertEquals(Rank.CHEMOFORM, Rank.inferRank(null, "Thymus", "vulgaris", "chemoform", "geraniol"));
        assertEquals(Rank.CHEMOVAR, Rank.inferRank(null, "Thymus", "vulgaris", "chemovar", "geraniol"));
        assertEquals(Rank.SEROVAR, Rank.inferRank(null, "Thymus", "vulgaris", "serovar", "geraniol"));

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
        assertTrue(Rank.PATHOVAR.isInfraspecific());
        for (Rank r : Rank.INFRASUBSPECIFIC_MICROBIAL_RANKS) {
          assertTrue(r.isInfraspecific());
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
        assertFalse(Rank.INFORMAL.isLinnean());
        assertFalse(Rank.PATHOVAR.isLinnean());
        assertFalse(Rank.CHEMOFORM.isLinnean());
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
  public void testIsMicrobial() {
    assertFalse(Rank.SUPERFAMILY.isMicrobial());
    assertFalse(Rank.KINGDOM.isMicrobial());
    assertFalse(Rank.PHYLUM.isMicrobial());
    assertFalse(Rank.SUPERFAMILY.isMicrobial());
    assertFalse(Rank.SUPRAGENERIC_NAME.isMicrobial());
    assertFalse(Rank.TRIBE.isMicrobial());
    assertFalse(Rank.SUBFAMILY.isMicrobial());
    assertFalse(Rank.INFRAGENERIC_NAME.isMicrobial());
    assertFalse(Rank.GENUS.isMicrobial());
    assertFalse(Rank.SPECIES.isMicrobial());
    assertFalse(Rank.SUBFORM.isMicrobial());
    assertFalse(Rank.STRAIN.isMicrobial());
    assertFalse(Rank.VARIETY.isMicrobial());

    assertTrue(Rank.PATHOVAR.isMicrobial());
    assertTrue(Rank.CHEMOFORM.isMicrobial());
    assertTrue(Rank.BIOVAR.isMicrobial());
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

    @Test
    public void testHigher() {
        assertFalse(Rank.SUPERFAMILY.higherThan(Rank.KINGDOM));
        assertFalse(Rank.SUPERFAMILY.higherThan(Rank.KINGDOM));
        assertFalse(Rank.SPECIES.higherThan(Rank.SUBGENUS));

        assertTrue(Rank.INFRASPECIFIC_NAME.higherThan(Rank.VARIETY));
        assertTrue(Rank.SUPERFAMILY.higherThan(Rank.FAMILY));
        assertTrue(Rank.SPECIES.higherThan(Rank.VARIETY));
        assertTrue(Rank.GENUS.higherThan(Rank.INFRAGENERIC_NAME));

        int expectedHigher = Rank.DWC_RANKS.size() -1;
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
        assertFalse(Rank.INFORMAL.higherThan(Rank.VARIETY));
        assertFalse(Rank.UNRANKED.higherThan(Rank.VARIETY));
    }

}
