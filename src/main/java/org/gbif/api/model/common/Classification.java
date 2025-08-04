package org.gbif.api.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import org.gbif.api.v2.RankedName;
import org.gbif.api.v2.Usage;

import java.util.List;

/**
 * Represents a classification of a taxon, including its usage, accepted usage,
 * and any issues associated with it.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classification {

  /** The usage that has been matched to */
  private Usage usage;
  /** The accepted usage of the taxon */
  private Usage acceptedUsage;
  /** The taxonomic status **/
  private String taxonomicStatus;
  /** The full taxonomic classification of the taxon */
  private List<RankedName> classification;
  /** The IUCN status of the taxon */
  private String iucnRedListCategoryCode;
  /** The issues associated with the match to this classification */
  private List<String> issues;
}
