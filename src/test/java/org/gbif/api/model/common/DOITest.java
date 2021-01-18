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
package org.gbif.api.model.common;

import org.gbif.api.SerdeTestUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DOITest {

  @Test
  public void testPrefixConstructor() {
    DOI d = new DOI("10.1234", "1ASCDU");
    assertEquals(d, new DOI("doi:10.1234/1ASCDU"));
  }

  @Test
  public void testIsParsable() {
    assertFalse(DOI.isParsable(null));
    assertFalse(DOI.isParsable(""));
    assertFalse(DOI.isParsable("10.1234.1ASCDU"));
    assertFalse(DOI.isParsable("DOI:123"));
    assertFalse(DOI.isParsable("   "));

    assertTrue(DOI.isParsable("http://dx.doi.org/urn:doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("http://doi.org/urn:doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("http://dx.doi.org/doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("http://doi.org/doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("http://doi.org/10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("https://dx.doi.org/10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("https://doi.org/10.1234/1ASCDU"));

    assertTrue(DOI.isParsable("doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("urn:doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("urn:doi:10.1234/1ASCDU"));
    assertTrue(DOI.isParsable("doi:10.1234/1ascdu"));
    assertTrue(DOI.isParsable("urn:doi:10.1234/1ascdu"));
    assertTrue(DOI.isParsable("10.1234/1ascdu"));
    assertTrue(DOI.isParsable("10.1234/1ASCDU"));

    // This is real.
    assertTrue(DOI.isParsable("10.1643/0045-8511(2007)2007[699:Tnvsot]2.0.Co;2"));
    assertTrue(DOI.isParsable("doi:10.1643/0045-8511(2007)2007[699:Tnvsot]2.0.Co;2"));
    assertTrue(DOI.isParsable("https://doi.org/10.1643/0045-8511(2007)2007%5b699%3aTnvsot%5d2.0.Co%3b2"));

    // try subdivisions
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234.999/1ASCDU"));
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234.999.123/1ASCDU"));
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234.999.123/1ASCDU/more/and/more"));
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234.999.123/1ASCDU.more.and/more"));
    assertTrue(DOI.isParsable("http://dx.doi.org/10.1234.999.123/unicode-isällöwéd_h&$e"));
  }

  @Test
  public void testCreate() {
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

    new DOI("https://doi.org/10.1643/0045-8511(2007)2007%5b699%3aTnvsot%5d2.0.Co%3b2");

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
    assertEquals("456%23789", d.getSuffix());
    assertSame(d, "10.1000/456%23789");

    //getUrl will be returned as https (preferred http://www.doi.org/doi_handbook/3_Resolution.html)
    assertEquals(URI.create("https://doi.org/10.1000/" + URLEncoder.encode("456%23789", "UTF-8")), d.getUrl());
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

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Container container = (Container) o;
      return Objects.equals(doi1, container.doi1) &&
        Objects.equals(doi2, container.doi2);
    }

    @Override
    public int hashCode() {
      return Objects.hash(doi1, doi2);
    }
  }

  @Test
  public void testSerDe() throws IOException {
    DOI d = new DOI("10.1234", "1ASCDU");
    SerdeTestUtils.testSerDe(d, DOI.class);

    Container source = new Container(new DOI("doi:10.1038/nature.2014.16460"), new DOI("http://dx.doi.org/10.1034/gbif.2014.xscdf2"));
    try {
      String json = SerdeTestUtils.testSerDe(source, Container.class);
      assertFalse(json.contains("doi:10."), "DOIs should be shown without a scheme");
      assertFalse(json.contains("http"), "DOIs should not use http resolvers");
      assertFalse(json.contains("doi.org"), "DOIs should not use http resolvers");
      System.out.println(json);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
