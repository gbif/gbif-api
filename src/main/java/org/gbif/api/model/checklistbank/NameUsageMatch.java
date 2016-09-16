/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.checklistbank;

import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.util.ClassificationUtils;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TaxonomicStatus;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The resulting lookup of a name usage match.
 * A single name usage key with its linnean classification and a confidence value for the match.
 */
public class NameUsageMatch implements LinneanClassification, LinneanClassificationKeys, Serializable {

  private static final long serialVersionUID = -8927655067465421358L;

  private Integer usageKey;
  private String scientificName;
  private String canonicalName;
  private Rank rank;
  private TaxonomicStatus status; // ACCEPTED, SYNONYM or DOUBTFUL only!
  private Integer confidence;
  private String note;
  private MatchType matchType = MatchType.NONE;
  private List<NameUsageMatch> alternatives;
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

  /**
   * The confidence that the lookup was correct.
   * A value between 0 and 100 with higher values being better matches.
   *
   * @return the lookup confidence
   */
  @Min(0)
  @Max(100)
  public Integer getConfidence() {
    return confidence;
  }

  /**
   * @param confidence the confidence to set
   */
  public void setConfidence(Integer confidence) {
    this.confidence = confidence;
  }

  /**
   * @return the type of match for this result
   */
  public MatchType getMatchType() {
    return matchType;
  }

  public void setMatchType(MatchType matchType) {
    this.matchType = matchType;
  }

  /**
   * @return the rank of the matching usage
   */
  @Nullable
  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  /**
   * The scientific name of the looked up name usage.
   *
   * @return the scientific name of the matched usage
   */
  @Nullable
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * The key of the name usage that has been looked up.
   *
   * @return the usageKey
   */
  @Nullable
  public Integer getUsageKey() {
    return usageKey;
  }

  /**
   * @param usageKey the usageKey to set
   */
  public void setUsageKey(Integer usageKey) {
    this.usageKey = usageKey;
  }

  /**
   * @return true if its a synonym
   */
  public boolean isSynonym() {
    return status != null && status.isSynonym();
  }

  /**
   * The taxonomic status of the backbone usage. This field is required and only 3 values are allowed:
   * <ul>
   *   <li>accepted: regular accepted taxon</li>
   *   <li>synonym: any kind of synonym</li>
   *   <li>doubtful: treated as accepted but in doubt for some reason</li>
   * </ul>
   */
  public TaxonomicStatus getStatus() {
    return status;
  }

  public void setStatus(TaxonomicStatus status) {
    this.status = status;
  }

  @Override
  @Nullable
  public String getKingdom() {
    return kingdom;
  }

  @Override
  public void setKingdom(String kingdom) {
    this.kingdom = kingdom;
  }

  @Override
  @Nullable
  public String getPhylum() {
    return phylum;
  }

  @Override
  public void setPhylum(String phylum) {
    this.phylum = phylum;
  }

  @Override
  @Nullable
  public String getClazz() {
    return clazz;
  }

  @Override
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  @Override
  @Nullable
  public String getOrder() {
    return order;
  }

  @Override
  public void setOrder(String order) {
    this.order = order;
  }

  @Override
  @Nullable
  public String getFamily() {
    return family;
  }

  @Override
  public void setFamily(String family) {
    this.family = family;
  }

  @Override
  @Nullable
  public String getGenus() {
    return genus;
  }

  @Override
  public void setGenus(String genus) {
    this.genus = genus;
  }

  @Override
  @Nullable
  public String getSubgenus() {
    return subgenus;
  }

  @Override
  public void setSubgenus(String subgenus) {
    this.subgenus = subgenus;
  }

  @Override
  @Nullable
  public String getSpecies() {
    return species;
  }

  @Override
  public void setSpecies(String species) {
    this.species = species;
  }

  @Override
  @Nullable
  public Integer getKingdomKey() {
    return kingdomKey;
  }

  @Override
  public void setKingdomKey(Integer kingdomKey) {
    this.kingdomKey = kingdomKey;
  }

  @Override
  @Nullable
  public Integer getPhylumKey() {
    return phylumKey;
  }

  @Override
  public void setPhylumKey(Integer phylumKey) {
    this.phylumKey = phylumKey;
  }

