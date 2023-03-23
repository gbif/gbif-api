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

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * GADM level.
 */
public class Gadm {

  @Schema(
    description = "The top-level division for a country, territory or island.\n\n" +
      "This is usually a three-letter code from ISO 3166."
  )
  private GadmFeature level0;

  @Schema(
    description = "The first-level division from the GADM database."
  )
  private GadmFeature level1;

  @Schema(
    description = "The second-level division from the GADM database."
  )
  private GadmFeature level2;

  @Schema(
    description = "The third-level division from the GADM database."
  )
  private GadmFeature level3;

  public GadmFeature getLevel0() {
    return level0;
  }

  public void setLevel0(GadmFeature level0) {
    this.level0 = level0;
  }

  public GadmFeature getLevel1() {
    return level1;
  }

  public void setLevel1(GadmFeature level1) {
    this.level1 = level1;
  }

  public GadmFeature getLevel2() {
    return level2;
  }

  public void setLevel2(GadmFeature level2) {
    this.level2 = level2;
  }

  public GadmFeature getLevel3() {
    return level3;
  }

  public void setLevel3(GadmFeature level3) {
    this.level3 = level3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Gadm gadm = (Gadm) o;
    return Objects.equals(level0, gadm.level0) &&
      Objects.equals(level1, gadm.level1) &&
      Objects.equals(level2, gadm.level2) &&
      Objects.equals(level3, gadm.level3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level0, level1, level2, level3);
  }

  @Override
  public String toString() {
    return "Gadm{" +
      "level0=" + level0 +
      ", level1=" + level1 +
      ", level2=" + level2 +
      ", level3=" + level3 +
      '}';
  }
}
