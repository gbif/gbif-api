package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.GbifTerm;
import org.gbif.dwc.terms.IucnTerm;
import org.gbif.dwc.terms.Term;
import org.gbif.dwc.terms.TermFactory;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  public void testHasField() {
    VerbatimOccurrence occ = new VerbatimOccurrence();

    occ.setField(DwcTerm.catalogNumber, "abc123");
    assertTrue(occ.hasField(DwcTerm.catalogNumber));
    assertFalse(occ.hasField(DwcTerm.institutionCode));

    occ.setField(DwcTerm.catalogNumber, " ");
    assertTrue(occ.hasField(DwcTerm.catalogNumber));

    occ.setField(DwcTerm.catalogNumber, "");
    assertFalse(occ.hasField(DwcTerm.catalogNumber));

    occ.setField(DwcTerm.catalogNumber, null);
    assertFalse(occ.hasField(DwcTerm.catalogNumber));
  }

  @Test
  public void testFieldFromTermFactory() {
    VerbatimOccurrence occ = new VerbatimOccurrence();
    String prefix = "t_";
    String colName = "t_dwc:basisOfRecord";
    Term term = TermFactory.instance().findTerm(colName.substring(prefix.length()));
    occ.setField(term, "PreservedSpecimen");
    assertEquals(1, occ.getFields().size());
    assertTrue(occ.hasField(DwcTerm.basisOfRecord));
    assertEquals("PreservedSpecimen", occ.getField(DwcTerm.basisOfRecord));
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

  @Test
  public void testJsonSerdeAllFields() throws IOException {
    ObjectMapper mapper = new ObjectMapper();


    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setKey(123);
    String termPrefix = "I am Jack's ";
    for (Term term : DwcTerm.values()) {
      verb.setField(term, termPrefix + term);
    }
    for (Term term : DcTerm.values()) {
      verb.setField(term, termPrefix + term);
    }
    for (Term term : GbifTerm.values()) {
      verb.setField(term, termPrefix + term);
    }
    for (Term term : IucnTerm.values()) {
      verb.setField(term, termPrefix + term);
    }

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (Term term : DwcTerm.values()) {
      assertTrue(deser.hasField(term));
      assertEquals(termPrefix + term, deser.getField(term));
    }
    for (Term term : DcTerm.values()) {
      assertTrue(deser.hasField(term));
      assertEquals(termPrefix + term, deser.getField(term));
    }
    for (Term term : GbifTerm.values()) {
      assertTrue(deser.hasField(term));
      assertEquals(termPrefix + term, deser.getField(term));
    }
    for (Term term : IucnTerm.values()) {
      assertTrue(deser.hasField(term));
      assertEquals(termPrefix + term, deser.getField(term));
    }
  }
}
