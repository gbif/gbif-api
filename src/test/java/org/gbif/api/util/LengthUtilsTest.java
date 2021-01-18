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
package org.gbif.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthUtilsTest {

  @Test
  public void testMetersToLatDegree() {
    assertEquals(0.009043, LengthUtils.metersToLatDegree(1000), 0.0000001);
    assertEquals(0.000904,  LengthUtils.metersToLatDegree(100), 0.0000001);
    assertEquals(0.00009, LengthUtils.metersToLatDegree(10), 0.0000001);
    assertEquals(0.000045, LengthUtils.metersToLatDegree(5), 0.0000001);
    assertEquals(0.000009, LengthUtils.metersToLatDegree(1), 0.0000001);
    assertEquals(-0.000009, LengthUtils.metersToLatDegree(-1), 0.0000001);

    assertEquals(0.0, LengthUtils.metersToLatDegree(0), 0.000001);
  }

  @Test
  public void testLatDegreeToMeters() {
    assertEquals(1000, LengthUtils.latDegreeToMeters(0.009044), 0.1);
    assertEquals(100,  LengthUtils.latDegreeToMeters(0.000904),  0.1);
    assertEquals(10,   LengthUtils.latDegreeToMeters(0.00009), 0.1);
    assertEquals(5,    LengthUtils.latDegreeToMeters(0.000045), 0.1);
    assertEquals(1,    LengthUtils.latDegreeToMeters(0.000009), 0.1);

    assertEquals(0.0, LengthUtils.latDegreeToMeters(0), 0.000001);
  }
}
