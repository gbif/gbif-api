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
package org.gbif.api.util;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.occurrence.geo.DistanceUnit;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.valid.IsValidOp;
import org.locationtech.spatial4j.context.jts.DatelineRule;
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory;
import org.locationtech.spatial4j.exception.InvalidShapeException;
import org.locationtech.spatial4j.io.WKTReader;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.jts.JtsGeometry;

import static org.gbif.api.model.common.search.SearchConstants.QUERY_WILDCARD;
import static org.gbif.api.util.IsoDateParsingUtils.SIMPLE_ISO_DATE_STR_PATTERN;

/**
 * Utility class to do basic validation of all search enum based values.
 */
public class SearchTypeValidator {

  private static final Pattern BOOLEAN = Pattern.compile("^(true|false)$", Pattern.CASE_INSENSITIVE);
  // this regex matches a double with an optional dot separated fracture and negative signing
  private static final String DEC = "-?\\d+(?:\\.\\d+)?";

  private static final String DECIMAL_OR_WILDCARD = "(" + DEC + "|\\*)";

  private static final Range<Double> LATITUDE_RNG = Range.closed(-90.0, 90.0);
  private static final Range<Double> LONGITUDE_RNG = Range.closed(-180.0, 180.0);

  private static final String LATITUDE_ERROR_MSG = "%s is not valid value, latitude must be between -90 and 90.";

  private static final String LONGITUDE_ERROR_MSG = "%s is not valid value, longitude must be between -180 and 180.";

  private static final String WILD_CARD = "*";

  /**
   * Matches ranges in formats
   *
   * <pre>
   * 23.1,55.2
   * </pre>
   *
   * <pre>
   * *,88
   * </pre>
   *
   * and
   *
   * <pre>
   * 55,*
   * </pre>
   *
   * .
   * The matcher returns 2 groups:
   * group 1: lower bound
   * group 2: upper bound
   */
  private static final Pattern DECIMAL_RANGE_PATTERN = Pattern.compile(
    "^" + DECIMAL_OR_WILDCARD + "\\s*,\\s*" + DECIMAL_OR_WILDCARD + "$", Pattern.CASE_INSENSITIVE);

  public static final String SIMPLE_ISO_YEAR_MONTH_PATTERN = "\\d{4}(?:-\\d{1,2})?";

  private static final String DATE_OR_WILDCARD = "(" + SIMPLE_ISO_DATE_STR_PATTERN + "|\\*)";

  private static final Pattern DATE_RANGE_PATTERN = Pattern.compile(
    "^(" + DATE_OR_WILDCARD + "\\s*,\\s*" + DATE_OR_WILDCARD + "|" + SIMPLE_ISO_YEAR_MONTH_PATTERN + ")$", Pattern.CASE_INSENSITIVE);

  /**
   * Private default constructor.
   */
  private SearchTypeValidator() {
    // empty block
  }

  /**
   * Determines whether the value given is a valid numeric range, delimiting two values by a comma.
   *
   * @return true if the given value is a valid range
   */
  public static boolean isNumericRange(String value) {
    if (StringUtils.isNotEmpty(value)) {
      // decimal range for ints or doubles, or date range
      return DECIMAL_RANGE_PATTERN.matcher(value).find();
    }
    return false;
  }

  /**
   * Determines whether the value given is a valid date range or low precision (year, year-month) date, delimiting two values by a comma.
   *
   * @return true if the given value is a valid date range
   */
  public static boolean isDateRange(String value) {
    if (StringUtils.isNotEmpty(value)) {
      // date range
      return DATE_RANGE_PATTERN.matcher(value).find();
    }
    return false;
  }

  /**
   * Parses a range of ISO dates.
   * The date format used is the first date format that successfully parses the lower range limit.
   *
   * @return the parsed range with wildcards represented as null values
   * @throws IllegalArgumentException if value is invalid or null
   */
  public static Range<LocalDate> parseDateRange(String value) {
    return IsoDateParsingUtils.parseDateRange(value);
  }

  /**
   * Parses a decimal range in the format 123.1,456.
   *
   * @return the parsed range with wildcards represented as null values
   * @throws IllegalArgumentException if value is invalid or null
   */
  public static Range<Double> parseDecimalRange(String value) {
    if (StringUtils.isNotEmpty(value)) {
      Matcher m = DECIMAL_RANGE_PATTERN.matcher(value);
      if (m.find()) {
        return Range.closed(parseDouble(m.group(1)), parseDouble(m.group(2)));
      }
    }
    throw new IllegalArgumentException("Invalid decimal range: " + value);
  }

