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
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.util.IsoDateInterval;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DurationUnit;
import org.gbif.api.vocabulary.EndpointType;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.MediaType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.OccurrenceStatus;
import org.gbif.api.vocabulary.TaxonomicStatus;

/**
 * Supported query parameters by the occurrence search and download service.
 * For download predicates only the numerical types support comparisons other than equals.
 */
@JsonDeserialize(as = OccurrenceSearchParameter.class, using = OccurrenceSearchParameter.OccurrenceSearchParameterDeserializer.class)
public class OccurrenceSearchParameter implements SearchParameter, Serializable {

  /**
   * The dataset key as a UUID.
   */
  public final static OccurrenceSearchParameter DATASET_KEY = new OccurrenceSearchParameter("DATASET_KEY", UUID.class);

  /**
   * The checklist key to use for taxonomy matching.
   */
  public final static OccurrenceSearchParameter CHECKLIST_KEY = new OccurrenceSearchParameter("CHECKLIST_KEY", String.class);

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
  public final static OccurrenceSearchParameter YEAR = new OccurrenceSearchParameter("YEAR", Integer.class);

  /**
   * The month of the year, starting with 1 for January.
   * A month query can be used to retrieve seasonal records when used without a year or a year range.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>4,8</dt>
   * <dd>Month between April and August</dd>
   * </dl>
   */
  public final static OccurrenceSearchParameter MONTH = new OccurrenceSearchParameter("MONTH", Integer.class);

  /**
   * The day of the month.
   * This parameter accepts comma separated range values, e.g.:
   * <dl>
   * <dt>24,28</dt>
   * <dd>Day of the month between 24 and 28</dd>
   * </dl>
   */
  public final static OccurrenceSearchParameter DAY = new OccurrenceSearchParameter("DAY", Integer.class);

  /**
   * The earliest integer day of the year on which the event occurred (1 for January 1, 365 for December 31, except in a
   * leap year, in which case it is 366).
   */
  public final static OccurrenceSearchParameter START_DAY_OF_YEAR = new OccurrenceSearchParameter("START_DAY_OF_YEAR", Integer.class);

  /**
   * The latest integer day of the year on which the event occurred (1 for January 1, 365 for December 31, except in a
   * leap year, in which case it is 366).
   */
  public final static OccurrenceSearchParameter END_DAY_OF_YEAR = new OccurrenceSearchParameter("END_DAY_OF_YEAR", Integer.class);

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
  public final static OccurrenceSearchParameter EVENT_DATE = new OccurrenceSearchParameter("EVENT_DATE", IsoDateInterval.class);

  /**
   * Lower limit for the range of the event date (date the occurrence was recorded).
   *
   * Included for backward compatibility as it mimicks the previous search behaviour:
   * https://github.com/gbif/occurrence/issues/346
   */
  @Deprecated
  public final static OccurrenceSearchParameter EVENT_DATE_GTE = new OccurrenceSearchParameter("EVENT_DATE_GTE", Date.class);

  /**
   * An identifier for the set of information associated with an Event (something that occurs at a place and time).
   * Maybe a global unique identifier or an identifier specific to the data set.
   */
  public final static OccurrenceSearchParameter EVENT_ID = new OccurrenceSearchParameter("EVENT_ID", String.class);

  /**
   * An identifier for the broader Event that groups this and potentially other Events.
   */
  public final static OccurrenceSearchParameter PARENT_EVENT_ID = new OccurrenceSearchParameter("PARENT_EVENT_ID", String.class);

  /**
   * An identifier for an Event and its children events.
   */
  public final static OccurrenceSearchParameter EVENT_ID_HIERARCHY = new OccurrenceSearchParameter("EVENT_ID_HIERARCHY", String.class);

  /**
   * An identifier for the vocabulary-backed Event Type.
   */
  public final static OccurrenceSearchParameter EVENT_TYPE = new OccurrenceSearchParameter("EVENT_TYPE", String.class);

  /**
   * An identifier for the verbatim Event Type.
   */
  @Experimental
  public final static OccurrenceSearchParameter VERBATIM_EVENT_TYPE = new OccurrenceSearchParameter("VERBATIM_EVENT_TYPE", String.class);

  /**
   * The name of, reference to, or description of the method or protocol used during an Event.
   */
  public final static OccurrenceSearchParameter SAMPLING_PROTOCOL = new OccurrenceSearchParameter("SAMPLING_PROTOCOL", String.class);

  /**
   * A list (concatenated and separated) of previous assignments of names to the organism.
   */
  public final static OccurrenceSearchParameter PREVIOUS_IDENTIFICATIONS = new OccurrenceSearchParameter("PREVIOUS_IDENTIFICATIONS", String.class);

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
  public final static OccurrenceSearchParameter LAST_INTERPRETED = new OccurrenceSearchParameter("LAST_INTERPRETED", Date.class);


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
  public final static OccurrenceSearchParameter MODIFIED = new OccurrenceSearchParameter("MODIFIED", Date.class);

  /**
   * Latitude in decimals between -90 and 90 based on WGS 84.
   */
  public final static OccurrenceSearchParameter DECIMAL_LATITUDE = new OccurrenceSearchParameter("DECIMAL_LATITUDE", Double.class);

