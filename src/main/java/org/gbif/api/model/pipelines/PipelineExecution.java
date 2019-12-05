package org.gbif.api.model.pipelines;

import org.gbif.api.jackson.LocalDateTimeSerDe;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import static org.gbif.api.model.pipelines.PipelineStep.STEPS_BY_START_AND_FINISH_ASC;
import static org.gbif.api.model.pipelines.PipelineStep.Status.RUNNING;

/** Models a execution of a pipeline that can include one or more steps. */
public class PipelineExecution implements Serializable {

  private long key;
  private List<StepType> stepsToRun = new ArrayList<>();
  private String pipelinesVersion;
  private String rerunReason;
  private String remarks;

  @JsonSerialize(using = LocalDateTimeSerDe.LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeSerDe.LocalDateTimeDeserializer.class)
  private LocalDateTime created;

  private String createdBy;
  private Set<PipelineStep> steps = new TreeSet<>(STEPS_BY_START_AND_FINISH_ASC);

  /**
   * Comparator that sorts pipeline executions by the start date of their latest step in an
   * ascending order. The steps that are running have preference.
   */
  public static final Comparator<PipelineExecution> PIPELINE_EXECUTION_BY_LATEST_STEP_ASC =
      (p1, p2) -> {
        Optional<PipelineStep> lastStepOpt1 = Optional.empty();
        if (p1 != null && p1.getSteps() != null) {
          lastStepOpt1 =
              p1.getSteps().stream()
                  .filter(p -> p.getState() != null)
                  .max(Comparator.comparing(PipelineStep::getStarted));
        }

        Optional<PipelineStep> lastStepOpt2 = Optional.empty();
        if (p2 != null && p2.getSteps() != null) {
          lastStepOpt2 =
              p2.getSteps().stream()
                  .filter(p -> p.getState() != null)
                  .max(Comparator.comparing(PipelineStep::getStarted));
        }

        if (!lastStepOpt1.isPresent()) {
          return !lastStepOpt2.isPresent() ? 0 : 1;
        } else if (!lastStepOpt2.isPresent()) {
          return -1;
        }

        PipelineStep step1 = lastStepOpt1.get();
        PipelineStep step2 = lastStepOpt2.get();

        if (step1.getStarted() == null) {
          return step2.getStarted() == null ? 0 : 1;
        } else if (step2.getStarted() == null) {
          return -1;
        }

        // steps that are running have preference
        if (step1.getState() == RUNNING) {
          return step2.getState() == RUNNING ? step1.getStarted().compareTo(step2.getStarted()) : 1;
        } else if (step2.getState() == RUNNING) {
          return -1;
        } else {
          return step1.getStarted().compareTo(step2.getStarted());
        }
      };

  public long getKey() {
    return key;
  }

  public List<StepType> getStepsToRun() {
    return stepsToRun;
  }

  public PipelineExecution setStepsToRun(List<StepType> stepsToRun) {
    this.stepsToRun = stepsToRun;
    return this;
  }

  public String getPipelinesVersion() {
    return pipelinesVersion;
  }

  public PipelineExecution setPipelinesVersion(String pipelinesVersion) {
    this.pipelinesVersion = pipelinesVersion;
    return this;
  }

  public String getRerunReason() {
    return rerunReason;
  }

  public PipelineExecution setRerunReason(String rerunReason) {
    this.rerunReason = rerunReason;
    return this;
  }

  public String getRemarks() {
    return remarks;
  }

  public PipelineExecution setRemarks(String remarks) {
    this.remarks = remarks;
    return this;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public PipelineExecution setCreated(LocalDateTime created) {
    this.created = created;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public PipelineExecution setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public Set<PipelineStep> getSteps() {
    return steps;
  }

  public void setSteps(Set<PipelineStep> steps) {
    this.steps.clear();
    this.steps.addAll(steps);
  }

  public PipelineExecution addStep(PipelineStep step) {
    steps.add(step);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PipelineExecution that = (PipelineExecution) o;
    return key == that.key
        && Objects.equals(stepsToRun, that.stepsToRun)
        && Objects.equals(pipelinesVersion, that.pipelinesVersion)
        && Objects.equals(rerunReason, that.rerunReason)
        && Objects.equals(remarks, that.remarks)
        && Objects.equals(created, that.created)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(steps, that.steps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key, stepsToRun, pipelinesVersion, rerunReason, remarks, created, createdBy);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineExecution.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("stepsToRun=" + stepsToRun)
        .add("pipelinesVersion='" + pipelinesVersion + "'")
        .add("rerunReason='" + rerunReason + "'")
        .add("remarks='" + remarks + "'")
        .add("created=" + created)
        .add("createdBy='" + createdBy + "'")
        .add("steps='" + steps + "'")
        .toString();
  }
}
