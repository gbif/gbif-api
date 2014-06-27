/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.occurrence.predicate;

import java.util.Collection;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This predicate is "OR"-ing its subpredicates together.
 */
public class DisjunctionPredicate extends CompoundPredicate {

  @JsonCreator
  public DisjunctionPredicate(@JsonProperty("predicates") Collection<Predicate> predicates) {
    super(predicates);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof DisjunctionPredicate)) {
      return false;
    }

    CompoundPredicate that = (CompoundPredicate) obj;
    return Objects.equal(this.getPredicates(), that.getPredicates());
  }
}
