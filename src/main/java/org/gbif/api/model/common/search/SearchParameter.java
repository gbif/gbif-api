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
package org.gbif.api.model.common.search;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.IOException;
import java.util.Optional;
import org.gbif.api.model.event.search.EventSearchParameter;
import org.gbif.api.model.occurrence.search.OccurrenceSearchParameter;

/**
 * Interface to be implemented by all search parameter enumerations. The enumeration member name
 * should avoid the following names which are already defined as standard (faceted) search
 * parameters:
 *
 * <ul>
 *   <li>limit
 *   <li>offset
 *   <li>q
 *   <li>hl
 *   <li>language
 *   <li>facet
 * </ul>
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    defaultImpl = SearchParameter.SearchParameterDefault.class)
@JsonSubTypes({
  @JsonSubTypes.Type(
      value = org.gbif.api.model.occurrence.search.OccurrenceSearchParameter.class,
      name = "occurrence"),
  @JsonSubTypes.Type(
      value = org.gbif.api.model.event.search.EventSearchParameter.class,
      name = "event")
})
@JsonDeserialize(using = SearchParameter.SearchParameterDeserializer.class)
public interface SearchParameter {

  String name();

  Class<?> type();

  // Fallback used when no type is provided for backwards compatibility
  @JsonDeserialize(using = SearchParameter.SearchParameterDeserializer.class)
  class SearchParameterDefault implements SearchParameter {
    @Override
    public String name() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> type() {
      return Object.class;
    }
  }

  // This deserializer is used only when the type property is not set. Otherwise it uses the custom
  // deserializers of each type
  class SearchParameterDeserializer extends JsonDeserializer<SearchParameter> {

    @Override
    public SearchParameter deserialize(JsonParser p, DeserializationContext ctx)
        throws IOException {
      JsonToken token = p.currentToken();

      if (token == JsonToken.VALUE_STRING) {
        String raw = p.getText();
        Optional<SearchParameter> opt = fromString(raw);
        if (opt.isPresent()) {
          return opt.get();
        }
      }

      // If parameter is an object, try to use the "name" property or map directly
      ObjectMapper mapper = (ObjectMapper) p.getCodec();
      JsonNode node = mapper.readTree(p);
      if (node.isObject()) {
        JsonNode nameNode = node.get("name");
        if (nameNode != null && nameNode.isTextual()) {
          String name = nameNode.asText();
          Optional<SearchParameter> opt = fromString(name);
          if (opt.isPresent()) {
            return opt.get();
          }
        }
      }

      return null;
    }

    private Optional<SearchParameter> fromString(String value) {
      if (value == null || value.isEmpty()) {
        return Optional.empty();
      }

      String name = value.trim();

      // Try occurrence first
      Optional<OccurrenceSearchParameter> occ = OccurrenceSearchParameter.lookup(name);
      if (occ.isPresent()) {
        return occ.map(o -> o);
      }

      // Then event
      return EventSearchParameter.lookupEventParam(name).map(v -> v);
    }
  }
}
