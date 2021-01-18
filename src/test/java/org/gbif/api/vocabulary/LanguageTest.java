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
package org.gbif.api.vocabulary;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LanguageTest {

  @Test
  public void testFromIsoCode() {
    assertEquals(Language.ENGLISH, Language.fromIsoCode("en"));
    assertEquals(Language.ESTONIAN, Language.fromIsoCode("et"));
    assertEquals(Language.ZULU, Language.fromIsoCode("ZU"));
    assertEquals(Language.GERMAN, Language.fromIsoCode("de"));
    assertEquals(Language.GERMAN, Language.fromIsoCode("DEU"));
    assertEquals(Language.GERMAN, Language.fromIsoCode("Deu"));
  }

  @Test
  public void testGetIso2LetterCode() {
    for (Language l : Language.values()) {
      assertNotNull(l.getIso2LetterCode());
      // make sure its lower case
      assertEquals(l.getIso2LetterCode().toLowerCase(), l.getIso2LetterCode());
    }
    assertEquals("en", Language.ENGLISH.getIso2LetterCode());
    assertEquals("de", Language.GERMAN.getIso2LetterCode());
    assertEquals("", Language.UNKNOWN.getIso2LetterCode());
  }

  @Test
  public void testGetIso3LetterCode() {
    for (Language l : Language.values()) {
      assertNotNull(l.getIso3LetterCode());
      // make sure its lower case
      assertEquals(l.getIso3LetterCode().toLowerCase(), l.getIso3LetterCode());
    }
    assertEquals("eng", Language.ENGLISH.getIso3LetterCode());
    assertEquals("deu", Language.GERMAN.getIso3LetterCode());
    assertEquals("", Language.UNKNOWN.getIso3LetterCode());
  }

  @Test
  public void testGetLocale() {
    for (Language l : Language.values()) {
      assertNotNull(l);
      assertNotNull(l.getLocale());
    }
    assertEquals(Locale.ENGLISH, Language.ENGLISH.getLocale());
    assertEquals(Locale.GERMAN, Language.GERMAN.getLocale());
    assertEquals(Locale.ROOT, Language.UNKNOWN.getLocale());
  }

  @Test
  public void testGetTitleEnglish() {
    for (Language l : Language.values()) {
      assertNotNull(l.getTitleEnglish());
    }
    assertEquals("English", Language.ENGLISH.getTitleEnglish());
    assertEquals("German", Language.GERMAN.getTitleEnglish());
    assertEquals("", Language.UNKNOWN.getTitleEnglish());
  }

  @Test
  public void testGetTitleNative() {
    for (Language l : Language.values()) {
      assertNotNull(l.getTitleNative());
    }
    assertEquals("English", Language.ENGLISH.getTitleNative());
    assertEquals("Deutsch", Language.GERMAN.getTitleNative());
    assertEquals("", Language.UNKNOWN.getTitleNative());
  }

  /**
   * A container of Languages using 2 properties.
   */
  private static class Container {

    public Language language;
    public Language island; // verify that the names don't matter

    public Container(Language language, Language island) {
      this.language = language;
      this.island = island;
    }

    // used by serializer below
    public Container() {
    }
  }

  @Test
  public void testSerDe() {
    ObjectMapper mapper = new ObjectMapper();
    Container source = new Container(Language.DANISH, Language.SUNDANESE);
    try {
      String json = mapper.writeValueAsString(source);
      assertTrue(json.equals(json.toLowerCase()), "Only lower case letters allowed for language iso codes");
      assertEquals(33, json.length());
      Container target = mapper.readValue(json, Container.class);
      assertEquals(source.language, target.language, "language differs");
      assertEquals(source.island, target.island, "island differs");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Ensure the decision to use iso 3 codes is as backwards compatible as possible.
   */
  @Test
  public void testSerDeBackwardsCompatible() {
    try {
      assertEquals(Language.PORTUGUESE, Language.LenientDeserializer.lenientParse("pt"));
      assertEquals(Language.PORTUGUESE, Language.LenientDeserializer.lenientParse("PT"));
      assertEquals(Language.PORTUGUESE, Language.LenientDeserializer.lenientParse("POR"));
      assertEquals(Language.PORTUGUESE, Language.LenientDeserializer.lenientParse("por"));
      assertEquals(Language.PORTUGUESE, Language.LenientDeserializer.lenientParse("PORTUGUESE"));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
