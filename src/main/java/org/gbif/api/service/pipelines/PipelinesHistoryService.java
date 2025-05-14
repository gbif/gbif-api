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
package org.gbif.api.service.pipelines;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.pipelines.PipelineExecution;
import org.gbif.api.model.pipelines.PipelineProcess;
import org.gbif.api.model.pipelines.PipelineStep;
import org.gbif.api.model.pipelines.RunPipelineResponse;
import org.gbif.api.model.pipelines.StepRunner;
import org.gbif.api.model.pipelines.StepType;
import org.gbif.api.model.pipelines.ws.PipelineProcessParameters;
import org.gbif.api.model.pipelines.ws.RunAllParams;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
   * @return an instance of pipelines process if exists.
   */
  PipelineProcess getPipelineProcess(@NotNull UUID datasetKey, int attempt);

  /** Returns information about all running pipelines executions */
  PagingResponse<PipelineProcess> getRunningPipelineProcess(
      @Nullable StepType stepType, @Nullable StepRunner stepRunner, Pageable pageable);

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
   * Gets execution key for running dataset
   *
   * @param datasetKey dataset identifier
   * @return running execution key
   */
  Long getRunningExecutionKey(@NotNull UUID datasetKey);

  /**
   * Update the information of a pipeline step.
   *
   * @param pipelineStep step to be added
   * @return the key of the PipelineStep created.
   */
  long updatePipelineStep(@NotNull PipelineStep pipelineStep);

  /**
   * Gets the PipelineStep of the specified keys.
   *
   * @param stepKey key of the pipeline step
   * @return {@link PipelineStep}.
   */
  PipelineStep getPipelineStep(long stepKey);

  /**
   * Gets the PipelineSteps list of the execution key.
   *
   * @param executionKey key of the pipeline execution
   * @return {@link List<PipelineStep>}.
   */
  List<PipelineStep> getPipelineStepsByExecutionKey(long executionKey);

  /** Mark all pipeline executions as finished to clean running UI */
  void markAllPipelineExecutionAsFinished();

  /**
   * Mark pipeline execution as finished when all pipelin steps are finished
   *
   * @param executionKey key of the pipeline execution
   */
  void markPipelineExecutionIfFinished(long executionKey);

  /**
   * Change status to ABORTED and set finished date if state is RUNNING, QUEUED or SUBMITTED, and
   * set pipeline execution as finished
   *
   * @param executionKey key of the pipeline execution
   */
  void markPipelineStatusAsAborted(long executionKey);

  /**
   * Runs the last attempt for all datasets.
   *
   * @param steps steps to run
   * @param reason reason to run
   * @param useLastSuccessful true if we want to run the latest successful attempt
   * @param markPreviousAttemptAsFailed previous status can't be wrong, when CLI restarted during
   *     processing a dataset
   * @param runAllParams parameters, contain datasets to exclude
   * @param interpretTypes is used for partial interpretation such as only TAXONOMY, METADATA and
   *     etc
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runAll(
      @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
      @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason,
      boolean useLastSuccessful,
      boolean markPreviousAttemptAsFailed,
      @Nullable RunAllParams runAllParams,
      @Nullable Set<String> interpretTypes);

  /**
   * Restart last failed pipelines step for a dataset.
   *
   * @param datasetKey dataset key
   * @param steps steps to run
   * @param reason reason to run
   * @param useLastSuccessful true if we want to run the latest successful attempt
   * @param markPreviousAttemptAsFailed previous status can't be wrong, when CLI restarted during
   *     processing a dataset
   * @param interpretTypes is used for partial interpretation such as only TAXONOMY, METADATA and
   *     etc
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runPipelineAttempt(
      @NotNull UUID datasetKey,
      @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
      @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason,
      boolean useLastSuccessful,
      boolean markPreviousAttemptAsFailed,
      @Nullable Set<String> interpretTypes);

  /**
   * Re-run a pipeline step.
   *
   * @param datasetKey dataset key
   * @param attempt attempt to run
   * @param steps steps to run
   * @param reason reason to run
   * @param markPreviousAttemptAsFailed previous status can't be wrong, when CLI restarted during
   *     processing a dataset
   * @param interpretTypes is used for partial interpretation such as only TAXONOMY, METADATA and
   *     etc
   * @return {@link RunPipelineResponse}.
   */
  RunPipelineResponse runPipelineAttempt(
      @NotNull UUID datasetKey,
      int attempt,
      @NotBlank(message = STEPS_REQUIRED_MESSAGE) String steps,
      @NotBlank(message = REASON_REQUIRED_MESSAGE) String reason,
      boolean markPreviousAttemptAsFailed,
      @Nullable Set<String> interpretTypes);

  /**
   * Sends email to data administrator about absent identifiers issue with a dataset
   *
   * <p>Deprecated: use {@link #notifyAbsentIdentifiers(UUID, int, String)} instead.
   *
   * @param datasetKey dataset key
   * @param attempt attempt to run
   * @param message with failed metrics and other info*
   */
  @Deprecated
  void sendAbsentIndentifiersEmail(@NotNull UUID datasetKey, int attempt, @NotNull String message);

  /**
   * Mark failed identifier stage as finished and continue interpretation process for datasets were
   * identifier stage failed because of a threshold limit
   *
   * @param datasetKey dataset key
   * @param attempt attempt to run
   */
  void allowAbsentIndentifiers(@NotNull UUID datasetKey, int attempt);

  /**
   * Mark latest failed identifier stage as finished and continue interpretation process for
   * datasets were identifier stage failed because of a threshold limit
   *
   * @param datasetKey dataset key
   */
  void allowAbsentIndentifiers(@NotNull UUID datasetKey);

  /**
   * Sends a notification to the data administrators about absent identifiers issues with the
   * dataset.
   *
   * @param datasetKey key of the dataset
   * @param attempt crawling attempt
   * @param executionKey key of the pipelines execution
   * @param message cause of the issue
   */
  void notifyAbsentIdentifiers(UUID datasetKey, int attempt, long executionKey, String message);

  /**
   * Sets the pipeline step state QUEUED only if it's in SUBMITTED state.
   *
   * @param key pipeline step key
   */
  void setSubmittedPipelineStepToQueued(long key);
}