  /**
   * Parses an integer range in the format 123,456
   *
   * @return the parsed range with wildcards represented as null values
   * @throws IllegalArgumentException if value is invalid or null
   */
  public static Range<Integer> parseIntegerRange(String value) {
    if (StringUtils.isNotEmpty(value)) {
      Matcher m = DECIMAL_RANGE_PATTERN.matcher(value);
      if (m.find()) {

        return Range.closed(parseInteger(m.group(1)), parseInteger(m.group(2)));
      }
    }
    throw new IllegalArgumentException("Invalid integer range: " + value);
  }

  /**
   * Validates that a given parameter value matches the expected type of the parameter as defined by
   * {@link SearchParameter#type()} and throws an IllegalArgumentException otherwise.
   *
   * @param param the search parameter defining the expected type
   * @param value the parameter value to be validated
   * @throws IllegalArgumentException if the value cannot be converted to the expected type
   */
  public static void validate(SearchParameter param, String value) throws IllegalArgumentException {
    final Class<?> pType = param.type();

    try {
      if (OccurrenceSearchParameter.GEOMETRY == param) {
        validateGeometry(value);
      }

      if (OccurrenceSearchParameter.GEO_DISTANCE == param) {
        validateGeoDistance(value);
      }

      // All the parameters except by GEOMETRY accept the wild card value
      if (!WILD_CARD.equalsIgnoreCase(StringUtils.trimToEmpty(value))) {
        if (OccurrenceSearchParameter.DECIMAL_LATITUDE == param) {
          validateLatitude(value);

        } else if (OccurrenceSearchParameter.DECIMAL_LONGITUDE == param) {
          validateLongitude(value);

        } else if (UUID.class.isAssignableFrom(pType)) {
          //noinspection ResultOfMethodCallIgnored
          UUID.fromString(value);

        } else if (Double.class.isAssignableFrom(pType)) {
          validateDouble(value);

        } else if (Integer.class.isAssignableFrom(pType)) {
          Collection<Integer> intsFound = validateInteger(value);
          if (OccurrenceSearchParameter.MONTH == param) {
            validateMonth(intsFound);
          }

        } else if (Boolean.class.isAssignableFrom(pType)) {
          // we cannot use Boolean.parseBoolean as this accepted anything as false
          if (!BOOLEAN.matcher(value).find()) {
            throw new IllegalArgumentException("Value " + value + " is no valid boolean");
          }

        } else if (Country.class.isAssignableFrom(pType)) {
          // iso codes or enum name expected
          if (Country.fromIsoCode(value) == null
            && VocabularyUtils.lookupEnum(value, Country.class) == null) {
            throw new NullPointerException();
          }
        } else if (Language.class.isAssignableFrom(pType)) {
          // iso codes expected
          Objects.requireNonNull(Language.fromIsoCode(value));
        } else if (Enum.class.isAssignableFrom(pType)) {
          // enum value expected, cast to enum
          @SuppressWarnings("unchecked")
          Class<? extends Enum<?>> eType = (Class<? extends Enum<?>>) pType;
          Objects.requireNonNull(VocabularyUtils.lookupEnum(value, eType));

        } else if (Date.class.isAssignableFrom(pType) || Temporal.class.isAssignableFrom(pType)) {
          // ISO date strings
          validateDate(value);

        } else if (IsoDateInterval.class.isAssignableFrom(pType)) {
          // ISO date strings
          validateDate(value);

        } else if (!String.class.isAssignableFrom(pType)) {
          // any string allowed
          // an unexpected data type - update this method!!
          throw new IllegalArgumentException("Unknown SearchParameter data type " + pType.getCanonicalName());
        }
      }
    } catch (NullPointerException e) {
      // Objects.requireNonNull throws NPE but we want IllegalArgumentException
      throw new IllegalArgumentException("Value " + value + " invalid for filter parameter " + param, e);
    }
  }

  /**
   * @return the parsed double or null for wildcards
   * @throws NumberFormatException if invalid double
   */
  private static Double parseDouble(String d) {
    if (QUERY_WILDCARD.equals(d)) {
      return null;
    }
    return Double.parseDouble(d);
  }

  /**
   * @return the parsed integer or null for wildcards
   * @throws NumberFormatException if invalid integer
   */
  private static Integer parseInteger(String d) {
    if (QUERY_WILDCARD.equals(d)) {
      return null;
    }
    return Integer.parseInt(d);
  }

