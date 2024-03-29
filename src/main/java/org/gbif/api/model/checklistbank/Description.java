/*
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

import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Description Model Object represents a taxon description.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/description.xml">Description Definition</a>
 */
@SuppressWarnings("unused")
public class Description implements NameUsageExtension {

  private Integer key;
  private Integer taxonKey;
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
  @Schema(description = "A unique GBIF identifier for the description.\n\n" +
    "This key is used in the table of contents to retrieve the detailed description.")
  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * The name usage "taxon" key this description belongs to.
   */
  @Schema(description = "The name usage “taxon“ key to which this species profile belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  /**
   * An entity responsible for making contributions to the textual information provided for a description.
   *
   * @return the contributor
   */
  @Schema(description = "An entity responsible for making contributions to the textual information provided for a description.")
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
  @Schema(description = "The author(s) of the textual information provided for a description.")
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
   * formatted with basic HTML tags, i.e. h1-4,p,i,b,a,img,ul and li. All other tags should be removed.
   *
   * @return the description
   */
  @Schema(description = "Any descriptive, free text matching the category given as dc:type.\n\n" +
    "The text should be either plain text or HTML.")
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
   * ISO 639-1 language code used for the description.
   *
   * @return the language
   */
  @Schema(description = "ISO 639-1 language code used for the description.")
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
  @Schema(description = "Official permission to do something with the resource.")
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
  @Schema(description = "Bibliographic citation referencing a source for the description.")
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

  @Schema(description = "The name usage key of the taxon in the checklist including this description.")
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
   * @see <a href="https://rs.gbif.org/vocabulary/gbif/descriptionType">Description type definition</a>
   */
  @Schema(description = "The type used to categorize paragraphs of a taxon description.\n\n" +
    "See the [Description Type vocabulary](http://rs.gbif.org/vocabulary/gbif/descriptionType).")
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Description that = (Description) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(taxonKey, that.taxonKey) &&
      Objects.equals(type, that.type) &&
      language == that.language &&
      Objects.equals(description, that.description) &&
      Objects.equals(source, that.source) &&
      Objects.equals(sourceTaxonKey, that.sourceTaxonKey) &&
      Objects.equals(creator, that.creator) &&
      Objects.equals(contributor, that.contributor) &&
      Objects.equals(license, that.license);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, taxonKey, type, language, description, source, sourceTaxonKey, creator,
      contributor, license);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Description.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("taxonKey=" + taxonKey)
      .add("type='" + type + "'")
      .add("language=" + language)
      .add("description='" + description + "'")
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("creator='" + creator + "'")
      .add("contributor='" + contributor + "'")
      .add("license='" + license + "'")
      .toString();
  }
}
