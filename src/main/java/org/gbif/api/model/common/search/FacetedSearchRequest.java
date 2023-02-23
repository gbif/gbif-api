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

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingRequest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Generic request class for search operations requesting facets.
 * It extends a search request with a list of desired facets and optional settings.
 */
@SuppressWarnings("unused")
public class FacetedSearchRequest<P extends SearchParameter> extends SearchRequest<P> {

  private Set<P> facets = new HashSet<>();

  private boolean multiSelectFacets;
  private Integer facetMinCount;
  private Integer facetLimit = 10;
  private Integer facetOffset;

  //Holds the paging configuration for each requested facet
  private Map<P, Pageable> facetPages = new HashMap<>();

  /**
   * Annotation to document the facet query parameters.
   */
  @Target({METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @io.swagger.v3.oas.annotations.Parameters(
    value = {
      @Parameter(
        name = "facet",
        description = "A facet name used to retrieve the most frequent values for a field. This parameter may be " +
          "repeated to request multiple facets.",
        array = @ArraySchema(schema = @Schema(implementation = String.class)),
        in = ParameterIn.QUERY,
        explode = Explode.TRUE),
      @Parameter(
        name = "facetMincount",
        description =
          "Used in combination with the facet parameter. Set `facetMincount={#}` to exclude facets with a count less than `{#}`.",
        schema = @Schema(implementation = Integer.class),
        in = ParameterIn.QUERY),
      @Parameter(
        name = "facetMultiselect",
        description =
          "Used in combination with the facet parameter. Set `facetMultiselect=true` to still return counts for values that are not currently filtered.",
        schema = @Schema(implementation = Boolean.class),
        in = ParameterIn.QUERY),
      @Parameter(
        name = "facetLimit",
        description =
          "Facet parameters allow paging requests using the parameters facetOffset and facetLimit",
        schema = @Schema(implementation = Integer.class),
        in = ParameterIn.QUERY),
      @Parameter(
        name = "facetOffset",
        description =
          "Facet parameters allow paging requests using the parameters facetOffset and facetLimit",
        schema = @Schema(implementation = Integer.class, minimum = "0"),
        in = ParameterIn.QUERY)
    }
  )
  public @interface FacetParameters {}

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
      this.facets = new HashSet<>(Arrays.asList(facets));
    } else {
      this.facets.addAll(new HashSet<>(Arrays.asList(facets)));
    }
  }
}