  /**
   * Validates if the string value is a valid ISO 8601 format.
   */
  private static void validateDate(String value) {
    if (isDateRange(value)) {
      IsoDateParsingUtils.parseDateRange(value);
    } else {
      IsoDateParsingUtils.parseDate(value);
    }
  }

  /**
   * Validates if the value is a valid single double or a range of double values.
   * If the value is a range each limit is validated and the wildcard character '*' is skipped.
   */
  private static void validateDouble(String value) {
    if (StringUtils.isEmpty(value)) {
      throw new IllegalArgumentException("Double cannot be null or empty");
    }
    try {
      Double.parseDouble(value);
    } catch (NumberFormatException e) {
      parseDecimalRange(value);
    }
  }

  /**
   * Validates if the value is a valid single double and its value is between a range.
   */
  private static void validateDoubleInRange(String value, Range<Double> range, String errorMsg) {
    if (StringUtils.isEmpty(value)) {
      throw new IllegalArgumentException("Double cannot be null or empty");
    }
    try {
      final Double doubleValue = Double.parseDouble(value);
      if (!range.contains(doubleValue)) {
        throw new IllegalArgumentException(String.format(errorMsg, value));
      }
    } catch (NumberFormatException e) {
      if (isNumericRange(value)) {
        Range<Double> rangeValue = parseDecimalRange(value);
        if (!range.encloses(rangeValue)) {
          throw new IllegalArgumentException(String.format(errorMsg, value));
        }
      } else {
        throw new IllegalArgumentException("Argument is not a valid number");
      }
    }
  }

  /**
   * Verify that we have indeed a wellKnownText parameter.
   * See <a href="https://en.wikipedia.org/wiki/Well-known_text">Wikipedia</a> for basic WKT specs.
   * The validation implemented does both syntactic and topological validation (for polygons only).
   */
  private static void validateGeometry(String wellKnownText) {
    JtsSpatialContextFactory spatialContextFactory = new JtsSpatialContextFactory();
    spatialContextFactory.normWrapLongitude = true;
    spatialContextFactory.srid = 4326;
    spatialContextFactory.datelineRule = DatelineRule.ccwRect;

    WKTReader reader = new WKTReader(spatialContextFactory.newSpatialContext(), spatialContextFactory);

    try {
      // This validates some errors, such as a latitude > 90°
      Shape shape = reader.parse(wellKnownText);

      if (shape instanceof JtsGeometry) {
        Geometry geometry = ((JtsGeometry) shape).getGeom();

        IsValidOp validator = new IsValidOp(geometry);

        if (!validator.isValid()) {
          throw new IllegalArgumentException("Invalid geometry: " + validator.getValidationError());
        }

        if (geometry.isEmpty()) {
          throw new IllegalArgumentException("Empty geometry: " + wellKnownText);
        }

        // Validate a polygon — check the winding order
        if (geometry instanceof Polygon) {
          validatePolygon((Polygon) geometry, wellKnownText);
        }

        // Validate polygons within a multipolygon
        if (geometry instanceof MultiPolygon) {
          MultiPolygon multiPolygon = (MultiPolygon) geometry;
          for (int p = 0; p < multiPolygon.getNumGeometries(); p++) {
            validatePolygon((Polygon) multiPolygon.getGeometryN(p), wellKnownText);
          }
        }

        switch (geometry.getGeometryType().toUpperCase()) {
          case "POINT":
          case "LINESTRING":
          case "POLYGON":
          case "MULTIPOLYGON":
            return;

          case "MULTIPOINT":
          case "MULTILINESTRING":
          case "GEOMETRYCOLLECTION":
          default:
            throw new IllegalArgumentException("Unsupported simple WKT (unsupported type " + geometry.getGeometryType() + "): " + wellKnownText);
        }
      }
    } catch (AssertionError | ParseException e) {
      throw new IllegalArgumentException("Cannot parse simple WKT: " + wellKnownText + " " + e.getMessage());
    } catch (InvalidShapeException e) {
      throw new IllegalArgumentException("Invalid shape in WKT: " + wellKnownText + " " + e.getMessage());
    }
  }