  /**
   * Longitude in decimals between -180 and 180 based on WGS 84.
   */
  public final static OccurrenceSearchParameter DECIMAL_LONGITUDE = new OccurrenceSearchParameter("DECIMAL_LONGITUDE", Double.class);

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
  public final static OccurrenceSearchParameter COORDINATE_UNCERTAINTY_IN_METERS = new OccurrenceSearchParameter("COORDINATE_UNCERTAINTY_IN_METERS", Double.class);

  /**
   * Country the occurrence was recorded in.
   */
  public final static OccurrenceSearchParameter COUNTRY = new OccurrenceSearchParameter("COUNTRY", Country.class);

  /**
   * GBIF region based on country
   */
  public final static OccurrenceSearchParameter GBIF_REGION = new OccurrenceSearchParameter("GBIF_REGION", GbifRegion.class);

  /**
   * Continent the occurrence was recorded in.
   */
  public final static OccurrenceSearchParameter CONTINENT = new OccurrenceSearchParameter("CONTINENT", Continent.class);

  /**
   * The country of the organization that publishes the dataset the occurrence belongs to.
   */
  public final static OccurrenceSearchParameter PUBLISHING_COUNTRY = new OccurrenceSearchParameter("PUBLISHING_COUNTRY", Country.class);

  /**
   * GBIF region based on publishibg country
   */
  public final static OccurrenceSearchParameter PUBLISHED_BY_GBIF_REGION = new OccurrenceSearchParameter("PUBLISHED_BY_GBIF_REGION", GbifRegion.class);

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
  public final static OccurrenceSearchParameter ELEVATION = new OccurrenceSearchParameter("ELEVATION", Double.class);

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
  public final static OccurrenceSearchParameter DEPTH = new OccurrenceSearchParameter("DEPTH", Double.class);

  /**
   * An identifier of any form assigned by the source to identify the institution
   * the record belongs to. Not guaranteed to be unique.
   */
  public final static OccurrenceSearchParameter INSTITUTION_CODE = new OccurrenceSearchParameter("INSTITUTION_CODE", String.class);

  /**
   * An identifier of any form assigned by the source to identify the physical collection or digital dataset
   * uniquely within the context of an institution.
   */
  public final static OccurrenceSearchParameter COLLECTION_CODE = new OccurrenceSearchParameter("COLLECTION_CODE", String.class);

  /**
   * An identifier of any form assigned by the source within a physical collection or digital dataset for the record
   * which may not be unique, but should be fairly unique in combination with the institution and collection code.
   */
  public final static OccurrenceSearchParameter CATALOG_NUMBER = new OccurrenceSearchParameter("CATALOG_NUMBER", String.class);

  /**
   * The person who recorded the occurrence.
   */
  public final static OccurrenceSearchParameter RECORDED_BY = new OccurrenceSearchParameter("RECORDED_BY", String.class);

  /**
   * The person who identified the occurrence.
   */
  public final static OccurrenceSearchParameter IDENTIFIED_BY = new OccurrenceSearchParameter("IDENTIFIED_BY", String.class);

  /**
   * An identifier given to the Occurrence at the time it was recorded.
   */
  public final static OccurrenceSearchParameter RECORD_NUMBER = new OccurrenceSearchParameter("RECORD_NUMBER", String.class);

  /**
   * A basis of record enumeration value.
   */
  public final static OccurrenceSearchParameter BASIS_OF_RECORD = new OccurrenceSearchParameter("BASIS_OF_RECORD", BasisOfRecord.class);

  /**
   * The sex of the biological individual(s) represented in the occurrence.
   */
  public final static OccurrenceSearchParameter SEX = new OccurrenceSearchParameter("SEX", String.class);

  /**
   * Presents of associated sequences or an extension
   */
  public final static OccurrenceSearchParameter IS_SEQUENCED = new OccurrenceSearchParameter("IS_SEQUENCED", Boolean.class);

  /**
   * A taxon key from the GBIF backbone. All included and synonym taxa are included in the search, so a search for
   * aves with taxonKey=212 will match all birds, no matter which species.
   */
  public final static OccurrenceSearchParameter TAXON_KEY = new OccurrenceSearchParameter("TAXON_KEY", String.class);

  /**
   * A taxon key from the GBIF backbone for the name usage of the currently valid or accepted taxon.
   */
  public final static OccurrenceSearchParameter ACCEPTED_TAXON_KEY = new OccurrenceSearchParameter("ACCEPTED_TAXON_KEY", String.class);

