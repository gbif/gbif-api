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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.fasterxml.jackson.databind.ObjectMapper;
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
