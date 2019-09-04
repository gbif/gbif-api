package org.gbif.api.model.pipelines;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Models the workflow of a pipeline. It includes all the steps that were run and the execution
 * order.
 */
public class PipelineWorkflow implements Serializable {

  private UUID datasetKey;
  private int attempt;
  private List<WorkflowStep> steps;

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public int getAttempt() {
    return attempt;
  }

  public void setAttempt(int attempt) {
    this.attempt = attempt;
  }

  public List<WorkflowStep> getSteps() {
    return steps;
  }

  public void setSteps(List<WorkflowStep> steps) {
    this.steps = steps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PipelineWorkflow that = (PipelineWorkflow) o;
    return attempt == that.attempt && Objects.equals(datasetKey, that.datasetKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, attempt);
  }
}
