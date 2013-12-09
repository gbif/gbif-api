/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.vocabulary;

import java.util.Set;

import com.google.common.collect.Sets;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CountryTest {

  @Test
  public void testFromIsoCode() throws Exception {
    assertEquals(Country.ARGENTINA, Country.fromIsoCode("ar"));
    assertEquals(Country.ARGENTINA, Country.fromIsoCode("AR"));
  }

  @Test
  public void testGetIso2LetterCode() throws Exception {
    Set<String> codes = Sets.newHashSet();
    for (Country l : Country.values()) {
      assertNotNull(l.getIso2LetterCode());
      // make sure its upper case
      assertEquals(l.getIso2LetterCode().toUpperCase(), l.getIso2LetterCode());
      // make sure its unique
      assertFalse(codes.contains(l.getIso2LetterCode()));
      codes.add(l.getIso2LetterCode());
    }
    assertEquals("DE", Country.GERMANY.getIso2LetterCode());
    assertEquals("GB", Country.UNITED_KINGDOM.getIso2LetterCode());
  }

  @Test
  public void testGetIso3LetterCode() throws Exception {
    Set<String> codes = Sets.newHashSet();
    for (Country l : Country.values()) {
      assertNotNull(l.getIso3LetterCode());
      // make sure its upper case
      assertEquals(l.getIso3LetterCode().toUpperCase(), l.getIso3LetterCode());
      // make sure its unique
      assertFalse(codes.contains(l.getIso3LetterCode()));
      codes.add(l.getIso3LetterCode());
    }
    assertEquals("GBR", Country.UNITED_KINGDOM.getIso3LetterCode());
    assertEquals("DEU", Country.GERMANY.getIso3LetterCode());
  }

  @Test
  public void testGetIsoNumericalCode() throws Exception {
    Set<Integer> codes = Sets.newHashSet();
    for (Country l : Country.values()) {
      assertNotNull(l.getIsoNumericalCode());
      // make sure its unique
      assertFalse(codes.contains(l.getIsoNumericalCode()));
      codes.add(l.getIsoNumericalCode());
    }
    assertEquals("GBR", Country.UNITED_KINGDOM.getIso3LetterCode());
    assertEquals("DEU", Country.GERMANY.getIso3LetterCode());
  }

  @Test
  public void testIsCustomCode() throws Exception {
    for (Country l : Country.values()) {
      if (l.isOfficial()) {
        assertFalse(Country.isCustomCode(l.getIso2LetterCode()));
        assertFalse(Country.isCustomCode(l.getIso3LetterCode()));
      } else {
        assertTrue(Country.isCustomCode(l.getIso2LetterCode()));
        assertTrue(Country.isCustomCode(l.getIso3LetterCode()));
      }
    }
  }

  @Test
  public void testIsOfficial() throws Exception {
    for (Country l : Country.OFFICIAL_COUNTRIES) {
      assertTrue(l.isOfficial());
    }
    int officialCountries = Country.OFFICIAL_COUNTRIES.size();
    int allCountries = Country.values().length;
    assertTrue(allCountries > officialCountries);
    assertEquals(249, officialCountries);
  }

  @Test
  public void testgetTitle() throws Exception {
    for (Country l : Country.values()) {
      assertNotNull(l.getTitle());
      assertTrue(l.getTitle().length() > 2);
    }
    assertEquals("United Kingdom", Country.UNITED_KINGDOM.getTitle());
    assertEquals("Germany", Country.GERMANY.getTitle());
  }

  /**
   * A container of Countries using 2 properties.
   */
  private static class Container {

    public Country country;
    public Country island; // verify that the names don't matter

    public Container(Country country, Country island) {
      this.country = country;
      this.island = island;
    }

    // used by serializer below
    public Container() {
    }
  }

  @Test
  public void testSerDe() {
    ObjectMapper mapper = new ObjectMapper();
    Container source = new Container(Country.DENMARK, Country.FALKLAND_ISLANDS);
    try {
      String serialized = mapper.writeValueAsString(source);
      Container target = mapper.readValue(serialized.getBytes(), Container.class);
      assertEquals("country differs", source.country, target.country);
      assertEquals("island differs", source.island, target.island);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
