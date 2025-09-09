package org.gbif.api.model.collections.descriptors;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Result class for descriptor vocabulary validation that allows graceful handling
 * of invalid values by leaving fields blank rather than failing the entire operation.
 */
@Data
@Builder
public class DescriptorValidationResult {
  private String validBiome;
  private String validObjectClassification;
  @Builder.Default
  private List<String> warnings = new ArrayList<>();

  public void addWarning(String warning) {
    if (warnings == null) {
      warnings = new ArrayList<>();
    }
    warnings.add(warning);
  }

  public boolean hasWarnings() {
    return warnings != null && !warnings.isEmpty();
  }
}
