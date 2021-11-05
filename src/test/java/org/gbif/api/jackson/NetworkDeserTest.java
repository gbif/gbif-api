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

import org.gbif.api.model.registry.Network;
import org.gbif.api.ws.mixin.NetworkMixin;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNull;

public class NetworkDeserTest {

  @Test
  public void testLogoEmptyAsNullDeser() throws Exception {
    String networkJson = "{\n" +
        "  \"key\": \"5f02b486-8869-418b-88ba-1819001f1da7\",\n" +
        "  \"language\": \"eng\",\n" +
        "  \"logoUrl\": \"\"\n" +
        "}";

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.addMixIn(Network.class, NetworkMixin.class);

    Network network = objectMapper.readValue(networkJson, Network.class);
    assertNull(network.getLogoUrl());
  }
}
