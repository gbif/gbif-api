/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.occurrence.predicate;

import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.util.SearchTypeValidator;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This predicate checks if an occurrence location falls within the given WKT geometry {@code value}.
 */
public class WithinPredicate implements Predicate {

  private static final Logger LOG = LoggerFactory.getLogger(WithinPredicate.class);

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
   * <strong>Unlike other predicates, this validation only logs in case of an invalid string.</strong>
   * This is because the WKT parser has been changed over time, and some old strings are not valid according
   * to the current parser.
   *
   * @param geometry
   */
  @JsonCreator
  public WithinPredicate(@JsonProperty("geometry") String geometry) {
    Objects.requireNonNull(geometry, "<geometry> may not be null");
    try {
      // test if it is a valid WKT
      SearchTypeValidator.validate(OccurrenceSearchParameter.GEOMETRY, geometry);
    } catch (IllegalArgumentException e) {
      // Log invalid strings, but continue - the geometry parser has changed over time, and some once-valid strings
      // are no longer considered valid.  See https://github.com/gbif/gbif-api/issues/48.
      LOG.warn("Invalid geometry string {}: {}", geometry, e.getMessage());
    }
    this.geometry = geometry;
  }

  public String getGeometry() {
    return geometry;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithinPredicate that = (WithinPredicate) o;
    return Objects.equals(geometry, that.geometry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geometry);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", WithinPredicate.class.getSimpleName() + "[", "]")
      .add("geometry='" + geometry + "'")
      .toString();
  }
}
