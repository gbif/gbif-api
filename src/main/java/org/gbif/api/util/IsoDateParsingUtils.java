package org.gbif.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import com.google.common.base.Strings;
import com.google.common.collect.Range;

import static org.gbif.api.model.common.search.SearchConstants.QUERY_WILDCARD;
import static org.gbif.api.util.SearchTypeValidator.buildRange;

/**
 * Utility class that parses date values, the allowed date formats are: "yyyy-MM-dd", "yyyy-MM" or "yyyy".
 */
public class IsoDateParsingUtils {
  private static Pattern SIMPLE_ISO_PATTERN = Pattern.compile("\\d{4}(?:-\\d{2}(?:-\\d{2})?)?");
  /**
   * Enumerations with the allowed date formats by the occurrence search service.
   */
  public enum IsoDateFormat {
    FULL("yyyy-MM-dd"),
    YEAR_MONTH("yyyy-MM"),
    YEAR("yyyy");

    private final String datePattern;


    /**
     * Private constructors.
     * Receives a pattern and creates DateFormat instance with the pattern parameter.
     */
    private IsoDateFormat(String datePattern) {
      this.datePattern = datePattern;
    }

    /**
     * @return the dateFormat that parses and formats date values.
     */
    public DateFormat getDateFormat() {
      DateFormat dateFormat = new SimpleDateFormat(datePattern);
      dateFormat.setLenient(false);
      return dateFormat;
    }

    /**
     * Checks if the parameter "value" can be parsed using the date format.
     */
    public boolean isValidDate(String value) {
      try {
        return (parseDate(value) != null);
      } catch (ParseException e) {
        return false;
      }
    }

    /**
     * Try to parse a string with the current date format.
     */
    public Date parseDate(String value) throws ParseException {
      ParsePosition position = new ParsePosition(0);
      Date date = getDateFormat().parse(value, position);
      if (position.getIndex() != value.length()) {
        throw new ParseException(value + " is not a valid date", position.getIndex());
      }
      return date;
    }
  }

  /**
   * Default private constructor.
   */
  private IsoDateParsingUtils() {
    // empty block
  }


  /**
   * Iterates over all the OccurrenceDateFormat's and returns the first one that parses successfully the value.
   * @throws IllegalArgumentException in case of unparsable dates
   */
  public static IsoDateFormat getFirstDateFormatMatch(String value) throws IllegalArgumentException {
    // at least 4 digits for a year must exist
    if (SIMPLE_ISO_PATTERN.matcher(value).find()) {
      for (IsoDateFormat dateFormat : IsoDateFormat.values()) {
        if (dateFormat.isValidDate(value)) {
          return dateFormat;
        }
      }
    }
    throw new IllegalArgumentException(value + " is not a valid date");
  }

  /**
   * Parses a single date value. The date is interpreted using the first available date format that successfully parses
   * the value.
   * @return the lowest possible date according to the precision given or null in case of *
   * @throws IllegalArgumentException in case of unparsable dates
   */
  public static Date parseDate(String value) {
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalArgumentException("Date parameter can't be null or empty");
    }
    try {
      return getFirstDateFormatMatch(value).parseDate(value);

    } catch (ParseException e) {
      throw new IllegalArgumentException(String.format("{} is not a valid date parameter", value));

    } catch (IllegalArgumentException e) {
      // could be a wildcard
      if (QUERY_WILDCARD.equals(value)) {
        return null;
      }
      throw e;
    }
  }

  /**
   * Parses a range of dates. The date format used is the first date format that successfully parses the lower range
   * limit.
   */
  public static Range<Date> parseDateRange(String value) {
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalArgumentException("Date parameter can't be null or empty");
    }
    final String[] dateValues = value.split(",");
    if (dateValues.length != 2) {
      throw new IllegalArgumentException("Date value must be a single value or a range");
    }

    final Date lowerDate = parseDate(dateValues[0]);

    Date upperDate = parseDate(dateValues[1]);
    // in case we have a real upper date check its precision and use the highest possible date, not lowest
    if (upperDate != null) {
      final IsoDateFormat upperDateFormat = getFirstDateFormatMatch(dateValues[1]);
      if (upperDateFormat == IsoDateFormat.YEAR_MONTH) {
        upperDate = toLastDayOfMonth(upperDate);

      } else if (upperDateFormat == IsoDateFormat.YEAR) {
        upperDate = toLastDayOfYear(upperDate);
      }
    }

    return buildRange(lowerDate, upperDate);
  }

  /**
   * Calculates the last day of the month for the date parameter and return it a new date instance.
   */
  public static Date toLastDayOfMonth(Date value) {
    return toLastDayOf(value, Calendar.DAY_OF_MONTH);
  }

  /**
   * Calculates the last day of the year for the date parameter and return it a new date instance.
   */
  public static Date toLastDayOfYear(Date value) {
    return toLastDayOf(value, Calendar.DAY_OF_YEAR);
  }

  /**
   * Calculates the last day of a year or month for the date parameter and return it a new date instance.
   */
  public static Date toLastDayOf(Date value, IsoDateFormat isoDateFormat) {
    if (IsoDateFormat.YEAR_MONTH == isoDateFormat) {
      return toLastDayOf(value, Calendar.DAY_OF_MONTH);
    } else if(IsoDateFormat.YEAR == isoDateFormat){
      return toLastDayOf(value, Calendar.DAY_OF_YEAR);
    }
    return value;
  }

  /**
   * Gets the actual maximum value for a field of a date value.
   */
  private static Date toLastDayOf(Date value, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(value);
    calendar.set(field, calendar.getActualMaximum(field));
    return calendar.getTime();
  }

}
