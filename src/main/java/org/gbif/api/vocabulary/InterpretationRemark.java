package org.gbif.api.vocabulary;

import org.gbif.dwc.terms.Term;

import java.util.Set;

/**
 * Represents a remark flagged during the interpretation phase.
 */
public interface InterpretationRemark {

  /**
   * {@link Set} containing the {@link Term} considered to flag this remark.
   * @return {@link Set} of {@link Term} or empty {@link Set}. Never null.
   */
  Set<Term> getRelatedTerms();

}
