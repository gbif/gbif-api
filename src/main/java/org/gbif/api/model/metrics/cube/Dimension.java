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

import com.google.common.base.Objects;

/**
 * A typed dimension to a cube, which is constructed with a unique key. This key defines the HTTP parameter name during
 * serialization, and the type is respected by the {@link ReadBuilder} during lookups.
 *
 * @param <T> The value type supported for the dimension
 */
public class Dimension<T> {
  // not final to enable easy Jackson serialization
  private String key;
  private Class<T> type;

  /**
   * Force the provision of the key in construction, which should be unique for the cube.
   *
   * @param key Which is used in the HTTP parameters
   * @param type For the dimension, stored to enable runtime checking where required
   */
  public Dimension(String key, Class<T> type) {
    this.key = key;
    this.type = type;
  }

  /**
   * Prefer use of the constructor taking the parameters over this method.
   * This is provided to allow easy Jackson serialization.
   */
  public Dimension() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Dimension)) {
      return false;
    }
    if (obj instanceof Dimension) {
      Dimension<?> that = (Dimension<?>) obj;
      return Objects.equal(this.key, that.key);
    }
    return false;
  }

  public String getKey() {
    return key;
  }

  public Class<T> getType() {
    return type;
  }


  public void setKey(String key) {
    this.key = key;
  }

  public void setType(Class<T> type) {
    this.type = type;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key,type);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", key).add("type", type).toString();
  }
}
