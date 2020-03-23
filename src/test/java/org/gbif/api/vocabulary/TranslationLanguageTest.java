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

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** Tests the {@link TranslationLanguage}. */
public class TranslationLanguageTest {

  @Test
  public void testFromIsoCode() throws Exception {
    assertEquals(TranslationLanguage.ENGLISH, TranslationLanguage.fromLocale("en"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-ES"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-es"));
    assertEquals(TranslationLanguage.ARABIC, TranslationLanguage.fromLocale("ar"));
    assertEquals(TranslationLanguage.ARPITAN, TranslationLanguage.fromLocale("frp-IT"));
  }

  @Test
  public void testSerDe() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String json = mapper.writeValueAsString(TranslationLanguage.AFAR);
      assertEquals(TranslationLanguage.AFAR, mapper.readValue(json, TranslationLanguage.class));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
