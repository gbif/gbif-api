/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.metrics.cube;

import java.util.Arrays;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

/**
 * A rollup defines an addressable count that is maintained for a set of dimensions.
 */
public class Rollup {

  // Not final, only to enable easy Jackson using default constructor
  private Set<Dimension<?>> dimensions;

  /**
   * Prefer use of the constructor taking the parameters over this method.
   * This is provided to allow easy Jackson serialization.
   */
  public Rollup() {
  }

  public Rollup(Dimension<?>... d) {
    this(new ImmutableSet.Builder<Dimension<?>>().addAll(Arrays.asList(d)).build());
  }

  public Rollup(Set<Dimension<?>> components) {
    this.dimensions = ImmutableSet.copyOf(components); // defensive copy
  }

  public Set<Dimension<?>> getDimensions() {
    return dimensions;
  }

  /**
   * Where possible use the constructor and not this version
   */
  public void setDimensions(Set<Dimension<?>> dimensions) {
    this.dimensions = dimensions;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("dimensions", dimensions).toString();
  }
}
