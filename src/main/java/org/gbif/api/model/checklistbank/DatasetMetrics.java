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

import org.gbif.api.vocabulary.Extension;
import org.gbif.api.vocabulary.Kingdom;
import org.gbif.api.vocabulary.Language;
import org.gbif.api.vocabulary.NameUsageIssue;
import org.gbif.api.vocabulary.Origin;
import org.gbif.api.vocabulary.Rank;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * Simple metrics about a single, processed checklist dataset in time.
 * The created timestamp with the dataset key should be unique and can be used to create graphs over time.
 */
public class DatasetMetrics {
  private int key;
  private UUID datasetKey;
  private int usagesCount;
  private int synonymsCount;
  private int distinctNamesCount;
  private int nubMatchingCount;
  private int colMatchingCount;
  private int nubCoveragePct;
  private int colCoveragePct;
  // breakdown by constituent
  private Map<UUID, Integer> countByConstituent = Maps.newHashMap();
  // breakdown by kingdom
  private Map<Kingdom, Integer> countByKingdom = Maps.newHashMap();
  // breakdown by rank
  private Map<Rank, Integer> countByRank = Maps.newHashMap();
  // breakdown common names by language
  private Map<Language, Integer> countNamesByLanguage = Maps.newHashMap();
  // number of extension records by extension
  private Map<Extension, Integer> countExtRecordsByExtension = Maps.newHashMap();
  // breakdown by kingdom
  private Map<Origin, Integer> countByOrigin = Maps.newHashMap();
  // breakdown by issue
  private Map<NameUsageIssue, Integer> countByIssue = Maps.newHashMap();
  // any other dynamic counts
  private Map<String, Integer> otherCount = Maps.newHashMap();
  private Date created;
  private Date downloaded;


  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  /**
   * @return percentage of distinct names that match a name in the Catalogue of Life
   */
  @Max(100)
  @Min(0)
  public int getColCoveragePct() {
    return colCoveragePct;
  }

  public void setColCoveragePct(int colCoveragePct) {
    this.colCoveragePct = colCoveragePct;
  }

  @NotNull
  public Map<UUID, Integer> getCountByConstituent() {
    return countByConstituent;
  }

  public void setCountByConstituent(Map<UUID, Integer> countByConstituent) {
    this.countByConstituent = countByConstituent;
  }

  @NotNull
  public Map<Kingdom, Integer> getCountByKingdom() {
    return countByKingdom;
  }

  public void setCountByKingdom(Map<Kingdom, Integer> countByKingdom) {
    this.countByKingdom = countByKingdom;
  }

  @NotNull
  public Map<Rank, Integer> getCountByRank() {
    return countByRank;
  }

  public void setCountByRank(Map<Rank, Integer> countByRank) {
    this.countByRank = countByRank;
  }

  /**
   * @return the number of distinct canonical name strings
   */
  @Min(0)
  public int getDistinctNamesCount() {
    return distinctNamesCount;
  }

  public void setDistinctNamesCount(int distinctNamesCount) {
    this.distinctNamesCount = distinctNamesCount;
  }

  @NotNull
  public Map<Extension, Integer> getCountExtRecordsByExtension() {
    return countExtRecordsByExtension;
  }

  public void setCountExtRecordsByExtension(Map<Extension, Integer> countExtRecordsByExtension) {
    this.countExtRecordsByExtension = countExtRecordsByExtension;
  }

  /**
   * @return number of records matching a name in the Catalogue of Life
   */
  @Min(0)
  public int getColMatchingCount() {
    return colMatchingCount;
  }

  public void setColMatchingCount(int colMatchingCount) {
    this.colMatchingCount = colMatchingCount;
  }

  /**
   * @return number of records matching a name in the GBIF backbone taxonomy
   */
  @Min(0)
  public int getNubMatchingCount() {
    return nubMatchingCount;
  }

  public void setNubMatchingCount(int nubMatchingCount) {
    this.nubMatchingCount = nubMatchingCount;
  }

  @NotNull
  public Map<Language, Integer> getCountNamesByLanguage() {
    return countNamesByLanguage;
  }

  public void setCountNamesByLanguage(Map<Language, Integer> countNamesByLanguage) {
    this.countNamesByLanguage = countNamesByLanguage;
  }

  /**
   * @return number of records with a taxonomic status of a synonym.
   *         For occurrence records the nub taxonomy status is used
   */
  @Min(0)
  public int getSynonymsCount() {
    return synonymsCount;
  }

  public void setSynonymsCount(int synonymsCount) {
    this.synonymsCount = synonymsCount;
  }

  /**
   * @return total number of name usage records in checklistbank
   */
  @Min(0)
  public int getUsagesCount() {
    return usagesCount;
  }

  public void setUsagesCount(int usagesCount) {
    this.usagesCount = usagesCount;
  }

  /**
   * @return map of total name usage counts by their origin
   */
  @NotNull
  public Map<Origin, Integer> getCountByOrigin() {
    return countByOrigin;
  }

  public void setCountByOrigin(Map<Origin, Integer> countByOrigin) {
    this.countByOrigin = countByOrigin;
  }

  /**
   * @return map of total name usage counts by their interpretation issue
   */
  @NotNull
  public Map<NameUsageIssue, Integer> getCountByIssue() {
    return countByIssue;
  }

  public void setCountByIssue(Map<NameUsageIssue, Integer> countByIssue) {
    this.countByIssue = countByIssue;
  }

  /**
   * @return date this metric was generated. Roughly equivalent with date of indexing
   */
  @NotNull
  @Past
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * @return date new dataset data was downloaded/harvested last time
   */
  @NotNull
  @Past
  public Date getDownloaded() {
    return downloaded;
  }

