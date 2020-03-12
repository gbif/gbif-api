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

import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Lists;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DwcaValidationReportTest {

  @Test
  public void testIsValid() throws Exception {
    assertTrue(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(10, 10, 0, 10, 0, true),
      new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList()), null
    ).isValid());

    // this is a regular case with Plazi checklist archives.
    // Occurrences are declared in meta.xml but the data file is empty
    // needs to validate for checklist indexing to happen!
    assertTrue(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(0, 0, 0, 0, 0, true),
      new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList()), null
    ).isValid());

    assertTrue(new DwcaValidationReport(UUID.randomUUID(),
      new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList())
    ).isValid());

    assertTrue(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(10, 4, 0, 10, 0, true)
    ).isValid());

    assertFalse(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(10, 4, 0, 6, 0, true),
      new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList()), null
    ).isValid());

    assertFalse(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(10, 10, 0, 10, 0, true),
      new GenericValidationReport(10, true, Lists.<String>newArrayList("r23"), Lists.<Integer>newArrayList()), null
    ).isValid());

    assertFalse(new DwcaValidationReport(UUID.randomUUID(),
      new OccurrenceValidationReport(10, 10, 0, 10, 0, true),
      new GenericValidationReport(10, true, Lists.<String>newArrayList(), Lists.<Integer>newArrayList(32)), null
    ).isValid());

    assertFalse(new DwcaValidationReport(UUID.randomUUID(), "Dont like the smell").isValid());
  }
}
