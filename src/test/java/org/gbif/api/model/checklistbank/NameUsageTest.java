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
package org.gbif.api.model.checklistbank;

import org.gbif.api.model.Constants;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.IdentifierType;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This tests the {@link NameUsage}.
 */
public class NameUsageTest {

  @Test
  public void testEquals() {
    Identifier id = new Identifier();
    id.setIdentifier("http://www.example.org");
    id.setType(IdentifierType.URL);

    NameUsage nu1 = new NameUsage();
    nu1.setKey(123);
    nu1.getIdentifiers().add(id);
    nu1.setKingdom("Animalia");

    NameUsage nu2 = new NameUsage();
    nu2.setKey(123);
    nu2.getIdentifiers().add(id);
    nu2.setKingdom("Animalia");

    Image ru1 = new Image();
    ru1.setDatasetKey(Constants.NUB_DATASET_KEY);
    ru1.setTitle("Puma concolor");
    ru1.setUsageKey(111);
    Image ru2 = new Image();
    ru2.setDatasetKey(Constants.NUB_DATASET_KEY);
    ru2.setTitle("Puma concolor");
    ru2.setUsageKey(111);

    assertEquals(nu1, nu2);

    nu2.setKey(124);

    assertFalse(nu1.equals(nu2));
  }

  @Test
  public void testEqualsNonNullity() {
    NameUsage nu1 = new NameUsage();
    assertFalse(nu1.equals(null));
  }

  @Test
  public void testEqualsReflexivity() {
    NameUsage nu = new NameUsage();
    assertEquals(nu, nu);

    nu = new NameUsage();
    assertEquals(nu, nu);
  }

  @Test
  public void testEqualsSymmetry() {
    NameUsage nu1 = new NameUsage();
    NameUsage nu2 = new NameUsage();

    nu1.setKey(123);
    nu2.setKey(123);

    assertEquals(nu1, nu2);
    assertEquals(nu2, nu1);

    nu1.setKey(1234);
    assertFalse(nu1.equals(nu2));
    assertFalse(nu2.equals(nu1));
  }

  @Test
  public void testHigherClassificationMap() {
    NameUsage nu = new NameUsage();
    nu.setKey(6);
    nu.setScientificName("Vicia faba L.");
    nu.setParentKey(10);
    nu.setParent("Vicia subgen. Vicia");
    nu.setRank(Rank.SPECIES);
    nu.setKingdomKey(1);
    nu.setKingdom("Plantae");
    nu.setGenus("Vicia");
    nu.setGenusKey(3);
    nu.setOrder("Fabales");
    nu.setOrderKey(4);
    nu.setSpecies("Vicia faba");
    nu.setSpeciesKey(6);
    nu.setPhylum("Magnoliophyta");
    nu.setPhylumKey(5);

    assertEquals(5, nu.getHigherClassificationMap().size());
    assertTrue(nu.getHigherClassificationMap().containsKey(10));
    assertFalse(nu.getHigherClassificationMap().containsKey(6));

    Queue<Integer> expectedKeyOrder = new LinkedList<Integer>();
    expectedKeyOrder.add(1);
    expectedKeyOrder.add(5);
    expectedKeyOrder.add(4);
    expectedKeyOrder.add(3);
    expectedKeyOrder.add(10);

    for (Object key : nu.getHigherClassificationMap().keySet()) {
      assertEquals(expectedKeyOrder.poll(), key);
    }
    assertTrue(expectedKeyOrder.isEmpty());
  }

  @Test
  public void testHigherClassificationMapWithRegularParent() {
    NameUsage nu = new NameUsage();
    nu.setKey(6);
    nu.setScientificName("Vicia faba L.");
    nu.setParentKey(3);
    nu.setParent("Vicia");
    nu.setRank(Rank.SPECIES);
    nu.setKingdomKey(1);
    nu.setKingdom("Plantae");
    nu.setGenus("Vicia");
    nu.setGenusKey(3);
    nu.setOrder("Fabales");
    nu.setOrderKey(4);
    nu.setSpecies("Vicia faba");
    nu.setSpeciesKey(6);
    nu.setPhylum("Magnoliophyta");
    nu.setPhylumKey(5);

    assertEquals(4, nu.getHigherClassificationMap().size());
    assertTrue(nu.getHigherClassificationMap().containsKey(3));

    Queue<Integer> expectedKeyOrder = new LinkedList<Integer>();
    expectedKeyOrder.add(1);
    expectedKeyOrder.add(5);
    expectedKeyOrder.add(4);
    expectedKeyOrder.add(3);

    for (Object key : nu.getHigherClassificationMap().keySet()) {
      assertEquals(expectedKeyOrder.poll(), key);
    }
    assertTrue(expectedKeyOrder.isEmpty());
  }

  @Test
  public void testSetLink() {
    Identifier link = new Identifier();
    link.setIdentifier("http://www.example.org");
    link.setType(IdentifierType.URL);

    Identifier id = new Identifier();
    id.setIdentifier("12345");
    id.setType(IdentifierType.SOURCE_ID);

    NameUsage nu = new NameUsage();
    nu.getIdentifiers().add(id);
    assertNull(nu.getLink());
    assertEquals("12345", nu.getSourceId());

    nu.getIdentifiers().add(link);
    assertEquals("http://www.example.org", nu.getLink());

    nu.getIdentifiers().clear();
    assertNull(nu.getLink());
    assertNull(nu.getSourceId());
    assertEquals(0, nu.getIdentifiers().size());


    // sourceid
    nu.setSourceId("99");
    assertEquals(1, nu.getIdentifiers().size());
    assertEquals("99", nu.getSourceId());
    assertEquals("99", nu.getIdentifiers().get(0).getIdentifier());

    nu.setSourceId("991");
    assertEquals(1, nu.getIdentifiers().size());
    assertEquals("991", nu.getSourceId());
    assertEquals("991", nu.getIdentifiers().get(0).getIdentifier());


    // references
    assertEquals(1, nu.getIdentifiers().size());
    nu.setReferences(URI.create("http://www.example.org"));
    assertEquals(1, nu.getIdentifiers().size());
    assertEquals("http://www.example.org", nu.getLink());
    assertEquals(nu.getLink(), nu.getReferences().toString());

    nu.setReferences(URI.create("http://www.example.org/2"));
    assertEquals(1, nu.getIdentifiers().size());
    assertEquals("http://www.example.org/2", nu.getLink());
    assertEquals(nu.getLink(), nu.getReferences().toString());
  }

  @Test
  public void testTaxonomicStatus() {
    NameUsage nu = new NameUsage();
    nu.setKey(6);
    nu.setScientificName("Vicia faba L.");
    nu.setSynonym(true);
    nu.setTaxonomicStatus(null);
    assertEquals(TaxonomicStatus.SYNONYM, nu.getTaxonomicStatus());

    nu.setProParteKey(432);
    assertEquals(TaxonomicStatus.PROPARTE_SYNONYM, nu.getTaxonomicStatus());

    nu.setSynonym(false);
    assertNull(nu.getTaxonomicStatus());

    nu.setTaxonomicStatus(TaxonomicStatus.HETEROTYPIC_SYNONYM);
    assertEquals(TaxonomicStatus.HETEROTYPIC_SYNONYM, nu.getTaxonomicStatus());
  }

  @Test
  public void testJsonSerde() throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    mapper.disable(SerializationConfig.Feature.WRITE_NULL_PROPERTIES);

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

    String json = mapper.writeValueAsString(u);
    System.out.println(json);
    assertEquals(u, mapper.readValue(json, NameUsage.class));
  }


}
