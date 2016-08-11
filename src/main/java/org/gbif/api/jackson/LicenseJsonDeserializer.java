package org.gbif.api.jackson;

import org.gbif.api.vocabulary.License;

import java.io.IOException;

import com.google.common.base.Strings;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Jackson {@link JsonDeserializer} for {@link License}.
 * If the value is empty, License.UNSPECIFIED will be returned.
 * If the value can not be transformed into a License, License.UNSUPPORTED will be returned.
 */
public class LicenseJsonDeserializer extends JsonDeserializer<License> {

  @Override
  public License deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
      if(Strings.isNullOrEmpty(jp.getText())){
        return License.UNSPECIFIED;
      }
      return License.fromLicenseUrl(jp.getText()).or(License.UNSUPPORTED);
    }
    throw ctxt.mappingException("Expected String");
  }
}
