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

import java.util.ArrayList;
import java.util.List;

/** Holds the alternative results of the collections lookup.*/
public class AlternativeMatches {

  private List<Match<InstitutionMatched>> institutionMatches = new ArrayList<>();
  private List<Match<CollectionMatched>> collectionMatches = new ArrayList<>();

  public List<Match<InstitutionMatched>> getInstitutionMatches() {
    return institutionMatches;
  }

  public void setInstitutionMatches(List<Match<InstitutionMatched>> institutionMatches) {
    this.institutionMatches = institutionMatches;
  }

  public List<Match<CollectionMatched>> getCollectionMatches() {
    return collectionMatches;
  }

  public void setCollectionMatches(List<Match<CollectionMatched>> collectionMatches) {
    this.collectionMatches = collectionMatches;
  }
}
