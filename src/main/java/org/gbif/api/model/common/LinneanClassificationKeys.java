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
package org.gbif.api.model.common;

import org.gbif.api.vocabulary.Rank;

import javax.annotation.Nullable;

/**
 * A flat taxonomic classification using the major, Linnean ranks with verbatim names and integer based keys.
 */
public interface LinneanClassificationKeys {

  /**
   * Return the class key for this usage. If the usage is for something above the "class" taxonomic level,
   * return null.
   *
   * @return the classKey
   */
  @Nullable
  Integer getClassKey();

  /**
   * @param classKey the classKey to set
   */
  void setClassKey(Integer classKey);

  /**
   * Return the family key for this usage. If the usage is for something above the "family" taxonomic level,
   * return null.
   *
   * @return the familyKey
   */
  @Nullable
  Integer getFamilyKey();

  /**
   * @param familyKey the familyKey to set
   */
  void setFamilyKey(Integer familyKey);

  /**
   * Return the genus key for this usage. If the usage is for something above the "genus" taxonomic level,
   * return null.
   *
   * @return the genusKey
   */
  @Nullable
  Integer getGenusKey();

  /**
   * @param genusKey the genusKey to set
   */
  void setGenusKey(Integer genusKey);

  /**
   * Return the kingdom key for this usage. If the usage is for something above the "kingdom" taxonomic level,
   * return null.
   *
   * @return the kingdomKey
   */
  @Nullable
  Integer getKingdomKey();

  /**
   * @param kingdomKey the kingdomKey to set
   */
  void setKingdomKey(Integer kingdomKey);

  /**
   * Return the order key for this usage. If the usage is for something above the "order" taxonomic level,
   * return null.
   *
   * @return the orderKey
   */
  @Nullable
  Integer getOrderKey();

  /**
   * @param orderKey the orderKey to set
   */
  void setOrderKey(Integer orderKey);

  /**
   * Return the phylum key for this usage. If the usage is for something above the "phylum" taxonomic level,
   * return null.
   *
   * @return the phylumKey
   */
  @Nullable
  Integer getPhylumKey();

  /**
   * @param phylumKey the phylumKey to set
   */
  void setPhylumKey(Integer phylumKey);

  /**
   * Return the species key for this usage. If the usage is for something above the "species" taxonomic level,
   * return null.
   *
   * @return the speciesKey
   */
  @Nullable
  Integer getSpeciesKey();

  /**
   * @param speciesKey the speciesKey to set
   */
  void setSpeciesKey(Integer speciesKey);

  /**
   * Return the subgenus key for this usage.
   * If the usage is for something above the "subgenus" taxonomic level, return null.
   *
   * @return subgenusKey
   */
  @Nullable
  Integer getSubgenusKey();

  /**
   * @param subgenusKey the subgenus usage key
   */
  void setSubgenusKey(Integer subgenusKey);

  /**
   * Gets a higher taxon key by passing the rank of it.
   * Only Linnean ranks are supported.
   *
   * @param rank the higher linnean rank to retrieve
   *
   * @return the key of the higher taxon or null if rank doesnt exist
   */
  @Nullable
  Integer getHigherRankKey(Rank rank);
}
