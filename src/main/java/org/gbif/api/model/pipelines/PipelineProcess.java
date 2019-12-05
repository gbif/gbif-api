package org.gbif.api.model.pipelines;

import org.gbif.api.jackson.LocalDateTimeSerDe;
import org.gbif.common.shaded.com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import static org.gbif.api.model.pipelines.PipelineExecution.PIPELINE_EXECUTION_BY_LATEST_STEP_ASC;

/** Models a pipeline process for a specific dataset and attempt. */
public class PipelineProcess implements Serializable {

  private static final long serialVersionUID = -3992826055732414678L;

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  private long key;

  private UUID datasetKey;
  private String datasetTitle;
  private int attempt;

  @JsonSerialize(using = LocalDateTimeSerDe.LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeSerDe.LocalDateTimeDeserializer.class)
  private LocalDateTime created;

  private String createdBy;
  private Set<PipelineExecution> executions =
      new TreeSet<>(PIPELINE_EXECUTION_BY_LATEST_STEP_ASC.reversed());

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

  public Set<PipelineExecution> getExecutions() {
    return executions;
  }

  public void setExecutions(Set<PipelineExecution> executions) {
    this.executions.clear();
    this.executions.addAll(executions);
  }

  public PipelineProcess addExecution(PipelineExecution execution) {
    executions.add(execution);
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineProcess.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("datasetKey=" + datasetKey)
        .add("datasetTitle=" + datasetTitle)
        .add("attempt=" + attempt)
        .add("created=" + created)
        .add("createdBy='" + createdBy + "'")
        .add("executions=" + executions)
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
