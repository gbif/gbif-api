package org.gbif.api.model.collections.search;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.vocabulary.collections.CollectionsFacetParameter;

@Data
@NoArgsConstructor
public class FacetedSearchResponse<T, F extends CollectionsFacetParameter>
    extends PagingResponse<T> {

  private List<CollectionFacet<F>> facets = new ArrayList<>();

  public FacetedSearchResponse(
      Pageable page, Long count, List<T> results, List<CollectionFacet<F>> facets) {
    super(page, count, results);
    this.facets = facets;
  }

  public FacetedSearchResponse(Pageable page, Long count, List<T> results) {
    this(page, count, results, new ArrayList<>());
  }

  public FacetedSearchResponse(
      long offset, int limit, Long count, List<T> results, List<CollectionFacet<F>> facets) {
    super(offset, limit, count, results);
    this.facets = new ArrayList<>();
  }

  public FacetedSearchResponse(long offset, int limit, Long count, List<T> results) {
    this(offset, limit, count, results, new ArrayList<>());
  }
}
