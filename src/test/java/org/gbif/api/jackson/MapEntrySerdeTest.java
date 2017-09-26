package org.gbif.api.jackson;

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;

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

    MapEntryListTermWrapper kvl = new MapEntryListTermWrapper(DwcTerm.country, 19);
    json = MAPPER.writeValueAsString(kvl);

    MapEntryListTermWrapper rebuiltKeyValueList = MAPPER.readValue(json, MapEntryListTermWrapper.class);
    assertEquals(2, rebuiltKeyValueList.getListTerm().size());

  }

  /**
   * For testing purpose only.
   */
  static class MapEntryWrapper {

    private Map.Entry<Object, Object> singleElement;

    MapEntryWrapper() {}

    MapEntryWrapper(Object key, Object value){
      singleElement = new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    @JsonSerialize(using = MapEntrySerde.MapEntryJsonSerializer.class)
    @JsonDeserialize(using = MapEntrySerde.MapEntryJsonDeserializer.class)
    Map.Entry<Object, Object> getSingleElement() {
      return singleElement;
    }
  }

  static class MapEntryListTermWrapper {

    private List<Map.Entry<Term, Object>> listTerm;

    MapEntryListTermWrapper() {}

    MapEntryListTermWrapper(Term key, Object value){
      listTerm = ImmutableList.of(new AbstractMap.SimpleImmutableEntry<>(key, value),
              new AbstractMap.SimpleImmutableEntry<>(key, value));
    }

    @JsonSerialize(contentUsing = MapEntrySerde.MapEntryJsonSerializer.class)
    @JsonDeserialize(contentUsing = MapEntrySerde.MapEntryJsonDeserializer.class)
    List<Map.Entry<Term, Object>> getListTerm() {
      return listTerm;
    }
  }

}
