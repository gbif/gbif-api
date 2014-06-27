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

import org.gbif.api.vocabulary.Rank;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * Basic metrics for a single name usage. These metrics have been embedded in the NameUsage class before
 * but are rarely being used. They are therefore split out into a separate class to be retrieved only when needed.
 */
public class NameUsageMetrics {

  private Integer key;
  private int numPhylum;
  private int numClass;
  private int numOrder;
  private int numFamily;
  private int numGenus;
  private int numSubgenus;
  private int numSpecies;
  private int numChildren;
  private int numDescendants;
  private int numSynonyms;


  /**
   * Return the key that uniquely identifies this name usage.
   *
   * @return the key
   */
  @NotNull
  public Integer getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(Integer key) {
    this.key = key;
  }


  /**
   * Returns the number of direct taxonomic elements under this usage. "Direct" means that children should have
   * this usageKey as their parentKey.
   *
   * @return the numChildren
   */
  @Min(0)
  public int getNumChildren() {
    return numChildren;
  }

  /**
   * @param numChildren the numChildren to set
   */
  public void setNumChildren(int numChildren) {
    this.numChildren = numChildren;
  }

  /**
   * Returns the number of classes that are below this taxon. Typically this would mean
   * this usage is of something above the "class" taxonomic level.
   *
   * @return the numClass
   */
  @Min(0)
  public int getNumClass() {
    return numClass;
  }

  /**
   * @param numClass the numClass to set
   */
  public void setNumClass(int numClass) {
    this.numClass = numClass;
  }

  /**
   * The number of all accepted taxonomic elements under this usage.
   *
   * @return the number of descendants
   */
  public int getNumDescendants() {
    return numDescendants;
  }

  /**
   * @param numDescendants the n umber of descendants to set
   */
  public void setNumDescendants(int numDescendants) {
    this.numDescendants = numDescendants;
  }

  /**
   * Returns the number of families that are below this taxon. Typically this would mean
   * this usage is of something above the "family" taxonomic level.
   *
   * @return the numFamily
   */
  @Min(0)
  public int getNumFamily() {
    return numFamily;
  }

  /**
   * @param numFamily the numFamily to set
   */
  public void setNumFamily(int numFamily) {
    this.numFamily = numFamily;
  }

  /**
   * Returns the number of genus that are below this taxon. Typically this would mean
   * this usage is of something above the "genus" taxonomic level.
   *
   * @return the numGenus
   */
  @Min(0)
  public int getNumGenus() {
    return numGenus;
  }

  /**
   * @param numGenus the numGenus to set
   */
  public void setNumGenus(int numGenus) {
    this.numGenus = numGenus;
  }

  /**
   * Returns the number of orders that are below this taxon. Typically this would mean
   * this usage is of something above the "order" taxonomic level.
   *
   * @return the numOrder
   */
  @Min(0)
  public int getNumOrder() {
    return numOrder;
  }

  /**
   * @param numOrder the numOrder to set
   */
  public void setNumOrder(int numOrder) {
    this.numOrder = numOrder;
  }

  /**
   * Returns the number of phyla that are below this taxon. Typically this would mean
   * this usage is of something above the "phylum" taxonomic level.
   *
   * @return the numPhylum
   */
  @Min(0)
  public int getNumPhylum() {
    return numPhylum;
  }

  /**
   * @param numPhylum the numPhylum to set
   */
  public void setNumPhylum(int numPhylum) {
    this.numPhylum = numPhylum;
  }

  /**
   * Returns the number of species that are below this taxon. Typically this would mean
   * this usage is of something above the "species" taxonomic level.
   *
   * @return the numSpecies.
   */
  @Min(0)
  public int getNumSpecies() {
    return numSpecies;
  }

  /**
   * @param numSpecies the numSpecies to set
   */
  public void setNumSpecies(int numSpecies) {
    this.numSpecies = numSpecies;
  }

  /**
   * Returns the number of distinct subgenera that are below this taxon. Typically this would mean
   * this usage is of something above the "subgenus" taxonomic level.
   *
   * @return the numSubgenus
   */
  @Min(0)
  public int getNumSubgenus() {
    return numSubgenus;
  }

  /**
   * @param numSubgenus the number of distinct subgenera
   */
  public void setNumSubgenus(int numSubgenus) {
    this.numSubgenus = numSubgenus;
  }

  /**
   * Returns the number of related synonyms this usage possses.
   *
   * @return the numSynonyms.
   */
  @Min(0)
  public int getNumSynonyms() {
    return numSynonyms;
  }

  /**
   * @param numSynonyms the numSynonyms to set
   */
  public void setNumSynonyms(int numSynonyms) {
    this.numSynonyms = numSynonyms;
  }

  /**
   * Gets the count of usages by a rank.
   * Only Linnean ranks are supported.
   *
   * @param rank the linnean rank to retrieve counts for
   *
   * @return the count or null if rank doesnt exist
   */
  @Nullable
  public Integer getNumByRank(Rank rank) {
    if (rank == Rank.PHYLUM) {
      return numPhylum;
    }
    if (rank == Rank.CLASS) {
      return numClass;
    }
    if (rank == Rank.ORDER) {
      return numOrder;
    }
    if (rank == Rank.FAMILY) {
      return numFamily;
    }
    if (rank == Rank.GENUS) {
      return numGenus;
    }
    if (rank == Rank.SUBGENUS) {
      return numSubgenus;
    }
    if (rank == Rank.SPECIES) {
      return numSpecies;
    }
    return null;
  }


  @Override
  public int hashCode() {
    return Objects
      .hashCode(
        key,
        numPhylum,
        numClass,
        numOrder,
        numFamily,
        numGenus,
        numSubgenus,
        numSpecies,
        numChildren,
        numDescendants,
        numSynonyms);
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
    final NameUsageMetrics other = (NameUsageMetrics) obj;
    return Objects.equal(this.key, other.key)
           && Objects.equal(this.numPhylum, other.numPhylum)
           && Objects.equal(this.numClass, other.numClass)
           && Objects.equal(this.numOrder, other.numOrder)
           && Objects.equal(this.numFamily, other.numFamily)
           && Objects.equal(this.numGenus, other.numGenus)
           && Objects.equal(this.numSubgenus, other.numSubgenus)
           && Objects.equal(this.numSpecies, other.numSpecies)
           && Objects.equal(this.numChildren, other.numChildren)
           && Objects.equal(this.numDescendants, other.numDescendants)
           && Objects.equal(this.numSynonyms, other.numSynonyms);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("numPhylum", numPhylum)
      .add("numClass", numClass)
      .add("numOrder", numOrder)
      .add("numFamily", numFamily)
      .add("numGenus", numGenus)
      .add("numSubgenus", numSubgenus)
      .add("numSpecies", numSpecies)
      .add("numChildren", numChildren)
      .add("numDescendants", numDescendants)
      .add("numSynonyms", numSynonyms)
      .toString();
  }
}
