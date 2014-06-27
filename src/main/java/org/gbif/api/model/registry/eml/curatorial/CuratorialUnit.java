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
package org.gbif.api.model.registry.eml.curatorial;

import java.io.Serializable;

import com.google.common.base.Objects;


/**
 * The base class of all curatorial units.
 */
public class CuratorialUnit implements Serializable {

  private static final long serialVersionUID = 2359062014051953305L;

  private CuratorialUnitType type;
  private String typeVerbatim;

  protected CuratorialUnit() {
  }

  protected CuratorialUnit(CuratorialUnitType type, String typeVerbatim) {
    this.type = type;
    this.typeVerbatim = typeVerbatim;
  }

  public CuratorialUnitType getType() {
    return type;
  }

  public void setType(CuratorialUnitType type) {
    this.type = type;
  }

  public String getTypeVerbatim() {
    return typeVerbatim;
  }

  public void setTypeVerbatim(String typeVerbatim) {
    this.typeVerbatim = typeVerbatim;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CuratorialUnit)) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }

    CuratorialUnit that = (CuratorialUnit) obj;
    return Objects.equal(this.type, that.type) && Objects.equal(this.typeVerbatim, that.typeVerbatim);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), type, typeVerbatim);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("super", super.toString())
      .add("type", type)
      .add("typeVerbatim", typeVerbatim)
      .toString();
  }

}
