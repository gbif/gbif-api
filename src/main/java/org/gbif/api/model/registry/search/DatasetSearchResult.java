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
package org.gbif.api.model.registry.search;

import org.gbif.api.model.common.DOI;
import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;
import org.gbif.api.vocabulary.License;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * The dataset search model object for faceted and full text search.
 */
public class DatasetSearchResult {

  private UUID key;
  private String title;
  private DOI doi;
  private String description;
  private DatasetType type;
  private DatasetSubtype subtype;
  private String fullText;
  private UUID hostingOrganizationKey;
  private String hostingOrganizationTitle;
  private Country hostingCountry;
  private String publisherTitle;
  private Set<Country> countryCoverage;
  private Set<Continent> continent;
  private Country publishingCountry;
  private UUID publishingOrganizationKey;
  private String publishingOrganizationTitle;
  private UUID endorsingNodeKey;
  private List<UUID> networkKeys;
  private License license;
  private List<Integer> decades;
  private List<String> keywords;
  private String projectIdentifier;
  private Integer recordCount;
  private Integer nameUsagesCount;
  private Set<String> category;

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public DOI getDoi() {
    return doi;
  }

  public void setDoi(DOI doi) {
    this.doi = doi;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DatasetType getType() {
    return type;
  }

  public void setType(DatasetType type) {
    this.type = type;
  }

  public DatasetSubtype getSubtype() {
    return subtype;
  }

  public void setSubtype(DatasetSubtype subtype) {
    this.subtype = subtype;
  }

  public String getFullText() {
    return fullText;
  }

  public void setFullText(String fullText) {
    this.fullText = fullText;
  }

  public UUID getHostingOrganizationKey() {
    return hostingOrganizationKey;
  }

  public void setHostingOrganizationKey(UUID hostingOrganizationKey) {
    this.hostingOrganizationKey = hostingOrganizationKey;
  }

  public String getHostingOrganizationTitle() {
    return hostingOrganizationTitle;
  }

  public void setHostingOrganizationTitle(String hostingOrganizationTitle) {
    this.hostingOrganizationTitle = hostingOrganizationTitle;
  }

  public Country getHostingCountry() {
    return hostingCountry;
  }

  public void setHostingCountry(Country hostingCountry) {
    this.hostingCountry = hostingCountry;
  }

  public String getPublisherTitle() {
    return publisherTitle;
  }

  public void setPublisherTitle(String publisherTitle) {
    this.publisherTitle = publisherTitle;
  }

  public UUID getEndorsingNodeKey() {
    return endorsingNodeKey;
  }

  public void setEndorsingNodeKey(UUID endorsingNodeKey) {
    this.endorsingNodeKey = endorsingNodeKey;
  }

  /**
   * Currently not populated.
   * TODO: http://dev.gbif.org/issues/browse/REG-393
   */
  public Set<Country> getCountryCoverage() {
    return countryCoverage;
  }

  public void setCountryCoverage(Set<Country> countryCoverage) {
    this.countryCoverage = countryCoverage;
  }

  /**
   * Currently not populated.
   * TODO: http://dev.gbif.org/issues/browse/REG-393
   */
  public Set<Continent> getContinent() {
    return continent;
  }

  public void setContinent(Set<Continent> continent) {
    this.continent = continent;
  }

  public Country getPublishingCountry() {
    return publishingCountry;
  }

  public void setPublishingCountry(Country publishingCountry) {
    this.publishingCountry = publishingCountry;
  }

  public UUID getPublishingOrganizationKey() {
    return publishingOrganizationKey;
  }

  public void setPublishingOrganizationKey(UUID publishingOrganizationKey) {
    this.publishingOrganizationKey = publishingOrganizationKey;
  }

  public String getPublishingOrganizationTitle() {
    return publishingOrganizationTitle;
  }

  public void setPublishingOrganizationTitle(String publishingOrganizationTitle) {
    this.publishingOrganizationTitle = publishingOrganizationTitle;
  }

  public List<UUID> getNetworkKeys() {
    return networkKeys;
  }

  public void setNetworkKeys(List<UUID> networkKeys) {
    this.networkKeys = networkKeys;
  }

  public License getLicense() {
    return license;
  }

  public void setLicense(License license) {
    this.license = license;
  }

  public List<Integer> getDecades() {
    return decades;
  }

  public void setDecades(List<Integer> decades) {
    this.decades = decades;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Integer getRecordCount() {
    return recordCount;
  }

  public void setRecordCount(Integer recordCount) {
    this.recordCount = recordCount;
  }

  public Integer getNameUsagesCount() {
    return nameUsagesCount;
  }

  public void setNameUsagesCount(Integer nameUsagesCount) {
    this.nameUsagesCount = nameUsagesCount;
  }

  public Set<String> getCategory() {
    return category;
  }

  public void setCategory(Set<String> category) {
    this.category = category;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatasetSearchResult that = (DatasetSearchResult) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(title, that.title) &&
      Objects.equals(doi, that.doi) &&
      Objects.equals(description, that.description) &&
      type == that.type &&
      subtype == that.subtype &&
      Objects.equals(fullText, that.fullText) &&
      Objects.equals(hostingOrganizationKey, that.hostingOrganizationKey) &&
      Objects.equals(hostingOrganizationTitle, that.hostingOrganizationTitle) &&
      Objects.equals(publisherTitle, that.publisherTitle) &&
      Objects.equals(countryCoverage, that.countryCoverage) &&
      Objects.equals(continent, that.continent) &&
      publishingCountry == that.publishingCountry &&
      Objects.equals(publishingOrganizationKey, that.publishingOrganizationKey) &&
      Objects.equals(publishingOrganizationTitle, that.publishingOrganizationTitle) &&
      license == that.license &&
      Objects.equals(decades, that.decades) &&
      Objects.equals(keywords, that.keywords) &&
      Objects.equals(projectIdentifier, that.projectIdentifier) &&
      Objects.equals(recordCount, that.recordCount) &&
      Objects.equals(networkKeys, that.networkKeys) &&
      Objects.equals(nameUsagesCount, that.nameUsagesCount) &&
      Objects.equals(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, title, doi, description, type, subtype, fullText, hostingOrganizationKey,
      hostingOrganizationTitle, publisherTitle, countryCoverage, continent, publishingCountry,
      publishingOrganizationKey, publishingOrganizationTitle, license, decades, keywords,
      projectIdentifier, recordCount, networkKeys, nameUsagesCount, category);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DatasetSearchResult.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("title='" + title + "'")
      .add("doi='" + doi + "'")
      .add("description='" + description + "'")
      .add("type=" + type)
      .add("subtype=" + subtype)
      .add("fullText='" + fullText + "'")
      .add("hostingOrganizationKey=" + hostingOrganizationKey)
      .add("hostingOrganizationTitle='" + hostingOrganizationTitle + "'")
      .add("publisherTitle='" + publisherTitle + "'")
      .add("countryCoverage=" + countryCoverage)
      .add("continent=" + continent)
      .add("publishingCountry=" + publishingCountry)
      .add("publishingOrganizationKey=" + publishingOrganizationKey)
      .add("publishingOrganizationTitle='" + publishingOrganizationTitle + "'")
      .add("license=" + license)
      .add("decades=" + decades)
      .add("keywords=" + keywords)
      .add("projectIdentifier='" + projectIdentifier + "'")
      .add("recordCount=" + recordCount)
      .add("networkKeys=" + networkKeys)
      .add("nameUsagesCount=" + nameUsagesCount)
      .add("category=" + category)
      .toString();
  }
}
