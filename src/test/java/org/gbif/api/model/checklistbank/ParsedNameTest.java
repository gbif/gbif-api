package org.gbif.api.model.checklistbank;

import org.gbif.api.vocabulary.NamePart;
import org.gbif.api.vocabulary.Rank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParsedNameTest {

  @Test
  public void testCanonicalName() throws Exception {
    ParsedName pn = new ParsedName();
    pn.setGenusOrAbove("Abies");
    assertEquals("Abies", pn.canonicalName());

    pn.setSpecificEpithet("alba");
    assertEquals("Abies alba", pn.canonicalName());


    pn = new ParsedName();
    pn.setGenusOrAbove("Abies");
    pn.setSpecificEpithet("alba");
    pn.setRank(Rank.VARIETY);
    pn.setAuthorship("Mill.");
    pn.setBracketAuthorship("Carl.");
    pn.setNotho(NamePart.GENERIC);
    pn.setInfraSpecificEpithet("alpina");
    pn.setYear("1887");
    pn.setSensu("Döring");
    pn.setRemarks("lost");
    pn.setNomStatus("nom. illeg.");
    pn.setAuthorsParsed(true);

    assertEquals("Abies alba alpina", pn.canonicalName());
    assertEquals("×Abies alba var. alpina", pn.canonicalNameWithMarker());
    assertEquals("×Abies alba var. alpina (Carl.) Mill., 1887", pn.canonicalNameComplete());
    assertEquals("Abies alba", pn.canonicalSpeciesName());
    assertEquals("×Abies alba var. alpina (Carl.) Mill., 1887 Döring, nom. illeg. [lost]", pn.fullName());
  }
}
