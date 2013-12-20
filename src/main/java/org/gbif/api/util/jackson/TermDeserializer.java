package org.gbif.api.util.jackson;

import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * A json deserializer that turns full qualified term names into dwc terms instances.
 */
public class TermDeserializer extends JsonDeserializer<Term> {
  private TermFactory factory = new TermFactory();

  @Override
  public Term deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      return factory.findTerm(jp.getText());
    }
    throw ctxt.mappingException("Expected JSON String");
  }
}
