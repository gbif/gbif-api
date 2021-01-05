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
package org.gbif.api.model.registry;

import java.io.Serializable;
import java.util.Objects;

public class Grid implements Serializable {

  private Integer key;
  private Integer totalCount;
  private Double minDist;
  private Integer minDistCount;
  private Double percent;
  private Double maxPercent;

  public Grid() {}

  public Integer getKey() {
    return key;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public Double getMinDist() {
    return minDist;
  }

  public Integer getMinDistCount() {
    return minDistCount;
  }

  public Double getPercent() {
    return percent;
  }

  public Double getMaxPercent() {
    return maxPercent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grid grid = (Grid) o;
    return Objects.equals(key, grid.key)
      && Objects.equals(totalCount, grid.totalCount)
      && Objects.equals(minDist, grid.minDist)
      && Objects.equals(minDistCount, grid.minDistCount)
      && Objects.equals(percent, grid.percent)
      && Objects.equals(maxPercent, grid.maxPercent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, totalCount, minDist, minDistCount, percent, maxPercent);
  }

  @Override
  public String toString() {
    return "Grid{" +
      "key=" + key +
      ", totalCount=" + totalCount +
      ", minDist=" + minDist +
      ", minDistCount=" + minDistCount +
      ", percent=" + percent +
      ", maxPercent=" + maxPercent +
      '}';
  }
}
