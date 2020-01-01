package org.gbif.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.Lists;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Deserializes list of maps of terms values.
 */
public class TermMapListDeserializer extends JsonDeserializer<List<Map<Term, String>>> {

  private final TermFactory termFactory = TermFactory.instance();

  @Override
  public List<Map<Term, String>> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp.getCurrentToken() == JsonToken.START_ARRAY) {

      // TODO: 01/01/2020 test it
      JsonDeserializer<Object> deserializer = ctxt.findNonContextualValueDeserializer(ctxt.constructType(List.class));

      List<Map<String, String>> verbatimTerms = (List<Map<String, String>>) deserializer.deserialize(jp, ctxt);
      List<Map<Term, String>> interpretedTerms = Lists.newArrayList();
      for (Map<String, String> verbExtension : verbatimTerms) {
        Map<Term, String> extension = new HashMap<>();
        for (Entry<String, String> entry : verbExtension.entrySet()) {
          Term term = termFactory.findTerm(entry.getKey());
          if (term == null && ctxt.getConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw ctxt.mappingException("Term not found " + entry.getKey());
          }
          extension.put(term, entry.getValue());
        }
        interpretedTerms.add(extension);
      }
      return interpretedTerms;
    }
    throw ctxt.mappingException("Expected JSON String");
  }
}
