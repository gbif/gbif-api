package org.gbif.api.model.occurrence.predicate;

import javax.validation.constraints.NotNull;

public class FullTextSearchPredicate implements Predicate {

  @NotNull private final String q;

  public FullTextSearchPredicate(String q) {
    this.q = q;
  }

  public String getQ() {
    return q;
  }
}
