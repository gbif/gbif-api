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
package org.gbif.api.model.registry.eml.curatorial;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Used for acuratorial unit count with uncertainty.
 * E.g. 100000 +/- 1000.
 */
public class CuratorialUnitCount extends CuratorialUnit {

  private static final long serialVersionUID = 6330209010528750428L;

  private int count;

  private int deviation;

  public CuratorialUnitCount() {
  }

  public CuratorialUnitCount(CuratorialUnitType type, String typeVerbatim, int count, int deviation) {
    super(type, typeVerbatim);
    this.count = count;
    this.deviation = deviation;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CuratorialUnitCount that = (CuratorialUnitCount) o;
    return count == that.count &&
      deviation == that.deviation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), count, deviation);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CuratorialUnitCount.class.getSimpleName() + "[", "]")
      .add("count=" + count)
      .add("deviation=" + deviation)
      .toString();
  }
}
