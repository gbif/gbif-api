package org.gbif.api.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usage {
  private String key;
  private String name;
  private String rank;
  private String code;
  private String authorship;
  private String genericName;
  private String infragenericEpithet;
  private String specificEpithet;
  private String infraspecificEpithet;
  private String formattedName;
}
