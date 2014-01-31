package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContinentTest {

  @Test
  public void testGetTitle() throws Exception {
    for (Continent c : Continent.values()) {
      assertNotNull(c.getTitle());
      assertTrue(c.getTitle().length() > 2);
    }
    assertEquals("North America", Continent.NORTH_AMERICA.getTitle());
    assertEquals("Asia", Continent.ASIA.getTitle());
  }

  @Test
  public void testFromEnum() throws Exception {
    assertEquals(Continent.ANTARCTICA, Continent.fromString("ANTARCTICA"));
    assertEquals(Continent.ANTARCTICA, Continent.fromString(Continent.ANTARCTICA.getTitle()));
    assertEquals(Continent.SOUTH_AMERICA, Continent.fromString("SOUTH_AMERICA"));
    assertEquals(Continent.SOUTH_AMERICA, Continent.fromString(Continent.SOUTH_AMERICA.getTitle()));
  }
}
