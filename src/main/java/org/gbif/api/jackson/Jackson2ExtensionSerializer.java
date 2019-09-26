package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

public class Jackson2ExtensionSerializer extends JsonSerializer<Extension> {

  @Override
  public void serialize(Extension value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeFieldName(value.getRowType());
  }
}
