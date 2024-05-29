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


import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;
import org.gbif.api.vocabulary.LifeStage;
import org.gbif.api.vocabulary.Sex;

import java.util.Objects;
import java.util.StringJoiner;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * VernacularName Model Object represents a vernacular name for a scientific taxon.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/vernacularname.xml">Vernacular Name
 * Definition</a>
 */
@SuppressWarnings("unused")
public class VernacularName implements NameUsageExtension {

  private Integer taxonKey;
  private String vernacularName;
  private Language language;
  private LifeStage lifeStage;
  private Sex sex;
  private Country country;
  private String area;
  private String source;
  private Integer sourceTaxonKey;
  private Boolean preferred;
  private Boolean plural;

  /**
   * The name usage "taxon" key to which this vernacular name belongs.
   */
  @Schema(description = "The name usage “taxon“ key to which this vernacular name belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  /**
   * The area for the vernacular name.
   *
   * @return the area
   */
  @Schema(description = "The area where the vernacular name is used.")
  @Nullable
  public String getArea() {
    return area;
  }

  /**
   * @param area the area to set
   */
  public void setArea(String area) {
    this.area = area;
  }

  /**
   * The country in which the vernacular name is used.
   *
   * @return the country
   */
  @Schema(description = "The country or area in which the vernacular name is used.")
  @Nullable
  public Country getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * ISO 639-1 language code used for the vernacular name value.
   * <blockquote>
   * <p>
   * <i>Example:</i> es
   * </p>
   * </blockquote>
   *
   * @return the language
   */
  @Nullable
  @Schema(description = "The language (from ISO 639-1) of the vernacular name.")
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
   * The age class or life stage of the species for which the vernacular name applies. Best practice
   * is to utilise a controlled list of terms for this value.
   * <blockquote>
   * <p>
   * <i>Example:</i> "juvenile" is the life stage of the fish Pomatomus saltatrix for which the name
   * "snapper blue"
   * refers.
   * </p>
   * </blockquote>
   *
   * @return the lifeStage
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/life_stage.xml">Life Stage GBIF Vocabulary</a>
   */
  @Schema(description = "The age class or life stage of the species for which the vernacular name applies.\n\n" +
    "For example, a juvenile *Anser anser* is called a “gosling“.")
  @Nullable
  public LifeStage getLifeStage() {
    return lifeStage;
  }

  /**
   * @param lifeStage the lifeStage to set
   */
  public void setLifeStage(LifeStage lifeStage) {
    this.lifeStage = lifeStage;
  }

  /**
   * The sex (gender) of the taxon for which the vernacular name applies when the vernacular name is
   * limited to a specific gender of a species. If not limited sex should be empty. For example the
   * vernacular name "Buck" applies to the "Male" gender of the species, Odocoileus virginianus.
   * <blockquote>
   * <p>
   * <i>Example:</i> male.
   * </p>
   * </blockquote>
   *
   * @return the sex
   * @see <a href="http://rs.gbif.org/vocabulary/gbif/sex.xml">Sex GBIF Vocabulary</a>
   */
  @Schema(description = "The sex of the taxon for which the vernacular name applies when the name is limited to a " +
    "specific sex of a species.\n\n" +
    "For example, “goose“ can refer to either male or female *Anser*, but only males may be called “gander”.")
  @Nullable
  public Sex getSex() {
    return sex;
  }

  /**
   * @param sex the sex to set
   */
  public void setSex(Sex sex) {
    this.sex = sex;
  }

  /**
   * Bibliographic citation referencing a source where the vernacular name refers to the cited
   * species.
   * <blockquote>
   * <p>
   * <i>Example:</i> Peterson Field Guide to the Eastern Seashore, Houghton Mifflin Co, 1961, p131.
   * </p>
   * </blockquote>
   *
   * @return the source
   */
  @Schema(description = "Bibliographic citation referencing a source where the vernacular name refers to the " +
    "cited species.")
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

  @Schema(description = "The name usage key of the taxon in the checklist including this vernacular name.")
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
   * A common or vernacular name.
   * <blockquote>
   * <p>
   * <i>Example:</i> Andean Condor", "Condor Andino", "American Eagle", "Gänsegeier".
   * </p>
   * </blockquote>
   *
   * @return the vernacularName
   */
  @Schema(description = "A common or vernacular name.")
  @NotNull
  public String getVernacularName() {
    return vernacularName;
  }

  /**
   * @param vernacularName the vernacularName to set
   */
  public void setVernacularName(String vernacularName) {
    this.vernacularName = vernacularName;
  }

  /**
   * This value is true if the vernacular name it qualifies refers to a plural form of the name.
   * <blockquote>
   * <p>
   * <i>Example:</i> The term "Schoolies" is the plural form of a name used along the coastal
   * Northeastern U.S. for
   * groups of juvenile fish of the species, Morone saxatilis.
   * </p>
   * </blockquote>
   *
   * @return the plural
   * @see <a href="http://rs.gbif.org/vocabulary/basic/boolean.xml">Boolean Vocabulary</a>
   */
  @Schema(description = "True if the vernacular name refers to a plural form of the name.")
  @Nullable
  public Boolean isPlural() {
    return plural;
  }

  /**
   * @param plural the plural to set
   */
  public void setPlural(Boolean plural) {
    this.plural = plural;
  }

  /**
   * This term is true if the source citing the use of this vernacular name indicates the usage has
   * some preference or specific standing over other possible vernacular names used for the
   * species.
   * <blockquote>
   * <p>
   * <i>Example:</i> Some organisations have attempted to assign specific and unique vernacular
   * names for particular
   * taxon groups in a systematic attempt to bring order and consistency to the use of these names.
   * For example, the American Ornithological Union assigns the name "Pearl Kite" for the taxon,
   * Gampsonyx swainsonii. The value of isPreferredName for this record would be true.
   * </p>
   * </blockquote>
   *
   * @return the preferred
   * @see <a href="http://rs.gbif.org/vocabulary/basic/boolean.xml">Boolean Vocabulary</a>
   */
  @Schema(description = "This term is true if the source citing the use of this vernacular name indicates the usage " +
    "has some preference or specific standing over other possible vernacular names used for the species.")
  @Nullable
  public Boolean isPreferred() {
    return preferred;
  }

  /**
   * @param preferred the preferred to set
   */
  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VernacularName that = (VernacularName) o;
    return Objects.equals(vernacularName, that.vernacularName) &&
      language == that.language &&
      lifeStage == that.lifeStage &&
      sex == that.sex &&
      country == that.country &&
      Objects.equals(area, that.area) &&
      Objects.equals(source, that.source) &&
      Objects.equals(sourceTaxonKey, that.sourceTaxonKey) &&
      Objects.equals(preferred, that.preferred) &&
      Objects.equals(plural, that.plural);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vernacularName, language, lifeStage, sex, country, area, source,
      sourceTaxonKey, preferred, plural);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VernacularName.class.getSimpleName() + "[", "]")
      .add("vernacularName='" + vernacularName + "'")
      .add("language=" + language)
      .add("lifeStage=" + lifeStage)
      .add("sex=" + sex)
      .add("country=" + country)
      .add("area='" + area + "'")
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .add("preferred=" + preferred)
      .add("plural=" + plural)
      .toString();
  }
}
