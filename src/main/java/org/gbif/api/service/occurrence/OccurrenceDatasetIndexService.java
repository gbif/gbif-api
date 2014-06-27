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

import org.gbif.api.vocabulary.Country;

import java.util.SortedMap;
import java.util.UUID;

/**
 * Supports the listing of datasets having occurrence records.
 */
public interface OccurrenceDatasetIndexService {

  /**
   * This provides a map of occurrence dataset keys to the number of records matching the given country.
   * Datasets which do not have content are not included (e.g. no zero-values will exist).
   * That is to say the count represents the number of records in the dataset that are interpreted as
   * the provided country, and not the total count of records in the dataset.
   *
   * @param country to filter occurrence datasets by
   * @return a map of dataset keys to counts sorted by count descending
   */
  SortedMap<UUID, Integer> occurrenceDatasetsForCountry(Country country);


  /**
   * This provides a map of occurrence dataset keys to the number of records matching the given nub key.
   * Datasets which do not have content are not included (e.g. no zero-values will exist).
   * That is to say the count represents the number of records in the dataset that are interpreted as
   * the provided taxon (or any subordinate taxon), and not the total count of records in the dataset. E.g. a dataset of
   * 1 million records covering everything, might report it has 300k records of birds (Aves); those records might
   * themselves have more specific identifications (such as the species).
   *
   * @param taxonKey to filter occurrence datasets by
   * @return a map of dataset keys to counts sorted by count descending
   */
  SortedMap<UUID, Integer> occurrenceDatasetsForNubKey(int taxonKey);
}
