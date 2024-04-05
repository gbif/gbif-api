/*
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
package org.gbif.api.model.collections.request;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.vocabulary.CollectionsSortField;
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.GbifRegion;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.SortOrder;
import org.gbif.api.vocabulary.collections.MasterSourceType;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

public class InstitutionSearchRequest extends SearchRequest {

  @Nullable private List<String> type;

  @Nullable private List<String> institutionalGovernance;

  @Nullable private List<String> disciplines;

  @Nullable
  public List<String> getType() {
    return type;
  }

  public void setType(@Nullable List<String> type) {
    this.type = type;
  }

  @Nullable
  public List<String> getInstitutionalGovernance() {
    return institutionalGovernance;
  }

  public void setInstitutionalGovernance(@Nullable List<String> institutionalGovernance) {
    this.institutionalGovernance = institutionalGovernance;
  }

  @Nullable
  public List<String> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(@Nullable List<String> disciplines) {
    this.disciplines = disciplines;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    String q;
    String code;
    String name;
    String alternativeCode;
    String machineTagNamespace;
    String machineTagName;
    String machineTagValue;
    IdentifierType identifierType;
    String identifier;
    List<Country> country;
    List<GbifRegion> gbifRegion;
    String city;
    String fuzzyName;
    Boolean active;
    List<String> type;
    List<String> institutionalGovernance;
    List<String> disciplines;
    MasterSourceType masterSourceType;
    String numberSpecimens;
    Boolean displayOnNHCPortal;
    UUID replacedBy;
    String occurrenceCount;
    String typeSpecimenCount;
    List<UUID> institutionKeys;
    CollectionsSortField sortBy;
    SortOrder sortOrder;
    Pageable page;

    public Builder query(String q) {
      this.q = q;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder alternativeCode(String alternativeCode) {
      this.alternativeCode = alternativeCode;
      return this;
    }

    public Builder machineTagNamespace(String machineTagNamespace) {
      this.machineTagNamespace = machineTagNamespace;
      return this;
    }

    public Builder machineTagName(String machineTagName) {
      this.machineTagName = machineTagName;
      return this;
    }

    public Builder machineTagValue(String machineTagValue) {
      this.machineTagValue = machineTagValue;
      return this;
    }

    public Builder identifierType(IdentifierType identifierType) {
      this.identifierType = identifierType;
      return this;
    }

    public Builder identifier(String identifier) {
      this.identifier = identifier;
      return this;
    }

    public Builder country(List<Country> country) {
      this.country = country;
      return this;
    }

    public Builder gbifRegion(List<GbifRegion> gbifRegion) {
      this.gbifRegion = gbifRegion;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder fuzzyName(String fuzzyName) {
      this.fuzzyName = fuzzyName;
      return this;
    }

    public Builder active(boolean active) {
      this.active = active;
      return this;
    }

    public Builder type(List<String> type) {
      this.type = type;
      return this;
    }

    public Builder institutionalGovernance(List<String> institutionalGovernance) {
      this.institutionalGovernance = institutionalGovernance;
      return this;
    }

    public Builder disciplines(List<String> disciplines) {
      this.disciplines = disciplines;
      return this;
    }

    public Builder masterSourceType(MasterSourceType masterSourceType) {
      this.masterSourceType = masterSourceType;
      return this;
    }

    public Builder numberSpecimens(String numberSpecimens) {
      this.numberSpecimens = numberSpecimens;
      return this;
    }

    public Builder displayOnNHCPortal(boolean displayOnNHCPortal) {
      this.displayOnNHCPortal = displayOnNHCPortal;
      return this;
    }

    public Builder replacedBy(UUID replacedBy) {
      this.replacedBy = replacedBy;
      return this;
    }

    public Builder occurrenceCount(String occurrenceCount) {
      this.occurrenceCount = occurrenceCount;
      return this;
    }

    public Builder typeSpecimenCount(String typeSpecimenCount) {
      this.typeSpecimenCount = typeSpecimenCount;
      return this;
    }

    public Builder institutionKeys(List<UUID> institutionKeys) {
      this.institutionKeys = institutionKeys;
      return this;
    }

    public Builder sortBy(CollectionsSortField sortBy) {
      this.sortBy = sortBy;
      return this;
    }

    public Builder sortOrder(SortOrder sortOrder) {
      this.sortOrder = sortOrder;
      return this;
    }

    public Builder page(Pageable page) {
      this.page = page;
      return this;
    }

    public InstitutionSearchRequest build() {
      InstitutionSearchRequest req = new InstitutionSearchRequest();
      req.setQ(q);
      req.setCode(code);
      req.setName(name);
      req.setAlternativeCode(alternativeCode);
      req.setMachineTagNamespace(machineTagNamespace);
      req.setMachineTagName(machineTagName);
      req.setMachineTagValue(machineTagValue);
      req.setIdentifierType(identifierType);
      req.setIdentifier(identifier);
      req.setCountry(country);
      req.setGbifRegion(gbifRegion);
      req.setCity(city);
      req.setFuzzyName(fuzzyName);
      req.setActive(active);
      req.setType(type);
      req.setInstitutionalGovernance(institutionalGovernance);
      req.setDisciplines(disciplines);
      req.setMasterSourceType(masterSourceType);
      req.setNumberSpecimens(numberSpecimens);
      req.setDisplayOnNHCPortal(displayOnNHCPortal);
      req.setReplacedBy(replacedBy);
      req.setOccurrenceCount(occurrenceCount);
      req.setTypeSpecimenCount(typeSpecimenCount);
      req.setInstitutionKeys(institutionKeys);
      req.setSortBy(sortBy);
      req.setSortOrder(sortOrder);
      if (page != null) {
        req.setLimit(page.getLimit());
        req.setOffset(page.getOffset());
      }
      return req;
    }
  }
}
