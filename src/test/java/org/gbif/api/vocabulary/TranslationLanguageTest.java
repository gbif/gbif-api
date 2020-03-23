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

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/** Tests the {@link TranslationLanguage}. */
public class TranslationLanguageTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testFromIsoCode() {
    assertEquals(TranslationLanguage.ENGLISH, TranslationLanguage.fromLocale("en"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-ES"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-es"));
    assertEquals(TranslationLanguage.ARABIC, TranslationLanguage.fromLocale("ar"));
    assertEquals(TranslationLanguage.ARPITAN, TranslationLanguage.fromLocale("frp-IT"));
  }

  @Test
  public void testSerDe() {
    try {
      String json = MAPPER.writeValueAsString(TranslationLanguage.AFAR);
      assertEquals(TranslationLanguage.AFAR.getLocale(), MAPPER.readTree(json).asText());
      assertEquals(TranslationLanguage.AFAR, MAPPER.readValue(json, TranslationLanguage.class));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testKeySerDe() {
    try {
      Map<TranslationLanguage, String> languageMap = new EnumMap<>(TranslationLanguage.class);
      languageMap.put(TranslationLanguage.SPANISH, "foo");

      String json = MAPPER.writeValueAsString(languageMap);
      assertTrue(MAPPER.readTree(json).has(TranslationLanguage.SPANISH.getLocale()));

      JavaType type =
          MAPPER
              .getTypeFactory()
              .constructMapType(HashMap.class, TranslationLanguage.class, String.class);
      Map<TranslationLanguage, String> mapDeserialized = MAPPER.readValue(json, type);
      assertEquals("foo", mapDeserialized.get(TranslationLanguage.SPANISH));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
