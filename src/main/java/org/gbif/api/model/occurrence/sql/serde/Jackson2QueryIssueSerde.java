package org.gbif.api.model.occurrence.sql.serde;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.gbif.api.model.occurrence.sql.Query;

import java.io.IOException;

public class Jackson2QueryIssueSerde extends JsonSerializer<Query.Issue> {

  @Override
  public void serialize(Query.Issue value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(String.format("%s: %s %s", value.name(), value.description(), value.comment()));
  }
}
