package org.gbif.api.model.collections.request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.collections.CollectionFacetParameter;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class CollectionDescriptorsSearchRequest extends CollectionSearchRequest
    implements FacetedSearchRequest<CollectionFacetParameter> {

  @Nullable private List<String> usageKey;
  @Nullable private List<String> usageName;
  @Nullable private List<String> usageRank;
  @Nullable private List<String> taxonKey;
  @Nullable private List<Country> descriptorCountry;
  @Nullable private List<String> individualCount;
  @Nullable private List<String> identifiedBy;
  @Nullable private List<String> dateIdentified;
  @Nullable private List<String> typeStatus;
  @Nullable private List<String> recordedBy;
  @Nullable private List<String> discipline;
  @Nullable private List<String> objectClassification;
  @Nullable private List<String> biome;
  @Nullable private List<String> issue;

  @Builder.Default @Nullable private Set<CollectionFacetParameter> facets = new HashSet<>();
  @Nullable private boolean multiSelectFacets;
  @Nullable private Integer facetMinCount;
  @Nullable private Integer facetLimit = 10;
  @Nullable private Integer facetOffset;

  // Holds the paging configuration for each requested facet
  @Builder.Default @Nullable
  private Map<CollectionFacetParameter, Pageable> facetPages = new HashMap<>();
}
