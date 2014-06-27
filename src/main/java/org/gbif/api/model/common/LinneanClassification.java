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
 * A flat taxonomic classification using the major, Linnean ranks with verbatim names.
 */
public interface LinneanClassification {

  /**
   * Return the class for this usage. If the usage is for something above the "class" taxonomic level,
   * return null.
   *
   * @return the class
   */
  @Nullable
  String getClazz();

  /**
   * @param clazz the clazz to set
   */
  void setClazz(String clazz);

  /**
   * Return the family for this usage. If the usage is for something above the "family" taxonomic level,
   * return null.
   *
   * @return the family
   */
  @Nullable
  String getFamily();

  /**
   * @param family the family to set
   */
  void setFamily(String family);

  /**
   * Return the genus for this usage. If the usage is for something above the "genus" taxonomic level,
   * return null.
   *
   * @return the genus
   */
  @Nullable
  String getGenus();

  /**
   * @param genus the genus to set
   */
  void setGenus(String genus);

  /**
   * Return the kingdom for this usage. If the usage is for something above the "kingdom" taxonomic level,
   * return null.
   *
   * @return the kingdom
   */
  @Nullable
  String getKingdom();

  /**
   * @param kingdom the kingdom to set
   */
  void setKingdom(String kingdom);

  /**
   * Return the order for this usage. If the usage is for something above the "order" taxonomic level,
   * return null.
   *
   * @return the order
   */
  @Nullable
  String getOrder();

  /**
   * @param order the order to set
   */
  void setOrder(String order);

  /**
   * Return the phylum for this usage. If the usage is for something above the "phylum" taxonomic level,
   * return null.
   *
   * @return the phylum
   */
  @Nullable
  String getPhylum();

  /**
   * @param phylum the phylum to set
   */
  void setPhylum(String phylum);

  /**
   * Return the species for this usage. If the usage is for something above the "species" taxonomic level,
   * return null.
   *
   * @return the species
   */
  @Nullable
  String getSpecies();

  /**
   * @param species the species to set
   */
  void setSpecies(String species);

  /**
   * Return the canonical subgenus name for this usage.
   * If the usage is for something above the "subgenus" taxonomic level, return null.
   *
   * @return the subgenus name
   */
  @Nullable
  String getSubgenus();

  /**
   * @param subgenus the subgenus canonical name
   */
  void setSubgenus(String subgenus);

  /**
   * Gets a higher taxon property by passing the rank of it.
   * Only Linnean ranks are supported.
   *
   * @param rank the higher linnean rank to retrieve
   *
   * @return the name of the higher taxon or null if rank doesnt exist
   */
  @Nullable
  String getHigherRank(Rank rank);
}
