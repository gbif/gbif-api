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

import org.gbif.api.vocabulary.License;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} and Jackson {@link JsonDeserializer} classes for {@link License}.
 */
public class LicenseSerde {

  /**
   * Jackson {@link JsonSerializer} for {@link License}.
   */
  public static class LicenseJsonSerializer extends JsonSerializer<License> {

    @Override
    public void serialize(License value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      if (value == null) {
        jgen.writeNull();
        return;
      }

      if (value.getLicenseUrl() != null) {
        jgen.writeString(value.getLicenseUrl());
        return;
      }

      //in last resort (UNSPECIFIED,UNSUPPORTED), we use the enum name
      jgen.writeString(value.name().toLowerCase());
    }
  }

  /**
   * Jackson {@link JsonDeserializer} for {@link License}.
   * If the value is empty, License.UNSPECIFIED will be returned.
   * If the value can not be transformed into a License, License.UNSUPPORTED will be returned.
   * This deserializer a little bit lenient, names of licenses (in the License enum) also understood.
   */
  public static class LicenseJsonDeserializer extends JsonDeserializer<License> {
    @Override
    public License deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
        if (StringUtils.isEmpty(jp.getText())) {
          return License.UNSPECIFIED;
        }
        // first, try by url
        Optional<License> license = License.fromLicenseUrl(jp.getText());
        if (license.isPresent()) {
          return license.get();
        }

        // then, try by name
        return License.fromString(jp.getText()).orElse(License.UNSUPPORTED);
      }
      throw JsonMappingException.from(jp, "Expected String");
    }
  }
}
