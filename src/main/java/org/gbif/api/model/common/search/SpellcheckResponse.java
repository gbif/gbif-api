package org.gbif.api.model.common.search;

import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
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
      return MoreObjects.toStringHelper(this)
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
    return MoreObjects.toStringHelper(this)
      .add("suggestions", suggestions)
      .add("correctlySpelled", correctlySpelled)
      .toString();
  }
}
