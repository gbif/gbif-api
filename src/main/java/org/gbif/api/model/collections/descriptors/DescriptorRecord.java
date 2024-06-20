package org.gbif.api.model.collections.descriptors;

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.TypeStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class DescriptorRecord implements Serializable {

  private long key;
  private Long descriptorKey;
  private String scientificName;
  private Country country;
  private Integer individualCount;
  private List<String> identifiedBy;
  private Date dateIdentified;
  private List<TypeStatus> typeStatus;
  private List<String> recordedBy;
  private String discipline;
  private String objectClassification;
  private List<String> issues = new ArrayList<>();
  private Map<String, String> verbatim = new HashMap<>();
}
