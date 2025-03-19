package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeospatialCoverage {
  @JsonProperty("country_list")
  private Map<String, Object> countryList;

  @JsonProperty("geographic_areas")
  private Map<String, Map<String, Map<String, Object>>> geographicAreas;
}
