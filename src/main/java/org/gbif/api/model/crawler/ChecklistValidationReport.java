package org.gbif.api.model.crawler;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class ChecklistValidationReport {
  // the number of records checked in the validation
  private final int checkedRecords;

  // false if we had to stop at our memory-saving limit
  private final boolean allRecordsChecked;

  // a sample of non unique core/taxon identifier
  private final List<String> duplicateIds;

  // a sample of data file line numbers with records missing a core/taxon identifier
  private final List<Integer> missingIds;

  // if the archive is not valid this will hold a readable reason
  private String invalidationReason;

  // is this archive valid
  private final boolean valid;

  public ChecklistValidationReport(int checkedRecords, boolean allRecordsChecked, List<String> duplicateIds,
    List<Integer> missingIds) {
    this.checkedRecords = checkedRecords;
    this.allRecordsChecked = allRecordsChecked;
    this.duplicateIds = Preconditions.checkNotNull(duplicateIds, "duplicateIds cannot be null");
    this.missingIds = Preconditions.checkNotNull(missingIds, "missingIds cannot be null");
    this.valid = validate();
  }

  private boolean validate() {
    if (!duplicateIds.isEmpty()) {
      invalidationReason = "Non unique taxon ids";
      return false;
    }
    if (!missingIds.isEmpty()) {
      invalidationReason = "Missing taxon ids";
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

  public List<Integer> getMissingIds() {
    return missingIds;
  }

  public String getInvalidationReason() {
    return invalidationReason;
  }

  public boolean isValid() {
    return valid;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(checkedRecords, allRecordsChecked, duplicateIds, missingIds, invalidationReason,valid);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final ChecklistValidationReport other = (ChecklistValidationReport) obj;
    return Objects.equal(this.checkedRecords, other.checkedRecords)
           && Objects.equal(this.allRecordsChecked, other.allRecordsChecked)
           && Objects.equal(this.duplicateIds, other.duplicateIds)
           && Objects.equal(this.missingIds, other.missingIds)
           && Objects.equal(this.invalidationReason, other.invalidationReason)
           && Objects.equal(this.valid, other.valid);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("checkedRecords", checkedRecords)
      .add("duplicateIds", duplicateIds)
      .add("missingIds", missingIds)
      .add("allRecordsChecked", allRecordsChecked)
      .add("invalidationReason", invalidationReason)
      .add("valid", valid)
      .toString();
  }
}
