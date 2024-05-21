package org.gbif.api.model.collections.latimercore;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Person {

  private String givenName;
  private String familyName;
  private List<Address> address = new ArrayList<>();
  private List<ContactDetail> contactDetail = new ArrayList<>();
  private List<Identifier> identifier = new ArrayList<>();
  private List<MeasurementOrFact> measurementOrFact = new ArrayList<>();
}
