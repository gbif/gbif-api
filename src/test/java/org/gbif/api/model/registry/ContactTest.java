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

    c.addUserId("https://scholar.google.com/citations?user=", "jvW0IrIAAAAJ");
    assertEquals("https://scholar.google.com/citations?user=jvW0IrIAAAAJ", c.getUserId().get(6));

    c.addUserId("https://www.linkedin.com/in/", "john-smith-12345");
    assertEquals("https://www.linkedin.com/in/john-smith-12345", c.getUserId().get(7));

    c.addUserId("https://www.linkedin.com/profile/view?id=", "AAkAAABiOnwBeoX3a3wKqe4IEqDkJ_ifoVj1234");
    assertEquals("https://www.linkedin.com/profile/view?id=AAkAAABiOnwBeoX3a3wKqe4IEqDkJ_ifoVj1234", c.getUserId().get(8));

    c.addUserId(", ,", ", ,");
    assertEquals(", ,", c.getUserId().get(9));
  }

  @Test
  public void testGetCompleteName() {
    Contact c = new Contact();
    assertTrue(c.getCompleteName().equals(""));

    c.setFirstName("FirstName ");
    assertTrue(c.getCompleteName().equals("FirstName"));

    c.setLastName("LastName");
    assertTrue(c.getCompleteName().equals("FirstName LastName"));

    c.setFirstName(" ");
    assertTrue(c.getCompleteName().equals("LastName"));
  }

}
