package org.gbif.api.jackson;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Jackson Serializer and Deserializer for {@link java.util.Map.Entry}.
 * This is mostly for pre 2.7 version of Jackson see
 * https://github.com/fasterxml/jackson-databind/issues/565
 *
 * The goal is to omit the key/value field name since they are implicit
 * for a Map.Entry.
 *
 * {"key":"mykey","value":18} becomes {"mykey":18}
 *
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
                          SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
      if(value == null){
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
        case VALUE_STRING: value = jp.getText();
          break;
        case VALUE_NUMBER_INT: value = jp.getIntValue();
          break;
        case VALUE_NUMBER_FLOAT: value = jp.getFloatValue();
          break;
        default :
          throw ctxt.mappingException("Expected String or Number");
      }
      jp.nextToken();
      tmp = jp.getText(); // }

      return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
  }
}
