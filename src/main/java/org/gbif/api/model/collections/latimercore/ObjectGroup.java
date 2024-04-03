package org.gbif.api.model.collections.latimercore;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ObjectGroup {

  private String collectionName;
  private String description;
  private List<String> discipline = new ArrayList<>();
  private List<String> typeOfObjectGroup = new ArrayList<>();
  private List<OrganisationalUnit> hasOrganisationalUnit = new ArrayList<>();
  private Boolean isCurrentCollection;
  private List<String> preservationMethod = new ArrayList<>();
  private List<Address> address = new ArrayList<>();
  private List<CollectionStatusHistory> collectionStatusHistory = new ArrayList<>();
  private List<ContactDetail> contactDetail = new ArrayList<>();
  private List<GeographicContext> geographicContext = new ArrayList<>();
  private List<Identifier> identifier = new ArrayList<>();
  private List<MeasurementOrFact> measurementOrFact = new ArrayList<>();
  private List<PersonRole> personRole = new ArrayList<>();
  private List<Reference> reference = new ArrayList<>();
  private List<ResourceRelationship> resourceRelationship = new ArrayList<>();
  private List<ObjectClassification> objectClassification = new ArrayList<>();
}
