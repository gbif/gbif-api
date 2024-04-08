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

import org.gbif.api.vocabulary.MaintenanceUpdateFrequency;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A description of single changes made to the data.
 */
@SuppressWarnings({"unused", "LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public class MaintenanceChange {

  private String changeScope;
  private MaintenanceUpdateFrequency oldValue;
  private Date changeDate;
  private String comment;

  public String getChangeScope() {
    return changeScope;
  }

  public void setChangeScope(String changeScope) {
    this.changeScope = changeScope;
  }

  public MaintenanceUpdateFrequency getOldValue() {
    return oldValue;
  }

  public void setOldValue(MaintenanceUpdateFrequency oldValue) {
    this.oldValue = oldValue;
  }

  public Date getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(Date changeDate) {
    this.changeDate = changeDate;
  }

  @Nullable
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MaintenanceChange that = (MaintenanceChange) o;
    return Objects.equals(changeScope, that.changeScope)
        && Objects.equals(oldValue, that.oldValue)
        && Objects.equals(changeDate, that.changeDate)
        && Objects.equals(comment, that.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(changeScope, oldValue, changeDate, comment);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MaintenanceChange.class.getSimpleName() + "[", "]")
        .add("changeScope='" + changeScope + "'")
        .add("oldValue='" + oldValue + "'")
        .add("changeDate=" + changeDate)
        .add("comment='" + comment + "'")
        .toString();
  }
}
