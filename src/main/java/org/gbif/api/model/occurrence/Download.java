package org.gbif.api.model.occurrence;

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.License;

import java.util.Date;
import java.util.EnumSet;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;


public class Download {

  /**
   * Reflects the possibles statuses of a download during its execution.
   * The download statuses are:
   * - PREPARING: the download is initiating
   * - RUNNING: the download is being processed
   * - SUCCEEDED: the file is ready to be downloaded
   * - CANCELLED: the download was cancelled by the user
   * - KILLED: the download was killed by the workflow engine
   * - FAILED: the download failed
   * - SUSPENDED: the download was paused and its executing will be resumed later
   */
  public enum Status {
    PREPARING, RUNNING, SUCCEEDED, CANCELLED, KILLED, FAILED, SUSPENDED;

    /**
     * Statuses that represent a download that that hasn't finished.
     */
    public static final EnumSet<Status> EXECUTING_STATUSES = EnumSet.of(PREPARING,RUNNING,SUSPENDED);

    /**
     * Statuses that represent a download that that has finished.
     */
    public static final EnumSet<Status> FINISH_STATUSES = EnumSet.of(SUCCEEDED, CANCELLED, KILLED, FAILED);
  }

  private String key;

  private DOI doi;

  private License license;

  private DownloadRequest request;

  private Date created;

  private Date modified;

  private Status status;

  private String downloadLink;

  private long size;

  private long totalRecords;

  private long numberDatasets;

  /**
   * @return timestamp when the download was created
   */
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Nullable
  public Date getCreated() {
    return created;
  }

  /**
   * @return the downloadLink
   */
  @Nullable
  public String getDownloadLink() {
    return downloadLink;
  }


  /**
   * @return unique key that can be used in the REST service to retrieve its status
   */
  @NotNull
  public String getKey() {
    return key;
  }


  /**
   * @return timestamp when the download result was last modified
   */
  @Null(groups = {PrePersist.class})
  @NotNull(groups = {PostPersist.class})
  @Nullable
  public Date getModified() {
    return modified;
  }

  /**
   * Request that originated the download.
   */
  @NotNull
  public DownloadRequest getRequest() {
    return request;
  }


  /**
   * @return the current download status
   */
  @NotNull
  public Status getStatus() {
    return status;
  }

  /**
   * @return true if the download is completed and a result is available
   */
  @JsonIgnore
  public boolean isAvailable() {
    return status == Status.SUCCEEDED;
  }

  /**
   * Size in bytes of the produced file.
   */
  @Nullable
  public long getSize() {
    return size;
  }

  /**
   * Number of occurrence records in the download file.
   */
  @Nullable
  public long getTotalRecords() {
    return totalRecords;
  }

  /**
   * Number of occurrence records in the download file.
   */
  @Nullable
  public long getNumberDatasets() {
    return numberDatasets;
  }

  /**
   * Occurrence download Digital Object Identifier.
   */
  @Nullable
  public DOI getDoi() {
    return doi;
  }

  /**
   * Get the license assigned to this occurrence download.
   * @return
   */
  public License getLicense() {
    return license;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setDoi(DOI doi) {
    this.doi = doi;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public void setRequest(DownloadRequest request) {
    this.request = request;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public void setTotalRecords(long totalRecords) {
    this.totalRecords = totalRecords;
  }

  public void setNumberDatasets(long numberDatasets) {
    this.numberDatasets = numberDatasets;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", key).add("doi", doi)
      .add("status", status).add("downloadLink", downloadLink)
      .add("request", request).add("created", created)
      .add("license", license)
      .add("modified", modified)
      .add("size", size)
      .add("totalRecords", totalRecords)
      .add("numberDatasets", numberDatasets).toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Download)) {
      return false;
    }

    Download that = (Download) obj;
    return Objects.equal(this.request, that.request)
      && Objects.equal(this.key, that.key)
      && Objects.equal(this.doi, that.doi)
      && Objects.equal(this.license, that.license)
      && Objects.equal(this.created, that.created)
      && Objects.equal(this.modified, that.modified)
      && Objects.equal(this.status, that.status)
      && Objects.equal(this.downloadLink, that.downloadLink)
      && Objects.equal(this.size, that.size)
      && Objects.equal(this.totalRecords, that.totalRecords)
      && Objects.equal(this.numberDatasets, that.numberDatasets);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, doi, license, request, created, modified, status, downloadLink, size, totalRecords,
            numberDatasets);
  }
}
