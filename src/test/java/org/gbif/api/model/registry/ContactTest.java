package org.gbif.api.model.registry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactTest {

  @Test
  public void testUserId() throws Exception {
    Contact c = new Contact();

    c.addUserId(null, null);
    assertTrue(c.getUserId().isEmpty());

    c.addUserId(null, "3421423");
    assertEquals("3421423", c.getUserId().get(0));

    c.addUserId("", "3421423");
    assertEquals("3421423", c.getUserId().get(1));

    c.addUserId("gbif", "3421423");
    assertEquals("gbif:3421423", c.getUserId().get(2));

    c.addUserId("http://orcid.org/", "3421423");
    assertEquals("http://orcid.org/3421423", c.getUserId().get(3));

    c.addUserId("http://orcid.org", "3421423");
    assertEquals("http://orcid.org/3421423", c.getUserId().get(4));

    c.addUserId("http://orcid.org", "de/12-3421423");
    assertEquals("http://orcid.org/de/12-3421423", c.getUserId().get(5));
  }
}
