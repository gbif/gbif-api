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
package org.gbif.api.model.occurrence.geo;

import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.StringUtils;

/**
 * The DistanceUnit enumerates several units for measuring distances. These units
 * provide methods for converting strings and methods to convert units among each
 * others.The default unit used within this project is <code>METERS</code>
 * which is defined by <code>DEFAULT</code>
 */
public enum DistanceUnit {
  INCH(0.0254, "in", "inch"),
  YARD(0.9144, "yd", "yards"),
  FEET(0.3048, "ft", "feet"),
  KILOMETERS(1000.0, "km", "kilometers"),
  NAUTICALMILES(1852.0, "NM", "nmi", "nauticalmiles"),
  MILLIMETERS(0.001, "mm", "millimeters"),
  CENTIMETERS(0.01, "cm", "centimeters"),

  // 'm' is a suffix of 'nmi' so it must follow 'nmi'
  MILES(1609.344, "mi", "miles"),

  // since 'm' is suffix of other unit
  // it must be the last entry of unit
  // names ending with 'm'. otherwise
  // parsing would fail
  METERS(1, "m", "meters");

  public static final DistanceUnit DEFAULT = METERS;

  private double meters;
  private final String[] names;

  DistanceUnit(double meters, String...names) {
    this.meters = meters;
    this.names = names;
  }

  public double getMeters() {
    return meters;
  }

  public String[] getNames() {
    return names;
  }

  /**
   * Convert a value to a distance string
   *
   * @param distance value to convert
   * @return String representation of the distance
   */
  public String toString(double distance) {
    return distance + toString();
  }

  @Override
  public String toString() {
    return names[0];
  }

  /**
   * Parse a {@link Distance} from a given String
   *
   * @param distance String defining a {@link Distance}
   * @return parsed {@link Distance}
   */
  public static Distance parseDistance(String distance) {
    for (DistanceUnit unit: DistanceUnit.values()) {
      for (String name : unit.getNames()) {
        if(distance.endsWith(name)) {
          return new Distance(Double.parseDouble(distance.substring(0, distance.length() - name.length())), unit);
        }
      }
    }
    return new Distance(Double.parseDouble(distance), DEFAULT);
  }

  /**
   * Converts the given distance from the given DistanceUnit, to the given DistanceUnit
   *
   * @param distance Distance to convert
   * @param from     Unit to convert the distance from
   * @param to       Unit of distance to convert to
   * @return Given distance converted to the distance in the given unit
   */
  public static double convert(double distance, DistanceUnit from, DistanceUnit to) {
    if (from == to) {
      return distance;
    } else {
      return distance * from.meters / to.meters;
    }
  }

  public static class Distance implements Comparable<Distance> {
    public final double value;
    public final DistanceUnit unit;

    @JsonCreator
    public Distance(@JsonProperty("value") double value,
                    @JsonProperty("unit") DistanceUnit unit) {
      this.value = value;
      this.unit = unit;
    }

    public double getValue() {
      return value;
    }

    public DistanceUnit getUnit() {
      return unit;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj == null) {
        return false;
      } else if (obj instanceof Distance) {
        Distance other = (Distance) obj;
        return DistanceUnit.convert(value, unit, other.unit) == other.value;
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Double.valueOf(value * unit.meters).hashCode();
    }

    @Override
    public int compareTo(Distance o) {
      return Double.compare(value, DistanceUnit.convert(o.value, o.unit, unit));
    }

    @Override
    public String toString() {
      return unit.toString(value);
    }
  }

  public static class GeoDistance {

    private final double latitude;

    private final double longitude;

    private final Distance distance;

    @JsonCreator
    public GeoDistance(@JsonProperty("latitude") double latitude,
                       @JsonProperty("longitude") double longitude,
                       @JsonProperty("distance") Distance distance) {
      this.latitude = latitude;
      this.longitude = longitude;
      this.distance = distance;
    }

    public double getLatitude() {
      return latitude;
    }

    public double getLongitude() {
      return longitude;
    }

    public Distance getDistance() {
      return distance;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GeoDistance that = (GeoDistance) o;
      return Double.compare(that.latitude, latitude) == 0
             && Double.compare(that.longitude, longitude) == 0
             && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
      return Objects.hash(latitude, longitude, distance);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", GeoDistance.class.getSimpleName() + "[", "]")
        .add("latitude=" + latitude)
        .add("longitude=" + longitude)
        .add("distance=" + distance)
        .toString();
    }

    public static GeoDistance parseGeoDistance(String geoDistance) {
      if (StringUtils.isEmpty(geoDistance)) {
        throw new IllegalArgumentException("GeoDistance cannot be null or empty");
      }
      String[] geoDistanceTokens = geoDistance.split(",");
      if (geoDistanceTokens.length != 3) {
        throw new IllegalArgumentException("GeoDistance must follow the format lat,lng,distance");
      }
      return parseGeoDistance(geoDistanceTokens[0].trim(), geoDistanceTokens[1].trim(), geoDistanceTokens[2].trim());
    }

    public String toGeoDistanceString() {
      return new StringJoiner(", ")
        .add(Double.toString(latitude))
        .add(Double.toString(longitude))
        .add(distance.toString())
        .toString();
    }

    public static GeoDistance parseGeoDistance(String latitude, String longitude, String distance) {
      return new GeoDistance(Double.parseDouble(latitude),
                             Double.parseDouble(longitude),
                             DistanceUnit.parseDistance(distance));
    }
  }
}
