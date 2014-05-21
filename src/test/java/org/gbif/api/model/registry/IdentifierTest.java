package org.gbif.api.model.registry;

import org.gbif.api.vocabulary.IdentifierType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdentifierTest {
  @Test
  public void testGetIdentifierLink() {
    Identifier i1 = new Identifier();
    i1.setKey(123);
    i1.setIdentifier("10.1594/PANGAEA.819874");
    i1.setType(IdentifierType.DOI);

    assertEquals("http://dx.doi.org/10.1594/PANGAEA.819874", i1.getIdentifierLink());
  }
}
