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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The rules followed here should match the document at:
 * http://dev.gbif.org/wiki/display/INT/Identifier+problems+and+how+to+solve+them.
 */
public class OccurrenceValidationReport {
  // if the percentage of invalid triplets (eg missing catalog number) is greater than this, the archive is invalid
  private static final double INVALID_TRIPLET_THRESHOLD = 0.25;

  // the number of occurrence records checked in the validation
  private final int checkedRecords;

  // the number of triplets that were unique
  private final int uniqueTriplets;

  /**
   * the number of triplets that were invalid (because one or more of institutionCode, collectionCode or catalogNumber
   * were null or empty)
   */
  private final int recordsWithInvalidTriplets;

  // the number of occurrenceIds that were unique (therefore also == the number of records with unique occurrenceId)
  private final int uniqueOccurrenceIds;

  // records that had no occurrenceId
  private final int recordsMissingOccurrenceId;

  // false if we had to stop at our memory-saving limit
  private final boolean allRecordsChecked;

  // if the archive is not valid this will hold a readable reason
  private String invalidationReason;

  // is this archive valid
  private final boolean valid;

  @JsonCreator
  public OccurrenceValidationReport(@JsonProperty("checkedRecords") int checkedRecords,
                                    @JsonProperty("uniqueTriplets") int uniqueTriplets,
                                    @JsonProperty("invalidTriplets") int recordsWithInvalidTriplets,
                                    @JsonProperty("uniqueOccIds") int uniqueOccurrenceIds,
                                    @JsonProperty("missingOccIds") int recordsMissingOccurrenceId,
                                    @JsonProperty("allRecordsChecked") boolean allRecordsChecked) {
    this.checkedRecords = checkedRecords;
    this.uniqueTriplets = uniqueTriplets;
    this.recordsWithInvalidTriplets = recordsWithInvalidTriplets;
    this.uniqueOccurrenceIds = uniqueOccurrenceIds;
    this.recordsMissingOccurrenceId = recordsMissingOccurrenceId;
    this.allRecordsChecked = allRecordsChecked;
    this.valid = validate();
  }

  /**
   * At the moment the only truly fatal conditions are:
   * - whole archive is empty or unreadable
   * - triplets are invalid (% invalid > than our threshold) && occIds are invalid (must be 100% coverage and unique)
   * - any duplicate triplets && occIds are invalid
   */
  private boolean validate() {
    boolean hasRecords = checkedRecords > 0;
    double invalidRatio = hasRecords ? (double) recordsWithInvalidTriplets / checkedRecords : 0;
    boolean invalidTripletsBelowLimit = invalidRatio <= INVALID_TRIPLET_THRESHOLD;
    boolean hasUniqueTriplets = uniqueTriplets == checkedRecords - recordsWithInvalidTriplets;
    boolean hasUniqueOccIds = uniqueOccurrenceIds == checkedRecords - recordsMissingOccurrenceId;
    boolean hasGoodOccIds = uniqueOccurrenceIds == checkedRecords;
    boolean looksValid = invalidTripletsBelowLimit && hasUniqueTriplets || hasGoodOccIds;

    if (!looksValid) {
      List<String> reasons = new ArrayList<>();
      if (!invalidTripletsBelowLimit) {
        reasons.add(Math.round(100 * invalidRatio) + "% invalid triplets is > than threshold of " + Math
          .round(100 * INVALID_TRIPLET_THRESHOLD) + '%');
      }
      if (!hasUniqueTriplets) {
        reasons.add((checkedRecords - recordsWithInvalidTriplets - uniqueTriplets) + " duplicate triplets detected");
      }
      if (!hasGoodOccIds) {
        if (recordsMissingOccurrenceId != 0) {
          reasons.add(recordsMissingOccurrenceId + " records without an occurrence id (should be 0)");
        }
        if (!hasUniqueOccIds) {
          reasons.add(
            (checkedRecords - recordsMissingOccurrenceId - uniqueOccurrenceIds) + " duplicate occurrence ids detected");
        }
      }

      String reason = String.join("; ", reasons);
      invalidationReason = "Archive invalid because [" + reason + ']';
    }

    return looksValid;
  }

  public int getCheckedRecords() {
    return checkedRecords;
  }

  public int getUniqueTriplets() {
    return uniqueTriplets;
  }

  public int getRecordsWithInvalidTriplets() {
    return recordsWithInvalidTriplets;
  }

  public int getUniqueOccurrenceIds() {
    return uniqueOccurrenceIds;
  }

  public int getRecordsMissingOccurrenceId() {
    return recordsMissingOccurrenceId;
  }

  public boolean isAllRecordsChecked() {
    return allRecordsChecked;
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
    OccurrenceValidationReport that = (OccurrenceValidationReport) o;
    return checkedRecords == that.checkedRecords &&
      uniqueTriplets == that.uniqueTriplets &&
      recordsWithInvalidTriplets == that.recordsWithInvalidTriplets &&
      uniqueOccurrenceIds == that.uniqueOccurrenceIds &&
      recordsMissingOccurrenceId == that.recordsMissingOccurrenceId &&
      allRecordsChecked == that.allRecordsChecked &&
      valid == that.valid;
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(checkedRecords, uniqueTriplets, recordsWithInvalidTriplets, uniqueOccurrenceIds,
        recordsMissingOccurrenceId, allRecordsChecked, valid);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OccurrenceValidationReport.class.getSimpleName() + "[",
      "]")
      .add("checkedRecords=" + checkedRecords)
      .add("uniqueTriplets=" + uniqueTriplets)
      .add("recordsWithInvalidTriplets=" + recordsWithInvalidTriplets)
      .add("uniqueOccurrenceIds=" + uniqueOccurrenceIds)
      .add("recordsMissingOccurrenceId=" + recordsMissingOccurrenceId)
      .add("allRecordsChecked=" + allRecordsChecked)
      .add("invalidationReason='" + invalidationReason + "'")
      .add("valid=" + valid)
      .toString();
  }
}
