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
package org.gbif.api.model.registry.eml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class TaxonomicCoverages implements Serializable {

  private static final long serialVersionUID = 334156985829698279L;

  private String description;

  private List<TaxonomicCoverage> coverages = new ArrayList<TaxonomicCoverage>();

  public TaxonomicCoverages() {
  }

  public TaxonomicCoverages(String description, List<TaxonomicCoverage> coverages) {
    this.description = description;
    this.coverages = coverages;
  }

  public List<TaxonomicCoverage> getCoverages() {
    return coverages;
  }

  public void setCoverages(List<TaxonomicCoverage> coverages) {
    this.coverages = coverages;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void addCoverages(TaxonomicCoverage coverage) {
    coverages.add(coverage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxonomicCoverages that = (TaxonomicCoverages) o;
    return Objects.equals(description, that.description) &&
      Objects.equals(coverages, that.coverages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, coverages);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TaxonomicCoverages.class.getSimpleName() + "[", "]")
      .add("description='" + description + "'")
      .add("coverages=" + coverages)
      .toString();
  }
}
