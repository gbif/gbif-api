package org.gbif.api.vocabulary;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LicenseTest {

  @Test
  public void testFromString() throws Exception {
    // positive matches
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/legalcode"));
    assertEquals(License.CC0_1_0, License.fromLicenseUrl(" http://creativecommons.org/publicdomain/zero/1.0/legalcode "));
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/legalcode/"));
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0"));
    assertEquals(License.CC0_1_0, License.fromLicenseUrl("http://creativecommons.org/publicdomain/zero/1.0/"));
    assertEquals(License.CC_BY_4_0, License.fromLicenseUrl("http://creativecommons.org/licenses/by/4.0/legalcode"));
    assertEquals(License.CC_BY_NC_4_0,
      License.fromLicenseUrl("http://creativecommons.org/licenses/by-nc/4.0/legalcode"));
    // negative matches
    assertNull(License.fromLicenseUrl("CC0"));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/legalcode"));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by/3.0/"));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/legalcode"));
    assertNull(License.fromLicenseUrl("https://creativecommons.org/licenses/by-nc/2.0/"));
  }
}
