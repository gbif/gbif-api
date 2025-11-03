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

import java.util.Objects;
import java.util.StringJoiner;

import jakarta.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpeciesProfile Model Object represents a species profile which describes basic species characteristics.
 *
 * @see <a href="http://rs.gbif.org/terms/1.0/SpeciesProfile">Species Profile Extension Definition</a>
 */
@SuppressWarnings("unused")
public class SpeciesProfile implements NameUsageExtension {

  private Integer taxonKey;
  private String livingPeriod;
  private String lifeForm;
  private String habitat;
  private Boolean marine;
  private Boolean freshwater;
  private Boolean terrestrial;
  private Boolean extinct;
  private Boolean hybrid;
  private Integer ageInDays;
  private Integer sizeInMillimeter;
  private Integer massInGram;
  private String source;
  private Integer sourceTaxonKey;

  /**
   * The name usage "taxon" key this species profile belongs to.
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
   * Maximum observed age of an organism given as number of days.
   * <blockquote>
   * <p>
   * <i>Example:</i> 5.
   * </p>
   * </blockquote>
   *
   * @return the ageInDays
   */
  @Schema(description = "Maximum observed age of an organism given as number of days.")
  @Nullable
  public Integer getAgeInDays() {
    return ageInDays;
  }

  /**
   * @param ageInDays the ageInDays to set
   */
  public void setAgeInDays(Integer ageInDays) {
    this.ageInDays = ageInDays;
  }

  /**
   * A Boolean flag indicating whether the taxon is a freshwater organism.
   *
   * @return freshwater flag
   */
  @Schema(description = "Flag indicating whether the taxon is a freshwater organism.")
  @Nullable
  public Boolean isFreshwater() {
    return freshwater;
  }

  public void setFreshwater(Boolean freshwater) {
    this.freshwater = freshwater;
  }

  /**
   * Comma separated list of mayor habitat classification as defined by IUCN in which a species is known to exist:
   * <a href="https://rs.gbif.org/vocabulary/iucn/habitat/">Habitats Vocabulary</a>
   * <blockquote>
   * <p>
   * <i>Example:</i> 1.1.
   * </p>
   * </blockquote>
   *
   * @return the habitat
   */
  @Schema(description = "Comma-separated list of major habitat classifications as defined by the IUCN in which " +
    "a species is known to exist.\n\n" +
    "See [Habitats vocabulary](https://rs.gbif.org/vocabulary/iucn/habitat/).")
  @Nullable
  public String getHabitat() {
    return habitat;
  }

  /**
   * @param habitat the habitat to set.
   */
  public void setHabitat(String habitat) {
    this.habitat = habitat;
  }

  /**
   * A term describing the growth/lifeform of an organism. Should be based on a vocabulary like Raunkiær for plants:
   * <a href="http://en.wikipedia.org/wiki/Raunkiær_plant_life-form">http://en.wikipedia.org/wiki/Raunkiær_plant_life-
   * form</a>. Recommended vocabulary:
   * <a href="https://rs.gbif.org/vocabulary/gbif/life_form">http://rs.gbif.org/vocabulary/gbif/life_form.xml</a>
   * <blockquote>
   * <p>
   * <i>Example:</i> Phanerophyte.
   * </p>
   * </blockquote>
   *
   * @return the lifeForm
   */
  @Schema(description = "A term describing the growth/lifeform of an organism.\n\n" +
    "See [Life Form vocabulary](https://rs.gbif.org/vocabulary/gbif/life_form).")
  @Nullable
  public String getLifeForm() {
    return lifeForm;
  }

  /**
   * @param lifeForm the lifeForm to set
   */
  public void setLifeForm(String lifeForm) {
    this.lifeForm = lifeForm;
  }

  /**
   * The (geological) time a currently extinct organism is known to have lived. For geological times of fossils ideally
   * based on a vocabulary like <a
   * href="http://en.wikipedia.org/wiki/Geologic_column">http://en.wikipedia.org/wiki/Geologic_column</a>
   * <blockquote>
   * <p>
   * <i>Example:</i> Cretaceous.
   * </p>
   * </blockquote>
   *
   * @return the livingPeriod
   */
  @Schema(description = "The geological time a currently extinct organism is known to have lived.")
  @Nullable
  public String getLivingPeriod() {
    return livingPeriod;
  }

  /**
   * @param livingPeriod the livingPeriod to set
   */
  public void setLivingPeriod(String livingPeriod) {
    this.livingPeriod = livingPeriod;
  }

  /**
   * Maximum observed weight of an organism in grams.
   * <blockquote>
   * <p>
   * <i>Example:</i> 12.
   * </p>
   * </blockquote>
   *
   * @return the massInGram
   */
  @Schema(description = "Maximum observed weight of an organism in grams.")
  @Nullable
  public Integer getMassInGram() {
    return massInGram;
  }

  /**
   * @param massInGram the massInGram to set
   */
  public void setMassInGram(Integer massInGram) {
    this.massInGram = massInGram;
  }

