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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.predicate.Predicate;

/** Search request that uses a predicate filter like the ones used un downloads.*/
@JsonDeserialize(as = OccurrencePredicateSearchRequest.class)
public class OccurrencePredicateSearchRequest extends OccurrenceSearchRequest {

  private Predicate predicate;

  public OccurrencePredicateSearchRequest() {
  }

  public OccurrencePredicateSearchRequest(Pageable page) {
    super(page);
  }

  public Predicate getPredicate() {
    return predicate;
  }

  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
  }
}
