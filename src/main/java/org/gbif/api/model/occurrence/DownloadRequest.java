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
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a request to download occurrence records.
 * A download request with a null predicate is interpreted as a "download all" request.
 */
public class DownloadRequest {

  private static final String DELIMITTER = ",";
  private static final Joiner COMMA_JOINER = Joiner.on(DELIMITTER).skipNulls();
  private static final Splitter COMMA_SPLITTER = Splitter.on(DELIMITTER).omitEmptyStrings().trimResults();

  private String creator;

  private Predicate predicate;

  private Set<String> notificationAddresses;

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
    @JsonProperty("predicate") Predicate predicate, @JsonProperty("creator") String creator,
    @JsonProperty("notification_address") @Nullable Collection<String> notificationAddresses) {
    this.creator = Preconditions.checkNotNull(creator, "Creator cannot be null");
    this.predicate = predicate;
    this.notificationAddresses = notificationAddresses == null ?
      ImmutableSet.<String>of() : ImmutableSet.copyOf(notificationAddresses);
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
      && Objects.equal(this.notificationAddresses, that.notificationAddresses);
  }

  /**
   * @return the user account that initiated the download
   */
  @NotNull
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
    return Objects.hashCode(creator, predicate, notificationAddresses);
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

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("creator", creator).add("predicate", predicate)
      .add("notificationAddresses", notificationAddresses).toString();
  }

}
