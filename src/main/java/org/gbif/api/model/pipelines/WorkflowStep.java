package org.gbif.api.model.pipelines;

import java.io.Serializable;
import java.util.*;

/**
 * Models a step in a {@link PipelineWorkflow}.
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
