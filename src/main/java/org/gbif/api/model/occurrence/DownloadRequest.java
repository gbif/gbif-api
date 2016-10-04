package org.gbif.api.model.occurrence;

import org.gbif.api.model.occurrence.predicate.Predicate;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a request to download occurrence records.
 * A download request with a null predicate is interpreted as a "download all" request.
 */
public class DownloadRequest {

  private static final String DELIMITER = ",";
  private static final Joiner COMMA_JOINER = Joiner.on(DELIMITER).skipNulls();
  private static final Splitter COMMA_SPLITTER = Splitter.on(DELIMITER).omitEmptyStrings().trimResults();

  private String creator;

  private Predicate predicate;

  private Set<String> notificationAddresses;

  private boolean sendNotification;

  private DownloadFormat format;

  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.DWCA;

  /**
   * Default constructor.
   */
  public DownloadRequest() {
    // Empty constructor required to create instances from the data access layer.
  }

  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  public DownloadRequest(
    @JsonProperty("predicate") Predicate predicate,
    @JsonProperty("creator") @Nullable String creator,
    @JsonProperty("notification_address") @Nullable Collection<String> notificationAddresses,
    @JsonProperty("send_notification") @Nullable boolean sendNotification,
    @JsonProperty("format") DownloadFormat format) {
    this.creator = creator;
    this.predicate = predicate;
    this.notificationAddresses = notificationAddresses == null ?
      ImmutableSet.<String>of() : ImmutableSet.copyOf(notificationAddresses);
    this.sendNotification = sendNotification;
    this.format = format == null ? DEFAULT_DOWNLOAD_FORMAT : format;
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
      && Objects.equal(this.predicate, that.predicate)
      && Objects.equal(this.notificationAddresses, that.notificationAddresses)
      && Objects.equal(this.sendNotification, that.sendNotification)
      && Objects.equal(this.format, that.format);
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

  /**
   * @return the download filter
   */
  @Nullable
  @Valid
  public Predicate getPredicate() {
    return predicate;
  }


  @Override
  public int hashCode() {
    return Objects.hashCode(creator, predicate, notificationAddresses, sendNotification, format);
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

  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
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
  public String toString() {
    return Objects.toStringHelper(this).add("creator", creator).add("predicate", predicate)
      .add("notificationAddresses", notificationAddresses).add("emailNotification", sendNotification)
      .add("format", format).toString();
  }

}
