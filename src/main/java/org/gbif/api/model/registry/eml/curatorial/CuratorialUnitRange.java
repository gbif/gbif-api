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

import com.google.common.base.Objects;


/**
 * Used to indicate that the curatorial unit falls within a range of values.
 */
public class CuratorialUnitRange extends CuratorialUnit {
  private static final long serialVersionUID = 6558580719534069911L;

  private int lower;
  private int upper;

  public CuratorialUnitRange() {
  }

  public CuratorialUnitRange(CuratorialUnitType type, String typeVerbatim, int lower, int upper) {
    super(type, typeVerbatim);
    this.lower = lower;
    this.upper = upper;
  }

  public int getLower() {
    return lower;
  }

  public void setLower(int lower) {
    this.lower = lower;
  }

  public int getUpper() {
    return upper;
  }

  public void setUpper(int upper) {
    this.upper = upper;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof CuratorialUnitRange)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }

    CuratorialUnitRange
      that = (CuratorialUnitRange) object;
    return Objects.equal(this.lower, that.lower) && Objects.equal(this.upper, that.upper);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), lower, upper);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("super", super.toString())
      .add("lower", lower)
      .add("upper", upper)
      .toString();
  }

}
