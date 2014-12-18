package org.gbif.api.model.registry;

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.occurrence.Download;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * Represents the information about the usage of one dataset in an occurrence download.
 * Provides information about the number of records that the dataset provided to the download; additionally, provide the
 * references to the download and dataset entities.
 */
public class DatasetOccurrenceDownloadUsage {

  private String downloadKey;
  private UUID datasetKey;
  private String datasetTitle;
  private DOI datasetDOI;
  private String datasetCitation;
  private long numberRecords;
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
  public String toString() {
    return Objects.toStringHelper(this).add("downloadKey", downloadKey).add("datasetKey", datasetKey)
      .add("datasetTitle", datasetTitle).add("datasetDOI", datasetDOI).add("datasetCitation",datasetCitation)
      .add("numberRecords", numberRecords).add("download", download).toString();
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(downloadKey, datasetKey, datasetTitle, datasetDOI, datasetDOI, numberRecords, download);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof DatasetOccurrenceDownloadUsage) {
      DatasetOccurrenceDownloadUsage that = (DatasetOccurrenceDownloadUsage) object;
      return Objects.equal(this.downloadKey, that.downloadKey)
        && Objects.equal(this.datasetKey, that.datasetKey)
        && Objects.equal(this.datasetTitle, that.datasetTitle)
        && Objects.equal(this.datasetDOI, that.datasetDOI)
        && Objects.equal(this.datasetCitation, that.datasetCitation)
        && Objects.equal(this.numberRecords, that.numberRecords)
        && Objects.equal(this.download, that.download);
    }
    return false;
  }


}
