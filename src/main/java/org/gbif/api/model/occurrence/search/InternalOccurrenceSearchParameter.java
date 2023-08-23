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
package org.gbif.api.model.occurrence.search;

import org.gbif.api.model.common.search.SearchParameter;

import java.util.Date;

/**
 * Query parameters used internally (not part of the public API) to support complex data types.
 */
public enum InternalOccurrenceSearchParameter implements SearchParameter {

  EVENT_DATE_GTE(Date.class),
  EVENT_DATE_LTE(Date.class);

  private final Class<?> type;

  InternalOccurrenceSearchParameter(Class<?> type) {
    this.type = type;
  }

  /**
   * @return the data type expected for the value of the respective search parameter
   */
  @Override
  public Class<?> type() {
    return type;
  }
}
