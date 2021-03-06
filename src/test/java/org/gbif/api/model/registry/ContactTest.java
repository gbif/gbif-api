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
package org.gbif.api.model.registry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest {

  @Test
  public void testUserId() {
    Contact c = new Contact();

    c.addUserId(null, null);
    assertNotNull(c.getUserId());
    assertTrue(c.getUserId().isEmpty());

    c.addUserId(null, "3421423");
    assertEquals("3421423", c.getUserId().get(0));

    c.addUserId("", "3421423");
    assertEquals("3421423", c.getUserId().get(1));

    // Directory prefix absent from the id.
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

    // Directory prefix included in the id
    c.addUserId("gbif", "gbif:105539");
    assertEquals("gbif:105539", c.getUserId().get(10));

    c.addUserId("http://orcid.org/", "http://orcid.org/0000-0003-0623-6682");
    assertEquals("http://orcid.org/0000-0003-0623-6682", c.getUserId().get(11));

    c.addUserId("http://orcid.org", "http://orcid.org/0000-0003-0623-6682");
    assertEquals("http://orcid.org/0000-0003-0623-6682", c.getUserId().get(12));

    c.addUserId("https://orcid.org", "http://orcid.org/0000-0003-0623-6682");
    assertEquals("http://orcid.org/0000-0003-0623-6682", c.getUserId().get(13));

    c.addUserId("http://orcid.org", "https://orcid.org/0000-0003-0623-6682");
    assertEquals("https://orcid.org/0000-0003-0623-6682", c.getUserId().get(14));

    c.addUserId("http://orcid.org", "orcid.org/0000-0003-0623-6682");
    assertEquals("http://orcid.org/0000-0003-0623-6682", c.getUserId().get(15));

    c.addUserId("https://orcid.org", "orcid.org/0000-0003-0623-6682");
    assertEquals("https://orcid.org/0000-0003-0623-6682", c.getUserId().get(16));

    c.addUserId("https://scholar.google.com/citations?user=", "https://scholar.google.com/citations?user=jvW0IrIAAAAJ");
    assertEquals("https://scholar.google.com/citations?user=jvW0IrIAAAAJ", c.getUserId().get(17));

    // Just to check this test is written correctly.
    assertEquals(18, c.getUserId().size());
  }

  @Test
  public void testGetCompleteName() {
    Contact c = new Contact();
    assertEquals("", c.computeCompleteName());

    c.setFirstName("FirstName ");
    assertEquals("FirstName", c.computeCompleteName());

    c.setLastName("LastName");
    assertEquals("FirstName LastName", c.computeCompleteName());

    c.setFirstName(" ");
    assertEquals("LastName", c.computeCompleteName());
  }
}
