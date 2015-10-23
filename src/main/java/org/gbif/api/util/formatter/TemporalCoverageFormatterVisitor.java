package org.gbif.api.util.formatter;


import org.gbif.api.model.registry.eml.temporal.DateRange;
import org.gbif.api.model.registry.eml.temporal.SingleDate;
import org.gbif.api.model.registry.eml.temporal.VerbatimTimePeriod;

/**
 * Allows formatter to be dynamically selected based on the concrete type of TemporalCoverage at runtime.
 *
 */
public interface TemporalCoverageFormatterVisitor {
  String format(DateRange t);
  String format(SingleDate t);
  String format(VerbatimTimePeriod t);
}
