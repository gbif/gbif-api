package org.gbif.api.model.collections.search;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.gbif.api.vocabulary.Country;

@Data
public class DescriptorMatch {
  private Long key;
  private Long descriptorGroupKey;
  private String usageName;
  private String usageKey;
  private String usageRank;
  private String checklistKey;
  private Country country;
  private Integer individualCount;
  private List<String> identifiedBy;
  private Date dateIdentified;
  private List<String> typeStatus;
  private List<String> recordedBy;
  private String discipline;
  private String objectClassification;
  private String biome;
  private String biomeType;
  private List<String> issues;

}
