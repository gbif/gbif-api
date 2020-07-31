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

import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.gbif.api.util.PreconditionUtils.checkArgument;

/**
 * This predicate checks if its {@code key} contains any of its {@code values}.
 */
public class InPredicate implements Predicate {

  @NotNull
  private final OccurrenceSearchParameter key;

  @NotNull
  @Size(min = 1)
  private final Collection<String> values;

  @Experimental
  @Nullable
  private final boolean matchCase;

  @JsonCreator
  public InPredicate(@JsonProperty("key") OccurrenceSearchParameter key,
                     @JsonProperty("values") Collection<String> values,
                     @JsonProperty(value = "matchCase", defaultValue = "false") boolean matchCase) {
    this.matchCase = matchCase;
    Objects.requireNonNull(key, "<key> may not be null");
    Objects.requireNonNull(values, "<values> may not be null");
    checkArgument(!values.isEmpty(), "<values> may not be empty");
    // make sure the value is of the right type according to the key given
    for (String value : values) {
      SearchTypeValidator.validate(key, value);
    }

    this.key = key;
    this.values = Collections.unmodifiableList(new ArrayList<>(values));
  }

  public OccurrenceSearchParameter getKey() {
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
  public boolean isMatchCase() {
    return matchCase;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InPredicate that = (InPredicate) o;
    return key == that.key &&
           Objects.equals(values, that.values) &&
           matchCase == that.matchCase;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, values);
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
