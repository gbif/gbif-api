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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.deser.std.DateDeserializer;

/**
 * Jackson {@link JsonSerializer} classes for {@link Date}s with specified formats.
 */
public class DateSerde {

  private static SimpleDateFormat noTimezoneFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  static {
    noTimezoneFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  /**
   * Jackson {@link JsonSerializer} for {@link Date}.
   */
  public static class NoTimezoneDateJsonSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      if (value == null) {
        // Empty fields aren't included in the JSON.
        return;
      } else {
        jgen.writeString(noTimezoneFormat.format(value));
      }
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link Date}s formatted above, falling back to the Jackson way.
   */
  public static class FlexibleDateJsonDeserializer extends DateDeserializer {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
        String text = jp.getText();
        if (text.length() == 19) {
          return Date.from(Instant.parse(text+"Z"));
        } else {
          return super.deserialize(jp, ctxt);
        }
      }
      throw ctxt.mappingException("Expected String");
    }
  }
}
