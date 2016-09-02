package org.gbif.api.util;

import org.gbif.api.model.common.search.SearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import static org.gbif.api.model.common.search.SearchConstants.QUERY_WILDCARD;

/**
 * Utility class to do basic validation of all search enum based values.
 */
public class SearchTypeValidator {

  private static final Pattern BOOLEAN = Pattern.compile("^(true|false)$", Pattern.CASE_INSENSITIVE);
  // this regex matches a double with an optional dot separated fracture and negative signing
  private static final String DEC = "-?\\d+(?:\\.\\d+)?";
  private static final String DECIMAL = "(" + DEC + ")";

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
   * Matches without groups a space delimited coordinate pair allowing prefixing or trailing whitespace: 10.12 -90.12
   */
  private static final String WKT_COORD = "\\s*" + DECIMAL + "\\s+" + DECIMAL + "\\s*";

  /**
   * Matches without groups a comma separated list of coordinates enclosed in brackets:
   * (10.12 -90.12, 30 10, 10 20, 20 40)
   */
  private static final String WKT_LINE = "\\s*\\(" + WKT_COORD + "(?:," + WKT_COORD + ")*\\)\\s*";

  private static final String WKT_POLYGON = "\\(" + WKT_LINE + "(?:," + WKT_LINE + ")*\\)";

  private static final List<Pattern> WKT_PATTERNS = ImmutableList.of(
    Pattern.compile("^POINT\\s*\\(" + WKT_COORD + "\\)$", Pattern.CASE_INSENSITIVE),
    Pattern.compile("^(?:LINESTRING|LINEARRING)\\s*" + WKT_LINE + "$", Pattern.CASE_INSENSITIVE),
    Pattern.compile("^POLYGON\\s*" + WKT_POLYGON + "$", Pattern.CASE_INSENSITIVE),
    Pattern.compile("^MULTIPOLYGON\\s*\\(" + WKT_POLYGON + "(?:," + WKT_POLYGON + ")*\\)$", Pattern.CASE_INSENSITIVE));

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

        } else if (Date.class.isAssignableFrom(pType)) {
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
   * See <a href="http://en.wikipedia.org/wiki/Well-known_text">wikipedia</a> for basic WKT specs.
   * The validation implemented does both syntactic and topological validation (for polygons only): only convex polygons
   * are accepted.
   */
  private static void validateGeometry(String wellKnownText) {
    validateGeometrySyntax(wellKnownText);
    try {
      Geometry geometry = new WKTReader().read(wellKnownText);
      // Calculating the area > 0 ensures that polygons that are representing lines or points are invalidated
      if (geometry instanceof Polygon && (!geometry.isValid() || geometry.getArea() == 0.0)) {
        throw new IllegalArgumentException("Invalid polygon " + wellKnownText);
      }
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid simple WKT: " + wellKnownText);
    }
  }

  /**
   * Verify that we have indeed a wellKnownText parameter.
   * See <a href="http://en.wikipedia.org/wiki/Well-known_text">wikipedia</a> for basic WKT specs.
   * The validation implemented does a basic syntax check for the following geometries, but does not
   * verify that the resulting geometries are topologically valid (see the OGC SFS specification).
   */
  private static void validateGeometrySyntax(String wellKnownText) {
    if (Strings.isNullOrEmpty(wellKnownText)) {
      throw new IllegalArgumentException("Well Known Text cannot be empty or null");
    }
    // test all 4 supported geometry types with their respective regex - one must match!
    for (Pattern regex : WKT_PATTERNS) {
      if (regex.matcher(wellKnownText).find()) {
        return;
      }
    }
    throw new IllegalArgumentException("Invalid simple WKT: " + wellKnownText);
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
