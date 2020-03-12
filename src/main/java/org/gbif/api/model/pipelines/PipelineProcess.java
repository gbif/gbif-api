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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gbif.api.jackson.LocalDateTimeSerDe;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.UUID;

import static org.gbif.api.model.pipelines.PipelineExecution.PIPELINE_EXECUTION_BY_CREATED_ASC;
import static org.gbif.api.model.pipelines.PipelineStep.Status.RUNNING;

/**
 * Models a pipeline process for a specific dataset and attempt.
 */
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
    new TreeSet<>(PIPELINE_EXECUTION_BY_CREATED_ASC.reversed());

  /**
   * Comparator that sorts the pipeline processes by the created date of the latest execution.
   */
  public static final Comparator<PipelineProcess> PIPELINE_PROCESS_BY_LATEST_EXEUCTION_ASC =
    (p1, p2) -> {
      if (p1 == null) {
        return p2 == null ? 0 : 1;
      } else if (p2 == null) {
        return -1;
      }

      PipelineExecution latestExecution1 =
        p1.getExecutions().isEmpty() ? null : p1.getExecutions().iterator().next();
      PipelineExecution latestExecution2 =
        p2.getExecutions().isEmpty() ? null : p2.getExecutions().iterator().next();

      return Objects.compare(
        latestExecution1, latestExecution2, PIPELINE_EXECUTION_BY_CREATED_ASC);
    };

  /**
   * Comparator that sorts pipeline processes by the start date of their latest step in an ascending
   * order. The steps that are running have preference and we take into account only the steps of
   * the latest execution.
   */
  public static final Comparator<PipelineProcess> PIPELINE_PROCESS_BY_LATEST_STEP_RUNNING_ASC =
    (p1, p2) -> {
      if (p1 == null) {
        return p2 == null ? 0 : 1;
      } else if (p2 == null) {
        return -1;
      }

      PipelineExecution latestExecution1 =
        p1.getExecutions().isEmpty() ? null : p1.getExecutions().iterator().next();
      PipelineExecution latestExecution2 =
        p2.getExecutions().isEmpty() ? null : p2.getExecutions().iterator().next();

      Optional<PipelineStep> lastStepOpt1 = Optional.empty();
      if (latestExecution1 != null && latestExecution1.getSteps() != null) {
        lastStepOpt1 =
          latestExecution1.getSteps().stream()
            .filter(p -> p.getState() != null)
            .max(Comparator.comparing(PipelineStep::getStarted));
      }

      Optional<PipelineStep> lastStepOpt2 = Optional.empty();
      if (latestExecution2 != null && latestExecution2.getSteps() != null) {
        lastStepOpt2 =
          latestExecution2.getSteps().stream()
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
