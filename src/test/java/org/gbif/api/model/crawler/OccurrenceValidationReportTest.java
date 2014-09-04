package org.gbif.api.model.crawler;

import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.*;

public class OccurrenceValidationReportTest {

  @Test
  public void testGoodTripletsNoOccId() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 100, 0, 0, 100, true);
    assertNull(report.getInvalidationReason());
    assertTrue(report.isValid());
  }

  @Test
  public void testMissingTripletsGoodOccId() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 0, 100, 100, 0, true);
    assertNull(report.getInvalidationReason());
    assertTrue(report.isValid());
  }

  @Test
  public void testDuplicateTripletsGoodOccId() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 80, 0, 100, 0, true);
    assertNull(report.getInvalidationReason());
    assertTrue(report.isValid());
  }

  @Test
  public void testMissingTripletsNoOccId() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 70, 30, 0, 100, true);
    assertFalse(report.isValid());
    assertEquals(
      "Archive invalid because [30% invalid triplets is > than threshold of 25%; 100 records without an occurrence id "
      + "(should be 0)]",
      report.getInvalidationReason());
  }

  @Test
  public void testDuplicateTripletsNoOccId() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 80, 0, 0, 100, false);
    assertEquals(
      "Archive invalid because [20 duplicate triplets detected; 100 records without an occurrence id (should be 0)]",
      report.getInvalidationReason());
    assertFalse(report.isValid());
  }

  @Test
  public void testBadEverything() {
    OccurrenceValidationReport report = new OccurrenceValidationReport(100, 50, 28, 80, 5, true);
    assertEquals(
      "Archive invalid because [28% invalid triplets is > than threshold of 25%; 22 duplicate triplets detected; 5 "
      + "records without an occurrence id (should be 0); 15 duplicate occurrence ids detected]",
      report.getInvalidationReason());
    assertFalse(report.isValid());
  }
}