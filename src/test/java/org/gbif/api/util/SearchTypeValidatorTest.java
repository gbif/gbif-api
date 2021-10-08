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

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.gbif.api.model.checklistbank.search.NameUsageSearchParameter.IS_EXTINCT;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.COORDINATE_UNCERTAINTY_IN_METERS;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.COUNTRY;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.DATASET_KEY;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.DECIMAL_LATITUDE;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.DECIMAL_LONGITUDE;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.ELEVATION;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.EVENT_DATE;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.GEOMETRY;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.GEO_DISTANCE;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.LAST_INTERPRETED;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.MEDIA_TYPE;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.MONTH;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.PUBLISHING_COUNTRY;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.RECORDED_BY;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.SCIENTIFIC_NAME;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.TYPE_STATUS;
import static org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SearchTypeValidatorTest {

  public static Stream<Arguments> data() {
    return Stream.of(
      Arguments.of(LAST_INTERPRETED, "2000-10,*", true, true),
      Arguments.of(RECORDED_BY, "henry", true, false),
      Arguments.of(ELEVATION, "1080", true, false),
      Arguments.of(ELEVATION, "1080.32", true, false),
      Arguments.of(ELEVATION, "1080m", false, false),
      Arguments.of(ELEVATION, "*, 900", true, true),
      Arguments.of(ELEVATION, "100 , *", true, true),
      Arguments.of(ELEVATION, "-10.5,200.5", true, true),
      Arguments.of(ELEVATION, " , 200", false, false),
      Arguments.of(ELEVATION, "*1,200", false, false),
      Arguments.of(ELEVATION, "*.1,200", false, false),
      Arguments.of(ELEVATION, " , ", false, false),
      Arguments.of(ELEVATION, "[1 TO 2]", false, false),
      Arguments.of(ELEVATION, "{1,2}", false, false),
      Arguments.of(COORDINATE_UNCERTAINTY_IN_METERS, "0", true, false),
      Arguments.of(COORDINATE_UNCERTAINTY_IN_METERS, "100 , *", true, true),
      Arguments.of(DATASET_KEY, UUID.randomUUID().toString(), true, false),
      Arguments.of(DATASET_KEY, "f81d4fae-7dec-11d0-a765-00a0c91e6bf6", true, false),
      Arguments.of(DATASET_KEY, "F81D4FAE-7DEC-11D0-A765-00A0C91E6BF6", true, false),
      Arguments.of(DATASET_KEY, "F81D4FAE7DEC11D0A76500A0C91E6BF6", false, false),
      Arguments.of(IS_EXTINCT, "true", true, false),
      Arguments.of(IS_EXTINCT, "FALSE", true, false),
      Arguments.of(IS_EXTINCT, "True", true, false),
      Arguments.of(IS_EXTINCT, "1", false, false),
      Arguments.of(IS_EXTINCT, "0", false, false),
      Arguments.of(IS_EXTINCT, "10", false, false),
      Arguments.of(IS_EXTINCT, "ja", false, false),
      Arguments.of(IS_EXTINCT, "no", false, false),
      Arguments.of(SCIENTIFIC_NAME, "abies%", true, false),
      Arguments.of(GEOMETRY, "POINT (30 10)", true, false),
      Arguments.of(GEOMETRY, "POINT (30 10.12)", true, false),
      Arguments.of(GEOMETRY, "POINT (30 10.12)", true, false),
      Arguments.of(GEOMETRY, "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))", true, false),
      Arguments.of(GEOMETRY, "polygon ((30.12 10, 10 20, 20 40, 40 40, 30.12 10))", true, false),
      Arguments.of(GEOMETRY, "Polygon ((30.12 10, 10 20, 20 40, 40 40, 30.1200 10.00))", true, false),
      Arguments.of(GEOMETRY, "POLYGON ((30,12 10, 10 20, 20 40, 40 40, 30,12 10))", false, false),
      Arguments.of(GEOMETRY, "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10,))", false, false),
      Arguments.of(GEOMETRY, "POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10),)", false, false),
      Arguments.of(GEOMETRY, "POLYGON ((30.12 10, 10 20, 20 40, 40 40, 30.12 10.01))", false, false), // valid wkt, although not a closed polygon
      Arguments.of(GEOMETRY, "POLYGON  ( (30 10 , 10 20 , 20 40 , 40 40 , 30 20)  )", false, false), // valid wkt, although not a closed polygon
      Arguments.of(GEOMETRY, "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),(20 30, 35 35, 30 20, 20 30))", true, false), // valid donut
      Arguments.of(GEOMETRY, "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),(20 30, 35 35, 30 20, 20 30))", true, false),
      Arguments.of(GEOMETRY, "MULTIPOLYGON (((35 10, 10 20, 15 40, 45 45, 35 10)),((20 30, 35 35, 30 20, 20 30)))", false, false), // invalid, overlaps
      Arguments.of(GEOMETRY, "MULTIPOLYGON (((35 10, 10 20, 15 40, 45 45, 35 10)),((120 30, 135 35, 130 20, 120 30)))", true, false),
      Arguments.of(GEOMETRY, "MULTIPOLYGON(((-125 38.4, -121.8 38.4, -121.8 40.9, -125 40.9, -125 38.4)),((-115 22.4, -111.8 22.4, -111.8 30.9, -115 30.9, -115 22.4)))", true, false),
      Arguments.of(GEOMETRY, "LINESTRING (30 10, 10 30, 40 40)", true, false),
      Arguments.of(GEOMETRY, "LINESTRING (30 10, 10 30, 40 40,)", false, false),
      Arguments.of(YEAR, "1991", true, false),
      Arguments.of(YEAR, "1991-01-31", false, false),
      Arguments.of(YEAR, "860", true, false),
      Arguments.of(YEAR, "1860, 1911", true, true),
      Arguments.of(YEAR, "1", true, false),
      Arguments.of(YEAR, "-10", true, false),
      Arguments.of(YEAR, "3018", true, false),
      Arguments.of(MONTH, "1991", false, false),
      Arguments.of(MONTH, "00", false, false),
      Arguments.of(MONTH, "13", false, false),
      Arguments.of(MONTH, "10", true, false),
      Arguments.of(MONTH, "", false, false),
      Arguments.of(MONTH, "0", false, false),
      Arguments.of(MONTH, "1", true, false),
      Arguments.of(MONTH, "-11", false, false),
      Arguments.of(MONTH, "1267", false, false),
      Arguments.of(EVENT_DATE, "1900-06", true, false),
      Arguments.of(EVENT_DATE, "01-01", false, false),
      Arguments.of(EVENT_DATE, "1900-01-01", true, false),
      Arguments.of(EVENT_DATE, "1900-1-01", true, false),
      Arguments.of(EVENT_DATE, "1900-1-1", true, false),
      Arguments.of(EVENT_DATE, "10", false, false),
      Arguments.of(EVENT_DATE, "2000", true, false),
      Arguments.of(EVENT_DATE, "*", true, false),
      Arguments.of(EVENT_DATE, "*,2000", true, true),
      Arguments.of(EVENT_DATE, "2000-10,*", true, true),
      Arguments.of(SearchLocalDateTime.SEARCH_LOCAL_DATE_TIME, "2000-10,*", true, true),
      Arguments.of(LAST_INTERPRETED, "1900-06", true, false),
      Arguments.of(LAST_INTERPRETED, "01-01", false, false),
      Arguments.of(LAST_INTERPRETED, "1900-01-01", true, false),
      Arguments.of(LAST_INTERPRETED, "1900-1-01", true, false),
      Arguments.of(LAST_INTERPRETED, "1900-1-1", true, false),
      Arguments.of(LAST_INTERPRETED, "10", false, false),
      Arguments.of(LAST_INTERPRETED, "2000", true, false),
      Arguments.of(LAST_INTERPRETED, "*", true, false),
      Arguments.of(LAST_INTERPRETED, "*,2000", true, true),
      Arguments.of(LAST_INTERPRETED, "2000-10,*", true, true),
      Arguments.of(DECIMAL_LATITUDE, "90.0", true, false),
      Arguments.of(DECIMAL_LATITUDE, "180.0", false, false),
      Arguments.of(DECIMAL_LATITUDE, "50.0,92.2", false, true),
      Arguments.of(DECIMAL_LATITUDE, "50.5,89.9", true, true),
      Arguments.of(DECIMAL_LONGITUDE, "180.0", true, false),
      Arguments.of(DECIMAL_LONGITUDE, "180.01", false, false),
      Arguments.of(DECIMAL_LONGITUDE, "-190.0,92.2", false, true),
      Arguments.of(DECIMAL_LONGITUDE, "-150.5,119.9", true, true),
      Arguments.of(DECIMAL_LONGITUDE, "*", true, false),
      Arguments.of(COUNTRY, "CR", true, false),
      Arguments.of(PUBLISHING_COUNTRY, "CR", true, false),
      Arguments.of(COUNTRY, "CRCRCC", false, false),
      Arguments.of(PUBLISHING_COUNTRY, "CRCRCC", false, false),
      Arguments.of(TYPE_STATUS, "TYPE", true, false),
      Arguments.of(TYPE_STATUS, "*", true, false),
      Arguments.of(MEDIA_TYPE, "StillImage", true, false),
      Arguments.of(MEDIA_TYPE, "*", true, false),
      Arguments.of(GEO_DISTANCE, "40,90,100m", true, false),
      Arguments.of(GEO_DISTANCE, "200,90,100m", false, false),
      Arguments.of(GEO_DISTANCE, "200", false, false),
      Arguments.of(GEO_DISTANCE, "30,90,277", true, false)
    );
  }

  @ParameterizedTest
  @MethodSource("data")
  public void testValidateFine(SearchParameter parameter, String arg, boolean valid, boolean range) {
    try {
      SearchTypeValidator.validate(parameter, arg);
      if (!valid) {
        fail(arg + " supposed to be an invalid value for parameter " + parameter);
      }
    } catch (IllegalArgumentException e) {
      if (valid) {
        fail(arg + " supposed to be a valid value for parameter " + parameter + " " + e.getMessage());
      }
    }

    // test isRange
    assertEquals(range, SearchTypeValidator.isRange(arg), "Wrong isRange parsing of value " + arg);
  }

  /**
   * Mock implementation of SearchParameter that used a {@link Temporal}.
   */
  private enum SearchLocalDateTime implements SearchParameter {
    SEARCH_LOCAL_DATE_TIME;
    @Override
    public Class<?> type() {
      return LocalDateTime.class;
    }
  }
}
