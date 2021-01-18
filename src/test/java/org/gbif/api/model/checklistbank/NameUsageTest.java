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

import org.gbif.api.SerdeTestUtils;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    assertNotEquals(nu2, nu1);
  }

  @Test
  public void testEqualsNonNullity() {
    NameUsage nu1 = new NameUsage();
    assertNotEquals(nu1, null);
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
    assertNotEquals(nu2, nu1);
    assertNotEquals(nu1, nu2);
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

    Queue<Integer> expectedKeyOrder = new LinkedList<>();
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

    Queue<Integer> expectedKeyOrder = new LinkedList<>();
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
    assertNotNull(nu.getReferences());
    assertEquals("http://www.example.org", nu.getReferences().toString());
  }

  @Test
  public void testTaxonomicStatus() {
    NameUsage nu = new NameUsage();
    nu.setKey(6);
    nu.setScientificName("Vicia faba L.");
    nu.setTaxonomicStatus(null);
    assertNull(nu.getTaxonomicStatus());
    assertFalse(nu.isSynonym());

    nu.setTaxonomicStatus(TaxonomicStatus.PROPARTE_SYNONYM);
    assertEquals(TaxonomicStatus.PROPARTE_SYNONYM, nu.getTaxonomicStatus());
    assertTrue(nu.isSynonym());

    nu.setTaxonomicStatus(TaxonomicStatus.SYNONYM);
    assertEquals(TaxonomicStatus.SYNONYM, nu.getTaxonomicStatus());
    assertTrue(nu.isSynonym());

    nu.setTaxonomicStatus(TaxonomicStatus.DOUBTFUL);
    assertEquals(TaxonomicStatus.DOUBTFUL, nu.getTaxonomicStatus());
    assertFalse(nu.isSynonym());

    nu.setProParteKey(432);
    assertTrue(nu.isProParte());

    nu.setTaxonomicStatus(TaxonomicStatus.HETEROTYPIC_SYNONYM);
    assertEquals(TaxonomicStatus.HETEROTYPIC_SYNONYM, nu.getTaxonomicStatus());
    assertTrue(nu.isSynonym());
  }

  @Test
  public void testJsonSerde() throws IOException {

    NameUsage u = new NameUsage();
    u.setKey(123);
    u.setDatasetKey(UUID.randomUUID());
    u.setNumDescendants(321);
    u.getNomenclaturalStatus().add(NomenclaturalStatus.AMBIGUOUS);
    u.setKingdomKey(6);
    u.setKingdom("Plants");

    SerdeTestUtils.testSerDe(u, NameUsage.class);
  }
}
