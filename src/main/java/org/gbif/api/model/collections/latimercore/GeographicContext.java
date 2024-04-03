package org.gbif.api.model.collections.latimercore;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GeographicContext {

  private List<MeasurementOrFact> hasMeasurementOrFact = new ArrayList<>();
}
