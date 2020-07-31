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

/** Result of a collections lookup that contains the institution and collections matches found. */
public class LookupResult {

  private Match<Institution> institutionMatch;
  private Match<Collection> collectionMatch;
  private AlternativeMatches alternativeMatches = new AlternativeMatches();

  public Match<Institution> getInstitutionMatch() {
    return institutionMatch;
  }

  public void setInstitutionMatch(Match<Institution> institutionMatch) {
    this.institutionMatch = institutionMatch;
  }

  public Match<Collection> getCollectionMatch() {
    return collectionMatch;
  }

  public void setCollectionMatch(Match<Collection> collectionMatch) {
    this.collectionMatch = collectionMatch;
  }

  public AlternativeMatches getAlternativeMatches() {
    return alternativeMatches;
  }

  public void setAlternativeMatches(AlternativeMatches alternativeMatches) {
    this.alternativeMatches = alternativeMatches;
  }
}
