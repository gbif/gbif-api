package org.gbif.api.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LengthUtilsTest {

  @Test
  public void testMetersToLatDegree() throws Exception {
    assertEquals(0.009043, LengthUtils.metersToLatDegree(1000), 0.0000001);
    assertEquals(0.000904,  LengthUtils.metersToLatDegree(100), 0.0000001);
    assertEquals(0.00009, LengthUtils.metersToLatDegree(10), 0.0000001);
    assertEquals(0.000045, LengthUtils.metersToLatDegree(5), 0.0000001);
    assertEquals(0.000009, LengthUtils.metersToLatDegree(1), 0.0000001);
    assertEquals(-0.000009, LengthUtils.metersToLatDegree(-1), 0.0000001);

    assertEquals(0.0, LengthUtils.metersToLatDegree(0), 0.000001);
  }

  @Test
  public void testLatDegreeToMeters() throws Exception {
    assertEquals(1000, LengthUtils.latDegreeToMeters(0.009044), 0.1);
    assertEquals(100,  LengthUtils.latDegreeToMeters(0.000904),  0.1);
    assertEquals(10,   LengthUtils.latDegreeToMeters(0.00009), 0.1);
    assertEquals(5,    LengthUtils.latDegreeToMeters(0.000045), 0.1);
    assertEquals(1,    LengthUtils.latDegreeToMeters(0.000009), 0.1);

    assertEquals(0.0, LengthUtils.latDegreeToMeters(0), 0.000001);
  }
}