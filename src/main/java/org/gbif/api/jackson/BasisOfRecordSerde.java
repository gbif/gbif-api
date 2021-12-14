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

import org.gbif.api.vocabulary.BasisOfRecord;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializer for BasisOfRecord. Handles deprecated elements in the enum.
 */
public class BasisOfRecordSerde {

  /**
   * Jackson {@link JsonSerializer} for {@link BasisOfRecord}.
   */
  public static class BasisOfRecordJsonSerializer extends JsonSerializer<BasisOfRecord> {

    @Override
    public void serialize(BasisOfRecord value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      if (value == null) {
        jgen.writeNull();
        return;
      }
      jgen.writeString(getValue(value).name());
    }
  }

  /**
   * Maps deprecated values into their default values.
   */
  private static BasisOfRecord getValue(BasisOfRecord basisOfRecord) {
    return BasisOfRecord.LITERATURE == basisOfRecord || BasisOfRecord.UNKNOWN == basisOfRecord? BasisOfRecord.OCCURRENCE : basisOfRecord;
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link BasisOfRecord}.
   */
  public static class BasisOfRecordJsonDeserializer extends JsonDeserializer<BasisOfRecord> {
    @Override
    public BasisOfRecord deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
        if (StringUtils.isEmpty(jp.getText())) {
          return null;
        }
        // first, try by url
        BasisOfRecord basisOfRecord = BasisOfRecord.valueOf(jp.getText());

        // then, try by name
        return getValue(basisOfRecord);
      }
      throw JsonMappingException.from(jp, "Expected String");
    }
  }
}
