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
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Installation;

import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface InstallationService
  extends NetworkEntityService<Installation> {

  /**
   * Provides paging service to list datasets hosted by a specific installation.
   *
   * @param page paging parameters to use, if {@code page} is {@code null} sensible defaults will be used
   */
  PagingResponse<Dataset> getHostedDatasets(@NotNull UUID installationKey, @Nullable Pageable page);

  /**
   * Provides access to deleted installations.
   */
  PagingResponse<Installation> listDeleted(@Nullable Pageable page);

  /**
   * Provides access to installations that serve no datasets.
   */
  PagingResponse<Installation> listNonPublishing(@Nullable Pageable page);
}
