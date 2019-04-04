package org.gbif.api.model.occurrence.sql.serde;

import org.gbif.api.model.occurrence.sql.Query;
import org.gbif.api.model.occurrence.sql.Query.Issue;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Json serializer, to use issue name with description and comment upon serialization.
 */
public class QueryIssueSerde extends JsonSerializer<Query.Issue> {

  @Override
  public void serialize(Issue value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(String.format("%s: %s %s", value.name(), value.description(), value.comment()));
  }
}
