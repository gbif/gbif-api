package org.gbif.api.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import org.gbif.api.v2.RankedName;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

  private String datasetKey;
  private RankedName usage;
  private RankedName acceptedUsage;
  private List<RankedName> classification;
  private String iucnRedListCategory;
  private List<String> issues;
}
