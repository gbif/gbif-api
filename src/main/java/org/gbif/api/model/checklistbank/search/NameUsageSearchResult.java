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
package org.gbif.api.model.checklistbank.search;

import org.gbif.api.model.checklistbank.Description;
import org.gbif.api.model.checklistbank.VernacularName;
import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.Habitat;
import org.gbif.api.vocabulary.NameType;
import org.gbif.api.vocabulary.NomenclaturalStatus;
import org.gbif.api.vocabulary.Origin;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;
import org.gbif.api.vocabulary.ThreatStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Class used for returning results of a full text search operation.
 * This class contains additional attributes that are required for displaying/providing textual information.
 */
@SuppressWarnings("unused")
public class NameUsageSearchResult implements LinneanClassification, LinneanClassificationKeys {

  private Integer key;
  private Integer nameKey;
  private UUID datasetKey;
  private UUID constituentKey;
  private Integer nubKey;
  private Integer parentKey;
  private String parent;
  private Integer acceptedKey;
  private String accepted;
  private Integer basionymKey;
  private String basionym;

  // for LinneanClassification
  private String kingdom;
  private String phylum;
  @JsonProperty("class")
  private String clazz;
  private String order;
  private String family;
  private String genus;
  private String subgenus;
  private String species;
  // for LinneanClassificationKeys
  private Integer kingdomKey;
  private Integer phylumKey;
  private Integer classKey;
  private Integer orderKey;
  private Integer familyKey;
  private Integer genusKey;
  private Integer subgenusKey;
  private Integer speciesKey;

  private String scientificName;
  private String canonicalName;
  private String authorship;
  private String publishedIn;
  private String accordingTo;

  private NameType nameType;
  private TaxonomicStatus taxonomicStatus;
  private Rank rank;
  private Origin origin;

  private int numDescendants;
  private int numOccurrences;

  private String taxonID;
  private Boolean extinct;
  private List<Habitat> habitats = new ArrayList<>();
  private List<NomenclaturalStatus> nomenclaturalStatus = new ArrayList<>();
  private List<ThreatStatus> threatStatuses = new ArrayList<>();
  private List<Description> descriptions = new ArrayList<>();
  private List<VernacularName> vernacularNames = new ArrayList<>();

  /**
   * @return the name key for retrieving a parsed name object
   */
  public Integer getNameKey() {
    return nameKey;
  }

  public void setNameKey(Integer nameKey) {
    this.nameKey = nameKey;
  }

  public String getAccepted() {
    return accepted;
  }

  public void setAccepted(String accepted) {
    this.accepted = accepted;
  }

  public Integer getAcceptedKey() {
    return acceptedKey;
  }

  public void setAcceptedKey(Integer acceptedKey) {
    this.acceptedKey = acceptedKey;
  }

  public String getAccordingTo() {
    return accordingTo;
  }

  public void setAccordingTo(String accordingTo) {
    this.accordingTo = accordingTo;
  }

  public String getAuthorship() {
    return authorship;
  }

  public void setAuthorship(String authorship) {
    this.authorship = authorship;
  }

  public String getBasionym() {
    return basionym;
  }

  public void setBasionym(String basionym) {
    this.basionym = basionym;
  }

  public Integer getBasionymKey() {
    return basionymKey;
  }

  public void setBasionymKey(Integer basionymKey) {
    this.basionymKey = basionymKey;
  }

  public String getCanonicalName() {
    return canonicalName;
  }

  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public UUID getConstituentKey() {
    return constituentKey;
  }

  public void setConstituentKey(UUID constituentKey) {
    this.constituentKey = constituentKey;
  }

  /**
   * @return the list of Description
   */
  @NotNull
  public List<Description> getDescriptions() {
    return descriptions;
  }

