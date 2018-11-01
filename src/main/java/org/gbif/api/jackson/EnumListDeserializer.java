package org.gbif.api.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class EnumListDeserializer extends JsonDeserializer<List> {

  @Override
  public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp != null && jp.getTextLength() > 0) {
      ArrayList<String> res = new ArrayList<>();

      JsonToken token = jp.getCurrentToken();
      if (token != JsonToken.START_ARRAY) {
        throw new JsonParseException("Start array expected", jp.getCurrentLocation());
      }

      if (jp.hasCurrentToken()) {
        jp.nextToken();
        String text = jp.getText();
        if (text != null && !text.isEmpty()) {
          Arrays.stream(text.split(",")).filter(Objects::nonNull).forEach(res::add);
        }
      }

      token = jp.nextToken();
      if (token != JsonToken.END_ARRAY) {
        throw new JsonParseException("End array expected", jp.getCurrentLocation());
      }

      return res;
    }

    return new ArrayList<>();
  }
}
