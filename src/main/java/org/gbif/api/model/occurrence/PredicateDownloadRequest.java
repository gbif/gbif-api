package org.gbif.api.model.occurrence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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
  public PredicateDownloadRequest(
    @JsonProperty("predicate") Predicate predicate,
    @JsonProperty("creator") @Nullable String creator,
    @JsonProperty("notificationAddresses") @Nullable Collection<String> notificationAddresses,
    @JsonProperty("sendNotification") boolean sendNotification,
    @JsonProperty("format") DownloadFormat format) {
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
