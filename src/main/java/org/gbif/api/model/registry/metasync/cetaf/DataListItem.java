package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataListItem {
  private CollectionData data;
  private String source;
  @JsonProperty("source_url")
  private Map<String, String> sourceUrl;
  @JsonProperty("modification_date")
  private String modificationDate;
}

