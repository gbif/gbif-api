/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.occurrence.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class IsNotNullPredicate implements Predicate {

  @NotNull
  private final OccurrenceSearchParameter parameter;

  @JsonCreator
  public IsNotNullPredicate(@JsonProperty("parameter") OccurrenceSearchParameter parameter) {
    Preconditions.checkNotNull(parameter, "<parameter> may not be null");
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
      throw new IllegalArgumentException("IsNotNull predicate is not supported for Geometry parameter");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(parameter);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("parameter", parameter).toString();
  }

}
