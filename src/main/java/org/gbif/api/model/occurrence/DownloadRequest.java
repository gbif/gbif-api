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

import org.gbif.api.jackson.DownloadRequestSerde;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.annotation.Nullable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Represents a request to download occurrence records.
 * This is the base class for specific type of downloads: predicate based downloads and SQL downloads..
 */
@JsonDeserialize(using = DownloadRequestSerde.class)
public abstract class DownloadRequest {

  private static final String DELIMITER = ",";
  private static final Joiner COMMA_JOINER = Joiner.on(DELIMITER).skipNulls();
  private static final Splitter COMMA_SPLITTER = Splitter.on(DELIMITER).omitEmptyStrings().trimResults();

  @JsonProperty("creator")
  private String creator;

  @JsonProperty("notificationAddresses")
  private Set<String> notificationAddresses;

  @JsonProperty("sendNotification")
  private boolean sendNotification;

  @JsonProperty("format")
  private DownloadFormat format;

  /**
   * Default constructor.
   */
  public DownloadRequest() {
    // Empty constructor required to create instances from the data access layer.
  }

  public DownloadRequest(String creator, Collection<String> notificationAddresses, boolean sendNotification, DownloadFormat format) {
    this.creator = creator;
    this.notificationAddresses = notificationAddresses == null ? Collections.emptySet() : ImmutableSet.copyOf(notificationAddresses);
    this.sendNotification = sendNotification;
    this.format = format;
  }

  /**
   * @return the user account that initiated the download
   */
  @Nullable
  public String getCreator() {
    return creator;
  }

  /**
   * @return set of email addresses for notifications
   */
  @Nullable
  public Set<String> getNotificationAddresses() {
    return notificationAddresses;
  }

  /**
   * Returns the notification addresses as single string. The emails are separated by ','.
   */
  @Nullable
  @JsonIgnore
  public String getNotificationAddressesAsString() {
    if (notificationAddresses != null) {
      return COMMA_JOINER.join(notificationAddresses);
    }
    return null;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public void setNotificationAddresses(Set<String> notificationAddresses) {
    this.notificationAddresses = notificationAddresses;
  }

  /**
   * Sets the notificationAddresses using a single String value that is split by ','.
   */
  public void setNotificationAddressesAsString(String notificationAddressesAsString) {
    if (notificationAddressesAsString != null) {
      notificationAddresses = Sets.newHashSet(COMMA_SPLITTER.split(notificationAddressesAsString));
    }
  }

  public boolean getSendNotification() {
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
      format == that.format;
  }

  @Override
  public int hashCode() {
    return Objects.hash(creator, notificationAddresses, sendNotification, format);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DownloadRequest.class.getSimpleName() + "[", "]")
      .add("creator='" + creator + "'")
      .add("notificationAddresses=" + notificationAddresses)
      .add("sendNotification=" + sendNotification)
      .add("format=" + format)
      .toString();
  }
}
