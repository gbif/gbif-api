package org.gbif.api.model.occurrence;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.validation.Valid;

import com.google.common.base.MoreObjects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gbif.api.model.occurrence.predicate.Predicate;
import com.google.common.base.Objects;

public class PredicateDownloadRequest extends DownloadRequest {


  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.DWCA;
  private Predicate predicate;

  public PredicateDownloadRequest() {

  }
  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  public PredicateDownloadRequest(@JsonProperty("predicate") Predicate predicate, @JsonProperty("creator") @Nullable String creator,
      @JsonProperty("notification_address") @Nullable Collection<String> notificationAddresses,
      @JsonProperty("send_notification") @Nullable boolean sendNotification, @JsonProperty("format") DownloadFormat format) {
    super(creator, notificationAddresses, sendNotification, format == null ? DEFAULT_DOWNLOAD_FORMAT : format);
    this.predicate = predicate;
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
    return super.equals(that) && Objects.equal(this.predicate, that.predicate);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), predicate);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).addValue(super.toString()).add("predicate", predicate).toString();
  }
}
