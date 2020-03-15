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

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This predicate negates its subpredicate.
 * Note: This may not work with all combinations of subpredicates.
 */
public class NotPredicate implements Predicate {

  @NotNull
  private final Predicate predicate;

  @JsonCreator
  public NotPredicate(@JsonProperty("predicate") Predicate predicate) {
    Objects.requireNonNull(predicate);

    this.predicate = predicate;
  }

  public Predicate getPredicate() {
    return predicate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotPredicate that = (NotPredicate) o;
    return Objects.equals(predicate, that.predicate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(predicate);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NotPredicate.class.getSimpleName() + "[", "]")
      .add("predicate=" + predicate)
      .toString();
  }
}
