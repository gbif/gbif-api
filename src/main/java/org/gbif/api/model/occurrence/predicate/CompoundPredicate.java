/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

import java.util.Collection;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A compound predicate is a Predicate that itself contains other Predicates.
 * This is to be used as a base class because the way the containing Predicates
 * should be combined needs to be specified (e.g. "AND", "OR").
 */
public class CompoundPredicate implements Predicate {

  @NotNull
  private final Collection<Predicate> predicates;

  @JsonCreator
  protected CompoundPredicate(@JsonProperty("predicates") Collection<Predicate> predicates) {
    Preconditions.checkNotNull(predicates, "Predicates may not be null");
    Preconditions.checkArgument(!predicates.isEmpty(), "Predicates may not be empty");
    this.predicates = ImmutableList.copyOf(predicates);
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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CompoundPredicate)) {
      return true;
    }

    final CompoundPredicate other = (CompoundPredicate) obj;
    return Objects.equal(this.predicates, other.predicates);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(predicates);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("predicates", predicates).toString();
  }
}
