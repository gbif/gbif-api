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
import org.gbif.api.model.registry.Network;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.model.registry.search.KeyTitleResult;
import org.gbif.api.model.registry.search.NetworkRequestSearchParams;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface NetworkService extends NetworkEntityService<Network> {

  /**
   * Pages through dataset constituents of a network, i.e. returns datasets which have an entry in
   * the dataset_network table.
   *
   * @param networkKey the network identifier
   */
  PagingResponse<Dataset> listConstituents(@NotNull UUID networkKey, @Nullable Pageable page);

  /**
   * Pages through publishing organizations of a network.
   *
   * @param networkKey the network identifier
   */
  PagingResponse<Organization> publishingOrganizations(
      @NotNull UUID networkKey, @Nullable Pageable page);

  /**
   * Adds an existing dataset to the list of constituents of a network.
   *
   * @param networkKey the network to add the dataset to
   * @param datasetKey the dataset to be added
   */
  void addConstituent(@NotNull UUID networkKey, @NotNull UUID datasetKey);

  /**
   * Removes an existing constituent dataset from a network.
   *
   * @param networkKey the network to remove the dataset from
   * @param datasetKey the dataset to be removed
   */
  void removeConstituent(@NotNull UUID networkKey, @NotNull UUID datasetKey);

  /** Provides a simple suggest service. */
  List<KeyTitleResult> suggest(@Nullable String q);

  /**
   * Provides paging service to list networks that can be filtered by multiple parameters.
   *
   * @param searchParams {@link NetworkRequestSearchParams}
   * @return list of networks ordered by creation date with the latest coming first
   */
  PagingResponse<Network> list(NetworkRequestSearchParams searchParams);
}
