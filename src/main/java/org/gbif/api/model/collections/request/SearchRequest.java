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
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingConstants;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.CollectionsSortField;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.SortOrder;
import org.gbif.api.vocabulary.collections.MasterSourceType;
import org.gbif.api.vocabulary.collections.Source;

@SuperBuilder
@Data
public abstract class SearchRequest implements Serializable {

  @Nullable private Boolean hl;
  @Nullable private String q;
  @Nullable private String code;
  @Nullable private String name;
  @Nullable private String alternativeCode;
  @Nullable private UUID contact;
  @Nullable private String machineTagNamespace;
  @Nullable private String machineTagName;
  @Nullable private String machineTagValue;
  @Nullable private IdentifierType identifierType;
  @Nullable private String identifier;
  @Nullable private List<Country> country;
  @Nullable private List<GbifRegion> gbifRegion;
  @Nullable private String city;
  @Nullable private String fuzzyName;
  @Nullable private Boolean active;
  @Nullable private MasterSourceType masterSourceType;
  @Nullable private String numberSpecimens;
  @Nullable private Boolean displayOnNHCPortal;
  @Nullable private UUID replacedBy;
  @Nullable private String occurrenceCount;
  @Nullable private String typeSpecimenCount;
  @Nullable private CollectionsSortField sortBy;
  @Nullable private SortOrder sortOrder;
  @Nullable private List<UUID> institutionKeys;
  @Nullable private Source source;
  @Nullable private String sourceId;
  @Nullable private Long offset;
  @Nullable private Integer limit;

  public Pageable getPage() {
    return new PagingRequest(
        offset != null ? offset : PagingConstants.DEFAULT_PARAM_OFFSET,
        limit != null ? limit : PagingConstants.DEFAULT_PARAM_LIMIT);
  }
}
