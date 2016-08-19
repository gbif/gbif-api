/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.common.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Generic request class for search operations requesting facets.
 * It extends a search request with a list of desired facets and optional settings.
 */
public class FacetedSearchRequest<P extends SearchParameter> extends SearchRequest<P> {

  private Set<P> facets = Sets.newHashSet();

  private boolean multiSelectFacets;
  private Integer facetMinCount;
  private Integer facetLimit = 10;
  private Integer facetOffset;

  //Holds the paging configuration for each requested facet
  private Map<P,Pageable> facetPages = new HashMap<P, Pageable>();

  public FacetedSearchRequest() {
  }

  public FacetedSearchRequest(Pageable page) {
    super(page);
  }

  public FacetedSearchRequest(SearchRequest<P> searchRequest) {
    super(searchRequest);
    setHighlight(searchRequest.isHighlight());
    setParameters(searchRequest.getParameters());
    setQ(searchRequest.getQ());
  }

  public FacetedSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * Min count of facet to return, if the facet count is less than this number the facet won't be included.
   */
  public Integer getFacetMinCount() {
    return facetMinCount;
  }

  public void setFacetMinCount(Integer facetMinCount) {
    this.facetMinCount = facetMinCount;
  }

  /**
   * Gets the list of requested facets by the search operation.
   * The facets are a list of search parameters.
   */
  public Set<P> getFacets() {
    return facets;
  }

  /**
   * Sets the list of facets.
   */
  public void setFacets(Set<P> facets) {
    this.facets = facets;
  }

  /**
   * @return the multiSelectFacets
   */
  public boolean isMultiSelectFacets() {
    return multiSelectFacets;
  }

  /**
   * @param multiSelectFacets the multiSelectFacets to set
   */
  public void setMultiSelectFacets(boolean multiSelectFacets) {
    this.multiSelectFacets = multiSelectFacets;
  }

  /**
   * Page size of the facet request.
   */
  public Integer getFacetLimit() {
    return facetLimit;
  }

  public void setFacetLimit(Integer facetLimit) {
    this.facetLimit = facetLimit;
  }

  /**
   * Holds the paging configuration for each requested facet.
   */
  public Map<P, Pageable> getFacetPages() {
    return facetPages;
  }

  public void setFacetPages(Map<P, Pageable> facetPages) {
    this.facetPages = facetPages;
  }

  /**
   * Sets the paging setting of facet parameter.
   */
  public void addFacetPage(P parameter, int facetOffset, int facetLimit){
    facetPages.put(parameter, new PagingRequest(facetOffset,facetLimit));
  }

  /**
   * Gets the paging configuration of a facet parameter.
   */
  public Pageable getFacetPage(P parameter) {
    return facetPages.get(parameter);
  }

  /**
   * Offset of the facet request.
   */
  public Integer getFacetOffset() {
    return facetOffset;
  }

  public void setFacetOffset(Integer facetOffset) {
    this.facetOffset = facetOffset;
  }

  public void addFacets(P... facets) {
    if (this.facets == null) {
      this.facets = Sets.newHashSet(facets);
    } else {
      this.facets.addAll(Sets.newHashSet(facets));
    }
  }

}
