/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
