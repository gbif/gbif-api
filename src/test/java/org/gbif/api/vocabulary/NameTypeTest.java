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
package org.gbif.api.vocabulary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameTypeTest {

  @Test
  public void testIsBackbone() {
    assertTrue(NameType.SCIENTIFIC.isBackboneType());
    assertTrue(NameType.VIRUS.isBackboneType());
    assertTrue(NameType.DOUBTFUL.isBackboneType());

    assertFalse(NameType.PLACEHOLDER.isBackboneType());
    assertFalse(NameType.HYBRID.isBackboneType());
    assertFalse(NameType.INFORMAL.isBackboneType());
    assertFalse(NameType.CULTIVAR.isBackboneType());
    assertFalse(NameType.NO_NAME.isBackboneType());
    assertFalse(NameType.OTU.isBackboneType());
    assertFalse(NameType.BLACKLISTED.isBackboneType());
  }

  @Test
  public void testIsParsable() {
    assertTrue(NameType.SCIENTIFIC.isParsable());
    assertTrue(NameType.INFORMAL.isParsable());
    assertTrue(NameType.DOUBTFUL.isParsable());
    assertTrue(NameType.BLACKLISTED.isParsable());

    assertFalse(NameType.VIRUS.isParsable());
    assertFalse(NameType.NO_NAME.isParsable());
    assertFalse(NameType.HYBRID.isParsable());
    assertFalse(NameType.OTU.isParsable());
  }
}
