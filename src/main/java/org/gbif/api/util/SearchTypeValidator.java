package org.gbif.api.util;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.valid.IsValidOp;
import org.locationtech.spatial4j.context.jts.DatelineRule;
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory;
import org.locationtech.spatial4j.exception.InvalidShapeException;
import org.locationtech.spatial4j.io.WKTReader;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.jts.JtsGeometry;

import static org.gbif.api.model.common.search.SearchConstants.QUERY_WILDCARD;

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

  /**
   * Private default constructor.
   */
  private SearchTypeValidator() {
    // empty block
  }

  /**
   * Builds a closed range from two given values, but accepts nulls which are converted into unbound ranges
   * in the guava instance.
   */
  public static <T extends Comparable<?>> Range<T> buildRange(final T lower, final T upper) {
    if (lower == null && upper != null) {
      return Range.atMost(upper);

    } else if (lower != null && upper == null) {
      return Range.atLeast(lower);

    } else if (lower == null && upper == null) {
      return Range.<T>all();

    } else {
      return Range.closed(lower, upper);
    }
  }

  /**
   * Determines whether the value given is a valid decimal or date range, delimiting two values by a comma.
   *
   * @return true if the given value is a valid range
   */
  public static boolean isRange(String value) {
    if (!Strings.isNullOrEmpty(value)) {
      // decimal range for ints or doubles
      if (DECIMAL_RANGE_PATTERN.matcher(value).find()) {
        return true;
      }
      // check date range
      try {
        IsoDateParsingUtils.parseDateRange(value);
        return true;
      } catch (Exception e) {
        // aha, no range
      }
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
  public static Range<Date> parseDateRange(String value) {
    return IsoDateParsingUtils.parseDateRange(value);
  }

  /**
   * Parses a decimal range in the format 123.1,456.
   *
   * @return the parsed range with wildcards represented as null values
   * @throws IllegalArgumentException if value is invalid or null
   */
  public static Range<Double> parseDecimalRange(String value) {
    if (!Strings.isNullOrEmpty(value)) {
      Matcher m = DECIMAL_RANGE_PATTERN.matcher(value);
      if (m.find()) {
        return buildRange(parseDouble(m.group(1)), parseDouble(m.group(2)));
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
    if (!Strings.isNullOrEmpty(value)) {
      Matcher m = DECIMAL_RANGE_PATTERN.matcher(value);
      if (m.find()) {

        return buildRange(parseInteger(m.group(1)), parseInteger(m.group(2)));
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
      // All the parameters except by GEOMETRY accept the wild card value
      if (!WILD_CARD.equalsIgnoreCase(Strings.nullToEmpty(value).trim())) {
        if (OccurrenceSearchParameter.DECIMAL_LATITUDE == param) {
          validateLatitude(value);

        } else if (OccurrenceSearchParameter.DECIMAL_LONGITUDE == param) {
          validateLongitude(value);

        } else if (UUID.class.isAssignableFrom(pType)) {
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
          // iso codes expected
          Preconditions.checkNotNull(Country.fromIsoCode(value));

        } else if (Language.class.isAssignableFrom(pType)) {
          // iso codes expected
          Preconditions.checkNotNull(Language.fromIsoCode(value));

        } else if (Enum.class.isAssignableFrom(pType)) {
          // enum value expected, cast to enum
          @SuppressWarnings("unchecked")
          Class<? extends Enum<?>> eType = (Class<? extends Enum<?>>) pType;
          Preconditions.checkNotNull(VocabularyUtils.lookupEnum(value, eType));

        } else if (Date.class.isAssignableFrom(pType) || Temporal.class.isAssignableFrom(pType)) {
          // ISO date strings
          validateDate(value);

        } else if (!String.class.isAssignableFrom(pType)) {
          // any string allowed
          // an unexpected data type - update this method!!
          throw new IllegalArgumentException("Unknown SearchParameter data type " + pType.getCanonicalName());
        }
      }
    } catch (NullPointerException e) {
      // Preconditions.checkNotNull throws NPE but we want IllegalArgumentException
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
    if (isRange(value)) {
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
    if (Strings.isNullOrEmpty(value)) {
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
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalArgumentException("Double cannot be null or empty");
    }
    try {
      final Double doubleValue = Double.parseDouble(value);
      if (!range.contains(doubleValue)) {
        throw new IllegalArgumentException(String.format(errorMsg, value));
      }
    } catch (NumberFormatException e) {
      if (isRange(value)) {
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

        // Calculating the area > 0 ensures that polygons that are representing lines or points are invalidated
        if (geometry instanceof Polygon && geometry.getArea() == 0.0) {
          throw new IllegalArgumentException("Polygon with zero area: " + wellKnownText);
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
    } catch (AssertionError e) {
      throw new IllegalArgumentException("Cannot parse simple WKT: " + wellKnownText + " " + e.getMessage());
    } catch (java.text.ParseException e) {
      throw new IllegalArgumentException("Cannot parse simple WKT: " + wellKnownText + " " + e.getMessage());
    } catch (InvalidShapeException e) {
      throw new IllegalArgumentException("Invalid shape in WKT: " + wellKnownText + " " + e.getMessage());
    }
  }

  /**
   * Validates if the value is a valid single integer or a range of integer values.
   * If the value is a range each limit is validated and the wildcard character '*' is skipped.
   *
   * @throws IllegalArgumentException if value is invalid or null
   */
  private static Collection<Integer> validateInteger(String value) {
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalArgumentException("Integer cannot be null or empty");
    }
    try {
      return Lists.newArrayList(Integer.parseInt(value));
    } catch (NumberFormatException e) {
      Range<Integer> range = parseIntegerRange(value);
      List<Integer> ints = Lists.newArrayList();
      if (range.hasLowerBound()) {
        ints.add(range.lowerEndpoint());
      }
      if (range.hasUpperBound()) {
        ints.add(range.upperEndpoint());
      }
      return ints;
    }
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
