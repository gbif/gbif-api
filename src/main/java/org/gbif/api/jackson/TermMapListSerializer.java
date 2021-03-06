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

import org.gbif.dwc.terms.Term;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

@Deprecated
public class TermMapListSerializer extends JsonSerializer<List<Map<Term, String>>> {
  @Override
  public void serialize(List<Map<Term, String>> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

    if ((value == null || value.isEmpty()) && provider.getConfig().isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)) {
      jgen.writeStartArray();
      jgen.writeEndArray();
    } else {
      jgen.writeStartArray();
      for (Map<Term, String> extension : value) {
        jgen.writeStartObject();
        for (Map.Entry<Term, String> entry : extension.entrySet()) {
          jgen.writeStringField(entry.getKey().qualifiedName(), entry.getValue());
        }
        jgen.writeEndObject();
      }
      jgen.writeEndArray();
    }
  }
}
