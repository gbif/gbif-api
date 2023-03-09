/*
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

import org.gbif.api.jackson.LocalDateTimeSerDe;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.gbif.api.model.pipelines.PipelineStep.STEPS_BY_FINISHED_ASC;


/**
 * Models an execution of a pipeline that can include one or more steps.
 */
public class PipelineExecution implements Serializable {

  private long key;
  private Set<StepType> stepsToRun = new HashSet<>();
  private String rerunReason;
  private String remarks;

  @JsonSerialize(using = LocalDateTimeSerDe.LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeSerDe.LocalDateTimeDeserializer.class)
  private LocalDateTime created;

  private String createdBy;
  private Set<PipelineStep> steps = new TreeSet<>(STEPS_BY_FINISHED_ASC);
  private boolean finished;

  public long getKey() {
    return key;
  }

  public PipelineExecution setKey(long key) {
    this.key = key;
    return this;
  }

  public Set<StepType> getStepsToRun() {
    return stepsToRun;
  }

  public PipelineExecution setStepsToRun(Set<StepType> stepsToRun) {
    this.stepsToRun = stepsToRun;
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

  public PipelineExecution setSteps(Set<PipelineStep> steps) {
    this.steps.clear();
    this.steps.addAll(steps);
    return this;
  }

  public PipelineExecution addStep(PipelineStep step) {
    steps.add(step);
    return this;
  }

  public boolean isFinished() {
    return finished;
  }

  public PipelineExecution setFinished(boolean finished) {
    this.finished = finished;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PipelineExecution that = (PipelineExecution) o;
    return key == that.key
      && Objects.equals(stepsToRun, that.stepsToRun)
      && Objects.equals(rerunReason, that.rerunReason)
      && Objects.equals(remarks, that.remarks)
      && Objects.equals(created, that.created)
      && Objects.equals(createdBy, that.createdBy)
      && Objects.equals(steps, that.steps)
      && Objects.equals(finished, that.finished);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, stepsToRun, rerunReason, remarks, created, createdBy, finished);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineExecution.class.getSimpleName() + "[", "]")
      .add("key=" + key)
      .add("stepsToRun=" + stepsToRun)
      .add("rerunReason='" + rerunReason + "'")
      .add("remarks='" + remarks + "'")
      .add("created=" + created)
      .add("createdBy='" + createdBy + "'")
      .add("steps='" + steps + "'")
      .add("finished='" + finished + "'")
      .toString();
  }
}