  /**
   * @param descriptions the list of Description
   */
  public void setDescriptions(List<Description> descriptions) {
    this.descriptions = descriptions;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public NameType getNameType() {
    return nameType;
  }

  public void setNameType(NameType nameType) {
    this.nameType = nameType;
  }

  public Integer getNubKey() {
    return nubKey;
  }

  public void setNubKey(Integer nubKey) {
    this.nubKey = nubKey;
  }

  public int getNumDescendants() {
    return numDescendants;
  }

  public void setNumDescendants(int numDescendants) {
    this.numDescendants = numDescendants;
  }

  public int getNumOccurrences() {
    return numOccurrences;
  }

  public void setNumOccurrences(int numOccurrences) {
    this.numOccurrences = numOccurrences;
  }

  public Origin getOrigin() {
    return origin;
  }

  public void setOrigin(Origin origin) {
    this.origin = origin;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public Integer getParentKey() {
    return parentKey;
  }

  public void setParentKey(Integer parentKey) {
    this.parentKey = parentKey;
  }

  public String getPublishedIn() {
    return publishedIn;
  }

  public void setPublishedIn(String publishedIn) {
    this.publishedIn = publishedIn;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public String getTaxonID() {
    return taxonID;
  }

  public void setTaxonID(String taxonID) {
    this.taxonID = taxonID;
  }

  public TaxonomicStatus getTaxonomicStatus() {
    return taxonomicStatus;
  }

  public void setTaxonomicStatus(TaxonomicStatus taxonomicStatus) {
    this.taxonomicStatus = taxonomicStatus;
  }

  public List<ThreatStatus> getThreatStatuses() {
    return threatStatuses;
  }

  public void setThreatStatuses(List<ThreatStatus> threatStatuses) {
    this.threatStatuses = threatStatuses;
  }

  public List<NomenclaturalStatus> getNomenclaturalStatus() {
    return nomenclaturalStatus;
  }

  public void setNomenclaturalStatus(List<NomenclaturalStatus> nomenclaturalStatus) {
    this.nomenclaturalStatus = nomenclaturalStatus;
  }

  /**
   * @return the list of VernacularName
   */
  @NotNull
  public List<VernacularName> getVernacularNames() {
    return vernacularNames;
  }

  /**
   * @param vernacularNames the VernacularName list to set
   */
  public void setVernacularNames(List<VernacularName> vernacularNames) {
    this.vernacularNames = vernacularNames;
  }

  public Boolean isExtinct() {
    return extinct;
  }

  public void setExtinct(Boolean extinct) {
    this.extinct = extinct;
  }

  public List<Habitat> getHabitats() {
    return habitats;
  }

  public void setHabitats(List<Habitat> habitats) {
    this.habitats = habitats;
  }

  @Override
  public String getKingdom() {
    return kingdom;
  }

  @Override
  public void setKingdom(String kingdom) {
    this.kingdom = kingdom;
  }

  @Override
  public String getPhylum() {
    return phylum;
  }

  @Override
  public void setPhylum(String phylum) {
    this.phylum = phylum;
  }

  @Override
  public String getClazz() {
    return clazz;
  }

  @Override
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public String getOrder() {
    return order;
  }

  @Override
  public void setOrder(String order) {
    this.order = order;
  }

  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public void setFamily(String family) {
    this.family = family;
  }

  @Override
  public String getGenus() {
    return genus;
  }

  @Override
  public void setGenus(String genus) {
    this.genus = genus;
  }

  @Override
  public String getSubgenus() {
    return subgenus;
  }

  @Override
  public void setSubgenus(String subgenus) {
    this.subgenus = subgenus;
  }

  @Override
  public String getSpecies() {
    return species;
  }

  @Override
  public void setSpecies(String species) {
    this.species = species;
  }

  @Override
  public Integer getKingdomKey() {
    return kingdomKey;
  }

  @Override
  public void setKingdomKey(Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Override
  public Integer getPhylumKey() {
    return phylumKey;
  }

  @Override
  public void setPhylumKey(Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Override
  public Integer getClassKey() {
    return classKey;
  }

  @Override
  public void setClassKey(Integer classKey) {
    this.classKey = classKey;
  }

  @Override
  public Integer getOrderKey() {
    return orderKey;
  }

  @Override
  public void setOrderKey(Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Override
  public Integer getFamilyKey() {
    return familyKey;
  }

  @Override
  public void setFamilyKey(Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Override
  public Integer getGenusKey() {
    return genusKey;
  }

  @Override
  public void setGenusKey(Integer genusKey) {
    this.genusKey = genusKey;
  }

  @Override
  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  @Override
  public void setSubgenusKey(Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  @Override
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  @Override
  public String getHigherRank(Rank rank) {
    return ClassificationUtils.getHigherRank(this, rank);
  }

  @Override
  public Integer getHigherRankKey(Rank rank) {
    return ClassificationUtils.getHigherRankKey(this, rank);
  }

  /**
   * An ordered map with entries for all higher Linnean ranks down to the actual direct parent of this usage.
   * The map starts with the highest rank, e.g. the kingdom and maps the name usage key to its canonical name.
   * The name usage itself is never included, even though a higher rank might point to the usage itself.
   *
   * @return map of higher ranks
   */
  @NotNull
  public LinkedHashMap<Integer, String> getHigherClassificationMap() {
    return ClassificationUtils.getHigherClassificationMap(this, key, parentKey, parent);
  }

  /**
   * Convenience method using the taxonomicStatus field.
   *
   * @return true if its a synonym
   */
  public boolean isSynonym() {
    return taxonomicStatus != null && taxonomicStatus.isSynonym();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NameUsageSearchResult that = (NameUsageSearchResult) o;
    return numDescendants == that.numDescendants &&
      numOccurrences == that.numOccurrences &&
      Objects.equals(key, that.key) &&
      Objects.equals(nameKey, that.nameKey) &&
      Objects.equals(datasetKey, that.datasetKey) &&
      Objects.equals(constituentKey, that.constituentKey) &&
      Objects.equals(nubKey, that.nubKey) &&
      Objects.equals(parentKey, that.parentKey) &&
      Objects.equals(parent, that.parent) &&
      Objects.equals(acceptedKey, that.acceptedKey) &&
      Objects.equals(accepted, that.accepted) &&
      Objects.equals(basionymKey, that.basionymKey) &&
      Objects.equals(basionym, that.basionym) &&
      Objects.equals(kingdom, that.kingdom) &&
      Objects.equals(phylum, that.phylum) &&
      Objects.equals(clazz, that.clazz) &&
      Objects.equals(order, that.order) &&
      Objects.equals(family, that.family) &&
      Objects.equals(genus, that.genus) &&
      Objects.equals(subgenus, that.subgenus) &&
      Objects.equals(species, that.species) &&
      Objects.equals(kingdomKey, that.kingdomKey) &&
      Objects.equals(phylumKey, that.phylumKey) &&
      Objects.equals(classKey, that.classKey) &&
      Objects.equals(orderKey, that.orderKey) &&
      Objects.equals(familyKey, that.familyKey) &&
      Objects.equals(genusKey, that.genusKey) &&
      Objects.equals(subgenusKey, that.subgenusKey) &&
      Objects.equals(speciesKey, that.speciesKey) &&
      Objects.equals(scientificName, that.scientificName) &&
      Objects.equals(canonicalName, that.canonicalName) &&
      Objects.equals(authorship, that.authorship) &&
      Objects.equals(publishedIn, that.publishedIn) &&
      Objects.equals(accordingTo, that.accordingTo) &&
      nameType == that.nameType &&
      taxonomicStatus == that.taxonomicStatus &&
      rank == that.rank &&
      origin == that.origin &&
      Objects.equals(taxonID, that.taxonID) &&
      Objects.equals(extinct, that.extinct) &&
      Objects.equals(habitats, that.habitats) &&
      Objects.equals(nomenclaturalStatus, that.nomenclaturalStatus) &&
      Objects.equals(threatStatuses, that.threatStatuses) &&
      Objects.equals(descriptions, that.descriptions) &&
      Objects.equals(vernacularNames, that.vernacularNames);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, nameKey, datasetKey, constituentKey, nubKey, parentKey, parent, acceptedKey,
        accepted, basionymKey, basionym, kingdom, phylum, clazz, order, family, genus, subgenus,
        species, kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, subgenusKey,
        speciesKey, scientificName, canonicalName, authorship, publishedIn, accordingTo, nameType,
        taxonomicStatus, rank, origin, numDescendants, numOccurrences, taxonID, extinct, habitats,
        nomenclaturalStatus, threatStatuses, descriptions, vernacularNames);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NameUsageSearchResult.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("nameKey=" + nameKey)
      .add("datasetKey=" + datasetKey)
      .add("constituentKey=" + constituentKey)
      .add("nubKey=" + nubKey)
      .add("parentKey=" + parentKey)
      .add("parent='" + parent + "'")
      .add("acceptedKey=" + acceptedKey)
      .add("accepted='" + accepted + "'")
      .add("basionymKey=" + basionymKey)
      .add("basionym='" + basionym + "'")
      .add("kingdom='" + kingdom + "'")
      .add("phylum='" + phylum + "'")
      .add("clazz='" + clazz + "'")
      .add("order='" + order + "'")
      .add("family='" + family + "'")
      .add("genus='" + genus + "'")
      .add("subgenus='" + subgenus + "'")
      .add("species='" + species + "'")
      .add("kingdomKey=" + kingdomKey)
      .add("phylumKey=" + phylumKey)
      .add("classKey=" + classKey)
      .add("orderKey=" + orderKey)
      .add("familyKey=" + familyKey)
      .add("genusKey=" + genusKey)
      .add("subgenusKey=" + subgenusKey)
      .add("speciesKey=" + speciesKey)
      .add("scientificName='" + scientificName + "'")
      .add("canonicalName='" + canonicalName + "'")
      .add("authorship='" + authorship + "'")
      .add("publishedIn='" + publishedIn + "'")
      .add("accordingTo='" + accordingTo + "'")
      .add("nameType=" + nameType)
      .add("taxonomicStatus=" + taxonomicStatus)
      .add("rank=" + rank)
      .add("origin=" + origin)
      .add("numDescendants=" + numDescendants)
      .add("numOccurrences=" + numOccurrences)
      .add("taxonID='" + taxonID + "'")
      .add("extinct=" + extinct)
      .add("habitats=" + habitats)
      .add("nomenclaturalStatus=" + nomenclaturalStatus)
      .add("threatStatuses=" + threatStatuses)
      .add("descriptions=" + descriptions)
      .add("vernacularNames=" + vernacularNames)
      .toString();
  }
}
