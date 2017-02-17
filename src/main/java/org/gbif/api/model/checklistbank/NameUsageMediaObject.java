package org.gbif.api.model.checklistbank;

import org.gbif.api.model.common.MediaObject;

import javax.annotation.Nullable;

/**
 * An extension to the common MediaObject that adds a source taxon key property from checklistbank.
 */
public class NameUsageMediaObject extends MediaObject implements NameUsageExtension {

  private Integer taxonKey;
  private Integer sourceTaxonKey;

  /**
   * The name usage "taxon" key this description belongs to.
   */
  @Override
  public Integer getTaxonKey() {
    return taxonKey;
  }

  @Override
  public void setTaxonKey(Integer taxonKey) {
    this.taxonKey = taxonKey;
  }

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
