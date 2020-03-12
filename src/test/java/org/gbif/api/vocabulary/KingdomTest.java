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

public class KingdomTest {

  @Test
  public void testGetScientificName() throws Exception {
    assertEquals("Plantae", Kingdom.PLANTAE.scientificName());
    assertEquals("Animalia", Kingdom.ANIMALIA.scientificName());
    assertEquals("Chromista", Kingdom.CHROMISTA.scientificName());
    assertEquals("incertae sedis", Kingdom.INCERTAE_SEDIS.scientificName());
  }

  @Test
  public void testNubUsageKey() throws Exception {
    assertEquals(0, Kingdom.INCERTAE_SEDIS.nubUsageKey());
    assertEquals(1, Kingdom.ANIMALIA.nubUsageKey());
    assertEquals(6, Kingdom.PLANTAE.nubUsageKey());

    for (Kingdom k : Kingdom.values()) {
      assertEquals(k.ordinal(), k.nubUsageKey());
    }
  }

  @Test
  public void testByKey() throws Exception {
    assertEquals(Kingdom.INCERTAE_SEDIS, Kingdom.byNubUsageKey(0));
    assertEquals(Kingdom.ANIMALIA, Kingdom.byNubUsageKey(1));
    assertEquals(Kingdom.PLANTAE, Kingdom.byNubUsageKey(6));
    assertEquals(Kingdom.VIRUSES, Kingdom.byNubUsageKey(8));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testByKeyException() throws Exception {
    Kingdom.byNubUsageKey(9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testByKeyException2() throws Exception {
    Kingdom.byNubUsageKey(-1);
  }
}
