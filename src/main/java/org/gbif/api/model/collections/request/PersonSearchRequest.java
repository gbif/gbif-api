/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
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
package org.gbif.api.model.collections.request;

import org.gbif.api.model.common.paging.Pageable;
import org.gbif.api.model.common.paging.PageableBase;
import org.gbif.api.model.common.paging.PagingRequest;
import org.gbif.api.vocabulary.IdentifierType;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Nullable;

public class PersonSearchRequest extends PageableBase implements Serializable {

  @Nullable private String q;
  @Nullable UUID primaryInstitution;
  @Nullable UUID primaryCollection;
  @Nullable private String machineTagNamespace;
  @Nullable private String machineTagName;
  @Nullable private String machineTagValue;
  @Nullable private IdentifierType identifierType;
  @Nullable private String identifier;

  @Nullable
  public String getQ() {
    return q;
  }

  public void setQ(@Nullable String q) {
    this.q = q;
  }

  @Nullable
  public UUID getPrimaryInstitution() {
    return primaryInstitution;
  }

  public void setPrimaryInstitution(@Nullable UUID primaryInstitution) {
    this.primaryInstitution = primaryInstitution;
  }

  @Nullable
  public UUID getPrimaryCollection() {
    return primaryCollection;
  }

  public void setPrimaryCollection(@Nullable UUID primaryCollection) {
    this.primaryCollection = primaryCollection;
  }

  @Nullable
  public String getMachineTagNamespace() {
    return machineTagNamespace;
  }

  public void setMachineTagNamespace(@Nullable String machineTagNamespace) {
    this.machineTagNamespace = machineTagNamespace;
  }

  @Nullable
  public String getMachineTagName() {
    return machineTagName;
  }

  public void setMachineTagName(@Nullable String machineTagName) {
    this.machineTagName = machineTagName;
  }

  @Nullable
  public String getMachineTagValue() {
    return machineTagValue;
  }

  public void setMachineTagValue(@Nullable String machineTagValue) {
    this.machineTagValue = machineTagValue;
  }

  @Nullable
  public IdentifierType getIdentifierType() {
    return identifierType;
  }

  public void setIdentifierType(@Nullable IdentifierType identifierType) {
    this.identifierType = identifierType;
  }

  @Nullable
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(@Nullable String identifier) {
    this.identifier = identifier;
  }

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String q;
    UUID primaryInstitution;
    UUID primaryCollection;
    private String machineTagNamespace;
    private String machineTagName;
    private String machineTagValue;
    private IdentifierType identifierType;
    private String identifier;
    Pageable page;

    public Builder primaryInstitution(UUID primaryInstitution) {
      this.primaryInstitution = primaryInstitution;
      return this;
    }

    public Builder primaryCollection(UUID primaryCollection) {
      this.primaryCollection = primaryCollection;
      return this;
    }

    public Builder query(String q) {
      this.q = q;
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

    public Builder page(Pageable page) {
      this.page = page;
      return this;
    }

    public PersonSearchRequest build() {
      PersonSearchRequest req = new PersonSearchRequest();
      req.setPrimaryCollection(primaryCollection);
      req.setPrimaryInstitution(primaryInstitution);
      req.setQ(q);
      req.setMachineTagNamespace(machineTagNamespace);
      req.setMachineTagName(machineTagName);
      req.setMachineTagValue(machineTagValue);
      req.setIdentifierType(identifierType);
      req.setIdentifier(identifier);
      if (page != null) {
        req.setLimit(page.getLimit());
        req.setOffset(page.getOffset());
      }
      return req;
    }
  }
}
