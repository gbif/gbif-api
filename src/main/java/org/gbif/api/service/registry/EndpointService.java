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

import org.gbif.api.model.registry.Endpoint;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Service provides a set of operations on {@link Endpoint}.
 */
@SuppressWarnings("unused")
public interface EndpointService {

  /**
   * Add a new Endpoint to a target entity.
   *
   * @param targetEntityKey key of target entity
   * @param endpoint        Endpoint to add
   *
   * @return key of Endpoint added
   */
  int addEndpoint(@NotNull UUID targetEntityKey, @NotNull @Valid Endpoint endpoint);

  /**
   * Delete an existing Endpoint from a target entity by endpoint key.
   *
   * @param targetEntityKey key of target entity
   * @param endpointKey     Endpoint key to delete
   */
  void deleteEndpoint(@NotNull UUID targetEntityKey, int endpointKey);

  /**
   * List all endpoints of a target entity.
   *
   * @param targetEntityKey key of target entity
   *
   * @return list of endpoints that belong to the entity
   */
  List<Endpoint> listEndpoints(@NotNull UUID targetEntityKey);
}
