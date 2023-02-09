/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
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
package org.gbif.api.model.occurrence.predicate;

import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(
  description = "This predicate checks if its `parameter` is null (empty)."
)
public class IsNullPredicate implements Predicate {

  @NotNull
  private final OccurrenceSearchParameter parameter;

  @JsonCreator
  public IsNullPredicate(@JsonProperty("parameter") OccurrenceSearchParameter parameter) {
    Objects.requireNonNull(parameter, "<parameter> may not be null");
    this.parameter = parameter;
    checkPredicateAllowed();
  }

  public OccurrenceSearchParameter getParameter() {
    return parameter;
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
    IsNullPredicate that = (IsNullPredicate) o;
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
