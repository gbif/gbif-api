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

import java.io.Serializable;
import java.util.UUID;

/**
 * Class to send the parameters of a {@link org.gbif.api.model.pipelines.PipelineProcess}.
 */
public class PipelineProcessParameters implements Serializable {

  private UUID datasetKey;
  private int attempt;

  public PipelineProcessParameters() {
    // needed for Jackson
  }

  public PipelineProcessParameters(UUID datasetKey, int attempt) {
    this.datasetKey = datasetKey;
    this.attempt = attempt;
  }

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
}
