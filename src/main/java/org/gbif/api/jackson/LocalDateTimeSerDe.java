package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Jackson {@link JsonSerializer} and Jackson {@link JsonDeserializer} classes for {@link
 * LocalDateTime}.
 */
public class LocalDateTimeSerDe {

  /**
   * Jackson {@link JsonSerializer} for {@link LocalDateTime}.
   */
  public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException {
      jgen.writeString((value.toString()));
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link LocalDateTime}.
   */
  public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
      return LocalDateTime.parse(jp.getText());
    }
  }
}
