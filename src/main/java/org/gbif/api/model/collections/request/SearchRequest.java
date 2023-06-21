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
import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.IdentifierType;
import org.gbif.api.vocabulary.collections.MasterSourceType;

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
  @Nullable private Country country;
  @Nullable private String city;
  @Nullable private String fuzzyName;
  @Nullable private Boolean active;

  @Nullable private MasterSourceType masterSourceType;
  @Nullable private String numberSpecimens;
  @Nullable private Boolean displayOnNHCPortal;
  @Nullable private UUID replacedBy;

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

  @Nullable
  public Country getCountry() {
    return country;
  }

  public void setCountry(@Nullable Country country) {
    this.country = country;
  }

  @Nullable
  public String getCity() {
    return city;
  }

  public void setCity(@Nullable String city) {
    this.city = city;
  }

  @Nullable
  public String getFuzzyName() {
    return fuzzyName;
  }

  public void setFuzzyName(@Nullable String fuzzyName) {
    this.fuzzyName = fuzzyName;
  }

  @Nullable
  public Boolean getActive() {
    return active;
  }

  public void setActive(@Nullable Boolean active) {
    this.active = active;
  }

  @Nullable
  public MasterSourceType getMasterSourceType() {
    return masterSourceType;
  }

  public void setMasterSourceType(@Nullable MasterSourceType masterSourceType) {
    this.masterSourceType = masterSourceType;
  }

  @Nullable
  public String getNumberSpecimens() {
    return numberSpecimens;
  }

  public void setNumberSpecimens(@Nullable String numberSpecimens) {
    this.numberSpecimens = numberSpecimens;
  }

  public Boolean getDisplayOnNHCPortal() {
    return displayOnNHCPortal;
  }

  public void setDisplayOnNHCPortal(Boolean displayOnNHCPortal) {
    this.displayOnNHCPortal = displayOnNHCPortal;
  }

  public Pageable getPage() {
    return new PagingRequest(getOffset(), getLimit());
  }

  @Nullable
  public UUID getReplacedBy() {
    return replacedBy;
  }

  public void setReplacedBy(@Nullable UUID replacedBy) {
    this.replacedBy = replacedBy;
  }
}
