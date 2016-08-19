package org.gbif.api.model.checklistbank;

import org.gbif.api.model.common.MediaObject;

import javax.annotation.Nullable;

/**
 * An extension to the common MediaObject that adds a source taxon key property from checklistbank.
 */
public class NameUsageMediaObject extends MediaObject implements NameUsageExtension {

  private Integer sourceTaxonKey;

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
