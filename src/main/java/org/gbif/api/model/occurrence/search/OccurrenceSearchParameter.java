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
package org.gbif.api.model.occurrence.search;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.util.IsoDateInterval;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MediaType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.OccurrenceStatus;
import org.gbif.api.vocabulary.Sex;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.TypeStatus;

import java.util.Date;
import java.util.UUID;

/**
 * Supported query parameters by the occurrence search and download service.
 * For download predicates only the numerical types support comparisons other than equals.
 */
public enum OccurrenceSearchParameter implements SearchParameter {

  /**
   * The dataset key as a UUID.
   */
  DATASET_KEY(UUID.class),

  /**
   * The 4 digit year. A year of 98 will be 98 common era, not 1998.
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
   * The earliest integer day of the year on which the event occurred (1 for January 1, 365 for December 31, except in a
   * leap year, in which case it is 366).
   */
  START_DAY_OF_YEAR(Integer.class),

  /**
   * The latest integer day of the year on which the event occurred (1 for January 1, 365 for December 31, except in a
   * leap year, in which case it is 366).
   */
  END_DAY_OF_YEAR(Integer.class),

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
  EVENT_DATE(IsoDateInterval.class),

  /**
   * Lower limit for the range of the event date (date the occurrence was recorded).
   *
   * Included for backward compatibility as it mimicks the previous search behaviour:
   * https://github.com/gbif/occurrence/issues/346
   */
  @Deprecated
  EVENT_DATE_GTE(Date.class),

  /**
   * An identifier for the set of information associated with an Event (something that occurs at a place and time).
   * Maybe a global unique identifier or an identifier specific to the data set.
   */
  EVENT_ID(String.class),

  /**
   * An identifier for the broader Event that groups this and potentially other Events.
   */
  PARENT_EVENT_ID(String.class),

  /**
   * The name of, reference to, or description of the method or protocol used during an Event.
   */
  SAMPLING_PROTOCOL(String.class),

  /**
   * A list (concatenated and separated) of previous assignments of names to the organism.
   */
  PREVIOUS_IDENTIFICATIONS(String.class),

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
   * Modified date in ISO 8601 formats:yyyy, yyyy-MM, yyyy-MM-dd and MM-dd.
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
  MODIFIED(Date.class),

  /**
   * Latitude in decimals between -90 and 90 based on WGS 84.
   */
  DECIMAL_LATITUDE(Double.class),

  /**
   * Longitude in decimals between -180 and 180 based on WGS 84.
   */
  DECIMAL_LONGITUDE(Double.class),

  /**
   * The uncertainty of the coordinate in meters.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>*,100</dt>
   * <dd>Uncertainty below or equals 100m</dd>
   * <dt>10000,*</dt>
   * <dd>Uncertainty above or equals 10,000m</dd>
   * <dt>1000,5000</dt>
   * <dd>Uncertainty between or equals 1000m and 5000m
   * </dd>
   * </dl>
   */
  COORDINATE_UNCERTAINTY_IN_METERS(Double.class),

  /**
   * Country the occurrence was recorded in.
   */
  COUNTRY(Country.class),

  /**
   * GBIF region based on country
   */
  GBIF_REGION(GbifRegion.class),

  /**
   * Continent the occurrence was recorded in.
   */
  CONTINENT(Continent.class),

  /**
   * The country of the organization that publishes the dataset the occurrence belongs to.
   */
  PUBLISHING_COUNTRY(Country.class),

  /**
   * GBIF region based on publishibg country
   */
  PUBLISHED_BY_GBIF_REGION(GbifRegion.class),

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
   * The person who identified the occurrence.
   */
  IDENTIFIED_BY(String.class),

  /**
   * An identifier given to the Occurrence at the time it was recorded.
   */
  RECORD_NUMBER(String.class),

  /**
   * A basis of record enumeration value.
   */
  BASIS_OF_RECORD(BasisOfRecord.class),

  /**
   * The sex of the biological individual(s) represented in the occurrence.
   */
  SEX(String.class),

  /**
   * Presents of associated sequences or an extension
   */
  IS_SEQUENCED(Boolean.class),

  /**
   * A taxon key from the GBIF backbone. All included and synonym taxa are included in the search, so a search for
   * aves with taxonKey=212 will match all birds, no matter which species.
   */
  TAXON_KEY(Integer.class),

  /**
   * A taxon key from the GBIF backbone for the name usage of the currently valid or accepted taxon.
   */
  ACCEPTED_TAXON_KEY(Integer.class),

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
   * Scientific name as provided byt the source.
   */
  VERBATIM_SCIENTIFIC_NAME(String.class),

  /**
   * Verbatim identifier for the set of taxon information. Maybe a global unique identifier or an identifier specific to
   * the data set.
   */
  TAXON_ID(String.class),

  /**
   * An identifier for the taxonomic concept to which the record refers - not for the nomenclatural details of a taxon.
   */
  TAXON_CONCEPT_ID(String.class),

