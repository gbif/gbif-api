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

import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

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
    NameUsage nu1 = new NameUsage();
    nu1.setKey(123);
    nu1.setKingdom("Animalia");

    NameUsage nu2 = new NameUsage();
    nu2.setKey(123);
    nu2.setKingdom("Animalia");

    NameUsageMediaObject ru1 = new NameUsageMediaObject();
    ru1.setTitle("Puma concolor");
    ru1.setSourceTaxonKey(111);
    NameUsageMediaObject ru2 = new NameUsageMediaObject();
    ru2.setSourceTaxonKey(111);

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
    NameUsage nu = new NameUsage();
    nu.setReferences(URI.create("http://www.example.org"));
    nu.setTaxonID("12345");
    assertEquals("12345", nu.getTaxonID());
    assertEquals("http://www.example.org", nu.getReferences().toString());
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
    u.setKingdomKey(6);
    u.setKingdom("Plants");

    String json = mapper.writeValueAsString(u);
    System.out.println(json);
    assertEquals(u, mapper.readValue(json, NameUsage.class));
  }


}
