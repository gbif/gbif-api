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
package org.gbif.api.util;

import org.gbif.api.model.checklistbank.NameUsage;
import org.gbif.api.vocabulary.Rank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClassificationUtilsTest {

  @Test
  public void testSetHigherRank() throws Exception {
    NameUsage lck = new NameUsage();

    ClassificationUtils.setHigherRank(lck, Rank.GENUS, "harry");
    assertEquals("harry", lck.getGenus());
    assertNull(lck.getGenusKey());

    ClassificationUtils.setHigherRank(lck, Rank.GENUS, "harrys", 432);
    assertEquals("harrys", lck.getGenus());
    assertEquals((Integer) 432, lck.getGenusKey());

    ClassificationUtils.setHigherRank(lck, null, "klinke", 1);
    assertEquals("harrys", lck.getGenus());
    assertEquals((Integer) 432, lck.getGenusKey());
  }

}
