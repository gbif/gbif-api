package org.gbif.api.model.collections.search;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gbif.api.vocabulary.collections.CollectionsFacetParameter;

@Data
@NoArgsConstructor
public class CollectionFacet<T extends CollectionsFacetParameter> {

  private T field;
  private long cardinality;
  private List<Count> counts;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Count {
    private String name;
    private Long count;
  }
}
