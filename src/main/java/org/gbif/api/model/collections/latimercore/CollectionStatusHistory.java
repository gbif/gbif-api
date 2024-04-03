package org.gbif.api.model.collections.latimercore;

import lombok.Data;

@Data
public class CollectionStatusHistory {

  private String status;
  private String statusType;
}
