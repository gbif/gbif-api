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

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for class SearchResponse
 */
public class SearchResponseTest {

  @Test
  public void testEquals() {
    List<String> results = Arrays.asList("r1", "r2", "r3");

    long offset = 10L;
    Long count = 100L;
    int limit = 40;
    SearchResponse<String, ?> searchResponse1 =
      new SearchResponse<String, NameUsageSearchParameter>(offset, limit, count, results, null);
    SearchResponse<String, ?> searchResponse2 =
      new SearchResponse<String, NameUsageSearchParameter>(offset, limit, count, results, null);

    assertEquals(searchResponse1, searchResponse2);
  }

}
