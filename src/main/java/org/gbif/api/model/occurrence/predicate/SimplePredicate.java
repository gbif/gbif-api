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

import java.util.Date;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class SimplePredicate implements Predicate {

  @NotNull
  private final OccurrenceSearchParameter key;

  @NotNull
  private final String value;

  protected SimplePredicate(boolean checkForNonEquals, OccurrenceSearchParameter key, String value) {
    Preconditions.checkNotNull(key, "<key> may not be null");
    Preconditions.checkNotNull(value, "<value> may not be null");
    Preconditions.checkArgument(!value.isEmpty(), "<value> may not be empty");
    // make sure the value is of the right type according to the key given
    SearchTypeValidator.validate(key, value);

    this.key = key;
    this.value = value;

    if (checkForNonEquals) {
      checkNonEqualsComparatorAllowed();
    }
  }

  public OccurrenceSearchParameter getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  /**
   * @throws IllegalArgumentException if the key SearchParameter allows other comparators than equals
   */
  private void checkNonEqualsComparatorAllowed() {
    if (!(Number.class.isAssignableFrom(key.type()) || Date.class.isAssignableFrom(key.type()))){
      throw new IllegalArgumentException("Only equals comparisons are allowed for search parameter " + key);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, value);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", key).add("value", value).toString();
  }

}
