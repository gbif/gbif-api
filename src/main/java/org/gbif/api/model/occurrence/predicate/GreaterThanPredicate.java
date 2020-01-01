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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

/**
 * This predicate checks if its {@code key} is greater than its {@code value}.
 */
public class GreaterThanPredicate extends SimplePredicate {

  @JsonCreator
  public GreaterThanPredicate(
    @JsonProperty("key") OccurrenceSearchParameter key,
    @JsonProperty("value") String value) {
    super(true, key, value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof GreaterThanPredicate)) {
      return false;
    }

    SimplePredicate that = (SimplePredicate) obj;
    return Objects.equal(this.getKey(), that.getKey()) && Objects.equal(this.getValue(), that.getValue());
  }
}
