package org.gbif.api.jackson;

import org.gbif.api.vocabulary.License;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} for {@link License}.
 */
public class LicenseJsonSerializer extends JsonSerializer<License> {

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
