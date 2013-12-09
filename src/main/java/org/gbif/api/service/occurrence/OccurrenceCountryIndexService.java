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
