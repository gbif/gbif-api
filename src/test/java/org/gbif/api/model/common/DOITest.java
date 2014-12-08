package org.gbif.api.model.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DOITest {

  @Test
  public void testPrefixConstructor() throws Exception {
    DOI d = new DOI("10.1234", "1ASCDU");
    assertEquals(d, new DOI("doi:10.1234/1ASCDU"));
  }

  @Test
  public void testGbif() throws Exception {
    assertFalse(new DOI("10.1234/1ASCDU").isGbif());
    assertFalse(new DOI("10.321/1ASCDU").isGbif());
    assertFalse(new DOI("10.1234.21/1ASCDU").isGbif());
    assertTrue(new DOI("10.15468/1ASCDU").isGbif());
  }

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



  /**
   * A container of Languages using 2 properties.
   */
  private static class Container {

    public DOI doi1;
    public DOI doi2; // verify that the names don't matter

    public Container(DOI doi1, DOI doi2) {
      this.doi1 = doi1;
      this.doi2 = doi2;
    }

    // used by serializer below
    public Container() {
    }
  }

  @Test
  public void testSerDe() {
    ObjectMapper mapper = new ObjectMapper();
    Container source = new Container(new DOI("doi:10.1038/nature.2014.16460"), new DOI("http://dx.doi.org/10.1034/gbif.2014.xscdf2"));

    try {
      String json = mapper.writeValueAsString(source);
      assertTrue("DOIs should start with doi:10.", json.contains("\"doi:10."));
      assertFalse("DOIs should not use http resolvers", json.contains("http"));
      assertFalse("DOIs should not use http resolvers", json.contains("doi.org"));
      System.out.println(json);
      Container target = mapper.readValue(json, Container.class);
      assertEquals("doi1 differs", source.doi1, target.doi1);
      assertEquals("doi2 differs", source.doi2, target.doi2);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}