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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Checks InterpretationRemark implementations.
 */
public class InterpretationRemarkTest {

  private static final String ROOT_PACKAGE = "org.gbif.api.vocabulary";

  // All the implementations of InterpretationRemark
  private static final List<Class<?>> IMPLEMENTING_CLASSES =
    Arrays.asList(NameUsageIssue.class, OccurrenceIssue.class);

  /**
   * Checks all classes (enumerations) that implement {@link InterpretationRemark} to ensure they
   * have unique entry names. {@link InterpretationRemark} implementations can be used as keys and
   * can also be serialized as String. For this reason we want to ensure we have unique entries
   * among implementations. This is mainly to avoid confusion when we do aggregation.
   */
  @Test
  public void testInterpretationRemarkImplementations() {
    Set<String> interpretationRemarks = new HashSet<>();
    Set<String> interpretationRemarksId = new HashSet<>();
    IMPLEMENTING_CLASSES
      .forEach(cl -> Arrays.asList((InterpretationRemark[]) cl.getEnumConstants()).forEach(
        ir -> {
          assertTrue("Enumeration value " + ir
              + " is unique within all InterpretationRemark implementations.",
            interpretationRemarks.add(ir.toString()));
          assertTrue("Enumeration value " + ir + " with getID " + ir.getId()
              + " is unique within all InterpretationRemark implementations.",
            interpretationRemarksId.add(ir.getId()));
        }
        )
      );
  }

  @Test
  public void testInterpretationRemarkNotNulls() {
    IMPLEMENTING_CLASSES
      .forEach(cl -> Arrays.asList((InterpretationRemark[]) cl.getEnumConstants()).forEach(
        ir -> assertNotNull(
          "InterpretationRemark implementations return a not null value for getSeverity()",
          ir.getSeverity())
        )
      );
  }

  @Test
  public void testInterpretationRemarkDeprecated() {
    assertTrue(OccurrenceIssue.COORDINATE_PRECISION_UNCERTAINTY_MISMATCH.isDeprecated());
    assertFalse(OccurrenceIssue.BASIS_OF_RECORD_INVALID.isDeprecated());
  }
}
