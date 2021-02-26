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

import org.gbif.api.vocabulary.Country;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/** Models a duplicate in GRSciColl. It's used for institutions and collections. */
public class Duplicate implements Serializable {

  private UUID key;
  private String code;
  private String name;
  private Country physicalCountry;
  private String physicalCity;
  private Country mailingCountry;
  private String mailingCity;
  // only for collections
  private UUID institutionKey;

  public UUID getKey() {
    return key;
  }

  public void setKey(UUID key) {
    this.key = key;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country getPhysicalCountry() {
    return physicalCountry;
  }

  public void setPhysicalCountry(Country physicalCountry) {
    this.physicalCountry = physicalCountry;
  }

  public String getPhysicalCity() {
    return physicalCity;
  }

  public void setPhysicalCity(String physicalCity) {
    this.physicalCity = physicalCity;
  }

  public Country getMailingCountry() {
    return mailingCountry;
  }

  public void setMailingCountry(Country mailingCountry) {
    this.mailingCountry = mailingCountry;
  }

  public String getMailingCity() {
    return mailingCity;
  }

  public void setMailingCity(String mailingCity) {
    this.mailingCity = mailingCity;
  }

  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Duplicate duplicate = (Duplicate) o;
    return Objects.equals(key, duplicate.key)
        && Objects.equals(code, duplicate.code)
        && Objects.equals(name, duplicate.name)
        && physicalCountry == duplicate.physicalCountry
        && Objects.equals(physicalCity, duplicate.physicalCity)
        && mailingCountry == duplicate.mailingCountry
        && Objects.equals(mailingCity, duplicate.mailingCity)
        && Objects.equals(institutionKey, duplicate.institutionKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        code,
        name,
        physicalCountry,
        physicalCity,
        mailingCountry,
        mailingCity,
        institutionKey);
  }
}
