/*
 * Copyright 2020-2021 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.api.util.ApiStringUtils;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.gbif.api.util.PreconditionUtils.checkArgument;
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
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
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
    this.datasetKey = Objects.requireNonNull(datasetKey, "datasetKey can't be null");
    // verify one of the 3 is not null
    checkArgument(invalidationReason != null || occurrenceReport != null || genericReport != null,
      "Either a report or invalidationReason cannot be null");
    this.occurrenceReport = occurrenceReport;
    this.genericReport = genericReport;
    this.invalidationReason = invalidationReason;
  }

  public DwcaValidationReport(UUID datasetKey, OccurrenceValidationReport occurrenceReport) {
    this.datasetKey = Objects.requireNonNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = Objects.requireNonNull(occurrenceReport, "occurrenceReport can't be null");
    this.genericReport = null;
    this.invalidationReason = null;
  }

  public DwcaValidationReport(UUID datasetKey, GenericValidationReport genericReport) {
    this.datasetKey = Objects.requireNonNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.genericReport = Objects.requireNonNull(genericReport, "genericReport can't be null");
    this.invalidationReason = null;
  }

  public DwcaValidationReport(UUID datasetKey, String invalidationReason) {
    this.datasetKey = Objects.requireNonNull(datasetKey, "datasetKey can't be null");
    this.occurrenceReport = null;
    this.genericReport = null;
    this.invalidationReason = Objects.requireNonNull(invalidationReason, "invalidationReason can't be null");
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

    return ApiStringUtils.emptyToNull(sb.toString());
  }

  public OccurrenceValidationReport getOccurrenceReport() {
    return occurrenceReport;
  }

  public GenericValidationReport getGenericReport() {
    return genericReport;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DwcaValidationReport that = (DwcaValidationReport) o;
    return Objects.equals(datasetKey, that.datasetKey) &&
      Objects.equals(occurrenceReport, that.occurrenceReport) &&
      Objects.equals(genericReport, that.genericReport) &&
      Objects.equals(invalidationReason, that.invalidationReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, occurrenceReport, genericReport, invalidationReason);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DwcaValidationReport.class.getSimpleName() + "[", "]")
      .add("datasetKey=" + datasetKey)
      .add("occurrenceReport=" + occurrenceReport)
      .add("genericReport=" + genericReport)
      .add("invalidationReason='" + invalidationReason + "'")
      .toString();
  }
}
