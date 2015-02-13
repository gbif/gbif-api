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
package org.gbif.api.model.common.paging;

import java.util.List;
import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Paging response bean.
 */
public class PagingResponse<T> extends PageableBase {

  private Boolean endOfRecords;
  private Long count;
  private List<T> results = Lists.newArrayList();

  /**
   * Default constructor with default paging values.
   */
  public PagingResponse() {
  }

  public PagingResponse(Pageable page) {
    super(page);
  }

  public PagingResponse(Pageable page, Long count) {
    super(page);
    this.count = count;
  }

  public PagingResponse(long offset, int limit) {
    super(offset, limit);
  }

  public PagingResponse(Pageable page, Long count, List<T> results) {
    super(page);
    this.results = results;
    this.count = count;
  }

  public PagingResponse(long offset, int limit, Long count) {
    super(offset, limit);
    this.count = count;
  }

  public PagingResponse(long offset, int limit, Long count, List<T> results) {
    super(offset, limit);
    this.results = results;
    this.count = count;
  }

  /**
   * Gets the count of total results of search operation.
   */
  @Nullable
  public Long getCount() {
    return count;
  }

  /**
   * Sets the total count for all results, not only the ones in this response page.
   * The method will not set endOfRecords automatically.
   */
  public void setCount(Long count) {
    this.count = count;
  }

  /**
   * Gets the list of results.
   * The type of element of the result are defined by the parameter class type T.
   *
   * @return the results list.
   */
  public List<T> getResults() {
    return results;
  }

  /**
   * Sets the list of results for the response.
   * This method will not modify the endOfRecords flag.
   */
  public void setResults(List<T> results) {
    Preconditions.checkNotNull(results, "results can't be null");
    this.results = results;
  }

  /**
   * Flag indicating whether more records do exist.
   * If the property has never been manually initialised, the flag is determined automatically.
   * If the total count is set it is used to determine the return value.
   * If only the result is given, we consider a result size equal to limit as an indication that there are (potentially
   * at least) more results.
   *
   * @return true if all records have been returned otherwise null.
   */
  public boolean isEndOfRecords() {
    if (endOfRecords == null) {
      // automatically determine end
      if (count != null) {
        return count <= offset + limit;
      } else if (results != null) {
        return results.size() < limit;
      } else {
        // what should this be if we dont have any content?
        return false;
      }
    }
    return endOfRecords;
  }

  /**
   * Manually sets the end of records flag.
   * Setting the flag to true or false deactivates the automatic calculation in #isEndOfRecords().
   */
  public void setEndOfRecords(boolean endOfRecords) {
    this.endOfRecords = endOfRecords;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof PagingResponse)) {
      return false;
    }

    PagingResponse<?> that = (PagingResponse<?>) obj;
    return Objects.equal(count, that.getCount())
           && Objects.equal(this.results, that.getResults())
           && Objects.equal(this.getOffset(), that.getOffset())
           && Objects.equal(this.getLimit(), that.getLimit());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(count, results, endOfRecords, getLimit(), getOffset());
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("count", count)
      .add("results", results)
      .add("offset", getOffset())
      .add("limit", getLimit())
      .toString();
  }

}
