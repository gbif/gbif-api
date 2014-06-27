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

import org.gbif.api.vocabulary.Country;
import org.gbif.api.vocabulary.Language;

import java.util.Map;
import java.util.UUID;
import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Provides building of addresses for reading the cube.
 * This class ensures the type safety of dimensions as they are added to the builder.
 */
@NotThreadSafe
public class ReadBuilder {

  private final Map<Dimension<?>, String> address = Maps.newHashMap();

  /**
   * Adds an country type dimension to the address.
   */
  public ReadBuilder at(Dimension<Country> dim, Country value) {
    Preconditions.checkNotNull(value, "Dimension cannot be null");
    address.put(dim, value.getIso2LetterCode());
    return this;
  }

  /**
   * Adds an language type dimension to the address.
   */
  public ReadBuilder at(Dimension<Language> dim, Language value) {
    Preconditions.checkNotNull(value, "Dimension cannot be null");
    address.put(dim, value.getIso2LetterCode());
    return this;
  }

  /**
   * Adds an enumerated type dimension to the address.
   */
  public ReadBuilder at(Dimension<? extends Enum<?>> dim, Enum<?> value) {
    Preconditions.checkNotNull(value, "Dimension cannot be null");
    address.put(dim, value.name());
    return this;
  }

  /**
   * Adds a boolen typed dimension to the address.
   */
  public ReadBuilder at(Dimension<Boolean> dim, boolean value) {
    address.put(dim, String.valueOf(value));
    return this;
  }

  /**
   * Adds a double typed dimension to the address.
   */
  public ReadBuilder at(Dimension<Double> dim, double value) {
    address.put(dim, String.valueOf(value));
    return this;
  }

  /**
   * Adds a float typed dimension to the address.
   */
  public ReadBuilder at(Dimension<Float> dim, float value) {
    address.put(dim, String.valueOf(value));
    return this;
  }

  /**
   * Adds an integer typed dimension to the address.
   */
  public ReadBuilder at(Dimension<Integer> dim, int value) {
    address.put(dim, String.valueOf(value));
    return this;
  }

  /**
   * Adds an String typed dimension to the address.
   */
  public ReadBuilder at(Dimension<String> dim, String value) {
    Preconditions.checkNotNull(value, "Dimension cannot be null");
    address.put(dim, value);
    return this;
  }

  /**
   * Adds a UUID typed dimension to the address.
   */
  public ReadBuilder at(Dimension<UUID> dim, UUID value) {
    Preconditions.checkNotNull(value, "Dimension cannot be null");
    address.put(dim, value.toString());
    return this;
  }

  /**
   * @return The built address.
   */
  public Map<Dimension<?>, String> build() {
    return address;
  }
}
