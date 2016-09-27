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
import org.gbif.api.vocabulary.Habitat;
import org.gbif.api.vocabulary.NameUsageIssue;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.ThreatStatus;

import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;

import static org.gbif.api.model.checklistbank.search.NameUsageSearchRequest.QueryField.DESCRIPTION;
import static org.gbif.api.model.checklistbank.search.NameUsageSearchRequest.QueryField.SCIENTIFIC;
import static org.gbif.api.model.checklistbank.search.NameUsageSearchRequest.QueryField.VERNACULAR;

/**
 * A name usage specific search request with convenience methods to add enum based search filters.
 * By default the query q is send to all available query fields.
 * Highlighting by default works for descriptions and vernacular names - if turned on.
 */
public class NameUsageSearchRequest extends FacetedSearchRequest<NameUsageSearchParameter> {
  private boolean extended = true;
  private Set<QueryField> queryFields = Sets.newHashSet(SCIENTIFIC, DESCRIPTION, VERNACULAR);
  private Set<QueryField> highlightFields = Sets.newHashSet(DESCRIPTION, VERNACULAR);
  private Integer highlightContext = 100;

  public enum QueryField {
    SCIENTIFIC,
    VERNACULAR,
    DESCRIPTION
  }

  public NameUsageSearchRequest() {
  }

  public NameUsageSearchRequest(Pageable page) {
    super(page);
  }

  public NameUsageSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * Defines whether to match against fields with scientific or vernacular names or both.
   */
  public Set<QueryField> getQueryFields() {
    return queryFields;
  }

  public void setQueryFields(Set<QueryField> queryFields) {
    this.queryFields = queryFields;
  }

  /**

   * Defines the fields to be highlighted if highlighting is activated.
   */
  public Set<QueryField> getHighlightFields() {
    return highlightFields;
  }

  public void setHighlightFields(Set<QueryField> highlightFields) {
    this.highlightFields = highlightFields;
  }

  /**
   * @return the number of characters of the context to show for the highlighted match, including the match itself.
   */
  public Integer getHighlightContext() {
    return highlightContext;
  }

  public void setHighlightContext(Integer highlightContext) {
    this.highlightContext = highlightContext;
  }

  /**
   * Allows to request an extended search object with the larger list properties:
   * <ul>
   *  <li>habitats</li>
   *  <li>nomenclaturalStatus</li>
   *  <li>threatStatuses</li>
   *  <li>descriptions</li>
   *  <li>vernacularNames</li>
   * </ul>
   *
   * If extended=false and highlighting is activated, the highlighted matches will still be added to the extended properties.
   * In that case only the matched parts are shown, e.g. just the one vernacular name that matched.
   *
   * @return if true request the extended search model
   */
  public boolean isExtended() {
    return extended;
  }

  public void setExtended(boolean extended) {
    this.extended = extended;
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

  public void addHabitatFilter(Habitat habitat) {
    addParameter(NameUsageSearchParameter.HABITAT, habitat);
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

  public void addIssueFilter(NameUsageIssue issue) {
    addParameter(NameUsageSearchParameter.ISSUE, issue);
  }

}
