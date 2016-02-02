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
