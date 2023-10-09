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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.gbif.api.model.occurrence.geo.DistanceUnit;
import org.gbif.api.util.SearchTypeValidator;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This predicate checks if an occurrence location falls within a distance of a location.
 */
@Schema(
  description = "This predicate checks if an occurrence falls within a distance of a location."
)
public class GeoDistancePredicate implements Predicate {

  @Hidden
  @NotNull
  private final DistanceUnit.GeoDistance geoDistance;

  @Schema(
    description = "The latitude of the location."
  )
  @NotNull
  private final String latitude;

  @Schema(
    description = "The longitude of the location."
  )
  @NotNull
  private final String longitude;

  @Schema(
    description = "The distance as a number and unit, e.g. `5km` or `5000m`."
  )
  @NotNull
  private final String distance;

  /**
   * Builds a new geodistance predicate that matches records within a given distance of a geopoint..
   *
   * @param latitude
   * @param longitude
   * @param distance
   */
  @JsonCreator
  public GeoDistancePredicate(@JsonProperty("latitude") String latitude,
                              @JsonProperty("longitude") String longitude,
                              @JsonProperty("distance") String distance) {
    Objects.requireNonNull(latitude, "<latitude> may not be null");
    Objects.requireNonNull(longitude, "<longitude> may not be null");
    Objects.requireNonNull(distance, "<distance> may not be null");
    this.latitude = latitude;
    this.longitude = longitude;
    this.distance = distance;
    // test if it is a valid GeoDistance
    SearchTypeValidator.validateGeoDistance(latitude, longitude, distance);

    this.geoDistance = DistanceUnit.GeoDistance.parseGeoDistance(latitude, longitude, distance);
  }

  public GeoDistancePredicate(@NotNull DistanceUnit.GeoDistance geoDistance) {
    this.geoDistance = geoDistance;

    this.latitude = Double.toString(geoDistance.getLatitude());
    this.longitude = Double.toString(geoDistance.getLongitude());
    this.distance = geoDistance.getDistance().toString();
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getDistance() {
    return distance;
  }

  @JsonIgnore
  public DistanceUnit.GeoDistance getGeoDistance() {
    return geoDistance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GeoDistancePredicate that = (GeoDistancePredicate) o;
    return Objects.equals(geoDistance, that.geoDistance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoDistance);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GeoDistancePredicate.class.getSimpleName() + "[", "]")
            .add("geoDistance=" + geoDistance).toString();
  }
}
