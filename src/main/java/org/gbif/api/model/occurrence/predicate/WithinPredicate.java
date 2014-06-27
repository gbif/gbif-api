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

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This predicate checks if an occurrence location falls within the given WKT geometry {@code value}.
 */
public class WithinPredicate implements Predicate {

  @NotNull
  private final String geometry;

  /**
   * Builds a new within predicate for a single, simple geometry as
   * <a href="http://en.wikipedia.org/wiki/Well-known_text">Well Known Text</a> (WKT).
   * Multi geometries like MULTIPOLYGON are not supported and multiple predicates should be used instead.
   * <br/>
   * The validation implemented does a basic syntax check for the following simple geometries, but does not
   * verify that the resulting geometries are topologically valid (see the OGC SFS specification).
   * <ul>
   *   <li>POINT</li>
   *   <li>LINESTRING</li>
   *   <li>POLYGON</li>
   *   <li>LINEARRING</li>
   * </ul>
   * @param geometry
   */
  @JsonCreator
  public WithinPredicate(@JsonProperty("geometry") String geometry) {
    Preconditions.checkNotNull(geometry, "<geometry> may not be null");
    // make sure its a valid WKT
    SearchTypeValidator.validate(OccurrenceSearchParameter.GEOMETRY, geometry);
    this.geometry = geometry;
  }

  public String getGeometry() {
    return geometry;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof WithinPredicate)) {
      return false;
    }

    WithinPredicate that = (WithinPredicate) obj;
    return Objects.equal(this.geometry, that.geometry);
  }

  @Override
   public int hashCode() {
     return Objects.hashCode(geometry);
   }

   @Override
   public String toString() {
     return Objects.toStringHelper(this).add("geometry", geometry).toString();
   }

}
