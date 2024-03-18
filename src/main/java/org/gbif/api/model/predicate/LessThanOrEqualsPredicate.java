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

import org.gbif.api.model.common.search.SearchParameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * This predicate checks if its {@code key} is less or equal than its {@code value}.
 */
@Schema(
  description = "This predicate checks if its `key` is less than or equal to to its `value`."
)
public class LessThanOrEqualsPredicate<S extends SearchParameter> extends SimplePredicate<S> {

  @JsonCreator
  public LessThanOrEqualsPredicate(
      @JsonProperty("key") S key, @JsonProperty("value") String value) {
    super(true, key, value, null);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof LessThanOrEqualsPredicate)) {
      return false;
    }

    SimplePredicate<S> that = (SimplePredicate<S>) obj;
    return Objects.equals(this.getKey(), that.getKey())
      && Objects.equals(this.getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getKey(), this.getValue());
  }
}
