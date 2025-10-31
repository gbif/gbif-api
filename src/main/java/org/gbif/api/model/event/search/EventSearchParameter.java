/*
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
package org.gbif.api.model.event.search;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Hidden;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.vocabulary.DurationUnit;
import org.gbif.api.vocabulary.EventIssue;

/**
 * Supported query parameters by the occurrence search and download service. For download predicates
 * only the numerical types support comparisons other than equals.
 */
@JsonDeserialize(
    as = EventSearchParameter.class,
    using = EventSearchParameter.EventSearchParameterDeserializer.class)
public class EventSearchParameter implements SearchParameter, Serializable {

  /** See @link {@link OccurrenceSearchParameter#DATASET_KEY} */
  public static final EventSearchParameter DATASET_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.DATASET_KEY);

  /** See @link {@link OccurrenceSearchParameter#CHECKLIST_KEY} */
  public static final EventSearchParameter CHECKLIST_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.CHECKLIST_KEY);

  /** See @link {@link OccurrenceSearchParameter#YEAR} */
  public static final EventSearchParameter YEAR =
      new EventSearchParameter(OccurrenceSearchParameter.YEAR);

  /** See @link {@link OccurrenceSearchParameter#MONTH} */
  public static final EventSearchParameter MONTH =
      new EventSearchParameter(OccurrenceSearchParameter.MONTH);

  /** See @link {@link OccurrenceSearchParameter#DAY} */
  public static final EventSearchParameter DAY =
      new EventSearchParameter(OccurrenceSearchParameter.DAY);

  /** See @link {@link OccurrenceSearchParameter#START_DAY_OF_YEAR} */
  public static final EventSearchParameter START_DAY_OF_YEAR =
      new EventSearchParameter(OccurrenceSearchParameter.START_DAY_OF_YEAR);

  /** See @link {@link OccurrenceSearchParameter#END_DAY_OF_YEAR} */
  public static final EventSearchParameter END_DAY_OF_YEAR =
      new EventSearchParameter(OccurrenceSearchParameter.END_DAY_OF_YEAR);

  /** See @link {@link OccurrenceSearchParameter#EVENT_DATE} */
  public static final EventSearchParameter EVENT_DATE =
      new EventSearchParameter(OccurrenceSearchParameter.EVENT_DATE);

  /** See {@link OccurrenceSearchParameter#EVENT_ID} */
  public static final EventSearchParameter EVENT_ID =
      new EventSearchParameter(OccurrenceSearchParameter.EVENT_ID);

  /** See @link {@link OccurrenceSearchParameter#PARENT_EVENT_ID} */
  public static final EventSearchParameter PARENT_EVENT_ID =
      new EventSearchParameter(OccurrenceSearchParameter.PARENT_EVENT_ID);

  /** See @link {@link OccurrenceSearchParameter#SAMPLING_PROTOCOL} */
  public static final EventSearchParameter SAMPLING_PROTOCOL =
      new EventSearchParameter(OccurrenceSearchParameter.SAMPLING_PROTOCOL);

  /** See @link {@link OccurrenceSearchParameter#PREVIOUS_IDENTIFICATIONS} */
  public static final EventSearchParameter PREVIOUS_IDENTIFICATIONS =
      new EventSearchParameter(OccurrenceSearchParameter.PREVIOUS_IDENTIFICATIONS);

  /** See @link {@link OccurrenceSearchParameter#LAST_INTERPRETED} */
  public static final EventSearchParameter LAST_INTERPRETED =
      new EventSearchParameter(OccurrenceSearchParameter.LAST_INTERPRETED);

  /** See @link {@link OccurrenceSearchParameter#MODIFIED} */
  public static final EventSearchParameter MODIFIED =
      new EventSearchParameter(OccurrenceSearchParameter.MODIFIED);

  /** See @link {@link OccurrenceSearchParameter#DECIMAL_LATITUDE} */
  public static final EventSearchParameter DECIMAL_LATITUDE =
      new EventSearchParameter(OccurrenceSearchParameter.DECIMAL_LATITUDE);

  /** See @link {@link OccurrenceSearchParameter#DECIMAL_LONGITUDE} */
  public static final EventSearchParameter DECIMAL_LONGITUDE =
      new EventSearchParameter(OccurrenceSearchParameter.DECIMAL_LONGITUDE);

  /** See @link {@link OccurrenceSearchParameter#COORDINATE_UNCERTAINTY_IN_METERS} */
  public static final EventSearchParameter COORDINATE_UNCERTAINTY_IN_METERS =
      new EventSearchParameter(OccurrenceSearchParameter.COORDINATE_UNCERTAINTY_IN_METERS);

  /** See @link {@link OccurrenceSearchParameter#COUNTRY} */
  public static final EventSearchParameter COUNTRY =
      new EventSearchParameter(OccurrenceSearchParameter.COUNTRY);

  /** See @link {@link OccurrenceSearchParameter#GBIF_REGION} */
  public static final EventSearchParameter GBIF_REGION =
      new EventSearchParameter(OccurrenceSearchParameter.GBIF_REGION);

  /** See @link {@link OccurrenceSearchParameter#CONTINENT} */
  public static final EventSearchParameter CONTINENT =
      new EventSearchParameter(OccurrenceSearchParameter.CONTINENT);

  /** See @link {@link OccurrenceSearchParameter#PUBLISHING_COUNTRY} */
  public static final EventSearchParameter PUBLISHING_COUNTRY =
      new EventSearchParameter(OccurrenceSearchParameter.PUBLISHING_COUNTRY);

  /** See @link {@link OccurrenceSearchParameter#PUBLISHED_BY_GBIF_REGION} */
  public static final EventSearchParameter PUBLISHED_BY_GBIF_REGION =
      new EventSearchParameter(OccurrenceSearchParameter.PUBLISHED_BY_GBIF_REGION);

  /** See @link {@link OccurrenceSearchParameter#ELEVATION} */
  public static final EventSearchParameter ELEVATION =
      new EventSearchParameter(OccurrenceSearchParameter.ELEVATION);

  /** See @link {@link OccurrenceSearchParameter#DEPTH} */
  public static final EventSearchParameter DEPTH =
      new EventSearchParameter(OccurrenceSearchParameter.DEPTH);

  /** See @link {@link OccurrenceSearchParameter#INSTITUTION_CODE} */
  public static final EventSearchParameter INSTITUTION_CODE =
      new EventSearchParameter(OccurrenceSearchParameter.INSTITUTION_CODE);

  /** See @link {@link OccurrenceSearchParameter#COLLECTION_CODE} */
  public static final EventSearchParameter COLLECTION_CODE =
      new EventSearchParameter(OccurrenceSearchParameter.COLLECTION_CODE);

  /** See @link {@link OccurrenceSearchParameter#CATALOG_NUMBER} */
  public static final EventSearchParameter CATALOG_NUMBER =
      new EventSearchParameter(OccurrenceSearchParameter.CATALOG_NUMBER);

  /** See @link {@link OccurrenceSearchParameter#RECORD_NUMBER} */
  public static final EventSearchParameter RECORD_NUMBER =
      new EventSearchParameter(OccurrenceSearchParameter.RECORD_NUMBER);

  /** See @link {@link OccurrenceSearchParameter#IS_SEQUENCED} */
  public static final EventSearchParameter IS_SEQUENCED =
      new EventSearchParameter(OccurrenceSearchParameter.IS_SEQUENCED);

  /** See @link {@link OccurrenceSearchParameter#TAXON_KEY} */
  public static final EventSearchParameter TAXON_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.TAXON_KEY);

  /** See @link {@link OccurrenceSearchParameter#ACCEPTED_TAXON_KEY} */
  public static final EventSearchParameter ACCEPTED_TAXON_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.ACCEPTED_TAXON_KEY);

  /** See @link {@link OccurrenceSearchParameter#KINGDOM_KEY} */
  public static final EventSearchParameter KINGDOM_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.KINGDOM_KEY);

  /** See @link {@link OccurrenceSearchParameter#PHYLUM_KEY} */
  public static final EventSearchParameter PHYLUM_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.PHYLUM_KEY);

  /** See @link {@link OccurrenceSearchParameter#CLASS_KEY} */
  public static final EventSearchParameter CLASS_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.CLASS_KEY);

  /** See @link {@link OccurrenceSearchParameter#ORDER_KEY} */
  public static final EventSearchParameter ORDER_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.ORDER_KEY);

  /** See @link {@link OccurrenceSearchParameter#FAMILY_KEY} */
  public static final EventSearchParameter FAMILY_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.FAMILY_KEY);

  /** See @link {@link OccurrenceSearchParameter#GENUS_KEY} */
  public static final EventSearchParameter GENUS_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.GENUS_KEY);

  /** See @link {@link OccurrenceSearchParameter#SUBGENUS_KEY} */
  public static final EventSearchParameter SUBGENUS_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.SUBGENUS_KEY);

  /** See @link {@link OccurrenceSearchParameter#SPECIES_KEY} */
  public static final EventSearchParameter SPECIES_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.SPECIES_KEY);

  /** See @link {@link OccurrenceSearchParameter#SCIENTIFIC_NAME} */
  public static final EventSearchParameter SCIENTIFIC_NAME =
      new EventSearchParameter(OccurrenceSearchParameter.SCIENTIFIC_NAME);

  /** See @link {@link OccurrenceSearchParameter#VERBATIM_SCIENTIFIC_NAME} */
  public static final EventSearchParameter VERBATIM_SCIENTIFIC_NAME =
      new EventSearchParameter(OccurrenceSearchParameter.VERBATIM_SCIENTIFIC_NAME);

  /** See @link {@link OccurrenceSearchParameter#TAXON_ID} */
  public static final EventSearchParameter TAXON_ID =
      new EventSearchParameter(OccurrenceSearchParameter.TAXON_ID);

  /** See @link {@link OccurrenceSearchParameter#TAXON_CONCEPT_ID} */
  public static final EventSearchParameter TAXON_CONCEPT_ID =
      new EventSearchParameter(OccurrenceSearchParameter.TAXON_CONCEPT_ID);

  /** See @link {@link OccurrenceSearchParameter#TAXONOMIC_STATUS} */
  public static final EventSearchParameter TAXONOMIC_STATUS =
      new EventSearchParameter(OccurrenceSearchParameter.TAXONOMIC_STATUS);

  /** See @link {@link OccurrenceSearchParameter#HAS_COORDINATE} */
  public static final EventSearchParameter HAS_COORDINATE =
      new EventSearchParameter(OccurrenceSearchParameter.HAS_COORDINATE);

  /** See @link {@link OccurrenceSearchParameter#GEOMETRY} */
  public static final EventSearchParameter GEOMETRY =
      new EventSearchParameter(OccurrenceSearchParameter.GEOMETRY);

  /** See @link {@link OccurrenceSearchParameter#GEO_DISTANCE} */
  public static final EventSearchParameter GEO_DISTANCE =
      new EventSearchParameter(OccurrenceSearchParameter.GEO_DISTANCE);

  /** See @link {@link OccurrenceSearchParameter#DISTANCE_FROM_CENTROID_IN_METERS} */
  public static final EventSearchParameter DISTANCE_FROM_CENTROID_IN_METERS =
      new EventSearchParameter(OccurrenceSearchParameter.DISTANCE_FROM_CENTROID_IN_METERS);

  /** See @link {@link OccurrenceSearchParameter#HAS_GEOSPATIAL_ISSUE} */
  public static final EventSearchParameter HAS_GEOSPATIAL_ISSUE =
      new EventSearchParameter(OccurrenceSearchParameter.HAS_GEOSPATIAL_ISSUE);

  /** See @link {@link OccurrenceSearchParameter#ISSUE} */
  public static final EventSearchParameter OCCURRENCE_ISSUE =
      new EventSearchParameter(OccurrenceSearchParameter.ISSUE);

  /** See @link {@link OccurrenceSearchParameter#TAXONOMIC_ISSUE} */
  public static final EventSearchParameter TAXONOMIC_ISSUE =
      new EventSearchParameter(OccurrenceSearchParameter.TAXONOMIC_ISSUE);

  /** See @link {@link OccurrenceSearchParameter#MEDIA_TYPE} */
  public static final EventSearchParameter MEDIA_TYPE =
      new EventSearchParameter(OccurrenceSearchParameter.MEDIA_TYPE);

  /** See @link {@link OccurrenceSearchParameter#OCCURRENCE_ID} */
  public static final EventSearchParameter OCCURRENCE_ID =
      new EventSearchParameter(OccurrenceSearchParameter.OCCURRENCE_ID);

  /** See @link {@link OccurrenceSearchParameter#REPATRIATED} */
  public static final EventSearchParameter REPATRIATED =
      new EventSearchParameter(OccurrenceSearchParameter.REPATRIATED);

  /** See @link {@link OccurrenceSearchParameter#ORGANISM_ID} */
  public static final EventSearchParameter ORGANISM_ID =
      new EventSearchParameter(OccurrenceSearchParameter.ORGANISM_ID);

  /** See @link {@link OccurrenceSearchParameter#STATE_PROVINCE} */
  public static final EventSearchParameter STATE_PROVINCE =
      new EventSearchParameter(OccurrenceSearchParameter.STATE_PROVINCE);

  /** See @link {@link OccurrenceSearchParameter#WATER_BODY} */
  public static final EventSearchParameter WATER_BODY =
      new EventSearchParameter(OccurrenceSearchParameter.WATER_BODY);

  /** See @link {@link OccurrenceSearchParameter#LOCALITY} */
  public static final EventSearchParameter LOCALITY =
      new EventSearchParameter(OccurrenceSearchParameter.LOCALITY);

  /** See @link {@link OccurrenceSearchParameter#PROTOCOL} */
  public static final EventSearchParameter PROTOCOL =
      new EventSearchParameter(OccurrenceSearchParameter.PROTOCOL);

  /** See @link {@link OccurrenceSearchParameter#LICENSE} */
  public static final EventSearchParameter LICENSE =
      new EventSearchParameter(OccurrenceSearchParameter.LICENSE);

  /** See @link {@link OccurrenceSearchParameter#PUBLISHING_ORG} */
  public static final EventSearchParameter PUBLISHING_ORG =
      new EventSearchParameter(OccurrenceSearchParameter.PUBLISHING_ORG);

  /** See @link {@link OccurrenceSearchParameter#NETWORK_KEY} */
  public static final EventSearchParameter NETWORK_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.NETWORK_KEY);

  /** See @link {@link OccurrenceSearchParameter#INSTALLATION_KEY} */
  public static final EventSearchParameter INSTALLATION_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.INSTALLATION_KEY);

  /** See @link {@link OccurrenceSearchParameter#HOSTING_ORGANIZATION_KEY} */
  public static final EventSearchParameter HOSTING_ORGANIZATION_KEY =
      new EventSearchParameter(OccurrenceSearchParameter.HOSTING_ORGANIZATION_KEY);

  /** See @link {@link OccurrenceSearchParameter#CRAWL_ID} */
  public static final EventSearchParameter CRAWL_ID =
      new EventSearchParameter(OccurrenceSearchParameter.CRAWL_ID);

  /** See @link {@link OccurrenceSearchParameter#PROJECT_ID} */
  public static final EventSearchParameter PROJECT_ID =
      new EventSearchParameter(OccurrenceSearchParameter.PROJECT_ID);

  /** See @link {@link OccurrenceSearchParameter#PROGRAMME} */
  public static final EventSearchParameter PROGRAMME =
      new EventSearchParameter(OccurrenceSearchParameter.PROGRAMME);

  /** See @link {@link OccurrenceSearchParameter#SAMPLE_SIZE_UNIT} */
  public static final EventSearchParameter SAMPLE_SIZE_UNIT =
      new EventSearchParameter(OccurrenceSearchParameter.SAMPLE_SIZE_UNIT);

  /** See @link {@link OccurrenceSearchParameter#SAMPLE_SIZE_VALUE} */
  public static final EventSearchParameter SAMPLE_SIZE_VALUE =
      new EventSearchParameter(OccurrenceSearchParameter.SAMPLE_SIZE_VALUE);

  /** See @link {@link OccurrenceSearchParameter#ORGANISM_QUANTITY} */
  public static final EventSearchParameter ORGANISM_QUANTITY =
      new EventSearchParameter(OccurrenceSearchParameter.ORGANISM_QUANTITY);

  /** See @link {@link OccurrenceSearchParameter#ORGANISM_QUANTITY_TYPE} */
  public static final EventSearchParameter ORGANISM_QUANTITY_TYPE =
      new EventSearchParameter(OccurrenceSearchParameter.ORGANISM_QUANTITY_TYPE);

  /** See @link {@link OccurrenceSearchParameter#RELATIVE_ORGANISM_QUANTITY} */
  public static final EventSearchParameter RELATIVE_ORGANISM_QUANTITY =
      new EventSearchParameter(OccurrenceSearchParameter.RELATIVE_ORGANISM_QUANTITY);

  /** See @link {@link OccurrenceSearchParameter#OCCURRENCE_STATUS} */
  public static final EventSearchParameter OCCURRENCE_STATUS =
      new EventSearchParameter(OccurrenceSearchParameter.OCCURRENCE_STATUS);

  /** See @link {@link OccurrenceSearchParameter#GADM_GID} */
  public static final EventSearchParameter GADM_GID =
      new EventSearchParameter(OccurrenceSearchParameter.GADM_GID);

  /** See @link {@link OccurrenceSearchParameter#GADM_LEVEL_0_GID} */
  public static final EventSearchParameter GADM_LEVEL_0_GID =
      new EventSearchParameter(OccurrenceSearchParameter.GADM_LEVEL_0_GID);

  /** See @link {@link OccurrenceSearchParameter#GADM_LEVEL_1_GID} */
  public static final EventSearchParameter GADM_LEVEL_1_GID =
      new EventSearchParameter(OccurrenceSearchParameter.GADM_LEVEL_1_GID);

  /** See @link {@link OccurrenceSearchParameter#GADM_LEVEL_2_GID} */
  public static final EventSearchParameter GADM_LEVEL_2_GID =
      new EventSearchParameter(OccurrenceSearchParameter.GADM_LEVEL_2_GID);

  /** See @link {@link OccurrenceSearchParameter#GADM_LEVEL_3_GID} */
  public static final EventSearchParameter GADM_LEVEL_3_GID =
      new EventSearchParameter(OccurrenceSearchParameter.GADM_LEVEL_3_GID);

  /** See @link {@link OccurrenceSearchParameter#IS_IN_CLUSTER} */
  public static final EventSearchParameter IS_IN_CLUSTER =
      new EventSearchParameter(OccurrenceSearchParameter.IS_IN_CLUSTER);

  /** See @link {@link OccurrenceSearchParameter#DWCA_EXTENSION} */
  public static final EventSearchParameter DWCA_EXTENSION =
      new EventSearchParameter(OccurrenceSearchParameter.DWCA_EXTENSION);

  /** See @link {@link OccurrenceSearchParameter#IUCN_RED_LIST_CATEGORY} */
  public static final EventSearchParameter IUCN_RED_LIST_CATEGORY =
      new EventSearchParameter(OccurrenceSearchParameter.IUCN_RED_LIST_CATEGORY);

  /** See @link {@link OccurrenceSearchParameter#DATASET_ID} */
  public static final EventSearchParameter DATASET_ID =
      new EventSearchParameter(OccurrenceSearchParameter.DATASET_ID);

  /** See @link {@link OccurrenceSearchParameter#DATASET_NAME} */
  public static final EventSearchParameter DATASET_NAME =
      new EventSearchParameter(OccurrenceSearchParameter.DATASET_NAME);

  /** See @link {@link OccurrenceSearchParameter#OTHER_CATALOG_NUMBERS} */
  public static final EventSearchParameter OTHER_CATALOG_NUMBERS =
      new EventSearchParameter(OccurrenceSearchParameter.OTHER_CATALOG_NUMBERS);

  /** See @link {@link OccurrenceSearchParameter#PREPARATIONS} */
  public static final EventSearchParameter PREPARATIONS =
      new EventSearchParameter(OccurrenceSearchParameter.PREPARATIONS);

  /** See @link {@link OccurrenceSearchParameter#ISLAND} */
  public static final EventSearchParameter ISLAND =
      new EventSearchParameter(OccurrenceSearchParameter.ISLAND);

  /** See @link {@link OccurrenceSearchParameter#ISLAND_GROUP} */
  public static final EventSearchParameter ISLAND_GROUP =
      new EventSearchParameter(OccurrenceSearchParameter.ISLAND_GROUP);

  /** See @link {@link OccurrenceSearchParameter#GEOREFERENCED_BY} */
  public static final EventSearchParameter GEOREFERENCED_BY =
      new EventSearchParameter(OccurrenceSearchParameter.GEOREFERENCED_BY);

  /** See @link {@link OccurrenceSearchParameter#HIGHER_GEOGRAPHY} */
  public static final EventSearchParameter HIGHER_GEOGRAPHY =
      new EventSearchParameter(OccurrenceSearchParameter.HIGHER_GEOGRAPHY);

  /** See @link {@link OccurrenceSearchParameter#FIELD_NUMBER} */
  public static final EventSearchParameter FIELD_NUMBER =
      new EventSearchParameter(OccurrenceSearchParameter.FIELD_NUMBER);

  /** See @link {@link OccurrenceSearchParameter#ASSOCIATED_SEQUENCES} */
  public static final EventSearchParameter ASSOCIATED_SEQUENCES =
      new EventSearchParameter(OccurrenceSearchParameter.ASSOCIATED_SEQUENCES);

  /** See @link {@link OccurrenceSearchParameter#GBIF_ID} */
  public static final EventSearchParameter GBIF_ID =
      new EventSearchParameter(OccurrenceSearchParameter.GBIF_ID);

  /** An identifier for an Event and its children events. */
  public static final EventSearchParameter EVENT_ID_HIERARCHY =
      new EventSearchParameter("EVENT_ID_HIERARCHY", String.class);

  /** An identifier for the vocabulary-backed Event Type. */
  public static final EventSearchParameter EVENT_TYPE =
      new EventSearchParameter("EVENT_TYPE", String.class);

  /** An identifier for the verbatim Event Type. */
  @Experimental
  public static final EventSearchParameter VERBATIM_EVENT_TYPE =
      new EventSearchParameter("VERBATIM_EVENT_TYPE", String.class);

  /** Searches events for those that have a specific issue. */
  // TODO: handle this in the argument resolver to use this instead of the occurrence one
  public static final EventSearchParameter ISSUE =
      new EventSearchParameter("ISSUE", EventIssue.class);

  /** Humboldt fields * */
  public static final EventSearchParameter HUMBOLDT_SITE_COUNT =
      new EventSearchParameter("HUMBOLDT_SITE_COUNT", Integer.class);

  public static final EventSearchParameter HUMBOLDT_VERBATIM_SITE_NAMES =
      new EventSearchParameter("HUMBOLDT_VERBATIM_SITE_NAMES", String.class);
  public static final EventSearchParameter HUMBOLDT_GEOSPATIAL_SCOPE_AREA_VALUE =
      new EventSearchParameter("HUMBOLDT_GEOSPATIAL_SCOPE_AREA_VALUE", Double.class);
  public static final EventSearchParameter HUMBOLDT_GEOSPATIAL_SCOPE_AREA_UNIT =
      new EventSearchParameter("HUMBOLDT_GEOSPATIAL_SCOPE_AREA_UNIT", String.class);
  public static final EventSearchParameter HUMBOLDT_TOTAL_AREA_SAMPLED_VALUE =
      new EventSearchParameter("HUMBOLDT_TOTAL_AREA_SAMPLED_VALUE", Double.class);
  public static final EventSearchParameter HUMBOLDT_TOTAL_AREA_SAMPLED_UNIT =
      new EventSearchParameter("HUMBOLDT_TOTAL_AREA_SAMPLED_UNIT", String.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_HABITAT_SCOPE =
      new EventSearchParameter("HUMBOLDT_TARGET_HABITAT_SCOPE", String.class);
  public static final EventSearchParameter HUMBOLDT_EVENT_DURATION =
      new EventSearchParameter("HUMBOLDT_EVENT_DURATION", String.class);

  @Hidden
  public static final EventSearchParameter HUMBOLDT_EVENT_DURATION_VALUE_IN_MINUTES =
      new EventSearchParameter("HUMBOLDT_EVENT_DURATION_VALUE_IN_MINUTES", Double.class);

  public static final EventSearchParameter HUMBOLDT_EVENT_DURATION_VALUE =
      new EventSearchParameter("HUMBOLDT_EVENT_DURATION_VALUE", Double.class);
  public static final EventSearchParameter HUMBOLDT_EVENT_DURATION_UNIT =
      new EventSearchParameter("HUMBOLDT_EVENT_DURATION_UNIT", DurationUnit.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_NAME =
      new EventSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_NAME", String.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_KEY =
      new EventSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_USAGE_KEY", String.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_TAXONOMIC_SCOPE_TAXON_KEY =
      new EventSearchParameter("HUMBOLDT_TARGET_TAXONOMIC_SCOPE_TAXON_KEY", String.class);
  public static final EventSearchParameter HUMBOLDT_TAXON_COMPLETENESS_PROTOCOLS =
      new EventSearchParameter("HUMBOLDT_TAXON_COMPLETENESS_PROTOCOLS", String.class);
  public static final EventSearchParameter HUMBOLDT_IS_TAXONOMIC_SCOPE_FULLY_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_TAXONOMIC_SCOPE_FULLY_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_IS_ABSENCE_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_ABSENCE_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_HAS_NON_TARGET_TAXA =
      new EventSearchParameter("HUMBOLDT_HAS_NON_TARGET_TAXA", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_ARE_NON_TARGET_TAXA_FULLY_REPORTED =
      new EventSearchParameter("HUMBOLDT_ARE_NON_TARGET_TAXA_FULLY_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_LIFE_STAGE_SCOPE =
      new EventSearchParameter("HUMBOLDT_TARGET_LIFE_STAGE_SCOPE", String.class);
  public static final EventSearchParameter HUMBOLDT_IS_LIFE_STAGE_SCOPE_FULLY_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_LIFE_STAGE_SCOPE_FULLY_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_DEGREE_OF_ESTABLISHMENT_SCOPE =
      new EventSearchParameter("HUMBOLDT_TARGET_DEGREE_OF_ESTABLISHMENT_SCOPE", String.class);
  public static final EventSearchParameter
      HUMBOLDT_IS_DEGREE_OF_ESTABLISHMENT_SCOPE_FULLY_REPORTED =
          new EventSearchParameter(
              "HUMBOLDT_IS_DEGREE_OF_ESTABLISHMENT_SCOPE_FULLY_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_TARGET_GROWTH_FORM_SCOPE =
      new EventSearchParameter("HUMBOLDT_TARGET_GROWTH_FORM_SCOPE", String.class);
  public static final EventSearchParameter HUMBOLDT_IS_GROWTH_FORM_SCOPE_FULLY_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_GROWTH_FORM_SCOPE_FULLY_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_HAS_NON_TARGET_ORGANISMS =
      new EventSearchParameter("HUMBOLDT_HAS_NON_TARGET_ORGANISMS", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_COMPILATION_TYPES =
      new EventSearchParameter("HUMBOLDT_COMPILATION_TYPES", String.class);
  public static final EventSearchParameter HUMBOLDT_COMPILATION_SOURCE_TYPES =
      new EventSearchParameter("HUMBOLDT_COMPILATION_SOURCE_TYPES", String.class);
  public static final EventSearchParameter HUMBOLDT_INVENTORY_TYPES =
      new EventSearchParameter("HUMBOLDT_INVENTORY_TYPES", String.class);
  public static final EventSearchParameter HUMBOLDT_PROTOCOL_NAMES =
      new EventSearchParameter("HUMBOLDT_PROTOCOL_NAMES", String.class);
  public static final EventSearchParameter HUMBOLDT_IS_ABUNDANCE_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_ABUNDANCE_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_IS_ABUNDANCE_CAP_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_ABUNDANCE_CAP_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_ABUNDANCE_CAP =
      new EventSearchParameter("HUMBOLDT_ABUNDANCE_CAP", Integer.class);
  public static final EventSearchParameter HUMBOLDT_IS_VEGETATION_COVER_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_VEGETATION_COVER_REPORTED", Boolean.class);
  public static final EventSearchParameter
      HUMBOLDT_IS_LEAST_SPECIFIC_TARGET_CATEGORY_QUANTITY_INCLUSIVE =
          new EventSearchParameter(
              "HUMBOLDT_IS_LEAST_SPECIFIC_TARGET_CATEGORY_QUANTITY_INCLUSIVE", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_HAS_VOUCHERS =
      new EventSearchParameter("HUMBOLDT_HAS_VOUCHERS", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_VOUCHER_INSTITUTIONS =
      new EventSearchParameter("HUMBOLDT_VOUCHER_INSTITUTIONS", String.class);
  public static final EventSearchParameter HUMBOLDT_HAS_MATERIAL_SAMPLES =
      new EventSearchParameter("HUMBOLDT_HAS_MATERIAL_SAMPLES", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_MATERIAL_SAMPLE_TYPES =
      new EventSearchParameter("HUMBOLDT_MATERIAL_SAMPLE_TYPES", String.class);
  public static final EventSearchParameter HUMBOLDT_SAMPLING_PERFORMED_BY =
      new EventSearchParameter("HUMBOLDT_SAMPLING_PERFORMED_BY", String.class);
  public static final EventSearchParameter HUMBOLDT_IS_SAMPLING_EFFORT_REPORTED =
      new EventSearchParameter("HUMBOLDT_IS_SAMPLING_EFFORT_REPORTED", Boolean.class);
  public static final EventSearchParameter HUMBOLDT_SAMPLING_EFFORT_VALUE =
      new EventSearchParameter("HUMBOLDT_SAMPLING_EFFORT_VALUE", Double.class);
  public static final EventSearchParameter HUMBOLDT_SAMPLING_EFFORT_UNIT =
      new EventSearchParameter("HUMBOLDT_SAMPLING_EFFORT_UNIT", String.class);

  public static EventSearchParameter[] values() {

    Field[] values = EventSearchParameter.class.getFields();
    List<EventSearchParameter> c = new ArrayList<>();
    for (Field field : values) {
      try {
        c.add((EventSearchParameter) field.get(EventSearchParameter.class));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return c.toArray(new EventSearchParameter[0]);
  }

  private Class<?> type;
  private String name;

  public EventSearchParameter() {}

  public EventSearchParameter(OccurrenceSearchParameter occurrenceSearchParameter) {
    this.name = occurrenceSearchParameter.getName();
    this.type = occurrenceSearchParameter.getType();
  }

  public EventSearchParameter(String name, Class<?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String name() {
    return this.name;
  }

  public Class<?> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public Class<?> type() {
    return this.type;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EventSearchParameter that = (EventSearchParameter) o;
    return Objects.equals(type, that.type) && Objects.equals(name, that.name);
  }

  @JsonValue
  public String getSerializedValue() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name);
  }

  /**
   * Lookup a parameter by its name.
   *
   * @param name the name of the parameter
   * @return the parameter if found, otherwise empty
   */
  public static Optional<EventSearchParameter> lookupEventParam(String name) {
    String normedType = name.toUpperCase().replaceAll("[. _-]", "");
    Field[] values = EventSearchParameter.class.getFields();
    for (Field field : values) {

      String fieldName = field.getName();
      String normedVal = fieldName.replaceAll("[. _-]", "");
      if (normedType.equals(normedVal)) {
        try {
          return Optional.of((EventSearchParameter) field.get(EventSearchParameter.class));
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return Optional.empty();
  }

  public static class EventSearchParameterKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String value, DeserializationContext deserializationContext)
        throws IOException {
      Field[] values = EventSearchParameter.class.getFields();
      try {
        for (Field field : values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (EventSearchParameter) field.get(EventSearchParameter.class);
          }
        }
      } catch (IllegalAccessException e) {
        // DO NOTHING
      }
      return null;
    }
  }

  public static class EventSearchParameterDeserializer
      extends JsonDeserializer<EventSearchParameter> {

    @Override
    public EventSearchParameter deserialize(
        com.fasterxml.jackson.core.JsonParser jsonParser,
        DeserializationContext deserializationContext)
        throws IOException, JacksonException {

      Field[] values = EventSearchParameter.class.getFields();
      try {
        String value = jsonParser.getText();
        for (Field field : values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (EventSearchParameter) field.get(EventSearchParameter.class);
          }
        }

      } catch (IllegalAccessException e) {
        // DO NOTHING
      }

      try {
        ObjectNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.get("name").asText();
        for (Field field : values) {
          if (field.getName().equalsIgnoreCase(value)) {
            return (EventSearchParameter) field.get(EventSearchParameter.class);
          }
        }
      } catch (Exception e) {
        // DO NOTHING
      }

      return null;
    }
  }
}
