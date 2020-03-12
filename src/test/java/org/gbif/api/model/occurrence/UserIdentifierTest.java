package org.gbif.api.model.occurrence;

import java.util.Optional;

import org.gbif.api.vocabulary.UserIdentifierType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserIdentifierTest {

  @Test
  public void testValueOf() {
    Optional<UserIdentifier> emptyValue = UserIdentifier.valueOf("");
    Optional<UserIdentifier> nullValue = UserIdentifier.valueOf(null);
    Optional<UserIdentifier> delimiter = UserIdentifier.valueOf(":");
    Optional<UserIdentifier> comma = UserIdentifier.valueOf(",");
    Optional<UserIdentifier> orcid = UserIdentifier.valueOf("orcid:213123");
    Optional<UserIdentifier> orcidUpper = UserIdentifier.valueOf("ORCID:213123");

    assertFalse(emptyValue.isPresent());
    assertFalse(nullValue.isPresent());
    assertFalse(delimiter.isPresent());
    assertFalse(comma.isPresent());
    assertFalse(orcid.isPresent());

    assertTrue(orcidUpper.isPresent());
    assertEquals(UserIdentifierType.ORCID, orcidUpper.get().getType());
    assertEquals("213123", orcidUpper.get().getValue());
  }

}
