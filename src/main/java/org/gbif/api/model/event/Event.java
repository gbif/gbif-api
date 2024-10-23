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
package org.gbif.api.model.event;

import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.Identifier;
import org.gbif.api.model.common.MediaObject;
import org.gbif.api.model.occurrence.AgentIdentifier;
import org.gbif.api.model.occurrence.Gadm;
import org.gbif.api.model.occurrence.MeasurementOrFact;
import org.gbif.api.model.occurrence.Occurrence;
import org.gbif.api.model.occurrence.OccurrenceRelation;
import org.gbif.api.model.occurrence.VerbatimOccurrence;
import org.gbif.api.util.IsoDateInterval;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.License;
import org.gbif.api.vocabulary.OccurrenceIssue;
import org.gbif.api.vocabulary.OccurrenceStatus;
import org.gbif.api.vocabulary.Sex;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.UnknownTerm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Event class based on https://dwc.tdwg.org/terms/#event.
 */
@Data
public class Event extends VerbatimOccurrence {

  public static final String GEO_DATUM = "WGS84";

  // keep names of ALL properties of this class in a set for jackson serialization, see #properties()
  private static final Set<String> PROPERTIES = Collections.unmodifiableSet(
    Stream.concat(
      // we need to these json properties manually because we have a fixed getter but no field for it
      Stream.of(DwcTerm.geodeticDatum.simpleName(), "class", "countryCode"),
      Stream.concat(Arrays.stream(Event.class.getDeclaredFields()),
                    Arrays.stream(VerbatimOccurrence.class.getDeclaredFields()))
        .filter(field -> !Modifier.isStatic(field.getModifiers()))
        .map(Field::getName)).collect(Collectors.toSet()));

  public static Event fromOccurrence(Occurrence occurrence) {
    Event event =new Event();
    event.setKey(occurrence.getKey());
    event.setDatasetKey(occurrence.getDatasetKey());
    event.setPublishingOrgKey(occurrence.getPublishingOrgKey());
    event.setNetworkKeys(occurrence.getNetworkKeys());
    event.setInstallationKey(occurrence.getInstallationKey());
    event.setPublishingCountry(occurrence.getPublishingCountry());
    event.setProtocol(occurrence.getProtocol());
    event.setLastCrawled(occurrence.getLastCrawled());
    event.setLastParsed(occurrence.getLastParsed());
    event.setCrawlId(occurrence.getCrawlId());
    event.setProjectId(occurrence.getProjectId());
    event.setProgrammeAcronym(occurrence.getProgrammeAcronym());
    event.setHostingOrganizationKey(occurrence.getHostingOrganizationKey());
    event.setVerbatimFields(occurrence.getVerbatimFields());
    event.setExtensions(occurrence.getExtensions());
    event.setBasisOfRecord(occurrence.getBasisOfRecord());
    event.setIndividualCount(occurrence.getIndividualCount());
    event.setOccurrenceStatus(occurrence.getOccurrenceStatus());
    event.setSex(occurrence.getSex());
    event.setLifeStage(occurrence.getLifeStage());
    event.setEstablishmentMeans(occurrence.getEstablishmentMeans());
    event.setDegreeOfEstablishment(occurrence.getDegreeOfEstablishment());
    event.setPathway(occurrence.getPathway());
    event.setDateIdentified(occurrence.getDateIdentified());
    event.setDecimalLongitude(occurrence.getDecimalLongitude());
    event.setDecimalLatitude(occurrence.getDecimalLatitude());
    event.setCoordinatePrecision(occurrence.getCoordinatePrecision());
    event.setCoordinateUncertaintyInMeters(occurrence.getCoordinateUncertaintyInMeters());
    event.setCoordinateAccuracy(occurrence.getCoordinateAccuracy());
    event.setElevation(occurrence.getElevation());
    event.setElevationAccuracy(occurrence.getElevationAccuracy());
    event.setDepth(occurrence.getDepth());
    event.setDepthAccuracy(occurrence.getDepthAccuracy());
    event.setContinent(occurrence.getContinent());
    event.setCountry(occurrence.getCountry());
    event.setStateProvince(occurrence.getStateProvince());
    event.setWaterBody(occurrence.getWaterBody());
    event.setDistanceFromCentroidInMeters(occurrence.getDistanceFromCentroidInMeters());
    event.setYear(occurrence.getYear());
    event.setMonth(occurrence.getMonth());
    event.setDay(occurrence.getDay());
    event.setEventDate(occurrence.getEventDate());
    event.setIssues(occurrence.getIssues());
    event.setModified(occurrence.getModified());
    event.setLastInterpreted(occurrence.getLastInterpreted());
    event.setReferences(occurrence.getReferences());
    event.setLicense(occurrence.getLicense());
    event.setOrganismQuantity(occurrence.getOrganismQuantity());
    event.setOrganismQuantityType(occurrence.getOrganismQuantityType());
    event.setSampleSizeUnit(occurrence.getSampleSizeUnit());
    event.setSampleSizeValue(occurrence.getSampleSizeValue());
    event.setRelativeOrganismQuantity(occurrence.getRelativeOrganismQuantity());
    event.setIdentifiers(occurrence.getIdentifiers());
    event.setMedia(occurrence.getMedia());
    event.setFacts(occurrence.getFacts());
    event.setRelations(occurrence.getRelations());
    event.setRecordedByIds(occurrence.getRecordedByIds());
    event.setIdentifiedByIds(occurrence.getIdentifiedByIds());
    event.setGadm(occurrence.getGadm());
    event.setInstitutionKey(occurrence.getInstitutionKey());
    event.setCollectionKey(occurrence.getCollectionKey());
    event.setInCluster(occurrence.getIsInCluster());
    event.setDatasetID(occurrence.getDatasetID());
    event.setDatasetName(occurrence.getDatasetName());
    event.setOtherCatalogNumbers(occurrence.getOtherCatalogNumbers());
    event.setRecordedBy(occurrence.getRecordedBy());
    event.setIdentifiedBy(occurrence.getIdentifiedBy());
    event.setPreparations(occurrence.getPreparations());
    event.setSamplingProtocol(occurrence.getSamplingProtocol());
    return event;
  }

