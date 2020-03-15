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

import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class used for returning results of a suggest operation.
 * This class contains additional attributes that are required for displaying/providing textual information.
 */
@SuppressWarnings("unused")
public class NameUsageSuggestResult implements LinneanClassification, LinneanClassificationKeys {

  private Integer key;
  private Integer nameKey;

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

  private String parent;
  private Integer parentKey;
  private Integer nubKey;
  private String scientificName;
  private String canonicalName;
  private Rank rank;
  private TaxonomicStatus status;

  /**
   * @return the name key for retrieving a parsed name object
   */
  public Integer getNameKey() {
    return nameKey;
  }

  public void setNameKey(Integer nameKey) {
    this.nameKey = nameKey;
  }

  /**
   * @return true if its a synonym
   */
  public boolean isSynonym() {
    return status != null && status.isSynonym();
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public TaxonomicStatus getStatus() {
    return status;
  }

  public void setStatus(TaxonomicStatus status) {
    this.status = status;
  }

  public Integer getNubKey() {
    return nubKey;
  }

  public void setNubKey(Integer nubKey) {
    this.nubKey = nubKey;
  }

  /**
   * Taxon key.
   */
  public Integer getKey() {
    return key;
  }

  public String getKingdom() {
    return kingdom;
  }

  public String getPhylum() {
    return phylum;
  }

  public String getClazz() {
    return clazz;
  }

  public String getOrder() {
    return order;
  }

  public String getFamily() {
    return family;
  }

  public String getGenus() {
    return genus;
  }

  public String getSubgenus() {
    return subgenus;
  }

  public String getSpecies() {
    return species;
  }

  public Integer getKingdomKey() {
    return kingdomKey;
  }

  public Integer getPhylumKey() {
    return phylumKey;
  }

  public Integer getClassKey() {
    return classKey;
  }

  public Integer getOrderKey() {
    return orderKey;
  }

  public Integer getFamilyKey() {
    return familyKey;
  }

  public Integer getGenusKey() {
    return genusKey;
  }

  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  public Integer getSpeciesKey() {
    return speciesKey;
  }

  public String getCanonicalName() {
    return canonicalName;
  }

  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  @Override
  public void setClassKey(Integer classKey) {
    this.classKey = classKey;
  }

  @Override
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public void setFamily(String family) {
    this.family = family;
  }

  @Override
  public void setFamilyKey(Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Override
  public void setGenus(String genus) {
    this.genus = genus;
  }

  @Override
  public void setGenusKey(Integer genusKey) {
    this.genusKey = genusKey;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  @Override
  public void setKingdom(String kingdom) {
    this.kingdom = kingdom;
  }

  @Override
  public void setKingdomKey(Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Override
  public void setOrder(String order) {
    this.order = order;
  }

  @Override
  public void setOrderKey(Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Override
  public void setPhylum(String phylum) {
    this.phylum = phylum;
  }

  @Override
  public void setPhylumKey(Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Override
  public void setSpecies(String species) {
    this.species = species;
  }

  @Override
  public void setSpeciesKey(Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  @Override
  public void setSubgenus(String subgenus) {
    this.subgenus = subgenus;
  }

  @Override
  public void setSubgenusKey(Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  public String getParent() {
    return parent;
  }

  public Integer getParentKey() {
    return parentKey;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public void setParentKey(Integer parentKey) {
    this.parentKey = parentKey;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NameUsageSuggestResult that = (NameUsageSuggestResult) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(nameKey, that.nameKey) &&
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
      Objects.equals(parent, that.parent) &&
      Objects.equals(parentKey, that.parentKey) &&
      Objects.equals(nubKey, that.nubKey) &&
      Objects.equals(scientificName, that.scientificName) &&
      Objects.equals(canonicalName, that.canonicalName) &&
      rank == that.rank &&
      status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(key, nameKey, kingdom, phylum, clazz, order, family, genus, subgenus, species,
        kingdomKey, phylumKey, classKey, orderKey, familyKey, genusKey, subgenusKey, speciesKey,
        parent, parentKey, nubKey, scientificName, canonicalName, rank, status);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NameUsageSuggestResult.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("nameKey=" + nameKey)
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
      .add("parent='" + parent + "'")
      .add("parentKey=" + parentKey)
      .add("nubKey=" + nubKey)
      .add("scientificName='" + scientificName + "'")
      .add("canonicalName='" + canonicalName + "'")
      .add("rank=" + rank)
      .add("status=" + status)
      .toString();
  }
}
