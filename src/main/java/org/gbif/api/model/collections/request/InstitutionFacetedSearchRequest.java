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
package org.gbif.api.model.collections.request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.vocabulary.collections.InstitutionFacetParameter;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class InstitutionFacetedSearchRequest extends InstitutionSearchRequest
    implements FacetedSearchRequest<InstitutionFacetParameter> {

  @Builder.Default @Nullable private Set<InstitutionFacetParameter> facets = new HashSet<>();
  @Nullable private boolean multiSelectFacets;
  @Nullable private Integer facetMinCount;
  @Builder.Default
  private Boolean facetIncludeChildren = Boolean.TRUE;
  @Nullable private Integer facetLimit = 10;
  @Nullable private Integer facetOffset;

  // Holds the paging configuration for each requested facet
  @Builder.Default @Nullable
  private Map<InstitutionFacetParameter, Pageable> facetPages = new HashMap<>();
}
