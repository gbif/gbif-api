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

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
  description = "This predicate checks if its `parameter` is null (empty)."
)
public class IsNullPredicate<S extends SearchParameter> implements Predicate {

  @Schema(
    description = "The search parameter to test."
  )
  @NotNull private final S parameter;

  @Schema(
    description = "Specify which taxonomy to use."
  )
  @Experimental
  @Nullable
  private final String checklistKey;

  public IsNullPredicate(S parameter) {
    this(parameter, null);
  }

  @JsonCreator
  public IsNullPredicate(@JsonProperty("parameter") S parameter, @Nullable @JsonProperty(value = "checklistKey") String checklistKey) {
    Objects.requireNonNull(parameter, "<parameter> may not be null");
    this.parameter = parameter;
    this.checklistKey = checklistKey;
    checkPredicateAllowed();
  }

  public S getParameter() {
    return parameter;
  }

  @Experimental
  public String getChecklistKey() {
    return checklistKey;
  }

  /**
   * @throws IllegalArgumentException if the key SearchParameter is Geometry
   */
  private void checkPredicateAllowed() {
    if (OccurrenceSearchParameter.GEOMETRY == parameter) {
      throw new IllegalArgumentException("IsNull predicate is not supported for Geometry parameter");
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
    IsNullPredicate<S> that = (IsNullPredicate<S>) o;
    return parameter == that.parameter;
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameter);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IsNullPredicate.class.getSimpleName() + "[", "]")
      .add("parameter=" + parameter)
      .toString();
  }
}
