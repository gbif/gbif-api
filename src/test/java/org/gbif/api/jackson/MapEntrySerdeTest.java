package org.gbif.api.jackson;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit tests for {@link MapEntrySerde}.
 */
public class MapEntrySerdeTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void testMapEntrySerde() throws IOException {

    MapEntryWrapper kv = new MapEntryWrapper("mykey", 18);
    String json = MAPPER.writeValueAsString(kv);

    MapEntryWrapper rebuiltKeyValue = MAPPER.readValue(json, MapEntryWrapper.class);
    assertEquals(kv.getSingleElement().getValue(), rebuiltKeyValue.getSingleElement().getValue());
    assertEquals(2, rebuiltKeyValue.getAsList().size());
  }

  /**
   * For testing purpose only.
   */
  static class MapEntryWrapper {

    private Map.Entry<String, Object> singleElement;
    private List<Map.Entry<String, Object>> asList;

    MapEntryWrapper() {}

    MapEntryWrapper(String key, Object value){
      singleElement = new AbstractMap.SimpleImmutableEntry<>(key, value);
      asList = ImmutableList.of(singleElement, singleElement);
    }

    @JsonSerialize(using = MapEntrySerde.MapEntryJsonSerializer.class)
    @JsonDeserialize(using = MapEntrySerde.MapEntryJsonDeserializer.class)
    Map.Entry<String, Object> getSingleElement() {
      return singleElement;
    }

    @JsonSerialize(contentUsing = MapEntrySerde.MapEntryJsonSerializer.class)
    @JsonDeserialize(contentUsing = MapEntrySerde.MapEntryJsonDeserializer.class)
    List<Map.Entry<String, Object>> getAsList() {
      return asList;
    }
  }

}
