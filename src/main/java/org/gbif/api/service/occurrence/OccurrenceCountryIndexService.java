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
package org.gbif.api.service.occurrence;

import org.gbif.api.vocabulary.Country;

import java.util.Map;

/**
 * Supports the listing of countries having occurrence records.
 */
public interface OccurrenceCountryIndexService {

  /**
   * Lists all publishing countries of occurrences falling within a given country.
   * @param country the country the occurrences fall into
   * @return map of publishing country to number of occurrences ordered by number of occurrences
   */
  Map<Country, Long> publishingCountriesForCountry(Country country);

  /**
   * Lists all countries occurrences are falling into that have been published by a given country.
   * @return map of countries to number of occurrences ordered by number of occurrences
   */
  Map<Country, Long> countriesForPublishingCountry(Country publishingCountry);
}
