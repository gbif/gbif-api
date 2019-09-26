package org.gbif.api.model.occurrence;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gbif.api.model.occurrence.predicate.Predicate;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.Collection;

/**
 * An occurrence download request whose filters are based on predicates ( see {@link Predicate}).
 */
public class PredicateDownloadRequest extends DownloadRequest {


  //Default download format.
  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.SIMPLE_CSV;

  private Predicate predicate;

  public PredicateDownloadRequest() {

  }

  /**
   * Full constructor. Used to create instances using JSON serialization.
   */
  @JsonCreator
  @com.fasterxml.jackson.annotation.JsonCreator
  public PredicateDownloadRequest(
    @com.fasterxml.jackson.annotation.JsonProperty("predicate") @JsonProperty("predicate") Predicate predicate,
    @com.fasterxml.jackson.annotation.JsonProperty("creator") @JsonProperty("creator") @Nullable String creator,
    @com.fasterxml.jackson.annotation.JsonProperty("notificationAddresses") @JsonProperty("notificationAddresses") @Nullable Collection<String> notificationAddresses,
    @com.fasterxml.jackson.annotation.JsonProperty("sendNotification") @JsonProperty("sendNotification") boolean sendNotification,
    @com.fasterxml.jackson.annotation.JsonProperty("format") @JsonProperty("format") DownloadFormat format) {
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
