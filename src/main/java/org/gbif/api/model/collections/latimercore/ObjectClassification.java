package org.gbif.api.model.collections.latimercore;

import lombok.Data;

@Data
public class ObjectClassification {

  private String objectClassificationName;
  // TODO: make it an enum?
  private String objectClassificationLevel;

}
