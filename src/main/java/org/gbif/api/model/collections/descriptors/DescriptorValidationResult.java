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
  private String validBiomeType;
  private String validObjectClassification;
  @Builder.Default
  private List<String> issues = new ArrayList<>();

  public void addIssue(String issue) {
    if (issues == null) {
      issues = new ArrayList<>();
    }
    issues.add(issue);
  }

  public boolean hasIssues() {
    return issues != null && !issues.isEmpty();
  }
}
