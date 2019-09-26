package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Jackson2TermMapListSerializer extends JsonSerializer<List<Map<Term, String>>> {
  @Override
  public void serialize(List<Map<Term, String>> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

    if ((value == null || value.isEmpty()) && provider.getConfig().isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)) {
      jgen.writeStartArray();
      jgen.writeEndArray();
    } else {
      jgen.writeStartArray();
      for (Map<Term, String> extension : value) {
        jgen.writeStartObject();
        for (Map.Entry<Term, String> entry : extension.entrySet()) {
          jgen.writeStringField(entry.getKey().qualifiedName(), entry.getValue());
        }
        jgen.writeEndObject();
      }
      jgen.writeEndArray();
    }
  }
}
