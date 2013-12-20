package org.gbif.api.util.jackson;

import org.gbif.dwc.terms.Term;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * A json serializer that turns dwc terms into their full qualified term name.
 */
public class TermSerializer extends JsonSerializer<Term> {

  @Override
  public void serialize(Term value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
    jgen.writeString(value.qualifiedName());
  }
}
