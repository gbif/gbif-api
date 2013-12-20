package org.gbif.api.util.jackson;

import org.gbif.dwc.terms.Term;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * An jackson (de-) serialization module containing all custom code needed to use the entire GBIF API with JSON in jackson.
 */
public class ApiJacksonModule extends SimpleModule {

  public ApiJacksonModule() {
    super("GBIF API", new Version(1,0,0, null));
    addDeserializer(Term.class, new TermDeserializer());
    addSerializer(Term.class, new TermSerializer());
  }
}
