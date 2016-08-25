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
package org.gbif.api.model.occurrence.search;


import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.EstablishmentMeans;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MediaType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.TypeStatus;

import java.util.Date;
import java.util.UUID;

/**
 * Supported query parameters by the occurrence search and download service.
 * For download predicates only the numerical types support comparisons other than equals.
 */
public enum OccurrenceSearchParameter implements SearchParameter {

  /**
   * The dataset key as a uuid.
   */
  DATASET_KEY(UUID.class),

  /**
   * The 4 digit year. A year of 98 will be 98 after christ, no 1998.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>*,1810</dt>
   * <dd>Found before or equal to 1810</dd>
   * <dt>1848,1933</dt>
   * <dd>Year between 1848 and 1933</dd>
   * </dl>
   */
  YEAR(Integer.class),

  /**
   * The month of the year, starting with 1 for January.
   * A month query can be used to retrieve seasonal records when used without a year or a year range.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>4,8</dt>
   * <dd>Month between April and August</dd>
   * </dl>
   */
  MONTH(Integer.class),


  /**
   * Event date (date the occurrence was recorded) in ISO 8601 formats:yyyy, yyyy-MM, yyyy-MM-dd and MM-dd.
   * This parameter accepts comma separated range values, examples of valid ranges are:
   * <dl>
   * <dt>2001-02-11,2010-01-10</dt>
   * <dd>Dates between 2001-02-11 and 2010-01-10</dd>
   * <dt>2001-02,2010-01</dt>
   * <dd>Dates between first day of 2001-02 and last day of 2010-01</dd>
   * <dt>2001,2010</dt>
   * <dd>Dates between first day of 2001 and last day of 2010</dd>
   * <dt>2001,2010-01</dt>
   * <dd>Dates between first day of 2001 and last day of 2010-01</dd>
   * <dt>2001-01-10,2010</dt>
   * <dd>Dates between 2001-01-10 and last day of 2010</dd>
   * <dt>2001-01-10,*</dt>
   * <dd>Dates after 2001-01-10</dd>
   * <dt>*,2001-01-10</dt>
   * <dd>Dates before 2001-01-10</dd>
   * <dt>*</dt>
   * <dd>all dates</dd>
   * </dl>
   */
  EVENT_DATE(Date.class),

  /**
   * Last interpreted date in ISO 8601 formats:yyyy, yyyy-MM, yyyy-MM-dd and MM-dd.
   * This parameter accepts comma separated range values, examples of valid ranges are:
   * <dl>
   * <dt>2001-02-11,2010-01-10</dt>
   * <dd>Dates between 2001-02-11 and 2010-01-10</dd>
   * <dt>2001-02,2010-01</dt>
   * <dd>Dates between first day of 2001-02 and last day of 2010-01</dd>
   * <dt>2001,2010</dt>
   * <dd>Dates between first day of 2001 and last day of 2010</dd>
   * <dt>2001,2010-01</dt>
   * <dd>Dates between first day of 2001 and last day of 2010-01</dd>
   * <dt>2001-01-10,2010</dt>
   * <dd>Dates between 2001-01-10 and last day of 2010</dd>
   * <dt>2001-01-10,*</dt>
   * <dd>Dates after 2001-01-10</dd>
   * <dt>*,2001-01-10</dt>
   * <dd>Dates before 2001-01-10</dd>
   * <dt>*</dt>
   * <dd>all dates</dd>
   * </dl>
   */
  LAST_INTERPRETED(Date.class),

  /**
   * Latitude in decimals between -90 and 90 based on WGS 84.
   */
  DECIMAL_LATITUDE(Double.class),

  /**
   * Longitude in decimals between -180 and 180 based on WGS 84.
   */
  DECIMAL_LONGITUDE(Double.class),

  /**
   * Country the occurrence was recorded in.
   */
  COUNTRY(Country.class),

  /**
   * Continent the occurrence was recorded in.
   */
  CONTINENT(Continent.class),

  /**
   * The country of the organization that publishes the dataset the occurrence belongs to.
   */
  PUBLISHING_COUNTRY(Country.class),

  /**
   * Altitude/elevation in meters above sea level.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>*,100</dt>
   * <dd>Altitude below or equals 100m</dd>
   * <dt>100,*</dt>
   * <dd>Altitude above or equals 100m</dd>
   * <dt>-2,8.8</dt>
   * <dd>Altitude between or equals -2m and 8.8m</dd>
   * </dl>
   */
  ELEVATION(Double.class),

  /**
   * Depth in meters relative to altitude. For example 10 meters below a lake surface with given altitude.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>*,10</dt>
   * <dd>Depth below or equals 10m</dd>
   * <dt>100,*</dt>
   * <dd>Depth above or equals 100m</dd>
   * <dt>12.1,28.8</dt>
   * <dd>Depth between or equals 12.1m and 28.8m</dd>
   * </dl>
   */
  DEPTH(Double.class),

  /**
   * An identifier of any form assigned by the source to identify the institution
   * the record belongs to. Not guaranteed to be unique.
   */
  INSTITUTION_CODE(String.class),

  /**
   * An identifier of any form assigned by the source to identify the physical collection or digital dataset
   * uniquely within the context of an institution.
   */
  COLLECTION_CODE(String.class),

