package org.gbif.api.vocabulary;

/**
 * Enumeration of issues for each name usage record encountered during checklist processing.
 */
public enum NameUsageIssue {
  PARENT_NAME_USAGE_ID_INVALID,
  ACCEPTED_NAME_USAGE_ID_INVALID,
  ORIGINAL_NAME_USAGE_ID_INVALID,

  /**
   * dwc:taxonRank could not be interpreted
   */
  RANK_INVALID,

  /**
   * dwc:nomenclaturalStatus could not be interpreted
   */
  NOMENCLATURAL_STATUS_INVALID,

  /**
   * dwc:taxonomicStatus could not be interpreted
   */
  TAXONOMIC_STATUS_INVALID,

  /**
   * The scientific name was assembled from the individual name parts and not given as a whole string.
   */
  SCIENTIFIC_NAME_ASSEMBLED,

  /**
   * If a synonym points to another synonym as its accepted taxon the chain is resolved.
   */
  CHAINED_SYNOYM,

  /**
   * The authorship of the original name does not match the authorshipin brackets of the actual name.
   */
  BASIONYM_AUTHOR_MISMATCH,

  TAXONOMIC_STATUS_MISMATCH,

  /**
   * The child parent classification resulted into a cycle that needed to be resolved/cut.
   */
  PARENT_CYCLE,

  CLASSIFICATION_RANK_ORDER_INVALID,

  /**
   * The denormalized classification could not be applied to the name usage.
   * For example if the id based classification has no ranks.
   */
  CLASSIFICATION_NOT_APPLIED


}
