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
package org.gbif.api.model.crawler;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericValidationReportTest {

  @Test
  public void testConstructor() {
    assertThrows(NullPointerException.class, () -> new GenericValidationReport(10, true, null, null));
  }

  @Test
  public void testConstructor2() {
    assertThrows(NullPointerException.class, () -> new GenericValidationReport(10, true, Collections.emptyList(), null));
  }

  @Test
  public void testConstructor3() {
    assertThrows(NullPointerException.class, () -> new GenericValidationReport(10, true, null, Collections.emptyList()));
  }

  @Test
  public void testIsValid() {
    assertTrue(new GenericValidationReport(10, true, Collections.emptyList(), Collections.emptyList()).isValid());
    assertTrue(new GenericValidationReport(0, true, Collections.emptyList(), Collections.emptyList()).isValid());
    assertFalse(new GenericValidationReport(10, true, Collections.emptyList(), Collections.singletonList(1)).isValid());
    assertFalse(new GenericValidationReport(10, true, Collections.emptyList(), Arrays.asList(1,2,3,4)).isValid());
    assertFalse(new GenericValidationReport(10, true, Collections.singletonList("r32"),
      Arrays.asList(1, 2)).isValid());
    assertFalse(new GenericValidationReport(10, true, Collections.singletonList("r32"), Collections.emptyList()).isValid());
  }
}
