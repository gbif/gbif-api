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
package org.gbif.api.model.predicate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  property = "type"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ConjunctionPredicate.class, name = "and"),
  @JsonSubTypes.Type(value = DisjunctionPredicate.class, name = "or"),
  @JsonSubTypes.Type(value = NotPredicate.class, name = "not"),
  @JsonSubTypes.Type(value = EqualsPredicate.class, name = "equals"),
  @JsonSubTypes.Type(value = LikePredicate.class, name = "like"),
  @JsonSubTypes.Type(value = LessThanPredicate.class, name = "lessThan"),
  @JsonSubTypes.Type(value = LessThanOrEqualsPredicate.class, name = "lessThanOrEquals"),
  @JsonSubTypes.Type(value = GreaterThanPredicate.class, name = "greaterThan"),
  @JsonSubTypes.Type(value = GreaterThanOrEqualsPredicate.class, name = "greaterThanOrEquals"),
  @JsonSubTypes.Type(value = InPredicate.class, name = "in"),
  @JsonSubTypes.Type(value = RangePredicate.class, name = "range"),
  @JsonSubTypes.Type(value = WithinPredicate.class, name = "within"),
  @JsonSubTypes.Type(value = GeoDistancePredicate.class, name = "geoDistance"),
  @JsonSubTypes.Type(value = IsNullPredicate.class, name = "isNull"),
  @JsonSubTypes.Type(value = IsNotNullPredicate.class, name = "isNotNull"),
  @JsonSubTypes.Type(value = FullTextSearchPredicate.class, name = "fullTextSearch")
})
@Schema(
  description = "A predicate defining filters to apply for the download.",
  discriminatorProperty = "type",
  discriminatorMapping = {
    @DiscriminatorMapping(schema = ConjunctionPredicate.class, value = "and"),
    @DiscriminatorMapping(schema = DisjunctionPredicate.class, value = "or"),
    @DiscriminatorMapping(schema = NotPredicate.class, value = "not"),
    @DiscriminatorMapping(schema = EqualsPredicate.class, value = "equals"),
    @DiscriminatorMapping(schema = LikePredicate.class, value = "like"),
    @DiscriminatorMapping(schema = LessThanPredicate.class, value = "lessThan"),
    @DiscriminatorMapping(schema = LessThanOrEqualsPredicate.class, value = "lessThanOrEquals"),
    @DiscriminatorMapping(schema = GreaterThanPredicate.class, value = "greaterThan"),
    @DiscriminatorMapping(schema = GreaterThanOrEqualsPredicate.class, value = "greaterThanOrEquals"),
    @DiscriminatorMapping(schema = InPredicate.class, value = "in"),
    // Not yet announced for GBIF API use
    // @DiscriminatorMapping(schema = RangePredicate.class, value = "range"),
    @DiscriminatorMapping(schema = WithinPredicate.class, value = "within"),
    @DiscriminatorMapping(schema = GeoDistancePredicate.class, value = "geoDistance"),
    @DiscriminatorMapping(schema = IsNullPredicate.class, value = "isNull"),
    @DiscriminatorMapping(schema = IsNotNullPredicate.class, value = "isNotNull")
    // Not yet ready for use.
    // @DiscriminatorMapping(schema = FullTextSearchPredicate.class, value = "fullTextSearch")
  }
)
public interface Predicate extends Serializable {
}