  /**
   * An identifier of any form assigned by the source within a physical collection or digital dataset for the record
   * which may not be unique, but should be fairly unique in combination with the institution and collection code.
   */
  CATALOG_NUMBER(String.class),

  /**
   * The person who recorded the occurrence.
   */
  RECORDED_BY(String.class),

  /**
   * An identifier given to the Occurrence at the time it was recorded.
   */
  RECORD_NUMBER(String.class),

  /**
   * A basis of record enumeration value.
   */
  BASIS_OF_RECORD(BasisOfRecord.class),

  /**
   * A taxon key from the GBIF backbone. All included and synonym taxa are included in the search, so a search for
   * aves with taxonKey=212 will match all birds, no matter which species.
   */
  TAXON_KEY(Integer.class),

  /**
   * A kingdom key from the GBIF backbone.
   */
  KINGDOM_KEY(Integer.class),

  /**
   * A phylum key from the GBIF backbone.
   */
  PHYLUM_KEY(Integer.class),

  /**
   * A class key from the GBIF backbone.
   */
  CLASS_KEY(Integer.class),

  /**
   * A order key from the GBIF backbone.
   */
  ORDER_KEY(Integer.class),

  /**
   * A family key from the GBIF backbone.
   */
  FAMILY_KEY(Integer.class),

  /**
   * A genus key from the GBIF backbone.
   */
  GENUS_KEY(Integer.class),

  /**
   * A subgenus key from the GBIF backbone.
   */
  SUBGENUS_KEY(Integer.class),

  /**
   * A species key from the GBIF backbone.
   */
  SPECIES_KEY(Integer.class),

  /**
   * Searches the interpreted, full scientific name of the occurrence.
   */
  SCIENTIFIC_NAME(String.class),

  /**
   * Searches for occurrence records which contain a value on its coordinate fields (latitude and longitude).
   * HAS_COORDINATE=true searches for occurrence records with a coordinate value.
   * HAS_COORDINATE=false searches for occurrence records without a coordinate value.
   */
  HAS_COORDINATE(Boolean.class),


  /**
   * Geometry in <a href="http://en.wikipedia.org/wiki/Well-known_text">Well Known Text</a> (WKT) format.
   * E.g.: POLYGON ((30.0 10.0, 10.12 20.23, 20 40, 40 40, 30 10)).
   * Multi geometries like MULTIPOLYGON are not supported and multiple parameters should be used instead.
   * Valid geometries are:
   * <ul>
   * <li>POINT</li>
   * <li>LINESTRING</li>
   * <li>POLYGON</li>
   * <li>LINEARRING</li>
   * </ul>
   */
  GEOMETRY(String.class),


  /**
   * Includes/excludes occurrence records which contain geospatial issues for their coordinate.
   * See {@link org.gbif.api.vocabulary.OccurrenceIssue#GEOSPATIAL_RULES}
   * HAS_GEOSPATIAL_ISSUE=true include records with spatial issues.
   * HAS_GEOSPATIAL_ISSUE=false exclude records with spatial issues.
   * The absence of this parameter returns any record with or without spatial issues.
   */
  HAS_GEOSPATIAL_ISSUE(Boolean.class),

  /**
   * Searches occurrence for those that have a specific issue.
   */
  ISSUE(OccurrenceIssue.class),

  /**
   * Nomenclatural type (type status, typified scientific name, publication) applied to the subject.
   */
  TYPE_STATUS(TypeStatus.class),


  /**
   * The kind of media object.
   * Recommended terms from the DCMI Type Vocabulary are StillImage, Sound or MovingImage for GBIF to index and show the
   * media files.
   */
  MEDIA_TYPE(MediaType.class),

  /**
   *  An identifier for the Occurrence (as opposed to a particular digital record of the occurrence).
   *  In the absence of a persistent global unique identifier, construct one from a combination of identifiers in the
   *  record that will most closely make the occurrenceID globally unique.
   */
  OCCURRENCE_ID(String.class),

  /**
   * The process by which the biological individual(s) represented in the Occurrence became established at the location.
   */
  ESTABLISHMENT_MEANS(EstablishmentMeans.class),

  /**
   * Searches for records whose publishing country is different to the country where the record was recorded in.
   */
  REPATRIATED(Boolean.class),

  /**
   * An identifier for the Organism instance (as opposed to a particular digital record of the Organism).
   * May be a globally unique identifier or an identifier specific to the data set.
   */
  ORGANISM_ID(String.class),

  /**
   * The name of the next smaller administrative region than country in which the Location occurs.
   */
  STATE_PROVINCE(String.class),

  /**
   * The name of the water body in which the Location occurs.
   */
  WATER_BODY(String.class),

  /**
   * The specific description of the place.
   * It may contain information modified from the original to correct perceived errors or standardize the description.
   */
  LOCALITY(String.class),

  /**
   * Protocol used to provide the occurrence record.
   */
  PROTOCOL(EndpointType.class),

  /**
   * The license applied to the dataset.
   */
  LICENSE(License.class),

  /**
   * The owning organizations uuid key.
   */
  PUBLISHING_ORG(UUID.class),

  /**
   * Crawl attempt that harvested this record.
   */
  CRAWL_ID(UUID.class);

  private final Class<?> type;

  private OccurrenceSearchParameter(Class<?> type) {
    this.type = type;
  }

  /**
   * @return the data type expected for the value of the respective search parameter
   */
  public Class<?> type() {
    return type;
  }

}
