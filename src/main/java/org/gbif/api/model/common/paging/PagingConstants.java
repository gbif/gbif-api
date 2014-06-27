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

/**
 * Paging constants.
 */
public final class PagingConstants {

  /**
   * Offset param name.
   */
  public static final String PARAM_OFFSET = "offset";

  /**
   * Limit param name.
   */
  public static final String PARAM_LIMIT = "limit";

  /**
   * Default start index in a result list of elements.
   * Note that offset=0 means to start with first record.
   */
  public static final long DEFAULT_PARAM_OFFSET = 0L;

  /**
   * Default number of records to return.
   */
  public static final int DEFAULT_PARAM_LIMIT = 20;

  private PagingConstants() {
    throw new UnsupportedOperationException("Can't initialize class");
  }

}
