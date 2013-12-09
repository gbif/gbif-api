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
  public void testNubUsageID() throws Exception {
    assertEquals((Integer)0, Kingdom.INCERTAE_SEDIS.nubUsageID());
    assertEquals((Integer)1, Kingdom.ANIMALIA.nubUsageID());
    assertEquals((Integer)6, Kingdom.PLANTAE.nubUsageID());
  }

  @Test
  public void testClbNameID() throws Exception {
    assertEquals((Integer)9, Kingdom.INCERTAE_SEDIS.clbNameID());
    assertEquals((Integer)1, Kingdom.ANIMALIA.clbNameID());
    assertEquals((Integer)6, Kingdom.PLANTAE.clbNameID());
  }
}
