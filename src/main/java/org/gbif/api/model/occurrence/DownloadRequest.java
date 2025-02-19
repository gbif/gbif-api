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

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.jackson.DownloadRequestSerde;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Represents a request to download occurrence records.
 * This is the base class for specific type of downloads: predicate based downloads and SQL downloads.
 */
@Schema(
  description = "An occurrence download request.",
  oneOf = {PredicateDownloadRequest.class, SqlDownloadRequest.class}
)
@SuppressWarnings("unused")
@JsonDeserialize(using = DownloadRequestSerde.class)
public abstract class DownloadRequest implements Serializable {

  private static final String DELIMITER = ",";

  @Schema(
    description = "The GBIF username of the initiator of the download request."
  )
  @JsonProperty("creator")
  private String creator;

  @ArraySchema(
    schema = @Schema(
      description = "An email addresses to notify when the download finishes."
    )
  )
  @JsonProperty("notificationAddresses")
  private Set<String> notificationAddresses;

  @Schema(
    description = "Whether to send a notification email when the download finishes."
  )
  @JsonProperty("sendNotification")
  private Boolean sendNotification;

  @Schema(
    description = "The data format of the download."
  )
  @JsonProperty("format")
  private DownloadFormat format;

  @Hidden
  @JsonProperty("type")
  private DownloadType type;

  @Schema(
    description = "A user-specified description of the download, such as the intended purpose or a tag " +
      "for later reference."
  )
  @JsonProperty("description")
  private String description;

  @JsonProperty("machineDescription")
  private JsonNode machineDescription;

  /**
   * Default constructor.
   */
  public DownloadRequest() {
    // Empty constructor required to create instances from the data access layer.
  }

  public DownloadRequest(String creator, Collection<String> notificationAddresses,
                         Boolean sendNotification, DownloadFormat format,
                         DownloadType downloadType, String description,
                         JsonNode machineDescription
  ) {
    this.creator = creator;
    this.notificationAddresses = notificationAddresses == null ? Collections.emptySet() :
      Collections.unmodifiableSet(new HashSet<>(notificationAddresses));
    this.sendNotification = sendNotification;
    this.format = format;
    this.type = downloadType;
    this.description = description;
    this.machineDescription = machineDescription;
  }

  /**
   * @return the user account that initiated the download
   */
  @Nullable
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  /**
   * @return set of email addresses for notifications
   */
  @Nullable
  public Set<String> getNotificationAddresses() {
    return notificationAddresses;
  }

  public void setNotificationAddresses(Set<String> notificationAddresses) {
    this.notificationAddresses = notificationAddresses;
  }

  /**
   * Returns the notification addresses as single string. The emails are separated by ','.
   */
  @Nullable
  @JsonIgnore
  public String getNotificationAddressesAsString() {
    if (notificationAddresses != null) {
      return notificationAddresses.stream()
        .filter(Objects::nonNull)
        .map(String::trim)
        .collect(Collectors.joining(DELIMITER));
    }
    return null;
  }

  /**
   * Sets the notificationAddresses using a single String value that is split by ','.
   */
  public void setNotificationAddressesAsString(String notificationAddressesAsString) {
    if (notificationAddressesAsString != null) {
      notificationAddresses = Stream.of(notificationAddressesAsString.split(DELIMITER))
        .filter(StringUtils::isNotEmpty)
        .map(String::trim)
        .collect(Collectors.toSet());
    }
  }

  @Nullable
  public Boolean getSendNotification() {
    return sendNotification;
  }

  /**
   * This parameter determines if the requested download must be notified to the created once it's ready.
   */
  public void setSendNotification(boolean sendNotification) {
    this.sendNotification = sendNotification;
  }

  public DownloadFormat getFormat() {
    return format;
  }

  /**
   * This parameter determines the output format of the requested download.
   */
  public void setFormat(DownloadFormat format) {
    this.format = format;
  }

  public DownloadType getType() {
    return type;
  }

  /**
   * Download type: Occurrence or Event.
   */
  public void setType(DownloadType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public JsonNode getMachineDescription() {
    return machineDescription;
  }

  public void setMachineDescription(JsonNode machineDescription) {
    this.machineDescription = machineDescription;
  }

  @JsonIgnore
  public String getFileExtension() {
    return format.getExtension();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DownloadRequest that = (DownloadRequest) o;
    return sendNotification == that.sendNotification &&
      Objects.equals(creator, that.creator) &&
      Objects.equals(notificationAddresses, that.notificationAddresses) &&
      format == that.format &&
      type == that.type &&
      Objects.equals(description, that.description);// &&
//      Objects.equals(machineDescription, that.machineDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creator, notificationAddresses, sendNotification, format, type, description, machineDescription);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DownloadRequest.class.getSimpleName() + "[", "]")
      .add("creator='" + creator + "'")
      .add("notificationAddresses=" + notificationAddresses)
      .add("sendNotification=" + sendNotification)
      .add("format=" + format)
      .add("type=" + type)
      .add("description=" + description)
      .add("machineDescription=" + machineDescription)
      .toString();
  }
}
