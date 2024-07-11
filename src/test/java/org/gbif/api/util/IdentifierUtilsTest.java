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

import org.gbif.api.vocabulary.IdentifierType;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentifierUtilsTest {

  /**
   * A test is run for each set of parameters. To add a new test, just add a new set of parameters.
   */
  public static Stream<Arguments> getTestParameters() {
    return Stream.of(
      Arguments.of(null, "49c5b4ac-e3bf-401b-94b1-c94a2ad5c8d6", IdentifierType.UUID),
      Arguments.of("https://www.gbif.org/dataset/ds1", "ds1", IdentifierType.GBIF_PORTAL),
      Arguments.of(null, "local_1:dx:4", IdentifierType.UNKNOWN),
      Arguments.of("http://en.wikipedia.org/wiki/Handle_1", "http://en.wikipedia.org/wiki/Handle_1", IdentifierType.HANDLER),
      Arguments.of("urn:ds:acns:1", "urn:ds:acns:1", IdentifierType.URI),
      Arguments.of("http://ipt.gbif.org/resource.do?r=ds1", "http://ipt.gbif.org/resource.do?r=ds1", IdentifierType.URL),
      Arguments.of("ftp://ftp.gbif.org", "ftp://ftp.gbif.org", IdentifierType.FTP),
      Arguments.of("https://doi.org/10.1016/S1097-2765(03)00225-9", "10.1016/S1097-2765(03)00225-9", IdentifierType.DOI),
      Arguments.of("http://www.lsid.info/132187", "132187", IdentifierType.LSID),
      Arguments.of("https://ror.org/02mhbdp94.","https://ror.org/02mhbdp94.", IdentifierType.ROR)
    );
  }

  @ParameterizedTest
  @MethodSource("getTestParameters")
  public void testGetIdentifierLink(String expected, String identifier, IdentifierType type) {
    assertEquals(expected, IdentifierUtils.getIdentifierLink(identifier, type));
  }

  @SuppressWarnings({"ConstantConditions", "unused"})
  @ParameterizedTest
  @MethodSource("getTestParameters")
  public void testGetIdentifierLinkWithoutIdentifer(String expected, String identifier, IdentifierType type) {
    assertNull(IdentifierUtils.getIdentifierLink(null, type));
  }

  @SuppressWarnings({"ConstantConditions", "unused"})
  @ParameterizedTest
  @MethodSource("getTestParameters")
  public void testGetIdentifierLinkWithoutType(String expected, String identifier, IdentifierType type) {
    assertNull(IdentifierUtils.getIdentifierLink(identifier, null));
  }

  @Test
  public void wikidataValidatorTest() {
    assertTrue(IdentifierUtils.isValidWikidataIdentifier("http://www.wikidata.org/entity/Q1528756"));
    assertTrue(IdentifierUtils.isValidWikidataIdentifier("https://www.wikidata.org/entity/q1528756"));
    assertFalse(IdentifierUtils.isValidWikidataIdentifier("https://www.wikidata.org/entity/1528756"));
    assertFalse(IdentifierUtils.isValidWikidataIdentifier("https://www.wikidata.org/entity/q15287h56"));
    assertFalse(IdentifierUtils.isValidWikidataIdentifier("https://www.wikidata.org/entity/1528756q"));
  }

  @Test
  public void rorIDValidatorTest() {
    assertTrue(IdentifierUtils.isValidRORIdentifier("https://ror.org/0566bfb96"));
    assertFalse(IdentifierUtils.isValidRORIdentifier("https://ror.org/https://ror.org/0566bfb96"));
    assertFalse(IdentifierUtils.isValidRORIdentifier("0566bfb96"));
    assertFalse(IdentifierUtils.isValidRORIdentifier("https://ror.org/2566bfb96"));
  }
}
