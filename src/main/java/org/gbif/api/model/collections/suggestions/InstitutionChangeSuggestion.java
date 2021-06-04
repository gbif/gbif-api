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
package org.gbif.api.model.collections.suggestions;

import org.gbif.api.model.collections.Institution;

import java.util.Objects;
import java.util.UUID;

public class InstitutionChangeSuggestion extends BaseChangeSuggestion<Institution> {

  private UUID institutionForConvertedCollection;
  private String nameForNewInstitutionForConvertedCollection;

  public UUID getInstitutionForConvertedCollection() {
    return institutionForConvertedCollection;
  }

  public void setInstitutionForConvertedCollection(UUID institutionForConvertedCollection) {
    this.institutionForConvertedCollection = institutionForConvertedCollection;
  }

  public String getNameForNewInstitutionForConvertedCollection() {
    return nameForNewInstitutionForConvertedCollection;
  }

  public void setNameForNewInstitutionForConvertedCollection(
      String nameForNewInstitutionForConvertedCollection) {
    this.nameForNewInstitutionForConvertedCollection = nameForNewInstitutionForConvertedCollection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    InstitutionChangeSuggestion that = (InstitutionChangeSuggestion) o;
    return Objects.equals(institutionForConvertedCollection, that.institutionForConvertedCollection)
        && Objects.equals(
            nameForNewInstitutionForConvertedCollection,
            that.nameForNewInstitutionForConvertedCollection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        institutionForConvertedCollection,
        nameForNewInstitutionForConvertedCollection);
  }
}
