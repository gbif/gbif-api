package org.gbif.api.model.pipelines;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/** Enum to represent the pipelines internal interpretation type steps. */
public interface InterpretationType extends Serializable {

  String name();

  String all();

  enum RecordType implements InterpretationType {
    ALL,
    // Raw
    VERBATIM,
    // Core types
    IDENTIFIER,
    EVENT_IDENTIFIER,
    IDENTIFIER_ABSENT,
    METADATA,
    BASIC,
    CLUSTERING,
    TEMPORAL,
    LOCATION,
    TAXONOMY,
    MULTI_TAXONOMY,
    GRSCICOLL,
    EVENT,
    // Extension types
    IMAGE,
    MULTIMEDIA,
    AUDUBON,
    MEASUREMENT_OR_FACT,
    AMPLIFICATION,
    DNA_DERIVED_DATA,
    HUMBOLDT,
    // Specific
    LOCATION_FEATURE,
    // Tables,
    // Remeber to add mapping to org.gbif.pipelines.core.utils.DwcaExtensionTermUtils
    // and org.gbif.pipelines.ingest.utils.HdfsViewAvroUtils
    OCCURRENCE,
    MEASUREMENT_OR_FACT_TABLE,
    IDENTIFICATION_TABLE,
    RESOURCE_RELATIONSHIP_TABLE,
    AMPLIFICATION_TABLE,
    CLONING_TABLE,
    GEL_IMAGE_TABLE,
    LOAN_TABLE,
    MATERIAL_SAMPLE_TABLE,
    PERMIT_TABLE,
    PREPARATION_TABLE,
    PRESERVATION_TABLE,
    GERMPLASM_MEASUREMENT_SCORE_TABLE,
    GERMPLASM_MEASUREMENT_TRAIT_TABLE,
    GERMPLASM_MEASUREMENT_TRIAL_TABLE,
    GERMPLASM_ACCESSION_TABLE,
    EXTENDED_MEASUREMENT_OR_FACT_TABLE,
    CHRONOMETRIC_AGE_TABLE,
    REFERENCE_TABLE,
    IDENTIFIER_TABLE,
    AUDUBON_TABLE,
    IMAGE_TABLE,
    MULTIMEDIA_TABLE,
    DNA_DERIVED_DATA_TABLE,
    HUMBOLDT_TABLE,
    // this is the occurrence extension
    OCCURRENCE_TABLE;

    @Override
    public String all() {
      return ALL.name();
    }

    public static Set<RecordType> getAllInterpretation() {
      return new HashSet<>(
        Arrays.asList(
          VERBATIM,
          // Core types
          METADATA,
          IDENTIFIER_ABSENT,
          CLUSTERING,
          BASIC,
          TEMPORAL,
          LOCATION,
          TAXONOMY,
          MULTI_TAXONOMY,
          GRSCICOLL,
          // Extension types
          IMAGE,
          MULTIMEDIA,
          AUDUBON,
          MEASUREMENT_OR_FACT,
          AMPLIFICATION,
          DNA_DERIVED_DATA,
          HUMBOLDT,
          // Specific
          LOCATION_FEATURE,
          OCCURRENCE));
    }

    public static Set<String> getAllInterpretationAsString() {
      return getAllInterpretation().stream().map(RecordType::name).collect(Collectors.toSet());
    }

    public static Set<String> getAllValidatorInterpretationAsString() {
      Set<String> set = getAllInterpretationAsString();
      set.add(IDENTIFIER.name());
      set.remove(IDENTIFIER_ABSENT.name());
      return set;
    }

    public static Set<RecordType> getAllTables() {
      return new HashSet<>(
        Arrays.asList(
          OCCURRENCE,
          MEASUREMENT_OR_FACT_TABLE,
          IDENTIFICATION_TABLE,
          RESOURCE_RELATIONSHIP_TABLE,
          AMPLIFICATION_TABLE,
          CLONING_TABLE,
          GEL_IMAGE_TABLE,
          LOAN_TABLE,
          MATERIAL_SAMPLE_TABLE,
          PERMIT_TABLE,
          PREPARATION_TABLE,
          PRESERVATION_TABLE,
          GERMPLASM_MEASUREMENT_SCORE_TABLE,
          GERMPLASM_MEASUREMENT_TRAIT_TABLE,
          GERMPLASM_MEASUREMENT_TRIAL_TABLE,
          GERMPLASM_ACCESSION_TABLE,
          EXTENDED_MEASUREMENT_OR_FACT_TABLE,
          CHRONOMETRIC_AGE_TABLE,
          REFERENCE_TABLE,
          IDENTIFIER_TABLE,
          AUDUBON_TABLE,
          IMAGE_TABLE,
          MULTIMEDIA_TABLE,
          DNA_DERIVED_DATA_TABLE,
          HUMBOLDT_TABLE,
          OCCURRENCE_TABLE));
    }
  }
}
