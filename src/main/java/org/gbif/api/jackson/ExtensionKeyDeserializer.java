package org.gbif.api.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.gbif.api.vocabulary.Extension;

import java.io.IOException;

/**
 * Deserializer for {@link Extension} in key values.
 */
public class ExtensionKeyDeserializer extends KeyDeserializer {

  @Override
  public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
    return Extension.fromRowType(key);
  }
}
