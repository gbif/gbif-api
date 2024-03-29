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
package org.gbif.api.model.common.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Generic response of a search operation. The result is a {@link java.util.List} of elements.
 * The type of the elements is bounded by the type parameter T. The list of results can be an empty list.
 * Optionally the response can contain: a count of the total returned elements, an offset, a pageSize and
 * the list of facets (if those were requested).
 *
 * @param <T> the actual type of response content
 */
public class SearchResponse<T, P extends SearchParameter> extends PagingResponse<T> {

  @Schema(
    description = "The resulting facets of a search operation.  The list can be empty if no facets were requested.\n\n" +
      "If there are no values for a facet, it will not be included in the list, i.e.: a facet should have " +
      "at least 1 occurrence."
  )
  private final List<Facet<P>> facets = new ArrayList<>();

  @Hidden
  private SpellCheckResponse spellCheckResponse;

  /**
   * Default constructor.
   */
  public SearchResponse() {
  }

  /**
   * Minimal paging constructor.
   */
  public SearchResponse(Pageable page) {
    super(page);
  }

  /**
   * Minimal paging constructor.
   */
  public SearchResponse(PagingResponse<T> response) {
    super(response, response.getCount(), response.getResults());
  }

  /**
   * Minimal paging constructor.
   */
  public SearchResponse(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * Full constructor.
   *
   * @param facets list of facets, never null
   */
  public SearchResponse(long offset, int pageSize, Long count, List<T> results, List<Facet<P>> facets) {
    super(offset, pageSize, count, results);
    setFacets(facets);
  }

  /**
   * Gets the resulting facets of a search operation, the list can be empty if no facets were requested.
   * If there are no values for a facet, it will not be included in the list, i.e.: a facet should have
   * at least 1 occurrence.
   */
  public List<Facet<P>> getFacets() {
    return facets;
  }

  /**
   * Sets the list of facets.
   *
   * @param facets list of facets, never null
   */
  public final void setFacets(List<Facet<P>> facets) {
    // copy entries
    this.facets.clear();
    if (facets != null) {
      this.facets.addAll(facets);
    }
  }

  /**
   *
   * @return the spellcheck response if it was requested
   */
  public SpellCheckResponse getSpellCheckResponse() {
    return spellCheckResponse;
  }

  /**
   * Sets the spellcheck response.
   * @param spellCheckResponse if it was requested
   */
  public void setSpellCheckResponse(SpellCheckResponse spellCheckResponse) {
    this.spellCheckResponse = spellCheckResponse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SearchResponse<?, ?> that = (SearchResponse<?, ?>) o;
    return Objects.equals(facets, that.facets) &&
      Objects.equals(spellCheckResponse, that.spellCheckResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), facets, spellCheckResponse);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SearchResponse.class.getSimpleName() + "[", "]")
      .add("facets=" + facets)
      .add("spellCheckResponse=" + spellCheckResponse)
      .add("offset=" + offset)
      .add("limit=" + limit)
      .add("results=" + getResults())
      .add("count=" + getCount())
      .toString();
  }
}
