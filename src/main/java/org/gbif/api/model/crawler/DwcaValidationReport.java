package org.gbif.api.model.crawler;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A report of the validity of a DwC-A.
 * <p/>
 * A DwC-A can comprise of a core and extensions.  This implementation is intended to be used as follows:
 * <ol>
 *   <li>Constructed with an OccurrenceValidationReport when the core of the DwC-A is an Occurrence</li>
 *   <li>Constructed with a GenericValidationReport when the core of the DwC-A is Taxon or Sample - optionally an
 *   occurrence report may be given in addition to represent the occurrence extension validity</li>
 * </ol>
 */
@Immutable
@ThreadSafe
public class DwcaValidationReport {
  private final UUID datasetKey;

  // the occurrence report may represent occurrences observed from the core or from extensions.
  @Nullable
  private final OccurrenceValidationReport occurrenceReport;

  // the generic report of the core which will always be present unless the core is of type occurrence, where it may be
  // null
  @Nullable
  private final GenericValidationReport genericReport;

  private final String invalidationReason;

  public boolean isValid() {
    return invalidationReason == null
      && (occurrenceReport == null || occurrenceReport.isValid())
      && (genericReport == null || genericReport.isValid());
  }

  @JsonCreator
  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey,
    @JsonProperty("occurrenceReport") OccurrenceValidationReport occurrenceReport,
    @JsonProperty("genericReport") GenericValidationReport genericReport,
    @JsonProperty("invalidationReason") String invalidationReason) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    // verify one of the 3 is not null
    checkArgument(invalidationReason!=null || occurrenceReport != null || genericReport != null,
        "Either a report or invalidationReason cannot be null");
    this.occurrenceReport = occurrenceReport;
    this.genericReport = genericReport;
    this.invalidationReason = invalidationReason;
  }

  public DwcaValidationReport(UUID datasetKey, OccurrenceValidationReport occurrenceReport) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = checkNotNull(occurrenceReport, "occurrenceReport can't be null");
    this.genericReport = null;
    this.invalidationReason = null;
  }

  public DwcaValidationReport(UUID datasetKey, GenericValidationReport genericReport) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.genericReport = checkNotNull(genericReport, "genericReport can't be null");
    this.invalidationReason = null;
  }

  public DwcaValidationReport(UUID datasetKey, String invalidationReason) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.genericReport = null;
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
    if (genericReport != null && !genericReport.isValid()) {
      if (sb.length() > 1) {
        sb.append("\n");
      }
      sb.append("Invalid Checklist: ");
      sb.append(genericReport.getInvalidationReason());
    }
    return Strings.emptyToNull(sb.toString());
  }

  public OccurrenceValidationReport getOccurrenceReport() {
    return occurrenceReport;
  }

  public GenericValidationReport getGenericReport() {
    return genericReport;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(datasetKey, occurrenceReport, genericReport, invalidationReason);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final DwcaValidationReport other = (DwcaValidationReport) obj;
    return Objects.equal(this.datasetKey, other.datasetKey)
           && Objects.equal(this.occurrenceReport, other.occurrenceReport)
           && Objects.equal(this.genericReport, other.genericReport)
           && Objects.equal(this.invalidationReason, other.invalidationReason);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("datasetKey", datasetKey)
      .add("invalidationReason", invalidationReason)
      .add("occurrenceReport", occurrenceReport)
      .add("genericReport", genericReport)
      .toString();
  }
}