  public void setDownloaded(Date downloaded) {
    this.downloaded = downloaded;
  }

  @NotNull
  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  /**
   * @return percentage of distinct names that match a name in the GBIF backbone taxonomy
   */
  @Max(100)
  @Min(0)
  public int getNubCoveragePct() {
    return nubCoveragePct;
  }

  public void setNubCoveragePct(int nubCoveragePct) {
    this.nubCoveragePct = nubCoveragePct;
  }

  /**
   * For an occurrence dataset get the number of records that are interpreted to belong to a certain nub kingdom.
   * For checklists the number of usages belonging to a certain nub kingdom. Note this is not the kingdom as explicitly
   * given by the checklist, but the nub kingdom after matching the usages to the nub.
   *
   * @param kingdom to get metrics for
   *
   * @return the number of records found in the respective kingdom
   */
  @Min(0)
  public int getCountByKingdom(Kingdom kingdom) {
    return getCountFromMap(countByKingdom, kingdom);
  }

  /**
   * Get the metrics by taxonomic rank, i.e. the lowest rank to which an occurrence record was identified
   * or the rank of the name usage in a checklist.
   *
   * @param rank to get metrics for. Mayor Linnéan ranks only down to species plus INFRASPECIFIC_NAME.
   *
   * @return the number of records for the given taxonomic rank.
   */
  @Min(0)
  public int getCountByRank(Rank rank) {
    return getCountFromMap(countByRank, rank);
  }

  /**
   * Get the metrics by name usage origin.
   */
  @Min(0)
  public int getCountByOrigin(Origin origin) {
    return getCountFromMap(countByOrigin, origin);
  }

  /**
   * Get the metrics by name usage issue.
   */
  @Min(0)
  public int getCountByIssue(NameUsageIssue issue) {
    return getCountFromMap(countByIssue, issue);
  }

  /**
   * Get the metrics for other dynamic counts.
   */
  @Min(0)
  public int getOtherCount(String key) {
    return getCountFromMap(otherCount, key);
  }

  /**
   * Number of vernacular names in this checklist dataset by language.
   * For occurrence datasets always 0.
   *
   * @param language of common names
   *
   * @return the number of records found for the extension
   */
  @Min(0)
  public int getCountNamesByLanguage(Language language) {
    return getCountFromMap(countNamesByLanguage, language);
  }

  /**
   * Number of extension records found in the dataset.
   *
   * @return the number of records found for the extension
   */
  @Min(0)
  public int getExtensionRecordCount(Extension extension) {
    return getCountFromMap(countExtRecordsByExtension, extension);
  }

  public Map<String, Integer> getOtherCount() {
    return otherCount;
  }

  public void setOtherCount(Map<String, Integer> otherCount) {
    this.otherCount = otherCount;
  }

  /**
   * @return value from map if key exists, 0 otherwise
   */
  private int getCountFromMap(Map<?, Integer> map, Object key) {
    if (key == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    if (map.containsKey(key)) {
      return map.get(key);
    }
    return 0;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof DatasetMetrics)) {
      return false;
    }
    DatasetMetrics that = (DatasetMetrics) object;
    return  Objects.equal(this.key, that.key)
           && Objects.equal(this.datasetKey, that.datasetKey)
           && Objects.equal(this.usagesCount, that.usagesCount)
           && Objects.equal(this.synonymsCount, that.synonymsCount)
           && Objects.equal(this.distinctNamesCount, that.distinctNamesCount)
           && Objects.equal(this.nubMatchingCount, that.nubMatchingCount)
           && Objects.equal(this.colMatchingCount, that.colMatchingCount)
           && Objects.equal(this.nubCoveragePct, that.nubCoveragePct)
           && Objects.equal(this.colCoveragePct, that.colCoveragePct)
           && Objects.equal(this.countByConstituent, that.countByConstituent)
           && Objects.equal(this.countByKingdom, that.countByKingdom)
           && Objects.equal(this.countByRank, that.countByRank)
           && Objects.equal(this.countNamesByLanguage, that.countNamesByLanguage)
           && Objects.equal(this.countExtRecordsByExtension, that.countExtRecordsByExtension)
           && Objects.equal(this.countByOrigin, that.countByOrigin)
           && Objects.equal(this.countByIssue, that.countByIssue)
           && Objects.equal(this.otherCount, that.otherCount)
           && Objects.equal(this.created, that.created)
           && Objects.equal(this.downloaded, that.downloaded);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, datasetKey, usagesCount, synonymsCount, distinctNamesCount, nubMatchingCount,
      colMatchingCount, nubCoveragePct, colCoveragePct, countByConstituent, countByKingdom, countByRank,
      countNamesByLanguage, countExtRecordsByExtension, countByOrigin, countByIssue, otherCount, created, downloaded);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("datasetKey", datasetKey)
      .add("usagesCount", usagesCount)
      .add("synonymsCount", synonymsCount)
      .add("distinctNamesCount", distinctNamesCount)
      .add("nubMatchingCount", nubMatchingCount)
      .add("colMatchingCount", colMatchingCount)
      .add("nubCoveragePct", nubCoveragePct)
      .add("colCoveragePct", colCoveragePct)
      .add("countByConstituent", countByConstituent)
      .add("countByKingdom", countByKingdom)
      .add("countByRank", countByRank)
      .add("countNamesByLanguage", countNamesByLanguage)
      .add("countExtRecordsByExtension", countExtRecordsByExtension)
      .add("countByOrigin", countByOrigin)
      .add("countByIssue", countByIssue)
      .add("otherCount", otherCount)
      .add("created", created)
      .add("downloaded", downloaded)
      .toString();
  }
}
