package org.gbif.api.vocabulary;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LicenseTest {

  @Test
  public void testFromString() throws Exception {
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
    assertEquals("The License value ordinal test covers all values of the Concrete License", 3, numberOfConcreteLicense);
    assertTrue(License.CC0_1_0.ordinal() < License.CC_BY_4_0.ordinal());
    assertTrue(License.CC_BY_4_0.ordinal() < License.CC_BY_NC_4_0.ordinal());
  }
}
