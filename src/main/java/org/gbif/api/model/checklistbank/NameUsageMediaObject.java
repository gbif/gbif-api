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
package org.gbif.api.model.checklistbank;

import org.gbif.api.model.common.MediaObject;

import javax.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * An extension to the common MediaObject that adds a source taxon key property from checklistbank.
 */
public class NameUsageMediaObject extends MediaObject implements NameUsageExtension {

  private Integer taxonKey;
  private Integer sourceTaxonKey;

  /**
   * The name usage "taxon" key this description belongs to.
   */
  @Schema(description = "The name usage “taxon“ key to which this species profile belongs.")
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

  @Schema(description = "The name usage key of the taxon in the checklist including this media object.")
  @Nullable
  @Override
  public Integer getSourceTaxonKey() {
    return sourceTaxonKey;
  }

  @Override
  public void setSourceTaxonKey(Integer sourceTaxonKey) {
    this.sourceTaxonKey = sourceTaxonKey;
  }
}
