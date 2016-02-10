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

import org.gbif.api.model.checklistbank.NameUsage;
import org.gbif.api.model.checklistbank.NameUsageMetrics;
import org.gbif.api.model.checklistbank.ParsedName;
import org.gbif.api.model.checklistbank.VerbatimNameUsage;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * This is the public interface providing methods for retrieving name usages in general, no matter if nub or
 * checklist usage.
 *
 * @see NameUsage
 */
public interface NameUsageService {

  /**
   * Attempt to find a name usage matching the passed key.
   * The given Locale determines the name used for the NameUsage.vernacularName property
   * with null ignoring any vernacular name.
   *
   * @param taxonKey that identifies a name usage
   * @param locale   the locale's language determines the vernacular name to use for a usage.
   *                 Use null to not load any common name
   *
   * @return a matching name usage, or null if no name usage found
   */
  @Nullable
  NameUsage get(int taxonKey, @Nullable Locale locale);

  /**
   * Gets the parsed name representation of a given name usage.
   *
   * @param taxonKey that identifies a name usage
   *
   * @return the parsed name of the name usage or null if none can be found
   */
  @Nullable
  ParsedName getParsedName(int taxonKey);

  /**
   * Gets the metrics for a given name usage.
   *
   * @param taxonKey that identifies a name usage
   *
   * @return the usage metrics of the name usage or null if none can be found
   */
  @Nullable
  NameUsageMetrics getMetrics(int taxonKey);

  /**
   * Returns the verbatim data for the usage or null if its a generated usage having no verbatim data.
   * This happens for all nub usages and some other usages which have a non SOURCE origin.
   *
   * @return verbatim data for the usage or null
   */
  @Nullable
  VerbatimNameUsage getVerbatim(int taxonKey);

  /**
   * Page through all usages across all or one checklists.
   *
   * @param datasetKey the optional checklist key to limit paging to.
   * @param sourceId   the optional checklist key to limit paging to.
   * @param locale     the locale's language determines the vernacular name to use for a usage
   *                   Use null to not load any common name
   * @param page       paging parameters or null for first page with default size
   *
   * @return Paged list of usages
   */
  PagingResponse<NameUsage> list(Locale locale, @Nullable UUID datasetKey, @Nullable String sourceId,
                                 @Nullable Pageable page);

  /**
   * Page through all usages with a given canonical name across all or some checklists.
   *
   * @param canonicalName the canonical name of a name usage.
   * @param locale        the locale's language determines the vernacular name to use for a usage
   *                      Use null to not load any common name
   * @param datasetKey   the optional list of checklist keys to limit paging to.
   * @param page          paging parameters or null for first page with default size
   *
   * @return Paged list of usages matching the exact canonical name
   */
  PagingResponse<NameUsage> listByCanonicalName(Locale locale, String canonicalName, @Nullable Pageable page,
    @Nullable UUID ... datasetKey);

  /**
   * Lists all accepted child name usages for a given parent.
   *
   * @param parentKey that identifies the parent name usage
   * @param locale    the locale's language determines the vernacular name to use for a usage
   *                  Use null to not load any common name
   * @param page      paging parameters or null for first page with default size
   *
   * @return Paged list of child usages.
   */
  PagingResponse<NameUsage> listChildren(int parentKey, Locale locale, @Nullable Pageable page);

  /**
   * Lists the complete parental hierarchy of a name usage regardless of their ranks.
   *
   * @param taxonKey that identifies the name usage to show parents of
   * @param locale   the locale's language determines the vernacular name to use for a usage
   *                 Use null to not load any common name
   *
   * @return List of parent usages with the last usage being the immediate parent
   */
  List<NameUsage> listParents(int taxonKey, Locale locale);

  /**
   * Lists all related checklist usages for a given nub usage.
   *
   * @param taxonKey   that identifies a nub usage
   * @param locale     the locale's language determines the vernacular name to use for a usage
   *                   Use null to not load any common name
   * @param page       paging parameters or null for first page with default size
   * @param datasetKey Optional list of checklist keys to restrict related usages to
   *
   * @return Paged list of related name usages.
   */
  PagingResponse<NameUsage> listRelated(int taxonKey, Locale locale, @Nullable Pageable page, @Nullable UUID... datasetKey);

  /**
   * Lists all root usages for a given checklist, i.e. accepted usages without a parent.
   * To list the 8 root kingdoms of the nub use the respective datasetKey,
   * @see org.gbif.api.model.Constants#NUB_DATASET_KEY
   *
   * @param datasetKey the registered dataset key for the checklist in question
   * @param locale     the locale's language determines the vernacular name to use for a usage
   *                   Use null to not load any common name
   * @param page       paging parameters or null for first page with default size
   *
   * @return Paged list of root name usages.
   *
   */
  PagingResponse<NameUsage> listRoot(UUID datasetKey, Locale locale, @Nullable Pageable page);

  /**
   * Lists all synonym name usages for a given accepted name usage.
   *
   * @param taxonKey that identifies any name usage
   * @param locale   the locale's language determines the vernacular name to use for a usage
   *                 Use null to not load any common name
   * @param page     paging parameters or null for first page with default size
   *
   * @return Paged list of synonym name usages.
   */
  PagingResponse<NameUsage> listSynonyms(int taxonKey, Locale locale, @Nullable Pageable page);

  /**
   * Lists all combinations or names at different rank that are based on this basionym, i.e. a list of all name usages sharing the same basionym (homotypical group).
   * The basionym itself is not included in this list.
   *
   * @param basionymKey the name usage key of the basionym
   * @param locale   the locale's language determines the vernacular name to use for a usage
   *                 Use null to not load any common name
   *
   * @return List of name usages sharing the same basionym.
   */
  List<NameUsage> listCombinations(int basionymKey, Locale locale);

}
