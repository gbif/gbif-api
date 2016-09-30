/*
 * Copyright 2011 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank.search;


import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.util.LinkedHashMap;

import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Class used for returning results of a suggest operation.
 * This class contains additional attributes that are required for displaying/providing textual information.
 */
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
  public int hashCode() {
    return Objects
      .hashCode(key, nameKey, parentKey, parent, kingdom, phylum, clazz, order, family, genus, subgenus, species, kingdomKey,
        phylumKey, classKey, orderKey, familyKey, genusKey, subgenusKey, speciesKey, scientificName, canonicalName, rank, status, nubKey);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NameUsageSuggestResult other = (NameUsageSuggestResult) obj;
    return Objects.equal(this.key, other.key)
      && Objects.equal(this.nameKey, other.nameKey)
      && Objects.equal(this.parentKey, other.parentKey)
      && Objects.equal(this.parent, other.parent)
      && Objects.equal(this.kingdom, other.kingdom)
      && Objects.equal(this.phylum, other.phylum)
      && Objects.equal(this.clazz, other.clazz)
      && Objects.equal(this.order, other.order)
      && Objects.equal(this.family, other.family)
      && Objects.equal(this.genus, other.genus)
      && Objects.equal(this.subgenus, other.subgenus)
      && Objects.equal(this.species, other.species)
      && Objects.equal(this.kingdomKey, other.kingdomKey)
      && Objects.equal(this.phylumKey, other.phylumKey)
      && Objects.equal(this.classKey, other.classKey)
      && Objects.equal(this.orderKey, other.orderKey)
      && Objects.equal(this.familyKey, other.familyKey)
      && Objects.equal(this.genusKey, other.genusKey)
      && Objects.equal(this.subgenusKey, other.subgenusKey)
      && Objects.equal(this.speciesKey, other.speciesKey)
      && Objects.equal(this.scientificName, other.scientificName)
      && Objects.equal(this.canonicalName, other.canonicalName)
      && Objects.equal(this.rank, other.rank)
      && Objects.equal(this.status, other.status)
      && Objects.equal(this.nubKey, other.nubKey);
  }

  @Override
  public String toString() {
    return "NameUsageSearchResult{" +
      "key=" + key +
      ", nameKey=" + nameKey +
      ", parentKey=" + parentKey +
      ", parent='" + parent + '\'' +
      ", kingdom='" + kingdom + '\'' +
      ", phylum='" + phylum + '\'' +
      ", clazz='" + clazz + '\'' +
      ", order='" + order + '\'' +
      ", family='" + family + '\'' +
      ", genus='" + genus + '\'' +
      ", subgenus='" + subgenus + '\'' +
      ", species='" + species + '\'' +
      ", kingdomKey=" + kingdomKey +
      ", phylumKey=" + phylumKey +
      ", classKey=" + classKey +
      ", orderKey=" + orderKey +
      ", familyKey=" + familyKey +
      ", genusKey=" + genusKey +
      ", subgenusKey=" + subgenusKey +
      ", speciesKey=" + speciesKey +
      ", scientificName='" + scientificName + '\'' +
      ", canonicalName='" + canonicalName + '\'' +
      ", rank=" + rank +
      ", status=" + status +
      ", nubKey=" + nubKey +
      '}';
  }

}
