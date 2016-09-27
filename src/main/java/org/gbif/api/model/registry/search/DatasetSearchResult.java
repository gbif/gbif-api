package org.gbif.api.model.registry.search;

import org.gbif.api.vocabulary.Continent;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.DatasetSubtype;
import org.gbif.api.vocabulary.DatasetType;
import org.gbif.api.vocabulary.License;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.base.Objects;

/**
 * The dataset search model object for faceted and full text search.
 */
public class DatasetSearchResult {

  private UUID key;
  private String title;
  private String description;
  private DatasetType type;
  private DatasetSubtype subtype;
  private String fullText;
  private UUID hostingOrganizationKey;
  private String hostingOrganizationTitle;
  private String publisherTitle;
  private Set<Country> countryCoverage;
  private Set<Continent> continent;
  private Country publishingCountry;
  private UUID publishingOrganizationKey;
  private String publishingOrganizationTitle;
  private License license;
  private List<Integer> decades;
  private List<String> keywords;
  private String projectIdentifier;

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

  public String getPublisherTitle() {
    return publisherTitle;
  }

  public void setPublisherTitle(String publisherTitle) {
    this.publisherTitle = publisherTitle;
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

  @Override
  public int hashCode() {
    return Objects
      .hashCode(key, title, description, type, subtype, fullText, hostingOrganizationKey, hostingOrganizationTitle,
        publisherTitle, countryCoverage, continent, publishingCountry, publishingOrganizationKey, publishingOrganizationTitle,
        decades, keywords, license, projectIdentifier);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof DatasetSearchResult) {
      DatasetSearchResult that = (DatasetSearchResult) object;
      return Objects.equal(this.key, that.key)
        && Objects.equal(this.title, that.title)
        && Objects.equal(this.description, that.description)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.subtype, that.subtype)
        && Objects.equal(this.fullText, that.fullText)
        && Objects.equal(this.hostingOrganizationKey, that.hostingOrganizationKey)
        && Objects.equal(this.hostingOrganizationTitle, that.hostingOrganizationTitle)
        && Objects.equal(this.publisherTitle, that.publisherTitle)
        && Objects.equal(this.countryCoverage, that.countryCoverage)
        && Objects.equal(this.continent, that.continent)
        && Objects.equal(this.publishingCountry, that.publishingCountry)
        && Objects.equal(this.publishingOrganizationKey, that.publishingOrganizationKey)
        && Objects.equal(this.publishingOrganizationTitle, that.publishingOrganizationTitle)
        && Objects.equal(this.decades, that.decades)
        && Objects.equal(this.keywords, that.keywords)
        && Objects.equal(this.license, that.license)
        && Objects.equal(this.projectIdentifier, that.projectIdentifier);
    }
    return false;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("key", key)
      .add("title", title)
      .add("description", description)
      .add("type", type)
      .add("subtype", subtype)
      .add("fullText", fullText)
      .add("hostingOrganizationKey", hostingOrganizationKey)
      .add("hostingOrganizationTitle", hostingOrganizationTitle)
      .add("publisherTitle", publisherTitle)
      .add("countryCoverage", countryCoverage)
      .add("continent", continent)
      .add("publishingCountry", publishingCountry)
      .add("publishingOrganizationKey", publishingOrganizationKey)
      .add("publishingOrganizationTitle", publishingOrganizationTitle)
      .add("decades", decades)
      .add("keywords", keywords)
      .add("license", license)
      .add("projectIdentifier", projectIdentifier)
      .toString();
  }
}
