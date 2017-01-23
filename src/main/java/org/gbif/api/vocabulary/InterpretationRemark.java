package org.gbif.api.vocabulary;

import org.gbif.dwc.terms.Term;

import java.util.Set;

/**
 * Represents a remark flagged during the interpretation phase.
 */
public interface InterpretationRemark {

  /**
   * {@link Set} containing the {@link Term} considered to flag this remark.
   *
   * @return {@link Set} of {@link Term} or empty {@link Set}. Never null.
   */
  Set<Term> getRelatedTerms();

  /**
   * Returns the default severity of this {@link InterpretationRemark}.
   * Severity can be relative to the context and profile. This {@link InterpretationRemarkSeverity}
   * should be seen as the default severity in the general context of interpretation.
   *
   * @return default severity of this {@link InterpretationRemark}. Never null.
   */
  InterpretationRemarkSeverity getSeverity();

}
