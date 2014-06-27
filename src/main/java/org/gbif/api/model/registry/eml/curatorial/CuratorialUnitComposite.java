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
 * Composite class, combining fields from CuratorialUnitCount and CuratorialUnitRange for simpler parsing of
 * curatorial units, since both count and range types share the same root element.
 */
public class CuratorialUnitComposite extends CuratorialUnit {

  private static final long serialVersionUID = 3259062014051953305L;

  // fields from CuratorialUnitCount
  private int count;
  private int deviation;
  // fields from CuratorialUnitRange
  private int lower;
  private int upper;

  public CuratorialUnitComposite() {
  }

  public CuratorialUnitComposite(int count, int deviation, int lower, int upper, String typeVerbatim) {
    super(null, typeVerbatim);
    this.count = count;
    this.deviation = deviation;
    this.lower = lower;
    this.upper = upper;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getDeviation() {
    return deviation;
  }

  public void setDeviation(int deviation) {
    this.deviation = deviation;
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

  public void addDeviation(String deviation) {
    try {
      this.deviation = Integer.valueOf(deviation);
    } catch (NumberFormatException ignored) {
      // nonesense provided
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof CuratorialUnitComposite)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }

    CuratorialUnitComposite
      that = (CuratorialUnitComposite) object;
    return Objects.equal(count, that.count)
           && Objects.equal(deviation, that.deviation)
           && Objects.equal(lower, that.lower)
           && Objects.equal(upper, that.upper);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), count, deviation, lower, upper);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("count", count)
      .add("deviation", deviation)
      .add("lower", lower)
      .add("upper", upper)
      .toString();
  }

}
