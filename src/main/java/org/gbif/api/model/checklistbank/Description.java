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

import org.gbif.api.vocabulary.Language;

import javax.annotation.Nullable;

import com.google.common.base.Objects;


/**
 * Description Model Object represents a taxon description.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/description.xml">Description Definition</a>
 */
public class Description implements NameUsageExtension {

  private Integer key;
  private String type;
  private Language language;
  private String description;
  private String source;
  private Integer sourceTaxonKey;
  private String creator;
  private String contributor;
  private String license;

  /**
   * A unique GBIF identifier for any description.
   * This key is used in the table of contents to retrieve the detailed description.
   */
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * An entity responsible for making contributions to the textual information provided for a description.
   *
   * @return the contributor
   */
  @Nullable
  public String getContributor() {
    return contributor;
  }

  /**
   * @param contributor the contributor to set
   */
  public void setContributor(String contributor) {
    this.contributor = contributor;
  }

  /**
   * The author(s) of the textual information provided for a description.
   *
   * @return the creator
   */
  @Nullable
  public String getCreator() {
    return creator;
  }

  /**
   * @param creator the creator to set.
   */
  public void setCreator(String creator) {
    this.creator = creator;
  }

  /**
   * Any descriptive free text matching the category given as dc:type. The text should be either plain text or
   * formatted with basic html tags, i.e. h1-4,p,i,b,a,img,ul and li. All other tags should be removed.
   *
   * @return the description
   */
  @Nullable
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * ISO 639-1 language code used for the vernacular name value.
   *
   * @return the language
   */
  @Nullable
  public Language getLanguage() {
    return language;
  }

  /**
   * @param language the language to set
   */
  public void setLanguage(Language language) {
    this.language = language;
  }

  /**
   * Official permission to do something with the resource. Please use Creative Commons URIs if you can. <br/>
   * <blockquote>
   * <p>
   * <i>Example:</i> CC-BY
   * </p>
   * </blockquote>
   *
   * @return the license.
   */
  @Nullable
  public String getLicense() {
    return license;
  }

  /**
   * @param license the license to set
   */
  public void setLicense(String license) {
    this.license = license;
  }

  /**
   * Source reference of this description, a URL or full publication citation.
   *
   * @return the source
   */
  @Nullable
  @Override
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  @Override
  public void setSource(String source) {
    this.source = source;
  }

  @Nullable
  @Override
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  @Override
  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  /**
   * Returns the type used to categorize paragraphs of a taxon description.
   * Given the list of types is so broad, an Enum is not used to maintain it. Rather it is kept as plain text.
   *
   * @return the type
   *
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/description_type.xml">Description type definition</a>
   */
  @Nullable
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Description)) {
      return false;
    }

    Description that = (Description) object;
    return Objects.equal(this.type, that.type)
           && Objects.equal(this.language, that.language)
           && Objects.equal(this.description, that.description)
           && Objects.equal(this.source, that.source)
           && Objects.equal(this.sourceTaxonKey, that.sourceTaxonKey)
           && Objects.equal(this.creator, that.creator)
           && Objects.equal(this.contributor, that.contributor)
           && Objects.equal(this.license, that.license);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(type, language, description, source, creator, contributor, license);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("type", type)
      .add("language", language)
      .add("description", description)
      .add("source", source)
      .add("sourceTaxonKey", sourceTaxonKey)
      .add("creator", creator)
      .add("contributor", contributor)
      .add("license", license)
      .toString();
  }

}
