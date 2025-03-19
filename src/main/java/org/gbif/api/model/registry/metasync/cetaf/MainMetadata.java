package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainMetadata {
  private Contact contact;
  private Description description;
  @JsonProperty("geographic_coverage")
  private Map<String, Object> geographicCoverage;
}

