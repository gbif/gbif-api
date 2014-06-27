/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtensionTest {

  @Test
  public void testFromRowType() throws Exception {
    assertNull(Extension.fromRowType(null));
    assertNull(Extension.fromRowType(""));
    assertNull(Extension.fromRowType(" "));
    assertNull(Extension.fromRowType("markus"));
    assertNull(Extension.fromRowType("http://rs.tdwg.org.notexists"));

    assertEquals(Extension.DESCRIPTION, Extension.fromRowType("http://rs.gbif.org/terms/1.0/Description"));
    assertEquals(Extension.DESCRIPTION, Extension.fromRowType("http://rs.gbif.org/terms/1.0/description"));
    assertEquals(Extension.DESCRIPTION, Extension.fromRowType("http://rs.gbif.org/TERMS/1.0/description"));

    assertEquals(Extension.DESCRIPTION, Extension.fromRowType("description"));
    assertEquals(Extension.VERNACULAR_NAME, Extension.fromRowType("VERNACULARNAME"));
    assertEquals(Extension.VERNACULAR_NAME, Extension.fromRowType("VernacularName"));
  }

}
