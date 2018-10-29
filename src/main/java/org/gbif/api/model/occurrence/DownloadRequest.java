package org.gbif.api.model.occurrence;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nullable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Represents a request to download occurrence records.
 * A download request with a null predicate is interpreted as a "download all" request.
 */
@JsonTypeInfo(
  use=JsonTypeInfo.Id.NAME,
  include= JsonTypeInfo.As.EXTERNAL_PROPERTY,
  property = "format",
  defaultImpl = PredicateDownloadRequest.class)
@JsonSubTypes({
  @JsonSubTypes.Type(value = SqlDownloadRequest.class, name = "SQL")
})
public abstract class DownloadRequest {

  private static final String DELIMITER = ",";
  private static final Joiner COMMA_JOINER = Joiner.on(DELIMITER).skipNulls();
  private static final Splitter COMMA_SPLITTER = Splitter.on(DELIMITER).omitEmptyStrings().trimResults();

  private String creator;

  private Set<String> notificationAddresses;

  private boolean sendNotification;

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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DownloadRequest)) {
      return false;
    }

    DownloadRequest that = (DownloadRequest) obj;
    return Objects.equal(this.creator, that.creator)
      && Objects.equal(this.notificationAddresses, that.notificationAddresses)
      && Objects.equal(this.sendNotification, that.sendNotification)
      && Objects.equal(this.format, that.format);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(creator, notificationAddresses, sendNotification, format);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("creator", creator)
      .add("notificationAddresses", notificationAddresses)
      .add("emailNotification", sendNotification)
      .add("format", format).toString();
  }

}
