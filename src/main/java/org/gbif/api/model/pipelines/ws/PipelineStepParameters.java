package org.gbif.api.model.pipelines.ws;

import org.gbif.api.model.pipelines.PipelineStep;

import java.util.List;

/** Class to send the parameters of a {@link org.gbif.api.model.pipelines.PipelineStep}. */
public class PipelineStepParameters {
  PipelineStep.Status status;
  List<PipelineStep.MetricInfo> metrics;

  public PipelineStepParameters() {
    // needed for jackson
  }

  public PipelineStepParameters(PipelineStep.Status status, List<PipelineStep.MetricInfo> metrics) {
    this.status = status;
    this.metrics = metrics;
  }

  // getters and setters needed for jackson

  public PipelineStep.Status getStatus() {
    return status;
  }

  public void setStatus(PipelineStep.Status status) {
    this.status = status;
  }

  public List<PipelineStep.MetricInfo> getMetrics() {
    return metrics;
  }

  public void setMetrics(List<PipelineStep.MetricInfo> metrics) {
    this.metrics = metrics;
  }
}
