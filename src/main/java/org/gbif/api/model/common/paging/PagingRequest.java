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

public class PagingRequest extends PageableBase {

  public PagingRequest() {
  }

  public PagingRequest(Pageable page) {
    super(page);
  }

  public PagingRequest(long offset, int limit) {
    super(offset, limit);
  }

  /**
   * Increases the offset based on the current limit to be able to request the next page.
   */
  public void nextPage() {
    addOffset(limit);
  }

}
