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
package org.gbif.api.model.occurrence;

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;

import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OccurrenceTest {

  @Test
  public void testGetHigherClassificationMap() throws Exception {
    Occurrence occ = new Occurrence();
    occ.setFamily("Plants family");
    occ.setFamilyKey(16);
    occ.setKingdom("Plants");
    occ.setKingdomKey(6);

    assertEquals(2, occ.getHigherClassificationMap().size());
    assertTrue(occ.getHigherClassificationMap().containsKey(16));
    assertTrue(occ.getHigherClassificationMap().containsKey(6));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants"));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants family"));

    occ.setNubKey(200);
    assertEquals(2, occ.getHigherClassificationMap().size());

    occ.setNubKey(16);
    assertEquals(1, occ.getHigherClassificationMap().size());
    assertTrue(occ.getHigherClassificationMap().containsKey(6));
    assertTrue(occ.getHigherClassificationMap().containsValue("Plants"));
    assertFalse(occ.getHigherClassificationMap().containsKey(16));
    assertFalse(occ.getHigherClassificationMap().containsValue("Plants family"));
  }

  @Test
  public void testProtocolAndHostCountry() {
    Occurrence occ = Occurrence.builder().key(1).datasetKey(UUID.randomUUID()).
      protocol(EndpointType.BIOCASE).hostCountry(Country.AFGHANISTAN).build();
    assertEquals(EndpointType.BIOCASE, occ.getProtocol());
    assertEquals(Country.AFGHANISTAN, occ.getHostCountry());

    occ = new Occurrence();
    occ.setKey(1);
    occ.setDatasetKey(UUID.randomUUID());
    occ.setHostCountry(Country.AFGHANISTAN);
    occ.setProtocol(EndpointType.BIOCASE);
    assertEquals(EndpointType.BIOCASE, occ.getProtocol());
    assertEquals(Country.AFGHANISTAN, occ.getHostCountry());
  }
}
