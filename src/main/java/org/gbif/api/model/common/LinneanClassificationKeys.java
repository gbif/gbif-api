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
  String getClassKey();

  /**
   * @param classKey the classKey to set
   */
  void setClassKey(String classKey);

  /**
   * Return the family key for this usage. If the usage is for something above the "family" taxonomic level,
   * return null.
   *
   * @return the familyKey
   */
  @Nullable
  String getFamilyKey();

  /**
   * @param familyKey the familyKey to set
   */
  void setFamilyKey(String familyKey);

  /**
   * Return the genus key for this usage. If the usage is for something above the "genus" taxonomic level,
   * return null.
   *
   * @return the genusKey
   */
  @Nullable
  String getGenusKey();

  /**
   * @param genusKey the genusKey to set
   */
  void setGenusKey(String genusKey);

  /**
   * Return the kingdom key for this usage. If the usage is for something above the "kingdom" taxonomic level,
   * return null.
   *
   * @return the kingdomKey
   */
  @Nullable
  String getKingdomKey();

  /**
   * @param kingdomKey the kingdomKey to set
   */
  void setKingdomKey(String kingdomKey);

  /**
   * Return the order key for this usage. If the usage is for something above the "order" taxonomic level,
   * return null.
   *
   * @return the orderKey
   */
  @Nullable
  String getOrderKey();

  /**
   * @param orderKey the orderKey to set
   */
  void setOrderKey(String orderKey);

  /**
   * Return the phylum key for this usage. If the usage is for something above the "phylum" taxonomic level,
   * return null.
   *
   * @return the phylumKey
   */
  @Nullable
  String getPhylumKey();

  /**
   * @param phylumKey the phylumKey to set
   */
  void setPhylumKey(String phylumKey);

  /**
   * Return the species key for this usage. If the usage is for something above the "species" taxonomic level,
   * return null.
   *
   * @return the speciesKey
   */
  @Nullable
  String getSpeciesKey();

  /**
   * @param speciesKey the speciesKey to set
   */
  void setSpeciesKey(String speciesKey);

  /**
   * Return the subgenus key for this usage.
   * If the usage is for something above the "subgenus" taxonomic level, return null.
   *
   * @return subgenusKey
   */
  @Nullable
  String getSubgenusKey();

  /**
   * @param subgenusKey the subgenus usage key
   */
  void setSubgenusKey(String subgenusKey);

  /**
   * Gets a higher taxon key by passing the rank of it.
   * Only Linnean ranks are supported.
   *
   * @param rank the higher linnean rank to retrieve
   *
   * @return the key of the higher taxon or null if rank doesnt exist
   */
  @Nullable
  String getHigherRankKey(Rank rank);
}
