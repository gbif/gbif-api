package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Rank;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} and Jackson {@link JsonDeserializer} classes for {@link Rank} that uses the common rank markers instead of enum names.
 */
public class RankSerde {

  /**
   * Jackson {@link JsonSerializer} for {@link Rank}.
   */
  public static class RankJsonSerializer extends JsonSerializer<Rank> {

    @Override
    public void serialize(Rank value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      if (value == null) {
        jgen.writeNull();
        return;
      }
      if (value.getMarker() == null) {
        jgen.writeString(value.name().toLowerCase().replaceAll("_", " "));
      } else {
        jgen.writeString(value.getMarker());
      }
    }
  }

  public static class Jackson2RankJsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Rank> {

    @Override
    public void serialize(Rank value,
                          com.fasterxml.jackson.core.JsonGenerator jgen,
                          com.fasterxml.jackson.databind.SerializerProvider provider) throws IOException {
      if (value == null) {
        jgen.writeNull();
        return;
      }
      if (value.getMarker() == null) {
        jgen.writeString(value.name().toLowerCase().replaceAll("_", " "));
      } else {
        jgen.writeString(value.getMarker());
      }
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link Rank}.
   */
  public static class RankJsonDeserializer extends JsonDeserializer<Rank> {
    private static final Map<String, Rank> RANKS = Maps.newHashMap();
    static {
      for (Rank r : Rank.values()) {
        if (r.getMarker() != null) {
          RANKS.put(r.getMarker(), r);
        }
      }
    }

    @Override
    public Rank deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
        if (RANKS.containsKey(jp.getText())) {
          return RANKS.get(jp.getText());

        } else {
          // try enum name as last resort
          try {
            return Rank.valueOf(jp.getText().toUpperCase().replaceAll(" ", "_"));
          } catch (IllegalArgumentException e) {
            // swallow
          }
        }
        return null;
      }
      throw ctxt.mappingException("Expected String");
    }
  }

  public static class Jackson2RankJsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<Rank> {
    private static final Map<String, Rank> RANKS = Maps.newHashMap();
    static {
      for (Rank r : Rank.values()) {
        if (r.getMarker() != null) {
          RANKS.put(r.getMarker(), r);
        }
      }
    }

    @Override
    public Rank deserialize(com.fasterxml.jackson.core.JsonParser jp,
                            com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == com.fasterxml.jackson.core.JsonToken.VALUE_STRING) {
        if (RANKS.containsKey(jp.getText())) {
          return RANKS.get(jp.getText());

        } else {
          // try enum name as last resort
          try {
            return Rank.valueOf(jp.getText().toUpperCase().replace(" ", "_"));
          } catch (IllegalArgumentException e) {
            // swallow
          }
        }
        return null;
      }
      throw ctxt.mappingException("Expected String");
    }
  }
}
