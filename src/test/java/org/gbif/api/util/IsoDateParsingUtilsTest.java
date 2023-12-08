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

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test case for class IsoDateParsingUtils.
 */
public class IsoDateParsingUtilsTest {

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   * Upper limit has an invalid date format.
   */
  @Test
  public void isParseWrongDateRangeFormatTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDateRange("2010-01,2000-10-33 00:00:00"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   * Lower limit > upper limit.
   */
  @Test
  public void isParseWrongDateRangeTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDateRange("2010-01,2000-10"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test
  public void isParseWrongDateTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("2010-01-01-10"));
  }

  @Test
  public void isParseNonExistingDateTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("2010-21-01"));
  }

  @Test
  public void isParseNonExistingDateTest2() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("2010-02-29"));
  }

  @Test
  public void isParseWrongDateWithTrailingStringTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("2010 year"));
  }

  @Test
  public void isParseWrongYearTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("10"));
  }

  @Test
  public void isParseWrongYearTest2() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("10-10"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test
  public void isParseWrongDateTimTest() {
    assertThrows(IllegalArgumentException.class, () -> IsoDateParsingUtils.parseDate("2010-01-01 22:00:00"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   */
  @Test
  public void parseDateRangeTest() {
    assertNotNull(IsoDateParsingUtils.parseDateRange("2000-01,2010"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("1900-09-23,2000"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("1999-10,1999-12"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("1999-10,*"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("*,1999-12"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("2000-10,*"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("2021"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("2021-10"));
    assertNotNull(IsoDateParsingUtils.parseDateRange("2021-10-25"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test
  public void parseDateTest() {
    assertNotNull(IsoDateParsingUtils.parseDate("2000-01"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000-01-01"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000-01-1"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000-1-01"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000-1-1"));
  }

  @Test
  public void checkResults() {
    assertEquals(LocalDate.parse("2021-10-25"), IsoDateParsingUtils.parseDate("2021-10-25"));

    assertEquals(LocalDate.parse("2021-10-01"), IsoDateParsingUtils.parseDateRange("2021-10").lowerEndpoint());
    assertEquals(LocalDate.parse("2021-11-01"), IsoDateParsingUtils.parseDateRange("2021-10").upperEndpoint());

    assertEquals(LocalDate.parse("2000-05-01"), IsoDateParsingUtils.parseDateRange("2000-05,2010").lowerEndpoint());
    assertEquals(LocalDate.parse("2011-01-01"), IsoDateParsingUtils.parseDateRange("2000-05,2010").upperEndpoint());
  }

  @Test
  public void testParseTemporal() throws IOException {
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_123_456, ZoneOffset.UTC), "2009-02-04T05:06:07.789123456Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_123_450, ZoneOffset.UTC), "2009-02-04T05:06:07.78912345Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_123_400, ZoneOffset.UTC), "2009-02-04T05:06:07.7891234Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_123_000, ZoneOffset.UTC), "2009-02-04T05:06:07.789123Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_120_000, ZoneOffset.UTC), "2009-02-04T05:06:07.78912Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_100_000, ZoneOffset.UTC), "2009-02-04T05:06:07.7891Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.UTC), "2009-02-04T05:06:07.789Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 780_000_000, ZoneOffset.UTC), "2009-02-04T05:06:07.78Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 700_000_000, ZoneOffset.UTC), "2009-02-04T05:06:07.7Z");

    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHoursMinutesSeconds(5, 30, 45)), "2009-02-04T05:06:07.789+05:30:45");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHoursMinutes(5, 30)), "2009-02-04T05:06:07.789+05:30:00");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07.789+05:00:00");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07.789+05:00");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHoursMinutes(5, 30)), "2009-02-04T05:06:07.789+0530");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07.789+05");

    assertParseTemporal(LocalDateTime.of(2009, 2, 4, 5, 6, 7, 789_000_000), "2009-02-04T05:06:07.789");

    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.UTC), "2009-02-04T05:06:07Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07+05:00");
    assertParseTemporal(LocalDateTime.of(2009, 2, 4, 5, 6, 7, 0), "2009-02-04T05:06:07");

    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.UTC), "2009-02-04T05:06Z");
    assertParseTemporal(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06+05:00");
    assertParseTemporal(LocalDateTime.of(2009, 2, 4, 5, 6), "2009-02-04T05:06");

    assertParseTemporal(LocalDate.of(2009, 2, 4), "2009-02-04");

    assertParseTemporal(YearMonth.of(2009, 2), "2009-02");

    assertParseTemporal(Year.of(2009), "2009");
  }

  private void assertParseTemporal(Temporal expected, String text) throws IOException {
    Temporal parsed = IsoDateParsingUtils.parseTemporal(text);
    assertEquals(expected, parsed);
  }
}
