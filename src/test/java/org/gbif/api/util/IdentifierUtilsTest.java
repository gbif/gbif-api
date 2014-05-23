package org.gbif.api.util;

import org.gbif.api.vocabulary.IdentifierType;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(value = Parameterized.class)
public class IdentifierUtilsTest {

  private String expected;
  private String identifier;
  private IdentifierType type;

  /**
   * A test is run for each set of parameters. To add a new test, just add a new set of parameters.
   */
  @Parameterized.Parameters
  public static Collection<Object[]> getTestParameters() {
    return Arrays.asList(new Object[][] { {null, "49c5b4ac-e3bf-401b-94b1-c94a2ad5c8d6", IdentifierType.UUID},
      {"http://data.gbif.org/datasets/resource/ds1", "ds1", IdentifierType.GBIF_PORTAL}, {null, "local_1:dx:4", IdentifierType.UNKNOWN},
      {"http://en.wikipedia.org/wiki/Handle_1", "http://en.wikipedia.org/wiki/Handle_1", IdentifierType.HANDLER},
      {"urn:ds:acns:1", "urn:ds:acns:1", IdentifierType.URI},
      {"http://ipt.gbif.org/resource.do?r=ds1", "http://ipt.gbif.org/resource.do?r=ds1", IdentifierType.URL},
      {"ftp://ftp.gbif.org", "ftp://ftp.gbif.org", IdentifierType.FTP},
      {"http://dx.doi.org/10.1016/S1097-2765(03)00225-9", "10.1016/S1097-2765(03)00225-9", IdentifierType.DOI},
      {"http://lsid.tdwg.org/summary/132187", "132187", IdentifierType.LSID},});
  }

  public IdentifierUtilsTest(String expected, String identifier, IdentifierType type) {
    this.expected = expected;
    this.identifier = identifier;
    this.type = type;
  }

  @Test
  public void testGetIdentifierLink() {
    assertEquals(expected, IdentifierUtils.getIdentifierLink(identifier, type));
  }

  @Test
  public void testGetIdentifierLinkWithoutIdentifer() {
    assertNull(IdentifierUtils.getIdentifierLink(null, type));
  }

  @Test
  public void testGetIdentifierLinkWithoutType() {
    assertNull(IdentifierUtils.getIdentifierLink(identifier, null));
  }

}
