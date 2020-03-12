/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.vocabulary;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

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
