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

import lombok.EqualsAndHashCode;

import lombok.ToString;

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
@EqualsAndHashCode
@ToString
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

  @Schema(
    description = "The publishing country code of the publishing organization of the dataset."
  )
  private String publishingCountryCode;

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

  public String getPublishingCountryCode() {
    return publishingCountryCode;
  }

  public void setPublishingCountryCode(String publishingCountryCode) {
    this.publishingCountryCode = publishingCountryCode;
  }
}
