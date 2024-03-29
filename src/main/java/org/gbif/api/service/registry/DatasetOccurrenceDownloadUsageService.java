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
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.DatasetOccurrenceDownloadUsage;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Interface to access and persists information about occurrence download events. This interface was
 * initially implemented as a internal GBIF component that shouldn't be exposed for the public use.
 */
@SuppressWarnings("unused")
public interface DatasetOccurrenceDownloadUsageService {
  /** Retrieves a pageable result of the downloads which contain data taken from a dataset. */
  PagingResponse<DatasetOccurrenceDownloadUsage> listByDataset(
      @NotNull UUID datasetKey, Boolean showDownloadDetails, @Nullable Pageable page);
}
