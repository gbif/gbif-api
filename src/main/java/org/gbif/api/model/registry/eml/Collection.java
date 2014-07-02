/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.registry.eml;

import org.gbif.api.model.registry.eml.curatorial.CuratorialUnitComposite;
import org.gbif.api.vocabulary.PreservationMethodType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * Collection data for the Dataset.
 */
public class Collection implements Serializable {

  private static final long serialVersionUID = 3259062777751953305L;

  private String name;
  private String identifier;
  private String parentIdentifier;
  private PreservationMethodType specimenPreservationMethod;
  private List<CuratorialUnitComposite> curatorialUnits = Lists.newArrayList();

  public Collection() {
  }

  public Collection(
    String name,
    String parentIdentifier,
    String identifier,
    PreservationMethodType specimenPreservationMethod,
    List<CuratorialUnitComposite> curatorialUnits
  ) {
    this.name = name;
    this.parentIdentifier = parentIdentifier;
    this.identifier = identifier;
    this.specimenPreservationMethod = specimenPreservationMethod;
    this.curatorialUnits = curatorialUnits;
  }

  /**
   * The URI (LSID or URL) of the collection. In RDF, used as URI of the collection resource.
   *
   * @return the collection identifier
   */
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  /**
   * The official name of the Collection in the local language.
   *
   * @return the collection name.
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The quantitative descriptor (number of specimens, samples or batches). The actual quantification could be covered
   * by 1) an exact number of “JGI-units” in the collection plus a measure of uncertainty (+/- x); 2) a range of
   * numbers
   * (x to x), with the lower value representing an exact number, when the higher value is omitted. The discussion
   * concluded that the quantification should encompass all specimens, not only those that have not yet been digitised.
   * This is to avoid having to update the numbers too often. The number of non-public data (not digitised or not
   * accessible) can be calculated from the GBIF numbers as opposed to the JGTI-data.
   *
   * @return the list of curatorial units
   */
  public List<CuratorialUnitComposite> getCuratorialUnits() {
    return curatorialUnits;
  }

  public void setCuratorialUnits(List<CuratorialUnitComposite> curatorialUnits) {
    this.curatorialUnits = curatorialUnits;
  }

  /**
   * The identifier for the parent collection for this sub-collection. Enables a hierarchy of collections and sub
   * collections to be built.
   *
   * @return the parent collection identifier
   */
  public String getParentIdentifier() {
    return parentIdentifier;
  }

  public void setParentIdentifier(String parentIdentifier) {
    this.parentIdentifier = parentIdentifier;
  }

  /**
   * Picklist keyword indicating the process or technique used to prevent physical deterioration of non-living
   * collections. Expected to contain an instance from the Specimen Preservation Method Type Term vocabulary.
   *
   * @return the preservation method type
   */
  public PreservationMethodType getSpecimenPreservationMethod() {
    return specimenPreservationMethod;
  }

  public void setSpecimenPreservationMethod(PreservationMethodType specimenPreservationMethod) {
    this.specimenPreservationMethod = specimenPreservationMethod;
  }

  /**
   * Adds a CuratorialUnitComposite to the Collection's list of CuratorialUnitComposit.
   *
   * @param curatorialUnitComposite curatorial unit
   */
  public void addCuratorialUnitComposite(CuratorialUnitComposite curatorialUnitComposite) {
    if (curatorialUnits == null) {
      List<CuratorialUnitComposite> list = new ArrayList<CuratorialUnitComposite>();
      list.add(curatorialUnitComposite);
      curatorialUnits = list;
    }
    curatorialUnits.add(curatorialUnitComposite);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Collection)) {
      return false;
    }

    final Collection other = (Collection) obj;
    return Objects.equal(this.name, other.name)
           && Objects.equal(this.identifier, other.identifier)
           && Objects.equal(this.parentIdentifier, other.parentIdentifier)
           && Objects.equal(this.specimenPreservationMethod, other.specimenPreservationMethod)
           && Objects.equal(this.curatorialUnits, other.curatorialUnits);
  }

  @Override
  public int hashCode() {
    return Objects
      .hashCode(name, identifier, parentIdentifier, specimenPreservationMethod,
        curatorialUnits);
  }

  @Override
  public String toString() {
    return "Collection{" +
           "name='" + name + '\'' +
           ", identifier='" + identifier + '\'' +
           ", parentIdentifier='" + parentIdentifier + '\'' +
           ", specimenPreservationMethod=" + specimenPreservationMethod +
           ", curatorialUnits=" + curatorialUnits +
           '}';
  }

}
