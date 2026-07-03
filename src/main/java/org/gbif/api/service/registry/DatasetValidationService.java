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

import java.util.UUID;

public interface DatasetValidationService {

  /**
   * Stores or updates the validation report for a specific crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @param attempt    crawl attempt number
   * @param report     validation report as a JSON string
   */
  void createOrUpdate(UUID datasetKey, int attempt, String report);

  /**
   * Retrieves the validation report for a specific crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @param attempt    crawl attempt number
   * @return validation report as a JSON string
   */
  String get(UUID datasetKey, int attempt);

  /**
   * Retrieves the validation report for the latest crawl attempt of a dataset.
   *
   * @param datasetKey key of the target dataset
   * @return validation report as a JSON string
   */
  String getLatest(UUID datasetKey);

}
