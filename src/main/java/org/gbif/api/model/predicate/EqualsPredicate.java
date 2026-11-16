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
 * This predicate checks if its {@code key} is equal to its {@code value}.
 */
@Schema(
  description = "This predicate checks if its `key` is equal to its `value`."
)
public class EqualsPredicate<S extends SearchParameter> extends SimplePredicate<S> {

  @Schema(
    description = "Specify which taxonomy to use."
  )
  @Nullable
  private final UUID checklistKey;

  @Schema(
    description = "Flag to also match the DNA-assisted classifications of the occurrences, in addition to their " +
      "regular classifications."
  )
  @Experimental
  private final Boolean useDnaAssistance;

  @Schema(
    description = "Specify the taxonomy whose DNA-assisted classifications are used instead of the regular " +
      "classifications. It is ignored when useDnaAssistance is used."
  )
  @Experimental
  @Nullable
  private final UUID dnaAssistedChecklistKey;

  public EqualsPredicate(S key, String value, Boolean matchCase) {
    this(key, value, matchCase, null, null);
  }

  public EqualsPredicate(S key, String value, Boolean matchCase, UUID checklistKey) {
    this(key, value, matchCase, checklistKey, null);
  }

  public EqualsPredicate(
    S key, String value, Boolean matchCase, UUID checklistKey, Boolean useDnaAssistance) {
    this(key, value, matchCase, checklistKey, useDnaAssistance, null);
  }

  @JsonCreator
  public EqualsPredicate(
    @JsonProperty("key") S key,
    @JsonProperty("value") String value,
    @Nullable @JsonProperty(value = "matchCase") Boolean matchCase,
    @Nullable @JsonProperty(value = "checklistKey") UUID checklistKey,
    @Nullable @JsonProperty(value = "useDnaAssistance") Boolean useDnaAssistance,
    @Nullable @JsonProperty(value = "dnaAssistedChecklistKey") UUID dnaAssistedChecklistKey
    ) {
    super(false, key, value, matchCase);
    this.checklistKey = checklistKey;
    this.useDnaAssistance = useDnaAssistance;
    this.dnaAssistedChecklistKey = dnaAssistedChecklistKey;
  }

  public UUID getChecklistKey() {
    return checklistKey;
  }

  @Experimental
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimplePredicate<S> that = (SimplePredicate<S>) o;
    return this.getKey() == that.getKey()
      && Objects.equals(this.getValue(), that.getValue())
      && this.isMatchCase() == that.isMatchCase()
      && Objects.equals(this.checklistKey, ((EqualsPredicate<?>) o).checklistKey)
      && Objects.equals(this.useDnaAssistance, ((EqualsPredicate<?>) o).useDnaAssistance)
      && Objects.equals(this.dnaAssistedChecklistKey, ((EqualsPredicate<?>) o).dnaAssistedChecklistKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), this.getValue(), this.isMatchCase(), checklistKey, useDnaAssistance,
      dnaAssistedChecklistKey);
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