  @Override
  @Nullable
  public Integer getClassKey() {
    return classKey;
  }

  @Override
  public void setClassKey(Integer classKey) {
    this.classKey = classKey;
  }

  @Override
  @Nullable
  public Integer getOrderKey() {
    return orderKey;
  }

  @Override
  public void setOrderKey(Integer orderKey) {
    this.orderKey = orderKey;
  }

  @Override
  @Nullable
  public Integer getFamilyKey() {
    return familyKey;
  }

  @Override
  public void setFamilyKey(Integer familyKey) {
    this.familyKey = familyKey;
  }

  @Override
  @Nullable
  public Integer getGenusKey() {
    return genusKey;
  }

  @Override
  public void setGenusKey(Integer genusKey) {
    this.genusKey = genusKey;
  }

  @Override
  @Nullable
  public Integer getSubgenusKey() {
    return subgenusKey;
  }

  @Override
  public void setSubgenusKey(Integer subgenusKey) {
    this.subgenusKey = subgenusKey;
  }

  @Override
  @Nullable
  public Integer getSpeciesKey() {
    return speciesKey;
  }

  @Override
  public void setSpeciesKey(Integer speciesKey) {
    this.speciesKey = speciesKey;
  }

  @Override
  @Nullable
  public String getHigherRank(Rank rank) {
    return ClassificationUtils.getHigherRank(this, rank);
  }

  @Override
  public Integer getHigherRankKey(Rank rank) {
    return ClassificationUtils.getHigherRankKey(this, rank);
  }

  @Nullable
  public String getCanonicalName() {
    return canonicalName;
  }

  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  /**
   * Optional notes on the matching.
   */
  @Nullable
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  /**
   * @return a list of alternative matches considered
   */
  @Nullable
  public List<NameUsageMatch> getAlternatives() {
    return alternatives;
  }

  public void setAlternatives(List<NameUsageMatch> alternatives) {
    this.alternatives = alternatives;
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(
        usageKey,
        scientificName,
        canonicalName,
        rank,
        status,
        confidence,
        note,
        matchType,
        alternatives,
        kingdom,
        phylum,
        clazz,
        order,
        family,
        genus,
        subgenus,
        species,
        kingdomKey,
        phylumKey,
        classKey,
        orderKey,
        familyKey,
        genusKey,
        subgenusKey,
        speciesKey
      );
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NameUsageMatch other = (NameUsageMatch) obj;
    return Objects.equal(this.usageKey, other.usageKey)
           && Objects.equal(this.scientificName, other.scientificName)
           && Objects.equal(this.canonicalName, other.canonicalName)
           && Objects.equal(this.rank, other.rank)
           && Objects.equal(this.status, other.status)
           && Objects.equal(this.confidence, other.confidence)
           && Objects.equal(this.note, other.note)
           && Objects.equal(this.matchType, other.matchType)
           && Objects.equal(this.alternatives, other.alternatives)
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
           && Objects.equal(this.speciesKey, other.speciesKey);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
    .add("usageKey", usageKey)
    .add("scientificName", scientificName)
    .add("canonicalName", canonicalName)
    .add("rank", rank)
    .add("status", status)
    .add("confidence", confidence)
    .add("note", note)
    .add("matchType", matchType)
    .add("alternatives", alternatives)
    .add("kingdom", kingdom)
    .add("phylum", phylum)
    .add("clazz", clazz)
    .add("order", order)
    .add("family", family)
    .add("genus", genus)
    .add("subgenus", subgenus)
    .add("species", species)
    .add("kingdomKey", kingdomKey)
    .add("phylumKey", phylumKey)
    .add("classKey", classKey)
    .add("orderKey", orderKey)
    .add("familyKey", familyKey)
    .add("genusKey", genusKey)
    .add("subgenusKey", subgenusKey)
    .add("speciesKey", speciesKey)
    .toString();
  }

  public enum MatchType {

    /**
     * An exact, straight match.
     */
    EXACT,
    /**
     * A fuzzy, non exact match.
     */
    FUZZY,
    /**
     * Matching on a higher rank than the lowest name given.
     */
    HIGHERRANK,
    /**
     * No match or matching several names with too little information to keep apart.
     */
    NONE

  }

}
