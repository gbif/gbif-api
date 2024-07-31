package org.gbif.api.model.collections.descriptors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.gbif.api.v2.RankedName;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Rank;
import org.gbif.api.vocabulary.TypeStatus;

@Data
public class Descriptor implements Serializable {

  private long key;
  private Long descriptorGroupKey;
  private Integer usageKey;
  private String usageName;
  private Rank usageRank;
  private Country country;
  private Integer individualCount;
  private List<String> identifiedBy;
  private Date dateIdentified;
  private List<String> typeStatus;
  private List<String> recordedBy;
  private String discipline;
  private String objectClassification;
  private List<RankedName> taxonClassification;
  private List<String> issues = new ArrayList<>();
  private Map<String, String> verbatim = new HashMap<>();
}
