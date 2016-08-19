package org.gbif.api.model.crawler;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

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
      List<String> reasons = Lists.newArrayList();
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
      String reason = Joiner.on("; ").join(reasons);
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
  public int hashCode() {
    return Objects.hashCode(checkedRecords, uniqueTriplets, recordsWithInvalidTriplets, uniqueOccurrenceIds,
      recordsMissingOccurrenceId, allRecordsChecked, valid);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final OccurrenceValidationReport other = (OccurrenceValidationReport) obj;
    return Objects.equal(this.checkedRecords, other.checkedRecords)
           && Objects.equal(this.uniqueTriplets, other.uniqueTriplets)
           && Objects.equal(this.recordsWithInvalidTriplets, other.recordsWithInvalidTriplets)
           && Objects.equal(this.uniqueOccurrenceIds, other.uniqueOccurrenceIds)
           && Objects.equal(this.recordsMissingOccurrenceId, other.recordsMissingOccurrenceId)
           && Objects.equal(this.allRecordsChecked, other.allRecordsChecked)
           && Objects.equal(this.valid, other.valid);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("checkedRecords", checkedRecords)
      .add("uniqueTriplets", uniqueTriplets).add("recordsWithInvalidTriplets", recordsWithInvalidTriplets)
      .add("uniqueOccurrenceIds", uniqueOccurrenceIds).add("recordsMissingOccurrenceId", recordsMissingOccurrenceId)
      .add("allRecordsChecked", allRecordsChecked).add("invalidationReason", invalidationReason).add("valid", valid)
      .toString();
  }
}