  /**
   * A kingdom key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter KINGDOM_KEY = new OccurrenceSearchParameter("KINGDOM_KEY", String.class);

  /**
   * A phylum key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter PHYLUM_KEY = new OccurrenceSearchParameter("PHYLUM_KEY", String.class);

  /**
   * A class key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter CLASS_KEY = new OccurrenceSearchParameter("CLASS_KEY", String.class);

  /**
   * A order key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter ORDER_KEY = new OccurrenceSearchParameter("ORDER_KEY", String.class);

  /**
   * A family key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter FAMILY_KEY = new OccurrenceSearchParameter("FAMILY_KEY", String.class);

  /**
   * A genus key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter GENUS_KEY = new OccurrenceSearchParameter("GENUS_KEY", String.class);

  /**
   * A subgenus key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter SUBGENUS_KEY = new OccurrenceSearchParameter("SUBGENUS_KEY", String.class);

  /**
   * A species key from the GBIF backbone.
   */
  public final static OccurrenceSearchParameter SPECIES_KEY = new OccurrenceSearchParameter("SPECIES_KEY", String.class);

  /**
   * Searches the interpreted, full scientific name of the occurrence.
   */
  public final static OccurrenceSearchParameter SCIENTIFIC_NAME = new OccurrenceSearchParameter("SCIENTIFIC_NAME", String.class);

  /**
   * Scientific name as provided byt the source.
   */
  public final static OccurrenceSearchParameter VERBATIM_SCIENTIFIC_NAME = new OccurrenceSearchParameter("VERBATIM_SCIENTIFIC_NAME", String.class);

  /**
   * Verbatim identifier for the set of taxon information. Maybe a global unique identifier or an identifier specific to
   * the data set.
   */
  public final static OccurrenceSearchParameter TAXON_ID = new OccurrenceSearchParameter("TAXON_ID", String.class);

  /**
   * An identifier for the taxonomic concept to which the record refers - not for the nomenclatural details of a taxon.
   */
  public final static OccurrenceSearchParameter TAXON_CONCEPT_ID = new OccurrenceSearchParameter("TAXON_CONCEPT_ID", String.class);

  /**
   * The status of the use of the  GBIF Backbone taxonKey.
   */
  public final static OccurrenceSearchParameter TAXONOMIC_STATUS = new OccurrenceSearchParameter("TAXONOMIC_STATUS", TaxonomicStatus.class);

  /**
   * Searches for occurrence records which contain a value on its coordinate fields (latitude and longitude).
   * HAS_COORDINATE=true searches for occurrence records with a coordinate value.
   * HAS_COORDINATE=false searches for occurrence records without a coordinate value.
   */
  public final static OccurrenceSearchParameter HAS_COORDINATE = new OccurrenceSearchParameter("HAS_COORDINATE", Boolean.class);

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
  public final static OccurrenceSearchParameter GEOMETRY = new OccurrenceSearchParameter("GEOMETRY", String.class);

  /**
   * Use in combination of LATITUDE and LONGITUDE parameters matches within a given distance.
   * E.g.: geo_distance=100m,40,90 geo_distance=100km,40,90 geo_distance=100mi,40,90.
   * See supported units in {@link org.gbif.api.model.occurrence.geo.DistanceUnit}.
   */
  public final static OccurrenceSearchParameter GEO_DISTANCE = new OccurrenceSearchParameter("GEO_DISTANCE", String.class);

  /**
   * The distance from a known centroid, e.g. a country centroid.
   */
  public final static OccurrenceSearchParameter DISTANCE_FROM_CENTROID_IN_METERS = new OccurrenceSearchParameter("DISTANCE_FROM_CENTROID_IN_METERS", Double.class);

  /**
   * Includes/excludes occurrence records which contain geospatial issues for their coordinate.
   * See {@link org.gbif.api.vocabulary.OccurrenceIssue#GEOSPATIAL_RULES}
   * HAS_GEOSPATIAL_ISSUE=true include records with spatial issues.
   * HAS_GEOSPATIAL_ISSUE=false exclude records with spatial issues.
   * The absence of this parameter returns any record with or without spatial issues.
   */
  public final static OccurrenceSearchParameter HAS_GEOSPATIAL_ISSUE = new OccurrenceSearchParameter("HAS_GEOSPATIAL_ISSUE", Boolean.class);

  /**
   * Searches occurrence for those that have a specific issue.
   */
  public final static OccurrenceSearchParameter ISSUE = new OccurrenceSearchParameter("ISSUE", OccurrenceIssue.class);

  /**
   * Searches occurrence for those that have a specific taxonomic issue. This is separated out from the general issue
   * field as taxonomic issues will be tied to a specific checklist.
   */
  public final static OccurrenceSearchParameter TAXONOMIC_ISSUE = new OccurrenceSearchParameter("TAXONOMIC_ISSUE", String.class);

  /**
   * Nomenclatural type (type status, typified scientific name, publication) applied to the subject.
   */
  public final static OccurrenceSearchParameter TYPE_STATUS = new OccurrenceSearchParameter("TYPE_STATUS", String.class);

  /**
   * The kind of media object.
   * Recommended terms from the DCMI Type Vocabulary are StillImage, Sound or MovingImage for GBIF to index and show the
   * media files.
   */
  public final static OccurrenceSearchParameter MEDIA_TYPE = new OccurrenceSearchParameter("MEDIA_TYPE", MediaType.class);

