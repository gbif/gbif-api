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
    occ.setVerbatimField(DwcTerm.catalogNumber, catNum);
    assertEquals(catNum, occ.getVerbatimField(DwcTerm.catalogNumber));

    String catNum2 = "qwer";
    occ.setVerbatimField(DwcTerm.catalogNumber, catNum2);
    assertEquals(catNum2, occ.getVerbatimField(DwcTerm.catalogNumber));
  }

  @Test
  public void testHasField() {
    VerbatimOccurrence occ = new VerbatimOccurrence();

    occ.setVerbatimField(DwcTerm.catalogNumber, "abc123");
    assertTrue(occ.hasVerbatimField(DwcTerm.catalogNumber));
    assertFalse(occ.hasVerbatimField(DwcTerm.institutionCode));

    occ.setVerbatimField(DwcTerm.catalogNumber, " ");
    assertTrue(occ.hasVerbatimField(DwcTerm.catalogNumber));

    occ.setVerbatimField(DwcTerm.catalogNumber, "");
    assertFalse(occ.hasVerbatimField(DwcTerm.catalogNumber));

    occ.setVerbatimField(DwcTerm.catalogNumber, null);
    assertFalse(occ.hasVerbatimField(DwcTerm.catalogNumber));
  }

  @Test
  public void testFieldFromTermFactory() {
    VerbatimOccurrence occ = new VerbatimOccurrence();
    String prefix = "t_";
    String colName = "t_dwc:basisOfRecord";
    Term term = TermFactory.instance().findTerm(colName.substring(prefix.length()));
    occ.setVerbatimField(term, "PreservedSpecimen");
    assertEquals(1, occ.getVerbatimFields().size());
    assertTrue(occ.hasVerbatimField(DwcTerm.basisOfRecord));
    assertEquals("PreservedSpecimen", occ.getVerbatimField(DwcTerm.basisOfRecord));
  }

  @Test
  public void testJsonSerde() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setVerbatimField(DwcTerm.institutionCode, "IC");
    verb.setVerbatimField(DwcTerm.collectionCode, "BUGS");
    verb.setVerbatimField(DwcTerm.catalogNumber, "MD10782");

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (DwcTerm term : DwcTerm.values()) {
      assertEquals(verb.getVerbatimField(term), deser.getVerbatimField(term));
    }
  }

  @Test
  public void testJsonSerdeAllFields() throws IOException {
    ObjectMapper mapper = new ObjectMapper();


    VerbatimOccurrence verb = new VerbatimOccurrence();
    verb.setKey(123);
    String termPrefix = "I am Jack's ";
    for (Term term : DwcTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : DcTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : GbifTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }
    for (Term term : IucnTerm.values()) {
      verb.setVerbatimField(term, termPrefix + term);
    }

    String json = mapper.writeValueAsString(verb);
    VerbatimOccurrence deser = mapper.readValue(json, VerbatimOccurrence.class);
    assertEquals(verb, deser);
    for (Term term : DwcTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : DcTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : GbifTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
    for (Term term : IucnTerm.values()) {
      assertTrue(deser.hasVerbatimField(term));
      assertEquals(termPrefix + term, deser.getVerbatimField(term));
    }
  }
}
