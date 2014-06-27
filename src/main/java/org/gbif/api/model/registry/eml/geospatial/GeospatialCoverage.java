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
package org.gbif.api.model.registry.eml.geospatial;

import com.google.common.base.Objects;


public class GeospatialCoverage implements Geometry {

  private String description;

  private BoundingBox boundingBox;

  public GeospatialCoverage() {
  }

  public GeospatialCoverage(String description, BoundingBox boundingBox) {
    this.description = description;
    this.boundingBox = boundingBox;
  }

  public BoundingBox getBoundingBox() {
    return boundingBox;
  }

  public void setBoundingBox(BoundingBox boundingBox) {
    // ensure globalCoverage is set
    boundingBox.setGlobalCoverage(boundingBox.getMinLatitude(),
                                  boundingBox.getMaxLatitude(),
                                  boundingBox.getMinLongitude(),
                                  boundingBox.getMaxLongitude());
    this.boundingBox = boundingBox;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toWellKnownText() {
    return boundingBox == null ? null : boundingBox.toWellKnownText();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof org.gbif.api.model.registry.eml.geospatial.GeospatialCoverage)) {
      return false;
    }

    org.gbif.api.model.registry.eml.geospatial.GeospatialCoverage
      that = (org.gbif.api.model.registry.eml.geospatial.GeospatialCoverage) obj;
    return Objects.equal(this.description, that.description) && Objects.equal(this.boundingBox, that.boundingBox);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(description, boundingBox);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("description", description).add("boundingBox", boundingBox).toString();
  }

}
