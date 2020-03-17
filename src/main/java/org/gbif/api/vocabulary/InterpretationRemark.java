/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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

import org.gbif.dwc.terms.Term;

import java.util.Set;

/**
 * Represents a remark flagged during the interpretation phase.
 * IMPORTANT: Make sure no name clashes in case new implementation were added.
 */
public interface InterpretationRemark {

  /**
   * Returns the identifier of the {@link InterpretationRemark}.
   * This is normally implemented to return the result of {@link Enum#name()}.
   * Potential name clashes are detected by unit tests.
   *
   * @return identifier of the {@link InterpretationRemark}. Never null.
   */
  String getId();

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

  /**
   * Checks if the {@link InterpretationRemark} is deprecated or not.
   * @return true if the {@link InterpretationRemark} is marked with @Deprecated.
   */
  boolean isDeprecated();

}
