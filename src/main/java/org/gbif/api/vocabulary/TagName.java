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

import static org.gbif.api.vocabulary.TagNamespace.CHECKLISTBANK;
import static org.gbif.api.vocabulary.TagNamespace.CRAWLER;
import static org.gbif.api.vocabulary.TagNamespace.GBIF;
import static org.gbif.api.vocabulary.TagNamespace.GBIF_HARVESTING;

/**
 * Known {@link org.gbif.api.model.registry.MachineTag#getName()} name value.
 * The tag names listed here are used/recognized by different services.
 */
public enum TagName {

  /**
   * BioCASE (ABCD 2.0.6) metadata response element: the dataset disclaimer.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  DISCLAIMER("Disclaimer", GBIF_HARVESTING),

  /**
   * BioCASE (ABCD 1.2 & 2.0.6) metadata response element: the dataset's owning organization name.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  ORGANISATION_NAME("OrganisationName", GBIF_HARVESTING),

  /**
   * BioCASE (ABCD 2.0.6) metadata response element: the dataset owner URL.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  OWNER_URL("OwnerURL", GBIF_HARVESTING),

  /**
   * BioCASE (ABCD 1.2) metadata response element: the dataset rights URL.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  RIGHTS_URL("RightsURL", GBIF_HARVESTING),

  /**
   * BioCASE (ABCD 1.2) metadata response element: the supplier URL.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  SUPPLIER_URL("SupplierURL", GBIF_HARVESTING),

  /**
   * BioCASE (ABCD 1.2 & 2.0.6) metadata response element: the dataset's terms of usage.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  TERMS_OF_USE("TermsOfUse", GBIF_HARVESTING),

  /**
   * TAPIR and BioCASE (ABCD 1.2 & 2.0.6) metadata response element: the intellectual property rights description.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  IPR_DECLARATION("IPRDeclaration", GBIF_HARVESTING),

  /**
   * TAPIR metadata response indexing preferences attribute: the frequency the publisher wishes the resource to be
   * re-indexed.
   * For example: frequency="P7D"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  FREQUENCY("frequency", GBIF_HARVESTING),

  /**
   * TAPIR metadata response indexing preferences attribute: the maximum duration permitted for indexing.
   * For example: maxDuration="PT1H"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MAX_DURATION("maxDuration", GBIF_HARVESTING),

  /**
   * TAPIR metadata response indexing preferences attribute: the preferred start time for indexing.
   * For example: startTime="01:30:00Z"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  START_TIME("startTime", GBIF_HARVESTING),

  /**
   * TAPIR metadata response element: the date the resource was last modified.
   * For example: 2009-05-25T02:00:00+02:00
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MODIFIED("modified", GBIF_HARVESTING),

  /**
   * TAPIR metadata response element: the date the resource was created.
   * For example: 2008-04-01T13:45:26+02:00
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  CREATED("created", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: the basis of record describing all records in the resource.
   * For example: "Observations"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  BASIS_OF_RECORD("basisOfRecord", GBIF_HARVESTING),

  /**
   * Multiple protocols: the record count as declared by the resource publisher. For DiGIR, this could come from the
   * metadata response resource element called numberOfRecords.
   * For example: 35548
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  DECLARED_RECORD_COUNT("declaredRecordCount", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: the date the resource was last modified.
   * For example: 1991-12-31T00:00:00-05:00
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  DATE_LAST_UPDATED("dateLastUpdated", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: maximum number of records that can be returned in a single inventory
   * response.
   * For example: 1000
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MAX_INVENTORY_RESPONSE_RECORDS("maxInventoryResponseRecords", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: maximum number of records that can be returned in a single search
   * response.
   * For example: 1000
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MAX_SEARCH_RESPONSE_RECORDS("maxSearchResponseRecords", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: minimum length of a string that can be used in an XML request.
   * For example: 3
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MIN_QUERY_TERM_LENGTH("minQueryTermLength", GBIF_HARVESTING),

  /**
   * DiGIR metadata response resource element: the URL location of the conceptual schema.
   * For example: "http://digir.net/schema/conceptual/darwin/2003/1.0"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  CONCEPTUAL_SCHEMA("conceptualSchema", GBIF_HARVESTING),


  /**
   * DiGIR metadata response resource element: the URL location of the schema.
   * For example: "http://bnhm.berkeley.edu/manis/DwC/darwin2jrw030315.xsd"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  SCHEMA_LOCATION("schemaLocation", GBIF_HARVESTING),

  /**
   * BioCASE capabilities response Concept attribute: flag that indicates whether the dataset title is a searchable
   * concept or not.
   * For example: searchable="1"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  IS_TITLE_SEARCHABLE("isTitleSearchable", GBIF_HARVESTING),

  /**
   * BioCASE capabilities response SupportedSchemas attribute: the URL location of the content namespace.
   * For example: namespace="http://www.tdwg.org/schemas/abcd/2.06"
   * </p>
   * TAPIR capabilities response concepts/schema attribute: the URL location of the content namespace.
   * For example: namespace="http://rs.tdwg.org/dwc/terms/"
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  CONTENT_NAMESPACE("contentNamespace", GBIF_HARVESTING),

  /**
   * TAPIR metadata response settings element: sets the maximum number of records in a response.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  MAX_ELEMENT_REPETITIONS("maxElementRepetitions", GBIF_HARVESTING),

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
   * The Darwin Core term datasetName which is found in dwc archives to reference subdatasets
   * such as the Catalogue of Life GSDs. Stored in the registry to keep a mapping between the subdataset registry key
   * and the dwca datasetID value.
   *
   * @see <a href="http://rs.tdwg.org/dwc/terms/datasetName">DWC Term</a>
   * @see <a href="http://kos.gbif.org/wiki/Dwc:datasetName">KOS wiki</a>
   */
  DATASET_NAME("dataset_name", GBIF_HARVESTING),