  /**
   *  An identifier for the Occurrence (as opposed to a particular digital record of the occurrence).
   *  In the absence of a persistent global unique identifier, construct one from a combination of identifiers in the
   *  record that will most closely make the occurrenceID globally unique.
   */
  public final static OccurrenceSearchParameter OCCURRENCE_ID = new OccurrenceSearchParameter("OCCURRENCE_ID", String.class);

  /**
   * The process by which the biological individual(s) represented in the Occurrence became established at the location.
   */
  public final static OccurrenceSearchParameter ESTABLISHMENT_MEANS = new OccurrenceSearchParameter("ESTABLISHMENT_MEANS", String.class);

  /**
   * Provides the controlled vocabulary for information about degree to which an Organism survives, reproduces, and expands its range at the given place and time.
   */
  public final static OccurrenceSearchParameter DEGREE_OF_ESTABLISHMENT = new OccurrenceSearchParameter("DEGREE_OF_ESTABLISHMENT", String.class);

  /**
   * Provides the controlled vocabulary for information about the process by which an Organism came to be in a given place at a given time.
   * The pathway of an organism or organisms have been introduced to a given place and time.
   */
  public final static OccurrenceSearchParameter PATHWAY = new OccurrenceSearchParameter("PATHWAY", String.class);

  /**
   * Searches for records whose publishing country is different to the country where the record was recorded in.
   */
  public final static OccurrenceSearchParameter REPATRIATED = new OccurrenceSearchParameter("REPATRIATED", Boolean.class);

  /**
   * An identifier for the Organism instance (as opposed to a particular digital record of the Organism).
   * May be a globally unique identifier or an identifier specific to the data set.
   */
  public final static OccurrenceSearchParameter ORGANISM_ID = new OccurrenceSearchParameter("ORGANISM_ID", String.class);

  /**
   * The name of the next smaller administrative region than country in which the Location occurs.
   */
  public final static OccurrenceSearchParameter STATE_PROVINCE = new OccurrenceSearchParameter("STATE_PROVINCE", String.class);

  /**
   * The name of the water body in which the Location occurs.
   */
  public final static OccurrenceSearchParameter WATER_BODY = new OccurrenceSearchParameter("WATER_BODY", String.class);

  /**
   * The specific description of the place.
   * It may contain information modified from the original to correct perceived errors or standardize the description.
   */
  public final static OccurrenceSearchParameter LOCALITY = new OccurrenceSearchParameter("LOCALITY", String.class);

  /**
   * Protocol used to provide the occurrence record.
   */
  public final static OccurrenceSearchParameter PROTOCOL = new OccurrenceSearchParameter("PROTOCOL", EndpointType.class);

  /**
   * The license applied to the dataset.
   */
  public final static OccurrenceSearchParameter LICENSE = new OccurrenceSearchParameter("LICENSE", License.class);

  /**
   * The owning organizations uuid key.
   */
  public final static OccurrenceSearchParameter PUBLISHING_ORG = new OccurrenceSearchParameter("PUBLISHING_ORG", UUID.class);

  /**
   * The GBIF network that the publishing organisation belongs to.
   */
  public final static OccurrenceSearchParameter NETWORK_KEY = new OccurrenceSearchParameter("NETWORK_KEY", UUID.class);

  /**
   * The technical installation key that hosts/publishes this record.
   */
  public final static OccurrenceSearchParameter INSTALLATION_KEY = new OccurrenceSearchParameter("INSTALLATION_KEY", UUID.class);

  /**
   * The organization key of the installation that hosts this record.
   */
  public final static OccurrenceSearchParameter HOSTING_ORGANIZATION_KEY = new OccurrenceSearchParameter("HOSTING_ORGANIZATION_KEY", UUID.class);

  /**
   * Crawl attempt that harvested this record.
   */
  public final static OccurrenceSearchParameter CRAWL_ID = new OccurrenceSearchParameter("CRAWL_ID", Integer.class);

  /**
   * GBIF ProjectId.
   */
  public final static OccurrenceSearchParameter PROJECT_ID = new OccurrenceSearchParameter("PROJECT_ID", String.class);

  /**
   * GBIF Programme Acronym.
   */
  public final static OccurrenceSearchParameter PROGRAMME = new OccurrenceSearchParameter("PROGRAMME", String.class);

  /**
   * A number or enumeration value for the quantity of organisms.
   */
  public final static OccurrenceSearchParameter ORGANISM_QUANTITY = new OccurrenceSearchParameter("ORGANISM_QUANTITY", Double.class);

  /**
   * The type of quantification system used for the quantity of organisms.
   */
  public final static OccurrenceSearchParameter ORGANISM_QUANTITY_TYPE = new OccurrenceSearchParameter("ORGANISM_QUANTITY_TYPE", String.class);

  /**
   * The unit of measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  public final static OccurrenceSearchParameter SAMPLE_SIZE_UNIT = new OccurrenceSearchParameter("SAMPLE_SIZE_UNIT", String.class);

  /**
   * A numeric value for a measurement of the size (time duration, length, area, or volume) of a sample in a sampling event.
   */
  public final static OccurrenceSearchParameter SAMPLE_SIZE_VALUE = new OccurrenceSearchParameter("SAMPLE_SIZE_VALUE", Double.class);

