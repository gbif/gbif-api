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
package org.gbif.api.model.checklistbank.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.FacetedSearchRequest;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.ThreatStatus;

import java.util.UUID;

/**
 * A name usage specific search request with convenience methods to add enum based search filters.
 */
public class NameUsageSearchRequest extends FacetedSearchRequest<NameUsageSearchParameter> {

  public NameUsageSearchRequest() {
  }

  public NameUsageSearchRequest(Pageable page) {
    super(page);
  }

  public NameUsageSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  public void addChecklistFilter(UUID datasetKey) {
    addParameter(NameUsageSearchParameter.DATASET_KEY, datasetKey.toString());
  }

  public void addExtinctFilter(boolean isExtinct) {
    addParameter(NameUsageSearchParameter.IS_EXTINCT, String.valueOf(isExtinct));
  }

  public void addHigherTaxonFilter(int taxonKey) {
    addParameter(NameUsageSearchParameter.HIGHERTAXON_KEY, taxonKey);
  }

  public void addMarineFilter(boolean isMarine) {
    addParameter(NameUsageSearchParameter.HABITAT, isMarine);
  }

  public void addRankFilter(Rank rank) {
    addParameter(NameUsageSearchParameter.RANK, rank);
  }

  public void addTaxonomicStatusFilter(TaxonomicStatus status) {
    addParameter(NameUsageSearchParameter.STATUS, status);
  }

  public void addTaxonomicStatusFilter(NomenclaturalStatus nomenclaturalStatus) {
    addParameter(NameUsageSearchParameter.NOMENCLATURAL_STATUS, nomenclaturalStatus);
  }

  public void addThreatStatusFilter(ThreatStatus threat) {
    addParameter(NameUsageSearchParameter.THREAT, threat);
  }

}
