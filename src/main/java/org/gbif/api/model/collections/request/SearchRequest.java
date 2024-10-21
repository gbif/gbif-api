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
package org.gbif.api.model.collections.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingConstants;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.SortOrder;
import org.gbif.api.vocabulary.collections.CollectionsFacetParameter;
import org.gbif.api.vocabulary.collections.CollectionsSortField;
import org.gbif.api.vocabulary.collections.MasterSourceType;
import org.gbif.api.vocabulary.collections.Source;

@SuperBuilder
@Data
public abstract class SearchRequest<F extends CollectionsFacetParameter> implements Serializable {

  @Nullable private Boolean hl;
  @Nullable private String q;
  @Nullable private List<String> code;
  @Nullable private List<String> name;
  @Nullable private List<String> alternativeCode;
  @Nullable private List<UUID> contact;
  @Nullable private List<String> machineTagNamespace;
  @Nullable private List<String> machineTagName;
  @Nullable private List<String> machineTagValue;
  @Nullable private List<IdentifierType> identifierType;
  @Nullable private List<String> identifier;
  @Nullable private List<Country> country;
  @Nullable private List<GbifRegion> gbifRegion;
  @Nullable private List<String> city;
  @Nullable private List<String> fuzzyName;
  @Nullable private List<Boolean> active;
  @Nullable private List<MasterSourceType> masterSourceType;
  @Nullable private String numberSpecimens;
  @Nullable private List<Boolean> displayOnNHCPortal;
  @Nullable private List<UUID> replacedBy;
  @Nullable private String occurrenceCount;
  @Nullable private String typeSpecimenCount;
  @Nullable private CollectionsSortField sortBy;
  @Nullable private SortOrder sortOrder;
  @Nullable private List<UUID> institutionKeys;
  @Nullable private List<Source> source;
  @Nullable private List<String> sourceId;
  @Nullable private Long offset;
  @Nullable private Integer limit;

  @Builder.Default @Nullable private Set<F> facets = new HashSet<>();
  @Nullable private boolean multiSelectFacets;
  @Nullable private Integer facetMinCount;
  @Nullable private Integer facetLimit = 10;
  @Nullable private Integer facetOffset;

  // Holds the paging configuration for each requested facet
  @Builder.Default @Nullable private Map<F, Pageable> facetPages = new HashMap<>();

  public Pageable getPage() {
    return new PagingRequest(
        offset != null ? offset : PagingConstants.DEFAULT_PARAM_OFFSET,
        limit != null ? limit : PagingConstants.DEFAULT_PARAM_LIMIT);
  }
}
