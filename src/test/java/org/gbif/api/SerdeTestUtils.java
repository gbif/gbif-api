package org.gbif.api;

import java.io.IOException;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import static org.junit.Assert.assertEquals;

public class SerdeTestUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
//    MAPPER.registerModule(new GuavaModule());
    MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MAPPER.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
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

  private SerdeTestUtils() {
    throw new UnsupportedOperationException("Can't initialize class");
  }

}
