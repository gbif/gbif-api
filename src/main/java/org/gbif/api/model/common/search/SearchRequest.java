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
package org.gbif.api.model.common.search;


import io.swagger.v3.oas.annotations.Hidden;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.util.IsoDateInterval;

/**
 * Generic request class for search operations. This class contains a list of parameters, a list of
 * desired facets and paging options (page size and offset).
 */
public interface SearchRequest<P extends SearchParameter> extends Pageable {

  /**
   * This flag enables the use of case-sensitive matches and aggregations on certain search
   * parameters.
   *
   * <p>Fields that support this feature are: occurrenceId, recordedBy, samplingProtocol,
   * catalogNumber, collectionCode, institutionCode, eventId, parentEventId, waterBody,
   * stateProvince, recordNumber, identifiedBy, organismId and locality.
   *
   * <p>This is an experimental feature, and its implementation may change or be removed at any
   * time.
   *
   * <p>Be aware that this is not a per-field flag, all possible fields will match case sensitively.
   */
  @Experimental
  Boolean isMatchCase();

  interface QueryField {}

  /**
   * @return true if highlighted search matches are requested
   */
  boolean isHighlight();

  /**
   * @param highlight the highlight to set
   */
  void setHighlight(boolean highlight);

  /**
   * @return true if spellCheck search is requested
   */
  boolean isSpellCheck();

  /**
   * @param spellCheck the highlight to set
   */
  void setSpellCheck(boolean spellCheck);

  /**
   * @return max number of spell check suggestions requested
   */
  int getSpellCheckCount();

  /**
   * @param spellCheckCount number of spell check suggestions
   */
  void setSpellCheckCount(int spellCheckCount);

  /** Defines whether to match against fields with scientific or vernacular names or both. */
  @Hidden
  Set<QueryField> getQFields();

  @Hidden
  void setQFields(Set<QueryField> qFields);

  /** Defines the fields to be highlighted if highlighting is activated. */
  Set<QueryField> getHighlightFields();

  void setHighlightFields(Set<QueryField> highlightFields);

  /**
   * List of input parameters of the search operation. The parameters are handled as the parameter
   * name and the string representation of its value.
   *
   * @return the list of parameters
   */
  Map<P, Set<String>> getParameters();

  /** Sets the list of parameters. */
  void setParameters(Map<P, Set<String>> parameters);

  /**
   * Query parameter.
   *
   * @return the q
   */
  String getQ();

  /**
   * @param q the q to set
   */
  void setQ(String q);

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values list of values of the parameter to add
   */
  void addParameter(P parameter, Iterable<String> values);

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values list of values of the parameter to add
   */
  void addParameter(P parameter, String... values);

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  void addParameter(P parameter, String value);

  /**
   * Adds the specified long parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  void addParameter(P parameter, long value);

  /**
   * Adds the specified int parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  void addParameter(P parameter, int value);

  /**
   * Adds the specified double parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  void addParameter(P parameter, double value);

  /**
   * Adds the specified boolean parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  void addParameter(P parameter, boolean value);

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value enum value of the parameter to add
   */
  void addParameter(P parameter, Enum<?> value);

  /**
   * Adds the specified date parameter as an ISO date.
   *
   * @param parameter parameter to add date for
   * @param value date value of the parameter to add
   */
  void addParameter(P parameter, Date value);

  /**
   * Adds the specified date parameter as an ISO date interval.
   *
   * @param parameter parameter to add date interval for
   * @param value date value of the parameter to add
   */
  void addParameter(P parameter, IsoDateInterval value);

  /**
   * Utility method to copy paging values.
   */
  void copyPagingValues(Pageable pageable);
}
