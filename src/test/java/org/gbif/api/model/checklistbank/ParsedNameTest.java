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

  @Test
  public void testBuildName() throws Exception {
    ParsedName pn = new ParsedName();
    pn.setGenusOrAbove("Pseudomonas");
    assertBuildName(pn, "Pseudomonas");

    pn.setSpecificEpithet("syringae");
    assertBuildName(pn, "Pseudomonas syringae");

    pn.setAuthorsParsed(true);
    pn.setAuthorship("Van Hall");
    assertBuildName(pn, "Pseudomonas syringae Van Hall", "Pseudomonas syringae", "Pseudomonas syringae Van Hall", "Pseudomonas syringae");

    pn.setYear("1904");
    assertBuildName(pn, "Pseudomonas syringae Van Hall, 1904", "Pseudomonas syringae", "Pseudomonas syringae Van Hall, 1904", "Pseudomonas syringae");

    pn.setBracketAuthorship("Carl.");
    assertBuildName(pn, "Pseudomonas syringae (Carl.) Van Hall, 1904", "Pseudomonas syringae", "Pseudomonas syringae (Carl.) Van Hall, 1904", "Pseudomonas syringae");

    pn.setRank(Rank.PATHOVAR);
    pn.setInfraSpecificEpithet("aceris");
    pn.setBracketAuthorship(null);
    assertBuildName(pn, "Pseudomonas syringae pv. aceris Van Hall, 1904", "Pseudomonas syringae aceris", "Pseudomonas syringae pv. aceris Van Hall, 1904", "Pseudomonas syringae pv. aceris");

    pn.setStrain("CFBP 2339");
    assertBuildName(pn, "Pseudomonas syringae pv. aceris Van Hall, 1904 CFBP 2339", "Pseudomonas syringae aceris", "Pseudomonas syringae pv. aceris Van Hall, 1904 CFBP 2339", "Pseudomonas syringae pv. aceris CFBP 2339");

    pn.setYear(null);
    pn.setAuthorship(null);
    assertBuildName(pn, "Pseudomonas syringae pv. aceris CFBP 2339", "Pseudomonas syringae aceris", "Pseudomonas syringae pv. aceris CFBP 2339", "Pseudomonas syringae pv. aceris CFBP 2339");
  }

  /**
   * assert all build name methods return the same string
   * @param pn
   * @param name expected string
   */
  private void assertBuildName(ParsedName pn, String name) {
    assertEquals(name, pn.fullName());
    assertEquals(name, pn.canonicalName());
    assertEquals(name, pn.canonicalNameComplete());
    assertEquals(name, pn.canonicalNameWithMarker());
  }

  private void assertBuildName(ParsedName pn, String full, String canonical, String canonicalComplete, String canonicalMarker) {
    assertEquals(full, pn.fullName());
    assertEquals(canonical, pn.canonicalName());
    assertEquals(canonicalComplete, pn.canonicalNameComplete());
    assertEquals(canonicalMarker, pn.canonicalNameWithMarker());
  }
}