  private static void validatePolygon(Polygon polygon, String wellKnownText) {
    // Calculating the area > 0 ensures that polygons that are representing lines or points are invalidated
    if (polygon.getArea() == 0.0) {
      throw new IllegalArgumentException("Polygon with zero area: " + polygon.toText());
    }

    // Exterior ring must be anticlockwise
    boolean isCCW = Orientation.isCCW(polygon.getExteriorRing().getCoordinates());
    if (!isCCW) {
      String reversedText = polygon.getExteriorRing().reverse().toText();
      throw new IllegalArgumentException("Polygon with clockwise exterior ring: " + wellKnownText +
        ". Did you mean these coordinates?  (Note this is only part of the polygon or multipolygon you provided.) " +
        reversedText);
    }

    // Interior rings must be clockwise
    for (int r = 0; r < polygon.getNumInteriorRing(); r++) {
      isCCW = Orientation.isCCW(polygon.getInteriorRingN(r).getCoordinates());
      if (isCCW) {
        String reversedText = polygon.getInteriorRingN(r).reverse().toText();
        throw new IllegalArgumentException("Polygon with anticlockwise interior ring: " + wellKnownText +
          ". Did you mean these coordinates? (Note this is only part of the polygon or multipolygon you provided.) " +
          reversedText);
      }
    }
  }

  /**
   * Validates if the value is a valid single integer or a range of integer values.
   * If the value is a range each limit is validated and the wildcard character '*' is skipped.
   *
   * @throws IllegalArgumentException if value is invalid or null
   */
  private static Collection<Integer> validateInteger(String value) {
    if (StringUtils.isEmpty(value)) {
      throw new IllegalArgumentException("Integer cannot be null or empty");
    }
    try {
      List<Integer> list = new ArrayList<>();
      list.add(Integer.parseInt(value));
      return list;
    } catch (NumberFormatException e) {
      Range<Integer> range = parseIntegerRange(value);
      List<Integer> ints = new ArrayList<>();
      if (range.hasLowerBound()) {
        ints.add(range.lowerEndpoint());
      }
      if (range.hasUpperBound()) {
        ints.add(range.upperEndpoint());
      }
      return ints;
    }
  }

  private static void validateGeoDistance(String geoDistance) {
    if (StringUtils.isEmpty(geoDistance)) {
      throw new IllegalArgumentException("GeoDistance cannot be null or empty");
    }
    String[] geoDistanceTokens = geoDistance.split(",");
    if (geoDistanceTokens.length != 3) {
      throw new IllegalArgumentException("GeoDistance must follow the format lat,lng,distance");
    }
    validateGeoDistance(geoDistanceTokens[0], geoDistanceTokens[1], geoDistanceTokens[2]);
  }


  public static void validateGeoDistance(String latitude, String longitude, String distance) {
    validateLatitude(latitude);
    validateLongitude(longitude);
    DistanceUnit.Distance parsedDistance = DistanceUnit.parseDistance(distance);
    if (parsedDistance.getValue() <= 0d) {
      throw new IllegalArgumentException("GeoDistance cannot be less than zero");
    }
 }

  /**
   * Parses a distance range in the format 123m,456km.
   *
   * @return the parsed range with wildcards represented as null values
   * @throws IllegalArgumentException if value is invalid or null
   */
  public static Range<DistanceUnit.Distance> parseDistanceRange(String value) {
    if (StringUtils.isNotEmpty(value)) {
      Matcher m = DECIMAL_RANGE_PATTERN.matcher(value);
      if (m.find()) {
        return Range.closed(parseDistance(m.group(1)), parseDistance(m.group(2)));
      }
    }
    throw new IllegalArgumentException("Invalid distance range: " + value);
  }

  public static DistanceUnit.Distance parseDistance(String distance) {
    DistanceUnit.Distance parsedDistance = DistanceUnit.parseDistance(distance);
    if (parsedDistance.getValue() <= 0d) {
      throw new IllegalArgumentException("Distance cannot be less than zero");
    }
    return parsedDistance;
  }

  /**
   * Validates if the parameter is a valid latitude value.
   */
  private static void validateLatitude(String value) {
    validateDoubleInRange(value, LATITUDE_RNG, LATITUDE_ERROR_MSG);
  }

  /**
   * Validates if the parameter is a valid longitude value.
   */
  private static void validateLongitude(String value) {
    validateDoubleInRange(value, LONGITUDE_RNG, LONGITUDE_ERROR_MSG);
  }

  private static void validateMonth(Collection<Integer> months) {
    for (Integer month : months) {
      if (month != null && (month < 1 || month > 12)) {
        throw new IllegalArgumentException("Month needs to be between 1 - 12");
      }
    }
  }

}
