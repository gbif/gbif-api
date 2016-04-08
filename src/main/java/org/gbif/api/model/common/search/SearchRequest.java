/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.common.search;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_LIMIT;
import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_OFFSET;

/**
 * Generic request class for search operations. This class contains a list of parameters,
 * a list of desired facets and paging options (page size and offset).
 */
public class SearchRequest<P extends SearchParameter> extends PageableBase {

  private Multimap<P, String> parameters = HashMultimap.create();
  private String q;
  private boolean highlight;
  private boolean spellCheck;
  private int spellCheckCount;

  /**
   * Constructor with default paging offset & limit.
   */
  public SearchRequest() {
    super(DEFAULT_PARAM_OFFSET, DEFAULT_PARAM_LIMIT);
  }

  /**
   * Simple query constructor with default paging offset & limit.
   *
   * @param query string for request
   */
  public SearchRequest(String query) {
    super(DEFAULT_PARAM_OFFSET, DEFAULT_PARAM_LIMIT);
    q = query;
  }

  /**
   * Minimal paging constructor.
   */
  public SearchRequest(Pageable page) {
    super(page.getOffset(), page.getLimit());
  }

  /**
   * Minimal paging constructor.
   */
  public SearchRequest(long offset, int limit) {
    super(offset, limit);
  }


  /**
   * @return true if highlighted search matches are requested
   */
  public boolean isHighlight() {
    return highlight;
  }

  /**
   * @param highlight the highlight to set
   */
  public void setHighlight(boolean highlight) {
    this.highlight = highlight;
  }

  /**
   *
   * @return true if spellCheck search is requested
   */
  public boolean isSpellCheck() {
    return spellCheck;
  }

  /**
   * @param spellCheck the highlight to set
   */
  public void setSpellCheck(boolean spellCheck) {
    this.spellCheck = spellCheck;
  }

  /**
   *
   * @return max number of spell check suggestions requested
   */
  public int getSpellCheckCount() {
    return spellCheckCount;
  }

  /**
   *
   * @param spellCheckCount number of spell check suggestions
   */
  public void setSpellCheckCount(int spellCheckCount) {
    this.spellCheckCount = spellCheckCount;
  }

  /**
   * List of input parameters of the search operation.
   * The parameters are handled as the parameter name and the string representation of its value.
   *
   * @return the list of parameters
   */
  public Multimap<P, String> getParameters() {
    return parameters;
  }

  /**
   * Sets the list of parameters.
   */
  public void setParameters(Multimap<P, String> parameters) {
    this.parameters = parameters;
  }

  /**
   * Query parameter.
   *
   * @return the q
   */
  public String getQ() {
    return q;
  }

  /**
   * @param q the q to set
   */
  public void setQ(String q) {
    this.q = q;
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values    list of values of the parameter to add
   */
  public void addParameter(P parameter, Iterable<String> values) {
    for (String parameterValue : values) {
      parameters.put(parameter, parameterValue);
    }
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param values    list of values of the parameter to add
   */
  public void addParameter(P parameter, String... values) {
    for (String value : values) {
      addParameter(parameter, value);
    }
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value     value of the parameter to add
   */
  public void addParameter(P parameter, String value) {
    if (value != null) {
      parameters.put(parameter, value);
    }
  }

  /**
   * Adds the specified long parameter.
   *
   * @param parameter parameter to add values for
   * @param value     value of the parameter to add
   */
  public void addParameter(P parameter, long value) {
    parameters.put(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified int parameter.
   *
   * @param parameter parameter to add values for
   * @param value     value of the parameter to add
   */
  public void addParameter(P parameter, int value) {
    parameters.put(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified double parameter.
   *
   * @param parameter parameter to add values for
   * @param value     value of the parameter to add
   */
  public void addParameter(P parameter, double value) {
    parameters.put(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified boolean parameter.
   *
   * @param parameter parameter to add values for
   * @param value     value of the parameter to add
   */
  public void addParameter(P parameter, boolean value) {
    parameters.put(parameter, String.valueOf(value));
  }

  /**
   * Adds the specified parameter.
   *
   * @param parameter parameter to add values for
   * @param value     enum value of the parameter to add
   */
  public void addParameter(P parameter, Enum<?> value) {
    if (value != null) {
      parameters.put(parameter, value.name());
    }
  }


  /**
   * Adds the specified date parameter as an ISO date.
   *
   * @param parameter parameter to add date for
   * @param value     date value of the parameter to add
   */
  public void addParameter(P parameter, Date value) {
    if (value != null) {
      // not thread safe, new instance
      DateFormat iso = new SimpleDateFormat("yyyy-MM-dd");
      parameters.put(parameter, iso.format(value));
    }
  }


}
