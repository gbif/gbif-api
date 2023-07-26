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
package org.gbif.api.model.occurrence;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gbif.api.model.common.DOI;
import org.gbif.api.model.registry.PostPersist;
import org.gbif.api.model.registry.PrePersist;
import org.gbif.api.vocabulary.License;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@ToString
@EqualsAndHashCode
public class Download implements Serializable {

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
   * - FILE_ERASED: the download was successful, but the download file has been deleted
   */
  @Schema(
    description = "The status of the download.\n\n" +
      "* `PREPARING`: the download is initiating\n" +
      "* `RUNNING`: the download is being processed\n" +
      "* `SUCCEEDED`: the download has completed and the file is ready to be downloaded\n" +
      "* `CANCELLED`: the download was cancelled by the user\n" +
      "* `KILLED`: the download was killed by the download engine\n" +
      "* `FAILED`: the download failed\n" +
      "* `SUSPENDED`: the download was paused and its execution will be resumed later\n" +
      "* `FILE_ERASED`: the download was successful, but the download file " +
      "[has since been deleted](https://www.gbif.org/faq?question=for-how-long-will-does-gbif-store-downloads)."
  )
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

  @Schema(
    description = "The GBIF key assigned to the download.\n\n" +
      "Note that citations should instead use the download DOI."
  )
  private String key;

  @Schema(
    description = "The primary Digital Object Identifier (DOI) for this download.",
    implementation = String.class,
    pattern = "(10(?:\\.[0-9]+)+)" + "/(.+)"
  )
  private DOI doi;

  @Schema(
    description = "The licence applied to the download.  This is calculated as the most restrictive licence of any " +
      " of the data included in the download, other data may be less restrictive."
  )
  private License license;

  @Schema(
    description = "The filter used to request this download."
  )
  private DownloadRequest request;

  @Schema(
    description = "The time the download request was submitted."
  )
  private Date created;

  @Schema(
    description = "The time the download was last modified."
  )
  private Date modified;

  @Schema(
    description = "A time after which the download [may be deleted](https://www.gbif.org/faq?question=for-how-long-will-does-gbif-store-downloads)."
  )
  private Date eraseAfter;

  @Schema(
    description = "The most recent time when the creator of the download was notified the download is due to be deleted."
  )
  private Date erasureNotification;

  // Documented in the enumeration above
  private Status status;

  @Schema(
    description = "A link to download the data, if the status is `SUCCEEDED`."
  )
  private String downloadLink;

  @Schema(
    description = "The size, in bytes, of the download data."
  )
  private long size;

  @Schema(
    description = "The total number of occurrence records included in the download."
  )
  private long totalRecords;

  @Schema(
    description = "The total number of datasets from which occurrence records were drawn."
  )
  @JsonInclude(Include.NON_NULL)
  private Long numberDatasets;

  @Schema(
    description = "The total number of organizations from which occurrence records were drawn."
  )
  @JsonInclude(Include.NON_NULL)
  private Long numberOrganizations;

  @Schema(
    description = "The total number of publishing countries from which occurrence records were drawn."
  )
  @JsonInclude(Include.NON_NULL)
  private Long numberPublishingCountries;

  @Hidden
  private String source;

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
   * @return timestamp when a notification of an impending file erasure was sent
   */
  @Nullable
  public Date getErasureNotification() {
    return erasureNotification;
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
  @Nullable
  public Long getNumberDatasets() {
    return numberDatasets;
  }

  /**
   * Source of the download to determine how the download was originated.
   */
  public String getSource() {
    return source;
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

  public void setErasureNotification(Date erasureNotification) {
    this.erasureNotification = erasureNotification;
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

  public void setNumberDatasets(Long numberDatasets) {
    this.numberDatasets = numberDatasets;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Nullable
  public Long getNumberOrganizations() {
    return numberOrganizations;
  }

  public void setNumberOrganizations(Long numberOrganizations) {
    this.numberOrganizations = numberOrganizations;
  }

  @Nullable
  public Long getNumberPublishingCountries() {
    return numberPublishingCountries;
  }

  public void setNumberPublishingCountries(Long numberPublishingCountries) {
    this.numberPublishingCountries = numberPublishingCountries;
  }
}
