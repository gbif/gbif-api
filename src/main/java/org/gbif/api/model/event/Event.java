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

import org.gbif.api.model.occurrence.Occurrence;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Event class based on https://dwc.tdwg.org/terms/#event.
 */
@Data
public class Event extends Occurrence {

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
    event.setTaxonKey(occurrence.getTaxonKey());
    event.setKingdomKey(occurrence.getKingdomKey());
    event.setPhylumKey(occurrence.getPhylumKey());
    event.setClassKey(occurrence.getClassKey());
    event.setOrderKey(occurrence.getOrderKey());
    event.setFamilyKey(occurrence.getFamilyKey());
    event.setGenusKey(occurrence.getGenusKey());
    event.setSubgenusKey(occurrence.getSubgenusKey());
    event.setSpeciesKey(occurrence.getSpeciesKey());
    event.setAcceptedTaxonKey(occurrence.getAcceptedTaxonKey());
    event.setScientificName(occurrence.getScientificName());
    event.setAcceptedScientificName(occurrence.getAcceptedScientificName());
    event.setKingdom(occurrence.getKingdom());
    event.setPhylum(occurrence.getPhylum());
    event.setClazz(occurrence.getClazz());
    event.setOrder(occurrence.getOrder());
    event.setFamily(occurrence.getFamily());
    event.setGenus(occurrence.getGenus());
    event.setSubgenus(occurrence.getSubgenus());
    event.setSpecies(occurrence.getSpecies());
    event.setGenericName(occurrence.getGenericName());
    event.setSpecificEpithet(occurrence.getSpecificEpithet());
    event.setInfraspecificEpithet(occurrence.getInfraspecificEpithet());
    event.setTaxonRank(occurrence.getTaxonRank());
    event.setTaxonomicStatus(occurrence.getTaxonomicStatus());
    event.setIucnRedListCategory(occurrence.getIucnRedListCategory());
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
    event.setYear(occurrence.getYear());
    event.setMonth(occurrence.getMonth());
    event.setDay(occurrence.getDay());
    event.setEventDate(occurrence.getEventDate());
    event.setTypeStatus(occurrence.getTypeStatus());
    event.setTypifiedName(occurrence.getTypifiedName());
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
    event.setIsInCluster(occurrence.getIsInCluster());
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
}
