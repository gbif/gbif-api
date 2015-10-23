package org.gbif.api.util.formatter;

import org.gbif.api.model.registry.eml.temporal.DateRange;
import org.gbif.api.model.registry.eml.temporal.SingleDate;
import org.gbif.api.model.registry.eml.temporal.TemporalCoverage;
import org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
