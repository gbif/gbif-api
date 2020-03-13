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
package org.gbif.api.util;

/**
 *
 */
public class LengthUtils {
  public static final int LAT_DEGREE_IN_METER = 110580;

  /**
   * Latitude degrees are roughly linear to meters on the earth spheroid.
   * Just the longitudinal degree changes considerably with distance to the equator
   * See https://en.wikipedia.org/wiki/Geographic_coordinate_system#Expressing_latitude_and_longitude_as_linear_units
   *
   * @return the decimal latitudinal degree equivalent to the given length in meters
   */
  public static double metersToLatDegree(double meter) {
    // http://gis.stackexchange.com/questions/14449/java-vividsolutions-jts-wgs-84-distance-to-meters
    return Math.round(meter/ LAT_DEGREE_IN_METER * 1000000.0) / 1000000.0;
  }

  /**
   * Latitude degrees are roughly linear to meters on the earth spheroid.
   * Just the longitudinal degree changes considerably with distance to the equator
   * See https://en.wikipedia.org/wiki/Geographic_coordinate_system#Expressing_latitude_and_longitude_as_linear_units
   *
   * @return the length in meters equivalent to the given decimal latitudinal degree
   */
  public static double latDegreeToMeters(double latDegrees) {
    // http://gis.stackexchange.com/questions/14449/java-vividsolutions-jts-wgs-84-distance-to-meters
    return Math.round(latDegrees * LAT_DEGREE_IN_METER * 100.0) / 100.0;
  }

}
