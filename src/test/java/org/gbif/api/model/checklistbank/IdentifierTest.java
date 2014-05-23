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

import org.gbif.api.vocabulary.IdentifierType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class IdentifierTest {

  @Test
  public void testEquals() {
    Identifier i1 = new Identifier();
    i1.setKey(123);
    i1.setIdentifier("432");
    i1.setType(IdentifierType.GBIF_PORTAL);

    Identifier i2 = new Identifier();
    i2.setKey(123);
    i2.setIdentifier("432");
    i2.setType(IdentifierType.GBIF_PORTAL);

    assertEquals(i1, i2);

    i2.setKey(124);

    assertFalse(i1.equals(i2));
  }

  @Test
  public void testGetIdentifierLink() {
    Identifier i1 = new Identifier();
    i1.setKey(124);
    i1.setIdentifier("10.1594/PANGAEA.819874");
    i1.setType(IdentifierType.DOI);

    assertEquals("http://dx.doi.org/10.1594/PANGAEA.819874", i1.getIdentifierLink());
  }

}
