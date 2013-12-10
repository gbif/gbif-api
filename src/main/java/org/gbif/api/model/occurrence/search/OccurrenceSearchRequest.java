/*
 * Copyright 2012 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.occurrence.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.SearchRequest;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Country;

import java.util.Date;
import java.util.UUID;


/**
 * Request class for issuing search request to the occurrence search service.
 */
public class OccurrenceSearchRequest extends SearchRequest<OccurrenceSearchParameter> {

  /**
   * Default constructor.
   */
  public OccurrenceSearchRequest() {
    // empty block
    super();
  }

  /**
   * @see PagingRequest#PagingRequest(long, int).
   */
  public OccurrenceSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * @see PagingRequest#PagingRequest(Pageable).
   */
  public OccurrenceSearchRequest(Pageable page) {
    super(page);
  }

  public void addBasisOfRecordFilter(BasisOfRecord basisOfRecord) {
    addParameter(OccurrenceSearchParameter.BASIS_OF_RECORD, basisOfRecord);
  }

  public void addCatalogNumberFilter(String catalogNumber) {
    addParameter(OccurrenceSearchParameter.CATALOG_NUMBER, catalogNumber);
  }

  public void addCollectorNameFilter(String collectorName) {
    addParameter(OccurrenceSearchParameter.COLLECTOR_NAME, collectorName);
  }

  public void addCountryFilter(Country country) {
    addParameter(OccurrenceSearchParameter.COUNTRY, country);
  }

  public void addDatasetFilter(UUID datasetKey) {
    addParameter(OccurrenceSearchParameter.DATASET_KEY, datasetKey.toString());
  }

  public void addGeometryFilter(String geometryAsWkt) {
    addParameter(OccurrenceSearchParameter.GEOMETRY, geometryAsWkt);
  }

  public void addLatitudeFilter(double latitude) {
    addParameter(OccurrenceSearchParameter.LATITUDE, latitude);
  }

  public void addLongitudeFilter(double longitude) {
    addParameter(OccurrenceSearchParameter.LONGITUDE, longitude);
  }

  public void addMonthFilter(int month) {
    addParameter(OccurrenceSearchParameter.MONTH, month);
  }

  public void addTaxonKeyFilter(int taxonKey) {
    addParameter(OccurrenceSearchParameter.TAXON_KEY, taxonKey);
  }

  public void addYearFilter(int year) {
    addParameter(OccurrenceSearchParameter.YEAR, year);
  }

  public void addDateFilter(Date date) {
    addParameter(OccurrenceSearchParameter.DATE, date);
  }

  public void addModifiedFilter(Date modified) {
    addParameter(OccurrenceSearchParameter.MODIFIED, modified);
  }

  public void addPublishingCountryFilter(Country country) {
    addParameter(OccurrenceSearchParameter.PUBLISHING_COUNTRY, country);
  }

  public void addInstitutionCodeFilter(String code) {
    addParameter(OccurrenceSearchParameter.INSTITUTION_CODE, code);
  }

  public void addGeoreferencedFilter(boolean isGeoreferenced) {
    addParameter(OccurrenceSearchParameter.GEOREFERENCED, isGeoreferenced);
  }

  public void addSpatialIssueFilter(boolean hasSpatialIssue) {
    addParameter(OccurrenceSearchParameter.SPATIAL_ISSUES, hasSpatialIssue);
  }

  public void addAltitudeFilter(int altitude) {
    addParameter(OccurrenceSearchParameter.ALTITUDE, altitude);
  }

}
