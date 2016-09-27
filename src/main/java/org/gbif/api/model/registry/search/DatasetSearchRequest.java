package org.gbif.api.model.registry.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.FacetedSearchRequest;
import org.gbif.api.model.registry.Tag;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;

import java.util.UUID;

/**
 * A dataset specific search request with convenience methods to add facet filters.
 */
public class DatasetSearchRequest extends FacetedSearchRequest<DatasetSearchParameter> {

  public DatasetSearchRequest() {
  }

  public DatasetSearchRequest(Pageable page) {
    super(page);
  }

  public DatasetSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * Filters dataset by the owning organizations country.
   */
  public void addPublishingCountryFilter(Country country) {
    addParameter(DatasetSearchParameter.PUBLISHING_COUNTRY, country.getIso2LetterCode());
  }

  /**
   * Filters dataset by a country of the geospatial coverage.
   *
   * @param country appearing in geospatial coverage
   */
  public void addCountryFilter(Country country) {
    addParameter(DatasetSearchParameter.COUNTRY, country.getIso2LetterCode());
  }

  /**
   * Filters dataset by a continent of the geospatial coverage.
   *
   * @param continent appearing in geospatial coverage
   */
  public void addContinentFilter(Continent continent) {
    addParameter(DatasetSearchParameter.CONTINENT, continent);
  }

  /**
   * Filters datasets by their temporal coverage broken down to decades.
   *
   * @param decade the decade given as a 4 digit integer
   */
  public void addDecadeFilter(int decade) {
    addParameter(DatasetSearchParameter.DECADE, decade);
  }

  public void addHostingOrgFilter(UUID orgKey) {
    addParameter(DatasetSearchParameter.HOSTING_ORG, orgKey.toString());
  }

  /**
   * Filters datasets by a keyword as generated through {@link org.gbif.api.model.registry.Dataset#getKeywords()} by
   * merging tags, the keywordCollections and temporalCoverages property.
   *
   * @param keyword a plain keyword e.g. created by Tag.toString()
   */
  // TODO: Fix Javadoc, what does this do? Keywords don't exist
  public void addKeywordFilter(String keyword) {
    addParameter(DatasetSearchParameter.KEYWORD, keyword);
  }

  /**
   * Filters dataset by a tag.
   *
   * @param keyword given as a tag
   */
  public void addKeywordFilter(Tag keyword) {
    addParameter(DatasetSearchParameter.KEYWORD, keyword.toString());
  }

  public void addPublishingOrgFilter(UUID orgKey) {
    addParameter(DatasetSearchParameter.PUBLISHING_ORG, orgKey.toString());
  }

  public void addSubTypeFilter(DatasetSubtype subtype) {
    addParameter(DatasetSearchParameter.SUBTYPE, subtype);
  }

  public void addTypeFilter(DatasetType type) {
    addParameter(DatasetSearchParameter.TYPE, type);
  }

  public void addProjectIdentifier(String identifier) {
    addParameter(DatasetSearchParameter.PROJECT_ID, identifier);
  }

}
