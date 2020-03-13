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
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test {@link Date} serialization using DateSerde.
 *
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
