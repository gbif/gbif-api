/*
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

import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.model.occurrence.predicate.Predicate;
import org.gbif.api.vocabulary.Extension;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An occurrence download request whose filters are based on predicates ( see {@link Predicate}).
 */
@Schema(
  description = "An occurrence download request whose filters are based on predicates."
)
public class PredicateDownloadRequest extends DownloadRequest {

  //Default download format.
  private static final DownloadFormat DEFAULT_DOWNLOAD_FORMAT = DownloadFormat.SIMPLE_CSV;

  @Schema(
    description = "A predicate defining the filters to apply to the download."
  )
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
    @JsonProperty("sendNotification") @Nullable Boolean sendNotification,
    @JsonProperty("format") @Nullable DownloadFormat format,
    @JsonProperty("type") @Nullable DownloadType type,
    @JsonProperty("verbatimExtensions") @Nullable Set<Extension> verbatimExtensions) {
    super(creator, notificationAddresses, sendNotification == null? Boolean.TRUE : sendNotification, format == null ? DEFAULT_DOWNLOAD_FORMAT : format, type == null? DownloadType.OCCURRENCE : type, verbatimExtensions);
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PredicateDownloadRequest that = (PredicateDownloadRequest) o;
    return Objects.equals(predicate, that.predicate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), predicate);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PredicateDownloadRequest.class.getSimpleName() + "[", "]")
      .add("predicate=" + predicate)
      .toString();
  }
}
