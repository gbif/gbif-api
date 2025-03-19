package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {
  private String abstractText;
}

