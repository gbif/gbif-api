package org.gbif.api.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gbif.api.vocabulary.Rank;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

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