  /**
   * Calculated organismQuantity relative to the sampleSizeValue i.e. -> organismQuantity / sampleSizeValue.
   */
  public final static OccurrenceSearchParameter RELATIVE_ORGANISM_QUANTITY = new OccurrenceSearchParameter("RELATIVE_ORGANISM_QUANTITY", Double.class);

  /**
   * Collection key. It links to the collection to which this record belongs.
   */
  public final static OccurrenceSearchParameter COLLECTION_KEY = new OccurrenceSearchParameter("COLLECTION_KEY", String.class);

  /**
   * Institution key. It links to the institution that maintains, recorded or digitized  this record.
   */
  public final static OccurrenceSearchParameter INSTITUTION_KEY = new OccurrenceSearchParameter("INSTITUTION_KEY", String.class);

  /**
   * Agent identifiers from GbifTerm.recordedByID
   */
  public final static OccurrenceSearchParameter RECORDED_BY_ID = new OccurrenceSearchParameter("RECORDED_BY_ID", String.class);

  /**
   * Agent identifiers from GbifTerm.identifiedByID
   */
  public final static OccurrenceSearchParameter IDENTIFIED_BY_ID = new OccurrenceSearchParameter("IDENTIFIED_BY_ID", String.class);

  /**
   * An occurrence status enumeration value.
   */
  public final static OccurrenceSearchParameter OCCURRENCE_STATUS = new OccurrenceSearchParameter("OCCURRENCE_STATUS", OccurrenceStatus.class);

  /**
   * A <a href="https://gadm.org">GADM</a> identifier at any level.
   */
  public final static OccurrenceSearchParameter GADM_GID = new OccurrenceSearchParameter("GADM_GID", String.class);

  /**
   * A <a href="https://gadm.org">GADM</a> country, island or territory (level zero) identifier.
   */
  public final static OccurrenceSearchParameter GADM_LEVEL_0_GID = new OccurrenceSearchParameter("GADM_LEVEL_0_GID", String.class);

  /**
   * A <a href="https://gadm.org">GADM</a> first-level identifier.
   */
  public final static OccurrenceSearchParameter GADM_LEVEL_1_GID = new OccurrenceSearchParameter("GADM_LEVEL_1_GID", String.class);

  /**
   * A <a href="https://gadm.org">GADM</a> second-level identifier.
   */
  public final static OccurrenceSearchParameter GADM_LEVEL_2_GID = new OccurrenceSearchParameter("GADM_LEVEL_2_GID", String.class);

  /**
   * A <a href="https://gadm.org">GADM</a> third-level identifier.
   */
  public final static OccurrenceSearchParameter GADM_LEVEL_3_GID = new OccurrenceSearchParameter("GADM_LEVEL_3_GID", String.class);

  /**
   * The life stage of an occurrence.
   */
  public final static OccurrenceSearchParameter   LIFE_STAGE = new OccurrenceSearchParameter("LIFE_STAGE", String.class);

  /**
   * Searches for occurrences that are clustered.
   */
  public final static OccurrenceSearchParameter IS_IN_CLUSTER = new OccurrenceSearchParameter("IS_IN_CLUSTER", Boolean.class);

  /**
   * Searches for occurrences that have a particular DwC-A extension.
   */
  public final static OccurrenceSearchParameter DWCA_EXTENSION = new OccurrenceSearchParameter("DWCA_EXTENSION", String.class);

  /**
   * Searches for occurrences that have a IUCN Red List Category.
   */
  public final static OccurrenceSearchParameter IUCN_RED_LIST_CATEGORY = new OccurrenceSearchParameter("IUCN_RED_LIST_CATEGORY", String.class);

  /**
   * The dwc dataset id.
   */
  public final static OccurrenceSearchParameter DATASET_ID = new OccurrenceSearchParameter("DATASET_ID", String.class);

  /**
   * The dwc dataset name.
   */
  public final static OccurrenceSearchParameter DATASET_NAME = new OccurrenceSearchParameter("DATASET_NAME", String.class);

  /**
   * Other catalog numbers associated to an occurrence.
   */
  public final static OccurrenceSearchParameter OTHER_CATALOG_NUMBERS = new OccurrenceSearchParameter("OTHER_CATALOG_NUMBERS", String.class);

  /**
   * Preparations methods of an occurrence.
   */
  public final static OccurrenceSearchParameter PREPARATIONS = new OccurrenceSearchParameter("PREPARATIONS", String.class);

  /**
   * The name of the island on or near which the location occurs.
   */
  public final static OccurrenceSearchParameter ISLAND = new OccurrenceSearchParameter("ISLAND", String.class);

  /**
   * The name of the island group in which the location occurs.
   */
  public final static OccurrenceSearchParameter ISLAND_GROUP = new OccurrenceSearchParameter("ISLAND_GROUP", String.class);

  /**
   * A list (concatenated and separated) of names of people, groups, or organizations who determined the georeference
   * (spatial representation) for the location.
   */
  public final static OccurrenceSearchParameter GEOREFERENCED_BY = new OccurrenceSearchParameter("GEOREFERENCED_BY", String.class);

