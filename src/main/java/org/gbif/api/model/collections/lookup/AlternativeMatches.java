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

import org.gbif.api.model.collections.Collection;
import org.gbif.api.model.collections.Institution;

import java.util.ArrayList;
import java.util.List;

public class AlternativeMatches {

  private List<Match<Institution>> institutionMatches = new ArrayList<>();
  private List<Match<Collection>> collectionMatches = new ArrayList<>();

  public List<Match<Institution>> getInstitutionMatches() {
    return institutionMatches;
  }

  public void setInstitutionMatches(List<Match<Institution>> institutionMatches) {
    this.institutionMatches = institutionMatches;
  }

  public List<Match<Collection>> getCollectionMatches() {
    return collectionMatches;
  }

  public void setCollectionMatches(List<Match<Collection>> collectionMatches) {
    this.collectionMatches = collectionMatches;
  }
}
