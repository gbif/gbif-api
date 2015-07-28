/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.crawler.DatasetProcessStatus;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Interface to access and persist information about dataset processing statuses.
 */
public interface DatasetProcessStatusService {

  /**
   * Persists the {@link DatasetProcessStatus} object.
   */
  void createDatasetProcessStatus(@NotNull DatasetProcessStatus datasetProcessStatus);

  /**
   * Persists the {@link DatasetProcessStatus} object which must exist.
   */
  void updateDatasetProcessStatus(@NotNull DatasetProcessStatus datasetProcessStatus);

  /**
   * Retrieves a {@link DatasetProcessStatus} by its datasetKey and attempt.
   */
  DatasetProcessStatus getDatasetProcessStatus(@NotNull UUID datasetKey, int attempt);

  /**
   * Retrieves a pageable result of all the dataset processing statuses.
   */
  PagingResponse<DatasetProcessStatus> listDatasetProcessStatus(@Nullable Pageable page);

  /**
   * Lists the {@link DatasetProcessStatus} of a dataset. The result is sorted by the descending started crawling date
   * (i.e. the last crawl comes first).
   */
  PagingResponse<DatasetProcessStatus> listDatasetProcessStatus(@NotNull UUID datasetKey, @Nullable Pageable page);


  /**
   * Retrieves a pageable result of all dataset processing statuses that have been aborted in their latest crawl
   */
  PagingResponse<DatasetProcessStatus> listAbortedDatasetProcesses(@Nullable Pageable page);
}
