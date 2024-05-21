package org.gbif.api.model.collections.latimercore;

import lombok.Data;

@Data
public class MeasurementOrFact {

  private String measurementFactText;
  private String measurementValue;
  private String measurementType;
}
