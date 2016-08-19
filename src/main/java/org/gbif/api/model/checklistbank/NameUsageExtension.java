package org.gbif.api.model.checklistbank;

import javax.annotation.Nullable;

/**
 *
 */
public interface NameUsageExtension {

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
