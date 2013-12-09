package org.gbif.api.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test case for class OccurrenceDateParsingUtils.
 */
public class IsoDateParsingUtilsTest {

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   * Upper limit has an invalid date format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongDateRangeFormatTest() {
    IsoDateParsingUtils.parseDateRange("2010-01,2000-10-33 00:00:00");
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   * Lower limit > upper limit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongDateRangeTest() {
    IsoDateParsingUtils.parseDateRange("2010-01,2000-10");
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongDateTest() {
    Date d = IsoDateParsingUtils.parseDate("2010-01-01-10");
    IsoDateParsingUtils.parseDate("2010-01-01-10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void isParseNonExistingDateTest() {
    IsoDateParsingUtils.parseDate("2010-21-01");
  }

  @Test(expected = IllegalArgumentException.class)
  public void isParseNonExistingDateTest2() {
    IsoDateParsingUtils.parseDate("2010-02-29");
  }

  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongDateWithTrailingStringTest() {
    IsoDateParsingUtils.parseDate("2010 year");
  }

  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongYearTest() {
    IsoDateParsingUtils.parseDate("10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongYearTest2() {
    IsoDateParsingUtils.parseDate("10-10");
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isParseWrongDateTimTest() {
    IsoDateParsingUtils.parseDate("2010-01-01 22:00:00");
  }

  /**
   * Test case for method {@link IsoDateParsingUtils#parseDateRange(String)}.
   */
  @Test
  public void parseDateRangeTest() {
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("2000-01,2010"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("1900-09-23,2000"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("1999-10,1999-12"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("1999-10,*"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("*,1999-12"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDateRange("2000-10,*"));
  }


  /**
   * Test case for method {@link IsoDateParsingUtils#parseDate(String)}.
   */
  @Test
  public void parseDateTest() {
    Assert.assertNotNull(IsoDateParsingUtils.parseDate("2000-01"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDate("2000"));
    Assert.assertNotNull(IsoDateParsingUtils.parseDate("2000-01-01"));
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
