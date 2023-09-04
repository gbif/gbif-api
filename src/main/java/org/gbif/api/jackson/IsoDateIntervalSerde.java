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
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} classes for {@link IsoDateInterval}s with specified formats.
 */
public class IsoDateIntervalSerde {

  /**
   * Jackson {@link JsonSerializer} for {@link IsoDateInterval}.
   */
  public static class NoTimezoneDateRangeJsonSerializer extends JsonSerializer<IsoDateInterval> {

    @Override
    public void serialize(IsoDateInterval value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      // Empty fields aren't included in the JSON.
      if (value != null) {
        jgen.writeString(value.toString());
      }
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link IsoDateInterval}s formatted above, falling back to the Jackson way.
   */
  public static class FlexibleDateRangeJsonDeserializer extends JsonDeserializer<IsoDateInterval> {

    @Override
    public IsoDateInterval deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
        String text = jp.getText();

        try {
          return IsoDateInterval.fromString(text);
        } catch (ParseException e) {
          throw JsonMappingException.from(jp, "Unable to parse date string");
        }

      }
      throw JsonMappingException.from(jp, "Expected String");
    }
  }
}
