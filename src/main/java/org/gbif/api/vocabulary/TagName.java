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
package org.gbif.api.vocabulary;

import static org.gbif.api.vocabulary.TagNamespace.*;

/**
 * Known {@link org.gbif.api.model.registry.MachineTag#getName()} name value.
 * The tag names listed here are used/recognized by different services.
 */
public enum TagName {

  /**
   * The Darwin Core term datasetID which is found in dwc archives to reference subdatasets
   * such as the Catalogue of Life GSDs. Stored in the registry to keep a mapping between the subdataset registry key
   * and the dwca datasetID value.
   *
   * @see <a href="http://rs.tdwg.org/dwc/terms/datasetID">DWC Term</a>
   * @see <a href="http://kos.gbif.org/wiki/Dwc:datasetID">KOS wiki</a>
   */
  DATASET_ID("dataset_id", GBIF_HARVESTING),

  /**
   * This is a counter starting at 1 incremented every time we try to crawl a dataset.
   * This is even incremented when a crawl fails for any reason.
   */
  CRAWL_ATTEMPT("crawl_attempt", GBIF_CRAWLER),

  /**
   * <em>Datasets</em> marked with this tag will not be in the periodic crawl.
   */
  OMIT_FROM_SCHEDULED_CRAWL("omitFromScheduledCrawl", GBIF_CRAWLER),

  /**
   * Records the source of an archive endpoint, e.g. that it is from a BioCASe installation.
   */
  ARCHIVE_ORIGIN("archiveOrigin", GBIF_METASYNC),

  /**
   * TaPIR or BioCASe conceptual schema.
   */
  CONCEPTUAL_SCHEMA("conceptualSchema", GBIF_METASYNC),

  /**
   * Dataset title for BioCASe, no longer used.
   */
  @Deprecated
  DATASET_TITLE("datasetTitle", GBIF_METASYNC),

  /**
   * Date the records in the dataset were last updated.
   */
  DATE_LAST_UPDATED("dateLastUpdated", GBIF_METASYNC),

  /**
   * Number of records declared in the dataset by the source.
   */
  DECLARED_COUNT("declaredCount", GBIF_METASYNC),

  /**
   * DiGIR code.
   */
  DIGIR_CODE("code", GBIF_METASYNC),

  /**
   * Not sure, no longer in use?
   */
  @Deprecated
  LOCAL_ID("localId", GBIF_METASYNC),

  /**
   * Number of records to request in each search.
   */
  MAX_SEARCH_RESPONSE_RECORDS("maxSearchResponseRecords", GBIF_METASYNC),

  /**
   * Records whether an orphan dataset has been RESCUED.
   */
  ORPHAN_STATUS("status", GBIF_ORPHANS),

  /**
   * The endpoint which was not working when the dataset was rescued.
   */
  ORPHANED_ENDPOINT("orphanEndpoint", GBIF_ORPHANS),

  /**
   * The GBIF download used to rescue the orphaned dataset.
   */
  ORPHAN_DOWNLOAD("download", GBIF_ORPHANS),

  /**
   * The modification time of the last-available Darwin Core Archive from temporary storage,
   * used to rescue the dataset.
   */
  ORPHAN_DWCA_CACHE_TIME("crawlerDwcaCacheTime", GBIF_ORPHANS);

  private final String name;

  private final TagNamespace namespace;

  /**
   * Default constructor.
   *
   * @param name      namespace value
   * @param namespace, Enum namespace to which belongs the predicate
   */
  TagName(String name, TagNamespace namespace) {
    this.name = name;
    this.namespace = namespace;
  }

  /**
   * Namespace to which belong this tag.
   *
   * @return the namespace
   */
  public TagNamespace getNamespace() {
    return namespace;
  }

  /**
   * Actual machine tag name.
   *
   * @return the machine tag name
   */
  public String getName() {
    return name;
  }

}
