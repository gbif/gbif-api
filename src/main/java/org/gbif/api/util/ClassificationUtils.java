/*
 * Copyright 2020-2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.util;

import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.model.common.LinneanClassificationKeys;
import org.gbif.api.vocabulary.Rank;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("unused")
public class ClassificationUtils {

  /**
   * Concatenates all higher Linnean taxa into a single dwc:higherClassification string, skipping
   * null values.
   *
   * @param lc the LinneanClassification to join into the higher classification string
   *
   * @return the concatenated dwc:higherClassification string
   */
  @Nullable
  public static String getHigherClassification(LinneanClassification lc) {
    return StringUtils.trimToNull(
      Stream.of(lc.getKingdom(), lc.getPhylum(), lc.getClazz(),
        lc.getOrder(), lc.getFamily(), lc.getGenus())
        .filter(Objects::nonNull)
        .collect(Collectors.joining(", ")));
  }

  /**
   * Gets a higher taxon property by passing the rank of it.
   * Only Linnean ranks are supported.
   *
   * @param lc   the LinneanClassification holding the taxon property to be retrieved
   * @param rank the higher linnean rank to retrieve
   *
   * @return the name of the higher taxon or null if rank doesnt exist
   */
  @Nullable
  public static String getHigherRank(LinneanClassification lc, Rank rank) {
    if (rank != null) {
      switch (rank) {
        case KINGDOM:
          return lc.getKingdom();
        case PHYLUM:
          return lc.getPhylum();
        case CLASS:
          return lc.getClazz();
        case ORDER:
          return lc.getOrder();
        case FAMILY:
          return lc.getFamily();
        case GENUS:
          return lc.getGenus();
        case SUBGENUS:
          return lc.getSubgenus();
        case SPECIES:
          return lc.getSpecies();
      }
    }
    return null;
  }

  /**
   * Sets a higher taxon property by passing the rank of it.
   *
   * @param lc   the LinneanClassification on which to set the new property
   * @param rank the higher linnean rank to set
   * @param name the higher ranks name
   */
  public static void setHigherRank(LinneanClassification lc, Rank rank, String name) {
    if (rank != null) {
      switch (rank) {
        case KINGDOM:
          lc.setKingdom(name);
          break;
        case PHYLUM:
          lc.setPhylum(name);
          break;
        case CLASS:
          lc.setClazz(name);
          break;
        case ORDER:
          lc.setOrder(name);
          break;
        case FAMILY:
          lc.setFamily(name);
          break;
        case GENUS:
          lc.setGenus(name);
          break;
        case SUBGENUS:
          lc.setSubgenus(name);
          break;
        case SPECIES:
          lc.setSpecies(name);
          break;
      }
    }
  }

  /**
   * An ordered map with entries for all higher Linnean ranks down to species which are not null.
   * The map starts with the highest rank, e.g. the kingdom and maps the name usage key to its canonical name.
   *
   * @param lc the object that implements both LinneanClassification and LinneanClassificationKeys from which to build
   *           the map
   *
   * @return map of higher ranks
   */
  @NotNull
  public static <T extends LinneanClassification & LinneanClassificationKeys> LinkedHashMap<Integer, String> getHigherClassificationMap(
    T lc) {
    return getHigherClassificationBaseMap(lc);
  }

  /**
   * An ordered map with entries for all higher Linnean ranks down to the actual direct parent of this usage.
   * The map starts with the highest rank, e.g. the kingdom and maps the name usage key to its canonical name.
   * The name usage itself is never included, even though a higher rank might point to the usage itself.
   *
   * @param lc the object that implements both LinneanClassification and LinneanClassificationKeys from which to build
   *           the map
   *
   * @return map of higher ranks
   */
  @NotNull
  public static <T extends LinneanClassification & LinneanClassificationKeys> LinkedHashMap<Integer, String> getHigherClassificationMap(
    T lc, int key, Integer parentKey, String parent) {
    LinkedHashMap<Integer, String> map = getHigherClassificationBaseMap(lc);

    if (parentKey != null) {
      map.put(parentKey, parent);
    }
    // remove notion to this usage
    map.remove(key);

    return map;
  }

  private static <T extends LinneanClassification & LinneanClassificationKeys> LinkedHashMap<Integer, String> getHigherClassificationBaseMap(
    T lc) {
    LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
    if (lc.getKingdomKey() != null) {
      map.put(lc.getKingdomKey(), lc.getKingdom());
    }
    if (lc.getPhylumKey() != null) {
      map.put(lc.getPhylumKey(), lc.getPhylum());
    }
    if (lc.getClassKey() != null) {
      map.put(lc.getClassKey(), lc.getClazz());
    }
    if (lc.getOrderKey() != null) {
      map.put(lc.getOrderKey(), lc.getOrder());
    }
    if (lc.getFamilyKey() != null) {
      map.put(lc.getFamilyKey(), lc.getFamily());
    }
    if (lc.getGenusKey() != null) {
      map.put(lc.getGenusKey(), lc.getGenus());
    }
    if (lc.getSpeciesKey() != null) {
      map.put(lc.getSpeciesKey(), lc.getSpecies());
    }
    return map;
  }

  /**
   * Gets a higher taxon key by passing the rank of it.
   * Only Linnean ranks are supported.
   *
   * @param lck  the classification that holds the taxon key
   * @param rank the higher linnean rank to retrieve
   *
   * @return the key of the higher taxon or null if rank doesnt exist
   */
  @Nullable
  public static Integer getHigherRankKey(LinneanClassificationKeys lck, Rank rank) {
    if (rank != null) {
      switch (rank) {
        case KINGDOM:
          return lck.getKingdomKey();
        case PHYLUM:
          return lck.getPhylumKey();
        case CLASS:
          return lck.getClassKey();
        case ORDER:
          return lck.getOrderKey();
        case FAMILY:
          return lck.getFamilyKey();
        case GENUS:
          return lck.getGenusKey();
        case SUBGENUS:
          return lck.getSubgenusKey();
        case SPECIES:
          return lck.getSpeciesKey();
      }
    }
    return null;
  }

  /**
   * Sets a higher taxon property by passing the rank of it.
   *
   * @param lck      the classification on which to set the taxon property
   * @param rank     the higher rank to set
   * @param usageKey key of the higher ranks usage
   */
  public static void setHigherRankKey(LinneanClassificationKeys lck, Rank rank, Integer usageKey) {
    if (rank != null) {
      switch (rank) {
        case KINGDOM:
          lck.setKingdomKey(usageKey);
          break;
        case PHYLUM:
          lck.setPhylumKey(usageKey);
          break;
        case CLASS:
          lck.setClassKey(usageKey);
          break;
        case ORDER:
          lck.setOrderKey(usageKey);
          break;
        case FAMILY:
          lck.setFamilyKey(usageKey);
          break;
        case GENUS:
          lck.setGenusKey(usageKey);
          break;
        case SUBGENUS:
          lck.setSubgenusKey(usageKey);
          break;
        case SPECIES:
          lck.setSpeciesKey(usageKey);
          break;
      }
    }
  }

  /**
   * Sets a higher taxon property by passing the rank of it.
   *
   * @param lc       the object that implements both LinneanClassification and LinneanClassificationKeys on which to set
   *                 the taxon property
   * @param rank     the higher linnean rank to set
   * @param name     the higher ranks name
   * @param usageKey key of the higher ranks usage
   */
  public static <T extends LinneanClassification & LinneanClassificationKeys> void setHigherRank(
    T lc, Rank rank, String name, Integer usageKey
  ) {
    setHigherRank(lc, rank, name);
    setHigherRankKey(lc, rank, usageKey);
  }

  /**
   * @return true if at least one higher rank of the classification is given, i.e. not null or empty
   */
  public static boolean hasContent(LinneanClassification lc) {
    for (Rank qr : Rank.DWC_RANKS) {
      if (StringUtils.isNotEmpty(getHigherRank(lc, qr))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Copies all linnean classification based higher taxon keys from one instance to another.
   */
  public static void copyLinneanClassificationKeys(LinneanClassificationKeys source, LinneanClassificationKeys target) {
    for (Rank r : Rank.DWC_RANKS) {
      setHigherRankKey(target, r, source.getHigherRankKey(r));
    }
  }

  /**
   * Copies all linnean classification based higher taxon names from one instance to another.
   */
  public static void copyLinneanClassification(LinneanClassification source, LinneanClassification target) {
    for (Rank r : Rank.DWC_RANKS) {
      setHigherRank(target, r, source.getHigherRank(r));
    }
  }

  /**
   * Utility class.
   */
  private ClassificationUtils() {
  }
}
