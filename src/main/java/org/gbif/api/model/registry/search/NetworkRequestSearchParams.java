package org.gbif.api.model.registry.search;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NetworkRequestSearchParams extends RequestSearchParams {

  private UUID datasetKey;

}
