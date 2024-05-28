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
package org.gbif.api.model.checklistbank;

import jakarta.annotation.Nullable;

public interface NameUsageExtension {

  /**
   * The name usage "taxon" key this extension record belongs to.
   */
  Integer getTaxonKey();

  void setTaxonKey(Integer taxonKey);

  /**
   * @return a source reference string
   */
  @Nullable
  String getSource();

  void setSource(String source);

  /**
   * If the source is another name usage this is the taxonKey of that usage.
   * Only useful for the backbone dataset.
   *
   * @return The key of the name usage this instance is derived from.
   */
  @Nullable
  Integer getSourceTaxonKey();

  void setSourceTaxonKey(Integer sourceTaxonKey);
}
