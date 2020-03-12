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
package org.gbif.api;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.junit.Assert.assertEquals;

public class SerdeTestUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // copied from common-ws JacksonJsonContextResolver to provide the same mapper configs
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    // extra for better debugging
    MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
  }

  /**
   * Does a roundtrip from object to JSON and back to another object and then compares the 2 instances
   * and their hashcodes.
   *
   * @return JSON string of the serialized object
   */
  public static <T> String testSerDe(T obj, Class<T> objClass) throws IOException {
    String json = MAPPER.writeValueAsString(obj);
    T obj2 = MAPPER.readValue(json, objClass);
    assertEquals(obj2, obj);
    assertEquals(obj.hashCode(), obj2.hashCode());
    return json;
  }

  public static String serialize(Object obj) throws IOException {
    return MAPPER.writeValueAsString(obj);
  }

  public static <T> T deserialize(String json, Class<T> objClass) throws IOException {
    return MAPPER.readValue(json, objClass);
  }

  private SerdeTestUtils() {
    throw new UnsupportedOperationException("Can't initialize class");
  }

}
