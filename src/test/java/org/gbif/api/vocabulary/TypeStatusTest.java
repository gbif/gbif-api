package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TypeStatusTest {

  @Test
  public void testSpecimenTypeStatusList() throws Exception {
    for (TypeStatus ts : TypeStatus.specimenTypeStatusList()) {
      assertTrue(ts.isTypeSpecimen());
    }
  }

  @Test
  public void testNameTypeStatusList() throws Exception {
    for (TypeStatus ts : TypeStatus.nameTypeStatusList()) {
      if (ts != TypeStatus.TYPE) {
        assertFalse(ts.isTypeSpecimen());
      }
    }
  }

  @Test
  public void testIsTypeSpecimen() throws Exception {
    assertFalse(TypeStatus.TYPE_SPECIES.isTypeSpecimen());
    assertTrue(TypeStatus.HOLOTYPE.isTypeSpecimen());
    assertTrue(TypeStatus.TYPE.isTypeSpecimen());
  }
}
