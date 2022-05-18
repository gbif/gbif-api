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
package org.gbif.api.model.occurrence.predicate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  property = "type"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ConjunctionPredicate.class, name = "and"),
  @JsonSubTypes.Type(value = DisjunctionPredicate.class, name = "or"),
  @JsonSubTypes.Type(value = EqualsPredicate.class, name = "equals"),
  @JsonSubTypes.Type(value = LikePredicate.class, name = "like"),
  @JsonSubTypes.Type(value = LessThanPredicate.class, name = "lessThan"),
  @JsonSubTypes.Type(value = LessThanOrEqualsPredicate.class, name = "lessThanOrEquals"),
  @JsonSubTypes.Type(value = GreaterThanPredicate.class, name = "greaterThan"),
  @JsonSubTypes.Type(value = GreaterThanOrEqualsPredicate.class, name = "greaterThanOrEquals"),
  @JsonSubTypes.Type(value = InPredicate.class, name = "in"),
  @JsonSubTypes.Type(value = WithinPredicate.class, name = "within"),
  @JsonSubTypes.Type(value = GeoDistancePredicate.class, name = "geoDistance"),
  @JsonSubTypes.Type(value = NotPredicate.class, name = "not"),
  @JsonSubTypes.Type(value = IsNotNullPredicate.class, name = "isNotNull"),
  @JsonSubTypes.Type(value = IsNullPredicate.class, name = "isNull"),
  @JsonSubTypes.Type(value = FullTextSearchPredicate.class, name = "fullTextSearchPredicate")
})
public interface Predicate extends Serializable {
}
