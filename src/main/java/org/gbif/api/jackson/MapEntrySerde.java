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
import java.util.AbstractMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <p>Jackson Serializer and Deserializer for {@link java.util.Map.Entry}.
 * <p>This is mostly for pre 2.7 version of Jackson, see <a href="https://github.com/fasterxml/jackson-databind/issues/565">Jackson Databind issue 565</a>.
 *
 * <p>The goal is to omit the key/value field name since they are implicit
 * for a Map.Entry.
 *
 * <code>{"key":"mykey","value":18}</code> becomes <code>{"mykey":18}</code>
 *
 * <p>The key will use <code>toString()</code> and the value can only be a String or a Number (int or float) for now.
 *
 * <pre>
 * // Usage for lists:
 * &#64;JsonSerialize(contentUsing = MapEntrySerde.MapEntryJsonSerializer.class)
 * public List<Map.Entry<String, Object>> getKeyValueList() { ... };
 * </pre>
 */
@Deprecated
public class MapEntrySerde {

  @Deprecated
  public static class MapEntryJsonSerializer extends JsonSerializer<Map.Entry<Object, Object>> {

    @Override
    public void serialize(Map.Entry<Object, Object> value, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {
      if (value == null) {
        jgen.writeNull();
        return;
      }
      jgen.writeStartObject();
      jgen.writeFieldName(value.getKey().toString());
      jgen.writeObject(value.getValue());
      jgen.writeEndObject();
    }
  }

  @Deprecated
  public static class MapEntryJsonDeserializer extends JsonDeserializer<Map.Entry<Object, Object>> {
    @Override
    public Map.Entry<Object, Object> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      String tmp = jp.getText(); // {
      jp.nextToken();
      String key = jp.getText();
      jp.nextToken();
      Object value;

      switch (jp.getCurrentToken()) {
        case VALUE_STRING:
          value = jp.getText();
          break;
        case VALUE_NUMBER_INT:
          value = jp.getIntValue();
          break;
        case VALUE_NUMBER_FLOAT:
          value = jp.getFloatValue();
          break;
        default:
          throw JsonMappingException.from(jp, "Expected String or Number");
      }
      jp.nextToken();
      tmp = jp.getText(); // }

      return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
  }
}
