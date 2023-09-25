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
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test case for class IsoDateInterval.
 */
public class IsoDateIntervalTest {

  /**
   * Test case for constructors.
   */
  @Test
  public void constructorTest() {
    assertNotNull(new IsoDateInterval());
    assertNotNull(new IsoDateInterval(Year.of(1800)));
    assertNotNull(new IsoDateInterval(Year.of(1800), Year.of(1900)));
    assertNotNull(new IsoDateInterval(YearMonth.of(1800, 06)));
    assertNotNull(new IsoDateInterval(YearMonth.of(1800, 06), YearMonth.of(1900, 06)));
    assertNotNull(new IsoDateInterval(LocalDate.of(1800, 06, 12)));
    assertNotNull(new IsoDateInterval(LocalDate.of(1800, 06, 12), LocalDate.of(1900, 06, 12)));
    assertNotNull(new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15)));
    assertNotNull(new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15), LocalDateTime.of(1900, 06, 12, 13, 14, 15)));
    assertNotNull(new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3))));
    assertNotNull(new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3)), OffsetDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(-3))));
    assertNotNull(new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3))));
    assertNotNull(new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3)), ZonedDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(-3))));

    // Reversed range
    assertThrows(IllegalArgumentException.class, () -> new IsoDateInterval(Year.of(1900), Year.of(1800)));

    // Incompatible types
    assertThrows(IllegalArgumentException.class, () -> new IsoDateInterval(Year.of(1900), YearMonth.of(1901, 06)));

    final IsoDateInterval idi = new IsoDateInterval();
    idi.setFrom(Year.of(1800));
    assertThrows(IllegalArgumentException.class, () -> idi.setTo(YearMonth.of(1900, 06)));
    assertThrows(IllegalArgumentException.class, () -> idi.setTo(Year.of(1750)));

    final IsoDateInterval idi2 = new IsoDateInterval();
    idi2.setTo(Year.of(1800));
    assertThrows(IllegalArgumentException.class, () -> idi2.setFrom(YearMonth.of(1900, 06)));
    assertThrows(IllegalArgumentException.class, () -> idi2.setFrom(Year.of(1950)));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   */
  @Test
  public void checkParsing() throws ParseException {
    assertNull(new IsoDateInterval().toString());
    assertEquals("1800", IsoDateInterval.fromString("1800").toString());
    assertEquals("1800/1900", IsoDateInterval.fromString("1800/1900").toString());
    assertEquals("1800-06", IsoDateInterval.fromString("1800-06").toString());
    assertEquals("1800-06/1900-06", IsoDateInterval.fromString("1800-06/1900-06").toString());
    assertEquals("1800-06-12", IsoDateInterval.fromString("1800-06-12").toString());
    assertEquals("1800-06-12/1900-06-12", IsoDateInterval.fromString("1800-06-12/1900-06-12").toString());
    assertEquals("1800-06-12T13:14:15", IsoDateInterval.fromString("1800-06-12T13:14:15").toString());
    assertEquals("1800-06-12T13:14:15/1900-06-12T13:14:15", IsoDateInterval.fromString("1800-06-12T13:14:15/1900-06-12T13:14:15").toString());
    assertEquals("1800-06-12T13:14:15+03:00", IsoDateInterval.fromString("1800-06-12T13:14:15+03:00").toString());
    assertEquals("1800-06-12T13:14:15+03:00/1900-06-12T13:14:15-03:00", IsoDateInterval.fromString("1800-06-12T13:14:15+03:00/1900-06-12T13:14:15-03:00").toString());
    assertEquals("1800-06-12T13:14:15Z", IsoDateInterval.fromString("1800-06-12T13:14:15Z").toString());
    assertEquals("1800-06-12T13:14:15Z/1900-06-12T13:14:15Z", IsoDateInterval.fromString("1800-06-12T13:14:15Z/1900-06-12T13:14:15Z").toString());
  }

  @Test
  public void checkStrings() {
    assertNull(new IsoDateInterval().toString());
    assertEquals("1800", new IsoDateInterval(Year.of(1800)).toString());
    assertEquals("1800/1900", new IsoDateInterval(Year.of(1800), Year.of(1900)).toString());
    assertEquals("1800-06", new IsoDateInterval(YearMonth.of(1800, 06)).toString());
    assertEquals("1800-06/1900-06", new IsoDateInterval(YearMonth.of(1800, 06), YearMonth.of(1900, 06)).toString());
    assertEquals("1800-06-12", new IsoDateInterval(LocalDate.of(1800, 06, 12)).toString());
    assertEquals("1800-06-12/1900-06-12", new IsoDateInterval(LocalDate.of(1800, 06, 12), LocalDate.of(1900, 06, 12)).toString());
    assertEquals("1800-06-12T13:14:15", new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15)).toString());
    assertEquals("1800-06-12T13:14:15/1900-06-12T13:14:15", new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15), LocalDateTime.of(1900, 06, 12, 13, 14, 15)).toString());
    assertEquals("1800-06-12T13:14:15+03:00", new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3))).toString());
    assertEquals("1800-06-12T13:14:15+03:00/1900-06-12T13:14:15-03:00", new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3)), OffsetDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(-3))).toString());
    assertEquals("1800-06-12T13:14:15Z", new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC)).toString());
    assertEquals("1800-06-12T13:14:15Z/1900-06-12T13:14:15Z", new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC), ZonedDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC)).toString());

    // Stripped offset
    assertEquals("1800-06-12T13:14:15", new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15)).toString(true));
    assertEquals("1800-06-12T13:14:15/1900-06-12T13:14:15", new IsoDateInterval(LocalDateTime.of(1800, 06, 12, 13, 14, 15), LocalDateTime.of(1900, 06, 12, 13, 14, 15)).toString(true));
    assertEquals("1800-06-12T13:14:15", new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3))).toString(true));
    assertEquals("1800-06-12T13:14:15/1900-06-12T13:14:15", new IsoDateInterval(OffsetDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(3)), OffsetDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.ofHours(-3))).toString(true));
    assertEquals("1800-06-12T13:14:15", new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC)).toString(true));
    assertEquals("1800-06-12T13:14:15/1900-06-12T13:14:15", new IsoDateInterval(ZonedDateTime.of(1800, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC), ZonedDateTime.of(1900, 06, 12, 13, 14, 15, 0, ZoneOffset.UTC)).toString(true));
  }
}
