package org.gbif.api.model.crawler;

import java.util.List;
import java.util.UUID;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A report of the state of unique identifiers (triplets and occurrenceId) in a DwC-A.
 */
@Immutable
@ThreadSafe
public class DwcaValidationReport {

  // if the percentage of invalid triplets (eg missing catalog number) is greater than this, the archive is invalid
  private static final double INVALID_TRIPLET_THRESHOLD = 0.25;

  private final UUID datasetKey;

  // the number of records checked in the validation
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

  @JsonCreator
  public DwcaValidationReport(@JsonProperty("datasetKey") UUID datasetKey,
    @JsonProperty("checkedRecords") int checkedRecords, @JsonProperty("uniqueTriplets") int uniqueTriplets,
    @JsonProperty("invalidTriplets") int recordsWithInvalidTriplets,
    @JsonProperty("uniqueOccIds") int uniqueOccurrenceIds,
    @JsonProperty("missingOccIds") int recordsMissingOccurrenceId,
    @JsonProperty("allRecordsChecked") boolean allRecordsChecked) {
    this.datasetKey = checkNotNull(datasetKey, "datasetKey can't be null");
    this.checkedRecords = checkedRecords;
    this.uniqueTriplets = uniqueTriplets;
    this.recordsWithInvalidTriplets = recordsWithInvalidTriplets;
    this.uniqueOccurrenceIds = uniqueOccurrenceIds;
    this.recordsMissingOccurrenceId = recordsMissingOccurrenceId;
    this.allRecordsChecked = allRecordsChecked;
  }

  /**
   * At the moment the only truly fatal conditions are:
   * - triplets are invalid (% invalid > than our threshold)
   * - whole archive is empty or unreadable
   * - there are duplicate triplets
   *
   * @return false if the archive is unreadable, has duplicate triplets, or more than the threshold of triplets are
   *         invalid, true otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    boolean hasRecords = checkedRecords > 0;
    double invalidRatio = hasRecords ? (double) recordsWithInvalidTriplets / checkedRecords : 0;
    boolean invalidTripletsBelowLimit = invalidRatio <= INVALID_TRIPLET_THRESHOLD;
    boolean hasUniqueTriplets = uniqueTriplets == checkedRecords - recordsWithInvalidTriplets;
    boolean valid = hasRecords && invalidTripletsBelowLimit && hasUniqueTriplets;

    if (!valid) {
      List<String> reasons = Lists.newArrayList();
      if (!hasRecords) {
        reasons.add("No readable records");
      }
      if (!invalidTripletsBelowLimit) {
        reasons.add(Math.round(100 * invalidRatio) + "% invalid triplets is > than threshold of "
                    + Math.round(100 * INVALID_TRIPLET_THRESHOLD) + '%');
      }
      if (!hasUniqueTriplets) {
        reasons.add((checkedRecords - recordsWithInvalidTriplets - uniqueTriplets) + " duplicate triplets detected");
      }
      String reason = Joiner.on("; ").join(reasons);
      invalidationReason = "Archive invalid because [" + reason + "]";
    }

    return valid;
  }

  public UUID getDatasetKey() {
    return datasetKey;
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

  @Override
  public int hashCode() {
    return Objects.hashCode(datasetKey, checkedRecords, uniqueTriplets, recordsWithInvalidTriplets, uniqueOccurrenceIds,
      recordsMissingOccurrenceId, allRecordsChecked);
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
    return Objects.equal(this.datasetKey, other.datasetKey) && Objects.equal(this.checkedRecords, other.checkedRecords)
           && Objects.equal(this.uniqueTriplets, other.uniqueTriplets) && Objects
      .equal(this.recordsWithInvalidTriplets, other.recordsWithInvalidTriplets) && Objects
             .equal(this.uniqueOccurrenceIds, other.uniqueOccurrenceIds) && Objects
             .equal(this.recordsMissingOccurrenceId, other.recordsMissingOccurrenceId) && Objects
             .equal(this.allRecordsChecked, other.allRecordsChecked);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("datasetKey", datasetKey).add("checkedRecords", checkedRecords)
      .add("uniqueTriplets", uniqueTriplets).add("recordsWithInvalidTriplets", recordsWithInvalidTriplets)
      .add("uniqueOccurrenceIds", uniqueOccurrenceIds).add("recordsMissingOccurrenceId", recordsMissingOccurrenceId)
      .add("allRecordsChecked", allRecordsChecked).add("invalidationReason", invalidationReason).toString();
  }
}
