package org.gbif.api.model.occurrence.predicate;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class FullTextSearchPredicate implements Predicate {

  @NotNull private final String q;

  @JsonCreator
  public FullTextSearchPredicate(@JsonProperty("q") String q) {
    this.q = q;
  }

  public String getQ() {
    return q;
  }
}
