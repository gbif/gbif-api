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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LicenseTest {

  @Test
  public void testFromString() {
    // positive matches
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/legalcode").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("HTTP://creativecommons.org/publicdomain/zero/1.0/legalcode").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl(" http://creativecommons.org/publicdomain/zero/1.0/legalcode ").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/legalcode/").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/").get());
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("HTTPS://CREATIVECOMMONS.ORG/PUBLICDOMAIN/ZERO/1.0/").get());
    assertEquals(License.CC_BY_4_0, License.fromLicenseUrl("http://creativecommons.org/licenses/by/4.0/legalcode").get());
    assertEquals(License.CC_BY_NC_4_0, License.fromLicenseUrl("http://creativecommons.org/licenses/by-nc/4.0/legalcode").get());

    // negative matches
    assertNull(License.fromLicenseUrl("CC0").orElse(null));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/legalcode").orElse(null));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/").orElse(null));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/legalcode").orElse(null));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/").orElse(null));
  }

  @Test
  public void testConcreteLicense() {
    assertFalse(License.UNSPECIFIED.isConcrete());
    assertFalse(License.UNSUPPORTED.isConcrete());
    assertTrue(License.CC0_1_0.isConcrete());
  }

  @Test
  public void testGetMostRestrictive(){
    assertEquals(License.CC_BY_4_0, License.getMostRestrictive(License.CC0_1_0, License.CC_BY_4_0, License.UNSPECIFIED));
    assertEquals(License.CC_BY_4_0, License.getMostRestrictive(License.CC_BY_4_0, License.CC0_1_0, License.UNSPECIFIED));

    assertEquals(License.CC_BY_NC_4_0, License.getMostRestrictive(License.CC_BY_4_0, License.CC_BY_NC_4_0, License.UNSPECIFIED));

    assertEquals(License.UNSPECIFIED, License.getMostRestrictive(License.UNSUPPORTED, License.CC0_1_0, License.UNSPECIFIED));
    assertEquals(License.UNSPECIFIED, License.getMostRestrictive(License.UNSUPPORTED, License.UNSPECIFIED, License.UNSPECIFIED));
    assertEquals(License.UNSPECIFIED, License.getMostRestrictive(License.CC_BY_NC_4_0, null, License.UNSPECIFIED));
  }

  @Test
  public void testLicenseOrdinalNumber() {
    int numberOfConcreteLicense = 0;
    for (License currLicense : License.values()) {
      if (currLicense.isConcrete()) {
        numberOfConcreteLicense++;
      }
    }

    //The ordinal number in the Enum implicitly defines the level of restriction.
    assertEquals(3, numberOfConcreteLicense, "The License value ordinal test covers all values of the Concrete License");
    assertTrue(License.CC0_1_0.ordinal() < License.CC_BY_4_0.ordinal());
    assertTrue(License.CC_BY_4_0.ordinal() < License.CC_BY_NC_4_0.ordinal());
  }
}