  /**
   * Maximum observed size of an organism in millimetres. Can be either height, length or width, whichever is greater.
   * <blockquote>
   * <p>
   * <i>Example:</i> 1500.
   * </p>
   * </blockquote>
   *
   * @return the sizeInMillimeter
   */
  @Schema(description = "Maximum observed size of an organism in millimetres.\n\n" +
    "Can be either height, length or width, whichever is greater.")
  @Nullable
  public Integer getSizeInMillimeter() {
    return sizeInMillimeter;
  }

  /**
   * @param sizeInMillimeter the sizeInMillimeter to se
   */
  public void setSizeInMillimeter(Integer sizeInMillimeter) {
    this.sizeInMillimeter = sizeInMillimeter;
  }

  /**
   * Flag indicating an extinct organism. Details about the timeperiod the organism has lived in can be supplied below.
   * <blockquote>
   * <p>
   * <i>Example:</i> true - false.
   * </p>
   * </blockquote>
   *
   * @return the extinct
   */
  @Schema(description = "Flag indicating an extinct organism.")
  @Nullable
  public Boolean isExtinct() {
    return extinct;
  }

  /**
   * @param extinct the extinct to set
   */
  public void setExtinct(Boolean extinct) {
    this.extinct = extinct;
  }

  /**
   * Flag indicating a hybrid organism. This does not have to be reflected in the name, but can be based on other
   * studies like chromosome numbers etc.
   * <blockquote>
   * <p>
   * <i>Example:</i> true - false.
   * </p>
   * </blockquote>
   *
   * @return the hybrid
   */
  @Schema(description = "Flag indicating a hybrid organism.")
  @Nullable
  public Boolean isHybrid() {
    return hybrid;
  }

  /**
   * @param hybrid the hybrid to set
   */
  public void setHybrid(Boolean hybrid) {
    this.hybrid = hybrid;
  }

  /**
   * A Boolean flag indicating whether the taxon is a marine organism, i.e. can be found in/above sea water
   * <blockquote>
   * <p>
   * <i>Example:</i> true - false.
   * </p>
   * </blockquote>
   *
   * @return is marine flag
   */
  @Schema(description = "A Boolean flag indicating whether the taxon is a marine organism, i.e. can be found in/above sea water.")
  @Nullable
  public Boolean isMarine() {
    return marine;
  }

  /**
   * @param marine the marine to set
   */
  public void setMarine(Boolean marine) {
    this.marine = marine;
  }

  /**
   * A Boolean flag indicating the taxon is a terrestrial organism, i.e. occurs on land as opposed to the sea.
   * <blockquote>
   * <p>
   * <i>Example:</i> true - false.
   * </p>
   * </blockquote>
   *
   * @return the terrestrial
   */
  @Schema(description = "A Boolean flag indicating the taxon is a terrestrial organism, i.e. occurs on land as opposed to the sea.")
  @Nullable
  public Boolean isTerrestrial() {
    return terrestrial;
  }

  /**
   * @param terrestrial the terrestrial to set
   */
  public void setTerrestrial(Boolean terrestrial) {
    this.terrestrial = terrestrial;
  }

  @Schema(description = "Bibliographic citation referencing a source for the species profile.")
  @Nullable
  @Override
  public String getSource() {
    return source;
  }

  @Override
  public void setSource(String source) {
    this.source = source;
  }

  @Schema(description = "The name usage key of the taxon in the checklist including this species profile.")
  @Nullable
  @Override
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  @Override
  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpeciesProfile that = (SpeciesProfile) o;
    return Objects.equals(livingPeriod, that.livingPeriod) &&
      Objects.equals(lifeForm, that.lifeForm) &&
      Objects.equals(habitat, that.habitat) &&
      Objects.equals(marine, that.marine) &&
      Objects.equals(terrestrial, that.terrestrial) &&
      Objects.equals(extinct, that.extinct) &&
      Objects.equals(hybrid, that.hybrid) &&
      Objects.equals(ageInDays, that.ageInDays) &&
      Objects.equals(sizeInMillimeter, that.sizeInMillimeter) &&
      Objects.equals(massInGram, that.massInGram) &&
      Objects.equals(source, that.source) &&
      Objects.equals(sourceTaxonKey, that.sourceTaxonKey);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(livingPeriod, lifeForm, habitat, marine, terrestrial, extinct, hybrid, ageInDays,
        sizeInMillimeter, massInGram, source, sourceTaxonKey);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SpeciesProfile.class.getSimpleName() + "[", "]")
      .add("livingPeriod='" + livingPeriod + "'")
      .add("lifeForm='" + lifeForm + "'")
      .add("habitat='" + habitat + "'")
      .add("marine=" + marine)
      .add("freshwater=" + freshwater)
      .add("terrestrial=" + terrestrial)
      .add("extinct=" + extinct)
      .add("hybrid=" + hybrid)
      .add("ageInDays=" + ageInDays)
      .add("sizeInMillimeter=" + sizeInMillimeter)
      .add("massInGram=" + massInGram)
      .add("source='" + source + "'")
      .add("sourceTaxonKey=" + sourceTaxonKey)
      .toString();
  }
}
