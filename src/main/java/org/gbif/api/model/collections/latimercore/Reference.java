package org.gbif.api.model.collections.latimercore;

import java.net.URI;

import lombok.Data;

@Data
public class Reference {

  private URI resourceIRI;
  private String referenceType;
  private String referenceName;
}
