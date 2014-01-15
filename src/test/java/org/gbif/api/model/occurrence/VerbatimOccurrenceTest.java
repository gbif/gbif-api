package org.gbif.api.model.occurrence;

import org.gbif.dwc.terms.DwcTerm;

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
}