  /**
   * The status of the use of the  GBIF Backbone taxonKey.
   */
  TAXONOMIC_STATUS(TaxonomicStatus.class),

  /**
   * Searches for occurrence records which contain a value on its coordinate fields (latitude and longitude).
   * HAS_COORDINATE=true searches for occurrence records with a coordinate value.
   * HAS_COORDINATE=false searches for occurrence records without a coordinate value.
   */
  HAS_COORDINATE(Boolean.class),

  /**
   * Geometry in <a href="https://en.wikipedia.org/wiki/Well-known_text">Well Known Text</a> (WKT) format.
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
   * Use in combination of LATITUDE and LONGITUDE parameters matches within a given distance.
   * E.g.: geo_distance=100m,40,90 geo_distance=100km,40,90 geo_distance=100mi,40,90.
   * See supported units in {@link org.gbif.api.model.occurrence.geo.DistanceUnit}.
   */
  GEO_DISTANCE(String.class),

  /**
   * The distance from a known centroid, e.g. a country centroid.
   */
  DISTANCE_FROM_CENTROID_IN_METERS(Double.class),

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
  TYPE_STATUS(String.class),

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
  ESTABLISHMENT_MEANS(String.class),

  /**
   * Provides the controlled vocabulary for information about degree to which an Organism survives, reproduces, and expands its range at the given place and time.
   */
  DEGREE_OF_ESTABLISHMENT(String.class),

  /**
   * Provides the controlled vocabulary for information about the process by which an Organism came to be in a given place at a given time.
   * The pathway of an organism or organisms have been introduced to a given place and time.
   */
  PATHWAY(String.class),

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
   * The GBIF network that the publishing organisation belongs to.
   */
  NETWORK_KEY(UUID.class),

  /**
   * The technical installation key that hosts/publishes this record.
   */
  INSTALLATION_KEY(UUID.class),

  /**
   * The organization key of the installation that hosts this record.
   */
  HOSTING_ORGANIZATION_KEY(UUID.class),

  /**
   * Crawl attempt that harvested this record.
   */
  CRAWL_ID(Integer.class),

  /**
   * GBIF ProjectId.
   */
  PROJECT_ID(String.class),

  /**
   * GBIF Programme Acronym.
   */
  PROGRAMME(String.class),

  /**
   * A number or enumeration value for the quantity of organisms.
   */
  ORGANISM_QUANTITY(Double.class),

  /**
   * The type of quantification system used for the quantity of organisms.
   */
  ORGANISM_QUANTITY_TYPE(String.class),

  /**
   * The unit of measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  SAMPLE_SIZE_UNIT(String.class),

  /**
   * A numeric value for a measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  SAMPLE_SIZE_VALUE(Double.class),

  /**
   * Calculated organismQuantity relative to the sampleSizeValue i.e. -> organismQuantity / sampleSizeValue.
   */
  RELATIVE_ORGANISM_QUANTITY(Double.class),

  /**
   * Collection key. It links to the collection to which this record belongs.
   */
  COLLECTION_KEY(String.class),

  /**
   * Institution key. It links to the institution that maintains, recorded or digitized  this record.
   */
  INSTITUTION_KEY(String.class),

  /**
   * Agent identifiers from GbifTerm.recordedByID
   */
  RECORDED_BY_ID(String.class),

  /**
   * Agent identifiers from GbifTerm.identifiedByID
   */
  IDENTIFIED_BY_ID(String.class),

  /**
   * An occurrence status enumeration value.
   */
  OCCURRENCE_STATUS(OccurrenceStatus.class),

  /**
   * A <a href="https://gadm.org">GADM</a> identifier at any level.
   */
  GADM_GID(String.class),

  /**
   * A <a href="https://gadm.org">GADM</a> country, island or territory (level zero) identifier.
   */
  GADM_LEVEL_0_GID(String.class),

  /**
   * A <a href="https://gadm.org">GADM</a> first-level identifier.
   */
  GADM_LEVEL_1_GID(String.class),

  /**
   * A <a href="https://gadm.org">GADM</a> second-level identifier.
   */
  GADM_LEVEL_2_GID(String.class),

  /**
   * A <a href="https://gadm.org">GADM</a> third-level identifier.
   */
  GADM_LEVEL_3_GID(String.class),

  /**
   * The life stage of an occurrence.
   */
  LIFE_STAGE(String.class),

  /**
   * Searches for occurrences that are clustered.
   */
  IS_IN_CLUSTER(Boolean.class),

  /**
   * Searches for occurrences that have a particular DwC-A extension.
   */
  DWCA_EXTENSION(String.class),

  /**
   * Searches for occurrences that have a IUCN Red List Category.
   */
  IUCN_RED_LIST_CATEGORY(String.class),

  /**
   * The dwc dataset id.
   */
  DATASET_ID(String.class),

  /**
   * The dwc dataset name.
   */
  DATASET_NAME(String.class),

