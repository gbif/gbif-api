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
package org.gbif.api.model.pipelines.ws;

import java.time.OffsetDateTime;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.gbif.api.jackson.OffsetDateTimeSerDe;

/** Models a flat response of a pipeline process search. */
public class SearchResult implements Serializable {

  private UUID datasetKey;
  private int attempt;
  private int executionKey;
  private String rerunReason;
  private String stepType;
  private String stepState;
  private String pipelinesVersion;

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime stepStarted;

  @JsonSerialize(using = OffsetDateTimeSerDe.OffsetDateTimeSerializer.class)
  @JsonDeserialize(using = OffsetDateTimeSerDe.OffsetDateTimeDeserializer.class)
  private OffsetDateTime stepFinished;

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public void setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
  }

  public int getAttempt() {
    return attempt;
  }

  public void setAttempt(int attempt) {
    this.attempt = attempt;
  }

  public int getExecutionKey() {
    return executionKey;
  }

  public void setExecutionKey(int executionKey) {
    this.executionKey = executionKey;
  }

  public String getRerunReason() {
    return rerunReason;
  }

  public void setRerunReason(String rerunReason) {
    this.rerunReason = rerunReason;
  }

  public String getStepType() {
    return stepType;
  }

  public void setStepType(String stepType) {
    this.stepType = stepType;
  }

  public String getStepState() {
    return stepState;
  }

  public void setStepState(String stepState) {
    this.stepState = stepState;
  }

  public String getPipelinesVersion() {
    return pipelinesVersion;
  }

  public void setPipelinesVersion(String pipelinesVersion) {
    this.pipelinesVersion = pipelinesVersion;
  }

  public OffsetDateTime getStepStarted() {
    return stepStarted;
  }

  public void setStepStarted(OffsetDateTime stepStarted) {
    this.stepStarted = stepStarted;
  }

  public OffsetDateTime getStepFinished() {
    return stepFinished;
  }

  public void setStepFinished(OffsetDateTime stepFinished) {
    this.stepFinished = stepFinished;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResult that = (SearchResult) o;
    return attempt == that.attempt
        && executionKey == that.executionKey
        && Objects.equals(datasetKey, that.datasetKey)
        && Objects.equals(rerunReason, that.rerunReason)
        && Objects.equals(stepType, that.stepType)
        && Objects.equals(stepState, that.stepState)
        && Objects.equals(pipelinesVersion, that.pipelinesVersion)
        && Objects.equals(stepStarted, that.stepStarted)
        && Objects.equals(stepFinished, that.stepFinished);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        datasetKey,
        attempt,
        executionKey,
        rerunReason,
        stepType,
        stepState,
        pipelinesVersion,
        stepStarted,
        stepFinished);
  }
}
