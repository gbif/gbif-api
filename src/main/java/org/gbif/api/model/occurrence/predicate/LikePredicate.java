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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This predicate checks if its {@code key} is LIKE its {@code value}.
 * The syntax for one <code>?</code> or any <code>*</code> arbitrary matching characters
 * is the one used by ElasticSearch, Unix/DOS shells, etc.
 */
public class LikePredicate extends SimplePredicate {

  @JsonCreator
  public LikePredicate(
    @JsonProperty("key") OccurrenceSearchParameter key,
    @JsonProperty("value") String value,
    @Nullable @JsonProperty(value = "matchCase") Boolean matchCase) {
    super(false, key, value, matchCase);
    // make sure we deal with a String type
    if (!String.class.equals(key.type())) {
      throw new IllegalArgumentException("Like comparisons are only allowed for strings but not parameter " + key);
    }
  }
}
