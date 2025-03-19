package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItem {
  private String uuid;
  @JsonProperty("uuid_institution_normalized")
  private String uuidInstitutionNormalized;
  @JsonProperty("uuid_collection_normalized")
  private String uuidCollectionNormalized;
  private boolean current;
  private int version;
  private String identifier;
  @JsonProperty("local_identifier")
  private String localIdentifier;
  private DataObject data;
  @JsonProperty("modification_date")
  private String modificationDate;
}

