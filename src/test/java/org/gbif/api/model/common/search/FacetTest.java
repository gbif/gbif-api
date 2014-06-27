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
package org.gbif.api.model.common.search;

import org.gbif.api.model.checklistbank.search.NameUsageSearchParameter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for model class Facet
 */
public class FacetTest {

  @Test
  public void testEquals() {
    List<Facet.Count> counts = new ArrayList<Facet.Count>();
    counts.add(new Facet.Count("c1", 1L));
    counts.add(new Facet.Count("c3", 2L));
    counts.add(new Facet.Count("c4", 3L));

    NameUsageSearchParameter field = NameUsageSearchParameter.RANK;
    Facet<NameUsageSearchParameter> facet1 = new Facet<NameUsageSearchParameter>(field, counts);
    Facet<NameUsageSearchParameter> facet2 = new Facet<NameUsageSearchParameter>(field, counts);

    Assert.assertEquals(facet1, facet2);
  }

}
