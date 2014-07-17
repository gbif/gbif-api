package org.gbif.api.vocabulary;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testUniqueness() throws Exception {
        Set<String> keys = Sets.newHashSet();
        for (NomenclaturalStatus ns : NomenclaturalStatus.values()) {
            if (ns.getAbbreviatedLabel() != null) {
                assertFalse(ns.name(), keys.contains(ns.getAbbreviatedLabel()));
                keys.add(ns.getAbbreviatedLabel());
            }

            if (ns.getLatinLabel() != null) {
                assertFalse(ns.name(), keys.contains(ns.getLatinLabel()));
                keys.add(ns.getLatinLabel());
            }
        }
    }
}
