package org.gbif.api.model.crawler.pipelines;

import org.gbif.api.model.registry.LenientEquals;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/** Models a step in pipelines. */
public class PipelineStep implements LenientEquals<PipelineStep>, Serializable {

  private static final long serialVersionUID = 460047082156621661L;

  private long key;
  private StepType type;
  private StepRunner runner;
  private LocalDateTime started;
  private LocalDateTime finished;
  private Status state;
  private String message;
  private String rerunReason;
  private String createdBy;
  private LocalDateTime modified;
  private String modifiedBy;
  private Set<MetricInfo> metrics = new HashSet<>();

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

  public LocalDateTime getStarted() {
    return started;
  }

  public PipelineStep setStarted(LocalDateTime started) {
    this.started = started;
    return this;
  }

  public LocalDateTime getFinished() {
    return finished;
  }

  public PipelineStep setFinished(LocalDateTime finished) {
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

  public String getRerunReason() {
    return rerunReason;
  }

  public PipelineStep setRerunReason(String rerunReason) {
    this.rerunReason = rerunReason;
    return this;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public PipelineStep setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public PipelineStep setModified(LocalDateTime modified) {
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

  /** Enum to represent the status of a step. */
  public enum Status {
    RUNNING,
    FAILED,
    COMPLETED
  }

  /** Inner class to store metrics. */
  public static class MetricInfo implements Serializable {

    private static final long serialVersionUID = 1872427841009786709L;

    private String name;
    private String value;

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

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      MetricInfo that = (MetricInfo) o;
      return name.equals(that.name) && value.equals(that.value);
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
        && Objects.equals(rerunReason, that.rerunReason)
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
        rerunReason,
        createdBy,
        modified,
        modifiedBy);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PipelineStep.class.getSimpleName() + "[", "]").add("key=" + key)
      .add("type=" + type)
      .add("runner=" + runner)
      .add("started=" + started)
      .add("finished=" + finished)
      .add("state=" + state)
      .add("message='" + message + "'")
      .add("rerunReason='" + rerunReason + "'")
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
        && Objects.equals(finished, other.finished)
        && state == other.state
        && Objects.equals(message, other.message)
        && Objects.equals(metrics, other.metrics)
        && Objects.equals(rerunReason, other.rerunReason);
  }
}
