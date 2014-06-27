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
package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameTypeTest {

  @Test
  public void testIsBetterThan() throws Exception {
    assertTrue(NameType.WELLFORMED.isBetterThan(NameType.SCINAME));
    assertTrue(NameType.WELLFORMED.isBetterThan(NameType.VIRUS));
    assertTrue(NameType.WELLFORMED.isBetterThan(NameType.DOUBTFUL));
    assertTrue(NameType.WELLFORMED.isBetterThan(NameType.BLACKLISTED));
    assertTrue(NameType.WELLFORMED.isBetterThan(NameType.INFORMAL));
    assertTrue(NameType.SCINAME.isBetterThan(NameType.DOUBTFUL));
    assertTrue(NameType.VIRUS.isBetterThan(NameType.DOUBTFUL));
    assertTrue(NameType.HYBRID.isBetterThan(NameType.DOUBTFUL));

    assertFalse(NameType.HYBRID.isBetterThan(NameType.VIRUS));
    assertFalse(NameType.VIRUS.isBetterThan(NameType.HYBRID));
    assertFalse(NameType.HYBRID.isBetterThan(NameType.SCINAME));
    assertFalse(NameType.BLACKLISTED.isBetterThan(NameType.DOUBTFUL));
  }

  @Test
  public void testIsParsable() throws Exception {
    assertTrue(NameType.SCINAME.isParsable());
    assertTrue(NameType.WELLFORMED.isParsable());
    assertTrue(NameType.DOUBTFUL.isParsable());
    assertFalse(NameType.VIRUS.isParsable());
    assertFalse(NameType.BLACKLISTED.isParsable());
    assertFalse(NameType.HYBRID.isParsable());
  }

}
