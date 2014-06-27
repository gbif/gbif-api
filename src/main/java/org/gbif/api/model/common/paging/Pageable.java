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

import javax.validation.constraints.Min;

/**
 * Most simple paging interface for both request and responses.
 */
public interface Pageable {

  /**
   * Maximum number of records to be returned.
   *
   * @return the limit.
   */
  @Min(0)
  int getLimit();

  /**
   * Defines how many items to skip before beginning to return records.
   *
   * @return the offset with 0 being no offset at all.
   */
  @Min(0)
  long getOffset();

}
