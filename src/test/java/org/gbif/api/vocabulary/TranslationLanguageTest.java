package org.gbif.api.vocabulary;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** Tests the {@link TranslationLanguage}. */
public class TranslationLanguageTest {

  @Test
  public void testFromIsoCode() throws Exception {
    assertEquals(TranslationLanguage.ENGLISH, TranslationLanguage.fromLocale("en-US"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-ES"));
    assertEquals(TranslationLanguage.SPANISH, TranslationLanguage.fromLocale("es-es"));
    assertEquals(TranslationLanguage.ARABIC, TranslationLanguage.fromLocale("ar-SA"));
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
