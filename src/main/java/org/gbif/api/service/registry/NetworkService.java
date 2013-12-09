/*
 * Copyright 2013 Global Biodiversity Information Facility (GBIF)
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

import java.util.UUID;
import javax.annotation.Nullable;

public interface NetworkService
  extends NetworkEntityService<Network> {

  /**
   * Pages through dataset constituents of a network, i.e. returns datasets which have an entry in the dataset_network
   * table.
   *
   * @param networkKey the network identifier
   */
  PagingResponse<Dataset> listConstituents(UUID networkKey, @Nullable Pageable page);

  /**
   * Adds an existing dataset to the list of constituents of a network.
   * @param networkKey the network to add the dataset to
   * @param datasetKey the dataset to be added
   */
  void addConstituent(UUID networkKey, UUID datasetKey);

  /**
   * Removes an existing constituent dataset from a network.
   * @param networkKey the network to remove the dataset from
   * @param datasetKey the dataset to be removed
   */
  void removeConstituent(UUID networkKey, UUID datasetKey);
}
