/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.service.checklistbank;

import org.gbif.api.model.checklistbank.DatasetMetrics;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

public interface DatasetMetricsService {

  /**
   * @return metrics about this dataset or null if none could be found
   */
  @Nullable
  DatasetMetrics get(UUID datasetKey);

  /**
   * @return the list of all metrics about this dataset ordered by ascending created timestamp.
   *         An empty list if no matching dataset is found.
   */
  @Nullable
  List<DatasetMetrics> list(UUID datasetKey);

}
