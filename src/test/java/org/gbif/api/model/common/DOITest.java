package org.gbif.api.model.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DOITest {

  @Test
  public void testCreate() throws Exception {
    DOI d = new DOI("http://dx.doi.org/10.1234/1ASCDU");

    assertSame(d, "http://dx.doi.org/urn:doi:10.1234/1ASCDU");
    assertSame(d, "http://doi.org/urn:doi:10.1234/1ASCDU");
    assertSame(d, "http://dx.doi.org/doi:10.1234/1ASCDU");
    assertSame(d, "http://doi.org/doi:10.1234/1ASCDU");
    assertSame(d, "http://dx.doi.org/10.1234/1ASCDU");
    assertSame(d, "http://doi.org/10.1234/1ASCDU");
    assertSame(d, "https://dx.doi.org/10.1234/1ASCDU");
    assertSame(d, "https://doi.org/10.1234/1ASCDU");

    assertSame(d, "doi:10.1234/1ASCDU");
    assertSame(d, "urn:doi:10.1234/1ASCDU");
    assertSame(d, "doi:10.1234/1ASCDU");
    assertSame(d, "urn:doi:10.1234/1ASCDU");
    assertSame(d, "doi:10.1234/1ascdu");
    assertSame(d, "urn:doi:10.1234/1ascdu");
    assertSame(d, "10.1234/1ascdu");
    assertSame(d, "10.1234/1ASCDU");

    // try subdivisions
    new DOI("http://dx.doi.org/10.1234.999/1ASCDU");
    new DOI("http://dx.doi.org/10.1234.999.123/1ASCDU");
    new DOI("http://dx.doi.org/10.1234.999.123/1ASCDU/more/and/more");
    new DOI("http://dx.doi.org/10.1234.999.123/1ASCDU.more.and/more");
    new DOI("http://dx.doi.org/10.1234.999.123/unicode-isällöwéd_h&$e");

    // try invalid ones
    assertInvalid("uri:doi:10.1234/1ascdu");
    assertInvalid("doi::10.1234/1ascdu");
    assertInvalid("10/1234/1ascdu");
    assertInvalid("doi");
    assertInvalid("Tim Robertson");
    assertInvalid("http://dx.doi.org");
    assertInvalid("http://dx.doi.org/10");
    assertInvalid("http://doi.org");
    assertInvalid("http://doi.org/10.453621432764678123");
    assertInvalid("http://dx.doi.org/11.1234.999/1ASCDU");
  }

  @Test
  public void testUrlConversion() throws Exception {
    DOI d = new DOI("http://doi.org/10.1000/456%23789");
    assertEquals("456#789", d.getSuffix());
    assertSame(d, "10.1000/456#789");
  }

  private void assertSame(DOI doi, String doi2) {
    DOI d2 = new DOI(doi2);
    assertEquals(d2, doi);
  }

  private void assertInvalid(String doi) {
    try {
      new DOI(doi);
      fail("Invalid DOI expected: " + doi);
    } catch (IllegalArgumentException e) {
      // expected!
    }
  }
}