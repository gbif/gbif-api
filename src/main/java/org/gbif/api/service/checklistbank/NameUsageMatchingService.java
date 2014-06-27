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
package org.gbif.api.service.checklistbank;

import org.gbif.api.model.checklistbank.NameUsageMatch;
import org.gbif.api.model.common.LinneanClassification;
import org.gbif.api.vocabulary.Rank;

import javax.annotation.Nullable;

/**
 * A lookup service that fuzzy matches classified scientific names against a body of names.
 * In GBIF this is in particular used for a Nub Lookup service that binds occurrence names and other external records
 * with a scientific name to the nub.
 * <p/>
 * Several match signatures for essentially the same matching are provided.
 */
public interface NameUsageMatchingService {

  /**
   * Tries to match a scientific name with an optional classification given as individual parameters.
   *
   * @param scientificName  the name to match against
   * @param rank            of the name to match
   * @param classification  optional classification of the name to match
   * @param strict          if true only tries to match the scientific name, if false (the default) the matching
   *                        service tries also to match the lowest possible taxon from the given classification
   * @param verbose         if true adds verbose matching information, i.e. list of alternative matches
   *
   * @return a match which is never null, but might have a usageKey=null if nothing could be matched
   */
  NameUsageMatch match(String scientificName, @Nullable Rank rank, @Nullable LinneanClassification classification,
    boolean strict, boolean verbose);

}
