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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This predicate checks if its {@code key} is equal to its {@code value}.
 */
@Schema(
  description = "This predicate checks if its `key` is equal to its `value`."
)
public class EqualsPredicate extends SimplePredicate {

  @JsonCreator
  public EqualsPredicate(
    @JsonProperty("key") OccurrenceSearchParameter key,
    @JsonProperty("value") String value,
    @Nullable @JsonProperty(value = "matchCase") Boolean matchCase) {
    super(false, key, value, matchCase);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof EqualsPredicate)) {
      return false;
    }

    SimplePredicate that = (SimplePredicate) obj;
    return Objects.equals(this.getKey(), that.getKey()) && Objects.equals(this.getValue(), that.getValue());
  }

}
