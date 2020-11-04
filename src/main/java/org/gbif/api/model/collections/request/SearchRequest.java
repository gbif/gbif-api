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

public abstract class SearchRequest extends PageableBase implements Serializable {

  @Nullable private String q;
  @Nullable private String code;
  @Nullable private String name;
  @Nullable private String alternativeCode;
  @Nullable private UUID contact;
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
  public String getCode() {
    return code;
  }

  public void setCode(@Nullable String code) {
    this.code = code;
  }

  @Nullable
  public String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  @Nullable
  public String getAlternativeCode() {
    return alternativeCode;
  }

  public void setAlternativeCode(@Nullable String alternativeCode) {
    this.alternativeCode = alternativeCode;
  }

  @Nullable
  public UUID getContact() {
    return contact;
  }

  public void setContact(@Nullable UUID contact) {
    this.contact = contact;
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
}
