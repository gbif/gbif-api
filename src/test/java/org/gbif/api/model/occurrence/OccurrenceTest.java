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

import org.gbif.api.model.checklistbank.Identifier;
import org.gbif.api.model.checklistbank.NameUsage;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.OccurrenceValidationRule;
import org.gbif.dwc.terms.DwcTerm;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
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

    LinkedHashMap<Integer, String> clMap = ClassificationUtils.getHigherClassificationMap(occ);
    assertEquals(2, clMap.size());
    assertTrue(clMap.containsKey(16));
    assertTrue(clMap.containsKey(6));
    assertTrue(clMap.containsValue("Plants"));
    assertTrue(clMap.containsValue("Plants family"));

    occ.setTaxonKey(200);
    clMap = ClassificationUtils.getHigherClassificationMap(occ);
    assertEquals(2, clMap.size());

    occ.setTaxonKey(16);
    clMap = ClassificationUtils.getHigherClassificationMap(occ, occ.getTaxonKey(), null, null);
    assertEquals(1, clMap.size());
    assertTrue(clMap.containsKey(6));
    assertTrue(clMap.containsValue("Plants"));
    assertFalse(clMap.containsKey(16));
    assertFalse(clMap.containsValue("Plants family"));
  }

  @Test
  public void testProtocolAndPublishingCountry() {
    Occurrence occ = new Occurrence();
    occ.setKey(1);
    occ.setDatasetKey(UUID.randomUUID());
    occ.setProtocol(EndpointType.BIOCASE);
    occ.setPublishingCountry(Country.AFGHANISTAN);

    assertEquals(EndpointType.BIOCASE, occ.getProtocol());
    assertEquals(Country.AFGHANISTAN, occ.getPublishingCountry());
  }

  @Test
  public void testJsonSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    Occurrence occ = new Occurrence();
    occ.setFamily("Plants family");
    occ.setFamilyKey(16);
    occ.setKingdom("Plants");
    occ.setKingdomKey(6);

    occ.getFields().put(DwcTerm.catalogNumber, "MD10782");
    occ.getValidations().put(OccurrenceValidationRule.COORDINATES_OUT_OF_RANGE, true);

    String json = mapper.writeValueAsString(occ);
    assertEquals(occ, mapper.readValue(json, Occurrence.class));

    NameUsage u = new NameUsage();
    u.setKey(123);
    u.setDatasetKey(UUID.randomUUID());
    u.setNumDescendants(321);
    u.getNomenclaturalStatus().add(NomenclaturalStatus.AMBIGUOUS);
    Identifier i = new Identifier();
    i.setIdentifier("me");
    u.addIdentifier(i);
    u.setKingdomKey(6);
    u.setKingdom("Plants");

    json = mapper.writeValueAsString(u);
    //TODO: the identifier list makes the followig equals fail - intended?
    //assertEquals(u, mapper.readValue(json, NameUsage.class));
  }
}
