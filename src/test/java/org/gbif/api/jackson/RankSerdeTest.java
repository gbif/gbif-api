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
package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Rank;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static junit.framework.TestCase.assertEquals;

/**
 * Test {@link Rank} serde using LicenseJsonSerializer and LicenseJsonDeserializer.
 *
 */
public class RankSerdeTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testRoundtrip() throws IOException {

    for (Rank r : Rank.values()) {
      RankWrapper rank = new RankWrapper(r);
      String json = MAPPER.writeValueAsString(rank);
      assertEquals(rank.rank, MAPPER.readValue(json, RankWrapper.class).rank);
    }
  }

  public static class RankWrapper {
    @JsonSerialize(using = RankSerde.RankJsonSerializer.class)
    @JsonDeserialize(using = RankSerde.RankJsonDeserializer.class)
    public Rank rank;

    public RankWrapper(){}

    public RankWrapper(Rank rank){
      this.rank = rank;
    }
  }
}
