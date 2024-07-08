package org.gbif.api.model.collections.latimercore;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrganisationalUnit {

  private String organisationalUnitName;
  private String organisationalUnitType;
  private List<Address> address = new ArrayList<>();
  private List<ContactDetail> contactDetail = new ArrayList<>();
  private List<Identifier> identifier = new ArrayList<>();
  private List<MeasurementOrFact> measurementOrFact = new ArrayList<>();
  private List<Reference> reference = new ArrayList<>();
}
