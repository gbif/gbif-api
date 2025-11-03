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
package org.gbif.api.service.registry;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import org.gbif.api.model.registry.Installation;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.model.registry.search.InstallationRequestSearchParams;
import org.gbif.api.model.registry.search.KeyTitleResult;
import org.gbif.api.model.registry.search.OrganizationRequestSearchParams;
import org.gbif.api.vocabulary.InstallationType;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface InstallationService
    extends NetworkEntityService<Installation> {

  /**
   * Provides paging service to list datasets hosted by a specific installation.
   *
   * @param page paging parameters to use, if {@code page} is {@code null} sensible defaults will be
   *     used
   */
  PagingResponse<Dataset> getHostedDatasets(@NotNull UUID installationKey, @Nullable Pageable page);

  /** Provides access to deleted installations. */
  PagingResponse<Installation> listDeleted(InstallationRequestSearchParams searchParams);

  /** Provides access to installations that serve no datasets. */
  PagingResponse<Installation> listNonPublishing(@Nullable Pageable page);

  /** Provides a simple suggest service. */
  List<KeyTitleResult> suggest(@Nullable String q);

  /**
   * Provides paging service to list installations filtered by a particular installation type.
   *
   * @param type the installation type filter
   * @return list of installations ordered by creation date with latest coming first
   */
  PagingResponse<Installation> listByType(InstallationType type, @Nullable Pageable page);

  /**
   * Provides paging service to list installations that can be filtered by multiple parameters.
   *
   * @param searchParams {@link InstallationRequestSearchParams}
   * @return list of installations ordered by creation date with the latest coming first
   */
  PagingResponse<Installation> list(InstallationRequestSearchParams searchParams);
}
