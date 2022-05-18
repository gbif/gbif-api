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
package org.gbif.api.model.occurrence.predicate;

import org.gbif.api.util.PreconditionUtils;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This predicate checks performs a full text search based on a query parameter.
 * Not all predicate back-ends support this predicate.
 */
public class FullTextSearchPredicate implements Predicate {

  @NotNull
  private final String q;

  @JsonCreator
  public FullTextSearchPredicate(@NotNull @JsonProperty("key") String q) {
    PreconditionUtils.checkArgument( q != null && !q.trim().isEmpty(), "Query parameter can't be null or empty");
    this.q = q.trim();
  }

  /**
   * Text query parameter.
   */
  public String getQ() {
    return q;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FullTextSearchPredicate that = (FullTextSearchPredicate) o;
    return q.equals(that.q);
  }

  @Override
  public int hashCode() {
    return Objects.hash(q);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FullTextSearchPredicate.class.getSimpleName() + "[", "]")
      .add("q=" + q)
      .toString();
  }
}
