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
package org.gbif.api.model.event.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.search.PredicateSearchRequest;
import org.gbif.api.model.predicate.Predicate;

/** Search request that uses a predicate filter like the ones used un downloads. */
@JsonDeserialize(as = EventPredicateSearchRequest.class)
public class EventPredicateSearchRequest extends EventSearchRequest
    implements PredicateSearchRequest {

  private Predicate predicate;

  public EventPredicateSearchRequest() {}

  public EventPredicateSearchRequest(Pageable page) {
    super(page);
  }

  @Override
  public Predicate getPredicate() {
    return predicate;
  }

  @Override
  public void setPredicate(Predicate predicate) {
    this.predicate = predicate;
  }
}
