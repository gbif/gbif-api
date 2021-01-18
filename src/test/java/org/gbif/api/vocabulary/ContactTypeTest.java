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
package org.gbif.api.vocabulary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTypeTest {

  @Test
  public void testFromString() {
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("POINT_OF_CONTACT"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("POINTOFCONTACT"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("point_of_contact"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("pointOfContact"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("pointofcontact"));
    assertEquals(ContactType.CURATOR, ContactType.fromString("curator"));
  }
}
