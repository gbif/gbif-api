package org.gbif.api.model.pipelines;

/** Enum to represent the pipelines step names. */
public enum StepType {

  //Generic step name used to be a replace of the following three when client doesn't know the endpoint type of a dataset
  TO_VERBATIM("toVerbatim", 1),
  DWCA_TO_VERBATIM("dwcaToVerbatim", 1),
  XML_TO_VERBATIM("xmlToVerbatim", 1),
  ABCD_TO_VERBATIM("abcdToVerbatim", 1),
  VERBATIM_TO_INTERPRETED("verbatimToInterpreted", 2),
  INTERPRETED_TO_INDEX("interpretedToIndex", 3),
  HDFS_VIEW("hdfsView", 3);

  private String label;
  private int executionOrder;

  StepType(String label, int executionOrder) {
    this.label = label;
    this.executionOrder = executionOrder;
  }

  public int getExecutionOrder() {
    return executionOrder;
  }

  public String getLabel() {
    return label;
  }
}
