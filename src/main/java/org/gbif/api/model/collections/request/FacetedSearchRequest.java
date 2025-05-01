package org.gbif.api.model.collections.request;

import java.util.Map;
import java.util.Set;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.vocabulary.collections.CollectionsFacetParameter;

public interface FacetedSearchRequest<F extends CollectionsFacetParameter> {

  Set<F> getFacets();

  void setFacets(Set<F> facets);

  boolean isMultiSelectFacets();

  void setMultiSelectFacets(boolean multiSelectFacets);

  boolean isIncludeChildren();

  void setIncludeChildren(boolean includeChildren);

  Integer getFacetMinCount();

  void setFacetMinCount(Integer facetMinCount);

  Integer getFacetLimit();

  void setFacetLimit(Integer facetLimit);

  Integer getFacetOffset();

  void setFacetOffset(Integer facetOffset);

  Map<F, Pageable> getFacetPages();

  void setFacetPages(Map<F, Pageable> facetPages);
}
