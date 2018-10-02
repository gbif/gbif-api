package org.gbif.api.model.occurrence;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.validation.Valid;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gbif.api.model.occurrence.predicate.Predicate;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

public class PredicateDownloadRequest extends DownloadRequest {


  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.DWCA;
  private Predicate predicate;
  
  public PredicateDownloadRequest()   {}
  
  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  public PredicateDownloadRequest(@JsonProperty("predicate") Predicate predicate, @JsonProperty("creator") @Nullable String creator,
      @JsonProperty("notification_address") @Nullable Collection<String> notificationAddresses,
      @JsonProperty("send_notification") @Nullable boolean sendNotification, @JsonProperty("format") DownloadFormat format) {
    this.creator = creator;
    this.predicate = predicate;
    this.notificationAddresses = notificationAddresses == null ? ImmutableSet.<String>of() : ImmutableSet.copyOf(notificationAddresses);
    this.sendNotification = sendNotification;
    this.format = format == null ? DEFAULT_DOWNLOAD_FORMAT : format;
  }

  /**
   * @return the download filter
   */
  @Nullable
  @Valid
  public Predicate getPredicate() {
    return predicate;
  }

  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
  }

  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PredicateDownloadRequest)) {
      return false;
    }

    PredicateDownloadRequest that = (PredicateDownloadRequest) obj;
    return Objects.equal(this.creator, that.creator) && Objects.equal(this.predicate, that.predicate)
        && Objects.equal(this.notificationAddresses, that.notificationAddresses)
        && Objects.equal(this.sendNotification, that.sendNotification) && Objects.equal(this.format, that.format);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(creator, predicate, notificationAddresses, sendNotification, format);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("creator", creator).add("predicate", predicate)
        .add("notificationAddresses", notificationAddresses).add("emailNotification", sendNotification).add("format", format).toString();
  }
}
