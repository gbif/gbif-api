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

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import static org.gbif.api.model.common.search.SearchConstants.QUERY_WILDCARD;

/**
 * Utility class that parses date values, the allowed date formats are: "yyyy-MM-dd", "yyyy-MM" or "yyyy".
 *
 * Note: Does not handle times or time zones.  This is used for search queries/predicates with date ranges.
 * Date ranges are closed at the lower bound, and open at the upper bound, to allow less-than predicates
 * to match times on the upper bound date (i.e. after midnight).
 */
public class IsoDateParsingUtils {

  /**
   * A year, or a year-month, or a year-month-day, or a year-month-day-hour-minute, or a year-month-day-hour-minute-second,
   * or a year-month-day-hour-minute-second-fraction — all with an optional timezone.
   */
  private static final DateTimeFormatter DATE_TIME_PATTERN =
    DateTimeFormatter.ofPattern("[yyyy[-MM[-dd['T'HH:mm[:ss[.SSSSSSSSS][.SSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]]]]][XXXXX][XXXX][XXX][XX][X]]");

  public static final String SIMPLE_ISO_DATE_STR_PATTERN = "\\d{4}(?:-\\d{1,2}(?:-\\d{1,2})?)?";

  // match formats 'yyyy', 'yyyy-MM', 'yyyy-M', 'yyyy-MM-dd', 'yyyy-MM-d' and 'yyyy-M-d'.
  public static final Pattern SIMPLE_ISO_PATTERN = Pattern.compile(SIMPLE_ISO_DATE_STR_PATTERN);

