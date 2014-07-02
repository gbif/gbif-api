package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTypeTest {

  @Test
  public void testFromString() throws Exception {
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("POINT_OF_CONTACT"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("POINTOFCONTACT"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("point_of_contact"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("pointOfContact"));
    assertEquals(ContactType.POINT_OF_CONTACT, ContactType.fromString("pointofcontact"));
  }

}
