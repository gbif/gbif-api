package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class ExtensionSerializer extends JsonSerializer<Extension> {

  @Override
  public void serialize(Extension value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeFieldName(value.getRowType());
  }
}
