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

import java.io.Serializable;
import java.util.*;

/**
 * Models a step in a Pipeline workflow.
 *
 * <p>These steps are identified by the {@link StepType} and are linked to their next steps in the
 * workflow.
 */
public class WorkflowStep implements Serializable {

  private StepType stepType;

  private PipelineStep lastStep;

  private Set<PipelineStep> allSteps =
      new TreeSet<>(Comparator.comparing(PipelineStep::getStarted).reversed());

  private List<WorkflowStep> nextSteps = new ArrayList<>();

  public StepType getStepType() {
    return stepType;
  }

  public void setStepType(StepType stepType) {
    this.stepType = stepType;
  }

  public PipelineStep getLastStep() {
    return lastStep;
  }

  public void setLastStep(PipelineStep lastStep) {
    this.lastStep = lastStep;
  }

  public Set<PipelineStep> getAllSteps() {
    return allSteps;
  }

  public void setAllSteps(Set<PipelineStep> allSteps) {
    this.allSteps = allSteps;
  }

  public List<WorkflowStep> getNextSteps() {
    return nextSteps;
  }

  public void setNextSteps(List<WorkflowStep> nextSteps) {
    this.nextSteps = nextSteps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkflowStep that = (WorkflowStep) o;
    return stepType == that.stepType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stepType);
  }
}
