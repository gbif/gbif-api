/*
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
package org.gbif.api.model.registry;

import java.io.Serializable;
import java.util.Objects;

import jakarta.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Approximate record counts for a dataset, sourced from the dataset search index.
 * Values are snapshots taken at index time and may lag live occurrence or checklist data.
 */
@Schema(
    description =
        "Approximate record counts for this dataset, sourced from the dataset search index. "
            + "Values are snapshots taken at index time and may lag live occurrence or checklist data. "
            + "Useful to avoid extra calls for occurrence and name-usage totals; not authoritative.")
@NoArgsConstructor
@Getter
@Setter
public class ApproximateCounts implements Serializable {

  @Schema(description = "Approximate number of occurrence records indexed for this dataset.")
  @Nullable
  private Long occurrenceCount;

  @Schema(
      description =
          "Approximate number of name usages for checklist datasets; typically null otherwise.")
  @Nullable
  private Long nameUsageCount;

  public ApproximateCounts(Long occurrenceCount, Long nameUsageCount) {
    this.occurrenceCount = occurrenceCount;
    this.nameUsageCount = nameUsageCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ApproximateCounts)) {
      return false;
    }
    ApproximateCounts that = (ApproximateCounts) o;
    return Objects.equals(occurrenceCount, that.occurrenceCount)
        && Objects.equals(nameUsageCount, that.nameUsageCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(occurrenceCount, nameUsageCount);
  }

  @Override
  public String toString() {
    return "ApproximateCounts{occurrenceCount="
        + occurrenceCount
        + ", nameUsageCount="
        + nameUsageCount
        + '}';
  }
}
