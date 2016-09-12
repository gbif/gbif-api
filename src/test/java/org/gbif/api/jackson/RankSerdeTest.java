package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Rank;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
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
