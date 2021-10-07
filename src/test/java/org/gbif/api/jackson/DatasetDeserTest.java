/*
 * Copyright 2021 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.jackson;

import org.gbif.api.model.registry.Dataset;
import org.gbif.api.ws.mixin.DatasetMixin;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DatasetDeserTest {

  @Test
  public void testHomepageAndLogoEmptyToNullDeserialization() throws Exception {
    String datasetJson = "{\n" +
        "  \"installationKey\": \"5f02b486-8869-418b-88ba-1819001f1da7\",\n" +
        "  \"publishingOrganizationKey\": \"83cc80f4-f95f-4404-a10a-fc02aa9fee4d\",\n" +
        "  \"homepage\": \"\",\n" +
        "  \"logoUrl\": \"\",\n" +
        "  \"type\": \"OCCURRENCE\",\n" +
        "  \"title\": \"Dataset URL validation\",\n" +
        "  \"description\": \"Created to test URL validation\",\n" +
        "  \"language\": \"eng\"\n" +
        "}";

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.addMixIn(Dataset.class, DatasetMixin.class);

    Dataset dataset = objectMapper.readValue(datasetJson, Dataset.class);
    assertNull(dataset.getHomepage());
    assertNull(dataset.getLogoUrl());
  }
}
