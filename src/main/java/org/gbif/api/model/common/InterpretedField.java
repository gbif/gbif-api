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
package org.gbif.api.model.common;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Allows for encapsulating a verbatim and an interpreted value of any type.
 */
@Deprecated
public class InterpretedField<V, I> {

  private V verbatim;
  private I interpreted;

  public InterpretedField() {
  }

  public InterpretedField(V verbatim, I interpreted) {
    this.verbatim = verbatim;
    this.interpreted = interpreted;
  }

  public I getInterpreted() {
    return interpreted;
  }

  public void setInterpreted(I interpreted) {
    this.interpreted = interpreted;
  }

  public V getVerbatim() {
    return verbatim;
  }

  public void setVerbatim(V verbatim) {
    this.verbatim = verbatim;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterpretedField<?, ?> that = (InterpretedField<?, ?>) o;
    return Objects.equals(verbatim, that.verbatim) &&
      Objects.equals(interpreted, that.interpreted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(verbatim, interpreted);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InterpretedField.class.getSimpleName() + "[", "]")
      .add("verbatim=" + verbatim)
      .add("interpreted=" + interpreted)
      .toString();
  }
}
