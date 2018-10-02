package org.gbif.api.model.occurrence;

import java.util.Set;
import javax.annotation.Nullable;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

/**
 * Represents a request to download occurrence records.
 * A download request with a null predicate is interpreted as a "download all" request.
 */
public class DownloadRequest {

  private static final String DELIMITER = ",";
  private static final Joiner COMMA_JOINER = Joiner.on(DELIMITER).skipNulls();
  private static final Splitter COMMA_SPLITTER = Splitter.on(DELIMITER).omitEmptyStrings().trimResults();

  protected String creator;

  protected Set<String> notificationAddresses;

  protected boolean sendNotification;

  protected DownloadFormat format;

  /**
   * Default constructor.
   */
  protected DownloadRequest() {
    // Empty constructor required to create instances from the data access layer.
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
  public String toString() {
    return Objects.toStringHelper(this).add("creator", creator)
      .add("notificationAddresses", notificationAddresses).add("emailNotification", sendNotification)
      .add("format", format).toString();
  }

}
