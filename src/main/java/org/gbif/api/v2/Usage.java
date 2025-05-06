package org.gbif.api.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usage {
  private String key;
  private String name;
  private String rank;
  private String authorship;
  private String infragenericEpithet;
  private String specificEpithet;
  private String infraspecificEpithet;
}
