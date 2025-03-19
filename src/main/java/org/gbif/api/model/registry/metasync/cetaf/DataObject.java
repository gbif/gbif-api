package org.gbif.api.model.registry.metasync.cetaf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataObject {
  private String type;
  @JsonProperty("data_list")
  private List<DataListItem> dataList;
  @JsonProperty("list_identifiers")
  private List<Identifier> listIdentifiers;
  @JsonProperty("child_collections")
  private List<List<ChildCollection>> childCollections;
  @JsonProperty("parent_institution_list_identifiers")
  private List<Identifier> parentInstitutionListIdentifiers;
}

