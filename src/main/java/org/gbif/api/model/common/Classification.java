package org.gbif.api.model.common;

import lombok.Data;

import org.gbif.api.v2.RankedName;

import java.util.List;

@Data
public class Classification {

  String datasetKey;
  RankedName usage;
  RankedName acceptedUsage;
  List<RankedName> classification;
}
