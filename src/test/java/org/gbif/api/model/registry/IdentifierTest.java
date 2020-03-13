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

    assertEquals("https://doi.org/10.1594/PANGAEA.819874", i1.getIdentifierLink());
  }
}
