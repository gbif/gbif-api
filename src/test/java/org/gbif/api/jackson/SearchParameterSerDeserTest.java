/*
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
package org.gbif.api.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gbif.api.model.checklistbank.search.NameUsageSearchParameter;
import org.gbif.api.model.common.search.Facet;
import org.gbif.api.model.event.search.EventSearchParameter;
import org.gbif.api.model.literature.search.LiteratureSearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;
import org.gbif.api.model.registry.search.DatasetSearchParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchParameterSerDeserTest {

  @Test
  public void testFacetsSer() throws Exception {
    Facet<OccurrenceSearchParameter> facet = new Facet<>(OccurrenceSearchParameter.ISLAND);

    ObjectMapper objectMapper = new ObjectMapper();
    Assertions.assertEquals(
        "{\"field\":\"ISLAND\",\"counts\":null}", objectMapper.writeValueAsString(facet));

    Facet<EventSearchParameter> eventFacet = new Facet<>(EventSearchParameter.ISLAND);
    Assertions.assertEquals(
        "{\"field\":\"ISLAND\",\"counts\":null}", objectMapper.writeValueAsString(eventFacet));

    Facet<DatasetSearchParameter> datasetFacet = new Facet<>(DatasetSearchParameter.CONTINENT);
    Assertions.assertEquals(
        "{\"field\":\"CONTINENT\",\"counts\":null}", objectMapper.writeValueAsString(datasetFacet));

    Facet<NameUsageSearchParameter> nameUsageFacet = new Facet<>(NameUsageSearchParameter.RANK);
    Assertions.assertEquals(
        "{\"field\":\"RANK\",\"counts\":null}", objectMapper.writeValueAsString(nameUsageFacet));

    Facet<LiteratureSearchParameter> literatureFacet = new Facet<>(LiteratureSearchParameter.DOI);
    Assertions.assertEquals(
        "{\"field\":\"DOI\",\"counts\":null}", objectMapper.writeValueAsString(literatureFacet));
  }
}
