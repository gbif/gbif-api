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

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

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
  @Nullable
  private final UUID checklistKey;

  @Schema(
    description = "Flag to also match the DNA-assisted classifications of the occurrences, in addition to their " +
      "regular classifications. It takes precedence over dnaAssistedChecklistKey."
  )
  @Experimental
  @Nullable
  private final Boolean useDnaAssistance;

  @Schema(
    description = "Specify the taxonomy whose DNA-assisted classifications are matched instead of the regular " +
      "classifications. It is ignored when useDnaAssistance is true, since both classifications are matched then."
  )
  @Experimental
  @Nullable
  private final UUID dnaAssistedChecklistKey;

  public LikePredicate(S key, String value, Boolean matchCase) {
    this(key, value, null, matchCase, null);
  }

  public LikePredicate(S key, String value, UUID checklistKey, Boolean matchCase) {
    this(key, value, checklistKey, matchCase, null);
  }

  public UUID getChecklistKey() {
    return checklistKey;
  }

  @Experimental
  @Nullable
  // not serialized when unset, so the JSON of the predicates without it doesn't change
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public Boolean getUseDnaAssistance() {
    return useDnaAssistance;
  }

  @Experimental
  @Nullable
  // not serialized when unset, so the JSON of the predicates without it doesn't change
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public UUID getDnaAssistedChecklistKey() {
    return dnaAssistedChecklistKey;
  }

  public LikePredicate(
      S key, String value, UUID checklistKey, Boolean matchCase, Boolean useDnaAssistance) {
    this(key, value, checklistKey, matchCase, useDnaAssistance, null);
  }

  @JsonCreator
  public LikePredicate(
      @JsonProperty("key") S key,
      @JsonProperty("value") String value,
      @Nullable @JsonProperty(value = "checklistKey") UUID checklistKey,
      @Nullable @JsonProperty(value = "matchCase") Boolean matchCase,
      @Nullable @JsonProperty(value = "useDnaAssistance") Boolean useDnaAssistance,
      @Nullable @JsonProperty(value = "dnaAssistedChecklistKey") UUID dnaAssistedChecklistKey) {
    super(false, key, value, matchCase);
    this.checklistKey = checklistKey;
    this.useDnaAssistance = useDnaAssistance;
    this.dnaAssistedChecklistKey = dnaAssistedChecklistKey;
    // make sure we deal with a String type
    if (!String.class.equals(key.type())) {
      throw new IllegalArgumentException("Like comparisons are only allowed for strings but not parameter " + key);
    }
  }

  @Override
  public boolean equals(Object o) {
    // the superclass checks the class, key, value and matchCase
    if (!super.equals(o)) {
      return false;
    }
    LikePredicate<?> that = (LikePredicate<?>) o;
    return Objects.equals(this.checklistKey, that.checklistKey)
      && Objects.equals(this.useDnaAssistance, that.useDnaAssistance)
      && Objects.equals(this.dnaAssistedChecklistKey, that.dnaAssistedChecklistKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), checklistKey, useDnaAssistance, dnaAssistedChecklistKey);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("key=" + getKey())
      .add("value='" + getValue() + "'")
      .add("matchCase='" + this.isMatchCase() + "'")
      .add("checklistKey='" + checklistKey + "'")
      .add("useDnaAssistance='" + useDnaAssistance + "'")
      .add("dnaAssistedChecklistKey='" + dnaAssistedChecklistKey + "'")
      .toString();
  }
}
