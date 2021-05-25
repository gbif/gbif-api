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
package org.gbif.api.model.occurrence;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Monthly dataset download statistics.
 */
public class DownloadStatistics {

  private UUID datasetKey;
  private Integer totalRecords;
  private Integer numberDownloads;
  private LocalDate yearMonth;

  public DownloadStatistics(){

  }

  public DownloadStatistics(UUID datasetKey, Integer totalRecords, Integer numberDownloads, LocalDate yearMonth) {
    this.datasetKey = datasetKey;
    this.totalRecords = totalRecords;
    this.numberDownloads = numberDownloads;
    this.yearMonth = yearMonth;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public Integer getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(Integer totalRecords) {
    this.totalRecords = totalRecords;
  }

  public Integer getNumberDownloads() {
    return numberDownloads;
  }

  public void setNumberDownloads(Integer numberDownloads) {
    this.numberDownloads = numberDownloads;
  }

  /**
   * Ignore in serialization, only year and month is relevant.
   */
  @JsonIgnore
  public LocalDate getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(LocalDate yearMonth) {
    this.yearMonth = yearMonth;
  }

  public int getYear() {
    return yearMonth.getYear();
  }

  public int getMonth() {
    return yearMonth.getMonthValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DownloadStatistics that = (DownloadStatistics) o;
    return datasetKey.equals(that.datasetKey)
           && totalRecords.equals(that.totalRecords)
           && numberDownloads.equals(that.numberDownloads)
           && yearMonth.equals(that.yearMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, totalRecords, numberDownloads, yearMonth);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DownloadStatistics.class.getSimpleName() + "[", "]")
      .add("datasetKey=" + datasetKey)
      .add("totalRecords=" + totalRecords)
      .add("numberDownloads=" + numberDownloads)
      .add("yearMonth=" + yearMonth)
      .toString();
  }
}
