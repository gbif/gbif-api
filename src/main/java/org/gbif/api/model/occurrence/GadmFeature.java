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

import javax.validation.constraints.NotNull;

/**
 * A <a href="https://gadm.org">GADM</a> region feature.
 */
public class GadmFeature {

  private String gid;
  private String name;

  public GadmFeature() {
  }

  public GadmFeature(String gid, String name) {
    this.gid = gid;
    this.name = name;
  }

  public GadmFeature(String name) {
    this.name = name;
  }

  public String getGid() {
    return gid;
  }

  public GadmFeature setGid(String gid) {
    this.gid = gid;
    return this;
  }

  @NotNull
  public String getName() {
    return name;
  }

  public GadmFeature setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GadmFeature that = (GadmFeature) o;
    return gid == that.gid &&
      Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gid, name);
  }

  @Override
  public String toString() {
    return "GadmFeature{" +
      "gid='" + gid + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
