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
package org.gbif.api.model.occurrence;

import org.gbif.api.model.occurrence.geo.DistanceUnit;

import org.junit.jupiter.api.Test;

import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for DistanceUnit class.
 */
public class DistanceUnitTests {

  @Test
  public void testDistanceUnitParseNames() {
    //Test all unit names
    for (DistanceUnit unit : DistanceUnit.values()) {
      for (String name : unit.getNames()) {
        DistanceUnit.parseDistance("1" + name);
      }
    }
  }

  @Test
  public void testDistanceUnitParseWrongNames() {
    assertThrows(IllegalArgumentException.class,
                () -> DistanceUnit.parseDistance("100wt")); //Wrong unit
  }

  @Test
  public void testDistanceUnitConversions() {
    //Simple conversion test
    DistanceUnit.Distance oneKm = DistanceUnit.parseDistance("1km");
    DistanceUnit.Distance tenThousandCm = DistanceUnit.parseDistance("100000cm");
    assertThat(oneKm, equalToObject(tenThousandCm));
  }


  @Test
  public void testGeoDistanceStringParsing() {
    //Simple conversion test
    String rawGeoDistance = "40.2, 100.2, 1.1km";
    DistanceUnit.GeoDistance oneKmDistance = DistanceUnit.GeoDistance.parseGeoDistance(rawGeoDistance);

    assertEquals(rawGeoDistance, oneKmDistance.toGeoDistanceString());
  }

}
