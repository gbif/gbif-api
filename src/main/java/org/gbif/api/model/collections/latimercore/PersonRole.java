package org.gbif.api.model.collections.latimercore;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PersonRole {

  private List<Person> person = new ArrayList<>();
  private List<Role> role = new ArrayList<>();
  private List<MeasurementOrFact> measurementOrFact = new ArrayList<>();
}
