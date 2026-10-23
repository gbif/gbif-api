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
package org.gbif.api.jackson;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test {@link Date} serialization using DateSerde.
 */
public class DateDeserTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testBothWays() throws IOException {
    Date d = Date.from(Instant.parse("1924-03-13T10:11:12.13Z"));

    DateWrapper Date = new DateWrapper(d);
    String json = MAPPER.writeValueAsString(Date);
    assertEquals("{\"date\":\"1924-03-13T10:11:12\"}", json);
    assertEquals("1924-03-13T10:11:12Z", MAPPER.readValue(json, DateWrapper.class).date.toInstant().toString());
  }

  @Test
  public void testLegacyDeser() throws IOException {
    String json = "{\"date\":\"1924-03-13T10:11:12.130+0000\"}";
    assertEquals("1924-03-13T10:11:12.130Z", MAPPER.readValue(json, DateWrapper.class).date.toInstant().toString());
  }

  @Test
  public void testSqlDate() throws IOException {
    java.sql.Date d = new java.sql.Date(Instant.parse("1924-03-13T10:11:12.13Z").toEpochMilli());
    assertEquals("{\"date\":\"1924-03-13T10:11:12\"}", MAPPER.writeValueAsString(new DateWrapper(d)));
  }

  /**
   * The serializer is shared by all threads using a mapper, so it must be thread-safe.
   */
  @Test
  public void testConcurrentSerialization() throws Exception {
    int threads = 16;
    int iterations = 2_000;
    ExecutorService executor = Executors.newFixedThreadPool(threads);
    try {
      List<Future<?>> futures = new ArrayList<>();
      for (int t = 0; t < threads; t++) {
        final int offset = t;
        futures.add(executor.submit(() -> {
          for (int i = 0; i < iterations; i++) {
            // Distinct dates per thread and iteration, so corrupted output is detected.
            Instant instant = Instant.parse("1924-03-13T10:11:12Z").plusSeconds((offset * iterations + i) * 86_461L);
            String expected = "{\"date\":\"" + instant.toString().replace("Z", "") + "\"}";
            assertEquals(expected, MAPPER.writeValueAsString(new DateWrapper(Date.from(instant))));
          }
          return null;
        }));
      }
      for (Future<?> f : futures) {
        f.get();
      }
    } finally {
      executor.shutdownNow();
    }
  }

  public static class DateWrapper {
    @JsonSerialize(using = DateSerde.NoTimezoneDateJsonSerializer.class)
    @JsonDeserialize(using = DateSerde.FlexibleDateJsonDeserializer.class)
    public Date date;

    public DateWrapper() {}

    public DateWrapper(Date date){
      this.date = date;
    }
  }
}
