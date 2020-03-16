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

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A container class used to capture the information necessary for a generic validation report.
 * <p/>
 * This is written with the intention to capture the information about e.g. the sample core or the taxon core of a
 * DwC-A.
 * <p/>
 * Generic validation includes information about the number of records checked, whether all records where checked
 * (implementations often cannot check everything) and information about the validity of primary keys.
 */
public class GenericValidationReport {
  // the number of records checked in the validation
  private final int checkedRecords;

  // false if we had to stop at an implementation imposed limit (e.g. due to memory restrictions)
  private final boolean allRecordsChecked;

  // a sample of duplicate ids which are expected to be unique (i.e. primary keys)
  private final List<String> duplicateIds;

  // a sample of data file line numbers with records missing a core identifier
  private final List<Integer> rowNumbersMissingId;

  // if the archive is not valid this will hold a readable reason
  private String invalidationReason;

  // is this archive valid
  private final boolean valid;

  @JsonCreator
  public GenericValidationReport(
    @JsonProperty("checkedRecords") int checkedRecords,
    @JsonProperty("allRecordsChecked") boolean allRecordsChecked,
    @JsonProperty("duplicateIds") List<String> duplicateIds,
    @JsonProperty("rowNumbersMissingId") List<Integer> rowNumbersMissingId
  ) {
    this.checkedRecords = checkedRecords;
    this.allRecordsChecked = allRecordsChecked;
    this.duplicateIds = Objects.requireNonNull(duplicateIds, "duplicateIds cannot be null");
    this.rowNumbersMissingId = Objects.requireNonNull(rowNumbersMissingId, "rowNumbersMissingId cannot be null");
    this.valid = validate();
  }

  private boolean validate() {
    if (!duplicateIds.isEmpty()) {
      invalidationReason = "Non unique core ids";
      return false;
    }
    if (!rowNumbersMissingId.isEmpty()) {
      invalidationReason = "Missing core ids";
      return false;
    }
    return true;
  }

  public int getCheckedRecords() {
    return checkedRecords;
  }

  public boolean isAllRecordsChecked() {
    return allRecordsChecked;
  }

  public List<String> getDuplicateIds() {
    return duplicateIds;
  }

  public List<Integer> getRowNumbersMissingId() {
    return rowNumbersMissingId;
  }

  public String getInvalidationReason() {
    return invalidationReason;
  }

  public boolean isValid() {
    return valid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenericValidationReport that = (GenericValidationReport) o;
    return checkedRecords == that.checkedRecords &&
      allRecordsChecked == that.allRecordsChecked &&
      valid == that.valid &&
      Objects.equals(duplicateIds, that.duplicateIds) &&
      Objects.equals(rowNumbersMissingId, that.rowNumbersMissingId) &&
      Objects.equals(invalidationReason, that.invalidationReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(checkedRecords, allRecordsChecked, duplicateIds, rowNumbersMissingId,
      invalidationReason, valid);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GenericValidationReport.class.getSimpleName() + "[", "]")
      .add("checkedRecords=" + checkedRecords)
      .add("allRecordsChecked=" + allRecordsChecked)
      .add("duplicateIds=" + duplicateIds)
      .add("rowNumbersMissingId=" + rowNumbersMissingId)
      .add("invalidationReason='" + invalidationReason + "'")
      .add("valid=" + valid)
      .toString();
  }
}