  /**
   * Flag whose very presence indicates the dataset should be ignored by GBIF harvesters and consequently never be
   * (re)indexed by GBIF. A reason explaining why the dataset is being ignored should be given in the Tag's value.
   * </p>
   * Some potential reasons for ignoring a dataset during indexing include the dataset is a duplicate, the publisher
   * has requested that it not be indexed, or it is currently on hold while the publisher performs updates.
   * </p>
   * This predicate belongs to the HIT namespace.
   */
  IS_IGNORED("isIgnored", GBIF_HARVESTING),

  /**
   * Flag that indicates the dataset has been manually deleted by a Registry administrator. Should a dataset be
   * logically deleted and not have this tag, it can be inferred the deletion was done by machine, for example by the
   * registry metadata synchronizer. A reason explaining why the dataset is being flagged as deleted should be given in
   * the Tag's value.
   * <p/>
   * This predicate belongs to the GBIF namespace.
   */
  IS_MANUALLY_DELETED("isManuallyDeleted", GBIF),

  /**
   * Flag that indicates if a dataset has been locked for automatic updates based on metadata documents.
   * If this tag is present the dataset registry information should only manually be editable.
   */
  IS_MANUALLY_CURATED("isManuallyCurated", GBIF),

  /**
   * This is a counter starting at 1 incremented every time we try to crawl a dataset.
   * This is even incremented when a crawl fails for any reason.
   */
  CRAWL_ATTEMPT("crawl_attempt", CRAWLER),

  /**
   * Any checklist participating as a source for the GBIF backbone must be tagged with a positive integer priority
   * value. The lowest value 1 has highest priority.
   */
  NUB_PRIORITY("nubPriority", CHECKLISTBANK),

  /**
   * The absolute number of usages matched to corresponding usages in the currently indexed Catalog of Life.
   */
  NUM_COL("numCol", CHECKLISTBANK),

  /**
   * The absolute number of usages matched to corresponding usages in the GBIF backbone.
   */
  NUM_NUB("numNub", CHECKLISTBANK),

  /**
   * A URL template to be used to create dynamic links to source web pages for individual name usages in this dataset.
   * If the variable {ID} is found in this template it will be replaced by the actual name usage key. If it
   * does not exist the key will be appended to the end of the URL.
   */
  RECORD_SOURCE_URL("recordSourceUrl", CHECKLISTBANK),

  /**
   * The kingdom the entire checklist dataset should be classified under.
   */
  KINGDOM("kingdom", CHECKLISTBANK),

  /**
   * The number of image records in this dataset.
   */
  NUM_IMAGES("numImages", CHECKLISTBANK),

  /**
   * The number of description records in this dataset.
   */
  NUM_DESCRIPTION("numDescriptions", CHECKLISTBANK),

  /**
   * The number of bibliographic reference records in this dataset.
   */
  NUM_REFERENCES("numReferences", CHECKLISTBANK),

  /**
   * The number of vernacular name records in this dataset.
   */
  NUM_VERNACULAR_NAMES("numVernacularNames", CHECKLISTBANK),

  /**
   * The number of type records in this dataset.
   */
  NUM_TYPES("numTypes", CHECKLISTBANK),

  /**
   * The number of species information records in this dataset.
   */
  NUM_SPECIES_INFOS("numSpeciesInfos", CHECKLISTBANK);

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
