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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
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
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.UTC), null, "2009-02-04T05:06:07Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.ofHours(5)), null, "2009-02-04T05:06:07+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6, 7, 0), null, "2009-02-04T05:06:07");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.UTC), null, "2009-02-04T05:06Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.ofHours(5)), null, "2009-02-04T05:06+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6), null, "2009-02-04T05:06");
    test(LocalDate.of(2009, 2, 4), null, "2009-02-04");
    test(YearMonth.of(2009, 2), null, "2009-02");
    test(Year.of(2009), null, "2009");

    // Implicit ranges to simplify
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.UTC), OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.UTC), "2009-02-04T05:06:07Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.ofHours(5)), OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6, 7, 0), LocalDateTime.of(2009, 2, 4, 5, 6, 7, 0), "2009-02-04T05:06:07");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.UTC), OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.UTC), "2009-02-04T05:06Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.ofHours(5)), OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6), LocalDateTime.of(2009, 2, 4, 5, 6), "2009-02-04T05:06");
    test(LocalDate.of(2009, 2, 4), LocalDate.of(2009, 2, 4), "2009-02-04");
    test(YearMonth.of(2009, 2), YearMonth.of(2009, 2), "2009-02");
    test(Year.of(2009), Year.of(2009), "2009");

    // Explicit ranges
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.UTC), OffsetDateTime.of(2009, 2, 14, 15, 16, 17, 0, ZoneOffset.UTC), "2009-02-04T05:06:07Z/2009-02-14T15:16:17Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 7, 0, ZoneOffset.ofHours(5)), OffsetDateTime.of(2009, 2, 14, 15, 16, 17, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06:07+05:00/2009-02-14T15:16:17+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6, 7, 0), LocalDateTime.of(2009, 2, 14, 15, 16, 17, 0), "2009-02-04T05:06:07/2009-02-14T15:16:17");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.UTC), OffsetDateTime.of(2009, 2, 14, 15, 16, 0, 0, ZoneOffset.UTC), "2009-02-04T05:06Z/2009-02-14T15:16Z");
    test(OffsetDateTime.of(2009, 2, 4, 5, 6, 0, 0, ZoneOffset.ofHours(5)), OffsetDateTime.of(2009, 2, 14, 15, 16, 0, 0, ZoneOffset.ofHours(5)), "2009-02-04T05:06+05:00/2009-02-14T15:16+05:00");
    test(LocalDateTime.of(2009, 2, 4, 5, 6), LocalDateTime.of(2009, 2, 14, 15, 16), "2009-02-04T05:06/2009-02-14T15:16");
    test(LocalDate.of(2023, 1, 13), LocalDate.of(2023, 1, 14), "2023-01-13/2023-01-14");
    test(YearMonth.of(2009, 2), YearMonth.of(2009, 4), "2009-02/2009-04");
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
    @JsonSerialize(using = IsoDateIntervalSerde.IsoDateIntervalSerializer.class)
    @JsonDeserialize(using = IsoDateIntervalSerde.IsoDateIntervalDeserializer.class)
    public IsoDateInterval isoDateInterval;

    public DateRangeWrapper() {}

    public DateRangeWrapper(IsoDateInterval isoDateInterval){
      this.isoDateInterval = isoDateInterval;
    }
  }
}
