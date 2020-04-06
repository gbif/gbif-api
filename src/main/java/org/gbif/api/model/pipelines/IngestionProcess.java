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

import org.gbif.api.annotation.Generated;
import org.gbif.api.model.crawler.DatasetProcessStatus;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class IngestionProcess {

  private UUID datasetKey;
  private String datasetTitle;
  private int attempt;
  private DatasetProcessStatus crawlInfo;
  private Set<PipelineExecution> pipelineExecutions;

  public UUID getDatasetKey() {
    return datasetKey;
  }

  public IngestionProcess setDatasetKey(UUID datasetKey) {
    this.datasetKey = datasetKey;
    return this;
  }

  public String getDatasetTitle() {
    return datasetTitle;
  }

  public IngestionProcess setDatasetTitle(String datasetTitle) {
    this.datasetTitle = datasetTitle;
    return this;
  }

  public int getAttempt() {
    return attempt;
  }

  public IngestionProcess setAttempt(int attempt) {
    this.attempt = attempt;
    return this;
  }

  public DatasetProcessStatus getCrawlInfo() {
    return crawlInfo;
  }

  public IngestionProcess setCrawlInfo(DatasetProcessStatus crawlInfo) {
    this.crawlInfo = crawlInfo;
    return this;
  }

  public Set<PipelineExecution> getPipelineExecutions() {
    return pipelineExecutions;
  }

  public IngestionProcess setPipelineExecutions(Set<PipelineExecution> pipelineExecutions) {
    this.pipelineExecutions = pipelineExecutions;
    return this;
  }

  @Generated
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IngestionProcess that = (IngestionProcess) o;
    return attempt == that.attempt && Objects.equals(datasetKey, that.datasetKey);
  }

  @Generated
  @Override
  public int hashCode() {
    return Objects.hash(datasetKey, attempt);
  }
}
