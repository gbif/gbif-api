package org.gbif.api.model.crawler;

import java.util.List;
import java.util.UUID;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A report of the state of a dwc archive, mainly testing the existance and use of unique identifiers.
 * It combines checklist and occurrence reports.
 */
@Immutable
@ThreadSafe
public class DwcaValidationReport {
  private final UUID datasetKey;

  private final OccurrenceValidationReport occurrenceReport;

  private final ChecklistValidationReport checklistReport;

  private final String invalidationReason;

  public boolean isValid() {
    return invalidationReason == null
      && (occurrenceReport == null || occurrenceReport.isValid())
      && (checklistReport == null || checklistReport.isValid());
  }

  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey, OccurrenceValidationReport occurrenceReport,
    ChecklistValidationReport checklistReport) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = checkNotNull(occurrenceReport, "occurrenceReport can't be null");
    this.checklistReport = checkNotNull(checklistReport, "checklistReport can't be null");
    this.invalidationReason = null;
  }

  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey, OccurrenceValidationReport occurrenceReport) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = checkNotNull(occurrenceReport, "occurrenceReport can't be null");
    this.checklistReport = null;
    this.invalidationReason = null;
  }

  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey, ChecklistValidationReport checklistReport) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.checklistReport = checkNotNull(checklistReport, "checklistReport can't be null");
    this.invalidationReason = null;
  }

  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey, String invalidationReason) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.checklistReport = null;
    this.invalidationReason = checkNotNull(invalidationReason, "invalidationReason can't be null");
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public String getInvalidationReason() {
    StringBuilder sb = new StringBuilder();
    if (invalidationReason != null) {
      sb.append(invalidationReason);
    }
    if (occurrenceReport != null && !occurrenceReport.isValid()) {
      if (sb.length() > 1) {
        sb.append("\n");
      }
      sb.append("Invalid Occurrences: ");
      sb.append(occurrenceReport.getInvalidationReason());
    }
    if (checklistReport != null && !checklistReport.isValid()) {
      if (sb.length() > 1) {
        sb.append("\n");
      }
      sb.append("Invalid Checklist: ");
      sb.append(checklistReport.getInvalidationReason());
    }
    return Strings.emptyToNull(sb.toString());
  }

  public OccurrenceValidationReport getOccurrenceReport() {
    return occurrenceReport;
  }

  public ChecklistValidationReport getChecklistReport() {
    return checklistReport;
  }
}