package org.gbif.api.jackson;

import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Serializes list of maps of terms values.
 */
public class TermMapListSerializer extends JsonSerializer<List<Map<Term, String>>> {

  @Override
  public void serialize(List<Map<Term, String>> value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException {
    if ((value == null || value.isEmpty()) && provider.getConfig().isEnabled(Feature.WRITE_EMPTY_JSON_ARRAYS)) {
      jgen.writeStartArray();
      jgen.writeEndArray();
    } else {
      jgen.writeStartArray();
      for (Map<Term, String> extension : value) {
        jgen.writeStartObject();
        for (Entry<Term, String> entry : extension.entrySet()) {
          jgen.writeStringField(entry.getKey().qualifiedName(), entry.getValue());
        }
        jgen.writeEndObject();
      }
      jgen.writeEndArray();
    }
  }
}
