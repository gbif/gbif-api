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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This predicate checks if its {@code key} is LIKE its {@code value}.
 * The syntax for one <code>?</code> or any <code>*</code> arbitrary matching characters
 * is the one used by ElasticSearch, Unix/DOS shells, etc.
 */
@Schema(
  description = "This predicate checks if its `key` matches a simple pattern in the `value`.\n\n" +
    "The character `?` matches a single character, and `*` matches zero or more characters.\n" +
    "This is similar to the matching used by ElasticSearch, Unix/DOS shells, etc."
)
public class LikePredicate<S extends SearchParameter> extends SimplePredicate<S> {

  @Schema(
    description = "Specify which taxonomy to use."
  )
  @Experimental
  @Nullable
  private final String checklistKey;

  public LikePredicate(S key, String value, Boolean matchCase) {
    this(key, value, null, matchCase);
  }

  @Experimental
  public String getChecklistKey() {
    return checklistKey;
  }

  @JsonCreator
  public LikePredicate(
      @JsonProperty("key") S key,
      @JsonProperty("value") String value,
      @Nullable @JsonProperty(value = "checklistKey") String checklistKey,
      @Nullable @JsonProperty(value = "matchCase") Boolean matchCase) {
    super(false, key, value, matchCase);
    this.checklistKey = checklistKey;
    // make sure we deal with a String type
    if (!String.class.equals(key.type())) {
      throw new IllegalArgumentException("Like comparisons are only allowed for strings but not parameter " + key);
    }
  }
}
