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

import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingResponse;
import org.gbif.api.model.registry.Dataset;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Dataset DataPackage metadata.
 */
@SuppressWarnings("unused")
public interface DatasetDataPackageService {

  /**
   * Creates <a href="https://specs.frictionlessdata.io/data-package/">DataPackage</a> metadata to a target dataset.
   *
   * @param datasetKey   key of target dataset
   * @param dataPackage  DataPackage metadata
   *
   */
  void create(UUID datasetKey, @NotNull @Valid Dataset.DataPackage dataPackage);

  /**
   * Updates <a href="https://specs.frictionlessdata.io/data-package/">DataPackage</a> metadata to a target dataset.
   *
   * @param datasetKey   key of target dataset
   * @param dataPackage  DataPackage metadata
   *
   */
  void update(UUID datasetKey, @NotNull @Valid Dataset.DataPackage dataPackage);

  /**
   * Retrieves <a href="https://specs.frictionlessdata.io/data-package/">DataPackage</a> metadata of a dataset.
   *
   * @param datasetKey   key of target dataset
   *
   */
  Dataset.DataPackage get(UUID datasetKey);


  /**
   * Retrieves the resource metadata information of a dataset data package.
   * @param datasetKey key of the dataset data package
   * @param resourceName resource name to retrieve
   * @return JSON object as String
   */
  String getResource(UUID datasetKey, String resourceName);

  /**
   * Retrieves the list of resources for a dataset data package.
   * @param datasetKey key of the dataset data package
   * @return JSON object as String
   */
  String getResources(UUID datasetKey);

  /**
   * Retrieves the list of resources names for a dataset data package.
   * @param datasetKey key of the dataset data package
   * @return JSON object as String
   */
  List<String> getResourceNames(UUID datasetKey);

  /**
   * Retrieves a pageable response of the registered data packages.
   * @param params pageable request
   * @return paging response containing a list of data packages
   */
  PagingResponse<Dataset.DataPackage> list(PageableBase params);

}
