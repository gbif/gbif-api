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
package org.gbif.api.service.occurrence;

import org.gbif.api.model.occurrence.Occurrence;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchRequest;
import org.gbif.api.service.common.SearchService;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;

/**
 * Interface that provides search operations over Occurrences.
 * As the occurrence Solr index doesn't store values we use the full Occurrence class for search responses
 * as we need to do lookups by key anyways.
 */
public interface OccurrenceSearchService
  extends SearchService<Occurrence, OccurrenceSearchParameter, OccurrenceSearchRequest> {

  /**
   * Searches catalog numbers which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of catalog numbers
   */
  List<String> suggestCatalogNumbers(@Min(1) String prefix, @Nullable Integer limit);

  /**
   * Searches collection codes which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of collection names
   */
  List<String> suggestCollectionCodes(@Min(1) String prefix, @Nullable Integer limit);


  /**
   * Searches collector names (dwc:recordedBy) which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of collector names
   */
  List<String> suggestRecordedBy(@Min(1) String prefix, @Nullable Integer limit);

  /**
   * Searches record numbers which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of collector names
   */
  List<String> suggestRecordNumbers(@Min(1) String prefix, @Nullable Integer limit);


  /**
   * Searches institution codes which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of institution codes
   */
  List<String> suggestInstitutionCodes(@Min(1) String prefix, @Nullable Integer limit);

  /**
   * Searches DwC occurrenceIds which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of occurrenceIds
   */
  List<String> suggestOccurrenceIds(@Min(1) String prefix, @Nullable Integer limit);


  /**
   * Searches DwC organismsIds which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of organismIds
   */
  List<String> suggestOrganismIds(@Min(1) String prefix, @Nullable Integer limit);


  /**
   * Searches DwC localities which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of localities
   */
  List<String> suggestLocalities(@Min(1) String prefix, @Nullable Integer limit);

  /**
   * Searches DwC water bodies which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of water bodies
   */
  List<String> suggestWaterBodies(@Min(1) String prefix, @Nullable Integer limit);

  /**
   * Searches DwC state/provinces which start with the input prefix.
   * If the limit is set to a number less than 0, then no maximum limit is enforced.
   * If limit contains a null value, a default value is set by the service implementation.
   *
   * @param prefix search pattern
   * @param limit maximum number of results to return
   * @return of state provinces
   */
  List<String> suggestStateProvinces(@Min(1) String prefix, @Nullable Integer limit);
}
