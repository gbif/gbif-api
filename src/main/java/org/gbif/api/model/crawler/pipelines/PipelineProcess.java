package org.gbif.api.model.crawler.pipelines;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Base POJO model for the Pipelines status service */
public class PipelineProcess implements Serializable {

  private static final long serialVersionUID = -3992826055732414678L;

  private long key;
  private UUID datasetKey;
  private int attempt;
  private String datasetTitle;
  private LocalDateTime created;
  private String createdBy;
  private Set<PipelineStep> steps = new HashSet<>();

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

  public int getAttempt() {
    return attempt;
  }

  public PipelineProcess setAttempt(int attempt) {
    this.attempt = attempt;
    return this;
  }

  public String getDatasetTitle() {
    return datasetTitle;
  }

  public PipelineProcess setDatasetTitle(String datasetTitle) {
    this.datasetTitle = datasetTitle;
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

  public Set<PipelineStep> getSteps() {
    return steps;
  }

  public void setSteps(Set<PipelineStep> steps) {
    this.steps = steps;
  }

  public void addStep(PipelineStep step) {
    steps.add(step);
  }
}
