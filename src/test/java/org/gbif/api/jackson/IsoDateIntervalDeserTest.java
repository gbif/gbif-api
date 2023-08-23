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
package org.gbif.api.jackson;

import org.gbif.api.util.IsoDateInterval;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test {@link IsoDateInterval} serialization using DateRangeSerde.
 */
public class IsoDateIntervalDeserTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testBothWays() throws IOException {
    // Implicit ranges
    test(ZonedDateTime.of(2009, 02, 04, 05, 06, 07, 0, ZoneOffset.UTC), null, "2009-02-04T05:06:07Z");
    test(LocalDate.of(2009, 02, 04), null, "2009-02-04");
    test(YearMonth.of(2009, 02), null, "2009-02");
    test(Year.of(2009), null, "2009");

    // Implicit ranges to simplify
    test(ZonedDateTime.of(2009, 02, 04, 05, 06, 07, 0, ZoneOffset.UTC), ZonedDateTime.of(2009, 02, 04, 05, 06, 07, 0, ZoneOffset.UTC), "2009-02-04T05:06:07Z");
    test(LocalDate.of(2009, 02, 04), LocalDate.of(2009, 02, 04), "2009-02-04");
    test(YearMonth.of(2009, 02), YearMonth.of(2009, 02), "2009-02");
    test(Year.of(2009), Year.of(2009), "2009");

    // Explicit ranges
    test(ZonedDateTime.of(2009, 02, 04, 05, 06, 07, 0, ZoneOffset.UTC), ZonedDateTime.of(2009, 02, 14, 15, 16, 17, 0, ZoneOffset.UTC), "2009-02-04T05:06:07Z/2009-02-14T15:16:17Z");
    test(LocalDate.of(2023, 01, 13), LocalDate.of(2023, 01, 14), "2023-01-13/2023-01-14");
    test(YearMonth.of(2009, 02), YearMonth.of(2009, 04), "2009-02/2009-04");
    test(Year.of(2009), Year.of(2010), "2009/2010");
  }

  private void test(Temporal from, Temporal to, String expectedJson) throws IOException {
    IsoDateInterval isoDateInterval = new IsoDateInterval(from, to);
    DateRangeWrapper drw = new DateRangeWrapper(isoDateInterval);
    String json = MAPPER.writeValueAsString(drw);
    assertEquals("{\"isoDateInterval\":\"" + expectedJson + "\"}", json);
    assertEquals(from, MAPPER.readValue(json, DateRangeWrapper.class).isoDateInterval.getFrom());
    if (from.equals(to)) {
      assertNull(MAPPER.readValue(json, DateRangeWrapper.class).isoDateInterval.getTo());
    } else {
      assertEquals(to, MAPPER.readValue(json, DateRangeWrapper.class).isoDateInterval.getTo());
    }
  }

  public static class DateRangeWrapper {
    @JsonSerialize(using = IsoDateRangeSerde.NoTimezoneDateRangeJsonSerializer.class)
    @JsonDeserialize(using = IsoDateRangeSerde.FlexibleDateRangeJsonDeserializer.class)
    public IsoDateInterval isoDateInterval;

    public DateRangeWrapper() {}

    public DateRangeWrapper(IsoDateInterval isoDateInterval){
      this.isoDateInterval = isoDateInterval;
    }
  }
}
