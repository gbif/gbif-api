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
package org.gbif.api.model.registry.eml;

import org.gbif.api.model.common.InterpretedEnum;
import org.gbif.api.vocabulary.Rank;

import java.io.Serializable;

import com.google.common.base.Objects;


/**
 * An individual coverage.
 */
public class TaxonomicCoverage implements Serializable {

  private static final long serialVersionUID = 7567059548990081342L;

  private String scientificName;

  private String commonName;

  private InterpretedEnum<String, Rank> rank;

  public TaxonomicCoverage() {
  }

  public TaxonomicCoverage(String scientificName, String commonName, InterpretedEnum<String, Rank> rank) {
    this.scientificName = scientificName;
    this.commonName = commonName;
    this.rank = rank;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public InterpretedEnum<String, Rank> getRank() {
    return rank;
  }

  public void setRank(InterpretedEnum<String, Rank> rank) {
    this.rank = rank;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof TaxonomicCoverage)) {
      return false;
    }

    TaxonomicCoverage
      that = (TaxonomicCoverage) obj;
    return Objects.equal(this.scientificName, that.scientificName)
           && Objects.equal(this.commonName, that.commonName)
           && Objects.equal(this.rank, that.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(scientificName, commonName, rank);
  }

  @Override
  public String toString() {
    return "TaxonomicCoverage{" +
           "scientificName='" + scientificName + '\'' +
           ", commonName='" + commonName + '\'' +
           ", rank=" + rank +
           '}';
  }

}
