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

import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * This predicate checks if its {@code key} contains any of its {@code values}.
 */
@Schema(
  description = "This predicate checks if its `key` is equal to any of its `values`."
)
public class InPredicate<S extends SearchParameter> implements Predicate {

  @Schema(
    description = "The search parameter to test."
  )
  @NotNull private final S key;

  @Schema(
    description = "The list of value to test for."
  )
  @NotNull
  @Size(min = 1)
  private final Collection<String> values;

  @Schema(
    description = "Whether to match letter case (UPPER or lower case) on string value comparisons."
  )
  @Experimental
  @Nullable
  private final Boolean matchCase;

  @JsonCreator
  public InPredicate(
      @JsonProperty("key") S key,
      @JsonProperty("values") Collection<String> values,
      @JsonProperty(value = "matchCase") Boolean matchCase) {
    this.matchCase = matchCase;
    Objects.requireNonNull(key, "<key> may not be null");
    Objects.requireNonNull(values, "<values> may not be null");
    checkArgument(!values.isEmpty(), "<values> may not be empty");
    // make sure the value is of the right type according to the key given
    for (String value : values) {
      if (value == null) {
        throw new NullPointerException();
      }
      SearchTypeValidator.validate(key, value);
    }

    this.key = key;
    this.values = Collections.unmodifiableList(new ArrayList<>(values));
  }

  public S getKey() {
    return key;
  }

  public Collection<String> getValues() {
    return values;
  }

  /**
   * This flag enables the use of case-sensitive matches and aggregations on certain search parameters.
   * <p>
   * Fields that support this feature are: occurrenceId, recordedBy, samplingProtocol, catalogNumber, collectionCode,
   * institutionCode, eventId, parentEventId, waterBody, stateProvince, recordNumber, identifiedBy, organismId and locality.
   * <p>
   * This is an experimental feature and its implementation map change or be removed at any time.
   */
  @Experimental
  public Boolean isMatchCase() {
    return Optional.ofNullable(matchCase).orElse(Boolean.FALSE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InPredicate<S> that = (InPredicate<S>) o;
    return key == that.key && Objects.equals(values, that.values) && matchCase == that.matchCase;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, values, matchCase);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InPredicate.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("values=" + values)
      .add("matchCase=" + matchCase)
      .toString();
  }
}
