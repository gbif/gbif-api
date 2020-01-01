package org.gbif.api.model.occurrence.sql.serde;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.gbif.api.model.occurrence.sql.Query.Issue;

import java.io.IOException;

/**
 * Json serializer, to use issue name with description and comment upon serialization.
 */
public class QueryIssueSerde extends JsonSerializer<Issue> {

  @Override
  public void serialize(Issue value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(String.format("%s: %s %s", value.name(), value.description(), value.comment()));
  }
}
