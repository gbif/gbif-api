package org.gbif.api.model.common;

import org.gbif.api.vocabulary.IdentifierType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class IdentifierTest {

  @Test
  public void testGetIdentifierLink() {
    Identifier i1 = new Identifier();
    i1.setIdentifier("10.1594/PANGAEA.819874");
    i1.setType(IdentifierType.DOI);

    assertEquals("https://doi.org/10.1594/PANGAEA.819874", i1.getIdentifierLink());
  }

  @Test
  public void testEquals() {
    Identifier i1 = new Identifier();
    i1.setIdentifier("10.1594/PANGAEA.819874");
    i1.setType(IdentifierType.DOI);

    Identifier i2 = new Identifier();
    i2.setIdentifier("10.1594/PANGAEA.819874");
    i2.setType(IdentifierType.DOI);

    assertEquals(i1, i2);

    i2.setTitle("me not");
    assertFalse(i1.equals(i2));
  }
}
