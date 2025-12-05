package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson {@link JsonSerializer} and {@link JsonDeserializer} classes for {@link OffsetDateTime}.
 */
public class OffsetDateTimeSerDe {

  private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");

  /**
   * Jackson {@link JsonSerializer} for {@link OffsetDateTime}.
   */
  public static class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException {
      jgen.writeString(value.format(FORMATTER));
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link OffsetDateTime}.
   */
  public static class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    @Override
    public OffsetDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
      return OffsetDateTime.parse(jp.getText(), FORMATTER);
    }
  }
}
