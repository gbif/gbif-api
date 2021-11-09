package org.gbif.api.model.occurrence.search;

import org.gbif.api.model.occurrence.predicate.Predicate;

/** Search request that uses a predicate filter like the ones used un downloads.*/
public class OccurrencePredicateSearchRequest extends OccurrenceSearchRequest {

  private Predicate predicate;

  public Predicate getPredicate() {
    return predicate;
  }

  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
  }
}
