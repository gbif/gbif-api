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

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test case for class OccurrenceDateParsingUtils.
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
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test
  public void parseDateTest() {
    assertNotNull(IsoDateParsingUtils.parseDate("2000-01"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000"));
    assertNotNull(IsoDateParsingUtils.parseDate("2000-01-01"));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#toLastDayOfMonth(Date)}.
   */
  @Test
  public void toLastDayOfMonthTest() {
    Date lowerDate = IsoDateParsingUtils.parseDate("2000-01-01");
    Calendar lastDayOfMonthCal = Calendar.getInstance();
    lastDayOfMonthCal.setTime(IsoDateParsingUtils.toLastDayOfMonth(lowerDate));
    assertEquals(2000, lastDayOfMonthCal.get(Calendar.YEAR));
    assertEquals(0, lastDayOfMonthCal.get(Calendar.MONTH)); // January is 0, months start at index 0
    assertEquals(31, lastDayOfMonthCal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#toLastDayOfYear(Date)}.
   */
  @Test
  public void toLastDayOfYearTest() {
    Date lowerDate = IsoDateParsingUtils.parseDate("2000-01-01");
    Calendar lastDayOfMonthCal = Calendar.getInstance();
    lastDayOfMonthCal.setTime(IsoDateParsingUtils.toLastDayOfYear(lowerDate));
    assertEquals(2000, lastDayOfMonthCal.get(Calendar.YEAR));
    assertEquals(11, lastDayOfMonthCal.get(Calendar.MONTH)); // December is 11, months start at index 0
    assertEquals(31, lastDayOfMonthCal.get(Calendar.DAY_OF_MONTH));
  }
}
