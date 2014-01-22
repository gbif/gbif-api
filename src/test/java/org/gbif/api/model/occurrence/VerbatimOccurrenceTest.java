package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.DwcTerm;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerbatimOccurrenceTest {

  @Test
  public void testFieldUpdate() {
    VerbatimOccurrence occ = new VerbatimOccurrence();
    String catNum = "abc123";
    occ.setField(DwcTerm.catalogNumber, catNum);
    assertEquals(catNum, occ.getField(DwcTerm.catalogNumber));

    String catNum2 = "qwer";
    occ.setField(DwcTerm.catalogNumber, catNum2);
    assertEquals(catNum2, occ.getField(DwcTerm.catalogNumber));
  }

  @Test
  public void testJsonSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setField(DwcTerm.institutionCode, "IC");
    verb.setField(DwcTerm.collectionCode, "BUGS");
    verb.setField(DwcTerm.catalogNumber, "MD10782");

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (DwcTerm term : DwcTerm.values()) {
      assertEquals(verb.getField(term), deser.getField(term));
    }
  }
}
