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

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * This predicate checks if its {@code key} is equal to its {@code value}.
 */
@Schema(
  description = "This predicate checks if its `key` is equal to its `value`."
)
public class EqualsPredicate<S extends SearchParameter> extends SimplePredicate<S> {

  private final String checklistKey;

  @Schema(
    description = "Flag to additionally search in DNA taxonomies to improve the taxon search."
  )
  @Experimental
  private final Boolean dnaTaxonSearch;

  public EqualsPredicate(S key, String value, Boolean matchCase) {
    this(key, value, matchCase, null, null);
  }

  public EqualsPredicate(S key, String value, Boolean matchCase, String checklistKey) {
    this(key, value, matchCase, checklistKey, null);
  }

  @JsonCreator
  public EqualsPredicate(
    @JsonProperty("key") S key,
    @JsonProperty("value") String value,
    @Nullable @JsonProperty(value = "matchCase") Boolean matchCase,
    @Nullable @JsonProperty(value = "checklistKey") String checklistKey,
    @Nullable @JsonProperty(value = "dnaTaxonSearch") Boolean dnaTaxonSearch
    ) {
    super(false, key, value, matchCase);
    this.checklistKey = checklistKey;
    this.dnaTaxonSearch = dnaTaxonSearch;
  }

  public String getChecklistKey() {
    return checklistKey;
  }

  @Experimental
  public Boolean getDnaTaxonSearch() {
    return dnaTaxonSearch;
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
      && Objects.equals(this.dnaTaxonSearch, ((EqualsPredicate<?>) o).dnaTaxonSearch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey(), this.getValue(), this.isMatchCase(), checklistKey, dnaTaxonSearch);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("key=" + getKey())
      .add("value='" + getValue() + "'")
      .add("matchCase='" + this.isMatchCase() + "'")
      .add("checklistKey='" + checklistKey + "'")
      .add("dnaTaxonSearch='" + dnaTaxonSearch + "'")
      .toString();
  }
}