  @Data
  @AllArgsConstructor
  public static class ParentLineage {
    private String id;
    private String eventType;
  }

  @Data
  @AllArgsConstructor
  public static class VocabularyConcept {
    private String concept;
    private Set<String> lineage;
  }

  private String id;
  private Set<String> samplingProtocols;
  private String eventID;
  private String parentEventID;
  private Integer startDayOfYear;
  private Integer endDayOfYear;
  private String locationID;
  private VocabularyConcept eventType;
  private List<ParentLineage> parentsLineage;

  // occurrence fields
  private BasisOfRecord basisOfRecord;
  private Integer individualCount;
  private OccurrenceStatus occurrenceStatus;
  private String sex;
  private String lifeStage;
  private String establishmentMeans;
  private String degreeOfEstablishment;
  private String pathway;

  // identification
  private Date dateIdentified;
  // location
  private Double decimalLongitude;
  private Double decimalLatitude;

  //coordinatePrecision and coordinateUncertaintyInMeters should be BigDecimal see POR-2795
  private Double coordinatePrecision;
  private Double coordinateUncertaintyInMeters;
  @Deprecated //see getter
  private Double coordinateAccuracy;

  private Double elevation;
  private Double elevationAccuracy;
  private Double depth;
  private Double depthAccuracy;
  private Continent continent;
  @JsonSerialize(using = Country.IsoSerializer.class)
  @JsonDeserialize(using = Country.IsoDeserializer.class)
  private Country country;
  private String stateProvince;
  private String waterBody;
  private Double distanceFromCentroidInMeters;

  // recording event
  private Integer year;
  private Integer month;
  private Integer day;
  private IsoDateInterval eventDate;

  private Set<OccurrenceIssue> issues = EnumSet.noneOf(OccurrenceIssue.class);

  // record level
  private Date modified;  // interpreted dc:modified, i.e. date changed in source
  private Date lastInterpreted;
  private URI references;
  private License license;
  private Double organismQuantity;
  private String organismQuantityType;
  private String sampleSizeUnit;
  private Double sampleSizeValue;
  private Double relativeOrganismQuantity;

  // interpreted extension data
  private List<Identifier> identifiers = new ArrayList<>();
  private List<MediaObject> media = new ArrayList<>();
  private List<MeasurementOrFact> facts = new ArrayList<>();
  private List<OccurrenceRelation> relations = new ArrayList<>();
  @JsonProperty("recordedByIDs")
  private List<AgentIdentifier> recordedByIds = new ArrayList<>();
  @JsonProperty("identifiedByIDs")
  private List<AgentIdentifier> identifiedByIds = new ArrayList<>();
  private Gadm gadm = new Gadm();
  @Experimental
  private String institutionKey;
  @Experimental
  private String collectionKey;
  private boolean isInCluster;
  private String datasetID;
  private String datasetName;
  private String otherCatalogNumbers;
  private String recordedBy;
  private String identifiedBy;
  private String preparations;
  private String samplingProtocol;

  /**
   * Convenience method checking if any spatial validation rule has not passed.
   * Primarily used to indicate that the record should not be displayed on a map.
   */
  @JsonIgnore
  public boolean hasSpatialIssue() {
    for (OccurrenceIssue rule : OccurrenceIssue.GEOSPATIAL_RULES) {
      if (issues.contains(rule)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The geodetic datum for the interpreted decimal coordinates.
   * This is always WGS84 if there a coordinate exists as we reproject other datums into WGS84.
   */
  @Nullable
  public String getGeodeticDatum() {
    if (decimalLatitude != null) {
      return GEO_DATUM;
    }
    return null;
  }

  /**
   * This private method is only for serialization via jackson and not exposed anywhere else!
   * It maps the verbatimField terms into properties with their simple name or qualified names for UnknownTerms.
   */
  @JsonAnyGetter
  private Map<String, String> jsonVerbatimFields() {
    Map<String, String> extendedProps = new HashMap<>();
    for (Map.Entry<Term, String> prop : getVerbatimFields().entrySet()) {
      Term t = prop.getKey();
      if (t instanceof UnknownTerm || PROPERTIES.contains(t.simpleName())) {
        extendedProps.put(t.qualifiedName(), prop.getValue());
      } else {
        // render all terms in controlled enumerations as simple names only - unless we have a property of that name already!
        extendedProps.put(t.simpleName(), prop.getValue());
      }
    }
    return extendedProps;
  }
}
