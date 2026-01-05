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
package org.gbif.api.model.common.search;

import org.gbif.api.model.checklistbank.search.NameUsageSearchParameter;
import org.gbif.api.model.checklistbank.search.NameUsageSuggestRequest;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchRequest;
import org.gbif.api.vocabulary.BasisOfRecord;
import org.gbif.api.vocabulary.Rank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for SearchRequest model class
 */
public class SearchRequestTest {

  @Test
  public void testEnumFacet() {
    SearchRequest<NameUsageSearchParameter> req = new NameUsageSuggestRequest();
    req.addParameter(NameUsageSearchParameter.RANK, Rank.SPECIES);
    assertEquals("SPECIES", req.getParameters().get(NameUsageSearchParameter.RANK).iterator().next());

    SearchRequest<OccurrenceSearchParameter> reqO = new OccurrenceSearchRequest();
    reqO.addParameter(OccurrenceSearchParameter.BASIS_OF_RECORD, BasisOfRecord.LITERATURE);
    assertEquals("LITERATURE",
      reqO.getParameters().get(OccurrenceSearchParameter.BASIS_OF_RECORD).iterator().next());
  }

}
