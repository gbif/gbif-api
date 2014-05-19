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
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.ThreatStatus;
import org.gbif.api.vocabulary.IdentifierType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This tests the {@link org.gbif.api.model.checklistbank.NameUsageContainer}.
 */
public class NameUsageContainerTest {

  @Test
  public void testConstructor() {
    final UUID checklistKey = UUID.randomUUID();
    NameUsage src = new NameUsage();
    src.setKey(1234);
    src.setDatasetKey(checklistKey);
    src.setKingdom("Animalia");
    src.setScientificName("Puma concolor Linné");

    Identifier id1 = new Identifier();
    id1.setIdentifier("1234");
    id1.setType(IdentifierType.SOURCE_ID);
    id1.setUsageKey(src.getKey());
    src.addIdentifier(id1);

    Identifier id2 = new Identifier();
    id2.setIdentifier("http://wisdom.org/1234");
    id2.setType(IdentifierType.URL);
    id2.setUsageKey(src.getKey());
    src.addIdentifier(id2);

    NameUsageContainer nu = new NameUsageContainer(src);

    assertEquals(checklistKey, nu.getDatasetKey());
    assertEquals((Integer) 1234, nu.getKey());
    assertEquals("http://wisdom.org/1234", nu.getLink());
    assertEquals("Animalia", nu.getKingdom());
    assertEquals("Puma concolor Linné", nu.getScientificName());
    assertEquals(2, nu.getIdentifiers().size());

    assertEquals("1234", nu.getIdentifiers().get(0).getIdentifier());
    assertEquals(IdentifierType.SOURCE_ID, nu.getIdentifiers().get(0).getType());
    assertEquals((Integer) 1234, nu.getIdentifiers().get(0).getUsageKey());

    assertEquals("http://wisdom.org/1234", nu.getIdentifiers().get(1).getIdentifier());
    assertEquals(IdentifierType.URL, nu.getIdentifiers().get(1).getType());
    assertEquals((Integer) 1234, nu.getIdentifiers().get(1).getUsageKey());

    assertNull(nu.getAccepted());
    assertNull(nu.getAcceptedKey());
    assertNull(nu.getAccordingTo());
    assertNull(nu.getNameType());
    assertNull(nu.getKingdomKey());

    assertEquals(0, nu.getImages().size());
    assertEquals(0, nu.getDescriptions().size());
    assertEquals(0, nu.getDistributions().size());
    assertEquals(0, nu.getReferenceList().size());
  }

  @Test
  public void testEquals() {
    Identifier id1 = new Identifier();
    id1.setIdentifier("http://www.example.org");
    id1.setType(IdentifierType.URL);

    NameUsageContainer nu1 = new NameUsageContainer();
    nu1.setKey(123);
    nu1.getIdentifiers().add(id1);
    nu1.setKingdom("Animalia");

    NameUsageContainer nu2 = new NameUsageContainer();
    nu2.setKey(123);
    nu2.getIdentifiers().add(id1);
    nu2.setKingdom("Animalia");

    Image ru1 = new Image();
    ru1.setDatasetKey(Constants.NUB_DATASET_KEY);
    ru1.setTitle("Puma concolor");
    ru1.setUsageKey(111);
    Image ru2 = new Image();
    ru2.setDatasetKey(Constants.NUB_DATASET_KEY);
    ru2.setTitle("Puma concolor");
    ru2.setUsageKey(111);

    List<Image> lru1 = new ArrayList<Image>();
    lru1.add(ru1);
    List<Image> lru2 = new ArrayList<Image>();
    lru2.add(ru2);

    nu1.setImages(lru1);
    nu2.setImages(lru2);

    assertEquals(nu1, nu2);

    nu2.setKey(124);

    assertFalse(nu1.equals(nu2));
  }

  @Test
  public void testEqualsNonNullity() {
    NameUsageContainer nu1 = new NameUsageContainer();
    assertFalse(nu1.equals(null));
  }

  @Test
  public void testEqualsReflexivity() {
    NameUsageContainer nu = new NameUsageContainer();
    assertEquals(nu, nu);

    nu = new NameUsageContainer();
    assertEquals(nu, nu);
  }

  @Test
  public void testEqualsSymmetry() {
    NameUsageContainer nu1 = new NameUsageContainer();
    NameUsageContainer nu2 = new NameUsageContainer();

    nu1.setKey(123);
    nu2.setKey(123);

    assertEquals(nu1, nu2);
    assertEquals(nu2, nu1);

    nu1.setKey(1234);
    assertFalse(nu1.equals(nu2));
    assertFalse(nu2.equals(nu1));
  }

