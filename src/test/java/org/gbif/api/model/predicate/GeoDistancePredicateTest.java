/*
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
package org.gbif.api.model.predicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the GeoDistancePredicate class.
 */
public class GeoDistancePredicateTest {

  @Test
  public void testValidGeoDistance() {
    // km
    new GeoDistancePredicate("45", "100", "100km");

    // meters
    new GeoDistancePredicate("45", "100", "100m");

    // feet
    new GeoDistancePredicate("45", "100", "10ft");
  }

  @Test
  public void testInvalidPoint() {
    // Illegal latitude
    assertThrows(
        IllegalArgumentException.class, () -> new GeoDistancePredicate("180", "100", "100km"));

    // Illegal longitude
    assertThrows(
        IllegalArgumentException.class, () -> new GeoDistancePredicate("70", "240", "100km"));
  }

  @Test
  public void testInvalidDistanceUnit() {
    // Wrong distance units
    assertThrows(
        IllegalArgumentException.class, () -> new GeoDistancePredicate("60", "100", "100wt"));
  }
}