  /**
   * A list (concatenated and separated) of geographic names less specific than the information captured in the locality
   * term.
   */
  public final static OccurrenceSearchParameter HIGHER_GEOGRAPHY = new OccurrenceSearchParameter("HIGHER_GEOGRAPHY", String.class);

  /**
   * 	An identifier given to the event in the field. Often serves as a link between field notes and the event.
   */
  public final static OccurrenceSearchParameter FIELD_NUMBER = new OccurrenceSearchParameter("FIELD_NUMBER", String.class);

  /**
   * The full name of the earliest possible geochronologic eon or lowest chrono-stratigraphic eonothem or the informal
   * name ("Precambrian") attributable to the stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter EARLIEST_EON_OR_LOWEST_EONOTHEM = new OccurrenceSearchParameter("EARLIEST_EON_OR_LOWEST_EONOTHEM", String.class);

  /**
   * The full name of the latest possible geochronologic eon or highest chrono-stratigraphic eonothem or the informal
   * name ("Precambrian") attributable to the stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LATEST_EON_OR_HIGHEST_EONOTHEM = new OccurrenceSearchParameter("LATEST_EON_OR_HIGHEST_EONOTHEM", String.class);

  /**
   * The full name of the earliest possible geochronologic era or lowest chronostratigraphic erathem attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter EARLIEST_ERA_OR_LOWEST_ERATHEM = new OccurrenceSearchParameter("EARLIEST_ERA_OR_LOWEST_ERATHEM", String.class);

  /**
   * The full name of the latest possible geochronologic era or highest chronostratigraphic erathem attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LATEST_ERA_OR_HIGHEST_ERATHEM = new OccurrenceSearchParameter("LATEST_ERA_OR_HIGHEST_ERATHEM", String.class);

  /**
   * The full name of the earliest possible geochronologic period or lowest chronostratigraphic system attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter EARLIEST_PERIOD_OR_LOWEST_SYSTEM = new OccurrenceSearchParameter("EARLIEST_PERIOD_OR_LOWEST_SYSTEM", String.class);

  /**
   * The full name of the latest possible geochronologic period or highest chronostratigraphic system attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LATEST_PERIOD_OR_HIGHEST_SYSTEM = new OccurrenceSearchParameter("LATEST_PERIOD_OR_HIGHEST_SYSTEM", String.class);

  /**
   * The full name of the earliest possible geochronologic epoch or lowest chronostratigraphic series attributable to
   * the stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter EARLIEST_EPOCH_OR_LOWEST_SERIES = new OccurrenceSearchParameter("EARLIEST_EPOCH_OR_LOWEST_SERIES", String.class);

  /**
   * The full name of the latest possible geochronologic epoch or highest chronostratigraphic series attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LATEST_EPOCH_OR_HIGHEST_SERIES = new OccurrenceSearchParameter("LATEST_EPOCH_OR_HIGHEST_SERIES", String.class);

  /**
   * The full name of the earliest possible geochronologic age or lowest chronostratigraphic stage attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter EARLIEST_AGE_OR_LOWEST_STAGE = new OccurrenceSearchParameter("EARLIEST_AGE_OR_LOWEST_STAGE", String.class);

  /**
   * The full name of the latest possible geochronologic age or highest chronostratigraphic stage attributable to the
   * stratigraphic horizon from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LATEST_AGE_OR_HIGHEST_STAGE = new OccurrenceSearchParameter("LATEST_AGE_OR_HIGHEST_STAGE", String.class);

  /**
   * The full name of the lowest possible geological biostratigraphic zone of the stratigraphic horizon from which the
   * MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter LOWEST_BIOSTRATIGRAPHIC_ZONE = new OccurrenceSearchParameter("LOWEST_BIOSTRATIGRAPHIC_ZONE", String.class);

  /**
   * The full name of the highest possible geological biostratigraphic zone of the stratigraphic horizon from which the
   * MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter HIGHEST_BIOSTRATIGRAPHIC_ZONE = new OccurrenceSearchParameter("HIGHEST_BIOSTRATIGRAPHIC_ZONE", String.class);

  /**
   * The full name of the lithostratigraphic group from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter GROUP = new OccurrenceSearchParameter("GROUP", String.class);

  /**
   * The full name of the lithostratigraphic formation from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter FORMATION = new OccurrenceSearchParameter("FORMATION", String.class);

  /**
   * The full name of the lithostratigraphic member from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter MEMBER = new OccurrenceSearchParameter("MEMBER", String.class);

  /**
   * The full name of the lithostratigraphic bed from which the MaterialEntity was collected.
   */
  public final static OccurrenceSearchParameter BED = new OccurrenceSearchParameter("BED", String.class);

  /**
   * A list (concatenated and separated) of identifiers (publication, global unique identifier, URI) of
   * genetic sequence information associated with the material entity.
   */
  public final static OccurrenceSearchParameter ASSOCIATED_SEQUENCES = new OccurrenceSearchParameter("ASSOCIATED_SEQUENCES", String.class);

  /**
   * Unique GBIF key for the occurrence.
   */
  public final static OccurrenceSearchParameter GBIF_ID = new OccurrenceSearchParameter("GBIF_ID", String.class);

