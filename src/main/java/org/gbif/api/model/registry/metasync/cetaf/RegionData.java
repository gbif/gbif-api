package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionData {
  @JsonProperty("object_quantity")
  private ObjectQuantity objectQuantity;
  @JsonProperty("confidence_%")
  private Confidence confidence;
  @JsonProperty("terrestrial_object_quantity")
  private ObjectQuantity terrestrialObjectQuantity;
}

