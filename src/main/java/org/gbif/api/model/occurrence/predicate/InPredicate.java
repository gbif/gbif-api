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

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

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
    Preconditions.checkNotNull(key, "<key> may not be null");
    Preconditions.checkNotNull(values, "<values> may not be null");
    Preconditions.checkArgument(!values.isEmpty(), "<values> may not be empty");
    // make sure the value is of the right type according to the key given
    for (String value : values) {
      SearchTypeValidator.validate(key, value);
    }

    this.key = key;
    this.values = ImmutableList.copyOf(values);
  }

  public OccurrenceSearchParameter getKey() {
    return key;
  }

  public Collection<String> getValues() {
    return values;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof InPredicate)) {
      return false;
    }

    InPredicate that = (InPredicate) obj;
    return Objects.equal(this.key, that.getKey()) && Objects.equal(this.values, that.getValues());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, values);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", key).add("values", values).toString();
  }
}
