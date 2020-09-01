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
package org.gbif.api.service.pipelines;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.pipelines.PipelineExecution;
import org.gbif.api.model.pipelines.PipelineProcess;
import org.gbif.api.model.pipelines.PipelineStep;
import org.gbif.api.model.pipelines.RunPipelineResponse;
import org.gbif.api.model.pipelines.ws.PipelineProcessParameters;
import org.gbif.api.model.pipelines.ws.PipelineStepParameters;
import org.gbif.api.model.pipelines.ws.RunAllParams;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface PipelinesHistoryService {

  String STEPS_REQUIRED_MESSAGE = "Steps parameter is required";
  String REASON_REQUIRED_MESSAGE = "Reason parameter is required";

  /**
   * Lists the history of all {@link PipelineProcess}, sorted descending from the most recent one.
   *
   * @param pageable paging request
   * @return a paged response that contains a list of {@link PipelineProcess}.
   */
  PagingResponse<PipelineProcess> history(Pageable pageable);

  /**
   * Lists the history of all {@link PipelineProcess} of a dataset, sorted descending from the most
   * recent one.
   *
   * @param datasetKey dataset identifier
   * @param pageable paging request
   * @return a paged response that contains a list of {@link PipelineProcess}.
   */
  PagingResponse<PipelineProcess> history(@NotNull UUID datasetKey, Pageable pageable);

  /**
   * Gets the PipelineProcess identified by the dataset and attempt identifiers.
   *
   * @param datasetKey dataset identifier
   * @param attempt crawl attempt identifier
   * @return a instance of pipelines process if exists.
   */
  PipelineProcess getPipelineProcess(@NotNull UUID datasetKey, int attempt);

  /**
   * Creates/persists a pipelines process of dataset for an attempt identifier. If the process
   * already exists it returns the existing one.
   *
   * @param params pipeline process parameters, contain dataset key and attempt
   * @return the key of the {@link PipelineProcess} created.
   */
  long createPipelineProcess(@NotNull PipelineProcessParameters params);

  /**
   * Adds/persists the information of a pipeline execution.
   *
   * @param processKey sequential identifier of a pipeline process
   * @param pipelineExecution pipeline execution data
   * @return the key of the PipelineExecution created.
   */
  long addPipelineExecution(long processKey, @NotNull PipelineExecution pipelineExecution);

  /**
   * Adds/persists the information of a pipeline step.
   *
   * @param processKey sequential identifier of a pipeline process
   * @param executionKey key of the pipeline execution
   * @param pipelineStep step to be added
   * @return the key of the PipelineStep created.
   */
  long addPipelineStep(long processKey, long executionKey, @NotNull PipelineStep pipelineStep);

  /**
   * Gets the PipelineStep of the specified keys.
   *
   * @param processKey key of the pipeline process
   * @param executionKey key of the pipeline execution
   * @param stepKey key of the pipeline step
   * @return {@link PipelineStep}.
   */
  PipelineStep getPipelineStep(long processKey, long executionKey, long stepKey);

  /**
   * Updates the status of a pipeline step and retrieves the metrics from ES and inserts them in the
   * DB.
   *
   * @param processKey key of the process of the step
   * @param executionKey key of the execution
   * @param stepKey sequential identifier of a pipeline process step
   * @param stepParams pipeline step parameters.
   */
  void updatePipelineStepStatusAndMetrics(long processKey, long executionKey, long stepKey,
    @NotNull PipelineStepParameters stepParams);

  /**
   * Runs the last attempt for all datasets.
   *
   * @param steps steps to run
   * @param reason reason to run
   * @param useLastSuccessful true if we want to run the latest successful attempt
   * @param runAllParams parameters, contain datasets to exclude
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runAll(
    @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
    @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason,
    boolean useLastSuccessful,
    @Nullable RunAllParams runAllParams);

  /**
   * Restart last failed pipelines step for a dataset.
   *
   * @param datasetKey dataset key
   * @param steps steps to run
   * @param reason reason to run
   * @param useLastSuccessful true if we want to run the latest successful attempt
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runPipelineAttempt(
    @NotNull UUID datasetKey,
    @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
    @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason,
    boolean useLastSuccessful);

  /**
   * Re-run a pipeline step.
   *
   * @param datasetKey dataset key
   * @param attempt attempt to run
   * @param steps steps to run
   * @param reason reason to run
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runPipelineAttempt(
    @NotNull UUID datasetKey,
    int attempt,
    @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
    @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason);
}
