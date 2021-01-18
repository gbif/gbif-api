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
package org.gbif.api.util.formatter;

import org.gbif.api.model.registry.eml.temporal.DateRange;
import org.gbif.api.model.registry.eml.temporal.SingleDate;
import org.gbif.api.model.registry.eml.temporal.TemporalCoverage;
import org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemporalCoverageFormatterVisitorTest {

  @Test
  public void testTemporalCoverageFormatterVisitor(){
    TemporalCoverageFormatterVisitor formatter = new TestFormatter();
    TemporalCoverage dateRange = new DateRange();
    TemporalCoverage singleDate = new SingleDate();
    TemporalCoverage verbatimTimePeriod = new VerbatimTimePeriod();

    assertEquals("dateRange", dateRange.acceptFormatter(formatter));
    assertEquals("singleDate", singleDate.acceptFormatter(formatter));
    assertEquals("verbatimTimePeriod", verbatimTimePeriod.acceptFormatter(formatter));
  }

  /**
   * Test implementation of TemporalCoverageFormatterVisitor to ensure all format() methods are reachable
   */
  private static class TestFormatter implements TemporalCoverageFormatterVisitor {

    @Override
    public String format(DateRange t) {
      return "dateRange";
    }

    @Override
    public String format(SingleDate t) {
      return "singleDate";
    }

    @Override
    public String format(VerbatimTimePeriod t) {
      return "verbatimTimePeriod";
    }
  }
}