  // Format as yyyy-MM-dd.
  public static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneOffset.UTC);

  /**
   * Enumerations with the allowed date formats by the occurrence search service.
   */
  public enum IsoDateFormat {
    YEAR_MONTH_DAY(new DateTimeFormatterBuilder()
      .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .appendLiteral('-')
      .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
      .appendLiteral('-')
      .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
      .toFormatter()),
    YEAR_MONTH(new DateTimeFormatterBuilder()
      .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .appendLiteral('-')
      .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
      .toFormatter()),
    YEAR(new DateTimeFormatterBuilder()
      .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .toFormatter());

    private final DateTimeFormatter dateFormatter;

    /**
     * Private constructors.
     * Receives a pattern and creates a strict DateTimeFormatter.
     */
    IsoDateFormat(DateTimeFormatter dateFormatter) {
      this.dateFormatter = dateFormatter
        .withZone(ZoneOffset.UTC)
        .withResolverStyle(ResolverStyle.STRICT)
        .withChronology(IsoChronology.INSTANCE);
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
    public TemporalAccessor parseDate(String value) throws ParseException {
      if (QUERY_WILDCARD.equals(value)) {
        return null;
      }

      try {
        switch (this) {
          case YEAR_MONTH_DAY:
            return dateFormatter.parse(value, LocalDate::from);

          case YEAR_MONTH:
            return dateFormatter.parse(value, YearMonth::from);

          case YEAR:
            return dateFormatter.parse(value, Year::from);
        }
      } catch (DateTimeException e) {
        throw new ParseException(value + " is not a valid date", 0);
      }
      throw new ParseException(value + " is not a valid date", 0);
    }

    /**
     * Returns the earliest date of a possible closed range, e.g. 2000-01-01 for 2000.
     */
    public LocalDate earliestDate(String value) throws ParseException {
      if (QUERY_WILDCARD.equals(value)) {
        return null;
      }

      TemporalAccessor ta = parseDate(value);
      switch (this) {
        case YEAR_MONTH_DAY:
          return LocalDate.from(ta);

        case YEAR_MONTH:
          YearMonth yearMonth = YearMonth.from(parseDate(value));
          return yearMonth.atDay(1);

        case YEAR:
          Year year = Year.from(parseDate(value));
          return year.atDay(1);
      }

      throw new ParseException(value + " is not a valid date", 0);
    }

    /**
     * Returns the latest date of a possible open range, e.g. 2001-01-01 for 2000.
     */
    public LocalDate latestDate(String value) throws ParseException {
      if (QUERY_WILDCARD.equals(value)) {
        return null;
      }

      TemporalAccessor ta = parseDate(value);
      switch (this) {
        case YEAR_MONTH_DAY:
          return LocalDate.from(ta).plusDays(1);

        case YEAR_MONTH:
          YearMonth yearMonth = YearMonth.from(parseDate(value));
          return yearMonth.atEndOfMonth().plusDays(1);

        case YEAR:
          Year year = Year.from(parseDate(value));
          return year.atDay(year.isLeap() ? 366 : 365).plusDays(1);
      }

      throw new ParseException(value + " is not a valid date", 0);
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
    // 4 digits for a year must exist
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
   * Parses an ISO 8601-format date or date-time.
   */
  public static Temporal parseTemporal(String value) {
    if (QUERY_WILDCARD.equals(value)) {
      return null;
    }

    if (value == null || value.isEmpty()) {
      return null;
    }

    // parse string
    return (Temporal) DATE_TIME_PATTERN.parseBest(
      value,
      OffsetDateTime::from,
      LocalDateTime::from,
      LocalDate::from,
      YearMonth::from,
      Year::from);
  }

  /**
   * Parses a single date value. The date is interpreted using the first available date format that successfully parses
   * the value.
   * @return the lowest possible date according to the precision given or null in case of *
   * @throws IllegalArgumentException in case of unparsable dates
   */
  public static LocalDate parseDate(String value) {
    if (StringUtils.isEmpty(value)) {
      throw new IllegalArgumentException("Date parameter can't be null or empty");
    }

    // could be a wildcard
    if (QUERY_WILDCARD.equals(value)) {
      return null;
    }

    try {
      return getFirstDateFormatMatch(value).earliestDate(value);
    } catch (DateTimeParseException | ParseException e) {
      throw new IllegalArgumentException(String.format("%s is not a valid date parameter", value));
    }
  }

  /**
   * Parses a closed-open range of dates, using the most specific format for each side of the range.
   * Returns null for an unbounded side of the range (*).
   */
  public static Range<LocalDate> parseDateRange(String value) {
    if (StringUtils.isEmpty(value)) {
      throw new IllegalArgumentException("Date parameter can't be null or empty");
    }
    final String[] dateValues = value.split(",");

    if (dateValues.length == 1) {
      try {
        final LocalDate lowerDate = parseDate(dateValues[0]);
        final LocalDate upperDate = getFirstDateFormatMatch(dateValues[0]).latestDate(dateValues[0]);

        return Range.closed(lowerDate, upperDate);
      } catch (ParseException e) {
        throw new IllegalArgumentException(String.format("%s is not a valid date parameter", value));
      }
    }

    if (dateValues.length == 2) {
      try {
        final LocalDate lowerDate = parseDate(dateValues[0]);
        LocalDate upperDate = parseDate(dateValues[1]);

        if (upperDate != null) {
          upperDate = getFirstDateFormatMatch(dateValues[1]).latestDate(dateValues[1]);
        }

        return Range.closed(lowerDate, upperDate);
      } catch (ParseException e) {
        throw new IllegalArgumentException(String.format("%s is not a valid date parameter", value));
      }
    }

    throw new IllegalArgumentException("Date value must be a single value or a range");
  }

  public static TemporalAccessor stripOffsetOrZone(TemporalAccessor temporalAccessor, boolean ignoreOffset) {
    if (temporalAccessor == null) {
      return null;
    } else if (!ignoreOffset && temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS)) {
      return ((OffsetDateTime)temporalAccessor.query(OffsetDateTime::from)).atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    } else {
      return temporalAccessor.isSupported(ChronoField.SECOND_OF_DAY) ? (TemporalAccessor)temporalAccessor.query(LocalDateTime::from) : temporalAccessor;
    }
  }
}
