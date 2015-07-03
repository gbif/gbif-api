package org.gbif.api.model.crawler;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
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
    this.duplicateIds = Preconditions.checkNotNull(duplicateIds, "duplicateIds cannot be null");
    this.rowNumbersMissingId = Preconditions.checkNotNull(rowNumbersMissingId, "rowNumbersMissingId cannot be null");
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
  public int hashCode() {
    return Objects.hashCode(checkedRecords, allRecordsChecked, duplicateIds,
                            rowNumbersMissingId, invalidationReason,valid);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final GenericValidationReport other = (GenericValidationReport) obj;
    return Objects.equal(this.checkedRecords, other.checkedRecords)
           && Objects.equal(this.allRecordsChecked, other.allRecordsChecked)
           && Objects.equal(this.duplicateIds, other.duplicateIds)
           && Objects.equal(this.rowNumbersMissingId, other.rowNumbersMissingId)
           && Objects.equal(this.invalidationReason, other.invalidationReason)
           && Objects.equal(this.valid, other.valid);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("checkedRecords", checkedRecords)
      .add("duplicateIds", duplicateIds)
      .add("rowNumbersMissingId", rowNumbersMissingId)
      .add("allRecordsChecked", allRecordsChecked)
      .add("invalidationReason", invalidationReason)
      .add("valid", valid)
      .toString();
  }
}
