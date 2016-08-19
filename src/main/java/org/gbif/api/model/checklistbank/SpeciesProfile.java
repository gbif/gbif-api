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

import javax.annotation.Nullable;

import com.google.common.base.Objects;


/**
 * SpeciesProfile Model Object represents a species profile which describes basic species characteristics.
 *
 * @see <a href="http://rs.gbif.org/extension/gbif/1.0/speciesprofile.xml">Species Profile Definition</a>
 */
public class SpeciesProfile implements NameUsageExtension {

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
   * Maximum observed age of an organism given as number of days.
   * <blockquote>
   * <p>
   * <i>Example:</i> 5.
   * </p>
   * </blockquote>
   *
   * @return the ageInDays
   */
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
  @Nullable
  public Boolean isFreshwater() {
    return freshwater;
  }

  public void setFreshwater(Boolean freshwater) {
    this.freshwater = freshwater;
  }

  /**
   * Comma seperated list of mayor habitat classification as defined by IUCN in which a species is known to exist:
   * <a href="http://www.iucnredlist.org/static/major_habitats">http://www.iucnredlist.org/static/major_habitats</a>
   * <blockquote>
   * <p>
   * <i>Example:</i> 1.1.
   * </p>
   * </blockquote>
   *
   * @return the habitat
   */
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
   * <a href="http://rs.gbif.org/vocabulary/gbif/life_form.xml">http://rs.gbif.org/vocabulary/gbif/life_form.xml</a>
   * <blockquote>
   * <p>
   * <i>Example:</i> Phanerophyte.
   * </p>
   * </blockquote>
   *
   * @return the lifeForm
   */
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
   * Maximum observed size of an organism in millimeter. Can be either height, length or width, whichever is greater.
   * <blockquote>
   * <p>
   * <i>Example:</i> 1500.
   * </p>
   * </blockquote>
   *
   * @return the sizeInMillimeter
   */
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
   * A Boolean flag indicating the taxon is a terrestial organism, i.e. occurrs on land as opposed to the sea.
   * <blockquote>
   * <p>
   * <i>Example:</i> true - false.
   * </p>
   * </blockquote>
   *
   * @return the terrestrial
   */
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

  @Nullable
  @Override
  public String getSource() {
    return source;
  }

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

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof SpeciesProfile)) {
      return false;
    }

    SpeciesProfile that = (SpeciesProfile) object;
    return Objects.equal(this.livingPeriod, that.livingPeriod)
           && Objects.equal(this.lifeForm, that.lifeForm)
           && Objects.equal(this.habitat, that.habitat)
           && Objects.equal(this.marine, that.marine)
           && Objects.equal(this.terrestrial, that.terrestrial)
           && Objects.equal(this.extinct, that.extinct)
           && Objects.equal(this.hybrid, that.hybrid)
           && Objects.equal(this.ageInDays, that.ageInDays)
           && Objects.equal(this.sizeInMillimeter, that.sizeInMillimeter)
           && Objects.equal(this.massInGram, that.massInGram)
           && Objects.equal(this.source, that.source)
           && Objects.equal(this.sourceTaxonKey, that.sourceTaxonKey);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(livingPeriod,
                            lifeForm,
                            habitat,
                            marine,
                            terrestrial,
                            extinct,
                            hybrid,
                            ageInDays,
                            sizeInMillimeter,
                            massInGram,
                            source,
                            sourceTaxonKey);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("livingPeriod", livingPeriod)
      .add("lifeForm", lifeForm)
      .add("habitat", habitat)
      .add("marine", marine)
      .add("terrestrial", terrestrial)
      .add("extinct", extinct)
      .add("hybrid", hybrid)
      .add("ageInDays", ageInDays)
      .add("sizeInMillimeter", sizeInMillimeter)
      .add("massInGram", massInGram)
      .add("source", source)
      .add("sourceTaxonKey", sourceTaxonKey)
      .toString();
  }

}
