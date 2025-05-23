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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Hidden;

import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.FacetedSearchRequest;
import org.gbif.api.util.IsoDateInterval;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.MediaType;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.OccurrenceStatus;

/** Request class for issuing search request to the occurrence search service. */
public class OccurrenceSearchRequest extends FacetedSearchRequest<OccurrenceSearchParameter> {

  @Hidden
  private Boolean matchCase;

  @Hidden
  private String shuffle;

  /**
   * This flag enables the use of case-sensitive matches and aggregations on certain search
   * parameters.
   *
   * <p>Fields that support this feature are: occurrenceId, recordedBy, samplingProtocol,
   * catalogNumber, collectionCode, institutionCode, eventId, parentEventId, waterBody,
   * stateProvince, recordNumber, identifiedBy, organismId and locality.
   *
   * <p>This is an experimental feature, and its implementation may change or be removed at any time.
   *
   * <p>Be aware that this is not a per-field flag, all possible fields will match case sensitively.
   */
  @Experimental
  public Boolean isMatchCase() {
    return Optional.ofNullable(matchCase).orElse(Boolean.FALSE);
  }

  @Experimental
  public void setMatchCase(Boolean matchCase) {
    this.matchCase = matchCase;
  }

  /**
   * This flag allows to sort the results in a random order by specifying a seed. The seed makes the
   * random results reproducible so we can use paging. The same seed has to be sent for each page.
   */
  @Experimental
  public String getShuffle() {
    return shuffle;
  }

  @Experimental
  public void setShuffle(String shuffle) {
    this.shuffle = shuffle;
  }

  public OccurrenceSearchRequest(Pageable page) {
    super(page);
  }

  public OccurrenceSearchRequest() {
  }

  public void addOccurrenceIDFilter(String occurrenceID) {
    addParameter(OccurrenceSearchParameter.OCCURRENCE_ID, occurrenceID);
  }

  public void addEstablishmentMeansFilter(String establishmentMeans) {
    addParameter(OccurrenceSearchParameter.ESTABLISHMENT_MEANS, establishmentMeans);
  }

  public void addBasisOfRecordFilter(BasisOfRecord basisOfRecord) {
    addParameter(OccurrenceSearchParameter.BASIS_OF_RECORD, basisOfRecord);
  }

  public void addTypeStatusFilter(String typeStatus) {
    addParameter(OccurrenceSearchParameter.TYPE_STATUS, typeStatus);
  }

  public void addCatalogNumberFilter(String catalogNumber) {
    addParameter(OccurrenceSearchParameter.CATALOG_NUMBER, catalogNumber);
  }

  public void addRecordedByFilter(String collectorName) {
    addParameter(OccurrenceSearchParameter.RECORDED_BY, collectorName);
  }

  public void addIdentifiedByFilter(String collectorName) {
    addParameter(OccurrenceSearchParameter.IDENTIFIED_BY, collectorName);
  }

  public void addRecordNumberFilter(String recordNumber) {
    addParameter(OccurrenceSearchParameter.RECORD_NUMBER, recordNumber);
  }

  public void addCountryFilter(Country country) {
    addParameter(OccurrenceSearchParameter.COUNTRY, country.getIso2LetterCode());
  }

  public void addContinentFilter(Continent continent) {
    addParameter(OccurrenceSearchParameter.CONTINENT, continent);
  }

  public void addDatasetKeyFilter(UUID datasetKey) {
    addParameter(OccurrenceSearchParameter.DATASET_KEY, datasetKey.toString());
  }

  public void addGeometryFilter(String geometryAsWkt) {
    addParameter(OccurrenceSearchParameter.GEOMETRY, geometryAsWkt);
  }

  public void addDecimalLatitudeFilter(double latitude) {
    addParameter(OccurrenceSearchParameter.DECIMAL_LATITUDE, latitude);
  }

  public void addDecimalLongitudeFilter(double longitude) {
    addParameter(OccurrenceSearchParameter.DECIMAL_LONGITUDE, longitude);
  }

  public void addCoordinateUncertaintyInMetersFilter(double uncertainty) {
    addParameter(OccurrenceSearchParameter.COORDINATE_UNCERTAINTY_IN_METERS, uncertainty);
  }

  public void addMonthFilter(int month) {
    addParameter(OccurrenceSearchParameter.MONTH, month);
  }

  public void addDayFilter(int day) {
    addParameter(OccurrenceSearchParameter.DAY, day);
  }

  public void addTaxonKeyFilter(String taxonKey) {
    addParameter(OccurrenceSearchParameter.TAXON_KEY, taxonKey);
  }

  public void addKingdomKeyFilter(String kingdomKey) {
    addParameter(OccurrenceSearchParameter.KINGDOM_KEY, kingdomKey);
  }

  public void addPhylumKeyFilter(String phylumKey) {
    addParameter(OccurrenceSearchParameter.PHYLUM_KEY, phylumKey);
  }

  public void addClassKeyFilter(String classKey) {
    addParameter(OccurrenceSearchParameter.CLASS_KEY, classKey);
  }

  public void addOrderKeyFilter(String orderKey) {
    addParameter(OccurrenceSearchParameter.ORDER_KEY, orderKey);
  }

