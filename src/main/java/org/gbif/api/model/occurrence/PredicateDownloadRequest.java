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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import javax.annotation.Nullable;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.gbif.api.model.predicate.ConjunctionPredicate;
import org.gbif.api.model.predicate.DisjunctionPredicate;
import org.gbif.api.model.predicate.EqualsPredicate;
import org.gbif.api.model.predicate.GeoDistancePredicate;
import org.gbif.api.model.predicate.GreaterThanOrEqualsPredicate;
import org.gbif.api.model.predicate.GreaterThanPredicate;
import org.gbif.api.model.predicate.InPredicate;
import org.gbif.api.model.predicate.IsNotNullPredicate;
import org.gbif.api.model.predicate.IsNullPredicate;
import org.gbif.api.model.predicate.LessThanOrEqualsPredicate;
import org.gbif.api.model.predicate.LessThanPredicate;
import org.gbif.api.model.predicate.LikePredicate;
import org.gbif.api.model.predicate.NotPredicate;
import org.gbif.api.model.predicate.Predicate;
import org.gbif.api.model.predicate.WithinPredicate;
import org.gbif.api.vocabulary.Extension;

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
    description = "A predicate defining the filters to apply to the download.",
    externalDocs = @ExternalDocumentation(url = "https://techdocs.gbif.org/en/data-use/api-downloads#predicates"),
    oneOf = {
      ConjunctionPredicate.class,
      DisjunctionPredicate.class,
      EqualsPredicate.class,
      GeoDistancePredicate.class,
      GreaterThanOrEqualsPredicate.class,
      GreaterThanPredicate.class,
      InPredicate.class,
      IsNotNullPredicate.class,
      IsNullPredicate.class,
      LessThanOrEqualsPredicate.class,
      LessThanPredicate.class,
      LikePredicate.class,
      NotPredicate.class,
      WithinPredicate.class
    }
  )
  private Predicate predicate;

  @Getter
  @Setter
  @ArraySchema(
    schema = @Schema(
      description = "A verbatim (unprocessed) extension to include on a Darwin Core Archive download."
    )
  )
  @JsonProperty("verbatimExtensions")
  private Set<Extension> verbatimExtensions;

  @Getter
  @Setter
  @ArraySchema(
    schema = @Schema(
      description = "An interpreted extension to include on a Darwin Core Archive download."
    )
  )
  @JsonProperty("interpretedExtensions")
  private Set<Extension> interpretedExtensions;


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
    @JsonProperty("description") @Nullable String description,
    @JsonProperty("machineDescription") @Nullable JsonNode machineDescription,
    @JsonProperty("verbatimExtensions") @Nullable Set<Extension> verbatimExtensions,
    @JsonProperty("interpretedExtensions") @Nullable Set<Extension> interpretedExtensions,
    @JsonProperty("checklistKey") @Nullable String checklistKey
    ) {
    super(
      creator,
      notificationAddresses,
      sendNotification == null ? Boolean.TRUE : sendNotification,
      format == null ? DEFAULT_DOWNLOAD_FORMAT : format,
      type == null ? DownloadType.OCCURRENCE : type,
      description,
      machineDescription,
      checklistKey
    );
    this.predicate = predicate;
    this.verbatimExtensions =
        verbatimExtensions == null ? Collections.emptySet() : verbatimExtensions;
    this.interpretedExtensions =
        interpretedExtensions == null ? Collections.emptySet() : interpretedExtensions;
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
    return Objects.equals(predicate, that.predicate)
        && Objects.equals(verbatimExtensions, that.verbatimExtensions)
        && Objects.equals(interpretedExtensions, that.interpretedExtensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), predicate, verbatimExtensions, interpretedExtensions);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PredicateDownloadRequest.class.getSimpleName() + "[", "]")
      .add("predicate=" + predicate)
      .add("creator='" + getCreator() + "'")
      .add("notificationAddresses=" + getNotificationAddresses())
      .add("sendNotification=" + getSendNotification())
      .add("format=" + getFormat())
      .add("type=" + getType())
      .add("verbatimExtensions=" + verbatimExtensions)
      .add("interpretedExtensions=" + interpretedExtensions)
      .toString();
  }
}
