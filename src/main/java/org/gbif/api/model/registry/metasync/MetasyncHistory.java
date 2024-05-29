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
package org.gbif.api.model.registry.metasync;

import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Metadata synchronization historical information.
 */
public class MetasyncHistory {

  private UUID installationKey;
  private Date syncDate;
  private MetasyncResult result;
  private String details;

  /**
   * Key of the synchronized installation.
   *
   * @return the installation key that was synchronized
   */
  @NotNull
  public UUID getInstallationKey() {
    return installationKey;
  }

  public void setInstallationKey(UUID installationKey) {
    this.installationKey = installationKey;
  }

  /**
   * Date when the metasync operation was executed.
   * <br>
   * NB: null before the record is persisted, not-null afterwards.
   *
   * @return timestamp when the synchronization was executed
   */
  @Nullable
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  public Date getSyncDate() {
    return syncDate;
  }

  public void setSyncDate(Date syncDate) {
    this.syncDate = syncDate;
  }

  /**
   * Result of the metasync operation.
   *
   * @return the result of the metasync operation
   */
  @Nullable
  public MetasyncResult getResult() {
    return result;
  }

  public void setResult(MetasyncResult result) {
    this.result = result;
  }

  /**
   * Details about the metasync operation.
   *
   * @return the metasync details
   */
  @Nullable
  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetasyncHistory that = (MetasyncHistory) o;
    return Objects.equals(installationKey, that.installationKey) &&
      Objects.equals(syncDate, that.syncDate) &&
      result == that.result &&
      Objects.equals(details, that.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(installationKey, syncDate, result, details);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MetasyncHistory.class.getSimpleName() + "[", "]")
      .add("installationKey=" + installationKey)
      .add("syncDate=" + syncDate)
      .add("result=" + result)
      .add("details='" + details + "'")
      .toString();
  }
}
