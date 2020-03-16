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

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.StringJoiner;

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

  @JsonCreator
  public InPredicate(@JsonProperty("key") OccurrenceSearchParameter key, @JsonProperty("values") Collection<String> values) {
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
      Objects.equals(values, that.values);
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
      .toString();
  }
}
