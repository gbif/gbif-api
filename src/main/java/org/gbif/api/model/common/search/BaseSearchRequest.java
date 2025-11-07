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

import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_LIMIT;
import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_OFFSET;

import io.swagger.v3.oas.annotations.Hidden;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import lombok.Getter;
import lombok.Setter;
import org.gbif.api.annotation.Experimental;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.util.IsoDateInterval;

/**
 * Generic request class for search operations. This class contains a list of parameters, a list of
 * desired facets and paging options (page size and offset).
 */
@SuppressWarnings("unused")
public abstract class BaseSearchRequest<P extends SearchParameter> extends PageableBase
    implements SearchRequest<P> {

  @Hidden private Map<P, Set<String>> parameters = new HashMap<>();

  @Hidden private String q;

  @Hidden private boolean highlight;

  @Hidden private boolean spellCheck;

  @Hidden private int spellCheckCount;

  @Hidden private Set<QueryField> qFields = new HashSet<>();

  @Hidden private Set<QueryField> highlightFields = new HashSet<>();

  @Hidden @Experimental @Setter private Boolean matchCase;

  /**
   * This flag allows to sort the results in a random order by specifying a seed. The seed makes the
   * random results reproducible so we can use paging. The same seed has to be sent for each page.
   */
  @Hidden @Experimental @Getter @Setter private String shuffle;

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
  @Override
  @Experimental
  public Boolean isMatchCase() {
    return Optional.ofNullable(matchCase).orElse(Boolean.FALSE);
  }

  /** Constructor with default paging offset & limit. */
  public BaseSearchRequest() {
    super(DEFAULT_PARAM_OFFSET, DEFAULT_PARAM_LIMIT);
  }

  /**
   * Simple query constructor with default paging offset & limit.
   *
   * @param query string for request
   */
  public BaseSearchRequest(String query) {
    super(DEFAULT_PARAM_OFFSET, DEFAULT_PARAM_LIMIT);
    q = query;
  }

  /** Minimal paging constructor. */
  public BaseSearchRequest(Pageable page) {
    super(page.getOffset(), page.getLimit());
  }

  /** Minimal paging constructor. */
  public BaseSearchRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * @return true if highlighted search matches are requested
   */
  @Override
  public boolean isHighlight() {
    return highlight;
  }

  /**
   * @param highlight the highlight to set
   */
  @Override
  public void setHighlight(boolean highlight) {
    this.highlight = highlight;
  }

  /**
   * @return true if spellCheck search is requested
   */
  @Override
  public boolean isSpellCheck() {
    return spellCheck;
  }

  /**
   * @param spellCheck the highlight to set
   */
  @Override
  public void setSpellCheck(boolean spellCheck) {
    this.spellCheck = spellCheck;
  }

  /**
   * @return max number of spell check suggestions requested
   */
  @Override
  public int getSpellCheckCount() {
    return spellCheckCount;
  }

  /**
   * @param spellCheckCount number of spell check suggestions
   */
  @Override
  public void setSpellCheckCount(int spellCheckCount) {
    this.spellCheckCount = spellCheckCount;
  }

  /** Defines whether to match against fields with scientific or vernacular names or both. */
  @Hidden
  public Set<QueryField> getQFields() {
    return qFields;
  }

  @Hidden
  public void setQFields(Set<QueryField> qFields) {
    this.qFields = qFields;
  }

  /** Defines the fields to be highlighted if highlighting is activated. */
  @Override
  public Set<QueryField> getHighlightFields() {
    return highlightFields;
  }

  @Override
  public void setHighlightFields(Set<QueryField> highlightFields) {
    this.highlightFields = highlightFields;
  }

  /**
   * List of input parameters of the search operation. The parameters are handled as the parameter
   * name and the string representation of its value.
   *
   * @return the list of parameters
   */
  @Override
  public Map<P, Set<String>> getParameters() {
    return parameters;
  }

  /** Sets the list of parameters. */
  @Override
  public void setParameters(Map<P, Set<String>> parameters) {
    this.parameters = parameters;
  }

  /**
   * Query parameter.
   *
   * @return the q
   */
  @Override
  public String getQ() {
    return q;
  }

  /**
   * @param q the q to set
   */
  @Override
  public void setQ(String q) {
    this.q = q;
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values list of values of the parameter to add
   */
  @Override
  public void addParameter(P parameter, Iterable<String> values) {
    if (parameters.containsKey(parameter)) {
      Set<String> paramValues = parameters.get(parameter);
      values.forEach(paramValues::add);
    } else {
      Set<String> paramValues = new HashSet<>();
      values.forEach(paramValues::add);
      parameters.put(parameter, paramValues);
    }
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values list of values of the parameter to add
   */
  @Override
  public void addParameter(P parameter, String... values) {
    for (String value : values) {
      addParameter(parameter, value);
    }
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, String value) {
    if (value != null) {
      if (parameters.containsKey(parameter)) {
        Set<String> paramValues = parameters.get(parameter);
        paramValues.add(value);
      } else {
        Set<String> paramValues = new HashSet<>();
        paramValues.add(value);
        parameters.put(parameter, paramValues);
      }
    }
  }

  /**
   * Adds the specified long parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, long value) {
    addParameter(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified int parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, int value) {
    addParameter(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified double parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, double value) {
    addParameter(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified boolean parameter.
   *
   * @param parameter parameter to add values for
   * @param value value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, boolean value) {
    addParameter(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value enum value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, Enum<?> value) {
    if (value != null) {
      addParameter(parameter, value.name());
    }
  }

  /**
   * Adds the specified date parameter as an ISO date.
   *
   * @param parameter parameter to add date for
   * @param value date value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, Date value) {
    if (value != null) {
      // not thread safe, new instance
      DateFormat iso = new SimpleDateFormat("yyyy-MM-dd");
      addParameter(parameter, iso.format(value));
    }
  }

  /**
   * Adds the specified date parameter as an ISO date interval.
   *
   * @param parameter parameter to add date interval for
   * @param value date value of the parameter to add
   */
  @Override
  public void addParameter(P parameter, IsoDateInterval value) {
    if (value != null) {
      addParameter(parameter, value.toString());
    }
  }

  @Override
  public void copyPagingValues(Pageable pageable) {
    super.copyPagingValues(pageable);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BaseSearchRequest.class.getSimpleName() + "[", "]")
        .add("parameters=" + parameters)
        .add("q='" + q + "'")
        .add("highlight=" + highlight)
        .add("spellCheck=" + spellCheck)
        .add("spellCheckCount=" + spellCheckCount)
        .add("offset=" + offset)
        .add("limit=" + limit)
        .toString();
  }
}
