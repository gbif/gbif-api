package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionData {
  @JsonProperty("main_metadata")
  private MainMetadata mainMetadata;
  @JsonProperty("collection_data")
  private CollectionDetails collectionData;
}

