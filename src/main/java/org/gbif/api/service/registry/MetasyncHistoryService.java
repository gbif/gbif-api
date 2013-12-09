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
import org.gbif.api.model.registry.metasync.MetasyncHistory;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Interface to access and persists historical metadata synchronization results.
 */
public interface MetasyncHistoryService {

  /**
   * Persists the {@link MetasyncHistory} object.
   */
  void createMetasync(@NotNull MetasyncHistory metasyncHistory);

  /**
   * Retrieves a pageable result of all the metasync history records.
   */
  PagingResponse<MetasyncHistory> listMetasync(@Nullable Pageable page);

  /**
   * Lists the {@link MetasyncHistory} of a installation. The result is sorted by the sync date.
   */
  PagingResponse<MetasyncHistory> listMetasync(@NotNull UUID installationKey, @Nullable Pageable page);

}
