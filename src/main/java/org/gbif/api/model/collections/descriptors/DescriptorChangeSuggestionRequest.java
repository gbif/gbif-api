package org.gbif.api.model.collections.descriptors;

import java.util.List;

import java.util.UUID;

import lombok.Data;

import org.gbif.api.model.collections.suggestions.Type;
import org.gbif.api.model.common.export.ExportFormat;

@Data
public class DescriptorChangeSuggestionRequest {

  private String title;
  private String description;
  private ExportFormat format = ExportFormat.CSV;
  private String proposerEmail;
  private List<String> comments;
  private long descriptorGroupKey;
  private UUID collectionKey;
  private Type type;

}

