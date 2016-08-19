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

import org.gbif.api.model.common.Identifier;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.ThreatStatus;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import static com.google.common.collect.Lists.newArrayList;

/**
 * An extension to a NameUsage adding all further properties that are not eagerly loaded.
 * Use this class to store the various subresources as you need them side by side with the NameUsage.
 * Note that properties are not automatically/lazy loaded in any way.
 * This is just a simple container class with a few convenience methods which needs to be populated manually via its
 * setters or the constructor.
 */
public class NameUsageContainer extends NameUsage {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  static {
    MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private List<Description> descriptions = newArrayList();
  private List<Distribution> distributions = newArrayList();
  private List<Identifier> identifiers = newArrayList();
  private List<NameUsageMediaObject> media = newArrayList();
  private List<Reference> referenceList = newArrayList();
  private List<SpeciesProfile> speciesProfiles = newArrayList();
  private List<NameUsage> synonyms = newArrayList();
  private List<NameUsage> combinations = newArrayList();
  private List<TypeSpecimen> typeSpecimens = newArrayList();
  private List<VernacularName> vernacularNames = newArrayList();

  /**
   * Default constructor.
   */
  public NameUsageContainer() {
    //empty constructor
  }

  /**
   * Constructs a NameUsageContainer from an existing NameUsage instance.
   */
  public NameUsageContainer(NameUsage usage) {
    try {
      JsonNode propTree = MAPPER.convertValue(usage, JsonNode.class);
      MAPPER.readerForUpdating(this).readValue(propTree);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to copy NameUsage properties to NameUsageContainer", e);
    }
  }

  /**
   * @return the list of Description
   */
  @NotNull
  public List<Description> getDescriptions() {
    return descriptions;
  }

  /**
   * @param descriptions the list of Description
   */
  public void setDescriptions(List<Description> descriptions) {
    this.descriptions = descriptions;
  }

  /**
   * @return the list of Distribution
   */
  @NotNull
  public List<Distribution> getDistributions() {
    return distributions;
  }

  /**
   * @param distributions the Distribution list to set
   */
  public void setDistributions(List<Distribution> distributions) {
    this.distributions = distributions;
  }

  /**
   * Convenience method that analyzes all species profile records and returns the distinct list of known habitats.
   *
   * @return list of unique habitats
   */
  @NotNull
  public Set<String> getHabitats() {
    Set<String> habitats = Sets.newLinkedHashSet();
    for (SpeciesProfile sp : speciesProfiles) {
      if (!Strings.isNullOrEmpty(sp.getHabitat())) {
        habitats.add(sp.getHabitat());
      }
    }
    return habitats;
  }

  public List<NameUsageMediaObject> getMedia() {
    return media;
  }

  public void setMedia(List<NameUsageMediaObject> media) {
    this.media = media;
  }

  /**
   * @return the list of all Identifier
   */
  @NotNull
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * @param identifiers the Identifier list to set
   */
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * @return the list of all URL Identifier
   */
  @NotNull
  @JsonIgnore
  public List<Identifier> getIdentifierByType(final IdentifierType type) {
    List<Identifier> ids = newArrayList();
    for (Identifier i : identifiers) {
      if (type == i.getType()) {
        ids.add(i);
      }
    }
    return ids;
  }

  /**
   * @return the list of Reference
   */
  @NotNull
  public List<Reference> getReferenceList() {
    return referenceList;
  }

  /**
   * @param referenceList the Reference list to set
   */
  public void setReferenceList(List<Reference> referenceList) {
    this.referenceList = referenceList;
  }

  /**
   * @return the list of SpeciesProfile
   */
  @NotNull
  public List<SpeciesProfile> getSpeciesProfiles() {
    return speciesProfiles;
  }

  /**
   * @param speciesProfiles the SpeciesProfile list to set
   */
  public void setSpeciesProfiles(List<SpeciesProfile> speciesProfiles) {
    this.speciesProfiles = speciesProfiles;
  }

  /**
   * @return the list of synonyms
   */
  public List<NameUsage> getSynonyms() {
    return synonyms;
  }

  /**
   * @param synonyms list of synonyms
   */
  public void setSynonyms(List<NameUsage> synonyms) {
    this.synonyms = synonyms;
  }

  /**
   * @return the list of combinations known for the basionym
   */
  @NotNull
  public List<NameUsage> getCombinations() {
    return combinations;
  }

  public void setCombinations(List<NameUsage> combinations) {
    this.combinations = combinations;
  }

    /**
   * @return the list of TypeSpecimen
   */
  @NotNull
  public List<TypeSpecimen> getTypeSpecimens() {
    return typeSpecimens;
  }

