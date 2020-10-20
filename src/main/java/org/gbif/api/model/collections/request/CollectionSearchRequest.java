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
import org.gbif.api.vocabulary.IdentifierType;

import java.util.UUID;

import javax.annotation.Nullable;

public class CollectionSearchRequest extends SearchRequest {

  @Nullable private UUID institutionKey;

  @Nullable
  public UUID getInstitutionKey() {
    return institutionKey;
  }

  public void setInstitutionKey(@Nullable UUID institutionKey) {
    this.institutionKey = institutionKey;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    UUID institutionKey;
    UUID contactKey;
    String query;
    String code;
    String name;
    String alternativeCode;
    String machineTagNamespace;
    String machineTagName;
    String machineTagValue;
    IdentifierType identifierType;
    String identifier;
    Pageable page;

    public Builder institutionKey(UUID institutionKey) {
      this.institutionKey = institutionKey;
      return this;
    }

    public Builder contactKey(UUID contactKey) {
      this.contactKey = contactKey;
      return this;
    }

    public Builder query(String query) {
      this.query = query;
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

    public Builder page(Pageable page) {
      this.page = page;
      return this;
    }

    public CollectionSearchRequest build() {
      CollectionSearchRequest req = new CollectionSearchRequest();
      req.setInstitutionKey(institutionKey);
      req.setContactKey(contactKey);
      req.setQuery(query);
      req.setCode(code);
      req.setName(name);
      req.setAlternativeCode(alternativeCode);
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
