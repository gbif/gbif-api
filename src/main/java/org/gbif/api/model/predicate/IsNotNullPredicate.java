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
package org.gbif.api.model.predicate;

import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

@Schema(
  description = "This predicate checks if the `parameter` is not null (empty)."
)
public class IsNotNullPredicate<S extends SearchParameter> implements Predicate {

  @Schema(
    description = "The search parameter to test."
  )
  @NotNull private final S parameter;

  @Schema(
    description = "Specify which taxonomy to use."
  )
  @Nullable
  private final UUID checklistKey;

  @Schema(
    description = "Flag to also match the DNA-assisted classifications of the occurrences, in addition to their " +
      "regular classifications. It takes precedence over dnaAssistedChecklistKey."
  )
  @Experimental
  @Nullable
  private final Boolean useDnaAssistance;

  @Schema(
    description = "Specify the taxonomy whose DNA-assisted classifications are matched instead of the regular " +
      "classifications. It is ignored when useDnaAssistance is true, since both classifications are matched then."
  )
  @Experimental
  @Nullable
  private final UUID dnaAssistedChecklistKey;

  public IsNotNullPredicate(S parameter) {
    this(parameter, null, null);
  }

  public IsNotNullPredicate(S parameter, UUID checklistKey) {
    this(parameter, checklistKey, null);
  }

  public IsNotNullPredicate(S parameter, UUID checklistKey, Boolean useDnaAssistance) {
    this(parameter, checklistKey, useDnaAssistance, null);
  }

  @JsonCreator
  public IsNotNullPredicate(
    @JsonProperty("parameter") S parameter,
    @Nullable @JsonProperty(value = "checklistKey") UUID checklistKey,
    @Nullable @JsonProperty(value = "useDnaAssistance") Boolean useDnaAssistance,
    @Nullable @JsonProperty(value = "dnaAssistedChecklistKey") UUID dnaAssistedChecklistKey) {
    Objects.requireNonNull(parameter, "<parameter> may not be null");
    this.parameter = parameter;
    this.checklistKey = checklistKey;
    this.useDnaAssistance = useDnaAssistance;
    this.dnaAssistedChecklistKey = dnaAssistedChecklistKey;
    checkPredicateAllowed();
  }

  public S getParameter() {
    return parameter;
  }

  public UUID getChecklistKey() {
    return checklistKey;
  }

  @Experimental
  // not serialized when unset, so the JSON of the predicates without it doesn't change
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public Boolean getUseDnaAssistance() {
    return useDnaAssistance;
  }

  @Experimental
  @Nullable
  // not serialized when unset, so the JSON of the predicates without it doesn't change
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public UUID getDnaAssistedChecklistKey() {
    return dnaAssistedChecklistKey;
  }

  /**
   * @throws IllegalArgumentException if the key SearchParameter is Geometry
   */
  private void checkPredicateAllowed() {
    if (OccurrenceSearchParameter.GEOMETRY == parameter) {
      throw new IllegalArgumentException("IsNotNull predicate is not supported for Geometry parameter");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IsNotNullPredicate<S> that = (IsNotNullPredicate<S>) o;
    return parameter == that.parameter
      && Objects.equals(checklistKey, that.checklistKey)
      && Objects.equals(useDnaAssistance, that.useDnaAssistance)
      && Objects.equals(dnaAssistedChecklistKey, that.dnaAssistedChecklistKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameter, checklistKey, useDnaAssistance, dnaAssistedChecklistKey);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IsNotNullPredicate.class.getSimpleName() + "[", "]")
      .add("parameter=" + parameter)
      .add("checklistKey=" + checklistKey)
      .add("useDnaAssistance=" + useDnaAssistance)
      .add("dnaAssistedChecklistKey=" + dnaAssistedChecklistKey)
      .toString();
  }
}
