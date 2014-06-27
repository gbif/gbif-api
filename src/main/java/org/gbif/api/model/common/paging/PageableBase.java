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

import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_LIMIT;
import static org.gbif.api.model.common.paging.PagingConstants.DEFAULT_PARAM_OFFSET;

/**
 * Generically is a class that contains attributes used by operations that are aware of pagination.
 * Its properties are final to allow calculations in other setter methods.
 */
public class PageableBase implements Pageable {

  protected long offset;
  protected int limit;

  /**
   * Default constructor with default paging values.
   */
  protected PageableBase() {
    offset = DEFAULT_PARAM_OFFSET;
    limit = DEFAULT_PARAM_LIMIT;
  }

  /**
   * Full constructor based on a request.
   *
   * @throws IllegalArgumentException if negative offset or limit.
   */
  protected PageableBase(Pageable page) {
    setOffset(page.getOffset());
    setLimit(page.getLimit());
  }

  /**
   * Full constructor.
   *
   * @throws IllegalArgumentException if negative offset or limit.
   */
  protected PageableBase(long offset, int limit) {
    setOffset(offset);
    setLimit(limit);
  }

  /**
   * Total of rows that are returned.
   */
  @Override
  public int getLimit() {
    return limit;
  }

  /**
   * @param limit the non negative limit to set
   *
   * @throws IllegalArgumentException if negative
   */
  public void setLimit(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("Limit cannot be negative");
    }
    this.limit = limit;
  }

  /**
   * Defines how many items to skip before beginning to return rows.
   */
  @Override
  public long getOffset() {
    return offset;
  }

  /**
   * @param offset the non negative offset to set
   *
   * @throws IllegalArgumentException if negative
   */
  public void setOffset(long offset) {
    if (offset < 0) {
      throw new IllegalArgumentException("Offset cannot be negative");
    }
    this.offset = offset;
  }

  /**
   * Adds to existing offset, setting offset to zero if it would be negative.
   *
   * @param offsetDiff to be added to existing offset.
   */
  public void addOffset(long offsetDiff) {
    offset += offsetDiff;
    if (offset < 0) {
      offset = 0;
    }
  }

  /**
   * Utility method to copy paging values.
   */
  public void copyPagingValues(Pageable pageable) {
    limit = pageable.getLimit();
    offset = pageable.getOffset();
  }

}
