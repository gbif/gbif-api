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
    assertEquals(License.CC_BY_4_0, License.fromLicenseUrl("http://creativecommons.org/licenses/by/4.0/legalcode").get());
    assertEquals(License.CC_BY_NC_4_0,
      License.fromLicenseUrl("http://creativecommons.org/licenses/by-nc/4.0/legalcode").get());
    
    // negative matches
    assertNull(License.fromLicenseUrl("CC0").orNull());
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/legalcode").orNull());
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/").orNull());
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/legalcode").orNull());
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/").orNull());
  }

  @Test
  public void testConcreteLicense() {
    assertFalse(License.UNSPECIFIED.isConcrete());
    assertFalse(License.UNSUPPORTED.isConcrete());
    assertTrue(License.CC0_1_0.isConcrete());
  }
}
