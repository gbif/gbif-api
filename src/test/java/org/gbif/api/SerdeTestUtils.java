package org.gbif.api;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SerdeTestUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // copied from common-ws JacksonJsonContextResolver to provide the same mapper configs
    MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    MAPPER.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    // extra for better debugging
    MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
  }

  /**
   * Does a roundtrip from object to JSON and back to another object and then compares the 2 instances
   * and their hashcodes.
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
