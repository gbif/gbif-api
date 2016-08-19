package org.gbif.api.jackson;

import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.KeyDeserializer;


/**
 * Deserializer for {@link Extension} in key values.
 */
public class ExtensionKeyDeserializer extends KeyDeserializer {

  @Override
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    return Extension.fromRowType(key);
  }
}