  public void addFamilyKeyFilter(String familyKey) {
    addParameter(OccurrenceSearchParameter.FAMILY_KEY, familyKey);
  }

  public void addGenusKeyFilter(String genusKey) {
    addParameter(OccurrenceSearchParameter.GENUS_KEY, genusKey);
  }

  public void addSubGenusKeyFilter(String subGenusKey) {
    addParameter(OccurrenceSearchParameter.SUBGENUS_KEY, subGenusKey);
  }

  public void addSpeciesKeyFilter(String speciesKey) {
    addParameter(OccurrenceSearchParameter.SPECIES_KEY, speciesKey);
  }

  public void addYearFilter(int year) {
    addParameter(OccurrenceSearchParameter.YEAR, year);
  }

  public void addEventDateFilter(IsoDateInterval date) {
    addParameter(OccurrenceSearchParameter.EVENT_DATE, date);
  }

  public void addLastInterpretedFilter(Date modified) {
    addParameter(OccurrenceSearchParameter.LAST_INTERPRETED, modified);
  }

  public void addPublishingCountryFilter(Country country) {
    addParameter(OccurrenceSearchParameter.PUBLISHING_COUNTRY, country.getIso2LetterCode());
  }

  public void addInstitutionCodeFilter(String code) {
    addParameter(OccurrenceSearchParameter.INSTITUTION_CODE, code);
  }

  public void addHasCoordinateFilter(boolean hasCoordinate) {
    addParameter(OccurrenceSearchParameter.HAS_COORDINATE, hasCoordinate);
  }

  public void addSpatialIssueFilter(boolean hasSpatialIssue) {
    addParameter(OccurrenceSearchParameter.HAS_GEOSPATIAL_ISSUE, hasSpatialIssue);
  }

  public void addIssueFilter(OccurrenceIssue issue) {
    addParameter(OccurrenceSearchParameter.ISSUE, issue);
  }

  public void addElevationFilter(double elevation) {
    addParameter(OccurrenceSearchParameter.ELEVATION, elevation);
  }

  public void addMediaTypeFilter(MediaType mediaType) {
    addParameter(OccurrenceSearchParameter.MEDIA_TYPE, mediaType);
  }

  public void addRecordedByIdsFilter(String recordedByIds) {
    addParameter(OccurrenceSearchParameter.RECORDED_BY_ID, recordedByIds);
  }

  public void addIdentifiedByIdsFilter(String identifiedByIds) {
    addParameter(OccurrenceSearchParameter.IDENTIFIED_BY_ID, identifiedByIds);
  }

  public void addOccurrenceStatusFilter(OccurrenceStatus occurrenceStatus) {
    addParameter(OccurrenceSearchParameter.OCCURRENCE_STATUS, occurrenceStatus);
  }

  public void addGadmGidFilter(String gadmGid) {
    addParameter(OccurrenceSearchParameter.GADM_GID, gadmGid);
  }

  public void addGadmLevel0GidFilter(String gadm0) {
    addParameter(OccurrenceSearchParameter.GADM_LEVEL_0_GID, gadm0);
  }

  public void addGadmLevel1GidFilter(String gadm1) {
    addParameter(OccurrenceSearchParameter.GADM_LEVEL_1_GID, gadm1);
  }

  public void addGadmLevel2GidFilter(String gadm2) {
    addParameter(OccurrenceSearchParameter.GADM_LEVEL_2_GID, gadm2);
  }

  public void addGadmLevel3GidFilter(String gadm3) {
    addParameter(OccurrenceSearchParameter.GADM_LEVEL_3_GID, gadm3);
  }

  public void addGeologicalTimeFilter(String geologicalTimeConcept) {
    addParameter(OccurrenceSearchParameter.GEOLOGICAL_TIME, geologicalTimeConcept);
  }

  public void addLithostratigraphyFilter(String lithostratigraphy) {
    addParameter(OccurrenceSearchParameter.LITHOSTRATIGRAPHY, lithostratigraphy);
  }

  public void addBiostratigraphyFilter(String biostratigraphy) {
    addParameter(OccurrenceSearchParameter.BIOSTRATIGRAPHY, biostratigraphy);
  }

  @Experimental
  public void addInstitutionKeyFilter(String institutionKey) {
    addParameter(OccurrenceSearchParameter.INSTITUTION_KEY, institutionKey);
  }

  @Experimental
  public void addCollectionKeyFilter(String collectionKey) {
    addParameter(OccurrenceSearchParameter.COLLECTION_KEY, collectionKey);
  }

  public void addDnaSequenceIDFilter(String dnaSequenceID) {
    addParameter(OccurrenceSearchParameter.DNA_SEQUENCE_ID, dnaSequenceID);
  }

  @Experimental
  public void addChecklistKeyFilter(String checklistKey) {
    addParameter(OccurrenceSearchParameter.CHECKLIST_KEY, checklistKey);
  }

  @Experimental
  public void addTaxonomicIssueFilter(String taxonomicIssue) {
    addParameter(OccurrenceSearchParameter.TAXONOMIC_ISSUE, taxonomicIssue);
  }
}
