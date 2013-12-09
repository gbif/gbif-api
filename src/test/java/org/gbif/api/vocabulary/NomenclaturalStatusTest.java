package org.gbif.api.vocabulary;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class NomenclaturalStatusTest {

  @Test
  public void testFromString() throws Exception {
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("conserved"));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("conserved "));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("nom.cons."));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("nomcons"));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("nomen conservandum"));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("orth. cons."));
    assertEquals(NomenclaturalStatus.CONSERVED, NomenclaturalStatus.fromString("conservandum"));

    assertNull(NomenclaturalStatus.fromString("carla"));
    assertNull(NomenclaturalStatus.fromString(""));
    assertNull(NomenclaturalStatus.fromString(null));
  }
}
