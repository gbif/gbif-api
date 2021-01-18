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
package org.gbif.api.util;

import org.gbif.api.vocabulary.ContactType;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.TechnicalInstallationType;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VocabularyUtilsTest {

  @Test
  public void testParseContactType() {
    assertEquals(ContactType.AUTHOR, VocabularyUtils.parseContactType("author"));
    assertEquals(ContactType.ADMINISTRATIVE_POINT_OF_CONTACT,
      VocabularyUtils.parseContactType("ADMINISTRATIVE POINT_of_CONTACT"));
    assertThrows(IllegalArgumentException.class, () -> VocabularyUtils.parseContactType("bad"));
  }

  @Test
  public void testParseEndpointType() {
    assertEquals(EndpointType.TAPIR, VocabularyUtils.parseEndpointType("Tapir"));
    assertEquals(EndpointType.DWC_ARCHIVE, VocabularyUtils.parseEndpointType("DWC_ARCHIVE"));
    assertThrows(IllegalArgumentException.class, () -> VocabularyUtils.parseEndpointType("bad"));
  }

  @Test
  public void testParseIdentifierType() {
    assertEquals(IdentifierType.LSID, VocabularyUtils.parseIdentifierType("lsid"));
    assertEquals(IdentifierType.DOI, VocabularyUtils.parseIdentifierType("d.o.i."));
    assertThrows(IllegalArgumentException.class, () -> VocabularyUtils.parseIdentifierType("bad"));
  }

  @Deprecated
  @Test
  public void testParseTechnicalInstallationType() {
    assertEquals(TechnicalInstallationType.IPT_INSTALLATION,
      VocabularyUtils.parseTechnicalInstallationType("ipt installation"));
    assertThrows(IllegalArgumentException.class, () -> VocabularyUtils.parseTechnicalInstallationType("bad"));
  }

  @Test
  public void testReflectionLookup() {
    assertEquals(Country.class, VocabularyUtils.lookupVocabulary(Country.class.getName()));
  }

  @Test
  public void testReflectionLookupError() {
    assertThrows(IllegalArgumentException.class, () -> VocabularyUtils.lookupVocabulary("WTFMate"));
  }

  @Test
  public void testLookupUsingOptional() {
    assertEquals(ContactType.AUTHOR, VocabularyUtils.lookup("author", ContactType.class).get());

    //Optional.absent() should be returned for all the following values
    assertEquals(Optional.empty(), VocabularyUtils.lookup("thor", ContactType.class));
    assertEquals(Optional.empty(), VocabularyUtils.lookup("", ContactType.class));
    assertEquals(Optional.empty(), VocabularyUtils.lookup(null, ContactType.class));
  }
}
