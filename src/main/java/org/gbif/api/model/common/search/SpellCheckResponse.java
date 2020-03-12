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
package org.gbif.api.model.common.search;

import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;

/**
 * Contains the response information of a spell check request.
 */
public class SpellCheckResponse {

  private boolean correctlySpelled;

  private Map<String,Suggestion> suggestions;

  /**
   * Information about a suggestion made for possible correct spelled version of a search term.
   */
  public static class Suggestion {

    private int numFound;
    private List<String> alternatives;

    /**
     * @return the number of records that matched this suggestion
     */
    public int getNumFound() {
      return numFound;
    }

    /**
     * Sets the number of documents found for this suggestion.
     */
    public void setNumFound(int numFound) {
      this.numFound = numFound;
    }

    /**
     *
     * @return the alternatives corrections for this token
     */
    public List<String> getAlternatives() {
      return alternatives;
    }

    /**
     * Sets the alternatives for a token.
     */
    public void setAlternatives(List<String> alternatives) {
      this.alternatives = alternatives;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Suggestion)) {
        return false;
      }
      Suggestion that = (Suggestion) obj;
      return Objects.equal(this.numFound, that.numFound)
             && Objects.equal(this.alternatives, that.alternatives);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(numFound,alternatives);
    }

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
        .add("numFound", numFound)
        .add("alternatives", alternatives)
        .toString();
    }
  }

  /**
   *
   * @return false if the response contains misspellings
   */
  public boolean isCorrectlySpelled() {
    return correctlySpelled;
  }

  /**
   * Sets the misspelling flag.
   */
  public void setCorrectlySpelled(boolean correctlySpelled) {
    this.correctlySpelled = correctlySpelled;
  }

  /**
   *
   * @return the suggestion map
   */
  public Map<String, Suggestion> getSuggestions() {
    return suggestions;
  }

  /**
   *
   * @param token correction of a search term
   * @return the suggestion for a token
   */
  public Suggestion getSuggestion(String token) {
    return suggestions.get(token);
  }

  /**
   * Sets the suggestion map.
   */
  public void setSuggestions(Map<String, Suggestion> suggestions) {
    this.suggestions = suggestions;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SpellCheckResponse)) {
      return false;
    }
    SpellCheckResponse that = (SpellCheckResponse) obj;
    return Objects.equal(this.suggestions, that.suggestions)
           && Objects.equal(this.correctlySpelled, that.correctlySpelled);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(suggestions,correctlySpelled);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("suggestions", suggestions)
      .add("correctlySpelled", correctlySpelled)
      .toString();
  }
}
