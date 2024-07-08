package org.gbif.api.model.collections.latimercore;

import lombok.Data;

@Data
public class ResourceRelationship {

  private String relatedResourceName;
  private String relationshipOfResource;
}
