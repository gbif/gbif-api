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
import org.gbif.api.model.registry.Node;
import org.gbif.api.model.registry.Organization;
import org.gbif.api.vocabulary.Country;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Actions on a GBIF node.
 * Be aware that of 2013 Nodes DO NOT support the ContactInterface although advertised in this interface!!!
 */
public interface NodeService extends NetworkEntityService<Node> {

  /**
   * Provides access to the organizations endorsed by a single node.
   */
  PagingResponse<Organization> endorsedOrganizations(@NotNull UUID nodeKey, @Nullable Pageable page);

  /**
   * Provides access to the organizations that are awaiting an endorsement approval.
   */
  PagingResponse<Organization> pendingEndorsements(@Nullable Pageable page);

  /**
   * Provides access to the organizations that are awaiting an endorsement approval for the given node.
   */
  PagingResponse<Organization> pendingEndorsements(@NotNull UUID nodeKey, @Nullable Pageable page);

  /**
   * Provides the installations that are registered to organizations with an approved endorsement from the node.
   */
  PagingResponse<Installation> installations(@NotNull UUID nodeKey, @Nullable Pageable page);

  /**
   * Returns a node for a given country.
   * 
   * @return the countries node or null if none exists
   */
  Node getByCountry(Country country);

  /**
   * Returns a list of all countries which do have a GBIF node.
   * 
   * @return list of distinct countries having a GBIF node
   */
  List<Country> listNodeCountries();

  /**
   * Returns those countries considered active in GBIF.
   * To be active a country must have a Node of type country present, that is either of voting or associate status.
   *  @return A list of countries ordered by iso code
    */
   List<Country> listActiveCountries();

  /**
   * Provides paging service to list datasets published, i.e. owned by organizations endorsed by the given node.
   * 
   * @return list of datasets ordered by creation date with latest coming first
   */
  PagingResponse<Dataset> endorsedDatasets(@NotNull UUID nodeKey, @Nullable Pageable page);
}
