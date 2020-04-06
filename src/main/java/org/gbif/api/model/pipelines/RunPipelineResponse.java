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
package org.gbif.api.model.pipelines;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Encapsulates the possible response of the request of re-execute a pipeline of dataset and
 * attempt.
 */
public class RunPipelineResponse {

  /** Possible response statuses. */
  public enum ResponseStatus {
    OK,
    PIPELINE_IN_SUBMITTED,
    UNSUPPORTED_STEP,
    ERROR
  }

  private final ResponseStatus responseStatus;

  private final Set<StepType> steps;

  private final Set<StepType> stepsFailed;

  private final String message;

  public RunPipelineResponse(
    ResponseStatus responseStatus,
    Set<StepType> steps,
    Set<StepType> stepsFailed,
    String message) {
    this.responseStatus = responseStatus;
    this.steps = steps;
    this.message = message;
    this.stepsFailed = stepsFailed;
  }

  /** @return the response status of execution request */
  public ResponseStatus getResponseStatus() {
    return responseStatus;
  }

  /** @return steps requested to be executed */
  public Set<StepType> getSteps() {
    return steps;
  }

  public String getMessage() {
    return message;
  }

  public Set<StepType> getStepsFailed() {
    return stepsFailed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RunPipelineResponse that = (RunPipelineResponse) o;
    return responseStatus == that.responseStatus
      && steps.equals(that.steps)
      && stepsFailed.equals(that.stepsFailed)
      && message.equals(that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatus, steps, stepsFailed, message);
  }

  /** @return a new Builder instance. */
  public static Builder builder() {
    return new Builder();
  }

  /** @return a new Builder instance. */
  public static Builder builder(RunPipelineResponse runPipelineResponse) {
    Builder builder = new Builder();
    return builder
      .setSteps(runPipelineResponse.steps)
      .setResponseStatus(runPipelineResponse.responseStatus);
  }

  /** Builder for {@link RunPipelineResponse}. */
  public static class Builder {

    private ResponseStatus responseStatus;
    private Set<StepType> steps;
    private Set<StepType> stepsFailed;
    private String message;

    public Builder setResponseStatus(ResponseStatus responseStatus) {
      this.responseStatus = responseStatus;
      return this;
    }

    public Builder setSteps(Set<StepType> steps) {
      this.steps = steps;
      return this;
    }

    public Builder addStep(StepType step) {
      if (this.steps == null) {
        this.steps = new HashSet<>();
      }
      this.steps.add(step);
      return this;
    }

    public Builder setStepsFailed(Set<StepType> stepsFailed) {
      this.stepsFailed = stepsFailed;
      return this;
    }

    public Builder addStepFailed(StepType stepFailed) {
      if (this.stepsFailed == null) {
        this.stepsFailed = new HashSet<>();
      }
      this.stepsFailed.add(stepFailed);
      return this;
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public RunPipelineResponse build() {
      return new RunPipelineResponse(responseStatus, steps, stepsFailed, message);
    }
  }
}
