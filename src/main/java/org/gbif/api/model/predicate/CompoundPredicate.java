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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.StringJoiner;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * A compound predicate is a Predicate that itself contains other Predicates.
 * This is to be used as a base class because the way the containing Predicates
 * should be combined needs to be specified (e.g. "AND", "OR").
 */
public class CompoundPredicate implements Predicate {

  @Schema(
    description = "The list of sub-predicates to combine"
  )
  @NotNull
  private final Collection<Predicate> predicates;

  @JsonCreator
  protected CompoundPredicate(@JsonProperty("predicates") Collection<Predicate> predicates) {
    Objects.requireNonNull(predicates, "Predicates may not be null");
    checkArgument(!predicates.isEmpty(), "Predicates may not be empty");
    this.predicates = Collections.unmodifiableList(new ArrayList<>(predicates));
  }

  /**
   * Returns all the predicates this compound predicate is made out of in an <em>immutable</em> collection.
   *
   * @return the immutable collection of child predicates.
   */
  public Collection<Predicate> getPredicates() {
    return predicates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompoundPredicate that = (CompoundPredicate) o;
    return Objects.equals(predicates, that.predicates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(predicates);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("predicates=" + predicates)
      .toString();
  }
}