  /**
   * @param typeSpecimens the TypeSpecimen list to set
   */
  public void setTypeSpecimens(List<TypeSpecimen> typeSpecimens) {
    this.typeSpecimens = typeSpecimens;
  }

  /**
   * @return the list of VernacularName
   */
  @NotNull
  public List<VernacularName> getVernacularNames() {
    return vernacularNames;
  }

  /**
   * @param vernacularNames the VernacularName list to set
   */
  public void setVernacularNames(List<VernacularName> vernacularNames) {
    this.vernacularNames = vernacularNames;
  }

  /**
   * Convenience method that analyzes all species profile records and returns the distinct list of known living
   * periods.
   *
   * @return list of unique living periods
   */
  @NotNull
  public Set<String> getLivingPeriods() {
    Set<String> periods = Sets.newLinkedHashSet();
    for (SpeciesProfile sp : speciesProfiles) {
      if (!Strings.isNullOrEmpty(sp.getLivingPeriod())) {
        periods.add(sp.getLivingPeriod());
      }
    }
    return periods;
  }

  /**
   * Convenience method that analyzes all distribution records and returns the distinct list of known threat status.
   *
   * @return list of unique threat status
   */
  @NotNull
  public Set<ThreatStatus> getThreatStatus() {
    Set<ThreatStatus> threats = Sets.newLinkedHashSet();
    for (Distribution d : distributions) {
      if (d.getThreatStatus() != null) {
        threats.add(d.getThreatStatus());
      }
    }
    return threats;
  }

  /**
   * Convenience method that analyzes all species profile records for extinct.
   * If several records contradict use the majority or if numbers are equal true.
   *
   * @return true if extinct
   */
  public Boolean isExtinct() {
    int ctrue = 0;
    int cfalse = 0;
    for (SpeciesProfile sp : speciesProfiles) {
      if (sp.isExtinct() == null) {
        continue;
      }

      if (sp.isExtinct()) {
        ctrue++;
      } else if (!sp.isExtinct()) {
        cfalse++;
      }
    }
    return ctrue + cfalse == 0 ? null : ctrue >= cfalse;
  }

  /**
   * Convenience method that analyzes all species profile records for freshwater habitat flags.
   *
   * @return true if freshwater
   */
  public Boolean isFreshwater() {
    int ctrue = 0;
    int cfalse = 0;
    for (SpeciesProfile sp : speciesProfiles) {
      if (sp.isFreshwater() == null) {
        continue;
      }

      if (sp.isFreshwater()) {
        ctrue++;
      } else if (!sp.isFreshwater()) {
        cfalse++;
      }
    }
    return ctrue + cfalse == 0 ? null : ctrue >= cfalse;
  }

  /**
   * Convenience method that analyzes all species profile records for marine habitat flags.
   *
   * @return true if marine
   */
  public Boolean isMarine() {
    int ctrue = 0;
    int cfalse = 0;
    for (SpeciesProfile sp : speciesProfiles) {
      if (sp.isMarine() == null) {
        continue;
      }

      if (sp.isMarine()) {
        ctrue++;
      } else if (!sp.isMarine()) {
        cfalse++;
      }
    }
    return ctrue + cfalse == 0 ? null : ctrue >= cfalse;
  }

  /**
   * Convenience method that analyzes all species profile records for terrestrial habitat flags.
   *
   * @return true if terrestrial
   */
  public Boolean isTerrestrial() {
    int ctrue = 0;
    int cfalse = 0;
    for (SpeciesProfile sp : speciesProfiles) {
      if (sp.isTerrestrial() == null) {
        continue;
      }

      if (sp.isTerrestrial()) {
        ctrue++;
      } else if (!sp.isTerrestrial()) {
        cfalse++;
      }
    }
    return ctrue + cfalse == 0 ? null : ctrue >= cfalse;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof NameUsageContainer) {
      if (!super.equals(object)) {
        return false;
      }
      final NameUsageContainer other = (NameUsageContainer) object;
      return Objects.equal(this.descriptions, other.descriptions)
             && Objects.equal(this.distributions, other.distributions)
             && Objects.equal(this.media, other.media)
             && Objects.equal(this.referenceList, other.referenceList)
             && Objects.equal(this.speciesProfiles, other.speciesProfiles)
             && Objects.equal(this.synonyms, other.synonyms)
             && Objects.equal(this.typeSpecimens, other.typeSpecimens)
             && Objects.equal(this.vernacularNames, other.vernacularNames);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(descriptions,
                            distributions,
                            media,
                            referenceList,
                            speciesProfiles,
                            synonyms,
                            typeSpecimens,
                            vernacularNames);
  }

}
