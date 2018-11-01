package org.gbif.api.jackson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

public class EnumListSerializer extends SerializerBase<List> {

  public EnumListSerializer() {
    super(List.class);
  }

  @Override
  public void serialize(List value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    if (value != null && jgen != null) {
      StringJoiner sj = new StringJoiner(",");
      value.stream().filter(Objects::nonNull).forEach(v -> sj.add(v.toString()));

      jgen.writeStartArray();
      jgen.writeString(sj.toString());
      jgen.writeEndArray();
    }
  }
}
