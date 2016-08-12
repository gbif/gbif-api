package org.gbif.api.jackson;

import org.gbif.api.vocabulary.License;

import java.io.IOException;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

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
      if(value == null){
        jgen.writeNull();
        return;
      }

      if(value.getLicenseUrl() != null){
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
        if(Strings.isNullOrEmpty(jp.getText())){
          return License.UNSPECIFIED;
        }
        //first, try by url
        Optional<License> license = License.fromLicenseUrl(jp.getText());
        if(license.isPresent()){
          return license.get();
        }
        //then, try by name
        license = License.fromString(jp.getText());
        return license.or(License.UNSUPPORTED);
      }
      throw ctxt.mappingException("Expected String");
    }
  }

}
