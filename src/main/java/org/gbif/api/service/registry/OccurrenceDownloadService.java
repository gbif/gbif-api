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
import org.gbif.api.model.occurrence.Download;
import org.gbif.api.model.registry.DatasetOccurrenceDownloadUsage;

import java.util.Set;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Interface to access and persists information about occurrence download events.
 */
public interface OccurrenceDownloadService {


  /**
   * Persists the occurrence download object. The object must contain a unique key, the persistence storage doesn't
   * generate one for it.
   */
  void create(@NotNull Download download);

  /**
   * Retrieves a occurrence download by its unique key.
   */
  Download get(@NotNull String key);


  /**
   * Retrieves a pageable result of all the downloads, optionally the downloads can be filtered by status.
   */
  PagingResponse<Download> list(@Nullable Pageable page,@Nullable Set<Download.Status> status);

  /**
   * Retrieves a pageable result of the downloads created by a user in a given status.
   */
  PagingResponse<Download> listByUser(@NotNull String user,  @Nullable Pageable page, @Nullable Set<Download.Status> status);

  /**
   * Update an existing occurrence download.
   */
  void update(@NotNull Download download);


  /**
   * Retrieves a pageable result of the dataset usages in a occurrence download.
   */
  PagingResponse<DatasetOccurrenceDownloadUsage> listDatasetUsages(@NotNull String downloadKey,
                                                                @Nullable Pageable page);
}
