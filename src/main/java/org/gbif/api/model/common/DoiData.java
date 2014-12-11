package org.gbif.api.model.common;

import java.net.URI;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data about a DOI with a target URI and a status enumeration.
 */
public class DoiData {
  private final DoiStatus status;
  private final URI target;

  @JsonCreator
  public DoiData(@JsonProperty("status") DoiStatus status, @JsonProperty("target") URI target) {
    this.status = status;
    this.target = target;
  }

  public DoiData(String ezidStatus, URI target) {
    this.status = DoiStatus.fromString(ezidStatus);
    this.target = target;
  }

  public DoiStatus getStatus() {
    return status;
  }

  public URI getTarget() {
    return target;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(status, target);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final DoiData other = (DoiData) obj;
    return Objects.equal(this.status, other.status) && Objects.equal(this.target, other.target);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("status", status)
      .add("target", target)
      .toString();
  }

}
