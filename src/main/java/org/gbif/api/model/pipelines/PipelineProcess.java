package org.gbif.api.model.pipelines;

import org.gbif.api.jackson.LocalDateTimeSerDe;
import org.gbif.common.shaded.com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/** Base POJO model for the Pipelines status service */
public class PipelineProcess implements Serializable {

  private static final long serialVersionUID = -3992826055732414678L;

  public static final Comparator<PipelineStep> STEPS_COMPARATOR =
      (s1, s2) -> {
        LocalDateTime started1 = s1 != null ? s1.getStarted() : null;
        LocalDateTime started2 = s2 != null ? s2.getStarted() : null;

        if (started1 == null) {
          return (started2 == null) ? 0 : 1;
        } else if (started2 == null) {
          return -1;
        } else {
          int comparison = started1.compareTo(started2);
          if (comparison != 0) {
            return comparison;
          } else {
            LocalDateTime finished1 = s1.getFinished();
            LocalDateTime finished2 = s2.getFinished();
            if (finished1 == null) {
              return (finished2 == null) ? 0 : 1;
            } else if (finished2 == null) {
              return -1;
            } else {
              return finished1.compareTo(finished2);
            }
          }
        }
      };

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
  private Set<PipelineStep> steps = new TreeSet<>(STEPS_COMPARATOR);

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
