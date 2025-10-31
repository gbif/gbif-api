package org.gbif.api.model.common.search;

import org.gbif.api.model.predicate.Predicate;

/** Interface for search requests that use predicates. */
public interface PredicateSearchRequest {

  Predicate getPredicate();

  void setPredicate(Predicate predicate);

  String getQ();

  void setQ(String q);
}
