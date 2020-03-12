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
package org.gbif.api.model.occurrence;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

/**
 * A fact or measurement about an occurrence.
 * @see <a href="http://rs.gbif.org/extension/dwc/measurements_or_facts.xml">Fact extension</a>
 */
public class FactOrMeasurment {
  private String id;
  private String type;
  private String value;
  private String unit;
  private String accuracy;
  private String method;
  private String determinedBy;
  private String determinedDate;
  private String remarks;

  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @NotNull
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @NotNull
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Nullable
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Nullable
  public String getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(String accuracy) {
    this.accuracy = accuracy;
  }

  @Nullable
  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  @Nullable
  public String getDeterminedBy() {
    return determinedBy;
  }

  public void setDeterminedBy(String determinedBy) {
    this.determinedBy = determinedBy;
  }

  @Nullable
  public String getDeterminedDate() {
    return determinedDate;
  }

  public void setDeterminedDate(String determinedDate) {
    this.determinedDate = determinedDate;
  }

  @Nullable
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, type, value, unit, accuracy, method, determinedBy, determinedDate, remarks);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof FactOrMeasurment)) {
      return false;
    }
    FactOrMeasurment that = (FactOrMeasurment) obj;
    return Objects.equal(this.id, that.id)
        && Objects.equal(this.type, that.type)
        && Objects.equal(this.value, that.value)
        && Objects.equal(this.unit, that.unit)
        && Objects.equal(this.accuracy, that.accuracy)
        && Objects.equal(this.method, that.method)
        && Objects.equal(this.determinedBy, that.determinedBy)
        && Objects.equal(this.determinedDate, that.determinedDate)
        && Objects.equal(this.remarks, that.remarks);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("id", id)
      .add("type", type)
      .add("value", value)
      .add("unit", unit)
      .add("accuracy", accuracy)
      .add("method", method)
      .add("determinedBy", determinedBy)
      .add("determinedDate", determinedDate)
      .add("remarks", remarks)
      .toString();
  }

 }
