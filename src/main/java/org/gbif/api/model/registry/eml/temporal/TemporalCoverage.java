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
package org.gbif.api.model.registry.eml.temporal;
import org.gbif.api.model.registry.eml.Keywords;
import org.gbif.api.util.formatter.TemporalCoverageFormatterVisitor;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * The base of all types of temporal coverages.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "@type"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = DateRange.class, name = "range"),
  @JsonSubTypes.Type(value = SingleDate.class, name = "single"),
  @JsonSubTypes.Type(value = VerbatimTimePeriod.class, name = "verbatim")
})
public abstract class TemporalCoverage implements Keywords {

  /**
   * Accept a TemporalCoverageFormatterVisitor for formatting purpose.
   * The concrete class should simply returns formatter.format(this);
   *
   * @param formatter
   * @return
   */
  public abstract String acceptFormatter(TemporalCoverageFormatterVisitor formatter);

}
