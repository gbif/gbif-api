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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import org.gbif.api.jackson.OffsetDateTimeSerDe;
import org.gbif.api.model.registry.LenientEquals;

/** Models a step in pipelines. */
public class PipelineStep implements LenientEquals<PipelineStep>, Serializable {

  private static final long serialVersionUID = 460047082156621661L;

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  private long key;

  private StepType type;
  private StepRunner runner;

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime started;

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime finished;

  private Status state;
  private String message;
  private Long numberRecords;
  private String pipelinesVersion;
  private String createdBy;

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime modified;

  private String modifiedBy;
  private Set<MetricInfo> metrics = new HashSet<>();

  public static final Comparator<PipelineStep> STEPS_BY_TYPE_ASC =
      (s1, s2) -> {
        StepType st1 = s1 != null ? s1.getType() : null;
        StepType st2 = s2 != null ? s2.getType() : null;

        if (st1 == null) {
          return (st2 == null) ? 0 : 1;
        } else if (st2 == null) {
          return -1;
        }
        return st1.compareTo(st2);
      };

  /**
   * Comparator that sorts pipeline steps by start date and then by finished date in ascending
   * order.
   */
  public static final Comparator<PipelineStep> STEPS_BY_FINISHED_ASC =
      (s1, s2) -> {
        OffsetDateTime finished1 = s1 != null ? s1.getFinished() : null;
        OffsetDateTime finished2 = s2 != null ? s2.getFinished() : null;

        if (finished1 == null && finished2 == null) {
          return STEPS_BY_TYPE_ASC.compare(s1, s2);
        }

        if (finished1 == null) {
          return 1;
        } else if (finished2 == null) {
          return -1;
        }

        if (finished1.isEqual(finished2)) {
          return STEPS_BY_TYPE_ASC.compare(s1, s2);
        }

        return finished1.toInstant().compareTo(finished2.toInstant());
      };

  public long getKey() {
    return key;
  }

  public StepType getType() {
    return type;
  }

  public PipelineStep setType(StepType type) {
    this.type = type;
    return this;
  }

  public StepRunner getRunner() {
    return runner;
  }

  public PipelineStep setRunner(StepRunner runner) {
    this.runner = runner;
    return this;
  }

  public OffsetDateTime getStarted() {
    return started;
  }

  public PipelineStep setStarted(OffsetDateTime started) {
    this.started = started;
    return this;
  }

  public OffsetDateTime getFinished() {
    return finished;
  }

  public PipelineStep setFinished(OffsetDateTime finished) {
    this.finished = finished;
    return this;
  }

  public Status getState() {
    return state;
  }

  public PipelineStep setState(Status state) {
    this.state = state;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public PipelineStep setMessage(String message) {
    this.message = message;
    return this;
  }

  public PipelineStep setKey(long key) {
    this.key = key;
    return this;
  }

  public Long getNumberRecords() {
    return numberRecords;
  }

  public PipelineStep setNumberRecords(Long numberRecords) {
    this.numberRecords = numberRecords;
    return this;
  }

  public String getPipelinesVersion() {
    return pipelinesVersion;
  }

  public PipelineStep setPipelinesVersion(String pipelinesVersion) {
    this.pipelinesVersion = pipelinesVersion;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public PipelineStep setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public OffsetDateTime getModified() {
    return modified;
  }

  public PipelineStep setModified(OffsetDateTime modified) {
    this.modified = modified;
    return this;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public PipelineStep setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
    return this;
  }

  public Set<MetricInfo> getMetrics() {
    return metrics;
  }

  public PipelineStep setMetrics(Set<MetricInfo> metrics) {
    this.metrics = metrics;
    return this;
  }

  public PipelineStep addMetricInfo(MetricInfo metricInfo) {
    metrics.add(metricInfo);
    return this;
  }

  /** Enum to represent the status of a step. */
  public enum Status {
    RUNNING,
    FAILED,
    SUBMITTED,
    COMPLETED,
    ABORTED,
    QUEUED
  }

  /** Inner class to store metrics. */
  public static class MetricInfo implements Serializable {

    private static final long serialVersionUID = 1872427841009786709L;

    private String name;
    private String value;

    public MetricInfo() {
      // needed for jackson
    }

    public MetricInfo(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public String getValue() {
      return value;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setValue(String value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      MetricInfo that = (MetricInfo) o;
      return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, value);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", MetricInfo.class.getSimpleName() + "[", "]")
          .add("name='" + name + "'")
          .add("value='" + value + "'")
          .toString();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PipelineStep that = (PipelineStep) o;
    return key == that.key
        && Objects.equals(type, that.type)
        && Objects.equals(runner, that.runner)
        && Objects.equals(started, that.started)
        && Objects.equals(finished, that.finished)
        && state == that.state
        && Objects.equals(message, that.message)
        && Objects.equals(metrics, that.metrics)
        && Objects.equals(numberRecords, that.numberRecords)
        && Objects.equals(pipelinesVersion, that.pipelinesVersion)
        && Objects.equals(createdBy, that.createdBy)
        && Objects.equals(modified, that.modified)
        && Objects.equals(modifiedBy, that.modifiedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        key,
        type,
        runner,
        started,
        finished,
        state,
        message,
        metrics,
        numberRecords,
        pipelinesVersion,
        createdBy,
        modified,
        modifiedBy);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineStep.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("type=" + type)
        .add("runner=" + runner)
        .add("started=" + started)
        .add("finished=" + finished)
        .add("state=" + state)
        .add("message='" + message + "'")
        .add("numberRecords='" + numberRecords + "'")
        .add("pipelinesVersion='" + pipelinesVersion + "'")
        .add("createdBy='" + createdBy + "'")
        .add("modified=" + modified)
        .add("modifiedBy='" + modifiedBy + "'")
        .add("metrics=" + metrics)
        .toString();
  }

  @Override
  public boolean lenientEquals(PipelineStep other) {
    if (this == other) return true;
    return Objects.equals(type, other.type)
        && Objects.equals(runner, other.runner)
        && finished.isEqual(other.finished)
        && state == other.state
        && Objects.equals(message, other.message)
        && Objects.equals(metrics, other.metrics)
        && Objects.equals(numberRecords, other.numberRecords)
        && Objects.equals(pipelinesVersion, other.pipelinesVersion);
  }
}
