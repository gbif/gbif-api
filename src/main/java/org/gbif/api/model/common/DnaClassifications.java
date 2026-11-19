package org.gbif.api.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * DNA-assisted classifications of an occurrence, keyed by checklistKey, plus remarks common to all of them.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DnaClassifications {

  /** Remarks common to all the DNA-assisted classifications */
  private String remarks;
  /** The DNA-assisted classifications keyed by checklistKey */
  private Map<String, Classification> classifications;
}
