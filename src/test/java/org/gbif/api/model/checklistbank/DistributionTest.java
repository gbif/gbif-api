/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.Country;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DistributionTest {

  @Test
  public void testEquals() {
    Distribution d1 = new Distribution();
    d1.setKey(123);
    d1.setCountry(Country.DENMARK);
    d1.setStartDayOfYear(1);

    Distribution d2 = new Distribution();
    d2.setKey(123);
    d2.setCountry(Country.DENMARK);
    d2.setStartDayOfYear(1);

    assertEquals(d1, d2);

    d2.setKey(124);

    assertFalse(d1.equals(d2));
  }

}
