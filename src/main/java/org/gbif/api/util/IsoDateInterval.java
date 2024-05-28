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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

import jakarta.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Represents an ISO 8601:2019 date, date-time or date/date-time interval.</p>
 * <p>Valid serializations include 2023, 2023-08, 2023-08-29, 2023/2024, 2023-08/2023-09 and so on.</p>
 */
public class IsoDateInterval {

  /**
   * Lower bound.
   */
  @Schema(description = "The lower bound.")
  private Temporal from;

  /**
   * Upper bound.
   */
  @Schema(description = "The upper bound.")
  private Temporal to;

  /**
   * Create an empty DateRange.
   */
  public IsoDateInterval() {
  }

  /**
   * Create a range with bounds of {@code date}.
   */
  public IsoDateInterval(Temporal date) {
    this(date, null);
  }

  /**
   * Create a range with bounds {@code from} and {@code to}.
   *
   * from and to must have the same type.
   *
   * @throws IllegalArgumentException if {@code from} is greater than {@code to}
   */
  public IsoDateInterval(Temporal from, Temporal to) {
    if (from != null && to != null && getRangeDiff(from, to) < 0) {
      throw new IllegalArgumentException(String.format("Invalid range: (%s, %s)", from, to));
    }
    setFrom(from);
    setTo(to);
  }

  /** Compare dates and return difference between FROM and TO dates in milliseconds */
  private static long getRangeDiff(Temporal from, Temporal to) {
    TemporalUnit unit = null;
    if (from instanceof Year) {
      unit = ChronoUnit.YEARS;
    } else if (from instanceof YearMonth) {
      unit = ChronoUnit.MONTHS;
    } else if (from instanceof LocalDate) {
      unit = ChronoUnit.DAYS;
    } else if (from instanceof LocalDateTime
        || from instanceof OffsetDateTime
        || from instanceof ZonedDateTime) {
      unit = ChronoUnit.SECONDS;
    }
    return from.until(to, unit);
  }

  @Nullable
  public Temporal getFrom() {
    return from;
  }

  public void setFrom(Temporal from) {
    if (this.to != null && from != null) {
      if (!this.to.getClass().isAssignableFrom(from.getClass())) {
        throw new IllegalArgumentException("From and to dates must be of compatible types.");
      }
      if (getRangeDiff(from, to) < 0) {
        throw new IllegalArgumentException(String.format("Invalid range: (%s, %s)", from, to));
      }
    }
    this.from = from;
  }

  public void setFrom(String textFrom) {
    this.setFrom(IsoDateParsingUtils.parseTemporal(textFrom));
  }

  @Nullable
  public Temporal getTo() {
    return to;
  }

  public void setTo(Temporal to) {
    if (this.from != null && to != null) {
      if (!this.from.getClass().isAssignableFrom(to.getClass())) {
        throw new IllegalArgumentException("From and to dates must be of compatible types.");
      }
      if (getRangeDiff(from, to) < 0) {
        throw new IllegalArgumentException(String.format("Invalid range: (%s, %s)", from, to));
      }
    }
    this.to = to;
  }

  public void setTo(String textTo) {
    this.setTo(IsoDateParsingUtils.parseTemporal(textTo));
  }

  /**
   * Returns the date-time interval formatted as a single value where the from and to values are the same
   * (e.g. "2023"), or as unabbreviated date-times at the defined accuracy otherwise ("2023-08-29/2023-08-30" rather
   * than "2023-08-29/30").
   */
  @Override
  public String toString() {
    return toString(false);
  }

  /**
   * Returns the date-time interval formatted as a single value where the from and to values are the same
   * (e.g. "2023"), or as unabbreviated date-times at the defined accuracy otherwise ("2023-08-29/2023-08-30" rather
   * than "2023-08-29/30").
   *
   * Optionally ignore a non-UTC offset.
   */
  public String toString(boolean ignoreNonUTCOffset) {
    if (this.getFrom() == null) {
      return null;
    }

    StringBuilder s = new StringBuilder();
    if (ignoreNonUTCOffset) {
      s.append(IsoDateParsingUtils.stripOffsetOrZoneExceptUTC(this.getFrom(), true).toString());
    } else {
      s.append(this.getFrom().toString());
    }

    if (this.getTo() != null && !this.getFrom().equals(this.getTo())) {
      s.append('/');
      if (ignoreNonUTCOffset) {
        s.append(IsoDateParsingUtils.stripOffsetOrZoneExceptUTC(this.getTo(), true).toString());
      } else {
        s.append(this.getTo().toString());
      }
    }

    return s.toString();
  }

  /**
   * Parses a well-formatted IsoDateInterval from the text representation.
   */
  public static IsoDateInterval fromString(String text) throws ParseException {
    int index;
    if ((index = text.indexOf("/")) >= 0) {
      if (index + 1 < text.length()) {
        // Explicit range
        return new IsoDateInterval(IsoDateParsingUtils.parseTemporal(text.substring(0, index)), IsoDateParsingUtils.parseTemporal(text.substring(index + 1)));
      } else {
        throw new ParseException("Missing 'to' end of IsoDateInterval in " + text, index);
      }
    } else {
      return new IsoDateInterval(IsoDateParsingUtils.parseTemporal(text));
    }
  }

  /**
   * Parses a well-formatted IsoDateInterval from the text representation.
   */
  public static IsoDateInterval fromString(String textFrom, String textTo) throws ParseException {
    return new IsoDateInterval(IsoDateParsingUtils.parseTemporal(textFrom), IsoDateParsingUtils.parseTemporal(textTo));
  }
}
