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
package org.gbif.api.model.collections.lookup;

import org.gbif.api.vocabulary.Country;
import org.gbif.common.shaded.com.google.common.base.Strings;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/** Holds the parameters that the collections lookup accepts. */
public class LookupParams {

  private String institutionCode;
  private String ownerInstitutionCode;
  private String institutionId;
  private String collectionCode;
  private String collectionId;
  private UUID datasetKey;
  private Country country;
  private boolean verbose;

  public String getInstitutionCode() {
    return institutionCode;
  }

  public void setInstitutionCode(String institutionCode) {
    this.institutionCode = institutionCode;
  }

  public String getOwnerInstitutionCode() {
    return ownerInstitutionCode;
  }

  public void setOwnerInstitutionCode(String ownerInstitutionCode) {
    this.ownerInstitutionCode = ownerInstitutionCode;
  }

  public String getInstitutionId() {
    return institutionId;
  }

  public void setInstitutionId(String institutionId) {
    this.institutionId = institutionId;
  }

  public String getCollectionCode() {
    return collectionCode;
  }

  public void setCollectionCode(String collectionCode) {
    this.collectionCode = collectionCode;
  }

  public String getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public boolean isVerbose() {
    return verbose;
  }

  public void setVerbose(boolean verbose) {
    this.verbose = verbose;
  }

  public boolean containsInstitutionParams() {
    return !Strings.isNullOrEmpty(institutionCode)
        || !Strings.isNullOrEmpty(institutionId)
        || !Strings.isNullOrEmpty(ownerInstitutionCode);
  }

  public boolean containsCollectionParams() {
    return !Strings.isNullOrEmpty(collectionCode) || !Strings.isNullOrEmpty(collectionId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LookupParams that = (LookupParams) o;
    return verbose == that.verbose
        && Objects.equals(institutionCode, that.institutionCode)
        && Objects.equals(ownerInstitutionCode, that.ownerInstitutionCode)
        && Objects.equals(institutionId, that.institutionId)
        && Objects.equals(collectionCode, that.collectionCode)
        && Objects.equals(collectionId, that.collectionId)
        && Objects.equals(datasetKey, that.datasetKey)
        && country == that.country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        institutionCode,
        ownerInstitutionCode,
        institutionId,
        collectionCode,
        collectionId,
        datasetKey,
        country,
        verbose);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", LookupParams.class.getSimpleName() + "[", "]")
        .add("institutionCode='" + institutionCode + "'")
        .add("ownerInstitutionCode='" + ownerInstitutionCode + "'")
        .add("institutionId='" + institutionId + "'")
        .add("collectionCode='" + collectionCode + "'")
        .add("collectionId='" + collectionId + "'")
        .add("datasetKey=" + datasetKey)
        .add("country=" + country)
        .add("verbose=" + verbose)
        .toString();
  }
}
