package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

public class ExtensionDeserializer extends JsonDeserializer<Extension> {

  @Override
  public Extension deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      return Extension.fromRowType(jp.getText());
    }
    throw ctxt.mappingException("Expected JSON String");
  }
}

