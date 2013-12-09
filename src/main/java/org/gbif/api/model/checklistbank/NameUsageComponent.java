/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import java.util.UUID;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;


/**
 * Base Model Object represents a name usage component.
 */
public class NameUsageComponent {

  private Integer key;
  private Integer usageKey;
  private UUID datasetKey;

  /**
   * The checklist key the usage belongs to.
   *
   * @return checklist key
   */
  public UUID getDatasetKey() {
    return datasetKey;
  }

  /**
   * @param datasetKey the key of the usage checklist
   */
  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  /**
   * @return the key
   */
  @NotNull
  public Integer getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * @return The key of the usage this component belongs to
   */
  @NotNull
  public Integer getUsageKey() {
    return usageKey;
  }

  /**
   * @param usageKey the usageKey to set
   */
  public void setUsageKey(Integer usageKey) {
    this.usageKey = usageKey;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof NameUsageComponent)) {
      return false;
    }

    NameUsageComponent that = (NameUsageComponent) obj;
    return Objects.equal(this.key, that.key)
           && Objects.equal(this.usageKey, that.usageKey)
           && Objects.equal(this.datasetKey, that.datasetKey);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, usageKey, datasetKey);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("usageKey", usageKey)
      .add("datasetKey", datasetKey)
      .toString();
  }

}