  /**
   * Geological time of an occurrence that searchs in the chronostratigraphy fields.
   */
  public final static OccurrenceSearchParameter GEOLOGICAL_TIME = new OccurrenceSearchParameter("GEOLOGICAL_TIME", String.class);

  /**
   * Searches in the lithostratigraphy fields(bed, formation, group, member).
   */
  public final static OccurrenceSearchParameter LITHOSTRATIGRAPHY = new OccurrenceSearchParameter("LITHOSTRATIGRAPHY", String.class);

  /**
   * Searches in the biostratigraphy fields(lowest and highest biostratigraphy).
   */
  public final static OccurrenceSearchParameter BIOSTRATIGRAPHY = new OccurrenceSearchParameter("BIOSTRATIGRAPHY", String.class);

  /**
   * @return the data type expected for the value of the respective search parameter
   */
  public final static OccurrenceSearchParameter DNA_SEQUENCE_ID = new OccurrenceSearchParameter("DNA_SEQUENCE_ID", String.class);

  /** Humboldt fields **/
  public final static OccurrenceSearchParameter HUMBOLDT_SITE_COUNT = new OccurrenceSearchParameter("HUMBOLDT_SITE_COUNT", Integer.class);
  public final static OccurrenceSearchParameter HUMBOLDT_VERBATIM_SITE_NAMES = new OccurrenceSearchParameter("HUMBOLDT_VERBATIM_SITE_NAMES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_GEOSPATIAL_SCOPE_AREA_VALUE = new OccurrenceSearchParameter("HUMBOLDT_GEOSPATIAL_SCOPE_AREA_VALUE", Double.class);
  public final static OccurrenceSearchParameter HUMBOLDT_GEOSPATIAL_SCOPE_AREA_UNIT = new OccurrenceSearchParameter("HUMBOLDT_GEOSPATIAL_SCOPE_AREA_UNIT", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TOTAL_AREA_SAMPLED_VALUE = new OccurrenceSearchParameter("HUMBOLDT_TOTAL_AREA_SAMPLED_VALUE", Double.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TOTAL_AREA_SAMPLED_UNIT = new OccurrenceSearchParameter("HUMBOLDT_TOTAL_AREA_SAMPLED_UNIT", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_HABITAT_SCOPE = new OccurrenceSearchParameter("HUMBOLDT_TARGET_HABITAT_SCOPE", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_EVENT_DURATION_VALUE_IN_MINUTES = new OccurrenceSearchParameter("HUMBOLDT_EVENT_DURATION_VALUE_IN_MINUTES", Double.class);
  public final static OccurrenceSearchParameter HUMBOLDT_EVENT_DURATION_VALUE = new OccurrenceSearchParameter("HUMBOLDT_EVENT_DURATION_VALUE", Double.class);
  public final static OccurrenceSearchParameter HUMBOLDT_EVENT_DURATION_UNIT = new OccurrenceSearchParameter("HUMBOLDT_EVENT_DURATION_UNIT", DurationUnit.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_NAME = new OccurrenceSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_NAME", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_KEY = new OccurrenceSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_KEY", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_TAXON_KEY = new OccurrenceSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_TAXON_KEY", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TAXON_COMPLETENESS_PROTOCOLS = new OccurrenceSearchParameter("HUMBOLDT_TAXON_COMPLETENESS_PROTOCOLS", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_TAXONOMIC_SCOPE_FULLY_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_TAXONOMIC_SCOPE_FULLY_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_ABSENCE_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_ABSENCE_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_HAS_NON_TARGET_TAXA = new OccurrenceSearchParameter("HUMBOLDT_HAS_NON_TARGET_TAXA", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_ARE_NON_TARGET_TAXA_FULLY_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_ARE_NON_TARGET_TAXA_FULLY_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_LIFE_STAGE_SCOPE = new OccurrenceSearchParameter("HUMBOLDT_TARGET_LIFE_STAGE_SCOPE", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_LIFE_STAGE_SCOPE_FULLY_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_LIFE_STAGE_SCOPE_FULLY_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_DEGREE_OF_ESTABLISHMENT_SCOPE = new OccurrenceSearchParameter("HUMBOLDT_TARGET_DEGREE_OF_ESTABLISHMENT_SCOPE", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_DEGREE_OF_ESTABLISHMENT_SCOPE_FULLY_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_DEGREE_OF_ESTABLISHMENT_SCOPE_FULLY_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_TARGET_GROWTH_FORM_SCOPE = new OccurrenceSearchParameter("HUMBOLDT_TARGET_GROWTH_FORM_SCOPE", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_GROWTH_FORM_SCOPE_FULLY_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_GROWTH_FORM_SCOPE_FULLY_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_HAS_NON_TARGET_ORGANISMS = new OccurrenceSearchParameter("HUMBOLDT_HAS_NON_TARGET_ORGANISMS", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_COMPILATION_TYPES = new OccurrenceSearchParameter("HUMBOLDT_COMPILATION_TYPES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_COMPILATION_SOURCE_TYPES = new OccurrenceSearchParameter("HUMBOLDT_COMPILATION_SOURCE_TYPES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_INVENTORY_TYPES = new OccurrenceSearchParameter("HUMBOLDT_INVENTORY_TYPES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_PROTOCOL_NAMES = new OccurrenceSearchParameter("HUMBOLDT_PROTOCOL_NAMES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_ABUNDANCE_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_ABUNDANCE_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_ABUNDANCE_CAP_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_ABUNDANCE_CAP_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_ABUNDANCE_CAP = new OccurrenceSearchParameter("HUMBOLDT_ABUNDANCE_CAP", Integer.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_VEGETATION_COVER_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_VEGETATION_COVER_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_LEAST_SPECIFIC_TARGET_CATEGORY_QUANTITY_INCLUSIVE = new OccurrenceSearchParameter("HUMBOLDT_IS_LEAST_SPECIFIC_TARGET_CATEGORY_QUANTITY_INCLUSIVE", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_HAS_VOUCHERS = new OccurrenceSearchParameter("HUMBOLDT_HAS_VOUCHERS", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_VOUCHER_INSTITUTIONS = new OccurrenceSearchParameter("HUMBOLDT_VOUCHER_INSTITUTIONS", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_HAS_MATERIAL_SAMPLES = new OccurrenceSearchParameter("HUMBOLDT_HAS_MATERIAL_SAMPLES", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_MATERIAL_SAMPLE_TYPES = new OccurrenceSearchParameter("HUMBOLDT_MATERIAL_SAMPLE_TYPES", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_SAMPLING_PERFORMED_BY = new OccurrenceSearchParameter("HUMBOLDT_SAMPLING_PERFORMED_BY", String.class);
  public final static OccurrenceSearchParameter HUMBOLDT_IS_SAMPLING_EFFORT_REPORTED = new OccurrenceSearchParameter("HUMBOLDT_IS_SAMPLING_EFFORT_REPORTED", Boolean.class);
  public final static OccurrenceSearchParameter HUMBOLDT_SAMPLING_EFFORT_VALUE = new OccurrenceSearchParameter("HUMBOLDT_SAMPLING_EFFORT_VALUE", Double.class);
  public final static OccurrenceSearchParameter HUMBOLDT_SAMPLING_EFFORT_UNIT = new OccurrenceSearchParameter("HUMBOLDT_SAMPLING_EFFORT_UNIT", String.class);

  public static OccurrenceSearchParameter[] values(){

    Field[] values = OccurrenceSearchParameter.class.getFields();
    List<OccurrenceSearchParameter> c = new ArrayList<>();
    for (Field field: values) {
      try {
        c.add((OccurrenceSearchParameter) field.get(OccurrenceSearchParameter.class));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return c.toArray(new OccurrenceSearchParameter[0]);
  }

  private Class<?> type;
  private String name;

  public OccurrenceSearchParameter() {}

  public OccurrenceSearchParameter(String name, Class<?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String name() {
    return this.name;
  }

  public Class<?> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public Class<?> type() {
    return this.type;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OccurrenceSearchParameter that = (OccurrenceSearchParameter) o;
    return Objects.equals(type, that.type) && Objects.equals(name, that.name);
  }

  @JsonValue
  public String getSerializedValue() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name);
  }

  /**
   * Lookup a parameter by its name.
   * @param name the name of the parameter
   * @return the parameter if found, otherwise empty
   */
  public static Optional<OccurrenceSearchParameter> lookup(String name) {
    String normedType = name.toUpperCase().replaceAll("[. _-]", "");
    java.lang.reflect.Field[] values = OccurrenceSearchParameter.class.getFields();
    for (Field field: values) {

      String fieldName = field.getName();
      String normedVal = fieldName.replaceAll("[. _-]", "");
      if (normedType.equals(normedVal)) {
        try {
          return Optional.of((OccurrenceSearchParameter) field.get(OccurrenceSearchParameter.class));
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return Optional.empty();
  }

  public static class OccurrenceSearchParameterKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String value, DeserializationContext deserializationContext) throws IOException {
      Field[] values = OccurrenceSearchParameter.class.getFields();
      try {
        for (Field field: values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (OccurrenceSearchParameter) field.get(OccurrenceSearchParameter.class);
          }
        }
      } catch (IllegalAccessException e) {
        // DO NOTHING
      }
      return null;
    }
  }

  public static class OccurrenceSearchParameterDeserializer extends JsonDeserializer<OccurrenceSearchParameter> {

    @Override
    public OccurrenceSearchParameter deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws IOException, JacksonException {

      Field[] values = OccurrenceSearchParameter.class.getFields();
      try {
        String value = jsonParser.getText();
        for (Field field: values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (OccurrenceSearchParameter) field.get(OccurrenceSearchParameter.class);
          }
        }

      } catch (IllegalAccessException e) {
        // DO NOTHING
      }

      try {
        ObjectNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.get("name").asText();
        for (Field field: values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (OccurrenceSearchParameter) field.get(OccurrenceSearchParameter.class);
          }
        }
      } catch (Exception e) {
        // DO NOTHING
      }

      return null;
    }
  }
}
