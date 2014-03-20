package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class ExtensionDeserializer extends JsonDeserializer<Extension> {

  @Override
  public Extension deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      return Extension.fromRowType(jp.getText());
    }
    throw ctxt.mappingException("Expected JSON String");
  }
}

