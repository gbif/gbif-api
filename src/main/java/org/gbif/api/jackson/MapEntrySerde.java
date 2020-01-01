package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Jackson Serializer and Deserializer for {@link java.util.Map.Entry}.
 * This is mostly for pre 2.7 version of Jackson see
 * https://github.com/fasterxml/jackson-databind/issues/565
 * <p>
 * The goal is to omit the key/value field name since they are implicit
 * for a Map.Entry.
 * <p>
 * {"key":"mykey","value":18} becomes {"mykey":18}
 * <p>
 * The key will use toString() and the value can only be a String or a Number (int or float) for now.
 *
 * <pre>
 * {@code
 * // Usage for lists:
 * @JsonSerialize(contentUsing = MapEntrySerde.MapEntryJsonSerializer.class)
 * public List<Map.Entry<String, Object>> getKeyValueList() { ... }
 * }
 * </pre>
 */
public class MapEntrySerde {

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
          throw ctxt.mappingException("Expected String or Number");
      }
      jp.nextToken();
      tmp = jp.getText(); // }

      return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
  }
}
