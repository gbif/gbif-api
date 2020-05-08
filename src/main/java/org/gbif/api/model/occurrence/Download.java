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

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.License;

import java.util.Date;
import java.util.EnumSet;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
   * - SUSPENDED: the download was paused and its execution will be resumed later
   * - FILE_DELETED: the download was successful, but the download file has been deleted
   */
  public enum Status {
    PREPARING,
    RUNNING,
    SUCCEEDED,
    CANCELLED,
    KILLED,
    FAILED,
    SUSPENDED,
    FILE_ERASED;

    /**
     * Statuses that represent a download that that hasn't finished.
     */
    public static final EnumSet<Status> EXECUTING_STATUSES = EnumSet.of(PREPARING, RUNNING, SUSPENDED);

    /**
     * Statuses that represent a download that that has finished.
     */
    public static final EnumSet<Status> FINISH_STATUSES = EnumSet.of(SUCCEEDED, CANCELLED, KILLED, FAILED, FILE_ERASED);
  }

  private String key;

  private DOI doi;

  private License license;

  private DownloadRequest request;

  private Date created;

  private Date modified;

  private Date eraseAfter;

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
   * @return timestamp after which the download file may be erased
   */
  @Nullable
  public Date getEraseAfter() {
    return eraseAfter;
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
  public long getSize() {
    return size;
  }

  /**
   * Number of occurrence records in the download file.
   */
  public long getTotalRecords() {
    return totalRecords;
  }

  /**
   * Number of datasets in the download file.
   */
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

  public void setEraseAfter(Date eraseAfter) {
    this.eraseAfter = eraseAfter;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Download download = (Download) o;
    return size == download.size &&
      totalRecords == download.totalRecords &&
      numberDatasets == download.numberDatasets &&
      Objects.equals(key, download.key) &&
      Objects.equals(doi, download.doi) &&
      license == download.license &&
      Objects.equals(request, download.request) &&
      Objects.equals(created, download.created) &&
      Objects.equals(modified, download.modified) &&
      Objects.equals(eraseAfter, download.eraseAfter) &&
      status == download.status &&
      Objects.equals(downloadLink, download.downloadLink);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, doi, license, request, created, modified, eraseAfter, status, downloadLink, size,
        totalRecords, numberDatasets);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Download.class.getSimpleName() + "[", "]")
      .add("key='" + key + "'")
      .add("doi=" + doi)
      .add("license=" + license)
      .add("request=" + request)
      .add("created=" + created)
      .add("modified=" + modified)
      .add("eraseAfter=" + eraseAfter)
      .add("status=" + status)
      .add("downloadLink='" + downloadLink + "'")
      .add("size=" + size)
      .add("totalRecords=" + totalRecords)
      .add("numberDatasets=" + numberDatasets)
      .toString();
  }
}