  @Test
  public void testExtinct() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);
    assertNull(nu.isExtinct());

    SpeciesProfile sp = new SpeciesProfile();
    sp.setExtinct(true);
    nu.getSpeciesProfiles().add(sp);
    assertTrue(nu.isExtinct());
    sp.setExtinct(false);
    assertFalse(nu.isExtinct());
    sp.setExtinct(null);
    assertNull(nu.isExtinct());

    nu.getSpeciesProfiles().clear();

    for (int i = 1; i < 10; i++) {
      sp = new SpeciesProfile();
      sp.setExtinct(i % 2 == 0);
      nu.getSpeciesProfiles().add(sp);
    }

    assertEquals(9, nu.getSpeciesProfiles().size());
    assertFalse(nu.isExtinct());

    sp = new SpeciesProfile();
    sp.setExtinct(true);
    nu.getSpeciesProfiles().add(sp);
    assertEquals(10, nu.getSpeciesProfiles().size());
    assertTrue(nu.isExtinct());
  }

  @Test
  public void testHabitats() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);
    for (int i = 1; i < 10; i++) {
      SpeciesProfile sp = new SpeciesProfile();
      sp.setHabitat("Habitat " + i);
      nu.getSpeciesProfiles().add(sp);
    }

    assertEquals(9, nu.getSpeciesProfiles().size());
    assertEquals(9, nu.getHabitats().size());

    SpeciesProfile sp = new SpeciesProfile();
    sp.setHabitat("Habitat 5");
    nu.getSpeciesProfiles().add(sp);
    assertEquals(10, nu.getSpeciesProfiles().size());
    assertEquals(9, nu.getHabitats().size());
  }

  @Test
  public void testHigherClassificationMap() {
    NameUsageContainer nu = new NameUsageContainer();
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
    NameUsageContainer nu = new NameUsageContainer();
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

    Queue<Integer> expectedKeyOrder = Lists.newLinkedList();
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
  public void testMarine() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);
    assertNull(nu.isMarine());

    SpeciesProfile sp = new SpeciesProfile();
    sp.setMarine(true);
    nu.getSpeciesProfiles().add(sp);
    assertTrue(nu.isMarine());
    sp.setMarine(false);
    assertFalse(nu.isMarine());
    sp.setMarine(null);
    assertNull(nu.isMarine());

    nu.getSpeciesProfiles().clear();

    for (int i = 1; i < 10; i++) {
      sp = new SpeciesProfile();
      sp.setMarine(i % 2 == 0);
      nu.getSpeciesProfiles().add(sp);
    }

    assertEquals(9, nu.getSpeciesProfiles().size());
    assertFalse(nu.isMarine());

    sp = new SpeciesProfile();
    sp.setMarine(true);
    nu.getSpeciesProfiles().add(sp);
    assertEquals(10, nu.getSpeciesProfiles().size());
    assertTrue(nu.isMarine());
  }

  @Test
  public void testTaxonomicStatus() {
    NameUsageContainer nu = new NameUsageContainer();
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
  public void testTerrestrial() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);
    assertNull(nu.isTerrestrial());

    SpeciesProfile sp = new SpeciesProfile();
    sp.setTerrestrial(true);
    nu.getSpeciesProfiles().add(sp);
    assertTrue(nu.isTerrestrial());
    sp.setTerrestrial(false);
    assertFalse(nu.isTerrestrial());
    sp.setTerrestrial(null);
    assertNull(nu.isTerrestrial());

    nu.getSpeciesProfiles().clear();

    for (int i = 1; i < 10; i++) {
      sp = new SpeciesProfile();
      sp.setTerrestrial(i % 2 == 0);
      nu.getSpeciesProfiles().add(sp);
    }

    assertEquals(9, nu.getSpeciesProfiles().size());
    assertFalse(nu.isTerrestrial());

    sp = new SpeciesProfile();
    sp.setTerrestrial(true);
    nu.getSpeciesProfiles().add(sp);
    assertEquals(10, nu.getSpeciesProfiles().size());
    assertTrue(nu.isTerrestrial());
  }

  @Test
  public void testThreats() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);

    assertEquals(0, nu.getDistributions().size());
    assertEquals(0, nu.getThreatStatus().size());

    Distribution d = new Distribution();
    d.setThreatStatus(ThreatStatus.ENDANGERED);
    nu.getDistributions().add(d);
    assertEquals(1, nu.getDistributions().size());
    assertEquals(1, nu.getThreatStatus().size());
    assertEquals(ThreatStatus.ENDANGERED, nu.getThreatStatus().iterator().next());

    d = new Distribution();
    d.setThreatStatus(ThreatStatus.ENDANGERED);
    nu.getDistributions().add(d);
    assertEquals(2, nu.getDistributions().size());
    assertEquals(1, nu.getThreatStatus().size());
    assertEquals(ThreatStatus.ENDANGERED, nu.getThreatStatus().iterator().next());

    d = new Distribution();
    d.setThreatStatus(ThreatStatus.CRITICALLY_ENDANGERED);
    nu.getDistributions().add(d);
    assertEquals(3, nu.getDistributions().size());
    assertEquals(2, nu.getThreatStatus().size());
    assertEquals(ThreatStatus.ENDANGERED, nu.getThreatStatus().iterator().next());
  }

}
