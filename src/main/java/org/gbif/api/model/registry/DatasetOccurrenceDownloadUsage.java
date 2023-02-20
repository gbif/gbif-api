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

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.occurrence.Download;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the information about the usage of one dataset in an occurrence download.
 * Provides information about the number of records that the dataset provided to the download; additionally, provide the
 * references to the download and dataset entities.
 */
@SuppressWarnings("unused")
public class DatasetOccurrenceDownloadUsage implements Serializable {

  @Schema(
    description = "The GBIF key assigned to the download.\n\n" +
      "Note that citations should instead use the download DOI."
  )
  private String downloadKey;

  @Schema(
    description = "The GBIF dataset key of the dataset."
  )
  private UUID datasetKey;

  @Schema(
    description = "The title of the dataset, at the time the download was created."
  )
  private String datasetTitle;

  @Schema(
    description = "The primary Digital Object Identifier (DOI) for the dataset.",
    implementation = String.class,
    pattern = "(10(?:\\.[0-9]+)+)" + "/(.+)"
  )
  private DOI datasetDOI;

  @Schema(
    description = "The citation for the dataset, at the time the download was created."
  )
  private String datasetCitation;

  @Schema(
    description = "The number of records from this dataset included in the download."
  )
  private long numberRecords;

  @Schema(
    description = "Further information about the download."
  )
  private Download download;

  /**
   * Dataset key.
   */
  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  /**
   * @return the download
   */
  public Download getDownload() {
    return download;
  }

  /**
   * Occurrence download key.
   */
  @NotNull
  public String getDownloadKey() {
    return downloadKey;
  }

  /**
   * Number of records that the dataset has provided to the occurrence download.
   */
  @NotNull
  public long getNumberRecords() {
    return numberRecords;
  }

  /**
   * Dataset title at the moment when the download was created.
   */
  public String getDatasetTitle() {
    return datasetTitle;
  }

  /**
   * Dataset DOI at the moment when the download was created.
   */
  public DOI getDatasetDOI() {
    return datasetDOI;
  }

  /**
   * Dataset citation at the moment when the download was created.
   */
  public String getDatasetCitation() {
    return datasetCitation;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public void setDownload(Download download) {
    this.download = download;
  }

  public void setDownloadKey(String downloadKey) {
    this.downloadKey = downloadKey;
  }

  public void setNumberRecords(long numberOfRecords) {
    this.numberRecords = numberOfRecords;
  }

  public void setDatasetTitle(String datasetTitle) {
    this.datasetTitle = datasetTitle;
  }

  public void setDatasetDOI(DOI datasetDOI) {
    this.datasetDOI = datasetDOI;
  }

  public void setDatasetCitation(String datasetCitation) {
    this.datasetCitation = datasetCitation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatasetOccurrenceDownloadUsage that = (DatasetOccurrenceDownloadUsage) o;
    return numberRecords == that.numberRecords
        && Objects.equals(downloadKey, that.downloadKey)
        && Objects.equals(datasetKey, that.datasetKey)
        && Objects.equals(datasetTitle, that.datasetTitle)
        && Objects.equals(datasetDOI, that.datasetDOI)
        && Objects.equals(datasetCitation, that.datasetCitation)
        && Objects.equals(download, that.download);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        downloadKey,
        datasetKey,
        datasetTitle,
        datasetDOI,
        datasetCitation,
        numberRecords,
        download);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DatasetOccurrenceDownloadUsage.class.getSimpleName() + "[", "]")
        .add("downloadKey='" + downloadKey + "'")
        .add("datasetKey=" + datasetKey)
        .add("datasetTitle='" + datasetTitle + "'")
        .add("datasetDOI=" + datasetDOI)
        .add("datasetCitation='" + datasetCitation + "'")
        .add("numberRecords=" + numberRecords)
        .add("download=" + download)
        .toString();
  }
}
