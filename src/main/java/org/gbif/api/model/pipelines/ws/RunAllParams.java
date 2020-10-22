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
package org.gbif.api.model.pipelines.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Encapsulates the params to pass in the body for the runAll method. */
public class RunAllParams {
  List<UUID> datasetsToExclude = new ArrayList<>();
  List<UUID> datasetsToInclude = new ArrayList<>();

  // getters and setters needed for jackson

  public List<UUID> getDatasetsToExclude() {
    return datasetsToExclude;
  }

  public void setDatasetsToExclude(List<UUID> datasetsToExclude) {
    this.datasetsToExclude = datasetsToExclude;
  }

  public List<UUID> getDatasetsToInclude() {
    return datasetsToInclude;
  }

  public void setDatasetsToInclude(List<UUID> datasetsToInclude) {
    this.datasetsToInclude = datasetsToInclude;
  }
}
