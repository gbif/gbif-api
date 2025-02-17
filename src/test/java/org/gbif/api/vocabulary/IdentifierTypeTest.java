/*
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
package org.gbif.api.vocabulary;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentifierTypeTest {

  @Test
  public void testGuessing() {
    assertEquals(IdentifierType.UNKNOWN, IdentifierType.inferFrom(null));
    assertEquals(IdentifierType.UNKNOWN, IdentifierType.inferFrom(""));
    assertEquals(IdentifierType.UNKNOWN, IdentifierType.inferFrom(" "));

    assertEquals(IdentifierType.DOI, IdentifierType.inferFrom("doi:10.1109/ISSTA.2002.1048560"));
    assertEquals(IdentifierType.DOI, IdentifierType.inferFrom("http://dx.doi.org/10.1109/ISSTA.2002.1048560"));

    assertEquals(IdentifierType.URL, IdentifierType.inferFrom("http://www.catalogueoflife.org/details/species/id/7195681"));

    assertEquals(IdentifierType.FTP, IdentifierType.inferFrom("ftp://catalogueoflife.org/species/7195681"));

    assertEquals(IdentifierType.LSID, IdentifierType
      .inferFrom("urn:lsid:catalogueoflife.org:taxon:2b5d679e-60a7-102d-be47-00304854f810:col20101221"));

    assertEquals(IdentifierType.UUID, IdentifierType.inferFrom("2b5d679e-60a7-102d-be47-00304854f810"));
    assertEquals(IdentifierType.UUID, IdentifierType.inferFrom("2b5d679e-60a7-102D-BE47-00304854f810"));
    assertEquals(IdentifierType.UUID, IdentifierType.inferFrom("urn:uuid:2b5d679e-60a7-102d-be47-00304854f810"));
    assertEquals(IdentifierType.UUID, IdentifierType.inferFrom("uuid:2b5d679e-60a7-102d-be47-00304854f810"));

    assertEquals(IdentifierType.IH_IRN, IdentifierType.inferFrom("gbif:ih:irn:125812"));
    assertEquals(IdentifierType.ROR, IdentifierType.inferFrom("https://ror.org/03yrm5c26"));
    assertEquals(IdentifierType.GRID, IdentifierType.inferFrom("grid.419696.5"));
    assertEquals(IdentifierType.ISIL, IdentifierType.inferFrom("DK-710100"));
  }

  @Test
  public void testTypes() {
    List<IdentifierType> identifierTypes = IdentifierType.TYPES;
    assertEquals(22, identifierTypes.size());
  }

}
