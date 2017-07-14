package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Ensure that roles defined in {@link UserRole} and {@link AppRole} are unique.
 */
public class RolesTest {

  @Test
  public void testRolesUniqueness() {
    for(UserRole ur : UserRole.values()) {
      try{
        AppRole.valueOf(ur.name());
        fail(ur.name() + " is defined in UserRole AND AppRole.");
      }
      catch (IllegalArgumentException iaEx) {
        //expected
      }
    }
  }
}
