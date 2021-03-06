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

import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public MapEntryWrapper() {}

    public MapEntryWrapper(Object key, Object value){
      singleElement = new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    public Map.Entry<Object, Object> getSingleElement() {
      return singleElement;
    }

    public void setSingleElement(Map.Entry<Object, Object> singleElement) {
      this.singleElement = singleElement;
    }
  }

  static class MapEntryListTermWrapper {

    private List<Map.Entry<Term, Object>> listTerm;

    public MapEntryListTermWrapper() {}

    public MapEntryListTermWrapper(Term key, Object value){
      listTerm = Collections.unmodifiableList(
        Arrays.asList(
          new AbstractMap.SimpleImmutableEntry<>(key, value),
          new AbstractMap.SimpleImmutableEntry<>(key, value)));
    }

    public List<Map.Entry<Term, Object>> getListTerm() {
      return listTerm;
    }

    public void setListTerm(List<Map.Entry<Term, Object>> listTerm) {
      this.listTerm = listTerm;
    }
  }
}
