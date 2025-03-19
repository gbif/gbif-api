package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionDetails {
  private Storage storage;
  @JsonProperty("geospatial_coverage")
  private GeospatialCoverage geospatialCoverage;
}

