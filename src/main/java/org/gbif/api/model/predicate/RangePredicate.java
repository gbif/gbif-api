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
import org.gbif.api.util.RangeValue;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This predicate checks if its {@code key} is within the range {@code value}.
 *
 * Note: ALA only, not advertised as part of the public GBIF API.
 */
@Schema(
  description = "This predicate checks if its `key` is within the range `value`."
)
public class RangePredicate<S extends SearchParameter> implements Predicate {

    @Schema(description = "The search parameter to test.")
    @NotNull
    private final S key;

    @Schema(description = "The range value to test for.")
    @NotNull private final RangeValue value;

    @JsonCreator
    public RangePredicate(@JsonProperty("key") S key, @JsonProperty("value") RangeValue value) {
        Objects.requireNonNull(key, "<key> may not be null");
        Objects.requireNonNull(value, "<value> may not be null");
        this.key = key;
        this.value = value;
    }

    public S getKey() {
        return key;
    }

    public RangeValue getValue() {
        return value;
    }
}
