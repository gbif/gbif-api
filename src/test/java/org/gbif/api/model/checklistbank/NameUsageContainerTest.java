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

import org.gbif.api.model.common.Identifier;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.NameUsageIssue;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.ThreatStatus;

import java.net.URI;
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
    src.setReferences(URI.create("http://wisdom.org/1234"));
    src.setSourceTaxonKey(12345);
    src.getIssues().add(NameUsageIssue.ACCEPTED_NAME_MISSING);
    src.getIssues().add(NameUsageIssue.BACKBONE_MATCH_NONE);
    src.getIssues().add(NameUsageIssue.BASIONYM_AUTHOR_MISMATCH);

    NameUsageContainer nu = new NameUsageContainer(src);
    Identifier id1 = new Identifier();
    id1.setIdentifier("1234");
    id1.setType(IdentifierType.GBIF_PORTAL);
    nu.getIdentifiers().add(id1);

    assertEquals(checklistKey, nu.getDatasetKey());
    assertEquals((Integer) 1234, nu.getKey());
    assertEquals((Integer) 12345, nu.getSourceTaxonKey());
    assertEquals("http://wisdom.org/1234", nu.getReferences().toString());
    assertEquals("Animalia", nu.getKingdom());
    assertEquals("Puma concolor Linné", nu.getScientificName());
    assertEquals(1, nu.getIdentifiers().size());

    assertEquals("1234", nu.getIdentifiers().get(0).getIdentifier());
    assertEquals(IdentifierType.GBIF_PORTAL, nu.getIdentifiers().get(0).getType());

    assertNull(nu.getAccepted());
    assertNull(nu.getAcceptedKey());
    assertNull(nu.getAccordingTo());
    assertNull(nu.getNameType());
    assertNull(nu.getKingdomKey());

    assertEquals(0, nu.getMedia().size());
    assertEquals(0, nu.getDescriptions().size());
    assertEquals(0, nu.getDistributions().size());
    assertEquals(0, nu.getReferenceList().size());
    assertEquals(3, nu.getIssues().size());
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

    NameUsageMediaObject ru1 = new NameUsageMediaObject();
    ru1.setTitle("Puma concolor");
    ru1.setSourceTaxonKey(111);
    NameUsageMediaObject ru2 = new NameUsageMediaObject();
    ru2.setTitle("Puma concolor");
    ru2.setSourceTaxonKey(111);

    List<NameUsageMediaObject> lru1 = new ArrayList<>();
    lru1.add(ru1);
    List<NameUsageMediaObject> lru2 = new ArrayList<>();
    lru2.add(ru2);

    nu1.setMedia(lru1);
    nu2.setMedia(lru2);

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
  public void testFreshwater() {
    NameUsageContainer nu = new NameUsageContainer();
    nu.setKey(6);
    assertNull(nu.isFreshwater());

    SpeciesProfile sp = new SpeciesProfile();
    sp.setFreshwater(true);
    nu.getSpeciesProfiles().add(sp);
    assertTrue(nu.isFreshwater());
    sp.setFreshwater(false);
    assertFalse(nu.isFreshwater());
    sp.setFreshwater(null);
    assertNull(nu.isFreshwater());

    nu.getSpeciesProfiles().clear();

    for (int i = 1; i < 10; i++) {
      sp = new SpeciesProfile();
      sp.setFreshwater(i % 2 == 0);
      nu.getSpeciesProfiles().add(sp);
    }

    assertEquals(9, nu.getSpeciesProfiles().size());
    assertFalse(nu.isFreshwater());

    sp = new SpeciesProfile();
    sp.setFreshwater(true);
    nu.getSpeciesProfiles().add(sp);
    assertEquals(10, nu.getSpeciesProfiles().size());
    assertTrue(nu.isFreshwater());
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
