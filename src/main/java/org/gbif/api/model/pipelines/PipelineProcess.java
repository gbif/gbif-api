package org.gbif.api.model.pipelines;

import org.gbif.api.jackson.LocalDateTimeSerDe;
import org.gbif.common.shaded.com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import static org.gbif.api.model.pipelines.PipelineStep.STEPS_BY_START_AND_FINISH_ASC;

/** Base POJO model for the Pipelines status service */
public class PipelineProcess implements Serializable {

  private static final long serialVersionUID = -3992826055732414678L;

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  private long key;

  private UUID datasetKey;
  private String datasetTitle;
  private int attempt;
  private long numberRecords;

  @JsonSerialize(using = LocalDateTimeSerDe.LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeSerDe.LocalDateTimeDeserializer.class)
  private LocalDateTime created;

  private String createdBy;
  private Set<PipelineStep> steps = new TreeSet<>(STEPS_BY_START_AND_FINISH_ASC);

  /**
   * Comparator that sorts pipeline processes by the start of their latest step in a descending order.
   */
  public static final Comparator<PipelineProcess> PIPELINE_PROCESS_BY_LATEST_STEP_DESC =
      (p1, p2) -> {
        LocalDateTime lastStepStarted1 = LocalDateTime.MIN;
        if (p1 != null && p1.getSteps() != null) {
          lastStepStarted1 =
              p1.getSteps().stream()
                  .max(Comparator.comparing(PipelineStep::getStarted))
                  .map(PipelineStep::getStarted)
                  .orElse(LocalDateTime.MIN);
        }

        LocalDateTime lastStepStarted2 = LocalDateTime.MIN;
        if (p2 != null && p2.getSteps() != null) {
          lastStepStarted2 =
              p2.getSteps().stream()
                  .max(Comparator.comparing(PipelineStep::getStarted))
                  .map(PipelineStep::getStarted)
                  .orElse(LocalDateTime.MIN);
        }

        return lastStepStarted1.compareTo(lastStepStarted2);
      };

  public long getKey() {
    return key;
  }

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public PipelineProcess setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
    return this;
  }

  public String getDatasetTitle() {
    return datasetTitle;
  }

  public void setDatasetTitle(String datasetTitle) {
    this.datasetTitle = datasetTitle;
  }

  public int getAttempt() {
    return attempt;
  }

  public PipelineProcess setAttempt(int attempt) {
    this.attempt = attempt;
    return this;
  }

  public long getNumberRecords() {
    return numberRecords;
  }

  public void setNumberRecords(long numberRecords) {
    this.numberRecords = numberRecords;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public PipelineProcess setCreated(LocalDateTime created) {
    this.created = created;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public PipelineProcess setCreatedBy(String createdBy) {
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

  public void addStep(PipelineStep step) {
    steps.add(step);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineProcess.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("datasetKey=" + datasetKey)
        .add("datasetTitle=" + datasetTitle)
        .add("attempt=" + attempt)
        .add("numberRecords=" + numberRecords)
        .add("created=" + created)
        .add("createdBy='" + createdBy + "'")
        .add("steps=" + steps)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PipelineProcess process = (PipelineProcess) o;
    return attempt == process.attempt && Objects.equals(datasetKey, process.datasetKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, attempt);
  }
}
