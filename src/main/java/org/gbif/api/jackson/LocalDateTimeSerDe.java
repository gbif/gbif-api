package org.gbif.api.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} and Jackson {@link JsonDeserializer} classes for {@link
 * LocalDateTime}.
 */
public class LocalDateTimeSerDe {

  /** Jackson {@link JsonSerializer} for {@link LocalDateTime}. */
  public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
      jgen.writeString((value.toString()));
    }
  }

  /** Jackson {@link JsonDeserializer} for {@link LocalDateTime}. */
  public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
      return LocalDateTime.parse(jp.getText());
    }
  }

  public static class Jackson2LocalDateTimeSerializer
    extends com.fasterxml.jackson.databind.JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value,
                          com.fasterxml.jackson.core.JsonGenerator jgen,
                          com.fasterxml.jackson.databind.SerializerProvider provider) throws IOException {
        jgen.writeString((value.toString()));
      }
  }

  public static class Jackson2LocalDateTimeDeserializer
    extends com.fasterxml.jackson.databind.JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser jp,
                                     com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
      return LocalDateTime.parse(jp.getText());
    }
  }
}
