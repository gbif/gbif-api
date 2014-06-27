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

import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This predicate negates its subpredicate.
 * Note: This may not work with all combinations of subpredicates.
 */
public class NotPredicate implements Predicate {

  @NotNull
  private final Predicate predicate;

  @JsonCreator
  public NotPredicate(@JsonProperty("predicate") Predicate predicate) {
    Preconditions.checkNotNull(predicate);

    this.predicate = predicate;
  }

  public Predicate getPredicate() {
    return predicate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof NotPredicate)) {
      return false;
    }

    NotPredicate that = (NotPredicate) obj;
    return Objects.equal(this.predicate, that.predicate);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(predicate);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("predicate", predicate).toString();
  }
}