  /**
   * Other catalog numbers associated to an occurrence.
   */
  OTHER_CATALOG_NUMBERS(String.class),

  /**
   * Preparations methods of an occurrence.
   */
  PREPARATIONS(String.class),

  /**
   * The name of the island on or near which the location occurs.
   */
  ISLAND(String.class),

  /**
   * The name of the island group in which the location occurs.
   */
  ISLAND_GROUP(String.class),

  /**
   * A list (concatenated and separated) of names of people, groups, or organizations who determined the georeference
   * (spatial representation) for the location.
   */
  GEOREFERENCED_BY(String.class),

  /**
   * A list (concatenated and separated) of geographic names less specific than the information captured in the locality
   * term.
   */
  HIGHER_GEOGRAPHY(String.class),

  /**
   * 	An identifier given to the event in the field. Often serves as a link between field notes and the event.
   */
  FIELD_NUMBER(String.class),

  /**
   * The full name of the earliest possible geochronologic eon or lowest chrono-stratigraphic eonothem or the informal
   * name ("Precambrian") attributable to the stratigraphic horizon from which the MaterialEntity was collected.
   */
  EARLIEST_EON_OR_LOWEST_EONOTHEM(String.class),

  /**
   * The full name of the latest possible geochronologic eon or highest chrono-stratigraphic eonothem or the informal
   * name ("Precambrian") attributable to the stratigraphic horizon from which the MaterialEntity was collected.
   */
  LATEST_EON_OR_HIGHEST_EONOTHEM(String.class),

  /**
   * The full name of the earliest possible geochronologic era or lowest chronostratigraphic erathem attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  EARLIEST_ERA_OR_LOWEST_ERATHEM(String.class),

  /**
   * The full name of the latest possible geochronologic era or highest chronostratigraphic erathem attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  LATEST_ERA_OR_HIGHEST_ERATHEM(String.class),

  /**
   * The full name of the earliest possible geochronologic period or lowest chronostratigraphic system attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  EARLIEST_PERIOD_OR_LOWEST_SYSTEM(String.class),

  /**
   * The full name of the latest possible geochronologic period or highest chronostratigraphic system attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  LATEST_PERIOD_OR_HIGHEST_SYSTEM(String.class),

  /**
   * The full name of the earliest possible geochronologic epoch or lowest chronostratigraphic series attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  EARLIEST_EPOCH_OR_LOWEST_SERIES(String.class),

  /**
   * The full name of the latest possible geochronologic epoch or highest chronostratigraphic series attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  LATEST_EPOCH_OR_HIGHEST_SERIES(String.class),

  /**
   * The full name of the earliest possible geochronologic age or lowest chronostratigraphic stage attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  EARLIEST_AGE_OR_LOWEST_STAGE(String.class),

  /**
   * The full name of the latest possible geochronologic age or highest chronostratigraphic stage attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  LATEST_AGE_OR_HIGHEST_STAGE(String.class),

  /**
   * The full name of the lowest possible geological biostratigraphic zone of the stratigraphic horizon from which the
   * MaterialEntity was collected.
   */
  LOWEST_BIOSTRATIGRAPHIC_ZONE(String.class),

  /**
   * The full name of the highest possible geological biostratigraphic zone of the stratigraphic horizon from which the
   * MaterialEntity was collected.
   */
  HIGHEST_BIOSTRATIGRAPHIC_ZONE(String.class),

  /**
   * The full name of the lithostratigraphic group from which the MaterialEntity was collected.
   */
  GROUP(String.class),

  /**
   * The full name of the lithostratigraphic formation from which the MaterialEntity was collected.
   */
  FORMATION(String.class),

  /**
   * The full name of the lithostratigraphic member from which the MaterialEntity was collected.
   */
  MEMBER(String.class),

  /**
   * The full name of the lithostratigraphic bed from which the MaterialEntity was collected.
   */
  BED(String.class),

  /**
   * A list (concatenated and separated) of identifiers (publication, global unique identifier, URI) of
   * genetic sequence information associated with the material entity.
   */
  ASSOCIATED_SEQUENCES(String.class),

  /**
   * Unique GBIF key for the occurrence.
   */
  GBIF_ID(String.class),

  /**
   * Geological time of an occurrence that searchs in the chronostratigraphy fields.
   */
  GEOLOGICAL_TIME(String.class),

  /**
   * Searchs in the lithostratigraphy fields(bed, formation, group, member).
   */
  LITHOSTRATIGRAPHY(String.class),

  /**
   * Searchs in the biostratigraphy fields(lowest and highest biostratigraphy).
   */
  BIOSTRATIGRAPHY(String.class);

  private final Class<?> type;

  OccurrenceSearchParameter(Class<?> type) {
    this.type = type;
  }

  /**
   * @return the data type expected for the value of the respective search parameter
   */
  @Override
  public Class<?> type() {
    return type;
  }
}
