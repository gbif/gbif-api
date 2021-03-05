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
package org.gbif.api.model.collections.duplicates;

import org.gbif.api.util.VocabularyUtils;
import org.gbif.api.vocabulary.Country;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

/** Request to be used for the GRSciColl API duplicates services. */
public class DuplicatesRequest implements Serializable {

  private Boolean sameName;
  private Boolean sameFuzzyName;
  private Boolean sameCode;
  private Boolean sameCountry;
  private Boolean sameCity;
  private Boolean sameInstitution;
  private List<String> inCountries;
  private List<String> notInCountries;
  private List<UUID> excludeKeys;
  private List<UUID> inInstitutions;
  private List<UUID> notInInstitutions;

  public Boolean getSameName() {
    return sameName;
  }

  public void setSameName(Boolean sameName) {
    this.sameName = sameName;
  }

  public Boolean getSameFuzzyName() {
    return sameFuzzyName;
  }

  public void setSameFuzzyName(Boolean sameFuzzyName) {
    this.sameFuzzyName = sameFuzzyName;
  }

  public Boolean getSameCode() {
    return sameCode;
  }

  public void setSameCode(Boolean sameCode) {
    this.sameCode = sameCode;
  }

  public Boolean getSameCountry() {
    return sameCountry;
  }

  public void setSameCountry(Boolean sameCountry) {
    this.sameCountry = sameCountry;
  }

  public Boolean getSameCity() {
    return sameCity;
  }

  public void setSameCity(Boolean sameCity) {
    this.sameCity = sameCity;
  }

  public List<Country> getInCountries() {
    if (inCountries == null) {
      return Collections.emptyList();
    }
    return inCountries.stream().map(this::toCountry).collect(Collectors.toList());
  }

  public void setInCountries(List<String> inCountries) {
    this.inCountries = inCountries;
  }

  public List<Country> getNotInCountries() {
    if (notInCountries == null) {
      return Collections.emptyList();
    }
    return notInCountries.stream().map(this::toCountry).collect(Collectors.toList());
  }

  public void setNotInCountries(List<String> notInCountries) {
    this.notInCountries = notInCountries;
  }

  public List<UUID> getExcludeKeys() {
    return excludeKeys;
  }

  public void setExcludeKeys(List<UUID> excludeKeys) {
    this.excludeKeys = excludeKeys;
  }

  public Boolean getSameInstitution() {
    return sameInstitution;
  }

  public void setSameInstitution(Boolean sameInstitution) {
    this.sameInstitution = sameInstitution;
  }

  public List<UUID> getInInstitutions() {
    return inInstitutions;
  }

  public void setInInstitutions(List<UUID> inInstitutions) {
    this.inInstitutions = inInstitutions;
  }

  public List<UUID> getNotInInstitutions() {
    return notInInstitutions;
  }

  public void setNotInInstitutions(List<UUID> notInInstitutions) {
    this.notInInstitutions = notInInstitutions;
  }

  public boolean isEmpty() {
    return sameName == null
        && sameFuzzyName == null
        && sameCode == null
        && sameCity == null
        && sameCountry == null;
  }

  private Country toCountry(String countryParam) {
    Country country = null;
    if (countryParam != null && !countryParam.isEmpty()) {
      country = Country.fromIsoCode(countryParam);

      if (country == null) {
        // if nothing found also try by enum name
        country = VocabularyUtils.lookupEnum(countryParam, Country.class);
      }
    }
    return country;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DuplicatesRequest.class.getSimpleName() + "[", "]")
        .add("sameName=" + sameName)
        .add("sameFuzzyName=" + sameFuzzyName)
        .add("sameCode=" + sameCode)
        .add("sameCountry=" + sameCountry)
        .add("sameCity=" + sameCity)
        .add("sameInstitution=" + sameInstitution)
        .add("inCountries=" + inCountries)
        .add("notInCountries=" + notInCountries)
        .add("excludeKeys=" + excludeKeys)
        .add("inInstitutions=" + inInstitutions)
        .add("notInInstitutions=" + notInInstitutions)
        .toString();
  }
}
