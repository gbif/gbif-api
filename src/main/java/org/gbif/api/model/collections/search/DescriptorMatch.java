package org.gbif.api.model.collections.search;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Rank;

@Data
public class DescriptorMatch {
  private Long key;
  private Long descriptorGroupKey;
  private String usageName;
  private Long usageKey;
  private Rank usageRank;
  private Country country;
  private Integer individualCount;
  private List<String> identifiedBy;
  private Date dateIdentified;
  private List<String> typeStatus;
  private List<String> recordedBy;
  private String discipline;
  private String objectClassification;
  private List<String> issues;
}
