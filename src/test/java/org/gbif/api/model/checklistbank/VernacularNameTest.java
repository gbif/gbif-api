/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

public class VernacularNameTest {

  @Test
  public void testEquals() {
    VernacularName v1 = new VernacularName();
    v1.setSourceTaxonKey(123);
    v1.setCountry(Country.DENMARK);
    v1.setPreferred(true);

    VernacularName v2 = new VernacularName();
    v2.setSourceTaxonKey(123);
    v2.setCountry(Country.DENMARK);
    v2.setPreferred(true);

    assertEquals(v1, v2);

    v2.setSourceTaxonKey(124);

  }

}
