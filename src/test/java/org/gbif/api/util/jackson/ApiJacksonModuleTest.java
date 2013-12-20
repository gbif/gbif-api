package org.gbif.api.util.jackson;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.Term;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiJacksonModuleTest {

  @Test
  public void testTermSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new ApiJacksonModule());

    for (Term t : DwcTerm.values()) {
      String json = mapper.writeValueAsString(t);
      assertEquals(t, mapper.readValue(json, Term.class));
    }

    for (Term t : GbifTerm.values()) {
      String json = mapper.writeValueAsString(t);
      assertEquals(t, mapper.readValue(json, Term.class));
    }
  }
}
